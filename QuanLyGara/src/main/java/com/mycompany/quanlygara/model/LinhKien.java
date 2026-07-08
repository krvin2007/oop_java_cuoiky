/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlygara.model;

import java.util.Scanner;

/**
 * LỚP LINHKIEN (Linh kiện phụ tùng thay thế) - KẾ THỪA TỪ LỚP HANGMUC
 * 
 * 1. TÍNH KẾ THỪA (INHERITANCE):
 * - Kế thừa từ lớp trừu tượng HangMuc thông qua từ khóa 'extends'.
 * - Thừa hưởng các thuộc tính mã, tên, đơn giá từ lớp cha.
 * 
 * 2. TÍNH ĐÓNG GÓI (ENCAPSULATION):
 * - Thêm thuộc tính riêng 'soLuongTon' (số lượng tồn kho) có phạm vi truy cập 'private' và cung cấp getter/setter để quản lý số lượng tồn của linh kiện một cách an toàn.
 * 
 * 3. TÍNH ĐA HÌNH (POLYMORPHISM):
 * - Ghi đè phương thức trừu tượng 'tinhThanhTien(int soLuong)'. 
 * - Với linh kiện, thành tiền sẽ bằng đơn giá nhân với số lượng thực tế sử dụng.
 */
public class LinhKien extends HangMuc {
    // Thuộc tính riêng của LinhKien (Đóng gói)
    private int soLuongTon;
    private String location;

    // Constructor mặc định
    public LinhKien() {
        super();
    }

    // Constructor đầy đủ tham số (bao gồm tham số lớp cha và lớp con)
    public LinhKien(String ma, String ten, double donGia, int soLuongTon, String location) {
        super(ma, ten, donGia); // Gọi constructor lớp cha
        this.soLuongTon = soLuongTon;
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // --- GETTER VÀ SETTER CHO THUỘC TÍNH RIÊNG (Đóng gói) ---

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    /**
     * TRIỂN KHAI PHƯƠNG THỨC TRỪU TƯỢNG (METHOD OVERRIDING):
     * - Định nghĩa lại phương thức tính thành tiền theo đặc trưng của Linh kiện:
     *   Thành tiền = đơn giá * số lượng mua/sử dụng.
     */
    @Override
    public double tinhThanhTien(int soLuong) {
        return getDonGia() * soLuong;
    }

    /**
     * GHI ĐÈ PHƯƠNG THỨC NHẬP THÔNG TIN (METHOD OVERRIDING):
     * - Đầu tiên, gọi super.nhapInfo(sc) để nhập các thông tin chung của Hạng mục.
     * - Sau đó, tiến hành nhập và validate số lượng tồn kho (phải là số nguyên và >= 0).
     */
    @Override
    public void nhapInfo(Scanner sc) {
        System.out.print("Nhap ma linh kien (de trong de tu dong tao): ");
        String maInput = sc.nextLine().trim();
        this.setMa(maInput); // can be empty for auto-generation

        System.out.print("Nhap ten linh kien: ");
        String tenInput = sc.nextLine().trim();
        while (tenInput.isEmpty()) {
            System.out.print("Ten linh kien khong duoc de trong! Moi nhap lai: ");
            tenInput = sc.nextLine().trim();
        }
        this.setTen(tenInput);

        while (true) {
            try {
                System.out.print("Nhap don gia (VND): ");
                double gia = Double.parseDouble(sc.nextLine());
                if (gia < 0) {
                    System.out.println("Don gia phai >= 0!");
                    continue;
                }
                this.setDonGia(gia);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so thuc hop le!");
            }
        }

        while (true) {
            try {
                System.out.print("Nhap so luong ton kho: ");
                this.soLuongTon = Integer.parseInt(sc.nextLine());
                if (this.soLuongTon < 0) {
                    System.out.println("So luong ton phai >= 0!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so nguyen hop le!");
            }
        }

        System.out.print("Nhap vi tri luu tru trong kho: ");
        this.location = sc.nextLine().trim();
    }

    /**
     * GHI ĐÈ PHƯƠNG THỨC TOSTRING (Đa hình)
     */
    @Override
    public String toString() {
        return "LinhKien [" + super.toString() + ", So luong ton: " + soLuongTon + ", Vi tri: " + location + "]";
    }
}
