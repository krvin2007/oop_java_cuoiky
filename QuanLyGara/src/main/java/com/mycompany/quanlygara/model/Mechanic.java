/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlygara.model;

import java.util.Scanner;

/**
 * LỚP MECHANIC (Thợ máy) - KẾ THỪA TỪ LỚP PERSON
 * 
 * 1. TÍNH KẾ THỪA (INHERITANCE):
 * - Kế thừa từ lớp cha Person bằng từ khóa 'extends'.
 * - Thừa hưởng các thuộc tính cơ bản (id, name, phone, address) và các getter/setter của Person.
 * 
 * 2. TÍNH ĐÓNG GÓI (ENCAPSULATION):
 * - Định nghĩa các thuộc tính riêng (specialization, salary, status) dạng 'private' nhằm bảo vệ và kiểm soát quyền truy cập dữ liệu đặc thù của Thợ máy.
 * 
 * 3. TÍNH ĐA HÌNH (POLYMORPHISM):
 * - Ghi đè (Overriding) phương thức nhapInfo() và toString() của lớp cha để thực hiện logic nhập liệu và in thông tin riêng biệt của Mechanic.
 */
public class Mechanic extends Person {
    // Thuộc tính riêng của lớp Mechanic (Đóng gói private)
    private String specialization; // Chuyên môn (ví dụ: Sửa gầm, Điện ô tô, Sơn, v.v.)
    private double salary;         // Lương cơ bản của thợ máy
    private String status;         // Trạng thái hiện tại: "Đang rảnh" / "Đang bận"

    // Constructor mặc định (Không tham số)
    public Mechanic() {
        super(); // Gọi Constructor của lớp cha Person
        this.status = "Đang rảnh"; // Mặc định khi tạo thợ máy mới là ở trạng thái sẵn sàng làm việc
    }

    // Constructor đầy đủ tham số (Gồm thuộc tính lớp cha và lớp con)
    public Mechanic(int id, String name, String phone, String address, String specialization, double salary, String status) {
        super(id, name, phone, address); // Truyền dữ liệu lên lớp cha khởi tạo trước
        this.specialization = specialization;
        this.salary = salary;
        this.status = status;
    }

    // --- CÁC PHƯƠNG THỨC GETTER VÀ SETTER CHO THUỘC TÍNH RIÊNG (Đóng gói) ---

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * GHI ĐÈ PHƯƠNG THỨC NHẬP THÔNG TIN (METHOD OVERRIDING):
     * - Đầu tiên, gọi 'super.nhapInfo(sc)' để nhập thông tin chung (Họ tên, SĐT, Địa chỉ).
     * - Sau đó, tiếp tục nhập và kiểm tra tính hợp lệ của các dữ liệu riêng của Mechanic (Chuyên môn, Lương >= 0).
     */
    @Override
    public void nhapInfo(Scanner sc) {
        super.nhapInfo(sc); // Tái sử dụng logic nhập của lớp cha Person
        
        System.out.print("Nhap chuyen mon (specialization): ");
        this.specialization = sc.nextLine().trim();
        while (this.specialization.isEmpty()) {
            System.out.print("Chuyen mon khong duoc de trong! Moi nhap lai: ");
            this.specialization = sc.nextLine().trim();
        }
        
        // Kiểm tra tính hợp lệ của Lương (phải là số thực và >= 0)
        while (true) {
            try {
                System.out.print("Nhap luong (salary): ");
                this.salary = Double.parseDouble(sc.nextLine());
                if (this.salary < 0) {
                    System.out.println("Luong phai >= 0!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so thuc hop le!");
            }
        }
        this.status = "Đang rảnh"; // Mặc định đặt trạng thái thợ máy mới nhập là "Đang rảnh"
    }

    /**
     * GHI ĐÈ PHƯƠNG THỨC TOSTRING (METHOD OVERRIDING):
     * - Kết hợp chuỗi thông tin của lớp cha 'super.toString()' với các thuộc tính riêng của lớp con 
     *   để tạo nên bản mô tả chi tiết và hoàn chỉnh về Thợ máy.
     */
    @Override
    public String toString() {
        return "Mechanic [" + super.toString() + ", Specialization: " + specialization + 
               ", Salary: " + String.format("%,.0f", salary) + " VND, Status: " + status + "]";
    }
}
