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

    // Thêm mới một bản ghi vào cơ sở dữ liệu
    @Override
    public void themMoi(Customer Customer) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            if (Customer.getId() > 0) {
                try (PreparedStatement ps = conn.prepareStatement("SELECT id FROM chu_xe WHERE id = ?")) {
                    ps.setInt(1, Customer.getId());
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            throw new DuplicateMaException("Ma chu xe '" + Customer.getId() + "' da ton tai!");
                        }
                    }
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT id FROM chu_xe WHERE sdt = ?")) {
                ps.setString(1, Customer.getPhone());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        throw new Exception("So dien thoai '" + Customer.getPhone() + "' da ton tai!");
                    }
                }
            }

            if (Customer.getId() > 0) {
                try (PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO chu_xe (id, ten, sdt, dia_chi, email) VALUES (?, ?, ?, ?, ?)")) {
                    ps.setInt(1, Customer.getId());
                    ps.setString(2, Customer.getName());
                    ps.setString(3, Customer.getPhone());
                    ps.setString(4, Customer.getAddress());
                    ps.setString(5, Customer.getEmail());
                    ps.executeUpdate();
                }
            } else {
                try (PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO chu_xe (ten, sdt, dia_chi, email) VALUES (?, ?, ?, ?)",
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

    // Cập nhật thông tin bản ghi trong cơ sở dữ liệu
    @Override
    public void capNhat(Customer Customer) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT id FROM chu_xe WHERE sdt = ? AND id <> ?")) {
                ps.setString(1, Customer.getPhone());
                ps.setInt(2, Customer.getId());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        throw new Exception("So dien thoai '" + Customer.getPhone() + "' da bi trung voi chu xe khac!");
                    }
                }
            }
            try (PreparedStatement ps = conn.prepareStatement(
                    "UPDATE chu_xe SET ten = ?, sdt = ?, dia_chi = ?, email = ? WHERE id = ?")) {
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

    // Xóa bản ghi khỏi cơ sở dữ liệu
    @Override
    public void xoa(Object id) throws Exception {
        int targetId = (Integer) id;
        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM xe WHERE ma_chu_xe = ?")) {
                ps.setInt(1, targetId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        throw new Exception("Không thể xóa chủ xe có ID = " + targetId + " vì người này đang có xe trong hệ thống!");
                    }
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("UPDATE chu_xe SET da_xoa = TRUE WHERE id = ?")) {
                ps.setInt(1, targetId);
                int rows = ps.executeUpdate();
                if (rows == 0) {
                    throw new Exception("Khong tim thay chu xe co ID = " + targetId + " de xoa!");
                }
            }
        }
    }

    // Lấy toàn bộ danh sách dữ liệu
    @Override
    public List<Customer> layTatCa() throws Exception {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT id, ten, sdt, dia_chi, email, da_xoa FROM chu_xe WHERE da_xoa = FALSE ORDER BY id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    // Lấy dữ liệu chi tiết theo mã định danh (ID)
    @Override
    public Customer layTheoId(Object id) throws Exception {
        int targetId = (Integer) id;
        String sql = "SELECT id, ten, sdt, dia_chi, email, da_xoa FROM chu_xe WHERE id = ? AND da_xoa = FALSE";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, targetId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Ánh xạ dữ liệu từ ResultSet sang đối tượng Java
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    // Tìm kiếm dữ liệu dựa trên điều kiện đầu vào
    public List<Customer> searchByName(String keyword) throws Exception {
        List<Customer> result = new ArrayList<>();
        String sql = "SELECT id, ten, sdt, dia_chi, email, da_xoa FROM chu_xe WHERE LOWER(ten) LIKE ? AND da_xoa = FALSE ORDER BY id";
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

    // Ánh xạ dữ liệu từ ResultSet sang đối tượng Java
    private Customer mapRow(ResultSet rs) throws SQLException {
        Customer Customer = new Customer(rs.getInt("id"), rs.getString("ten"), rs.getString("sdt"), rs.getString("dia_chi"), rs.getString("email"));
        Customer.setDeleted(rs.getBoolean("da_xoa"));
        return Customer;
    }
}

