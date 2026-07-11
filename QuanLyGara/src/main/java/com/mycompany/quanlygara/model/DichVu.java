
package com.mycompany.quanlygara.model;

import java.util.Scanner;


public class DichVu extends HangMuc {

    
    public DichVu() {
        super();
    }

    
    public DichVu(String ma, String ten, double donGia) {
        super(ma, ten, donGia);
    }

    
    @Override
    public double tinhThanhTien(int soLuong) {
        
        return getDonGia();
    }

    
    @Override
    public void nhapInfo(Scanner sc) {
        System.out.print("Nhap ma dich vu (de trong de tu dong tao): ");
        String maInput = sc.nextLine().trim();
        while (!maInput.isEmpty() && maInput.contains(" ")) {
            System.out.print("Ma dich vu khong duoc chua khoang trang! Moi nhap lai: ");
            maInput = sc.nextLine().trim();
        }
        this.setMa(maInput); 

        System.out.print("Nhap ten dich vu: ");
        String tenInput = sc.nextLine().trim();
        while (tenInput.length() < 2) {
            System.out.print("Ten dich vu qua ngan (it nhat 2 ky tu)! Moi nhap lai: ");
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
    }

    
    @Override
    public String toString() {
        return "DichVu [" + super.toString() + "]";
    }
}
