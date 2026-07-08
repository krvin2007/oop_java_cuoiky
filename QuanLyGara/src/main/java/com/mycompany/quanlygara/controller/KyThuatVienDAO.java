package com.mycompany.quanlygara.controller;

import com.mycompany.quanlygara.model.Mechanic;
import com.mycompany.quanlygara.model.DuplicateMaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class KyThuatVienDAO implements IRepository<Mechanic> {

    @Override
    public void themMoi(Mechanic mechanic) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            if (mechanic.getId() > 0) {
                try (PreparedStatement ps = conn.prepareStatement("SELECT id FROM employees WHERE id = ?")) {
                    ps.setInt(1, mechanic.getId());
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            throw new DuplicateMaException("Ma ky thuat vien '" + mechanic.getId() + "' da ton tai!");
                        }
                    }
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT id FROM employees WHERE phone = ?")) {
                ps.setString(1, mechanic.getPhone());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        throw new Exception("So dien thoai '" + mechanic.getPhone() + "' da ton tai cho mot nhan vien khac!");
                    }
                }
            }
            if (mechanic.getStatus() == null) {
                mechanic.setStatus("Đang rảnh");
            }
            
            String uname = mechanic.getUsername() != null && !mechanic.getUsername().isEmpty() ? mechanic.getUsername() : mechanic.getPhone();
            String pwd = mechanic.getPassword() != null && !mechanic.getPassword().isEmpty() ? mechanic.getPassword() : "123456";

            if (mechanic.getId() > 0) {
                try (PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO employees (id, name, phone, address, username, password, role, specialization, salary, status) VALUES (?, ?, ?, ?, ?, ?, 'KyThuat', ?, ?, ?)")) {
                    ps.setInt(1, mechanic.getId());
                    ps.setString(2, mechanic.getName());
                    ps.setString(3, mechanic.getPhone());
                    ps.setString(4, mechanic.getAddress());
                    ps.setString(5, uname);
                    ps.setString(6, pwd);
                    ps.setString(7, mechanic.getSpec());
                    ps.setDouble(8, mechanic.getSalary());
                    ps.setString(9, mechanic.getStatus());
                    ps.executeUpdate();
                }
            } else {
                try (PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO employees (name, phone, address, username, password, role, specialization, salary, status) VALUES (?, ?, ?, ?, ?, 'KyThuat', ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, mechanic.getName());
                    ps.setString(2, mechanic.getPhone());
                    ps.setString(3, mechanic.getAddress());
                    ps.setString(4, uname);
                    ps.setString(5, pwd);
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
    public void capNhat(Mechanic mechanic) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT id FROM employees WHERE phone = ? AND id <> ?")) {
                ps.setString(1, mechanic.getPhone());
                ps.setInt(2, mechanic.getId());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        throw new Exception("So dien thoai '" + mechanic.getPhone() + "' da trung voi mot nhan vien khac!");
                    }
                }
            }
            try (PreparedStatement ps = conn.prepareStatement(
                    "UPDATE employees SET name = ?, phone = ?, address = ?, username = ?, password = ?, specialization = ?, salary = ?, status = ? WHERE id = ? AND role = 'KyThuat'")) {
                ps.setString(1, mechanic.getName());
                ps.setString(2, mechanic.getPhone());
                ps.setString(3, mechanic.getAddress());
                ps.setString(4, mechanic.getUsername());
                ps.setString(5, mechanic.getPassword());
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
        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM repair_orders WHERE mechanic_id = ? AND status != 'COMPLETED'")) {
                ps.setInt(1, targetId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        throw new Exception("Không thể xóa thợ có ID = " + targetId + " vì người này đang đảm nhận phiếu sửa chữa chưa hoàn thành!");
                    }
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("UPDATE employees SET is_deleted = TRUE WHERE id = ? AND role = 'KyThuat'")) {
                ps.setInt(1, targetId);
                int rows = ps.executeUpdate();
                if (rows == 0) {
                    throw new Exception("Khong tim thay ky thuat vien co ID = " + targetId + " de xoa!");
                }
            }
        }
    }

    @Override
    public List<Mechanic> layTatCa() throws Exception {
        return querySql("SELECT id, name, phone, address, username, password, specialization, salary, status, is_deleted FROM employees WHERE role = 'KyThuat' AND is_deleted = FALSE ORDER BY id", null);
    }

    @Override
    public Mechanic layTheoId(Object id) throws Exception {
        int targetId = (Integer) id;
        List<Mechanic> list = querySql(
                "SELECT id, name, phone, address, username, password, specialization, salary, status, is_deleted FROM employees WHERE id = ? AND role = 'KyThuat' AND is_deleted = FALSE", targetId);
        return list.isEmpty() ? null : list.get(0);
    }

    public List<Mechanic> sortBySalary(boolean ascending) throws Exception {
        String order = ascending ? "ASC" : "DESC";
        return querySql("SELECT id, name, phone, address, username, password, specialization, salary, status, is_deleted FROM employees WHERE role = 'KyThuat' AND is_deleted = FALSE ORDER BY salary " + order, null);
    }

    public List<Mechanic> sortByName() throws Exception {
        return querySql("SELECT id, name, phone, address, username, password, specialization, salary, status, is_deleted FROM employees WHERE role = 'KyThuat' AND is_deleted = FALSE ORDER BY name ASC", null);
    }

    private List<Mechanic> querySql(String sql, Integer idParam) throws Exception {
        List<Mechanic> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
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

    private Mechanic mapRow(ResultSet rs) throws SQLException {
        Mechanic mechanic = new Mechanic(
            rs.getInt("id"), 
            rs.getString("name"), 
            rs.getString("phone"), 
            rs.getString("address"),
            rs.getString("username"),
            rs.getString("password"),
            rs.getDouble("salary"), 
            rs.getString("status"),
            rs.getString("specialization")
        );
        mechanic.setDeleted(rs.getBoolean("is_deleted"));
        return mechanic;
    }
}
