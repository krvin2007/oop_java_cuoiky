
package com.mycompany.quanlygara.model;

import java.util.Scanner;


public class ChuXe extends Nguoi {

    
    private String email;

    
    public ChuXe() {
        
        
        super();
    }

    
    public ChuXe(int id, String name, String phone, String address, String email) {
        
        
        super(id, name, phone, address);
        this.email = email;
    }

    
    public String getEmail() {
        return email;
    }

    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Override
    public void nhapInfo(Scanner sc) {
        super.nhapInfo(sc);
        System.out.print("Nhap email: ");
        this.email = sc.nextLine().trim();
        while (!this.email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            System.out.print("Email khong hop le (Vui long nhap dung dinh dang, vi du: abc@gmail.com)! Moi nhap lai: ");
            this.email = sc.nextLine().trim();
        }
    }

    
    @Override
    public String toString() {
        return "ChuXe [" + super.toString() + ", Email: " + email + "]";
    }

    
    @Override
    public String getRoleDescription() {
        return "Vai tro: Chu xe (Khach hang su dung dich vu gara)";
    }
}
