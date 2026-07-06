package com.mycompany.quanlygara.model;

public class Storekeeper extends Employee {
    public Storekeeper() {
        super();
    }

    public Storekeeper(int id, String name, String phone, String address, String username, String password, double salary, String status) {
        super(id, name, phone, address, username, password, "ThuKho", salary, status);
    }

    @Override
    public String getRoleDescription() {
        return "Storekeeper: Quan ly kho linh kien.";
    }
}
