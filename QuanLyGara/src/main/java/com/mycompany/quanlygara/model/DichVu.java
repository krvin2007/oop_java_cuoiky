/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlygara.model;

import java.util.Scanner;

/**
 * LỚP DICHVU (Dịch vụ sửa chữa) - KẾ THỪA TỪ LỚP HANGMUC
 * 
 * 1. TÍNH KẾ THỪA (INHERITANCE):
 * - Kế thừa lớp trừu tượng HangMuc bằng từ khóa 'extends'.
 * - Thừa hưởng các thuộc tính mã, tên và đơn giá từ lớp cha.
 * 
 * 2. TÍNH ĐA HÌNH (POLYMORPHISM):
 * - Ghi đè và triển khai thực thể (implement) phương thức trừu tượng 'tinhThanhTien(int soLuong)'.
 * - Đối với Dịch vụ sửa chữa, tiền công được tính trọn gói cố định cho mỗi hạng mục, nên thành tiền bằng chính đơn giá (không nhân với số lượng).
 */
public class DichVu extends HangMuc {

    // Constructor mặc định
    public DichVu() {
        super();
    }

    // Constructor đầy đủ tham số
    public DichVu(String ma, String ten, double donGia) {
        super(ma, ten, donGia);
    }

    /**
     * TRIỂN KHAI PHƯƠNG THỨC TRỪU TƯỢNG (METHOD OVERRIDING):
     * - Đây là biểu hiện của tính Đa hình.
     * - 'DichVu' định nghĩa logic riêng cho 'tinhThanhTien': Trả về trực tiếp đơn giá vì dịch vụ là trọn gói (số lượng luôn là 1).
     */
    @Override
    public double tinhThanhTien(int soLuong) {
        return getDonGia();
    }

    @Override
    public void nhapInfo(Scanner sc) {
        System.out.print("Nhap ma dich vu (de trong de tu dong tao): ");
        String maInput = sc.nextLine().trim();
        this.setMa(maInput); // can be empty for auto-generation

        System.out.print("Nhap ten dich vu: ");
        String tenInput = sc.nextLine().trim();
        while (tenInput.isEmpty()) {
            System.out.print("Ten dich vu khong duoc de trong! Moi nhap lai: ");
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
    }

    /**
     * GHI ĐÈ PHƯƠNG THỨC TOSTRING (Đa hình)
     */
    @Override
    public String toString() {
        return "DichVu [" + super.toString() + "]";
    }
}
