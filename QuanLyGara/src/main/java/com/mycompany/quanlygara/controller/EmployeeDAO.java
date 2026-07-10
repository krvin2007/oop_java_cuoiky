package com.mycompany.quanlygara.controller;

import com.mycompany.quanlygara.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class EmployeeDAO {
    
    // Xác thực thông tin đăng nhập của nhân viên và trả về đối tượng Employee tương ứng nếu thành công
    public Employee login(String username, String password) {
        String sql = "SELECT id, ten, sdt, dia_chi, ten_dang_nhap, mat_khau, vai_tro, chuyen_mon, luong, trang_thai FROM nhan_vien WHERE BINARY ten_dang_nhap = ? AND BINARY mat_khau = ? AND da_xoa = FALSE";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, username);
            ps.setString(2, com.mycompany.quanlygara.util.StringUtils.hashPassword(password));
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
                        return new Owner(id, n, p, a, u, pw, sal, st);
                    } else if (role.equals("KeToan")) {
                        return new Accountant(id, n, p, a, u, pw, sal, st);
                    } else if (role.equals("ThuKho")) {
                        return new Storekeeper(id, n, p, a, u, pw, sal, st);
                    } else if (role.equals("KyThuat")) {
                        return new Mechanic(id, n, p, a, u, pw, sal, st, spec);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Loi xac thuc User: " + e.getMessage());
        }
        return null;
    }

    // Cập nhật mật khẩu mới cho tài khoản nhân viên dựa trên tên đăng nhập
    public boolean updatePassword(String username, String newPassword) {
        String sql = "UPDATE nhan_vien SET mat_khau = ? WHERE ten_dang_nhap = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, com.mycompany.quanlygara.util.StringUtils.hashPassword(newPassword));
            ps.setString(2, username);
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            System.out.println("Loi khi doi mat khau: " + e.getMessage());
            return false;
        }
    }

    public List<Employee> getAllAccounts() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT id, ten, sdt, dia_chi, ten_dang_nhap, mat_khau, vai_tro, chuyen_mon, luong, trang_thai FROM nhan_vien WHERE da_xoa = FALSE";
        try (Connection conn = DBConnection.getConnection();
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
                    list.add(new Owner(id, n, p, a, u, pw, sal, st));
                } else if (role.equals("KeToan")) {
                    list.add(new Accountant(id, n, p, a, u, pw, sal, st));
                } else if (role.equals("ThuKho")) {
                    list.add(new Storekeeper(id, n, p, a, u, pw, sal, st));
                } else if (role.equals("KyThuat")) {
                    list.add(new Mechanic(id, n, p, a, u, pw, sal, st, spec));
                }
            }
        } catch (SQLException e) {
            System.out.println("Loi lay danh sach tai khoan: " + e.getMessage());
        }
        return list;
    }
}

