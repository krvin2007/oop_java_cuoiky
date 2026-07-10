/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlygara.controller;

import com.mycompany.quanlygara.model.Vehicle;
import com.mycompany.quanlygara.exception.InvalidPlateNumberException;
import com.mycompany.quanlygara.exception.VehicleNotFoundException;
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


public class XeDAO implements IRepository<Vehicle> {

    // Thêm mới một bản ghi vào cơ sở dữ liệu
    @Override
    public void themMoi(Vehicle vehicle) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(
                    "SELECT bien_so FROM xe WHERE LOWER(bien_so) = LOWER(?) AND da_xoa = FALSE")) {
                ps.setString(1, vehicle.getLicensePlate());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        throw new InvalidPlateNumberException("Bien so xe '" + vehicle.getLicensePlate() + "' da ton tai!");
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

    // Cập nhật thông tin bản ghi trong cơ sở dữ liệu
    @Override
    public void capNhat(Vehicle vehicle) throws Exception {
        String sql = "UPDATE xe SET hang_xe = ?, dong_xe = ?, nam_san_xuat = ?, ma_chu_xe = ?, mau_sac = ?, tinh_trang_tiep_nhan = ? WHERE LOWER(bien_so) = LOWER(?)";
        try (Connection conn = DBConnection.getConnection();
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
                throw new VehicleNotFoundException("Xe can cap nhat khong ton tai!");
            }
        }
    }

    // Xóa bản ghi khỏi cơ sở dữ liệu
    @Override
    public void xoa(Object id) throws Exception {
        String lp = (String) id;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE xe SET da_xoa = TRUE WHERE LOWER(bien_so) = LOWER(?)")) {
            ps.setString(1, lp);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new VehicleNotFoundException("Khong tim thay xe co bien so '" + lp + "' de xoa!");
            }
        }
    }

    // Lấy toàn bộ danh sách dữ liệu
    @Override
    public List<Vehicle> layTatCa() throws Exception {
        List<Vehicle> list = new ArrayList<>();
        String sql = "SELECT bien_so, hang_xe, dong_xe, nam_san_xuat, ma_chu_xe, mau_sac, tinh_trang_tiep_nhan, da_xoa FROM xe WHERE da_xoa = FALSE ORDER BY bien_so";
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
    public Vehicle layTheoId(Object id) throws Exception {
        String lp = (String) id;
        String sql = "SELECT bien_so, hang_xe, dong_xe, nam_san_xuat, ma_chu_xe, mau_sac, tinh_trang_tiep_nhan, da_xoa FROM xe WHERE LOWER(bien_so) = LOWER(?) AND da_xoa = FALSE";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, lp);
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
    public List<Vehicle> searchByLicensePlate(String lp) throws Exception {
        List<Vehicle> result = new ArrayList<>();
        String sql = "SELECT bien_so, hang_xe, dong_xe, nam_san_xuat, ma_chu_xe, mau_sac, tinh_trang_tiep_nhan, da_xoa FROM xe WHERE LOWER(bien_so) LIKE ? AND da_xoa = FALSE ORDER BY bien_so";
        try (Connection conn = DBConnection.getConnection();
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

    // Tìm kiếm dữ liệu dựa trên điều kiện đầu vào
    public List<Vehicle> searchByOwnerName(String ownerName) throws Exception {
        List<Vehicle> result = new ArrayList<>();
        String sql = "SELECT v.bien_so, v.hang_xe, v.dong_xe, v.nam_san_xuat, v.ma_chu_xe, v.mau_sac, v.tinh_trang_tiep_nhan, v.da_xoa "
                + "FROM xe v JOIN chu_xe o ON v.ma_chu_xe = o.id "
                + "WHERE LOWER(o.ten) LIKE ? AND v.da_xoa = FALSE ORDER BY v.bien_so";
        try (Connection conn = DBConnection.getConnection();
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

    // Ánh xạ dữ liệu từ ResultSet sang đối tượng Java
    private Vehicle mapRow(ResultSet rs) throws SQLException {
        int ownerId = rs.getInt("ma_chu_xe");
        com.mycompany.quanlygara.model.Customer Customer = null;
        try {
            ChuXeDAO chuXeDAO = new ChuXeDAO();
            Customer = chuXeDAO.layTheoId(ownerId);
        } catch (Exception e) {
            System.out.println("Loi khi lay thong tin chu xe: " + e.getMessage());
        }
        
        Vehicle vehicle = new Vehicle(rs.getString("bien_so"), rs.getString("hang_xe"), rs.getString("dong_xe"),
                rs.getInt("nam_san_xuat"), Customer, rs.getString("mau_sac"), rs.getString("tinh_trang_tiep_nhan"));
        vehicle.setDeleted(rs.getBoolean("da_xoa"));
        return vehicle;
    }
}

