/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlygara.controller;

import com.mycompany.quanlygara.model.HangMuc;
import com.mycompany.quanlygara.model.LinhKien;
import com.mycompany.quanlygara.model.DichVu;
import com.mycompany.quanlygara.model.RepairOrder;
import com.mycompany.quanlygara.model.RepairOrderDetail;
import com.mycompany.quanlygara.model.Mechanic;
import com.mycompany.quanlygara.exception.PartOutOfStockException;
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
public class PhieuSuaChuaDAO implements IRepository<RepairOrder> {

    // Thêm mới một bản ghi vào cơ sở dữ liệu
    @Override
    public void themMoi(RepairOrder order) throws Exception {
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO phieu_sua_chua (bien_so, ngay_vao, ngay_ra, ma_tho_may, trang_thai, tinh_trang_ben_ngoai) VALUES (?, ?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, order.getVehicle() != null ? order.getVehicle().getLicensePlate() : null);
            ps.setTimestamp(2, order.getEntryDate() != null ? new Timestamp(order.getEntryDate().getTime()) : null);
            ps.setTimestamp(3, order.getExitDate() != null ? new Timestamp(order.getExitDate().getTime()) : null);
            ps.setInt(4, order.getMechanic() != null ? order.getMechanic().getId() : 0);
            ps.setString(5, order.getStatus());
            ps.setString(6, order.getVisualCondition());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    order.setOrderId(keys.getInt(1));
                }
            }
        }

        // Cap nhat trang thai ky thuat vien thanh "Đang bận"
        KyThuatVienDAO mechanicDAO = new KyThuatVienDAO();
        Mechanic m = mechanicDAO.layTheoId(order.getMechanic() != null ? order.getMechanic().getId() : 0);
        if (m != null) {
            m.setStatus("Đang bận");
            mechanicDAO.capNhat(m);
        }
    }

    // Cập nhật thông tin bản ghi trong cơ sở dữ liệu
    @Override
    public void capNhat(RepairOrder order) throws Exception {
        RepairOrder existing = layTheoId(order.getOrderId());
        if (existing == null) {
            throw new Exception("Phieu sua chua can cap nhat khong ton tai!");
        }
        if ("COMPLETED".equalsIgnoreCase(existing.getStatus())) {
            throw new Exception("Loi: Khong the cap nhat phieu sua chua da hoan tat (COMPLETED)!");
        }
        String oldStatus = existing.getStatus();
        int oldMechanicId = existing.getMechanic() != null ? existing.getMechanic().getId() : 0;
        int newMechanicId = order.getMechanic() != null ? order.getMechanic().getId() : 0;

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(
                        "UPDATE phieu_sua_chua SET bien_so = ?, ngay_vao = ?, ngay_ra = ?, ma_tho_may = ?, trang_thai = ?, tinh_trang_ben_ngoai = ? WHERE ma_phieu = ?")) {
            ps.setString(1, order.getVehicle() != null ? order.getVehicle().getLicensePlate() : null);
            ps.setTimestamp(2, order.getEntryDate() != null ? new Timestamp(order.getEntryDate().getTime()) : null);
            ps.setTimestamp(3, order.getExitDate() != null ? new Timestamp(order.getExitDate().getTime()) : null);
            ps.setInt(4, newMechanicId);
            ps.setString(5, order.getStatus());
            ps.setString(6, order.getVisualCondition());
            ps.setInt(7, order.getOrderId());
            ps.executeUpdate();
        }

        if (oldMechanicId != newMechanicId) {
            updateMechanicStatusSafe(oldMechanicId);
        }
        updateMechanicStatusSafe(newMechanicId);
    }

    // Xóa bản ghi khỏi cơ sở dữ liệu
    @Override
    public void xoa(Object id) throws Exception {
        int targetId = (Integer) id;
        RepairOrder toRemove = layTheoId(targetId);
        if (toRemove == null) {
            throw new Exception("Khong tim thay phieu sua chua co ID = " + targetId + " de xoa!");
        }

        try (Connection conn = DBConnection.getConnection()) {
            // Kiem tra xem co Invoice nao dang tro toi RepairOrder nay khong
            try (PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM hoa_don WHERE ma_phieu = ?")) {
                ps.setInt(1, targetId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        throw new Exception("Loi: Khong the xoa Phieu sua chua da duoc xuat Hoa don (Invoice)!");
                    }
                }
            }

            // Hoan lai linh kien ve kho neu co
            try (PreparedStatement ps = conn
                    .prepareStatement("SELECT ma_hang_muc, so_luong FROM chi_tiet_phieu_sua WHERE ma_phieu = ?")) {
                ps.setInt(1, targetId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String ma = rs.getString("ma_hang_muc");
                        int sl = rs.getInt("so_luong");
                        if (ma.toUpperCase().startsWith("LK")) {
                            try (PreparedStatement psUpdate = conn.prepareStatement(
                                    "UPDATE linh_kien SET so_luong_ton = so_luong_ton + ? WHERE ma = ?")) {
                                psUpdate.setInt(1, sl);
                                psUpdate.setString(2, ma);
                                psUpdate.executeUpdate();
                            }
                        }
                    }
                }
            }

            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM chi_tiet_phieu_sua WHERE ma_phieu = ?")) {
                ps.setInt(1, targetId);
                ps.executeUpdate();
            }
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM phieu_sua_chua WHERE ma_phieu = ?")) {
                ps.setInt(1, targetId);
                ps.executeUpdate();
            }
        }

        // Cap nhat lai trang thai tho sau khi xoa phieu
        int mId = toRemove.getMechanic() != null ? toRemove.getMechanic().getId() : 0;
        updateMechanicStatusSafe(mId);
    }

    private void updateMechanicStatusSafe(int mechanicId) {
        if (mechanicId <= 0)
            return;
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT COUNT(*) FROM phieu_sua_chua WHERE ma_tho_may = ? AND trang_thai != 'COMPLETED'")) {
            ps.setInt(1, mechanicId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int activeCount = rs.getInt(1);
                    KyThuatVienDAO mechanicDAO = new KyThuatVienDAO();
                    Mechanic m = mechanicDAO.layTheoId(mechanicId);
                    if (m != null) {
                        if (activeCount > 0) {
                            m.setStatus("Đang bận");
                        } else {
                            m.setStatus("Đang rảnh");
                        }
                        mechanicDAO.capNhat(m);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Loi cap nhat trang thai tho: " + e.getMessage());
        }
    }

    // Lấy toàn bộ danh sách dữ liệu
    @Override
    public List<RepairOrder> layTatCa() throws Exception {
        List<RepairOrder> list = new ArrayList<>();
        String sql = "SELECT ma_phieu, bien_so, ngay_vao, ngay_ra, ma_tho_may, trang_thai, tinh_trang_ben_ngoai FROM phieu_sua_chua ORDER BY ma_phieu";
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
    public RepairOrder layTheoId(Object id) throws Exception {
        int targetId = (Integer) id;
        String sql = "SELECT ma_phieu, bien_so, ngay_vao, ngay_ra, ma_tho_may, trang_thai, tinh_trang_ben_ngoai FROM phieu_sua_chua WHERE ma_phieu = ?";
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

    // Thêm một chi tiết vào danh sách phiếu sửa chữa
    public void addDetail(RepairOrderDetail detail) throws Exception {
        RepairOrder ro = layTheoId(detail.getOrderId());
        if (ro != null && "COMPLETED".equalsIgnoreCase(ro.getStatus())) {
            throw new Exception("Lỗi: Không thể thêm vào phiếu đã hoàn tất (COMPLETED)!");
        }

        try (Connection conn = DBConnection.getConnection()) {
            Integer existingQty = null;
            try (PreparedStatement ps = conn.prepareStatement(
                    "SELECT so_luong FROM chi_tiet_phieu_sua WHERE ma_phieu = ? AND LOWER(ma_hang_muc) = LOWER(?) AND loai_hang_muc = ?")) {
                ps.setInt(1, detail.getOrderId());
                ps.setString(2, detail.getMaHangMuc());
                ps.setString(3, detail.getLoaiHangMuc());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        existingQty = rs.getInt("so_luong");
                    }
                }
            }
            if (existingQty != null) {
                try (PreparedStatement ps = conn.prepareStatement(
                        "UPDATE chi_tiet_phieu_sua SET so_luong = ?, don_gia_thuc_te = ? WHERE ma_phieu = ? AND LOWER(ma_hang_muc) = LOWER(?) AND loai_hang_muc = ?")) {
                    ps.setInt(1, existingQty + detail.getSoLuong());
                    ps.setDouble(2, detail.getDonGiaThucTe());
                    ps.setInt(3, detail.getOrderId());
                    ps.setString(4, detail.getMaHangMuc());
                    ps.setString(5, detail.getLoaiHangMuc());
                    ps.executeUpdate();
                }
            } else {
                try (PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO chi_tiet_phieu_sua (ma_phieu, ma_hang_muc, loai_hang_muc, don_gia_thuc_te, so_luong) VALUES (?, ?, ?, ?, ?)")) {
                    ps.setInt(1, detail.getOrderId());
                    ps.setString(2, detail.getMaHangMuc());
                    ps.setString(3, detail.getLoaiHangMuc());
                    ps.setDouble(4, detail.getDonGiaThucTe());
                    ps.setInt(5, detail.getSoLuong());
                    ps.executeUpdate();
                }
            }
        }
    }

    public void xoaChiTiet(int orderId, String maHangMuc) throws Exception {
        RepairOrder ro = layTheoId(orderId);
        if (ro != null && "COMPLETED".equalsIgnoreCase(ro.getStatus())) {
            throw new Exception("Lỗi: Không thể xóa chi tiết của phiếu đã hoàn tất (COMPLETED)!");
        }

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                int sl = 0;
                try (PreparedStatement ps = conn.prepareStatement(
                        "SELECT so_luong FROM chi_tiet_phieu_sua WHERE ma_phieu = ? AND LOWER(ma_hang_muc) = LOWER(?)")) {
                    ps.setInt(1, orderId);
                    ps.setString(2, maHangMuc);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            sl = rs.getInt("so_luong");
                        } else {
                            throw new Exception("Khong tim thay chi tiet nay trong phieu!");
                        }
                    }
                }

                if (maHangMuc.toUpperCase().startsWith("LK")) {
                    try (PreparedStatement psUpdate = conn.prepareStatement(
                            "UPDATE linh_kien SET so_luong_ton = so_luong_ton + ? WHERE LOWER(ma) = LOWER(?)")) {
                        psUpdate.setInt(1, sl);
                        psUpdate.setString(2, maHangMuc);
                        psUpdate.executeUpdate();
                    }
                }

                try (PreparedStatement ps = conn.prepareStatement(
                        "DELETE FROM chi_tiet_phieu_sua WHERE ma_phieu = ? AND LOWER(ma_hang_muc) = LOWER(?)")) {
                    ps.setInt(1, orderId);
                    ps.setString(2, maHangMuc);
                    ps.executeUpdate();
                }

                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    public void themMoiChiTietVaTruKho(RepairOrderDetail detail) throws Exception {
        RepairOrder ro = layTheoId(detail.getOrderId());
        if (ro != null && "COMPLETED".equalsIgnoreCase(ro.getStatus())) {
            throw new Exception("Lỗi: Không thể thêm linh kiện vào phiếu đã hoàn tất (COMPLETED)!");
        }

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // Bắt đầu Transaction
            try {
                if (detail.getMaHangMuc().toUpperCase().startsWith("LK")) {
                    try (PreparedStatement psUpdate = conn.prepareStatement(
                            "UPDATE linh_kien SET so_luong_ton = so_luong_ton - ? WHERE LOWER(ma) = LOWER(?) AND so_luong_ton >= ?")) {
                        psUpdate.setInt(1, detail.getSoLuong());
                        psUpdate.setString(2, detail.getMaHangMuc());
                        psUpdate.setInt(3, detail.getSoLuong());
                        int rows = psUpdate.executeUpdate();
                        if (rows == 0) {
                            throw new PartOutOfStockException("Linh kiện không tồn tại hoặc kho không đủ hàng!");
                        }
                    }
                }

                Integer existingQty = null;
                try (PreparedStatement ps = conn.prepareStatement(
                        "SELECT so_luong FROM chi_tiet_phieu_sua WHERE ma_phieu = ? AND LOWER(ma_hang_muc) = LOWER(?) AND loai_hang_muc = ?")) {
                    ps.setInt(1, detail.getOrderId());
                    ps.setString(2, detail.getMaHangMuc());
                    ps.setString(3, detail.getLoaiHangMuc());
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            existingQty = rs.getInt("so_luong");
                        }
                    }
                }
                if (existingQty != null) {
                    try (PreparedStatement ps = conn.prepareStatement(
                            "UPDATE chi_tiet_phieu_sua SET so_luong = ?, don_gia_thuc_te = ? WHERE ma_phieu = ? AND LOWER(ma_hang_muc) = LOWER(?) AND loai_hang_muc = ?")) {
                        ps.setInt(1, existingQty + detail.getSoLuong());
                        ps.setDouble(2, detail.getDonGiaThucTe());
                        ps.setInt(3, detail.getOrderId());
                        ps.setString(4, detail.getMaHangMuc());
                        ps.setString(5, detail.getLoaiHangMuc());
                        ps.executeUpdate();
                    }
                } else {
                    try (PreparedStatement ps = conn.prepareStatement(
                            "INSERT INTO chi_tiet_phieu_sua (ma_phieu, ma_hang_muc, loai_hang_muc, don_gia_thuc_te, so_luong) VALUES (?, ?, ?, ?, ?)")) {
                        ps.setInt(1, detail.getOrderId());
                        ps.setString(2, detail.getMaHangMuc());
                        ps.setString(3, detail.getLoaiHangMuc());
                        ps.setDouble(4, detail.getDonGiaThucTe());
                        ps.setInt(5, detail.getSoLuong());
                        ps.executeUpdate();
                    }
                }
                conn.commit(); // Thành công cả 2 mới ghi DB
            } catch (Exception e) {
                conn.rollback(); // Lỗi thì khôi phục lại
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    // Lấy giá trị của thuộc tính DetailsByOrderId
    public List<RepairOrderDetail> getDetailsByOrderId(int orderId) throws Exception {
        List<RepairOrderDetail> result = new ArrayList<>();
        String sql = "SELECT ma_phieu, ma_hang_muc, loai_hang_muc, don_gia_thuc_te, so_luong FROM chi_tiet_phieu_sua WHERE ma_phieu = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(new RepairOrderDetail(rs.getInt("ma_phieu"), rs.getString("ma_hang_muc"),
                            rs.getString("loai_hang_muc"), rs.getDouble("don_gia_thuc_te"), rs.getInt("so_luong")));
                }
            }
        }
        return result;
    }

    /**
     * Lay toan bo chi tiet phieu sua chua (dung cho bao cao thong ke).
     * Thay the cho JsonStorage.loadList("chi_tiet_phieu_sua.json", ...) truoc day.
     */
    public List<RepairOrderDetail> getAllDetails() throws Exception {
        List<RepairOrderDetail> result = new ArrayList<>();
        String sql = "SELECT ma_phieu, ma_hang_muc, loai_hang_muc, don_gia_thuc_te, so_luong FROM chi_tiet_phieu_sua";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(new RepairOrderDetail(rs.getInt("ma_phieu"), rs.getString("ma_hang_muc"),
                        rs.getString("loai_hang_muc"), rs.getDouble("don_gia_thuc_te"), rs.getInt("so_luong")));
            }
        }
        return result;
    }

    // Lấy giá trị của thuộc tính DanhSachChiTiet
    public List<HangMuc> getDanhSachChiTiet(int orderId) throws Exception {
        List<RepairOrderDetail> details = getDetailsByOrderId(orderId);
        List<HangMuc> danhSachChiTiet = new ArrayList<>();
        LinhKienDAO lkDAO = new LinhKienDAO();
        DichVuDAO dvDAO = new DichVuDAO();
        for (int i = 0; i < details.size(); i++) {
            RepairOrderDetail d = details.get(i);
            if ("LINHKIEN".equalsIgnoreCase(d.getLoaiHangMuc())) {
                LinhKien lk = lkDAO.layTheoId(d.getMaHangMuc());
                if (lk == null) {
                    lk = new LinhKien();
                    lk.setMa(d.getMaHangMuc());
                    lk.setTen("Linh kiện (Đã bị xóa khỏi kho)");
                }
                lk.setDonGia(d.getDonGiaThucTe()); // Override current price with recorded price
                danhSachChiTiet.add(lk);
            } else if ("DICHVU".equalsIgnoreCase(d.getLoaiHangMuc())) {
                DichVu dv = dvDAO.layTheoId(d.getMaHangMuc());
                if (dv == null) {
                    dv = new DichVu();
                    dv.setMa(d.getMaHangMuc());
                    dv.setTen("Dịch vụ (Đã bị xóa)");
                }
                dv.setDonGia(d.getDonGiaThucTe());
                danhSachChiTiet.add(dv);
            }
        }
        return danhSachChiTiet;
    }

    // Ánh xạ dữ liệu từ ResultSet sang đối tượng Java
    private RepairOrder mapRow(ResultSet rs) throws SQLException {
        Timestamp entry = rs.getTimestamp("ngay_vao");
        Timestamp exit = rs.getTimestamp("ngay_ra");
        int orderId = rs.getInt("ma_phieu");
        String licensePlate = rs.getString("bien_so");
        int mechanicId = rs.getInt("ma_tho_may");
        String status = rs.getString("trang_thai");
        String visualCondition = rs.getString("tinh_trang_ben_ngoai");

        com.mycompany.quanlygara.model.Vehicle vehicle = null;
        com.mycompany.quanlygara.model.Mechanic mechanic = null;
        java.util.List<RepairOrderDetail> detailList = new ArrayList<>();

        try {
            XeDAO xeDAO = new XeDAO();
            vehicle = xeDAO.layTheoId(licensePlate);

            KyThuatVienDAO kyThuatVienDAO = new KyThuatVienDAO();
            mechanic = kyThuatVienDAO.layTheoId(mechanicId);

            detailList = getDetailsByOrderId(orderId);
        } catch (Exception e) {
            System.out.println("Loi khi load thong tin chi tiet cho phieu sua chua: " + e.getMessage());
        }

        RepairOrder ro = new RepairOrder(
                orderId,
                vehicle,
                entry != null ? new Date(entry.getTime()) : null,
                exit != null ? new Date(exit.getTime()) : null,
                mechanic,
                status,
                visualCondition);
        ro.setDetails(detailList);
        return ro;
    }
}
