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

    /**
     * GHI ĐÈ PHƯƠNG THỨC NHẬP THÔNG TIN (Đa hình): Gọi lại logic nhập của lớp cha HangMuc
     */
    @Override
    public void nhapInfo(Scanner sc) {
        super.nhapInfo(sc);
    }

    /**
     * GHI ĐÈ PHƯƠNG THỨC TOSTRING (Đa hình)
     */
    @Override
    public String toString() {
        return "DichVu [" + super.toString() + "]";
    }
}
