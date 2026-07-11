
package com.mycompany.quanlygara.model;

import java.util.Scanner;


public abstract class Nguoi {
    
    
    private int id;
    
    private String name;
    
    private String phone;
    
    private String address;
    
    private boolean isDeleted;

    
    
    
    public Nguoi() {
    }

    
    
    public Nguoi(int id, String name, String phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.isDeleted = false;
    }

    
    public boolean isDeleted() {
        return isDeleted;
    }

    
    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    
    

    
    public int getId() {
        return id;
    }

    
    public void setId(int id) {
        this.id = id;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public String getPhone() {
        return phone;
    }

    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    public String getAddress() {
        return address;
    }

    
    public void setAddress(String address) {
        this.address = address;
    }

    
    public void nhapInfo(Scanner sc) {
        
        System.out.print("Nhap ho ten: ");
        this.name = com.mycompany.quanlygara.util.TienIchChuoi.removeAccents(sc.nextLine().trim());
        while (this.name.isEmpty() || !this.name.matches("^[a-zA-Z\\s]{2,50}$")) {
            if (this.name.isEmpty()) {
                System.out.print("Ho ten khong duoc de trong! Moi nhap lai: ");
            } else {
                System.out.print("Ho ten khong hop le (chi gom chu cai va khoang trang, tu 2-50 ky tu). Moi nhap lai: ");
            }
            this.name = com.mycompany.quanlygara.util.TienIchChuoi.removeAccents(sc.nextLine().trim());
        }

        
        System.out.print("Nhap so dien thoai: ");
        this.phone = com.mycompany.quanlygara.util.TienIchChuoi.removeAccents(sc.nextLine().trim());
        while (!this.phone.matches("^(03|05|07|08|09)\\d{8}$")) {
            System.out.print("So dien thoai khong hop le (Gom 10 so, bat dau bang 03,05,07,08,09). Moi nhap lai: ");
            this.phone = com.mycompany.quanlygara.util.TienIchChuoi.removeAccents(sc.nextLine().trim());
        }

        
        System.out.print("Nhap dia chi: ");
        this.address = sc.nextLine().trim();
        while (this.address.length() < 5) {
            System.out.print("Dia chi qua ngan (Phai co it nhat 5 ky tu)! Moi nhap lai: ");
            this.address = sc.nextLine().trim();
        }
    }

    
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Phone: " + phone + ", Address: " + address;
    }

    
    public abstract String getRoleDescription();
}
