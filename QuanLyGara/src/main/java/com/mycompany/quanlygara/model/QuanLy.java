package com.mycompany.quanlygara.model;

public class QuanLy extends NhanVien {
    
    public QuanLy() {
        super();
    }
    
    
    public QuanLy(int id, String name, String phone, String address, String username, String password, double salary, String status) {
        super(id, name, phone, address, username, password, "QuanLy", salary, status);
    }

    
    @Override
    public String getRoleDescription() {
        return "QuanLy: Quan ly toan bo gara.";
    }
}
