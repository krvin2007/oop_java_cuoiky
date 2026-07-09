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
    // Mã phiếu sửa chữa (Order ID)
    private int orderId;
    // Xe được sửa chữa (Vehicle)
    private Vehicle vehicle;
    // Ngày xe vào xưởng (Entry Date)
    private Date entryDate;
    // Ngày xe xuất xưởng (Exit Date)
    private Date exitDate;
    // Kỹ thuật viên phụ trách (Mechanic)
    private Mechanic mechanic;
    // Trạng thái phiếu sửa chữa (Status: RECEIVING, REPAIRING, COMPLETED)
    private String status; // RECEIVING, REPAIRING, COMPLETED
    // Tình trạng ngoại quan của xe (Visual Condition)
    private String visualCondition;
    // Danh sách chi tiết hạng mục sửa chữa (Details)
    private java.util.List<RepairOrderDetail> details;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // Khởi tạo đối tượng RepairOrder mới
    public RepairOrder() {
        this.entryDate = new Date();
        this.status = "RECEIVING";
        this.details = new java.util.ArrayList<>();
    }

    // Khởi tạo đối tượng RepairOrder mới
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

    // Lấy giá trị của thuộc tính VisualCondition
    public String getVisualCondition() {
        return visualCondition;
    }

    // Cập nhật giá trị cho thuộc tính VisualCondition
    public void setVisualCondition(String visualCondition) {
        this.visualCondition = visualCondition;
    }

    // Lấy giá trị của thuộc tính OrderId
    public int getOrderId() {
        return orderId;
    }

    // Cập nhật giá trị cho thuộc tính OrderId
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    // Lấy giá trị của thuộc tính Vehicle
    public Vehicle getVehicle() {
        return vehicle;
    }

    // Cập nhật giá trị cho thuộc tính Vehicle
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    // Lấy giá trị của thuộc tính EntryDate
    public Date getEntryDate() {
        return entryDate;
    }

    // Cập nhật giá trị cho thuộc tính EntryDate
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    // Lấy giá trị của thuộc tính ExitDate
    public Date getExitDate() {
        return exitDate;
    }

    // Cập nhật giá trị cho thuộc tính ExitDate
    public void setExitDate(Date exitDate) {
        this.exitDate = exitDate;
    }

    // Lấy giá trị của thuộc tính Mechanic
    public Mechanic getMechanic() {
        return mechanic;
    }

    // Cập nhật giá trị cho thuộc tính Mechanic
    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    // Lấy giá trị của thuộc tính Status
    public String getStatus() {
        return status;
    }

    // Cập nhật giá trị cho thuộc tính Status
    public void setStatus(String status) {
        this.status = status;
    }

    public java.util.List<RepairOrderDetail> getDetails() {
        return details;
    }

    // Cập nhật giá trị cho thuộc tính Details
    public void setDetails(java.util.List<RepairOrderDetail> details) {
        this.details = details;
    }

    // Thêm một chi tiết vào danh sách phiếu sửa chữa
    public void addDetail(RepairOrderDetail detail) {
        this.details.add(detail);
    }

    // Nhập thông tin cho đối tượng từ giao diện Console
    public void nhapInfo(Scanner sc) {
        System.out.print("Nhap bien so xe (license plate): ");
        String inputLicensePlate = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
        while (inputLicensePlate.isEmpty()) {
            System.out.print("Bien so xe khong duoc de trong! Moi nhap lai: ");
            inputLicensePlate = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
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
        String entryDateStr = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
        if (entryDateStr.isEmpty()) {
            this.entryDate = new Date();
        } else {
            try {
                this.entryDate = sdf.parse(entryDateStr);
                if (this.entryDate.after(new Date())) {
                    System.out.println("Loi logic: Ngay vao xuong khong duoc o tuong lai! He thong tu dong lay thoi gian hien tai.");
                    this.entryDate = new Date();
                }
            } catch (ParseException e) {
                System.out.println("Sai dinh dang, su dung thoi gian hien tai.");
                this.entryDate = new Date();
            }
        }
        
        System.out.print("Nhap mo ta hien trang ngoai quan xe luc tiep nhan: ");
        this.visualCondition = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
        this.status = "RECEIVING";
    }

    // Trả về chuỗi đại diện chứa thông tin của đối tượng
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
