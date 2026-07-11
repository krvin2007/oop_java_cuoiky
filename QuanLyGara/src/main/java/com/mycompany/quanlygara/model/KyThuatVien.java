package com.mycompany.quanlygara.model;

import java.util.Scanner;

public class KyThuatVien extends NhanVien {
    
    private String spec;

    
    public KyThuatVien() {
        super();
        this.setRole("KyThuat");
    }

    
    public KyThuatVien(int id, String name, String phone, String address, String username, String password, double salary,
            String status, String spec) {
        super(id, name, phone, address, username, password, "KyThuat", salary, status);
        this.spec = spec;
    }

    
    public String getSpec() {
        return spec;
    }

    
    public void setSpec(String spec) {
        this.spec = spec;
    }

    
    @Override
    public String getRoleDescription() {
        return "KyThuatVien: Thuc hien sua chua xe. Chuyen mon: " + (spec != null ? spec : "Chung");
    }

    
    @Override
    public void nhapInfo(Scanner sc) {
        super.nhapInfo(sc);
        System.out.print("Nhap username: ");
        this.setUsername(sc.nextLine().trim());
        while (!this.getUsername().matches("^[a-zA-Z0-9_]{4,}$")) {
            System.out.print("Username khong hop le (Tu 4 ky tu tro len, chi chua chu, so, _)! Moi nhap lai: ");
            this.setUsername(sc.nextLine().trim());
        }

        System.out.print("Nhap password: ");
        this.setPassword(sc.nextLine().trim());
        while (this.getPassword().length() < 6) {
            System.out.print("Password phai co it nhat 6 ky tu! Moi nhap lai: ");
            this.setPassword(sc.nextLine().trim());
        }
        System.out.print("Nhap chuyen mon (spec): ");
        this.spec = sc.nextLine().trim();
        while (this.spec.length() < 2) {
            System.out.print("Chuyen mon qua ngan (it nhat 2 ky tu)! Moi nhap lai: ");
            this.spec = sc.nextLine().trim();
        }

        while (true) {
            try {
                System.out.print("Nhap muc luong co ban: ");
                this.setSalary(Double.parseDouble(sc.nextLine()));
                if (this.getSalary() < 0) {
                    System.out.println("Luong phai >= 0!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so hop le!");
            }
        }
        this.setStatus("Dang ranh");
    }

    
    @Override
    public String toString() {
        return "KyThuatVien [ID: " + getId() + ", Name: " + getName() + ", Phone: " + getPhone() +
                ", Spec: " + spec + ", Salary: " + String.format("%,.0f", getSalary()) + ", Status: " + getStatus()
                + "]";
    }
}
