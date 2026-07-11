
package com.mycompany.quanlygara.model;

import java.util.Scanner;


public class ChiTietPhieuSua {
    
    private int orderId;
    
    private String maHangMuc;
    
    private String loaiHangMuc;
    
    private double donGiaThucTe;
    
    private int soLuong;

    
    public ChiTietPhieuSua() {
    }

    
    public ChiTietPhieuSua(int orderId, String maHangMuc, String loaiHangMuc, double donGiaThucTe, int soLuong) {
        this.orderId = orderId;
        this.maHangMuc = maHangMuc;
        this.loaiHangMuc = loaiHangMuc;
        this.donGiaThucTe = donGiaThucTe;
        this.soLuong = soLuong;
    }

    
    public int getOrderId() {
        return orderId;
    }

    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    
    public String getMaHangMuc() {
        return maHangMuc;
    }

    
    public void setMaHangMuc(String maHangMuc) {
        this.maHangMuc = maHangMuc;
    }

    
    public String getLoaiHangMuc() {
        return loaiHangMuc;
    }

    
    public void setLoaiHangMuc(String loaiHangMuc) {
        this.loaiHangMuc = loaiHangMuc;
    }

    
    public double getDonGiaThucTe() {
        return donGiaThucTe;
    }

    
    public void setDonGiaThucTe(double donGiaThucTe) {
        this.donGiaThucTe = donGiaThucTe;
    }

    
    public int getSoLuong() {
        return soLuong;
    }

    
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    
    public void nhapInfo(Scanner sc) {
        System.out.print("Nhap ma hang muc (Linh kien / Dich vu): ");
        this.maHangMuc = com.mycompany.quanlygara.util.TienIchChuoi.removeAccents(sc.nextLine().trim());
        while (true) {
            try {
                System.out.print("Nhap so luong/lan su dung: ");
                this.soLuong = Integer.parseInt(sc.nextLine());
                if (this.soLuong <= 0) {
                    System.out.println("So luong phai > 0!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("So luong phai la so nguyen!");
            }
        }
    }

    
    @Override
    public String toString() {
        return "ChiTietPhieuSua [Order ID: " + orderId + ", Ma Hang Muc: " + maHangMuc + 
               ", Loai Hang Muc: " + loaiHangMuc + ", Don Gia Thuc Te: " + donGiaThucTe + 
               ", So Luong: " + soLuong + "]";
    }
}
