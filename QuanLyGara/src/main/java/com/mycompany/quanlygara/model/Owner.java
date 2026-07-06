/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlygara.model;

import java.util.Scanner;

/**
 * LỚP OWNER (Chủ xe) - KẾ THỪA TỪ LỚP PERSON
 * 
 * 1. TÍNH KẾ THỪA (INHERITANCE):
 * - Được thể hiện qua từ khóa 'extends Person'.
 * - Lớp Owner kế thừa tất cả thuộc tính (id, name, phone, address) và các phương thức getter/setter của lớp cha Person mà không cần định nghĩa lại.
 * 
 * 2. TÍNH ĐA HÌNH (POLYMORPHISM):
 * - Thể hiện qua việc ghi đè phương thức (Overriding): Ghi đè nhapInfo() và toString() của lớp cha để thực hiện nghiệp vụ riêng của Owner.
 */
public class Owner extends Person {

    // Constructor mặc định (Không tham số)
    public Owner() {
        // Từ khóa super() gọi Constructor không tham số của lớp cha (Person) để khởi tạo đối tượng cha ngầm định
        super();
    }

    // Constructor đầy đủ tham số
    public Owner(int id, String name, String phone, String address) {
        // Từ khóa super(...) truyền các tham số trực tiếp cho Constructor của lớp cha để gán giá trị cho các thuộc tính kế thừa
        super(id, name, phone, address);
    }

    /**
     * GHI ĐÈ PHƯƠNG THỨC (METHOD OVERRIDING):
     * - Ghi đè phương thức nhapInfo() từ lớp cha Person.
     * - Sử dụng từ khóa 'super.nhapInfo(sc)' để chạy lại logic nhập thông tin cơ bản (Tên, SĐT, Địa chỉ) của lớp cha,
     *   giúp tái sử dụng mã nguồn và tránh viết lại mã giống nhau.
     */
    @Override
    public void nhapInfo(Scanner sc) {
        super.nhapInfo(sc);
    }

    /**
     * GHI ĐÈ PHƯƠNG THỨC (METHOD OVERRIDING):
     * - Ghi đè phương thức toString() từ lớp cha Person.
     * - Gọi 'super.toString()' để lấy chuỗi thông tin của Person và bổ sung nhãn "Owner [...]" 
     *   để nhận diện đối tượng cụ thể là Chủ xe.
     */
    @Override
    public String toString() {
        return "Owner [" + super.toString() + "]";
    }

    /**
     * TRIỂN KHAI PHƯƠNG THỨC TRỪU TƯỢNG (IMPLEMENT ABSTRACT METHOD):
     * - Bắt buộc phải ghi đè phương thức trừu tượng từ lớp cha Person.
     * - Thể hiện tính Abstraction và Polymorphism.
     */
    @Override
    public String getRoleDescription() {
        return "Vai trò: Chủ xe (Khách hàng sử dụng dịch vụ gara)";
    }
}
