
package com.mycompany.quanlygara.controller;

import com.mycompany.quanlygara.model.ChuXe;
import com.mycompany.quanlygara.model.DuplicateMaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ChuXeDAO implements IKhoLuuTru<ChuXe> {

    
    @Override
    public void themMoi(ChuXe ChuXe) throws Exception {
        try (Connection conn = KetNoiCSDL.getConnection()) {
            if (ChuXe.getId() > 0) {
                try (PreparedStatement ps = conn.prepareStatement("SELECT id FROM chu_xe WHERE id = ?")) {
                    ps.setInt(1, ChuXe.getId());
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            throw new DuplicateMaException("Ma chu xe '" + ChuXe.getId() + "' da ton tai!");
                        }
                    }
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT id FROM chu_xe WHERE sdt = ? AND da_xoa = FALSE")) {
                ps.setString(1, ChuXe.getPhone());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        throw new Exception("So dien thoai '" + ChuXe.getPhone() + "' da ton tai!");
                    }
                }
            }

            if (ChuXe.getId() > 0) {
                try (PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO chu_xe (id, ten, sdt, dia_chi, email) VALUES (?, ?, ?, ?, ?)")) {
                    ps.setInt(1, ChuXe.getId());
                    ps.setString(2, ChuXe.getName());
                    ps.setString(3, ChuXe.getPhone());
                    ps.setString(4, ChuXe.getAddress());
                    ps.setString(5, ChuXe.getEmail());
                    ps.executeUpdate();
                }
            } else {
                try (PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO chu_xe (ten, sdt, dia_chi, email) VALUES (?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, ChuXe.getName());
                    ps.setString(2, ChuXe.getPhone());
                    ps.setString(3, ChuXe.getAddress());
                    ps.setString(4, ChuXe.getEmail());
                    ps.executeUpdate();
                    try (ResultSet keys = ps.getGeneratedKeys()) {
                        if (keys.next()) {
                            ChuXe.setId(keys.getInt(1));
                        }
                    }
                }
            }
        }
    }

    
    @Override
    public void capNhat(ChuXe ChuXe) throws Exception {
        try (Connection conn = KetNoiCSDL.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT id FROM chu_xe WHERE sdt = ? AND id <> ? AND da_xoa = FALSE")) {
                ps.setString(1, ChuXe.getPhone());
                ps.setInt(2, ChuXe.getId());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        throw new Exception("So dien thoai '" + ChuXe.getPhone() + "' da bi trung voi chu xe khac!");
                    }
                }
            }
            try (PreparedStatement ps = conn.prepareStatement(
                    "UPDATE chu_xe SET ten = ?, sdt = ?, dia_chi = ?, email = ? WHERE id = ?")) {
                ps.setString(1, ChuXe.getName());
                ps.setString(2, ChuXe.getPhone());
                ps.setString(3, ChuXe.getAddress());
                ps.setString(4, ChuXe.getEmail());
                ps.setInt(5, ChuXe.getId());
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
        try (Connection conn = KetNoiCSDL.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM xe WHERE ma_chu_xe = ? AND da_xoa = FALSE")) {
                ps.setInt(1, targetId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        throw new Exception("Khong the xoa chu xe co ID = " + targetId + " vi nguoi nay dang co xe trong he thong!");
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

    
    @Override
    public List<ChuXe> layTatCa() throws Exception {
        List<ChuXe> list = new ArrayList<>();
        String sql = "SELECT id, ten, sdt, dia_chi, email, da_xoa FROM chu_xe WHERE da_xoa = FALSE ORDER BY id";
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
    public ChuXe layTheoId(Object id) throws Exception {
        int targetId = (Integer) id;
        String sql = "SELECT id, ten, sdt, dia_chi, email, da_xoa FROM chu_xe WHERE id = ? AND da_xoa = FALSE";
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

    
    public List<ChuXe> searchByName(String keyword) throws Exception {
        List<ChuXe> result = new ArrayList<>();
        String sql = "SELECT id, ten, sdt, dia_chi, email, da_xoa FROM chu_xe WHERE LOWER(ten) LIKE ? AND da_xoa = FALSE ORDER BY id";
        try (Connection conn = KetNoiCSDL.getConnection();
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

    
    private ChuXe mapRow(ResultSet rs) throws SQLException {
        ChuXe ChuXe = new ChuXe(rs.getInt("id"), rs.getString("ten"), rs.getString("sdt"), rs.getString("dia_chi"), rs.getString("email"));
        ChuXe.setDeleted(rs.getBoolean("da_xoa"));
        return ChuXe;
    }

    public List<ChuXe> layDanhSachDaXoa() throws Exception {
        List<ChuXe> list = new ArrayList<>();
        String sql = "SELECT id, ten, sdt, dia_chi, email, da_xoa FROM chu_xe WHERE da_xoa = TRUE ORDER BY id";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    public void khoiPhuc(int id) throws Exception {
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE chu_xe SET da_xoa = FALSE WHERE id = ?")) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new Exception("Khong tim thay chu xe co ID = " + id + " trong Thung rac!");
            }
        }
    }

    public void xoaVinhVien(int id) throws Exception {
        try (Connection conn = KetNoiCSDL.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM chu_xe WHERE id = ?")) {
                ps.setInt(1, id);
                int rows = ps.executeUpdate();
                if (rows == 0) {
                    throw new Exception("Khong tim thay chu xe co ID = " + id + " de xoa vinh vien!");
                }
            }
        }
    }
}

