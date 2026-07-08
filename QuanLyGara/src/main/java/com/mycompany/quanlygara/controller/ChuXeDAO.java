/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlygara.controller;

import com.mycompany.quanlygara.model.Customer;
import com.mycompany.quanlygara.model.DuplicateMaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ManhQuynh
 */
public class ChuXeDAO implements IRepository<Customer> {

    @Override
    public void themMoi(Customer Customer) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            if (Customer.getId() > 0) {
                try (PreparedStatement ps = conn.prepareStatement("SELECT id FROM owners WHERE id = ?")) {
                    ps.setInt(1, Customer.getId());
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            throw new DuplicateMaException("Ma chu xe '" + Customer.getId() + "' da ton tai!");
                        }
                    }
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT id FROM owners WHERE phone = ?")) {
                ps.setString(1, Customer.getPhone());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        throw new Exception("So dien thoai '" + Customer.getPhone() + "' da ton tai!");
                    }
                }
            }

            if (Customer.getId() > 0) {
                try (PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO owners (id, name, phone, address, email) VALUES (?, ?, ?, ?, ?)")) {
                    ps.setInt(1, Customer.getId());
                    ps.setString(2, Customer.getName());
                    ps.setString(3, Customer.getPhone());
                    ps.setString(4, Customer.getAddress());
                    ps.setString(5, Customer.getEmail());
                    ps.executeUpdate();
                }
            } else {
                try (PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO owners (name, phone, address, email) VALUES (?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, Customer.getName());
                    ps.setString(2, Customer.getPhone());
                    ps.setString(3, Customer.getAddress());
                    ps.setString(4, Customer.getEmail());
                    ps.executeUpdate();
                    try (ResultSet keys = ps.getGeneratedKeys()) {
                        if (keys.next()) {
                            Customer.setId(keys.getInt(1));
                        }
                    }
                }
            }
        }
    }

    @Override
    public void capNhat(Customer Customer) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT id FROM owners WHERE phone = ? AND id <> ?")) {
                ps.setString(1, Customer.getPhone());
                ps.setInt(2, Customer.getId());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        throw new Exception("So dien thoai '" + Customer.getPhone() + "' da bi trung voi chu xe khac!");
                    }
                }
            }
            try (PreparedStatement ps = conn.prepareStatement(
                    "UPDATE owners SET name = ?, phone = ?, address = ?, email = ? WHERE id = ?")) {
                ps.setString(1, Customer.getName());
                ps.setString(2, Customer.getPhone());
                ps.setString(3, Customer.getAddress());
                ps.setString(4, Customer.getEmail());
                ps.setInt(5, Customer.getId());
                int rows = ps.executeUpdate();
                if (rows == 0) {
                    throw new Exception("Chu xe can cap nhat khong ton tai!");
                }
            }
        }
    }

    @Override
    public void xoa(Object id) throws Exception {
        int targetId = (Integer) id;
        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM vehicles WHERE owner_id = ?")) {
                ps.setInt(1, targetId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        throw new Exception("Không thể xóa chủ xe có ID = " + targetId + " vì người này đang có xe trong hệ thống!");
                    }
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("UPDATE owners SET is_deleted = TRUE WHERE id = ?")) {
                ps.setInt(1, targetId);
                int rows = ps.executeUpdate();
                if (rows == 0) {
                    throw new Exception("Khong tim thay chu xe co ID = " + targetId + " de xoa!");
                }
            }
        }
    }

    @Override
    public List<Customer> layTatCa() throws Exception {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT id, name, phone, address, email, is_deleted FROM owners WHERE is_deleted = FALSE ORDER BY id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    @Override
    public Customer layTheoId(Object id) throws Exception {
        int targetId = (Integer) id;
        String sql = "SELECT id, name, phone, address, email, is_deleted FROM owners WHERE id = ? AND is_deleted = FALSE";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, targetId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    public List<Customer> searchByName(String keyword) throws Exception {
        List<Customer> result = new ArrayList<>();
        String sql = "SELECT id, name, phone, address, email, is_deleted FROM owners WHERE LOWER(name) LIKE ? AND is_deleted = FALSE ORDER BY id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword.toLowerCase() + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(mapRow(rs));
                }
            }
        }
        return result;
    }

    private Customer mapRow(ResultSet rs) throws SQLException {
        Customer Customer = new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("phone"), rs.getString("address"), rs.getString("email"));
        Customer.setDeleted(rs.getBoolean("is_deleted"));
        return Customer;
    }
}
