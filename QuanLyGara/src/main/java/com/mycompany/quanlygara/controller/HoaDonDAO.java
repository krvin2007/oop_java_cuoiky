
package com.mycompany.quanlygara.controller;

import com.mycompany.quanlygara.model.HoaDon;
import com.mycompany.quanlygara.model.ChiTietPhieuSua;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HoaDonDAO implements IKhoLuuTru<HoaDon> {

    
    @Override
    public void themMoi(HoaDon invoice) throws Exception {
        try (Connection conn = KetNoiCSDL.getConnection()) {
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

    
    @Override
    public void capNhat(HoaDon invoice) throws Exception {
        String sql = "UPDATE hoa_don SET ma_phieu = ?, ngay_thanh_toan = ?, tong_tien_linh_kien = ?, tong_tien_cong = ?, thue_vat = ?, tong_tien = ? WHERE ma_hoa_don = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
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

    
    @Override
    public void xoa(Object id) throws Exception {
        int targetId = (Integer) id;
        try (Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement("DELETE FROM hoa_don WHERE ma_hoa_don = ?")) {
            ps.setInt(1, targetId);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new Exception("Khong tim thay hoa don co ID = " + targetId + " de xoa!");
            }
        }
    }

    
    @Override
    public List<HoaDon> layTatCa() throws Exception {
        return querySql(
                "SELECT ma_hoa_don, ma_phieu, ngay_thanh_toan, tong_tien_linh_kien, tong_tien_cong, thue_vat, tong_tien FROM hoa_don ORDER BY ma_hoa_don",
                null);
    }

    
    @Override
    public HoaDon layTheoId(Object id) throws Exception {
        int targetId = (Integer) id;
        List<HoaDon> list = querySql(
                "SELECT ma_hoa_don, ma_phieu, ngay_thanh_toan, tong_tien_linh_kien, tong_tien_cong, thue_vat, tong_tien FROM hoa_don WHERE ma_hoa_don = ?",
                targetId);
        return list.isEmpty() ? null : list.get(0);
    }

    
    public HoaDon getByOrderId(int orderId) throws Exception {
        List<HoaDon> list = querySql(
                "SELECT ma_hoa_don, ma_phieu, ngay_thanh_toan, tong_tien_linh_kien, tong_tien_cong, thue_vat, tong_tien FROM hoa_don WHERE ma_phieu = ?",
                orderId);
        return list.isEmpty() ? null : list.get(0);
    }

    
    public HoaDon generateInvoice(int orderId) throws Exception {
        PhieuSuaChuaDAO roDAO = new PhieuSuaChuaDAO();

        List<ChiTietPhieuSua> details = roDAO.getDetailsByOrderId(orderId);
        double totalPartCost = 0;
        double totalLaborCost = 0;

        for (int i = 0; i < details.size(); i++) {
            ChiTietPhieuSua d = details.get(i);
            if ("LINHKIEN".equalsIgnoreCase(d.getLoaiHangMuc())) {
                totalPartCost += d.getDonGiaThucTe() * d.getSoLuong();
            } else if ("DICHVU".equalsIgnoreCase(d.getLoaiHangMuc())) {
                totalLaborCost += d.getDonGiaThucTe() * d.getSoLuong();
            }
        }

        HoaDon invoice = new HoaDon();
        com.mycompany.quanlygara.model.PhieuSuaChua ro = roDAO.layTheoId(orderId);
        invoice.setRepairOrder(ro);
        invoice.setTotalPartCost(totalPartCost);
        invoice.setTotalLaborCost(totalLaborCost);
        invoice.setVatRate(0.10); 
        invoice.calculateTotal();

        return invoice;
    }

    
    private List<HoaDon> querySql(String sql, Integer idParam) throws Exception {
        List<HoaDon> list = new ArrayList<>();
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

    
    private HoaDon mapRow(ResultSet rs) throws SQLException {
        Timestamp pay = rs.getTimestamp("ngay_thanh_toan");
        int orderId = rs.getInt("ma_phieu");
        com.mycompany.quanlygara.model.PhieuSuaChua ro = null;
        try {
            PhieuSuaChuaDAO phieuDAO = new PhieuSuaChuaDAO();
            ro = phieuDAO.layTheoId(orderId);
        } catch (Exception e) {
            System.out.println("Loi load phieu sua chua cho hoa don: " + e.getMessage());
        }

        return new HoaDon(
                rs.getInt("ma_hoa_don"),
                ro,
                pay != null ? new Date(pay.getTime()) : null,
                rs.getDouble("tong_tien_linh_kien"),
                rs.getDouble("tong_tien_cong"),
                rs.getDouble("thue_vat"),
                rs.getDouble("tong_tien"));
    }
}

