
package com.mycompany.quanlygara.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class PhieuSuaChua {
    
    private int orderId;
    
    private Xe vehicle;
    
    private Date entryDate;
    
    private Date exitDate;
    
    private KyThuatVien mechanic;
    
    private String status; 
    
    private String visualCondition;
    
    private java.util.List<ChiTietPhieuSua> details;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    
    public PhieuSuaChua() {
        this.entryDate = new Date();
        this.status = "RECEIVING";
        this.details = new java.util.ArrayList<>();
    }

    
    public PhieuSuaChua(int orderId, Xe vehicle, Date entryDate, Date exitDate, KyThuatVien mechanic, String status, String visualCondition) {
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

    
    public Xe getVehicle() {
        return vehicle;
    }

    
    public void setVehicle(Xe vehicle) {
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

    
    public KyThuatVien getMechanic() {
        return mechanic;
    }

    
    public void setMechanic(KyThuatVien mechanic) {
        this.mechanic = mechanic;
    }

    
    public String getStatus() {
        return status;
    }

    
    public void setStatus(String status) {
        this.status = status;
    }

    
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

    public java.util.List<ChiTietPhieuSua> getDetails() {
        return details;
    }

    
    public void setDetails(java.util.List<ChiTietPhieuSua> details) {
        this.details = details;
    }

    
    public void addDetail(ChiTietPhieuSua detail) {
        this.details.add(detail);
    }

    
    public void nhapInfo(Scanner sc) {
        String inputLicensePlate = "";
        String plateTypeChoice = "";
        while (true) {
            System.out.println("--- Chon loai bien so xe ---");
            System.out.println("1. Bien dan su (Trang, Vang, Xanh...) - Vi du: 29A-123.45, 51F-999.99");
            System.out.println("2. Bien quan su (Quan doi)           - Vi du: AA-12-34");
            System.out.print("Nhap lua chon cua ban (1-2): ");
            plateTypeChoice = com.mycompany.quanlygara.util.TienIchChuoi.removeAccents(sc.nextLine().trim());
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
            inputLicensePlate = com.mycompany.quanlygara.util.TienIchChuoi.removeAccents(sc.nextLine().trim()).toUpperCase();
            
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
                    if (Xe.getProvinceName(provCode) != null) {
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
        this.vehicle = new Xe();
        this.vehicle.setLicensePlate(inputLicensePlate); 

        while (true) {
            try {
                System.out.println("--- Danh sach Ky thuat vien (Tho may) hien co ---");
                com.mycompany.quanlygara.controller.KyThuatVienDAO mechanicDAO = new com.mycompany.quanlygara.controller.KyThuatVienDAO();
                java.util.List<KyThuatVien> mechanics = mechanicDAO.layTatCa();
                com.mycompany.quanlygara.util.DinhDangBang.printMechanics(mechanics);
                
                System.out.print("Nhap ID ky thuat vien dam nhan: ");
                int mId = Integer.parseInt(sc.nextLine());
                
                
                boolean exists = false;
                for (KyThuatVien m : mechanics) {
                    if (m.getId() == mId) {
                        exists = true;
                        break;
                    }
                }
                
                if (!exists) {
                    System.out.println("Loi: ID ky thuat vien khong ton tai! Vui long nhap dung ID tu danh sach tren.");
                    continue;
                }
                
                this.mechanic = new KyThuatVien();
                this.mechanic.setId(mId); 
                break;
            } catch (NumberFormatException e) {
                System.out.println("Loi: Vui long nhap ID la so nguyen hop le!");
            } catch (Exception e) {
                System.out.println("Loi lay du lieu: " + e.getMessage());
            }
        }
        System.out.print("Nhap ngay gio vao xuong (yyyy-MM-dd HH:mm:ss hoac go Enter de lay hien tai): ");
        String entryDateStr = com.mycompany.quanlygara.util.TienIchChuoi.removeAccents(sc.nextLine().trim());
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
        this.visualCondition = com.mycompany.quanlygara.util.TienIchChuoi.removeAccents(sc.nextLine().trim());
        this.status = "RECEIVING";
    }

    
    @Override
    public String toString() {
        String entryStr = entryDate != null ? sdf.format(entryDate) : "N/A";
        String exitStr = exitDate != null ? sdf.format(exitDate) : "N/A";
        String lp = vehicle != null ? vehicle.getLicensePlate() : "null";
        String mechName = mechanic != null ? mechanic.getName() : "null";
        return "PhieuSuaChua [ID: " + orderId + ", Xe: " + lp + 
               ", Entry: " + entryStr + ", Exit: " + exitStr + 
               ", KyThuatVien: " + mechName + ", Status: " + getFriendlyStatus() + 
               ", Visual Condition: " + visualCondition + ", Details: " + details.size() + " items]";
    }
}
