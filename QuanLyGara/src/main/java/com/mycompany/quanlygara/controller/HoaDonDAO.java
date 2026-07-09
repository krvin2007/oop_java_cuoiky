/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlygara.controller;

import com.mycompany.quanlygara.model.Invoice;
import com.mycompany.quanlygara.model.RepairOrderDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ManhQuynh
 */
public class HoaDonDAO implements IRepository<Invoice> {

    // Thêm mới một hóa đơn vào cơ sở dữ liệu
    @Override
    public void themMoi(Invoice invoice) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            int oId = invoice.getRepairOrder() != null ? invoice.getRepairOrder().getOrderId() : 0;
            try (PreparedStatement ps = conn.prepareStatement("SELECT ma_hoa_don FROM hoa_don WHERE ma_phieu = ?")) {
                ps.setInt(1, oId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        throw new Exception("Hoa don cho phieu sua chua ID = " + oId + " da ton tai!");
                    }
                }
            }
            try (PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO hoa_don (ma_phieu, ngay_thanh_toan, tong_tien_linh_kien, tong_tien_cong, thue_vat, tong_tien) VALUES (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, invoice.getRepairOrder() != null ? invoice.getRepairOrder().getOrderId() : 0);
                ps.setTimestamp(2,
                        invoice.getPaymentDate() != null ? new Timestamp(invoice.getPaymentDate().getTime()) : null);
                ps.setDouble(3, invoice.getTotalPartCost());
                ps.setDouble(4, invoice.getTotalLaborCost());
                ps.setDouble(5, invoice.getVatRate());
                ps.setDouble(6, invoice.getTotalAmount());
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        invoice.setInvoiceId(keys.getInt(1));
                    }
                }
            }
        }
    }

    // Cập nhật thông tin hóa đơn đã tồn tại trong cơ sở dữ liệu
    @Override
    public void capNhat(Invoice invoice) throws Exception {
        String sql = "UPDATE hoa_don SET ma_phieu = ?, ngay_thanh_toan = ?, tong_tien_linh_kien = ?, tong_tien_cong = ?, thue_vat = ?, tong_tien = ? WHERE ma_hoa_don = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, invoice.getRepairOrder() != null ? invoice.getRepairOrder().getOrderId() : 0);
            ps.setTimestamp(2,
                    invoice.getPaymentDate() != null ? new Timestamp(invoice.getPaymentDate().getTime()) : null);
            ps.setDouble(3, invoice.getTotalPartCost());
            ps.setDouble(4, invoice.getTotalLaborCost());
            ps.setDouble(5, invoice.getVatRate());
            ps.setDouble(6, invoice.getTotalAmount());
            ps.setInt(7, invoice.getInvoiceId());
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new Exception("Hoa don can cap nhat khong ton tai!");
            }
        }
    }

    // Xóa hóa đơn khỏi cơ sở dữ liệu dựa trên ID
    @Override
    public void xoa(Object id) throws Exception {
        int targetId = (Integer) id;
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("DELETE FROM hoa_don WHERE ma_hoa_don = ?")) {
            ps.setInt(1, targetId);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new Exception("Khong tim thay hoa don co ID = " + targetId + " de xoa!");
            }
        }
    }

    // Lấy danh sách tất cả hóa đơn từ cơ sở dữ liệu
    @Override
    public List<Invoice> layTatCa() throws Exception {
        return querySql(
                "SELECT ma_hoa_don, ma_phieu, ngay_thanh_toan, tong_tien_linh_kien, tong_tien_cong, thue_vat, tong_tien FROM hoa_don ORDER BY ma_hoa_don",
                null);
    }

    // Lấy thông tin hóa đơn dựa trên ID
    @Override
    public Invoice layTheoId(Object id) throws Exception {
        int targetId = (Integer) id;
        List<Invoice> list = querySql(
                "SELECT ma_hoa_don, ma_phieu, ngay_thanh_toan, tong_tien_linh_kien, tong_tien_cong, thue_vat, tong_tien FROM hoa_don WHERE ma_hoa_don = ?",
                targetId);
        return list.isEmpty() ? null : list.get(0);
    }

    // Lấy thông tin hóa đơn dựa trên mã phiếu sửa chữa (Order ID)
    public Invoice getByOrderId(int orderId) throws Exception {
        List<Invoice> list = querySql(
                "SELECT ma_hoa_don, ma_phieu, ngay_thanh_toan, tong_tien_linh_kien, tong_tien_cong, thue_vat, tong_tien FROM hoa_don WHERE ma_phieu = ?",
                orderId);
        return list.isEmpty() ? null : list.get(0);
    }

    // Tạo hóa đơn tự động từ mã phiếu sửa chữa, bao gồm tính toán tiền linh kiện, tiền công và thuế VAT
    public Invoice generateInvoice(int orderId) throws Exception {
        PhieuSuaChuaDAO roDAO = new PhieuSuaChuaDAO();

        List<RepairOrderDetail> details = roDAO.getDetailsByOrderId(orderId);
        double totalPartCost = 0;
        double totalLaborCost = 0;

        for (int i = 0; i < details.size(); i++) {
            RepairOrderDetail d = details.get(i);
            if ("LINHKIEN".equalsIgnoreCase(d.getLoaiHangMuc())) {
                totalPartCost += d.getDonGiaThucTe() * d.getSoLuong();
            } else if ("DICHVU".equalsIgnoreCase(d.getLoaiHangMuc())) {
                totalLaborCost += d.getDonGiaThucTe() * d.getSoLuong();
            }
        }

        Invoice invoice = new Invoice();
        com.mycompany.quanlygara.model.RepairOrder ro = roDAO.layTheoId(orderId);
        invoice.setRepairOrder(ro);
        invoice.setTotalPartCost(totalPartCost);
        invoice.setTotalLaborCost(totalLaborCost);
        invoice.setVatRate(0.10); // 10% VAT
        invoice.calculateTotal();

        return invoice;
    }

    // Hàm hỗ trợ thực thi truy vấn SQL lấy danh sách hóa đơn dựa trên tham số truyền vào
    private List<Invoice> querySql(String sql, Integer idParam) throws Exception {
        List<Invoice> list = new ArrayList<>();
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

    // Ánh xạ dữ liệu từ ResultSet (kết quả truy vấn) sang đối tượng Invoice
    private Invoice mapRow(ResultSet rs) throws SQLException {
        Timestamp pay = rs.getTimestamp("ngay_thanh_toan");
        int orderId = rs.getInt("ma_phieu");
        com.mycompany.quanlygara.model.RepairOrder ro = null;
        try {
            PhieuSuaChuaDAO phieuDAO = new PhieuSuaChuaDAO();
            ro = phieuDAO.layTheoId(orderId);
        } catch (Exception e) {
            System.out.println("Loi load phieu sua chua cho hoa don: " + e.getMessage());
        }

        return new Invoice(
                rs.getInt("ma_hoa_don"),
                ro,
                pay != null ? new Date(pay.getTime()) : null,
                rs.getDouble("tong_tien_linh_kien"),
                rs.getDouble("tong_tien_cong"),
                rs.getDouble("thue_vat"),
                rs.getDouble("tong_tien"));
    }
}

