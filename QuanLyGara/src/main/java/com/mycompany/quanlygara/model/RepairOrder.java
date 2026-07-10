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

    // Lấy trạng thái thân thiện không dấu tiếng Việt
    public String getFriendlyStatus() {
        if (this.status == null) return "";
        switch (this.status.toUpperCase()) {
            case "RECEIVING":
                return "TIEP NHAN";
            case "REPAIRING":
            case "IN_PROGRESS":
                return "DANG SUA";
            case "COMPLETED":
                return "HOAN THANH";
            default:
                return this.status;
        }
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
        String inputLicensePlate = "";
        String plateTypeChoice = "";
        while (true) {
            System.out.println("--- Chon loai bien so xe ---");
            System.out.println("1. Bien dan su (Trang, Vang, Xanh...) - Vi du: 29A-123.45, 51F-999.99");
            System.out.println("2. Bien quan su (Quan doi)           - Vi du: AA-12-34");
            System.out.print("Nhap lua chon cua ban (1-2): ");
            plateTypeChoice = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
            if (plateTypeChoice.matches("[1-2]")) {
                break;
            }
            System.out.println("Lua chon khong hop le! Vui long nhap 1 hoac 2.");
        }
        
        while (true) {
            if (plateTypeChoice.equals("2")) {
                System.out.print("Nhap bien so xe quan doi (Vi du: AA-12-34): ");
            } else {
                System.out.print("Nhap bien so xe dan su (Vi du: 29A-123.45): ");
            }
            inputLicensePlate = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim()).toUpperCase();
            
            if (inputLicensePlate.isEmpty()) {
                System.out.println("Bien so xe khong duoc de trong!");
                continue;
            }
            
            boolean isValid = false;
            if (plateTypeChoice.equals("2")) {
                if (inputLicensePlate.matches("^[A-Z]{2}[- ]?([0-9]{2}[- ]?[0-9]{2}|[0-9]{4,5})$")) {
                    isValid = true;
                } else {
                    System.out.println("Loi: Bien so quan doi khong dung dinh dang! Vi du: AA-12-34.");
                }
            } else {
                if (inputLicensePlate.matches("^([0-9]{2})([A-Z]{1,2}[0-9]?)[- ]?([0-9]{3,5}|[0-9]{3}\\.[0-9]{2})$")) {
                    String provCode = inputLicensePlate.substring(0, 2);
                    if (Vehicle.getProvinceName(provCode) != null) {
                        isValid = true;
                    } else {
                        System.out.println("Loi: Hai chu so dau tien '" + provCode + "' khong phai ma tinh/thanh pho hop le cua Viet Nam!");
                    }
                } else {
                    System.out.println("Loi: Dinh dang bien so xe khong hop le! Vi du: 29A-123.45.");
                }
            }
            
            if (isValid) {
                break;
            }
        }
        this.vehicle = new Vehicle();
        this.vehicle.setLicensePlate(inputLicensePlate); // Temporary wrapper

        while (true) {
            try {
                System.out.println("--- Danh sach Ky thuat vien (Tho may) hien co ---");
                com.mycompany.quanlygara.controller.KyThuatVienDAO mechanicDAO = new com.mycompany.quanlygara.controller.KyThuatVienDAO();
                java.util.List<Mechanic> mechanics = mechanicDAO.layTatCa();
                com.mycompany.quanlygara.util.TableFormatter.printMechanics(mechanics);
                
                System.out.print("Nhap ID ky thuat vien dam nhan: ");
                int mId = Integer.parseInt(sc.nextLine());
                
                // Validate if mechanic exists in database
                boolean exists = false;
                for (Mechanic m : mechanics) {
                    if (m.getId() == mId) {
                        exists = true;
                        break;
                    }
                }
                
                if (!exists) {
                    System.out.println("Loi: ID ky thuat vien khong ton tai! Vui long nhap dung ID tu danh sach tren.");
                    continue;
                }
                
                this.mechanic = new Mechanic();
                this.mechanic.setId(mId); // Temporary wrapper
                break;
            } catch (NumberFormatException e) {
                System.out.println("Loi: Vui long nhap ID la so nguyen hop le!");
            } catch (Exception e) {
                System.out.println("Loi lay du lieu: " + e.getMessage());
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
               ", Mechanic: " + mechName + ", Status: " + getFriendlyStatus() + 
               ", Visual Condition: " + visualCondition + ", Details: " + details.size() + " items]";
    }
}
