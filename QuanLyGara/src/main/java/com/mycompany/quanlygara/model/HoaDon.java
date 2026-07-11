package com.mycompany.quanlygara.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;

public class HoaDon {
    
    private int invoiceId;
    
    private PhieuSuaChua repairOrder;
    
    private Date paymentDate;
    
    private double totalPartCost;
    
    private double totalLaborCost;
    
    private double vatRate; 
    
    private double totalAmount;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    
    public HoaDon() {
        this.paymentDate = new Date();
        this.vatRate = 0.1; 
    }

    
    public HoaDon(int invoiceId, PhieuSuaChua repairOrder, Date paymentDate, double totalPartCost, double totalLaborCost, double vatRate, double totalAmount) {
        this.invoiceId = invoiceId;
        this.repairOrder = repairOrder;
        this.paymentDate = paymentDate;
        this.totalPartCost = totalPartCost;
        this.totalLaborCost = totalLaborCost;
        this.vatRate = vatRate;
        this.totalAmount = totalAmount;
    }

    
    public int getInvoiceId() { return invoiceId; }
    
    public void setInvoiceId(int invoiceId) { this.invoiceId = invoiceId; }

    
    public PhieuSuaChua getRepairOrder() { return repairOrder; }
    
    public void setRepairOrder(PhieuSuaChua repairOrder) { this.repairOrder = repairOrder; }

    
    public Date getPaymentDate() { return paymentDate; }
    
    public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }

    
    public double getTotalPartCost() { return totalPartCost; }
    
    public void setTotalPartCost(double totalPartCost) { this.totalPartCost = totalPartCost; }

    
    public double getTotalLaborCost() { return totalLaborCost; }
    
    public void setTotalLaborCost(double totalLaborCost) { this.totalLaborCost = totalLaborCost; }

    
    public double getVatRate() { return vatRate; }
    
    public void setVatRate(double vatRate) { this.vatRate = vatRate; }

    
    public double getTotalAmount() { return totalAmount; }
    
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    
    public void calculateTotal() {
        double subTotal = totalPartCost + totalLaborCost;
        this.totalAmount = subTotal + (subTotal * vatRate);
    }

    
    public void printInvoice() {
        System.out.println("----- HOA DON THANH TOAN -----");
        System.out.println("Ma hoa don: " + invoiceId);
        System.out.println("Ma phieu sua: " + (repairOrder != null ? repairOrder.getOrderId() : "N/A"));
        System.out.println("Ngay thanh toan: " + (paymentDate != null ? sdf.format(paymentDate) : "N/A"));
        System.out.println("Tong tien linh kien: " + String.format("%,.0f", totalPartCost) + " VND");
        System.out.println("Tong tien cong (Dich vu): " + String.format("%,.0f", totalLaborCost) + " VND");
        System.out.println("Thue VAT (" + (vatRate * 100) + "%): " + String.format("%,.0f", (totalPartCost + totalLaborCost) * vatRate) + " VND");
        System.out.println("-> TONG THANH TOAN: " + String.format("%,.0f", totalAmount) + " VND");
        System.out.println("------------------------------");
    }

    
    public void nhapInfo(Scanner sc) {
        while (true) {
            try {
                System.out.print("Nhap ID phieu sua chua: ");
                int oId = Integer.parseInt(sc.nextLine());
                this.repairOrder = new PhieuSuaChua();
                this.repairOrder.setOrderId(oId);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so nguyen hop le!");
            }
        }
        System.out.print("Nhap ngay thanh toan (yyyy-MM-dd HH:mm:ss hoac go Enter de lay hien tai): ");
        String dStr = com.mycompany.quanlygara.util.TienIchChuoi.removeAccents(sc.nextLine().trim());
        if (dStr.isEmpty()) {
            this.paymentDate = new Date();
        } else {
            try {
                this.paymentDate = sdf.parse(dStr);
            } catch (ParseException e) {
                System.out.println("Sai dinh dang, dung hien tai.");
                this.paymentDate = new Date();
            }
        }
        while (true) {
            try {
                System.out.print("Nhap tong tien linh kien: ");
                this.totalPartCost = Double.parseDouble(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Nhap so hop le!");
            }
        }
        while (true) {
            try {
                System.out.print("Nhap tong tien cong (dich vu): ");
                this.totalLaborCost = Double.parseDouble(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Nhap so hop le!");
            }
        }
        calculateTotal();
    }

    
    @Override
    public String toString() {
        return "HoaDon [ID: " + invoiceId + ", OrderID: " + (repairOrder != null ? repairOrder.getOrderId() : "N/A") + 
               ", Payment Date: " + (paymentDate != null ? sdf.format(paymentDate) : "N/A") + 
               ", Total Amount: " + String.format("%,.0f", totalAmount) + " VND]";
    }
}
