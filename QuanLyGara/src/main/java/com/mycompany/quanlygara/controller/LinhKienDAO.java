/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlygara.controller;

import com.mycompany.quanlygara.model.LinhKien;
import com.mycompany.quanlygara.exception.PartOutOfStockException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ManhQuynh
 */
public class LinhKienDAO implements IRepository<LinhKien> {

    // Thêm mới một bản ghi vào cơ sở dữ liệu
    @Override
    public void themMoi(LinhKien lk) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
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

    // Cập nhật thông tin bản ghi trong cơ sở dữ liệu
    @Override
    public void capNhat(LinhKien lk) throws Exception {
        String sql = "UPDATE linh_kien SET ten = ?, don_gia = ?, so_luong_ton = ?, vi_tri = ? WHERE LOWER(ma) = LOWER(?)";
        try (Connection conn = DBConnection.getConnection();
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

    // Xóa bản ghi khỏi cơ sở dữ liệu
    @Override
    public void xoa(Object id) throws Exception {
        String ma = (String) id;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE linh_kien SET da_xoa = TRUE WHERE LOWER(ma) = LOWER(?)")) {
            ps.setString(1, ma);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new Exception("Khong tim thay linh kien co ma '" + ma + "' de xoa!");
            }
        }
    }

    // Lấy toàn bộ danh sách dữ liệu
    @Override
    public List<LinhKien> layTatCa() throws Exception {
        // Thực thi phương thức querySql để xử lý logic tương ứng
        return querySql("SELECT ma, ten, don_gia, so_luong_ton, vi_tri FROM linh_kien WHERE da_xoa = FALSE ORDER BY ma", null);
    }

    // Lấy dữ liệu chi tiết theo mã định danh (ID)
    @Override
    public LinhKien layTheoId(Object id) throws Exception {
        String ma = (String) id;
        List<LinhKien> list = querySql("SELECT ma, ten, don_gia, so_luong_ton, vi_tri FROM linh_kien WHERE LOWER(ma) = LOWER(?) AND da_xoa = FALSE", ma);
        return list.isEmpty() ? null : list.get(0);
    }

    // Tìm kiếm dữ liệu dựa trên điều kiện đầu vào
    public List<LinhKien> searchByName(String keyword) throws Exception {
        return querySql("SELECT ma, ten, don_gia, so_luong_ton, vi_tri FROM linh_kien WHERE LOWER(ten) LIKE ? AND da_xoa = FALSE ORDER BY ma",
                "%" + keyword.toLowerCase() + "%");
    }

    // Tìm kiếm dữ liệu dựa trên điều kiện đầu vào
    public List<LinhKien> searchByPriceRange(double minPrice, double maxPrice) throws Exception {
        List<LinhKien> list = new ArrayList<>();
        String sql = "SELECT ma, ten, don_gia, so_luong_ton, vi_tri FROM linh_kien WHERE don_gia >= ? AND don_gia <= ? AND da_xoa = FALSE ORDER BY don_gia ASC";
        try (Connection conn = DBConnection.getConnection();
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

    // Thực thi phương thức deductQuantity để xử lý logic tương ứng
    public void deductQuantity(String ma, int qtyToDeduct) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(
                    "UPDATE linh_kien SET so_luong_ton = so_luong_ton - ? WHERE LOWER(ma) = LOWER(?) AND so_luong_ton >= ? AND da_xoa = FALSE")) {
                ps.setInt(1, qtyToDeduct);
                ps.setString(2, ma);
                ps.setInt(3, qtyToDeduct);
                int rows = ps.executeUpdate();
                if (rows == 0) {
                    throw new PartOutOfStockException("Linh kiện với mã " + ma + " không tồn tại hoặc không đủ số lượng tồn kho!");
                }
            }
        }
    }

    // Sắp xếp danh sách dữ liệu
    public List<LinhKien> sortByPrice(boolean ascending) throws Exception {
        String order = ascending ? "ASC" : "DESC";
        // Thực thi phương thức querySql để xử lý logic tương ứng
        return querySql("SELECT ma, ten, don_gia, so_luong_ton, vi_tri FROM linh_kien WHERE da_xoa = FALSE ORDER BY don_gia " + order, null);
    }

    // Sắp xếp danh sách dữ liệu
    public List<LinhKien> sortByQuantity(boolean ascending) throws Exception {
        String order = ascending ? "ASC" : "DESC";
        // Thực thi phương thức querySql để xử lý logic tương ứng
        return querySql("SELECT ma, ten, don_gia, so_luong_ton, vi_tri FROM linh_kien WHERE da_xoa = FALSE ORDER BY so_luong_ton " + order, null);
    }

    // Thực thi phương thức querySql để xử lý logic tương ứng
    private List<LinhKien> querySql(String sql, String param) throws Exception {
        List<LinhKien> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
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

    // Ánh xạ dữ liệu từ ResultSet sang đối tượng Java
    private LinhKien mapRow(ResultSet rs) throws SQLException {
        return new LinhKien(rs.getString("ma"), rs.getString("ten"), rs.getDouble("don_gia"), rs.getInt("so_luong_ton"), rs.getString("vi_tri"));
    }
}

