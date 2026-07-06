/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlygara.model;

import java.util.Scanner;

/**
 *
 * @author ManhQuynh
 */
public class Vehicle {
    private String licensePlate;
    private String brand;
    private String model;
    private int productionYear;
    private Owner owner;

    public Vehicle() {
    }

    public Vehicle(String licensePlate, String brand, String model, int productionYear, Owner owner) {
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.owner = owner;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void nhapInfo(Scanner sc) {
        System.out.print("Nhap bien so xe (license plate): ");
        this.licensePlate = sc.nextLine().trim();
        while (this.licensePlate.isEmpty()) {
            System.out.print("Bien so xe khong duoc de trong! Moi nhap lai: ");
            this.licensePlate = sc.nextLine().trim();
        }

        System.out.print("Nhap hang xe (brand): ");
        this.brand = sc.nextLine().trim();
        while (this.brand.isEmpty()) {
            System.out.print("Hang xe khong duoc de trong! Moi nhap lai: ");
            this.brand = sc.nextLine().trim();
        }

        System.out.print("Nhap mau xe (model): ");
        this.model = sc.nextLine().trim();
        while (this.model.isEmpty()) {
            System.out.print("Mau xe khong duoc de trong! Moi nhap lai: ");
            this.model = sc.nextLine().trim();
        }
        while (true) {
            try {
                System.out.print("Nhap nam san xuat: ");
                this.productionYear = Integer.parseInt(sc.nextLine());
                if (this.productionYear < 1900 || this.productionYear > 2100) {
                    System.out.println("Nam san xuat khong hop le!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so nguyen hop le!");
            }
        }
        // Note: Owner should be set by the caller fetching from DAO based on input ID.
        System.out.print("Nhap ID chu xe: ");
        int inputOwnerId = 0;
        try {
            inputOwnerId = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {}
        this.owner = new Owner();
        this.owner.setId(inputOwnerId); // Temporary ID wrapper
    }

    @Override
    public String toString() {
        return "Vehicle [License Plate: " + licensePlate + ", Brand: " + brand + ", Model: " + model + ", Production Year: " + productionYear + ", Owner: " + (owner != null ? owner.getName() : "null") + "]";
    }
}
