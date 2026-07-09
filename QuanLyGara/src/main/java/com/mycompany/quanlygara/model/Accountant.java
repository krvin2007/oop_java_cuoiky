package com.mycompany.quanlygara.model;

public class Accountant extends Employee {
    // Khởi tạo đối tượng Accountant mới
    public Accountant() {
        super();
    }

    // Khởi tạo đối tượng Accountant mới
    public Accountant(int id, String name, String phone, String address, String username, String password, double salary, String status) {
        super(id, name, phone, address, username, password, "KeToan", salary, status);
    }

    // Lấy giá trị của thuộc tính RoleDescription
    @Override
    public String getRoleDescription() {
        return "Accountant: Quan ly thanh toan va bao cao.";
    }
}
