/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlygara.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author ManhQuynh
 */
public class RepairOrder {
    private int orderId;
    private Vehicle vehicle;
    private Date entryDate;
    private Date exitDate;
    private Mechanic mechanic;
    private String status; // RECEIVING, REPAIRING, COMPLETED
    private String visualCondition;
    private java.util.List<RepairOrderDetail> details;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public RepairOrder() {
        this.entryDate = new Date();
        this.status = "RECEIVING";
        this.details = new java.util.ArrayList<>();
    }

    public RepairOrder(int orderId, Vehicle vehicle, Date entryDate, Date exitDate, Mechanic mechanic, String status, String visualCondition) {
        this.orderId = orderId;
        this.vehicle = vehicle;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.mechanic = mechanic;
        this.status = status;
        this.visualCondition = visualCondition;
        this.details = new java.util.ArrayList<>();
    }

    public String getVisualCondition() {
        return visualCondition;
    }

    public void setVisualCondition(String visualCondition) {
        this.visualCondition = visualCondition;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getExitDate() {
        return exitDate;
    }

    public void setExitDate(Date exitDate) {
        this.exitDate = exitDate;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public java.util.List<RepairOrderDetail> getDetails() {
        return details;
    }

    public void setDetails(java.util.List<RepairOrderDetail> details) {
        this.details = details;
    }

    public void addDetail(RepairOrderDetail detail) {
        this.details.add(detail);
    }

    public void nhapInfo(Scanner sc) {
        System.out.print("Nhap bien so xe (license plate): ");
        String inputLicensePlate = sc.nextLine().trim();
        while (inputLicensePlate.isEmpty()) {
            System.out.print("Bien so xe khong duoc de trong! Moi nhap lai: ");
            inputLicensePlate = sc.nextLine().trim();
        }
        this.vehicle = new Vehicle();
        this.vehicle.setLicensePlate(inputLicensePlate); // Temporary wrapper

        while (true) {
            try {
                System.out.print("Nhap ID ky thuat vien dam nhan: ");
                int mId = Integer.parseInt(sc.nextLine());
                this.mechanic = new Mechanic();
                this.mechanic.setId(mId); // Temporary wrapper
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap ID so nguyen hop le!");
            }
        }
        System.out.print("Nhap ngay gio vao xuong (yyyy-MM-dd HH:mm:ss hoac go Enter de lay hien tai): ");
        String entryDateStr = sc.nextLine().trim();
        if (entryDateStr.isEmpty()) {
            this.entryDate = new Date();
        } else {
            try {
                this.entryDate = sdf.parse(entryDateStr);
            } catch (ParseException e) {
                System.out.println("Sai dinh dang, su dung thoi gian hien tai.");
                this.entryDate = new Date();
            }
        }
        
        System.out.print("Nhap mo ta hien trang ngoai quan xe luc tiep nhan: ");
        this.visualCondition = sc.nextLine().trim();
        this.status = "RECEIVING";
    }

    @Override
    public String toString() {
        String entryStr = entryDate != null ? sdf.format(entryDate) : "N/A";
        String exitStr = exitDate != null ? sdf.format(exitDate) : "N/A";
        String lp = vehicle != null ? vehicle.getLicensePlate() : "null";
        String mechName = mechanic != null ? mechanic.getName() : "null";
        return "RepairOrder [ID: " + orderId + ", Vehicle: " + lp + 
               ", Entry: " + entryStr + ", Exit: " + exitStr + 
               ", Mechanic: " + mechName + ", Status: " + status + 
               ", Visual Condition: " + visualCondition + ", Details: " + details.size() + " items]";
    }
}
