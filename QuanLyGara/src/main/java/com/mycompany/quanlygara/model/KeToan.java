package com.mycompany.quanlygara.model;

public class KeToan extends NhanVien {
    
    public KeToan() {
        super();
    }

    
    public KeToan(int id, String name, String phone, String address, String username, String password, double salary, String status) {
        super(id, name, phone, address, username, password, "KeToan", salary, status);
    }

    
    @Override
    public String getRoleDescription() {
        return "KeToan: Quan ly thanh toan va bao cao.";
    }
}
