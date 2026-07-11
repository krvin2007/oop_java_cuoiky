package com.mycompany.quanlygara.model;

public class ThuKho extends NhanVien {
    
    public ThuKho() {
        super();
    }

    
    public ThuKho(int id, String name, String phone, String address, String username, String password, double salary, String status) {
        super(id, name, phone, address, username, password, "ThuKho", salary, status);
    }

    
    @Override
    public String getRoleDescription() {
        return "ThuKho: Quan ly kho linh kien.";
    }
}
