package com.mycompany.quanlygara.controller;

import com.mycompany.quanlygara.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {
    
    public Employee login(String username, String password) {
        String sql = "SELECT id, name, phone, address, username, password, role, specialization, salary, status FROM employees WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String role = rs.getString("role");
                    int id = rs.getInt("id");
                    String n = rs.getString("name");
                    String p = rs.getString("phone");
                    String a = rs.getString("address");
                    String u = rs.getString("username");
                    String pw = rs.getString("password");
                    double sal = rs.getDouble("salary");
                    String st = rs.getString("status");
                    String spec = rs.getString("specialization");
                    
                    if (role.equals("QuanLy")) {
                        return new Manager(id, n, p, a, u, pw, sal, st);
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
}
