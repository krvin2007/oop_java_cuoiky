package com.mycompany.quanlygara.model;

public class Storekeeper extends Employee {
    // Khởi tạo đối tượng Storekeeper mới
    public Storekeeper() {
        super();
    }

    // Khởi tạo đối tượng Storekeeper mới
    public Storekeeper(int id, String name, String phone, String address, String username, String password, double salary, String status) {
        super(id, name, phone, address, username, password, "ThuKho", salary, status);
    }

    // Lấy giá trị của thuộc tính RoleDescription
    @Override
    public String getRoleDescription() {
        return "Storekeeper: Quan ly kho linh kien.";
    }
}
