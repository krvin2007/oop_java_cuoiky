package com.mycompany.quanlygara.controller;

import com.mycompany.quanlygara.model.KyThuatVien;
import com.mycompany.quanlygara.model.DuplicateMaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class KyThuatVienDAO implements IKhoLuuTru<KyThuatVien> {

    
    @Override
    public void themMoi(KyThuatVien mechanic) throws Exception {
        try (Connection conn = KetNoiCSDL.getConnection()) {
            if (mechanic.getId() > 0) {
                try (PreparedStatement ps = conn.prepareStatement("SELECT id FROM nhan_vien WHERE id = ?")) {
                    ps.setInt(1, mechanic.getId());
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            throw new DuplicateMaException("Ma ky thuat vien '" + mechanic.getId() + "' da ton tai!");
                        }
                    }
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT id FROM nhan_vien WHERE sdt = ? AND da_xoa = FALSE")) {
                ps.setString(1, mechanic.getPhone());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        throw new Exception("So dien thoai '" + mechanic.getPhone() + "' da ton tai cho mot nhan vien khac!");
                    }
                }
            }
            if (mechanic.getStatus() == null) {
                mechanic.setStatus("Dang ranh");
            }
            
            String uname = mechanic.getUsername() != null && !mechanic.getUsername().isEmpty() ? mechanic.getUsername() : mechanic.getPhone();
            String pwd = mechanic.getPassword() != null && !mechanic.getPassword().isEmpty() ? mechanic.getPassword() : "123456";
            String hashedPwd = com.mycompany.quanlygara.util.TienIchChuoi.hashPassword(pwd);

            if (mechanic.getId() > 0) {
                try (PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO nhan_vien (id, ten, sdt, dia_chi, ten_dang_nhap, mat_khau, vai_tro, chuyen_mon, luong, trang_thai) VALUES (?, ?, ?, ?, ?, ?, 'KyThuat', ?, ?, ?)")) {
                    ps.setInt(1, mechanic.getId());
                    ps.setString(2, mechanic.getName());
                    ps.setString(3, mechanic.getPhone());
                    ps.setString(4, mechanic.getAddress());
                    ps.setString(5, uname);
                    ps.setString(6, hashedPwd);
                    ps.setString(7, mechanic.getSpec());
                    ps.setDouble(8, mechanic.getSalary());
                    ps.setString(9, mechanic.getStatus());
                    ps.executeUpdate();
                }
            } else {
                try (PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO nhan_vien (ten, sdt, dia_chi, ten_dang_nhap, mat_khau, vai_tro, chuyen_mon, luong, trang_thai) VALUES (?, ?, ?, ?, ?, 'KyThuat', ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, mechanic.getName());
                    ps.setString(2, mechanic.getPhone());
                    ps.setString(3, mechanic.getAddress());
                    ps.setString(4, uname);
                    ps.setString(5, hashedPwd);
                    ps.setString(6, mechanic.getSpec());
                    ps.setDouble(7, mechanic.getSalary());
                    ps.setString(8, mechanic.getStatus());
                    ps.executeUpdate();
                    try (ResultSet keys = ps.getGeneratedKeys()) {
                        if (keys.next()) {
                            mechanic.setId(keys.getInt(1));
                        }
                    }
                }
            }
        }
    }

    
    @Override
    public void capNhat(KyThuatVien mechanic) throws Exception {
        try (Connection conn = KetNoiCSDL.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT id FROM nhan_vien WHERE sdt = ? AND id <> ? AND da_xoa = FALSE")) {
                ps.setString(1, mechanic.getPhone());
                ps.setInt(2, mechanic.getId());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        throw new Exception("So dien thoai '" + mechanic.getPhone() + "' da trung voi mot nhan vien khac!");
                    }
                }
            }
            try (PreparedStatement ps = conn.prepareStatement(
                    "UPDATE nhan_vien SET ten = ?, sdt = ?, dia_chi = ?, ten_dang_nhap = ?, mat_khau = ?, chuyen_mon = ?, luong = ?, trang_thai = ? WHERE id = ? AND vai_tro = 'KyThuat'")) {
                ps.setString(1, mechanic.getName());
                ps.setString(2, mechanic.getPhone());
                ps.setString(3, mechanic.getAddress());
                ps.setString(4, mechanic.getUsername());
                ps.setString(5, com.mycompany.quanlygara.util.TienIchChuoi.hashPassword(mechanic.getPassword()));
                ps.setString(6, mechanic.getSpec());
                ps.setDouble(7, mechanic.getSalary());
                ps.setString(8, mechanic.getStatus());
                ps.setInt(9, mechanic.getId());
                int rows = ps.executeUpdate();
                if (rows == 0) {
                    throw new Exception("Ky thuat vien can cap nhat khong ton tai!");
                }
            }
        }
    }

    
    @Override
    public void xoa(Object id) throws Exception {
        int targetId = (Integer) id;
        try (Connection conn = KetNoiCSDL.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM phieu_sua_chua WHERE ma_tho_may = ? AND trang_thai != 'COMPLETED'")) {
                ps.setInt(1, targetId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        throw new Exception("Khong the xoa tho co ID = " + targetId + " vi nguoi nay dang dam nhan phieu sua chua chua hoan thanh!");
                    }
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("UPDATE nhan_vien SET da_xoa = TRUE WHERE id = ? AND vai_tro = 'KyThuat'")) {
                ps.setInt(1, targetId);
                int rows = ps.executeUpdate();
                if (rows == 0) {
                    throw new Exception("Khong tim thay ky thuat vien co ID = " + targetId + " de xoa!");
                }
            }
        }
    }

    
    @Override
    public List<KyThuatVien> layTatCa() throws Exception {
        
        return querySql("SELECT id, ten, sdt, dia_chi, ten_dang_nhap, mat_khau, chuyen_mon, luong, trang_thai, da_xoa FROM nhan_vien WHERE vai_tro = 'KyThuat' AND da_xoa = FALSE ORDER BY id", null);
    }

    
    @Override
    public KyThuatVien layTheoId(Object id) throws Exception {
        int targetId = (Integer) id;
        List<KyThuatVien> list = querySql(
                "SELECT id, ten, sdt, dia_chi, ten_dang_nhap, mat_khau, chuyen_mon, luong, trang_thai, da_xoa FROM nhan_vien WHERE id = ? AND vai_tro = 'KyThuat' AND da_xoa = FALSE", targetId);
        return list.isEmpty() ? null : list.get(0);
    }

    
    public List<KyThuatVien> sortBySalary(boolean ascending) throws Exception {
        String order = ascending ? "ASC" : "DESC";
        
        return querySql("SELECT id, ten, sdt, dia_chi, ten_dang_nhap, mat_khau, chuyen_mon, luong, trang_thai, da_xoa FROM nhan_vien WHERE vai_tro = 'KyThuat' AND da_xoa = FALSE ORDER BY luong " + order, null);
    }

    
    public List<KyThuatVien> sortByName() throws Exception {
        
        return querySql("SELECT id, ten, sdt, dia_chi, ten_dang_nhap, mat_khau, chuyen_mon, luong, trang_thai, da_xoa FROM nhan_vien WHERE vai_tro = 'KyThuat' AND da_xoa = FALSE ORDER BY ten ASC", null);
    }

    
    private List<KyThuatVien> querySql(String sql, Integer idParam) throws Exception {
        List<KyThuatVien> list = new ArrayList<>();
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            if (idParam != null) {
                ps.setInt(1, idParam);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        }
        return list;
    }

    
    private KyThuatVien mapRow(ResultSet rs) throws SQLException {
        KyThuatVien mechanic = new KyThuatVien(
            rs.getInt("id"), 
            rs.getString("ten"), 
            rs.getString("sdt"), 
            rs.getString("dia_chi"),
            rs.getString("ten_dang_nhap"),
            rs.getString("mat_khau"),
            rs.getDouble("luong"), 
            rs.getString("trang_thai"),
            rs.getString("chuyen_mon")
        );
        mechanic.setDeleted(rs.getBoolean("da_xoa"));
        return mechanic;
    }
}

