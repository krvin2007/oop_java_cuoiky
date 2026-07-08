package com.mycompany.quanlygara.model;

public class Owner extends Employee {
    public Owner() {
        super();
    }
    
    public Owner(int id, String name, String phone, String address, String username, String password, double salary, String status) {
        super(id, name, phone, address, username, password, "QuanLy", salary, status);
    }

    @Override
    public String getRoleDescription() {
        return "Owner: Quan ly toan bo gara.";
    }
}
