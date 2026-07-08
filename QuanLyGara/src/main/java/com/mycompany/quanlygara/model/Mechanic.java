package com.mycompany.quanlygara.model;

import java.util.Scanner;

public class Mechanic extends Employee {
    private String spec;

    public Mechanic() {
        super();
        this.setRole("KyThuat");
    }

    public Mechanic(int id, String name, String phone, String address, String username, String password, double salary, String status, String spec) {
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
        return "Mechanic: Thuc hien sua chua xe. Chuyen mon: " + (spec != null ? spec : "Chung");
    }

    @Override
    public void nhapInfo(Scanner sc) {
        super.nhapInfo(sc);
        System.out.print("Nhap username: ");
        this.setUsername(sc.nextLine().trim());
        System.out.print("Nhap password: ");
        this.setPassword(sc.nextLine().trim());
        System.out.print("Nhap chuyen mon (spec): ");
        this.spec = sc.nextLine().trim();
        
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
        this.setStatus("Đang rảnh");
    }

    @Override
    public String toString() {
        return "Mechanic [ID: " + getId() + ", Name: " + getName() + ", Phone: " + getPhone() + 
               ", Spec: " + spec + ", Salary: " + String.format("%,.0f", getSalary()) + ", Status: " + getStatus() + "]";
    }
}
