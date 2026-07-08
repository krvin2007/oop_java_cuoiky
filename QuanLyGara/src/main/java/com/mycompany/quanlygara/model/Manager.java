package com.mycompany.quanlygara.model;

public class Manager extends Employee {
    public Manager() {
        super();
    }
    
    public Manager(int id, String name, String phone, String address, String username, String password, double salary, String status) {
        super(id, name, phone, address, username, password, "QuanLy", salary, status);
    }

    @Override
    public String getRoleDescription() {
        return "Manager: Quan ly toan bo gara.";
    }
}
