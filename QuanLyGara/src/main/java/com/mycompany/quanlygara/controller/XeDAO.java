
package com.mycompany.quanlygara.controller;

import com.mycompany.quanlygara.model.Xe;
import com.mycompany.quanlygara.exception.BienSoKhongHopLeException;
import com.mycompany.quanlygara.exception.KhongTimThayXeException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class XeDAO implements IKhoLuuTru<Xe> {

    
    @Override
    public void themMoi(Xe vehicle) throws Exception {
        try (Connection conn = KetNoiCSDL.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(
                    "SELECT bien_so FROM xe WHERE LOWER(bien_so) = LOWER(?) AND da_xoa = FALSE")) {
                ps.setString(1, vehicle.getLicensePlate());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        throw new BienSoKhongHopLeException("Bien so xe '" + vehicle.getLicensePlate() + "' da ton tai!");
                    }
                }
            }
            try (PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO xe (bien_so, hang_xe, dong_xe, nam_san_xuat, ma_chu_xe, mau_sac, tinh_trang_tiep_nhan) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
                ps.setString(1, vehicle.getLicensePlate());
                ps.setString(2, vehicle.getBrand());
                ps.setString(3, vehicle.getModel());
                ps.setInt(4, vehicle.getProductionYear());
                ps.setInt(5, vehicle.getOwner() != null ? vehicle.getOwner().getId() : 0);
                ps.setString(6, vehicle.getColor());
                ps.setString(7, vehicle.getCondition());
                ps.executeUpdate();
            }
        }
    }

    
    @Override
    public void capNhat(Xe vehicle) throws Exception {
        String sql = "UPDATE xe SET hang_xe = ?, dong_xe = ?, nam_san_xuat = ?, ma_chu_xe = ?, mau_sac = ?, tinh_trang_tiep_nhan = ? WHERE LOWER(bien_so) = LOWER(?)";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, vehicle.getBrand());
            ps.setString(2, vehicle.getModel());
            ps.setInt(3, vehicle.getProductionYear());
            ps.setInt(4, vehicle.getOwner() != null ? vehicle.getOwner().getId() : 0);
            ps.setString(5, vehicle.getColor());
            ps.setString(6, vehicle.getCondition());
            ps.setString(7, vehicle.getLicensePlate());
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new KhongTimThayXeException("Xe can cap nhat khong ton tai!");
            }
        }
    }

    
    @Override
    public void xoa(Object id) throws Exception {
        String lp = (String) id;
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE xe SET da_xoa = TRUE WHERE LOWER(bien_so) = LOWER(?)")) {
            ps.setString(1, lp);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new KhongTimThayXeException("Khong tim thay xe co bien so '" + lp + "' de xoa!");
            }
        }
    }

    
    @Override
    public List<Xe> layTatCa() throws Exception {
        List<Xe> list = new ArrayList<>();
        String sql = "SELECT bien_so, hang_xe, dong_xe, nam_san_xuat, ma_chu_xe, mau_sac, tinh_trang_tiep_nhan, da_xoa FROM xe WHERE da_xoa = FALSE ORDER BY bien_so";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    
    @Override
    public Xe layTheoId(Object id) throws Exception {
        String lp = (String) id;
        String sql = "SELECT bien_so, hang_xe, dong_xe, nam_san_xuat, ma_chu_xe, mau_sac, tinh_trang_tiep_nhan, da_xoa FROM xe WHERE LOWER(bien_so) = LOWER(?) AND da_xoa = FALSE";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, lp);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    
    public List<Xe> searchByLicensePlate(String lp) throws Exception {
        List<Xe> result = new ArrayList<>();
        String sql = "SELECT bien_so, hang_xe, dong_xe, nam_san_xuat, ma_chu_xe, mau_sac, tinh_trang_tiep_nhan, da_xoa FROM xe WHERE LOWER(bien_so) LIKE ? AND da_xoa = FALSE ORDER BY bien_so";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + lp.toLowerCase() + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(mapRow(rs));
                }
            }
        }
        return result;
    }

    
    public List<Xe> searchByOwnerName(String ownerName) throws Exception {
        List<Xe> result = new ArrayList<>();
        String sql = "SELECT v.bien_so, v.hang_xe, v.dong_xe, v.nam_san_xuat, v.ma_chu_xe, v.mau_sac, v.tinh_trang_tiep_nhan, v.da_xoa "
                + "FROM xe v JOIN chu_xe o ON v.ma_chu_xe = o.id "
                + "WHERE LOWER(o.ten) LIKE ? AND v.da_xoa = FALSE ORDER BY v.bien_so";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + ownerName.toLowerCase() + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(mapRow(rs));
                }
            }
        }
        return result;
    }

    
    private Xe mapRow(ResultSet rs) throws SQLException {
        int ownerId = rs.getInt("ma_chu_xe");
        com.mycompany.quanlygara.model.ChuXe ChuXe = null;
        try {
            ChuXeDAO chuXeDAO = new ChuXeDAO();
            ChuXe = chuXeDAO.layTheoId(ownerId);
        } catch (Exception e) {
            System.out.println("Loi khi lay thong tin chu xe: " + e.getMessage());
        }
        
        Xe vehicle = new Xe(rs.getString("bien_so"), rs.getString("hang_xe"), rs.getString("dong_xe"),
                rs.getInt("nam_san_xuat"), ChuXe, rs.getString("mau_sac"), rs.getString("tinh_trang_tiep_nhan"));
        vehicle.setDeleted(rs.getBoolean("da_xoa"));
        return vehicle;
    }

    public List<Xe> layDanhSachDaXoa() throws Exception {
        List<Xe> list = new ArrayList<>();
        String sql = "SELECT bien_so, hang_xe, dong_xe, nam_san_xuat, ma_chu_xe, mau_sac, tinh_trang_tiep_nhan, da_xoa FROM xe WHERE da_xoa = TRUE ORDER BY bien_so";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    public void khoiPhuc(String lp) throws Exception {
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE xe SET da_xoa = FALSE WHERE LOWER(bien_so) = LOWER(?)")) {
            ps.setString(1, lp);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new Exception("Khong tim thay xe co bien so '" + lp + "' trong Thung rac!");
            }
        }
    }

    public void xoaVinhVien(String lp) throws Exception {
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM xe WHERE LOWER(bien_so) = LOWER(?)")) {
            ps.setString(1, lp);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new Exception("Khong tim thay xe co bien so '" + lp + "' de xoa vinh vien!");
            }
        }
    }
}

