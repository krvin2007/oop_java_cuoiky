package com.mycompany.quanlygara.controller;

import com.mycompany.quanlygara.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class NhanVienDAO {
    
    
    public NhanVien login(String username, String password) {
        String sql = "SELECT id, ten, sdt, dia_chi, ten_dang_nhap, mat_khau, vai_tro, chuyen_mon, luong, trang_thai FROM nhan_vien WHERE BINARY ten_dang_nhap = ? AND BINARY mat_khau = ? AND da_xoa = FALSE";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, username);
            ps.setString(2, com.mycompany.quanlygara.util.TienIchChuoi.hashPassword(password));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String role = rs.getString("vai_tro");
                    int id = rs.getInt("id");
                    String n = rs.getString("ten");
                    String p = rs.getString("sdt");
                    String a = rs.getString("dia_chi");
                    String u = rs.getString("ten_dang_nhap");
                    String pw = rs.getString("mat_khau");
                    double sal = rs.getDouble("luong");
                    String st = rs.getString("trang_thai");
                    String spec = rs.getString("chuyen_mon");
                    
                    if (role.equals("QuanLy")) {
                        return new QuanLy(id, n, p, a, u, pw, sal, st);
                    } else if (role.equals("KeToan")) {
                        return new KeToan(id, n, p, a, u, pw, sal, st);
                    } else if (role.equals("ThuKho")) {
                        return new ThuKho(id, n, p, a, u, pw, sal, st);
                    } else if (role.equals("KyThuat")) {
                        return new KyThuatVien(id, n, p, a, u, pw, sal, st, spec);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Loi xac thuc User: " + e.getMessage());
        }
        return null;
    }

    
    public boolean updatePassword(String username, String newPassword) {
        String sql = "UPDATE nhan_vien SET mat_khau = ? WHERE ten_dang_nhap = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, com.mycompany.quanlygara.util.TienIchChuoi.hashPassword(newPassword));
            ps.setString(2, username);
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            System.out.println("Loi khi doi mat khau: " + e.getMessage());
            return false;
        }
    }

    public List<NhanVien> getAllAccounts() {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT id, ten, sdt, dia_chi, ten_dang_nhap, mat_khau, vai_tro, chuyen_mon, luong, trang_thai FROM nhan_vien WHERE da_xoa = FALSE";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             
            while (rs.next()) {
                String role = rs.getString("vai_tro");
                int id = rs.getInt("id");
                String n = rs.getString("ten");
                String p = rs.getString("sdt");
                String a = rs.getString("dia_chi");
                String u = rs.getString("ten_dang_nhap");
                String pw = rs.getString("mat_khau");
                double sal = rs.getDouble("luong");
                String st = rs.getString("trang_thai");
                String spec = rs.getString("chuyen_mon");
                
                if (role.equals("QuanLy")) {
                    list.add(new QuanLy(id, n, p, a, u, pw, sal, st));
                } else if (role.equals("KeToan")) {
                    list.add(new KeToan(id, n, p, a, u, pw, sal, st));
                } else if (role.equals("ThuKho")) {
                    list.add(new ThuKho(id, n, p, a, u, pw, sal, st));
                } else if (role.equals("KyThuat")) {
                    list.add(new KyThuatVien(id, n, p, a, u, pw, sal, st, spec));
                }
            }
        } catch (SQLException e) {
            System.out.println("Loi lay danh sach tai khoan: " + e.getMessage());
        }
        return list;
    }
}

