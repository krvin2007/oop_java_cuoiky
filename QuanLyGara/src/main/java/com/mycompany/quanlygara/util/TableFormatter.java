package com.mycompany.quanlygara.util;

import java.util.List;
import com.mycompany.quanlygara.model.*;

public class TableFormatter {

    public static void printCustomers(List<Customer> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Danh sach trong.");
            return;
        }
        System.out.println("+-------+----------------------+--------------+--------------------------------+----------------------+");
        System.out.println("| ID    | Ten Khach Hang       | Dien thoai   | Dia chi                        | Email                |");
        System.out.println("+-------+----------------------+--------------+--------------------------------+----------------------+");
        for (Customer c : list) {
            System.out.printf("| %-5d | %-20s | %-12s | %-30s | %-20s |\n",
                c.getId(), truncate(c.getName(), 20), truncate(c.getPhone(), 12), truncate(c.getAddress(), 30), truncate(c.getEmail(), 20));
        }
        System.out.println("+-------+----------------------+--------------+--------------------------------+----------------------+");
    }

    public static void printVehicles(List<Vehicle> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Danh sach trong.");
            return;
        }
        System.out.println("+--------------+-----------------+-----------------+------+-----------------+----------------------+----------------------+");
        System.out.println("| Bien So      | Hang Xe         | Dong Xe         | Nam  | Mau Sac         | Tinh Trang           | Chu Xe               |");
        System.out.println("+--------------+-----------------+-----------------+------+-----------------+----------------------+----------------------+");
        for (Vehicle v : list) {
            String ownerName = v.getOwner() != null ? v.getOwner().getName() : "N/A";
            System.out.printf("| %-12s | %-15s | %-15s | %-4d | %-15s | %-20s | %-20s |\n",
                truncate(v.getLicensePlate(), 12), truncate(v.getBrand(), 15), truncate(v.getModel(), 15), 
                v.getProductionYear(), truncate(v.getColor(), 15), truncate(v.getCondition(), 20), truncate(ownerName, 20));
        }
        System.out.println("+--------------+-----------------+-----------------+------+-----------------+----------------------+----------------------+");
    }

    public static void printMechanics(List<Mechanic> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Danh sach trong.");
            return;
        }
        System.out.println("+-------+----------------------+--------------+----------------------+-----------------+-----------------+");
        System.out.println("| ID    | Ten KTV              | Dien thoai   | Chuyen Mon           | Luong           | Trang Thai      |");
        System.out.println("+-------+----------------------+--------------+----------------------+-----------------+-----------------+");
        for (Mechanic m : list) {
            System.out.printf("| %-5d | %-20s | %-12s | %-20s | %-15s | %-15s |\n",
                m.getId(), truncate(m.getName(), 20), truncate(m.getPhone(), 12), truncate(m.getSpec(), 20), 
                String.format("%,.0f", m.getSalary()), truncate(m.getStatus(), 15));
        }
        System.out.println("+-------+----------------------+--------------+----------------------+-----------------+-----------------+");
    }

    public static void printParts(List<LinhKien> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Danh sach trong.");
            return;
        }
        System.out.println("+------------+--------------------------------+-----------------+-----------+----------------------+");
        System.out.println("| Ma LK      | Ten Linh Kien                  | Don Gia         | SL Ton    | Vi Tri Kho           |");
        System.out.println("+------------+--------------------------------+-----------------+-----------+----------------------+");
        for (LinhKien p : list) {
            System.out.printf("| %-10s | %-30s | %-15s | %-9d | %-20s |\n",
                truncate(p.getMa(), 10), truncate(p.getTen(), 30), String.format("%,.0f", p.getDonGia()), p.getSoLuongTon(), truncate(p.getLocation(), 20));
        }
        System.out.println("+------------+--------------------------------+-----------------+-----------+----------------------+");
    }

    public static void printServices(List<DichVu> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Danh sach trong.");
            return;
        }
        System.out.println("+------------+--------------------------------+-----------------+");
        System.out.println("| Ma DV      | Ten Dich Vu                    | Don Gia         |");
        System.out.println("+------------+--------------------------------+-----------------+");
        for (DichVu d : list) {
            System.out.printf("| %-10s | %-30s | %-15s |\n",
                truncate(d.getMa(), 10), truncate(d.getTen(), 30), String.format("%,.0f", d.getDonGia()));
        }
        System.out.println("+------------+--------------------------------+-----------------+");
    }

    public static void printRepairOrders(List<RepairOrder> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Danh sach trong.");
            return;
        }
        System.out.println("+-------+--------------+----------------------+----------------------+------------+");
        System.out.println("| ID    | Bien So Xe   | Ky Thuat Vien        | Ngay Tiep Nhan       | Trang Thai |");
        System.out.println("+-------+--------------+----------------------+----------------------+------------+");
        for (RepairOrder r : list) {
            String lp = r.getVehicle() != null ? r.getVehicle().getLicensePlate() : "N/A";
            String mName = r.getMechanic() != null ? r.getMechanic().getName() : "N/A";
            String dStr = r.getEntryDate() != null ? new java.text.SimpleDateFormat("dd/MM/yyyy").format(r.getEntryDate()) : "N/A";
            System.out.printf("| %-5d | %-12s | %-20s | %-20s | %-10s |\n",
                r.getOrderId(), truncate(lp, 12), truncate(mName, 20), truncate(dStr, 20), truncate(r.getStatus(), 10));
        }
        System.out.println("+-------+--------------+----------------------+----------------------+------------+");
    }

    public static void printInvoices(List<Invoice> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Danh sach trong.");
            return;
        }
        System.out.println("+-------+----------+----------------------+-----------------+");
        System.out.println("| ID HD | Phieu SC | Ngay Lap HD          | Tong Tien       |");
        System.out.println("+-------+----------+----------------------+-----------------+");
        for (Invoice inv : list) {
            String oId = inv.getRepairOrder() != null ? String.valueOf(inv.getRepairOrder().getOrderId()) : "N/A";
            String dStr = inv.getPaymentDate() != null ? new java.text.SimpleDateFormat("dd/MM/yyyy").format(inv.getPaymentDate()) : "N/A";
            System.out.printf("| %-5d | %-8s | %-20s | %-15s |\n",
                inv.getInvoiceId(), truncate(oId, 8), truncate(dStr, 20), String.format("%,.0f", inv.getTotalAmount()));
        }
        System.out.println("+-------+----------+----------------------+-----------------+");
    }

    private static String truncate(String str, int len) {
        if (str == null) return "";
        if (str.length() > len) {
            return str.substring(0, len - 3) + "...";
        }
        return str;
    }
}
