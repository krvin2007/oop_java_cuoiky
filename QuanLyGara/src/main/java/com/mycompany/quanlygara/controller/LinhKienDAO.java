
package com.mycompany.quanlygara.controller;

import com.mycompany.quanlygara.model.LinhKien;
import com.mycompany.quanlygara.exception.LinhKienHetHangException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LinhKienDAO implements IKhoLuuTru<LinhKien> {

    
    @Override
    public void themMoi(LinhKien lk) throws Exception {
        try (Connection conn = KetNoiCSDL.getConnection()) {
            if (lk.getMa() == null || lk.getMa().trim().isEmpty()) {
                int maxId = 0;
                try (PreparedStatement ps = conn.prepareStatement("SELECT ma FROM linh_kien");
                     ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        try {
                            String cleanMa = rs.getString("ma").replace("LK", "");
                            int idNum = Integer.parseInt(cleanMa);
                            if (idNum > maxId) {
                                maxId = idNum;
                            }
                        } catch (Exception ignored) {
                        }
                    }
                }
                lk.setMa("LK" + String.format("%03d", maxId + 1));
            } else {
                try (PreparedStatement ps = conn.prepareStatement("SELECT ma FROM linh_kien WHERE LOWER(ma) = LOWER(?)")) {
                    ps.setString(1, lk.getMa());
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            throw new Exception("Ma linh kien '" + lk.getMa() + "' da ton tai trong kho!");
                        }
                    }
                }
            }
            try (PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO linh_kien (ma, ten, don_gia, so_luong_ton, vi_tri) VALUES (?, ?, ?, ?, ?)")) {
                ps.setString(1, lk.getMa());
                ps.setString(2, lk.getTen());
                ps.setDouble(3, lk.getDonGia());
                ps.setInt(4, lk.getSoLuongTon());
                ps.setString(5, lk.getLocation());
                ps.executeUpdate();
            }
        }
    }

    
    @Override
    public void capNhat(LinhKien lk) throws Exception {
        String sql = "UPDATE linh_kien SET ten = ?, don_gia = ?, so_luong_ton = ?, vi_tri = ? WHERE LOWER(ma) = LOWER(?)";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, lk.getTen());
            ps.setDouble(2, lk.getDonGia());
            ps.setInt(3, lk.getSoLuongTon());
            ps.setString(4, lk.getLocation());
            ps.setString(5, lk.getMa());
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new Exception("Linh kien can cap nhat khong ton tai!");
            }
        }
    }

    
    @Override
    public void xoa(Object id) throws Exception {
        String ma = (String) id;
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE linh_kien SET da_xoa = TRUE WHERE LOWER(ma) = LOWER(?)")) {
            ps.setString(1, ma);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new Exception("Khong tim thay linh kien co ma '" + ma + "' de xoa!");
            }
        }
    }

    
    @Override
    public List<LinhKien> layTatCa() throws Exception {
        
        return querySql("SELECT ma, ten, don_gia, so_luong_ton, vi_tri FROM linh_kien WHERE da_xoa = FALSE ORDER BY ma", null);
    }

    
    @Override
    public LinhKien layTheoId(Object id) throws Exception {
        String ma = (String) id;
        List<LinhKien> list = querySql("SELECT ma, ten, don_gia, so_luong_ton, vi_tri FROM linh_kien WHERE LOWER(ma) = LOWER(?) AND da_xoa = FALSE", ma);
        return list.isEmpty() ? null : list.get(0);
    }

    
    public List<LinhKien> searchByName(String keyword) throws Exception {
        return querySql("SELECT ma, ten, don_gia, so_luong_ton, vi_tri FROM linh_kien WHERE LOWER(ten) LIKE ? AND da_xoa = FALSE ORDER BY ma",
                "%" + keyword.toLowerCase() + "%");
    }

    
    public List<LinhKien> searchByPriceRange(double minPrice, double maxPrice) throws Exception {
        List<LinhKien> list = new ArrayList<>();
        String sql = "SELECT ma, ten, don_gia, so_luong_ton, vi_tri FROM linh_kien WHERE don_gia >= ? AND don_gia <= ? AND da_xoa = FALSE ORDER BY don_gia ASC";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, minPrice);
            ps.setDouble(2, maxPrice);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        }
        return list;
    }

    
    public void deductQuantity(String ma, int qtyToDeduct) throws Exception {
        try (Connection conn = KetNoiCSDL.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(
                    "UPDATE linh_kien SET so_luong_ton = so_luong_ton - ? WHERE LOWER(ma) = LOWER(?) AND so_luong_ton >= ? AND da_xoa = FALSE")) {
                ps.setInt(1, qtyToDeduct);
                ps.setString(2, ma);
                ps.setInt(3, qtyToDeduct);
                int rows = ps.executeUpdate();
                if (rows == 0) {
                    throw new LinhKienHetHangException("Linh kien voi ma " + ma + " khong ton tai hoac khong du so luong ton kho!");
                }
            }
        }
    }

    
    public List<LinhKien> sortByPrice(boolean ascending) throws Exception {
        String order = ascending ? "ASC" : "DESC";
        
        return querySql("SELECT ma, ten, don_gia, so_luong_ton, vi_tri FROM linh_kien WHERE da_xoa = FALSE ORDER BY don_gia " + order, null);
    }

    
    public List<LinhKien> sortByQuantity(boolean ascending) throws Exception {
        String order = ascending ? "ASC" : "DESC";
        
        return querySql("SELECT ma, ten, don_gia, so_luong_ton, vi_tri FROM linh_kien WHERE da_xoa = FALSE ORDER BY so_luong_ton " + order, null);
    }

    
    private List<LinhKien> querySql(String sql, String param) throws Exception {
        List<LinhKien> list = new ArrayList<>();
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            if (param != null) {
                ps.setString(1, param);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        }
        return list;
    }

    
    private LinhKien mapRow(ResultSet rs) throws SQLException {
        return new LinhKien(rs.getString("ma"), rs.getString("ten"), rs.getDouble("don_gia"), rs.getInt("so_luong_ton"), rs.getString("vi_tri"));
    }
}

