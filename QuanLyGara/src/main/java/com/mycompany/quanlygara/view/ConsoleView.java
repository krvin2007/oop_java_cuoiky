/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlygara.view;

import com.mycompany.quanlygara.controller.*;
import com.mycompany.quanlygara.model.*;
import java.util.*;

/**
 *
 * @author ManhQuynh | TĂ i lĂ m nhiá»u nháº¥t
 */
public class ConsoleView {
    // Äá»‘i tÆ°á»£ng nháº­p liá»‡u tá»« bĂ n phĂ­m (Scanner)
    private final Scanner sc;
    // Bá»™ Ä‘iá»u khiá»ƒn/Quáº£n lĂ½ chá»§ xe (Owner/Customer Controller)
    private final ChuXeDAO ownerController;
    // Bá»™ Ä‘iá»u khiá»ƒn/Quáº£n lĂ½ xe (Vehicle Controller)
    private final XeDAO vehicleController;
    // Bá»™ Ä‘iá»u khiá»ƒn/Quáº£n lĂ½ ká»¹ thuáº­t viĂªn (Mechanic Controller)
    private final KyThuatVienDAO mechanicController;
    // Bá»™ Ä‘iá»u khiá»ƒn/Quáº£n lĂ½ linh kiá»‡n (Part Controller)
    private final LinhKienDAO partController;
    // Bá»™ Ä‘iá»u khiá»ƒn/Quáº£n lĂ½ phiáº¿u sá»­a chá»¯a (Repair Order Controller)
    private final PhieuSuaChuaDAO repairOrderController;
    // Bá»™ Ä‘iá»u khiá»ƒn/Quáº£n lĂ½ hĂ³a Ä‘Æ¡n (Invoice Controller)
    private final HoaDonDAO invoiceController;
    // Bá»™ Ä‘iá»u khiá»ƒn/Quáº£n lĂ½ bĂ¡o cĂ¡o (Report Controller)
    private final ReportController reportController;
    // Bá»™ Ä‘iá»u khiá»ƒn/Quáº£n lĂ½ dá»‹ch vá»¥ (Service Controller)
    private final DichVuDAO dichVuDAO;
    // Bá»™ Ä‘iá»u khiá»ƒn/Quáº£n lĂ½ nhĂ¢n viĂªn (Employee Controller)
    private final EmployeeDAO employeeDAO;
    // NgÆ°á»i dĂ¹ng hiá»‡n táº¡i Ä‘ang Ä‘Äƒng nháº­p (Current User)
    private Employee currentUser;

    // Khá»Ÿi táº¡o Ä‘á»‘i tÆ°á»£ng ConsoleView má»›i
    public ConsoleView() {
        this.sc = new Scanner(System.in);
        this.ownerController = new ChuXeDAO();
        this.vehicleController = new XeDAO();
        this.mechanicController = new KyThuatVienDAO();
        this.partController = new LinhKienDAO();
        this.repairOrderController = new PhieuSuaChuaDAO();
        this.invoiceController = new HoaDonDAO();
        this.reportController = new ReportController();
        this.dichVuDAO = new DichVuDAO();
        this.employeeDAO = new EmployeeDAO();
        this.currentUser = null;
    }

    // PhÆ°Æ¡ng thá»©c main - Äiá»ƒm báº¯t Ä‘áº§u cháº¡y chÆ°Æ¡ng trĂ¬nh
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();
        view.start();
    }

    // Khá»Ÿi Ä‘á»™ng giao diá»‡n chĂ­nh cá»§a chÆ°Æ¡ng trĂ¬nh
    public void start() {
        System.out.println("===============================================================");
        System.out.println("              CHAO MUNG DEN HE THONG QUAN LY GARA              ");
        System.out.println("===============================================================");

        while (true) {
            while (currentUser == null) {
                System.out.println("\n--- VUI LONG DANG NHAP ---");
                System.out.print("Username (nhap 'exit' de thoat): ");
                String username = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
                if (username.equalsIgnoreCase("exit")) {
                    System.out.println("Cam on ban da su dung chuong trinh!");
                    return;
                }
                System.out.print("Password: ");
                String password = sc.nextLine().trim();
                
                if (com.mycompany.quanlygara.util.StringUtils.hasAccents(password)) {
                    System.out.println("Loi: Mat khau khong the chua tieng Viet co dau!");
                    continue;
                }

                currentUser = employeeDAO.login(username, password);
                if (currentUser != null) {
                    System.out.println(
                            "Dang nhap thanh cong! Xin chao " + currentUser.getName() + " (Chuc vu: "
                                    + currentUser.getRole() + ")");
                } else {
                    System.out.println("Sai tai khoan hoac mat khau. Vui long thu lai!");
                }
            }

            int choice = -1;
            do {
                showMainMenu();
                try {
                    System.out.print("Nhap lua chon cua ban (0-8): ");
                    choice = Integer.parseInt(sc.nextLine());
                    System.out.println();
                    if (choice == 0) {
                        System.out.println("Da dang xuat thanh cong!");
                        currentUser = null;
                        break;
                    }

                    boolean hasPermission = false;
                    String role = currentUser.getRole();
                    if (choice == 0 || choice == 8) {
                        hasPermission = true;
                    } else if (role.equals("QuanLy")) {
                        hasPermission = true;
                    } else if (role.equals("KeToan") && (choice == 5 || choice == 6)) {
                        hasPermission = true;
                    } else if (role.equals("ThuKho") && (choice == 3)) {
                        hasPermission = true;
                    } else if (role.equals("KyThuat") && (choice == 1 || choice == 4)) {
                        hasPermission = true;
                    }

                    if (!hasPermission) {
                        System.out.println("----------------------------------------------------");
                        System.out.println("LOI: Ban (" + role + ") khong co quyen truy cap chuc nang nay!");
                        System.out.println("----------------------------------------------------");
                        continue;
                    }

                    switch (choice) {
                        case 1:
                            menuVehicleAndOwner();
                            break;
                        case 2:
                            menuMechanic();
                            break;
                        case 3:
                            menuPart();
                            break;
                        case 4:
                            menuRepairOrder();
                            break;
                        case 5:
                            menuInvoice();
                            break;
                        case 6:
                            menuReport();
                            break;
                        case 7:
                            menuDichVu();
                            break;
                        case 8:
                            changePassword();
                            break;
                        default:
                            System.out.println("Lua chon khong hop le! Vui long nhap tu 0 den 8.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Loi: Vui long nhap so nguyen hop le!");
                } catch (Exception e) {
                    System.out.println("Da xay ra loi: " + e.getMessage());
                }
                System.out.println();
            } while (currentUser != null);
        }
    }

    // Thá»±c thi phÆ°Æ¡ng thá»©c changePassword Ä‘á»ƒ xá»­ lĂ½ logic tÆ°Æ¡ng á»©ng
    private void changePassword() {
        System.out.println("===============================================================");
        System.out.println("                        DOI MAT KHAU                           ");
        System.out.println("===============================================================");
        System.out.print("Nhap mat khau hien tai: ");
        String currentPwd = sc.nextLine().trim();
        if (!currentPwd.equals(currentUser.getPassword())) {
            System.out.println("Mat khau hien tai khong dung!");
            return;
        }
        System.out.print("Nhap mat khau moi: ");
        String newPwd = sc.nextLine().trim();
        if (newPwd.isEmpty()) {
            System.out.println("Mat khau moi khong duoc de trong!");
            return;
        }
        if (com.mycompany.quanlygara.util.StringUtils.hasAccents(newPwd)) {
            System.out.println("Loi: Mat khau moi khong duoc chua tieng Viet co dau!");
            return;
        }
        System.out.print("Xac nhan mat khau moi: ");
        String confirmPwd = sc.nextLine().trim();
        if (!newPwd.equals(confirmPwd)) {
            System.out.println("Xac nhan mat khau khong khop!");
            return;
        }

        boolean success = employeeDAO.updatePassword(currentUser.getUsername(), newPwd);
        if (success) {
            currentUser.setPassword(newPwd);
            System.out.println("Doi mat khau thanh cong!");
        } else {
            System.out.println("Doi mat khau that bai!");
        }
    }

    // Hiá»ƒn thá»‹ menu chĂ­nh cá»§a há»‡ thá»‘ng
    private void showMainMenu() {
        String role = currentUser != null ? currentUser.getRole() : "";

        System.out.println("===============================================================");
        System.out.println("              HE THONG QUAN LY GARA SUA CHUA O TO              ");
        System.out.println("===============================================================");
        System.out.println("1. Quan ly Xe va Chu xe .................... "
                + ((role.equals("QuanLy") || role.equals("KyThuat")) ? "[    CO QUYEN    ]" : "[ KHONG CO QUYEN ]"));
        System.out.println("2. Quan ly Ky thuat vien (Ky thuat vien) ... "
                + (role.equals("QuanLy") ? "[    CO QUYEN    ]" : "[ KHONG CO QUYEN ]"));
        System.out.println("3. Quan ly Kho linh kien (Linh kien) ....... "
                + ((role.equals("QuanLy") || role.equals("ThuKho")) ? "[    CO QUYEN    ]" : "[ KHONG CO QUYEN ]"));
        System.out.println("4. Quan ly Phieu sua chua (Phieu sua chua) . "
                + ((role.equals("QuanLy") || role.equals("KyThuat")) ? "[    CO QUYEN    ]" : "[ KHONG CO QUYEN ]"));
        System.out.println("5. Thanh toan & Hoa don (Hoa don) .......... "
                + ((role.equals("QuanLy") || role.equals("KeToan")) ? "[    CO QUYEN    ]" : "[ KHONG CO QUYEN ]"));
        System.out.println("6. Bao cao thong ke doanh thu & hieu suat .. "
                + ((role.equals("QuanLy") || role.equals("KeToan")) ? "[    CO QUYEN    ]" : "[ KHONG CO QUYEN ]"));
        System.out.println("7. Quan ly Danh muc Dich vu (Dich vu) ...... "
                + (role.equals("QuanLy") ? "[    CO QUYEN    ]" : "[ KHONG CO QUYEN ]"));
        System.out.println("8. Doi mat khau ............................ [    CO QUYEN    ]");
        System.out.println("0. Dang xuat (Dang xuat) ................... [    CO QUYEN    ]");
        System.out.println("===============================================================");
    }

    // --- 1. QUAN LY XE VA CHU XE ---
    private void menuVehicleAndOwner() throws Exception {
        int choice = -1;
        do {
            System.out.println("===============================================================");
            System.out.println("                   MENU QUAN LY XE & CHU XE                    ");
            System.out.println("===============================================================");
            System.out.println("1. Them Chu xe moi (Khach hang)");
            System.out.println("2. Them Xe moi (Phuong tien)");
            System.out.println("3. Xem danh sach Chu xe");
            System.out.println("4. Xem danh sach Xe");
            System.out.println("5. Tim kiem xe theo Bien so");
            System.out.println("6. Tim kiem xe theo Ten chu xe");
            System.out.println("7. Cap nhat thong tin Chu xe");
            System.out.println("8. Xoa Xe");
            System.out.println("0. Quay lai Menu chinh");
            System.out.println("===============================================================");
            System.out.print("Nhap lua chon (0-8): ");
            try {
                choice = Integer.parseInt(sc.nextLine());

                if (currentUser != null && currentUser.getRole().equals("KyThuat") && (choice == 7 || choice == 8)) {
                    System.out.println("LOI: Ban la Ky thuat vien, khong co quyen Sua hoac Xoa thong tin Chu xe/Xe!");
                    continue;
                }

                switch (choice) {
                    case 1:
                        System.out.println("Ban muon them bao nhieu Chu xe?");
                        System.out.print("=> So luong: ");
                        int countCustomer = Integer.parseInt(sc.nextLine());
                        for (int i = 0; i < countCustomer; i++) {
                            System.out.println("--- Nhap thong tin Chu xe thu " + (i + 1) + " ---");
                            Customer newOwner = new Customer();
                            newOwner.nhapInfo(sc);
                            try {
                                ownerController.themMoi(newOwner);
                                System.out.println("Them chu xe thanh cong! ID duoc cap: " + newOwner.getId());
                            } catch (DuplicateMaException e) {
                                System.out.println("Loi trung ma: " + e.getMessage());
                                System.out.println("Vui long nhap lai thong tin cho chu xe nay!");
                                i--;
                            } catch (Exception e) {
                                System.out.println("Loi: " + e.getMessage());
                                System.out.println("Vui long nhap lai thong tin cho chu xe nay!");
                                i--;
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Ban muon them bao nhieu Xe?");
                        System.out.print("=> So luong: ");
                        int countVeh = Integer.parseInt(sc.nextLine());
                        for (int i = 0; i < countVeh; i++) {
                            System.out.println("--- Nhap thong tin Xe thu " + (i + 1) + " ---");
                            System.out.println("--- Danh sach Chu xe hien co ---");
                            List<Customer> availableOwners = ownerController.layTatCa();
                            com.mycompany.quanlygara.util.TableFormatter.printCustomers(availableOwners);
                            System.out.println("--------------------------------");
                            Vehicle newVehicle = new Vehicle();
                            newVehicle.nhapInfo(sc);
                            int oId = newVehicle.getOwner() != null ? newVehicle.getOwner().getId() : 0;
                            Customer customer = ownerController.layTheoId(oId);
                            if (customer == null) {
                                System.out.println(
                                        "Loi: Khong tim thay chu xe co ID = " + oId + ". Vui long tao chu xe truoc!");
                                System.out.println("Vui long nhap lai thong tin cho xe nay!");
                                i--;
                                continue;
                            }
                            Vehicle existing = vehicleController.layTheoId(newVehicle.getLicensePlate());
                            if (existing != null) {
                                System.out.println("Loi: Bien so xe '" + newVehicle.getLicensePlate()
                                        + "' da ton tai trong he thong!");
                                System.out.println("Vui long nhap lai thong tin cho xe nay!");
                                i--;
                                continue;
                            }
                            vehicleController.themMoi(newVehicle);
                            System.out.println("Them xe thanh cong!");
                        }
                        break;
                    case 3:
                        List<Customer> owners = ownerController.layTatCa();
                        System.out.println("Danh sach chu xe:");
                        com.mycompany.quanlygara.util.TableFormatter.printCustomers(owners);
                        break;
                    case 4:
                        List<Vehicle> vehicles = vehicleController.layTatCa();
                        System.out.println("Danh sach xe trong gara:");
                        com.mycompany.quanlygara.util.TableFormatter.printVehicles(vehicles);
                        break;
                    case 5:
                        System.out.print("Nhap bien so xe can tim: ");
                        String lp = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
                        List<Vehicle> searchLp = vehicleController.searchByLicensePlate(lp);
                        if (searchLp.isEmpty()) {
                            System.out.println("Khong tim thay xe nao.");
                        } else {
                            com.mycompany.quanlygara.util.TableFormatter.printVehicles(searchLp);
                        }
                        break;
                    case 6:
                        System.out.print("Nhap ten chu xe de tim xe: ");
                        String ownerName = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
                        List<Vehicle> searchName = vehicleController.searchByOwnerName(ownerName);
                        if (searchName.isEmpty()) {
                            System.out.println("Khong tim thay xe nao.");
                        } else {
                            com.mycompany.quanlygara.util.TableFormatter.printVehicles(searchName);
                        }
                        break;
                    case 7:
                        System.out.print("Nhap ID Chu xe can cap nhat: ");
                        int editOwnerId = Integer.parseInt(sc.nextLine());
                        Customer editOwner = ownerController.layTheoId(editOwnerId);
                        if (editOwner == null) {
                            System.out.println("Khong tim thay chu xe!");
                        } else {
                            System.out.println("Thong tin hien tai: " + editOwner);
                            editOwner.nhapInfo(sc);
                            ownerController.capNhat(editOwner);
                            System.out.println("Cap nhat thong tin chu xe thanh cong!");
                        }
                        break;
                    case 8:
                        System.out.print("Nhap Bien so xe can xoa: ");
                        String deleteLp = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
                        Vehicle delVeh = vehicleController.layTheoId(deleteLp);
                        if (delVeh == null) {
                            System.out.println("Xe khong ton tai!");
                        } else {
                            vehicleController.xoa(deleteLp);
                            System.out.println("Da xoa xe thanh cong.");
                        }
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Lua chon khong hop le!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Loi: Vui long nhap so nguyen hop le!");
            }
            System.out.println();
        } while (choice != 0);
    }

    // --- 2. QUAN LY KY THUAT VIEN ---
    private void menuMechanic() throws Exception {
        int choice = -1;
        do {
            System.out.println("===============================================================");
            System.out.println("                  MENU QUAN LY KY THUAT VIEN                   ");
            System.out.println("===============================================================");
            System.out.println("1. Them Ky thuat vien");
            System.out.println("2. Xem danh sach Ky thuat vien (Sap xep theo ten)");
            System.out.println("3. Xem danh sach Ky thuat vien (Sap xep theo luong tang dan)");
            System.out.println("4. Xem danh sach Ky thuat vien (Sap xep theo luong giam dan)");
            System.out.println("5. Cap nhat thong tin Ky thuat vien");
            System.out.println("6. Xoa Ky thuat vien");
            System.out.println("0. Quay lai Menu chinh");
            System.out.println("===============================================================");
            System.out.print("Nhap lua chon (0-6): ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Ban muon them bao nhieu Ky thuat vien?");
                        System.out.print("=> So luong: ");
                        int countMechanic = Integer.parseInt(sc.nextLine());
                        for (int i = 0; i < countMechanic; i++) {
                            System.out.println("--- Nhap thong tin Ky thuat vien thu " + (i + 1) + " ---");
                            Mechanic newMechanic = new Mechanic();
                            newMechanic.nhapInfo(sc);
                            try {
                                mechanicController.themMoi(newMechanic);
                                System.out
                                        .println("Them ky thuat vien thanh cong! ID duoc cap: " + newMechanic.getId());
                            } catch (DuplicateMaException e) {
                                System.out.println("Loi trung ma: " + e.getMessage());
                                System.out.println("Vui long nhap lai thong tin cho ky thuat vien nay!");
                                i--;
                            } catch (Exception e) {
                                System.out.println("Loi: " + e.getMessage());
                                System.out.println("Vui long nhap lai thong tin cho ky thuat vien nay!");
                                i--;
                            }
                        }
                        break;
                    case 2:
                        List<Mechanic> mechanicsByName = mechanicController.sortByName();
                        System.out.println("Danh sach ky thuat vien (Xep theo ten):");
                        com.mycompany.quanlygara.util.TableFormatter.printMechanics(mechanicsByName);
                        break;
                    case 3:
                        List<Mechanic> mechanicsBySalaryAsc = mechanicController.sortBySalary(true);
                        System.out.println("Danh sach ky thuat vien (Xep theo luong Tang dan):");
                        com.mycompany.quanlygara.util.TableFormatter.printMechanics(mechanicsBySalaryAsc);
                        break;
                    case 4:
                        List<Mechanic> mechanicsBySalaryDesc = mechanicController.sortBySalary(false);
                        System.out.println("Danh sach ky thuat vien (Xep theo luong Giam dan):");
                        com.mycompany.quanlygara.util.TableFormatter.printMechanics(mechanicsBySalaryDesc);
                        break;
                    case 5:
                        System.out.print("Nhap ID Ky thuat vien can cap nhat: ");
                        int editMechId = Integer.parseInt(sc.nextLine());
                        Mechanic editMech = mechanicController.layTheoId(editMechId);
                        if (editMech == null) {
                            System.out.println("Khong tim thay ky thuat vien!");
                        } else {
                            System.out.println("Thong tin hien tai: " + editMech);
                            editMech.nhapInfo(sc);
                            mechanicController.capNhat(editMech);
                            System.out.println("Cap nhat thong tin ky thuat vien thanh cong!");
                        }
                        break;
                    case 6:
                        System.out.print("Nhap ID Ky thuat vien can xoa: ");
                        int deleteMechId = Integer.parseInt(sc.nextLine());
                        Mechanic delMech = mechanicController.layTheoId(deleteMechId);
                        if (delMech == null) {
                            System.out.println("Ky thuat vien khong ton tai!");
                        } else {
                            mechanicController.xoa(deleteMechId);
                            System.out.println("Da xoa ky thuat vien thanh cong.");
                        }
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Lua chon khong hop le!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Loi: Vui long nhap so nguyen hop le!");
            }
            System.out.println();
        } while (choice != 0);
    }

    // --- 3. QUAN LY KHO LINH KIEN ---
    private void menuPart() throws Exception {
        int choice = -1;
        do {
            System.out.println("===============================================================");
            System.out.println("                  MENU QUAN LY KHO LINH KIEN                   ");
            System.out.println("===============================================================");
            System.out.println("1. Them Linh kien moi");
            System.out.println("2. Xem danh sach linh kien (Sap xep theo gia ban tang dan)");
            System.out.println("3. Xem danh sach linh kien (Sap xep theo gia ban giam dan)");
            System.out.println("4. Xem danh sach linh kien (Sap xep theo ton kho tang dan)");
            System.out.println("5. Tim kiem linh kien theo Ten");
            System.out.println("6. Cap nhat Linh kien");
            System.out.println("7. Xoa Linh kien");
            System.out.println("8. Tim kiem linh kien theo khoang gia");
            System.out.println("0. Quay lai Menu chinh");
            System.out.println("===============================================================");
            System.out.print("Nhap lua chon (0-8): ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Ban muon them bao nhieu Linh kien?");
                        System.out.print("=> So luong: ");
                        int countPart = Integer.parseInt(sc.nextLine());
                        for (int i = 0; i < countPart; i++) {
                            System.out.println("--- Nhap thong tin Linh kien thu " + (i + 1) + " ---");
                            LinhKien newPart = new LinhKien();
                            newPart.nhapInfo(sc);
                            try {
                                partController.themMoi(newPart);
                                System.out
                                        .println("Them linh kien vao kho thanh cong! Ma duoc cap: " + newPart.getMa());
                            } catch (Exception e) {
                                System.out.println("Loi: " + e.getMessage());
                                System.out.println("Vui long nhap lai thong tin linh kien nay!");
                                i--;
                            }
                        }
                        break;
                    case 2:
                        List<LinhKien> partsPriceAsc = partController.sortByPrice(true);
                        System.out.println("Kho linh kien (Sap xep gia Tang dan):");
                        com.mycompany.quanlygara.util.TableFormatter.printParts(partsPriceAsc);
                        break;
                    case 3:
                        List<LinhKien> partsPriceDesc = partController.sortByPrice(false);
                        System.out.println("Kho linh kien (Sap xep gia Giam dan):");
                        com.mycompany.quanlygara.util.TableFormatter.printParts(partsPriceDesc);
                        break;
                    case 4:
                        List<LinhKien> partsQtyAsc = partController.sortByQuantity(true);
                        System.out.println("Kho linh kien (Sap xep ton kho Tang dan):");
                        com.mycompany.quanlygara.util.TableFormatter.printParts(partsQtyAsc);
                        break;
                    case 5:
                        System.out.print("Nhap ten linh kien can tim kiem: ");
                        String partNameKeyword = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
                        List<LinhKien> partsByName = partController.searchByName(partNameKeyword);
                        if (partsByName.isEmpty()) {
                            System.out.println("Khong tim thay linh kien nao.");
                        } else {
                            com.mycompany.quanlygara.util.TableFormatter.printParts(partsByName);
                        }
                        break;
                    case 6:
                        System.out.print("Nhap Ma Linh kien can cap nhat: ");
                        String editPartId = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
                        LinhKien editPart = partController.layTheoId(editPartId);
                        if (editPart == null) {
                            System.out.println("Khong tim thay linh kien!");
                        } else {
                            System.out.println("Thong tin hien tai: " + editPart);
                            editPart.nhapInfo(sc);
                            partController.capNhat(editPart);
                            System.out.println("Cap nhat thong tin linh kien thanh cong!");
                        }
                        break;
                    case 7:
                        System.out.print("Nhap Ma Linh kien can xoa: ");
                        String deletePartId = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
                        LinhKien delPart = partController.layTheoId(deletePartId);
                        if (delPart == null) {
                            System.out.println("Linh kien khong ton tai!");
                        } else {
                            partController.xoa(deletePartId);
                            System.out.println("Da xoa linh kien thanh cong.");
                        }
                        break;
                    case 8:
                        System.out.print("Nhap gia thap nhat: ");
                        double minPrice = Double.parseDouble(sc.nextLine());
                        System.out.print("Nhap gia cao nhat: ");
                        double maxPrice = Double.parseDouble(sc.nextLine());
                        List<LinhKien> partsByPrice = partController.searchByPriceRange(minPrice, maxPrice);
                        if (partsByPrice.isEmpty()) {
                            System.out.println("Khong tim thay linh kien nao trong khoang gia nay.");
                        } else {
                            com.mycompany.quanlygara.util.TableFormatter.printParts(partsByPrice);
                        }
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Lua chon khong hop le!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Loi: Vui long nhap so nguyen hop le!");
            }
            System.out.println();
        } while (choice != 0);
    }

    // --- 4. QUAN LY PHIEU SUA CHUA ---
    private void menuRepairOrder() throws Exception {
        int choice = -1;
        do {
            System.out.println("===============================================================");
            System.out.println("                      MENU PHIEU SUA CHUA                      ");
            System.out.println("===============================================================");
            System.out.println("1. Lap Phieu sua chua moi (Tiep nhan xe)");
            System.out.println("2. Xem danh sach tat ca Phieu sua chua");
            System.out.println("3. Them Linh kien / Dich vu sua chua (Cho xe)");
            System.out.println("4. Xem chi tiet cong viec cua Phieu sua chua");
            System.out.println("5. Cap nhat trang thai Phieu (TIEP NHAN -> DANG SUA -> HOAN THANH)");
            System.out.println("0. Quay lai Menu chinh");
            System.out.println("===============================================================");
            System.out.print("Nhap lua chon (0-5): ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("--- Danh sach Xe hien co ---");
                        List<Vehicle> availableVehicles = vehicleController.layTatCa();
                        com.mycompany.quanlygara.util.TableFormatter.printVehicles(availableVehicles);
                        System.out.println("--- Danh sach Ky thuat vien dang ranh ---");
                        List<Mechanic> availableMechs = mechanicController.layTatCa().stream().filter(m -> "Đang rảnh".equalsIgnoreCase(m.getStatus())).collect(java.util.stream.Collectors.toList());
                        com.mycompany.quanlygara.util.TableFormatter.printMechanics(availableMechs);
                        System.out.println("--------------------------------");
                        RepairOrder order = new RepairOrder();
                        order.nhapInfo(sc);
                        String lp = order.getVehicle() != null ? order.getVehicle().getLicensePlate() : "";
                        Vehicle v = vehicleController.layTheoId(lp);
                        if (v == null) {
                            System.out.println("Loi: Xe co bien so '" + lp
                                    + "' chua duoc dang ky tiep nhan! Vui long them xe truoc.");
                            break;
                        }
                        int mId = order.getMechanic() != null ? order.getMechanic().getId() : 0;
                        Mechanic m = mechanicController.layTheoId(mId);
                        if (m == null) {
                            System.out.println("Loi: Khong tim thay ky thuat vien co ID = " + mId);
                            break;
                        }
                        repairOrderController.themMoi(order);
                        System.out.println("Lap phieu sua chua thanh cong! ID Phieu: " + order.getOrderId());
                        break;
                    case 2:
                        List<RepairOrder> orders = repairOrderController.layTatCa();
                        System.out.println("Danh sach phieu sua chua:");
                        com.mycompany.quanlygara.util.TableFormatter.printRepairOrders(orders);
                        break;
                    case 3:
                        System.out.print("Nhap ID Phieu sua chua can them linh kien/dich vu: ");
                        int orderId = Integer.parseInt(sc.nextLine());
                        RepairOrder oObj = repairOrderController.layTheoId(orderId);
                        if (oObj == null) {
                            System.out.println("Phieu sua chua khong ton tai!");
                            break;
                        }
                        if (oObj.getStatus().equals("COMPLETED")) {
                            System.out.println("Loi: Phieu sua chua nay da hoan thanh va chot don, khong the sua doi!");
                            break;
                        }

                        System.out.println("Chon loai hang muc can them:");
                        System.out.println("1. Linh kien (Phu tung)");
                        System.out.println("2. Dich vu (Cong tho)");
                        System.out.print("Nhap lua chon (1-2): ");
                        int typeChoice = Integer.parseInt(sc.nextLine());

                        RepairOrderDetail detail = new RepairOrderDetail();
                        detail.setOrderId(orderId);

                        if (typeChoice == 1) {
                            System.out.println("--- Danh sach Linh kien ---");
                            com.mycompany.quanlygara.util.TableFormatter.printParts(partController.layTatCa());
                            System.out.print("Nhap Ma Linh kien: ");
                            String maLK = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
                            LinhKien lk = partController.layTheoId(maLK);
                            if (lk == null) {
                                System.out.println("Loi: Khong tim thay linh kien voi ma: " + maLK);
                                break;
                            }
                            System.out.print("Nhap so luong su dung: ");
                            int qty = Integer.parseInt(sc.nextLine());
                            if (qty <= 0) {
                                System.out.println("Loi: So luong phai > 0!");
                                break;
                            }
                            detail.setMaHangMuc(maLK);
                            detail.setLoaiHangMuc("LINHKIEN");
                            detail.setDonGiaThucTe(lk.getDonGia());
                            detail.setSoLuong(qty);
                        } else if (typeChoice == 2) {
                            System.out.println("--- Danh sach Dich vu ---");
                            com.mycompany.quanlygara.util.TableFormatter.printServices(dichVuDAO.layTatCa());
                            System.out.print("Nhap Ma Dich vu: ");
                            String maDV = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
                            DichVu dv = dichVuDAO.layTheoId(maDV);
                            if (dv == null) {
                                System.out.println("Loi: Khong tim thay dich vu voi ma: " + maDV);
                                break;
                            }
                            detail.setMaHangMuc(maDV);
                            detail.setLoaiHangMuc("DICHVU");
                            detail.setDonGiaThucTe(dv.getDonGia());
                            detail.setSoLuong(1); // quantity is always 1 for service
                        } else {
                            System.out.println("Lua chon loai khong hop le!");
                            break;
                        }

                        // Add detail using transaction method
                        try {
                            repairOrderController.themMoiChiTietVaTruKho(detail);
                            System.out.println("Da them vao phieu sua chua!");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            break;
                        }

                        // Automatically update status to REPAIRING if it was RECEIVING
                        if (oObj.getStatus().equals("RECEIVING")) {
                            oObj.setStatus("REPAIRING");
                            repairOrderController.capNhat(oObj);
                            System.out.println("Trang thai phieu tu dong chuyen sang: DANG SUA.");
                        }
                        break;
                    case 4:
                        System.out.print("Nhap ID Phieu sua chua can xem chi tiet: ");
                        int showOrderId = Integer.parseInt(sc.nextLine());
                        RepairOrder ro = repairOrderController.layTheoId(showOrderId);
                        if (ro == null) {
                            System.out.println("Phieu sua chua khong ton tai!");
                        } else {
                            System.out.println("=".repeat(81));
                            System.out.printf("| %-77s |\n", "                       === THONG TIN PHIEU SUA CHUA ===");
                            System.out.println("=".repeat(81));
                            System.out.printf("| ID Phieu: %-15d | Bien so xe: %-38s |\n", 
                                ro.getOrderId(), 
                                ro.getVehicle() != null ? ro.getVehicle().getLicensePlate() : "N/A");
                            System.out.printf("| Ngay vao: %-15s | Ngay ra: %-41s |\n", 
                                ro.getEntryDate() != null ? new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(ro.getEntryDate()) : "N/A", 
                                ro.getExitDate() != null ? new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(ro.getExitDate()) : "N/A");
                            System.out.printf("| Tho may:  %-15s | Trang thai: %-38s |\n", 
                                ro.getMechanic() != null ? ro.getMechanic().getName() : "N/A", 
                                ro.getFriendlyStatus());
                            System.out.printf("| Ngoai quan luc nhan: %-52s |\n", 
                                ro.getVisualCondition() != null && !ro.getVisualCondition().isEmpty() ? ro.getVisualCondition() : "N/A");
                            System.out.println("=".repeat(81));
                            System.out.printf("| %-77s |\n", "               --- CHI TIET LINH KIEN & CONG VIEC (DA HINH) ---");
                            System.out.println("-".repeat(81));

                            List<HangMuc> listChiTiet = repairOrderController.getDanhSachChiTiet(showOrderId);
                            List<RepairOrderDetail> rawDetails = repairOrderController.getDetailsByOrderId(showOrderId);

                            if (listChiTiet.isEmpty()) {
                                System.out.printf("| %-77s |\n", "Chua co linh kien hay cong viec nao duoc ghi nhan.");
                                System.out.println("-".repeat(81));
                            } else {
                                String fmt = "| %-9s | %-9s | %-26s | %-4s | %-17s |";
                                System.out.printf(fmt + "\n", "Loai", "Ma HM", "Ten hang muc", "SL", "Thanh tien");
                                System.out.println("-".repeat(81));
                                
                                double totalParts = 0;
                                double totalLabor = 0;

                                for (int i = 0; i < listChiTiet.size(); i++) {
                                    HangMuc hm = listChiTiet.get(i);
                                    int qty = rawDetails.get(i).getSoLuong();
                                    double thanhTien = hm.tinhThanhTien(qty); // polymorphic call

                                    String typeStr = (hm instanceof LinhKien) ? "Linh kien" : "Dich vu";
                                    String nameStr = hm.getTen() != null ? (hm.getTen().length() > 26 ? hm.getTen().substring(0, 23) + "..." : hm.getTen()) : "";
                                    String qtyStr = String.valueOf(qty);
                                    String priceStr = String.format("%,.0f", thanhTien) + " VND";
                                    
                                    System.out.printf(fmt + "\n", typeStr, hm.getMa(), nameStr, qtyStr, priceStr);

                                    if (hm instanceof LinhKien) {
                                        totalParts += thanhTien;
                                    } else {
                                        totalLabor += thanhTien;
                                    }
                                }
                                System.out.println("-".repeat(81));
                                System.out.printf("| Tong tien linh kien: %-56s |\n", String.format("%,.0f", totalParts) + " VND");
                                System.out.printf("| Tong tien cong:      %-56s |\n", String.format("%,.0f", totalLabor) + " VND");
                                System.out.printf("| Tong thanh toan:     %-56s |\n", String.format("%,.0f", totalParts + totalLabor) + " VND");
                                System.out.println("=".repeat(81));
                            }
                        }
                        break;
                    case 5:
                        System.out.print("Nhap ID Phieu sua chua can cap nhat trang thai: ");
                        int editStatusId = Integer.parseInt(sc.nextLine());
                        RepairOrder orderStatus = repairOrderController.layTheoId(editStatusId);
                        if (orderStatus == null) {
                            System.out.println("Phieu sua chua khong ton tai!");
                            break;
                        }
                        System.out.println("Trang thai hien tai: " + orderStatus.getFriendlyStatus());
                        System.out.print("Nhap trang thai moi (1 - TIEP NHAN, 2 - DANG SUA, 3 - HOAN THANH): ");
                        int stChoice = Integer.parseInt(sc.nextLine());
                        String newStatus = "";
                        if (stChoice == 1)
                            newStatus = "RECEIVING";
                        else if (stChoice == 2)
                            newStatus = "REPAIRING";
                        else if (stChoice == 3)
                            newStatus = "COMPLETED";
                        else {
                            System.out.println("Lua chon khong hop le!");
                            break;
                        }

                        orderStatus.setStatus(newStatus);
                        if (newStatus.equals("COMPLETED")) {
                            Date now = new Date();
                            if (orderStatus.getEntryDate() != null && now.before(orderStatus.getEntryDate())) {
                                System.out.println(
                                        "Loi logic: Ngay ra xuong (thoi gian hien tai) khong the nho hon Ngay vao xuong!");
                                System.out.println(
                                        "Vui long kiem tra lai thoi gian he thong hoac du lieu phieu sua chua.");
                                break;
                            }
                            orderStatus.setExitDate(now);
                        } else {
                            orderStatus.setExitDate(null);
                        }

                        repairOrderController.capNhat(orderStatus);
                        System.out.println("Cap nhat trang thai phieu sua chua thanh cong!");
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Lua chon khong hop le!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Loi: Vui long nhap so nguyen hop le!");
            }
            System.out.println();
        } while (choice != 0);
    }

    // --- 5. THANH TOAN & HOA DON ---
    private void menuInvoice() throws Exception {
        int choice = -1;
        do {
            System.out.println("===============================================================");
            System.out.println("               MENU QUAN LY THANH TOAN & HOA DON               ");
            System.out.println("===============================================================");
            System.out.println("1. Thanh toan va Xuat hoa don cho xe");
            System.out.println("2. Xem danh sach tat ca hoa don da thanh toan");
            System.out.println("0. Quay lai Menu chinh");
            System.out.println("===============================================================");
            System.out.print("Nhap lua chon (0-2): ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("Nhap ID Phieu sua chua can thanh toan: ");
                        int orderId = Integer.parseInt(sc.nextLine());
                        RepairOrder ro = repairOrderController.layTheoId(orderId);
                        if (ro == null) {
                            System.out.println("Phieu sua chua khong ton tai!");
                            break;
                        }

                        if (!ro.getStatus().equals("COMPLETED")) {
                            System.out.println(
                                    "Loi: Phieu sua chua phai co trang thai 'HOAN THANH' moi co the thanh toan!");
                            System.out.println("Vui long vao muc 4, cap nhat trang thai phieu sang HOAN THANH truoc.");
                            break;
                        }

                        Invoice existingInv = invoiceController.getByOrderId(orderId);
                        if (existingInv != null) {
                            System.out.println("Phieu sua chua nay da duoc thanh toan truoc do!");
                            System.out.println("Chi tiet hoa don: " + existingInv);
                            break;
                        }

                        Invoice generated = invoiceController.generateInvoice(orderId);
                        invoiceController.themMoi(generated);

                        System.out.println("====================================================");
                        System.out.println("          HOA DON THANH TOAN GARA O TO              ");
                        System.out.println("====================================================");
                        System.out.println("Ma hoa don: " + generated.getInvoiceId());
                        System.out.println("Ma phieu sua chua: "
                                + (generated.getRepairOrder() != null ? generated.getRepairOrder().getOrderId()
                                        : "N/A"));
                        System.out.println("Ngay thanh toan: " + generated.getPaymentDate());
                        System.out.println(
                                "Bien so xe: " + (ro.getVehicle() != null ? ro.getVehicle().getLicensePlate() : "N/A"));
                        System.out.println("Tong tien linh kien: "
                                + String.format("%,.0f", generated.getTotalPartCost()) + " VND");
                        System.out.println(
                                "Tong tien cong: " + String.format("%,.0f", generated.getTotalLaborCost()) + " VND");
                        System.out.println("Thue VAT (10%): "
                                + String.format("%,.0f",
                                        (generated.getTotalPartCost() + generated.getTotalLaborCost()) * 0.10)
                                + " VND");
                        System.out.println("----------------------------------------------------");
                        System.out.println("TONG TIEN THANH TOAN (Gom VAT): "
                                + String.format("%,.0f", generated.getTotalAmount()) + " VND");
                        System.out.println("====================================================");
                        break;
                    case 2:
                        List<Invoice> invoices = invoiceController.layTatCa();
                        System.out.println("Danh sach hoa don da thanh toan:");
                        com.mycompany.quanlygara.util.TableFormatter.printInvoices(invoices);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Lua chon khong hop le!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Loi: Vui long nhap so nguyen hop le!");
            }
            System.out.println();
        } while (choice != 0);
    }

    // --- 6. BAO CAO THONG KE ---
    private void menuReport() throws Exception {
        int choice = -1;
        do {
            System.out.println("===============================================================");
            System.out.println("                     MENU BAO CAO THONG KE                     ");
            System.out.println("===============================================================");
            System.out.println("1. Thong ke doanh thu theo khoang thoi gian");
            System.out.println("2. Thong ke Top linh kien ban nhieu nhat");
            System.out.println("3. Thong ke Top xe sua chua nhieu nhat");
            System.out.println("4. Thong ke Top ky thuat vien lam viec nhieu nhat");
            System.out.println("0. Quay lai Menu chinh");
            System.out.println("===============================================================");
            System.out.print("Nhap lua chon (0-4): ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("Nhap tu ngay (yyyy-MM-dd, hoac Enter de lay het): ");
                        String from = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
                        System.out.print("Nhap den ngay (yyyy-MM-dd, hoac Enter de lay het): ");
                        String to = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
                        double revenue = reportController.getRevenue(from, to);
                        System.out.println("----------------------------------------------");
                        System.out.println("TONG DOANH THU THU DUOC: " + String.format("%,.0f", revenue) + " VND");
                        System.out.println("----------------------------------------------");
                        break;
                    case 2:
                        System.out.print("Nhap so luong linh kien can lay (limit): ");
                        int limitParts = Integer.parseInt(sc.nextLine());
                        Map<String, Integer> partsReport = reportController.getMostUsedParts(limitParts);
                        System.out.println("=== TOP LINH KIEN SU DUNG NHIEU NHAT ===");
                        int idx1 = 1;
                        for (Map.Entry<String, Integer> entry : partsReport.entrySet()) {
                            System.out.println(idx1++ + ". " + entry.getKey() + " | So luong: " + entry.getValue());
                        }
                        break;
                    case 3:
                        System.out.print("Nhap so luong xe can lay (limit): ");
                        int limitVehicles = Integer.parseInt(sc.nextLine());
                        Map<String, Integer> vehiclesReport = reportController.getMostRepairedVehicles(limitVehicles);
                        System.out.println("=== TOP XE DUOC SUA CHUA NHIEU NHAT ===");
                        int idx2 = 1;
                        for (Map.Entry<String, Integer> entry : vehiclesReport.entrySet()) {
                            System.out.println(idx2++ + ". " + entry.getKey() + " | So lan sua: " + entry.getValue());
                        }
                        break;
                    case 4:
                        System.out.print("Nhap so luong ky thuat vien can lay (limit): ");
                        int limitMechs = Integer.parseInt(sc.nextLine());
                        Map<String, Integer> mechsReport = reportController.getMostActiveMechanics(limitMechs);
                        System.out.println("=== TOP KY THUAT VIEN DUOC GIAO NHIEU VIEC ===");
                        int idx3 = 1;
                        for (Map.Entry<String, Integer> entry : mechsReport.entrySet()) {
                            System.out.println(
                                    idx3++ + ". " + entry.getKey() + " | So phieu dam nhan: " + entry.getValue());
                        }
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Lua chon khong hop le!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Loi: Vui long nhap so nguyen hop le!");
            }
            System.out.println();
        } while (choice != 0);
    }

    // --- 7. QUAN LY DANH MUC DICH VU ---
    private void menuDichVu() throws Exception {
        int choice = -1;
        do {
            System.out.println("===============================================================");
            System.out.println("                 MENU QUAN LY DANH MUC DICH VU                 ");
            System.out.println("===============================================================");
            System.out.println("1. Them Dich vu moi");
            System.out.println("2. Xem danh sach Dich vu");
            System.out.println("3. Tim kiem dich vu theo Ten");
            System.out.println("4. Cap nhat thong tin Dich vu");
            System.out.println("5. Xoa Dich vu");
            System.out.println("0. Quay lai Menu chinh");
            System.out.println("===============================================================");
            System.out.print("Nhap lua chon (0-5): ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Ban muon them bao nhieu Dich vu?");
                        System.out.print("=> So luong: ");
                        int countDichVu = Integer.parseInt(sc.nextLine());
                        for (int i = 0; i < countDichVu; i++) {
                            System.out.println("--- Nhap thong tin Dich vu thu " + (i + 1) + " ---");
                            DichVu newDV = new DichVu();
                            newDV.nhapInfo(sc);
                            try {
                                dichVuDAO.themMoi(newDV);
                                System.out.println("Them dich vu moi thanh cong! Ma duoc cap: " + newDV.getMa());
                            } catch (Exception e) {
                                System.out.println("Loi: " + e.getMessage());
                                System.out.println("Vui long nhap lai thong tin dich vu nay!");
                                i--;
                            }
                        }
                        break;
                    case 2:
                        List<DichVu> list = dichVuDAO.layTatCa();
                        System.out.println("Danh sach dich vu cua gara:");
                        com.mycompany.quanlygara.util.TableFormatter.printServices(list);
                        break;
                    case 3:
                        System.out.print("Nhap ten dich vu can tim: ");
                        String kw = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
                        List<DichVu> searchResult = dichVuDAO.searchByName(kw);
                        if (searchResult.isEmpty()) {
                            System.out.println("Khong tim thay dich vu nao.");
                        } else {
                            com.mycompany.quanlygara.util.TableFormatter.printServices(searchResult);
                        }
                        break;
                    case 4:
                        System.out.print("Nhap Ma Dich vu can cap nhat: ");
                        String ma = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
                        DichVu editDV = dichVuDAO.layTheoId(ma);
                        if (editDV == null) {
                            System.out.println("Khong tim thay dich vu!");
                        } else {
                            System.out.println("Thong tin hien tai: " + editDV);
                            editDV.nhapInfo(sc);
                            dichVuDAO.capNhat(editDV);
                            System.out.println("Cap nhat thong tin dich vu thanh cong!");
                        }
                        break;
                    case 5:
                        System.out.print("Nhap Ma Dich vu can xoa: ");
                        String deleteMa = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
                        DichVu delDV = dichVuDAO.layTheoId(deleteMa);
                        if (delDV == null) {
                            System.out.println("Dich vu khong ton tai!");
                        } else {
                            dichVuDAO.xoa(deleteMa);
                            System.out.println("Da xoa dich vu thanh cong.");
                        }
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Lua chon khong hop le!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Loi: Vui long nhap so nguyen hop le!");
            }
            System.out.println();
        } while (choice != 0);
    }
}
