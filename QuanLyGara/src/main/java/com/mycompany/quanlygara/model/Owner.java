package com.mycompany.quanlygara.model;

public class Owner extends Employee {
    // Khởi tạo đối tượng Owner mới
    public Owner() {
        super();
    }
    
    // Khởi tạo đối tượng Owner mới
    public Owner(int id, String name, String phone, String address, String username, String password, double salary, String status) {
        super(id, name, phone, address, username, password, "QuanLy", salary, status);
    }

    // Lấy giá trị của thuộc tính RoleDescription
    @Override
    public String getRoleDescription() {
        return "Owner: Quan ly toan bo gara.";
    }
}
