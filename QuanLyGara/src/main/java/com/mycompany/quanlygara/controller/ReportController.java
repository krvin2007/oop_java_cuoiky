/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlygara.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author ManhQuynh
 */
public class ReportController implements IReportService {

    // Lấy giá trị của thuộc tính Revenue
    @Override
    public double getRevenue(String fromDate, String toDate) throws Exception {
        double totalRevenue = 0;
        SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");

        StringBuilder sql = new StringBuilder("SELECT SUM(tong_tien) FROM hoa_don WHERE 1=1");

        Date start = null;
        Date end = null;

        if (fromDate != null && !fromDate.trim().isEmpty()) {
            start = sdfInput.parse(fromDate.trim());
            sql.append(" AND ngay_thanh_toan >= ?");
        }
        if (toDate != null && !toDate.trim().isEmpty()) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdfInput.parse(toDate.trim()));
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            end = cal.getTime();
            sql.append(" AND ngay_thanh_toan <= ?");
        }

        if (start != null && end != null && start.after(end)) {
            throw new Exception("Loi logic: 'Tu ngay' khong duoc lon hon 'Den ngay'!");
        }

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int paramIndex = 1;
            if (start != null) {
                ps.setTimestamp(paramIndex++, new Timestamp(start.getTime()));
            }
            if (end != null) {
                ps.setTimestamp(paramIndex++, new Timestamp(end.getTime()));
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalRevenue = rs.getDouble(1);
                }
            }
        }
        return totalRevenue;
    }

    @Override
    public Map<String, Integer> getMostUsedParts(int limit) throws Exception {
        Map<String, Integer> result = new LinkedHashMap<>();
        String sql = "SELECT lk.ten, SUM(d.so_luong) as total_qty " +
                "FROM chi_tiet_phieu_sua d " +
                "JOIN linh_kien lk ON LOWER(d.ma_hang_muc) = LOWER(lk.ma) " +
                "GROUP BY lk.ten " +
                "ORDER BY total_qty DESC " +
                "LIMIT ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.put(rs.getString("ten"), rs.getInt("total_qty"));
                }
            }
        }
        return result;
    }

    @Override
    public Map<String, Integer> getMostRepairedVehicles(int limit) throws Exception {
        Map<String, Integer> result = new LinkedHashMap<>();
        String sql = "SELECT ro.bien_so, v.hang_xe, v.dong_xe, COUNT(ro.ma_phieu) as repair_count " +
                "FROM phieu_sua_chua ro " +
                "LEFT JOIN xe v ON LOWER(ro.bien_so) = LOWER(v.bien_so) " +
                "GROUP BY ro.bien_so, v.hang_xe, v.dong_xe " +
                "ORDER BY repair_count DESC " +
                "LIMIT ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String lp = rs.getString("bien_so");
                    String brand = rs.getString("hang_xe");
                    String model = rs.getString("dong_xe");
                    String vehicleInfo = (brand != null && model != null) ? (lp + " - " + brand + " " + model) : lp;
                    result.put(vehicleInfo, rs.getInt("repair_count"));
                }
            }
        }
        return result;
    }

    @Override
    public Map<String, Integer> getMostActiveMechanics(int limit) throws Exception {
        Map<String, Integer> result = new LinkedHashMap<>();
        String sql = "SELECT ro.ma_tho_may, m.ten, COUNT(ro.ma_phieu) as active_count " +
                "FROM phieu_sua_chua ro " +
                "LEFT JOIN nhan_vien m ON ro.ma_tho_may = m.id " +
                "GROUP BY ro.ma_tho_may, m.ten " +
                "ORDER BY active_count DESC " +
                "LIMIT ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int mechId = rs.getInt("ma_tho_may");
                    String name = rs.getString("ten");
                    String mechanicName = (name != null) ? name : "Mechanic ID: " + mechId;
                    result.put(mechanicName, rs.getInt("active_count"));
                }
            }
        }
        return result;
    }
}

