package com.mycompany.quanlygara.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinhKienTest {

    @Test
    public void testLinhKienCreation() {
        LinhKien lk = new LinhKien("LK001", "Lốp xe", 500000, 10, "Kệ A1");
        
        assertEquals("LK001", lk.getMa());
        assertEquals("Lốp xe", lk.getTen());
        assertEquals(500000, lk.getDonGia());
        assertEquals(10, lk.getSoLuongTon());
        assertEquals("Kệ A1", lk.getLocation());
    }

    @Test
    public void testTinhThanhTien() {
        LinhKien lk = new LinhKien("LK002", "Bugi", 100000, 50, "Kệ B2");
        
        double thanhTien = lk.tinhThanhTien(5);
        assertEquals(500000.0, thanhTien);
    }
}
