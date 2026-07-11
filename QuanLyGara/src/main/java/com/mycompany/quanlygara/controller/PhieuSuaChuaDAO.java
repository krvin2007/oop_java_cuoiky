
package com.mycompany.quanlygara.controller;

import com.mycompany.quanlygara.model.HangMuc;
import com.mycompany.quanlygara.model.LinhKien;
import com.mycompany.quanlygara.model.DichVu;
import com.mycompany.quanlygara.model.PhieuSuaChua;
import com.mycompany.quanlygara.model.ChiTietPhieuSua;
import com.mycompany.quanlygara.model.KyThuatVien;
import com.mycompany.quanlygara.exception.LinhKienHetHangException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PhieuSuaChuaDAO implements IKhoLuuTru<PhieuSuaChua> {

    
    @Override
    public void themMoi(PhieuSuaChua order) throws Exception {
        try (Connection conn = KetNoiCSDL.getConnection();
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

        
        KyThuatVienDAO mechanicDAO = new KyThuatVienDAO();
        KyThuatVien m = mechanicDAO.layTheoId(order.getMechanic() != null ? order.getMechanic().getId() : 0);
        if (m != null) {
            m.setStatus("Dang ban");
            mechanicDAO.capNhat(m);
        }
    }

    
    @Override
    public void capNhat(PhieuSuaChua order) throws Exception {
        PhieuSuaChua existing = layTheoId(order.getOrderId());
        if (existing == null) {
            throw new Exception("Phieu sua chua can cap nhat khong ton tai!");
        }
        if ("COMPLETED".equalsIgnoreCase(existing.getStatus())) {
            throw new Exception("Loi: Khong the cap nhat phieu sua chua da hoan tat (COMPLETED)!");
        }

        int oldMechanicId = existing.getMechanic() != null ? existing.getMechanic().getId() : 0;
        int newMechanicId = order.getMechanic() != null ? order.getMechanic().getId() : 0;

        try (Connection conn = KetNoiCSDL.getConnection();
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

    
    @Override
    public void xoa(Object id) throws Exception {
        int targetId = (Integer) id;
        PhieuSuaChua toRemove = layTheoId(targetId);
        if (toRemove == null) {
            throw new Exception("Khong tim thay phieu sua chua co ID = " + targetId + " de xoa!");
        }

        try (Connection conn = KetNoiCSDL.getConnection()) {
            
            try (PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM hoa_don WHERE ma_phieu = ?")) {
                ps.setInt(1, targetId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        throw new Exception("Loi: Khong the xoa Phieu sua chua da duoc xuat Hoa don (HoaDon)!");
                    }
                }
            }

            
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

        
        int mId = toRemove.getMechanic() != null ? toRemove.getMechanic().getId() : 0;
        updateMechanicStatusSafe(mId);
    }

    private void updateMechanicStatusSafe(int mechanicId) {
        if (mechanicId <= 0)
            return;
        try (Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT COUNT(*) FROM phieu_sua_chua WHERE ma_tho_may = ? AND trang_thai != 'COMPLETED'")) {
            ps.setInt(1, mechanicId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int activeCount = rs.getInt(1);
                    KyThuatVienDAO mechanicDAO = new KyThuatVienDAO();
                    KyThuatVien m = mechanicDAO.layTheoId(mechanicId);
                    if (m != null) {
                        if (activeCount > 0) {
                            m.setStatus("Dang ban");
                        } else {
                            m.setStatus("Dang ranh");
                        }
                        mechanicDAO.capNhat(m);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Loi cap nhat trang thai tho: " + e.getMessage());
        }
    }

    
    @Override
    public List<PhieuSuaChua> layTatCa() throws Exception {
        List<PhieuSuaChua> list = new ArrayList<>();
        String sql = "SELECT ma_phieu, bien_so, ngay_vao, ngay_ra, ma_tho_may, trang_thai, tinh_trang_ben_ngoai FROM phieu_sua_chua ORDER BY ma_phieu";
        try (Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    
    @Override
    public PhieuSuaChua layTheoId(Object id) throws Exception {
        int targetId = (Integer) id;
        String sql = "SELECT ma_phieu, bien_so, ngay_vao, ngay_ra, ma_tho_may, trang_thai, tinh_trang_ben_ngoai FROM phieu_sua_chua WHERE ma_phieu = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
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

    
    public void addDetail(ChiTietPhieuSua detail) throws Exception {
        PhieuSuaChua ro = layTheoId(detail.getOrderId());
        if (ro != null && "COMPLETED".equalsIgnoreCase(ro.getStatus())) {
            throw new Exception("Loi: Khong the them vao phieu da hoan tat (COMPLETED)!");
        }

        try (Connection conn = KetNoiCSDL.getConnection()) {
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
        PhieuSuaChua ro = layTheoId(orderId);
        if (ro != null && "COMPLETED".equalsIgnoreCase(ro.getStatus())) {
            throw new Exception("Loi: Khong the xoa chi tiet cua phieu da hoan tat (COMPLETED)!");
        }

        try (Connection conn = KetNoiCSDL.getConnection()) {
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

    public void themMoiChiTietVaTruKho(ChiTietPhieuSua detail) throws Exception {
        PhieuSuaChua ro = layTheoId(detail.getOrderId());
        if (ro != null && "COMPLETED".equalsIgnoreCase(ro.getStatus())) {
            throw new Exception("Loi: Khong the them linh kien vao phieu da hoan tat (COMPLETED)!");
        }

        try (Connection conn = KetNoiCSDL.getConnection()) {
            conn.setAutoCommit(false); 
            try {
                if (detail.getMaHangMuc().toUpperCase().startsWith("LK")) {
                    try (PreparedStatement psUpdate = conn.prepareStatement(
                            "UPDATE linh_kien SET so_luong_ton = so_luong_ton - ? WHERE LOWER(ma) = LOWER(?) AND so_luong_ton >= ?")) {
                        psUpdate.setInt(1, detail.getSoLuong());
                        psUpdate.setString(2, detail.getMaHangMuc());
                        psUpdate.setInt(3, detail.getSoLuong());
                        int rows = psUpdate.executeUpdate();
                        if (rows == 0) {
                            throw new LinhKienHetHangException("Linh kien khong ton tai hoac kho khong du hang!");
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
                conn.commit(); 
            } catch (Exception e) {
                conn.rollback(); 
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    
    public List<ChiTietPhieuSua> getDetailsByOrderId(int orderId) throws Exception {
        List<ChiTietPhieuSua> result = new ArrayList<>();
        String sql = "SELECT ma_phieu, ma_hang_muc, loai_hang_muc, don_gia_thuc_te, so_luong FROM chi_tiet_phieu_sua WHERE ma_phieu = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(new ChiTietPhieuSua(rs.getInt("ma_phieu"), rs.getString("ma_hang_muc"),
                            rs.getString("loai_hang_muc"), rs.getDouble("don_gia_thuc_te"), rs.getInt("so_luong")));
                }
            }
        }
        return result;
    }

    
    public List<ChiTietPhieuSua> getAllDetails() throws Exception {
        List<ChiTietPhieuSua> result = new ArrayList<>();
        String sql = "SELECT ma_phieu, ma_hang_muc, loai_hang_muc, don_gia_thuc_te, so_luong FROM chi_tiet_phieu_sua";
        try (Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(new ChiTietPhieuSua(rs.getInt("ma_phieu"), rs.getString("ma_hang_muc"),
                        rs.getString("loai_hang_muc"), rs.getDouble("don_gia_thuc_te"), rs.getInt("so_luong")));
            }
        }
        return result;
    }

    
    public List<HangMuc> getDanhSachChiTiet(int orderId) throws Exception {
        List<ChiTietPhieuSua> details = getDetailsByOrderId(orderId);
        List<HangMuc> danhSachChiTiet = new ArrayList<>();
        LinhKienDAO lkDAO = new LinhKienDAO();
        DichVuDAO dvDAO = new DichVuDAO();
        for (int i = 0; i < details.size(); i++) {
            ChiTietPhieuSua d = details.get(i);
            if ("LINHKIEN".equalsIgnoreCase(d.getLoaiHangMuc())) {
                LinhKien lk = lkDAO.layTheoId(d.getMaHangMuc());
                if (lk == null) {
                    lk = new LinhKien();
                    lk.setMa(d.getMaHangMuc());
                    lk.setTen("Linh kien (Da bi xoa khoi kho)");
                }
                lk.setDonGia(d.getDonGiaThucTe()); 
                danhSachChiTiet.add(lk);
            } else if ("DICHVU".equalsIgnoreCase(d.getLoaiHangMuc())) {
                DichVu dv = dvDAO.layTheoId(d.getMaHangMuc());
                if (dv == null) {
                    dv = new DichVu();
                    dv.setMa(d.getMaHangMuc());
                    dv.setTen("Dich vu (Da bi xoa)");
                }
                dv.setDonGia(d.getDonGiaThucTe());
                danhSachChiTiet.add(dv);
            }
        }
        return danhSachChiTiet;
    }

    
    private PhieuSuaChua mapRow(ResultSet rs) throws SQLException {
        Timestamp entry = rs.getTimestamp("ngay_vao");
        Timestamp exit = rs.getTimestamp("ngay_ra");
        int orderId = rs.getInt("ma_phieu");
        String licensePlate = rs.getString("bien_so");
        int mechanicId = rs.getInt("ma_tho_may");
        String status = rs.getString("trang_thai");
        String visualCondition = rs.getString("tinh_trang_ben_ngoai");

        com.mycompany.quanlygara.model.Xe vehicle = null;
        com.mycompany.quanlygara.model.KyThuatVien mechanic = null;
        java.util.List<ChiTietPhieuSua> detailList = new ArrayList<>();

        try {
            XeDAO xeDAO = new XeDAO();
            vehicle = xeDAO.layTheoId(licensePlate);

            KyThuatVienDAO kyThuatVienDAO = new KyThuatVienDAO();
            mechanic = kyThuatVienDAO.layTheoId(mechanicId);

            detailList = getDetailsByOrderId(orderId);
        } catch (Exception e) {
            System.out.println("Loi khi load thong tin chi tiet cho phieu sua chua: " + e.getMessage());
        }

        PhieuSuaChua ro = new PhieuSuaChua(
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
