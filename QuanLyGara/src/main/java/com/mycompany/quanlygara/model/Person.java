/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlygara.model;

import java.util.Scanner;

/**
 * LỚP TRỪU TƯỢNG PERSON (Đại diện cho một con người nói chung)
 * 
 * 1. TÍNH TRỪU TƯỢNG (ABSTRACTION):
 * - Khai báo lớp với từ khóa 'abstract'. 
 * - Đây là một lớp trừu tượng, nghĩa là ta KHÔNG THỂ khởi tạo đối tượng trực tiếp từ lớp này (ví dụ: 'new Person()' sẽ báo lỗi).
 * - Lớp này chỉ dùng làm khuôn mẫu (template) định nghĩa các thuộc tính và hành vi chung cho các lớp con kế thừa (như Owner, Mechanic).
 * 
 * 2. TÍNH ĐÓNG GÓI (ENCAPSULATION):
 * - Các thuộc tính (id, name, phone, address) đều được đặt phạm vi truy cập là 'private'.
 * - Dữ liệu bên trong đối tượng được bảo vệ, tránh bị đọc hoặc sửa trực tiếp từ bên ngoài.
 * - Muốn tương tác với thuộc tính, bắt buộc phải thông qua các phương thức Getter (để lấy giá trị) và Setter (để sửa giá trị) được công khai (public).
 */
public abstract class Person {
    // Các thuộc tính private (Đóng gói dữ liệu)
    private int id;
    private String name;
    private String phone;
    private String address;

    // Constructor mặc định (Không tham số)
    // TÍNH ĐA HÌNH (POLYMORPHISM) - NẠP CHỒNG (OVERLOADING): Cho phép khởi tạo đối tượng bằng nhiều cách khác nhau.
    public Person() {
    }

    // Constructor có tham số (Được dùng để gán trực tiếp dữ liệu lúc tạo đối tượng mới)
    public Person(int id, String name, String phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    // --- CÁC PHƯƠNG THỨC GETTER VÀ SETTER (Cung cấp cổng truy cập có kiểm soát) ---

    // Getter cho ID
    public int getId() {
        return id;
    }

    // Setter cho ID
    public void setId(int id) {
        this.id = id;
    }

    // Getter cho Name
    public String getName() {
        return name;
    }

    // Setter cho Name
    public void setName(String name) {
        this.name = name;
    }

    // Getter cho Phone
    public String getPhone() {
        return phone;
    }

    // Setter cho Phone
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Getter cho Address
    public String getAddress() {
        return address;
    }

    // Setter cho Address
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Phương thức nhập thông tin cơ bản từ bàn phím.
     * Thể hiện kiểm soát dữ liệu chặt chẽ (Validation) trước khi gán vào thuộc tính private.
     */
    public void nhapInfo(Scanner sc) {
        // Nhập họ tên: Không được để trống
        System.out.print("Nhap ho ten: ");
        this.name = sc.nextLine().trim();
        while (this.name.isEmpty()) {
            System.out.print("Ho ten khong duoc de trong! Moi nhap lai: ");
            this.name = sc.nextLine().trim();
        }

        // Nhập số điện thoại: Kiểm tra định dạng Regex (bắt đầu bằng 0, gồm 10 số)
        System.out.print("Nhap so dien thoai: ");
        this.phone = sc.nextLine().trim();
        while (!this.phone.matches("^0\\d{9}$")) {
            System.out.print("So dien thoai khong hop le (Phai gom 10 so, bat dau tu 0). Moi nhap lai: ");
            this.phone = sc.nextLine().trim();
        }

        // Nhập địa chỉ: Không được để trống
        System.out.print("Nhap dia chi: ");
        this.address = sc.nextLine().trim();
        while (this.address.isEmpty()) {
            System.out.print("Dia chi khong duoc de trong! Moi nhap lai: ");
            this.address = sc.nextLine().trim();
        }
    }

    /**
     * TÍNH ĐA HÌNH (POLYMORPHISM) - GHI ĐÈ PHƯƠNG THỨC (OVERRIDING):
     * - Phương thức toString() này ghi đè phương thức mặc định của lớp cha tối cao Object.
     * - Trả về một chuỗi đại diện mô tả thông tin cơ bản của lớp Person.
     */
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Phone: " + phone + ", Address: " + address;
    }

    /**
     * PHƯƠNG THỨC TRỪU TƯỢNG (ABSTRACT METHOD):
     * - Thể hiện tính Abstraction (Trừu tượng) rõ ràng nhất.
     * - Phương thức không có phần thân (body), bắt buộc tất cả các lớp con kế thừa 
     *   phải cung cấp chi tiết triển khai riêng (Overriding).
     * @return Chuỗi mô tả vai trò của đối tượng.
     */
    public abstract String getRoleDescription();
}
