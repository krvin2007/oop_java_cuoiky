/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlygara.model;

import java.util.Scanner;

/**
 * LỚP TRỪU TƯỢNG HANGMUC (Đại diện cho Hạng mục: Dịch vụ sửa chữa hoặc Linh
 * kiện thay thế)
 * 
 * 1. TÍNH TRỪU TƯỢNG (ABSTRACTION):
 * - Được khai báo với từ khóa 'abstract class'. Lớp này không thể khởi tạo đối
 * tượng trực tiếp.
 * - Khai báo một phương thức trừu tượng: 'public abstract double
 * tinhThanhTien(int soLuong);'.
 * Phương thức này không có phần thân xử lý, buộc các lớp con (DichVu, LinhKien)
 * phải tự định nghĩa (implement) logic tính toán cụ thể của riêng mình.
 * 
 * 2. TÍNH ĐÓNG GÓI (ENCAPSULATION):
 * - Các thuộc tính chung (ma, ten, donGia) đều là 'private' và được truy cập,
 * thay đổi thông qua các phương thức Getter/Setter tương ứng.
 */
public abstract class HangMuc {
    // Các thuộc tính private (Đóng gói)
    // Mã hạng mục (Category Code)
    private String ma;
    // Tên hạng mục (Category Name)
    private String ten;
    // Đơn giá (Unit Price)
    private double donGia;

    // Constructor mặc định (Không tham số)
    public HangMuc() {
    }

    // Constructor có tham số
    public HangMuc(String ma, String ten, double donGia) {
        this.ma = ma;
        this.ten = ten;
        this.donGia = donGia;
    }

    // --- CÁC PHƯƠNG THỨC GETTER VÀ SETTER (Đóng gói) ---

    // Lấy giá trị của thuộc tính Ma
    public String getMa() {
        return ma;
    }

    // Cập nhật giá trị cho thuộc tính Ma
    public void setMa(String ma) {
        this.ma = ma;
    }

    // Lấy giá trị của thuộc tính Ten
    public String getTen() {
        return ten;
    }

    // Cập nhật giá trị cho thuộc tính Ten
    public void setTen(String ten) {
        this.ten = ten;
    }

    // Lấy giá trị của thuộc tính DonGia
    public double getDonGia() {
        return donGia;
    }

    // Cập nhật giá trị cho thuộc tính DonGia
    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    /**
     * Phương thức nhập thông tin cơ bản của một hạng mục từ bàn phím.
     * Kiểm tra dữ liệu đơn giá phải >= 0 để đảm bảo tính hợp lệ.
     */
    public void nhapInfo(Scanner sc) {
        System.out.print("Nhap ma hang muc: ");
        this.ma = sc.nextLine().trim();
        while (this.ma.isEmpty() || this.ma.contains(" ")) {
            System.out.print("Ma hang muc khong duoc de trong va khong chua khoang trang! Moi nhap lai: ");
            this.ma = sc.nextLine().trim();
        }

        System.out.print("Nhap ten hang muc: ");
        this.ten = sc.nextLine().trim();
        while (this.ten.length() < 2) {
            System.out.print("Ten hang muc qua ngan (it nhat 2 ky tu)! Moi nhap lai: ");
            this.ten = sc.nextLine().trim();
        }

        while (true) {
            try {
                System.out.print("Nhap don gia (VND): ");
                this.donGia = Double.parseDouble(sc.nextLine());
                if (this.donGia <= 0) {
                    System.out.println("Don gia phai > 0!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so thuc hop le!");
            }
        }
    }

    /**
     * PHƯƠNG THỨC TRỪU TƯỢNG (ABSTRACT METHOD):
     * - Đây là trái tim của tính đa hình trong phân cấp HangMuc.
     * - Phương thức này không có thân hàm.
     * - Bất cứ lớp con nào kế thừa 'HangMuc' bắt buộc phải ghi đè và viết code xử
     * lý riêng cho phương thức này.
     */
    public abstract double tinhThanhTien(int soLuong);

    /**
     * GHI ĐÈ PHƯƠNG THỨC TOSTRING (Đa hình)
     */
    @Override
    public String toString() {
        return "Ma: " + ma + ", Ten: " + ten + ", Don gia: " + String.format("%,.0f", donGia) + " VND";
    }
}
