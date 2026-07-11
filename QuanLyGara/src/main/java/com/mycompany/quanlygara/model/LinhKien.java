
package com.mycompany.quanlygara.model;

import java.util.Scanner;


public class LinhKien extends HangMuc {
    
    
    private int soLuongTon;
    
    private String location;

    
    public LinhKien() {
        super();
    }

    
    public LinhKien(String ma, String ten, double donGia, int soLuongTon, String location) {
        super(ma, ten, donGia); 
        this.soLuongTon = soLuongTon;
        this.location = location;
    }

    
    public String getLocation() {
        return location;
    }

    
    public void setLocation(String location) {
        this.location = location;
    }

    

    
    public int getSoLuongTon() {
        return soLuongTon;
    }

    
    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    
    @Override
    public double tinhThanhTien(int soLuong) {
        return getDonGia() * soLuong;
    }

    
    @Override
    public void nhapInfo(Scanner sc) {
        System.out.print("Nhap ma linh kien (de trong de tu dong tao): ");
        String maInput = sc.nextLine().trim();
        while (!maInput.isEmpty() && maInput.contains(" ")) {
            System.out.print("Ma linh kien khong duoc chua khoang trang! Moi nhap lai: ");
            maInput = sc.nextLine().trim();
        }
        this.setMa(maInput); 

        System.out.print("Nhap ten linh kien: ");
        String tenInput = sc.nextLine().trim();
        while (tenInput.length() < 2) {
            System.out.print("Ten linh kien qua ngan (it nhat 2 ky tu)! Moi nhap lai: ");
            tenInput = sc.nextLine().trim();
        }
        this.setTen(tenInput);

        while (true) {
            try {
                System.out.print("Nhap don gia (VND): ");
                double gia = Double.parseDouble(sc.nextLine());
                if (gia <= 0) {
                    System.out.println("Don gia phai > 0!");
                    continue;
                }
                this.setDonGia(gia);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so thuc hop le!");
            }
        }

        while (true) {
            try {
                System.out.print("Nhap so luong ton kho: ");
                this.soLuongTon = Integer.parseInt(sc.nextLine());
                if (this.soLuongTon < 0) {
                    System.out.println("So luong ton phai >= 0!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so nguyen hop le!");
            }
        }

        System.out.print("Nhap vi tri luu tru trong kho: ");
        this.location = com.mycompany.quanlygara.util.TienIchChuoi.removeAccents(sc.nextLine().trim());
    }

    
    @Override
    public String toString() {
        return "LinhKien [" + super.toString() + ", So luong ton: " + soLuongTon + ", Vi tri: " + location + "]";
    }
}
