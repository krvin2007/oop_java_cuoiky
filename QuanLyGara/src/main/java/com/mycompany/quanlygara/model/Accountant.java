package com.mycompany.quanlygara.model;

public class Accountant extends Employee {
    public Accountant() {
        super();
    }

    public Accountant(int id, String name, String phone, String address, String username, String password, double salary, String status) {
        super(id, name, phone, address, username, password, "KeToan", salary, status);
    }

    @Override
    public String getRoleDescription() {
        return "Accountant: Quan ly thanh toan va bao cao.";
    }
}
