package com.mycompany.quanlygara.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;

public class Invoice {
    // Mã hóa đơn (Invoice ID)
    private int invoiceId;
    // Phiếu sửa chữa (Repair Order)
    private RepairOrder repairOrder;
    // Ngày thanh toán (Payment Date)
    private Date paymentDate;
    // Tổng chi phí linh kiện (Total Part Cost)
    private double totalPartCost;
    // Tổng chi phí nhân công/dịch vụ (Total Labor Cost)
    private double totalLaborCost;
    // Thuế suất giá trị gia tăng (VAT Rate)
    private double vatRate; // ví dụ 0.1 cho 10%
    // Tổng số tiền thanh toán (Total Amount)
    private double totalAmount;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // Khởi tạo đối tượng Invoice mới
    public Invoice() {
        this.paymentDate = new Date();
        this.vatRate = 0.1; // mac dinh VAT 10%
    }

    // Khởi tạo đối tượng Invoice mới
    public Invoice(int invoiceId, RepairOrder repairOrder, Date paymentDate, double totalPartCost, double totalLaborCost, double vatRate, double totalAmount) {
        this.invoiceId = invoiceId;
        this.repairOrder = repairOrder;
        this.paymentDate = paymentDate;
        this.totalPartCost = totalPartCost;
        this.totalLaborCost = totalLaborCost;
        this.vatRate = vatRate;
        this.totalAmount = totalAmount;
    }

    // Lấy giá trị của thuộc tính InvoiceId
    public int getInvoiceId() { return invoiceId; }
    // Cập nhật giá trị cho thuộc tính InvoiceId
    public void setInvoiceId(int invoiceId) { this.invoiceId = invoiceId; }

    // Lấy giá trị của thuộc tính RepairOrder
    public RepairOrder getRepairOrder() { return repairOrder; }
    // Cập nhật giá trị cho thuộc tính RepairOrder
    public void setRepairOrder(RepairOrder repairOrder) { this.repairOrder = repairOrder; }

    // Lấy giá trị của thuộc tính PaymentDate
    public Date getPaymentDate() { return paymentDate; }
    // Cập nhật giá trị cho thuộc tính PaymentDate
    public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }

    // Lấy giá trị của thuộc tính TotalPartCost
    public double getTotalPartCost() { return totalPartCost; }
    // Cập nhật giá trị cho thuộc tính TotalPartCost
    public void setTotalPartCost(double totalPartCost) { this.totalPartCost = totalPartCost; }

    // Lấy giá trị của thuộc tính TotalLaborCost
    public double getTotalLaborCost() { return totalLaborCost; }
    // Cập nhật giá trị cho thuộc tính TotalLaborCost
    public void setTotalLaborCost(double totalLaborCost) { this.totalLaborCost = totalLaborCost; }

    // Lấy giá trị của thuộc tính VatRate
    public double getVatRate() { return vatRate; }
    // Cập nhật giá trị cho thuộc tính VatRate
    public void setVatRate(double vatRate) { this.vatRate = vatRate; }

    // Lấy giá trị của thuộc tính TotalAmount
    public double getTotalAmount() { return totalAmount; }
    // Cập nhật giá trị cho thuộc tính TotalAmount
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    // Tính toán tổng chi phí của hóa đơn (bao gồm linh kiện, dịch vụ và VAT)
    public void calculateTotal() {
        double subTotal = totalPartCost + totalLaborCost;
        this.totalAmount = subTotal + (subTotal * vatRate);
    }

    // In thông tin chi tiết của hóa đơn ra màn hình
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

    // Nhập thông tin cho đối tượng từ giao diện Console
    public void nhapInfo(Scanner sc) {
        while (true) {
            try {
                System.out.print("Nhap ID phieu sua chua: ");
                int oId = Integer.parseInt(sc.nextLine());
                this.repairOrder = new RepairOrder();
                this.repairOrder.setOrderId(oId);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so nguyen hop le!");
            }
        }
        System.out.print("Nhap ngay thanh toan (yyyy-MM-dd HH:mm:ss hoac go Enter de lay hien tai): ");
        String dStr = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
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

    // Trả về chuỗi đại diện chứa thông tin của đối tượng
    @Override
    public String toString() {
        return "Invoice [ID: " + invoiceId + ", OrderID: " + (repairOrder != null ? repairOrder.getOrderId() : "N/A") + 
               ", Payment Date: " + (paymentDate != null ? sdf.format(paymentDate) : "N/A") + 
               ", Total Amount: " + String.format("%,.0f", totalAmount) + " VND]";
    }
}
