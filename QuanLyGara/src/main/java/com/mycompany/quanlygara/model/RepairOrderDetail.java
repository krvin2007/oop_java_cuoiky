/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlygara.model;

import java.util.Scanner;

/**
 *
 * @author ManhQuynh
 */
public class RepairOrderDetail {
    // Mã phiếu sửa chữa (Order ID)
    private int orderId;
    // Mã hạng mục sửa chữa/linh kiện (Item Code)
    private String maHangMuc;
    // Loại hạng mục (Item Type)
    private String loaiHangMuc;
    // Đơn giá thực tế (Actual Unit Price)
    private double donGiaThucTe;
    // Số lượng (Quantity)
    private int soLuong;

    // Khởi tạo đối tượng RepairOrderDetail mới
    public RepairOrderDetail() {
    }

    // Khởi tạo đối tượng RepairOrderDetail mới
    public RepairOrderDetail(int orderId, String maHangMuc, String loaiHangMuc, double donGiaThucTe, int soLuong) {
        this.orderId = orderId;
        this.maHangMuc = maHangMuc;
        this.loaiHangMuc = loaiHangMuc;
        this.donGiaThucTe = donGiaThucTe;
        this.soLuong = soLuong;
    }

    // Lấy giá trị của thuộc tính OrderId
    public int getOrderId() {
        return orderId;
    }

    // Cập nhật giá trị cho thuộc tính OrderId
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    // Lấy giá trị của thuộc tính MaHangMuc
    public String getMaHangMuc() {
        return maHangMuc;
    }

    // Cập nhật giá trị cho thuộc tính MaHangMuc
    public void setMaHangMuc(String maHangMuc) {
        this.maHangMuc = maHangMuc;
    }

    // Lấy giá trị của thuộc tính LoaiHangMuc
    public String getLoaiHangMuc() {
        return loaiHangMuc;
    }

    // Cập nhật giá trị cho thuộc tính LoaiHangMuc
    public void setLoaiHangMuc(String loaiHangMuc) {
        this.loaiHangMuc = loaiHangMuc;
    }

    // Lấy giá trị của thuộc tính DonGiaThucTe
    public double getDonGiaThucTe() {
        return donGiaThucTe;
    }

    // Cập nhật giá trị cho thuộc tính DonGiaThucTe
    public void setDonGiaThucTe(double donGiaThucTe) {
        this.donGiaThucTe = donGiaThucTe;
    }

    // Lấy giá trị của thuộc tính SoLuong
    public int getSoLuong() {
        return soLuong;
    }

    // Cập nhật giá trị cho thuộc tính SoLuong
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    // Nhập thông tin cho đối tượng từ giao diện Console
    public void nhapInfo(Scanner sc) {
        System.out.print("Nhap ma hang muc (Linh kien / Dich vu): ");
        this.maHangMuc = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
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

    // Trả về chuỗi đại diện chứa thông tin của đối tượng
    @Override
    public String toString() {
        return "RepairOrderDetail [Order ID: " + orderId + ", Ma Hang Muc: " + maHangMuc + 
               ", Loai Hang Muc: " + loaiHangMuc + ", Don Gia Thuc Te: " + donGiaThucTe + 
               ", So Luong: " + soLuong + "]";
    }
}
