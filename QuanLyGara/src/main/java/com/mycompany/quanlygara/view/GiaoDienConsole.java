
package com.mycompany.quanlygara.view;

import com.mycompany.quanlygara.controller.*;
import com.mycompany.quanlygara.model.*;
import java.util.*;


public class GiaoDienConsole {
    
    private final Scanner sc;
    
    private final ChuXeDAO ownerController;
    
    private final XeDAO vehicleController;
    
    private final KyThuatVienDAO mechanicController;
    
    private final LinhKienDAO partController;
    
    
    private final PhieuSuaChuaDAO repairOrderController;
    
    private final HoaDonDAO invoiceController;
    
    private final BaoCaoController reportController;
    
    private final DichVuDAO dichVuDAO;
    
    private final NhanVienDAO employeeDAO;
    
    private NhanVien currentUser;

    
    public GiaoDienConsole() {
        this.sc = new Scanner(System.in);
        this.ownerController = new ChuXeDAO();
        this.vehicleController = new XeDAO();
        this.mechanicController = new KyThuatVienDAO();
        this.partController = new LinhKienDAO();
        this.repairOrderController = new PhieuSuaChuaDAO();
        this.invoiceController = new HoaDonDAO();
        this.reportController = new BaoCaoController();
        this.dichVuDAO = new DichVuDAO();
        this.employeeDAO = new NhanVienDAO();
        this.currentUser = null;
    }

    
    public static void main(String[] args) {
        try {
            System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        } catch (Exception ignored) {
        }
        System.setIn(new com.mycompany.quanlygara.util.LuongNhapKhongDau(System.in));
        GiaoDienConsole view = new GiaoDienConsole();
        view.start();
    }

    
    public void start() {
        System.out.println("===============================================================");
        System.out.println("              CHAO MUNG DEN HE THONG QUAN LY GARA              ");
        System.out.println("===============================================================");

        while (true) {
            while (currentUser == null) {
                System.out.println("\n--- VUI LONG DANG NHAP ---");
                System.out.println("Danh sach tai khoan (Username - Chuc vu):");
                List<NhanVien> accounts = employeeDAO.getAllAccounts();
                if (accounts.isEmpty()) {
                    System.out.println(" (Khong co tai khoan nao, vui long kiem tra CSDL)");
                } else {
                    for (NhanVien emp : accounts) {
                        System.out.println(" - " + emp.getUsername() + " (" + emp.getRole() + ")");
                    }
                }
                System.out.println("--------------------------");
                System.out.print("Username (nhap 'exit' de thoat): ");
                String username = com.mycompany.quanlygara.util.TienIchChuoi.removeAccents(sc.nextLine().trim());
                if (username.equalsIgnoreCase("exit")) {
                    System.out.println("Cam on ban da su dung chuong trinh!");
                    return;
                }
                System.out.print("Password: ");
                String password = sc.nextLine().trim();

                if (com.mycompany.quanlygara.util.TienIchChuoi.hasAccents(password)) {
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
        if (com.mycompany.quanlygara.util.TienIchChuoi.hasAccents(newPwd)) {
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
            System.out.println("8. Xoa Xe (Dua vao Thung rac)");
            System.out.println("9. Xoa Chu xe (Dua vao Thung rac)");
            System.out.println("10. Quan ly Thung rac & Khoi phuc (Chi danh cho Admin)");
            System.out.println("0. Quay lai Menu chinh");
            System.out.println("===============================================================");
            System.out.print("Nhap lua chon (0-10): ");
            try {
                choice = Integer.parseInt(sc.nextLine());

                if (currentUser != null && currentUser.getRole().equals("KyThuat")
                        && (choice == 7 || choice == 8 || choice == 9 || choice == 10)) {
                    System.out.println("LOI: Ban la Ky thuat vien, khong co quyen truy cap chuc nang nay!");
                    continue;
                }

                switch (choice) {
                    case 1:
                        System.out.println("Ban muon them bao nhieu Chu xe?");
                        int countCustomer = 0;
                        while (true) {
                            try {
                                System.out.print("=> So luong: ");
                                countCustomer = Integer.parseInt(sc.nextLine());
                                if (countCustomer <= 0) {
                                    System.out.println("So luong phai lon hon 0!");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Vui long nhap so nguyen hop le!");
                            }
                        }
                        for (int i = 0; i < countCustomer; i++) {
                            System.out.println("--- Nhap thong tin Chu xe thu " + (i + 1) + " ---");
                            ChuXe newOwner = new ChuXe();
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
                        int countVeh = 0;
                        while (true) {
                            try {
                                System.out.print("=> So luong: ");
                                countVeh = Integer.parseInt(sc.nextLine());
                                if (countVeh <= 0) {
                                    System.out.println("So luong phai lon hon 0!");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Vui long nhap so nguyen hop le!");
                            }
                        }
                        for (int i = 0; i < countVeh; i++) {
                            System.out.println("--- Nhap thong tin Xe thu " + (i + 1) + " ---");
                            Xe newVehicle = new Xe();
                            newVehicle.nhapInfo(sc);
                            Xe existing = vehicleController.layTheoId(newVehicle.getLicensePlate());
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
                        List<ChuXe> owners = ownerController.layTatCa();
                        System.out.println("Danh sach chu xe:");
                        com.mycompany.quanlygara.util.DinhDangBang.printCustomers(owners);
                        break;
                    case 4:
                        List<Xe> vehicles = vehicleController.layTatCa();
                        System.out.println("Danh sach xe trong gara:");
                        com.mycompany.quanlygara.util.DinhDangBang.printVehicles(vehicles);
                        break;
                    case 5:
                        System.out.print("Nhap bien so xe can tim: ");
                        String lp = com.mycompany.quanlygara.util.TienIchChuoi.removeAccents(sc.nextLine().trim());
                        List<Xe> searchLp = vehicleController.searchByLicensePlate(lp);
                        if (searchLp.isEmpty()) {
                            System.out.println("Khong tim thay xe nao.");
                        } else {
                            com.mycompany.quanlygara.util.DinhDangBang.printVehicles(searchLp);
                        }
                        break;
                    case 6:
                        System.out.print("Nhap ten chu xe de tim xe: ");
                        String ownerName = com.mycompany.quanlygara.util.TienIchChuoi
                                .removeAccents(sc.nextLine().trim());
                        List<Xe> searchName = vehicleController.searchByOwnerName(ownerName);
                        if (searchName.isEmpty()) {
                            System.out.println("Khong tim thay xe nao.");
                        } else {
                            com.mycompany.quanlygara.util.DinhDangBang.printVehicles(searchName);
                        }
                        break;
                    case 7:
                        System.out.print("Nhap ID Chu xe can cap nhat: ");
                        int editOwnerId = Integer.parseInt(sc.nextLine());
                        ChuXe editOwner = ownerController.layTheoId(editOwnerId);
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
                        String deleteLp = com.mycompany.quanlygara.util.TienIchChuoi.removeAccents(sc.nextLine().trim());
                        Xe delVeh = vehicleController.layTheoId(deleteLp);
                        if (delVeh == null) {
                            System.out.println("Xe khong ton tai!");
                        } else {
                            vehicleController.xoa(deleteLp);
                            System.out.println("Da xoa xe thanh cong.");
                        }
                        break;
                    case 9:
                        System.out.println("--- XOA CHU XE ---");
                        System.out.println("--- Danh sach Chu xe hien co ---");
                        List<ChuXe> customers = ownerController.layTatCa();
                        com.mycompany.quanlygara.util.DinhDangBang.printCustomers(customers);
                        System.out.println("--------------------------------");
                        System.out.print("Nhap ID chu xe can xoa: ");
                        int deleteCustId = 0;
                        while (true) {
                            try {
                                deleteCustId = Integer.parseInt(sc.nextLine());
                                if (deleteCustId <= 0) {
                                    System.out.println("ID chu xe phai la so nguyen duong!");
                                    System.out.print("Nhap lai ID chu xe: ");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Vui long nhap so nguyen hop le!");
                                System.out.print("Nhap lai ID chu xe: ");
                            }
                        }
                        try {
                            ownerController.xoa(deleteCustId);
                            System.out.println("Xoa chu xe thanh cong!");
                        } catch (Exception e) {
                            System.out.println("Loi: " + e.getMessage());
                        }
                        break;
                    case 10:
                        menuThungRac();
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

    
    private void menuThungRac() {
        int subChoice = -1;
        do {
            System.out.println("===============================================================");
            System.out.println("                      QUAN LY THUNG RAC                        ");
            System.out.println("===============================================================");
            System.out.println("1. Xem danh sach Chu xe trong Thung rac");
            System.out.println("2. Xem danh sach Xe trong Thung rac");
            System.out.println("3. Khoi phuc Chu xe");
            System.out.println("4. Khoi phuc Xe");
            System.out.println("5. Xoa vinh vien Chu xe");
            System.out.println("6. Xoa vinh vien Xe");
            System.out.println("0. Quay lai menu truoc");
            System.out.println("===============================================================");
            System.out.print("Nhap lua chon (0-6): ");
            try {
                subChoice = Integer.parseInt(sc.nextLine());
                switch (subChoice) {
                    case 1:
                        List<ChuXe> deletedOwners = ownerController.layDanhSachDaXoa();
                        System.out.println("--- Danh sach Chu xe trong Thung rac ---");
                        com.mycompany.quanlygara.util.DinhDangBang.printCustomers(deletedOwners);
                        break;
                    case 2:
                        List<Xe> deletedVehicles = vehicleController.layDanhSachDaXoa();
                        System.out.println("--- Danh sach Xe trong Thung rac ---");
                        com.mycompany.quanlygara.util.DinhDangBang.printVehicles(deletedVehicles);
                        break;
                    case 3:
                        System.out.print("Nhap ID Chu xe can khoi phuc: ");
                        int restoreOwnerId = Integer.parseInt(sc.nextLine());
                        ownerController.khoiPhuc(restoreOwnerId);
                        System.out.println("Da khoi phuc chu xe thanh cong!");
                        break;
                    case 4:
                        System.out.print("Nhap Bien so xe can khoi phuc: ");
                        String restoreLp = com.mycompany.quanlygara.util.TienIchChuoi.removeAccents(sc.nextLine().trim());
                        vehicleController.khoiPhuc(restoreLp);
                        System.out.println("Da khoi phuc xe thanh cong!");
                        break;
                    case 5:
                        System.out.print("Nhap ID Chu xe can xoa vinh vien: ");
                        int deleteOwnerId = Integer.parseInt(sc.nextLine());
                        System.out.print("Canh bao: Hanh dong nay khong the hoan tac! Ban co chac chan? (Y/N): ");
                        String confirmOwner = sc.nextLine().trim();
                        if (confirmOwner.equalsIgnoreCase("Y")) {
                            ownerController.xoaVinhVien(deleteOwnerId);
                            System.out.println("Da xoa vinh vien chu xe thanh cong!");
                        } else {
                            System.out.println("Da huy thao tac.");
                        }
                        break;
                    case 6:
                        System.out.print("Nhap Bien so xe can xoa vinh vien: ");
                        String deleteLp = com.mycompany.quanlygara.util.TienIchChuoi.removeAccents(sc.nextLine().trim());
                        System.out.print("Canh bao: Hanh dong nay khong the hoan tac! Ban co chac chan? (Y/N): ");
                        String confirmVeh = sc.nextLine().trim();
                        if (confirmVeh.equalsIgnoreCase("Y")) {
                            vehicleController.xoaVinhVien(deleteLp);
                            System.out.println("Da xoa vinh vien xe thanh cong!");
                        } else {
                            System.out.println("Da huy thao tac.");
                        }
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Lua chon khong hop le!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Loi: Vui long nhap so nguyen hop le!");
            } catch (Exception e) {
                System.out.println("Loi: " + e.getMessage());
            }
        } while (subChoice != 0);
    }

    
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
                        int countMechanic = 0;
                        while (true) {
                            try {
                                System.out.print("=> So luong: ");
                                countMechanic = Integer.parseInt(sc.nextLine());
                                if (countMechanic <= 0) {
                                    System.out.println("So luong phai lon hon 0!");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Vui long nhap so nguyen hop le!");
                            }
                        }
                        for (int i = 0; i < countMechanic; i++) {
                            System.out.println("--- Nhap thong tin Ky thuat vien thu " + (i + 1) + " ---");
                            KyThuatVien newMechanic = new KyThuatVien();
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
                        List<KyThuatVien> mechanicsByName = mechanicController.sortByName();
                        System.out.println("Danh sach ky thuat vien (Xep theo ten):");
                        com.mycompany.quanlygara.util.DinhDangBang.printMechanics(mechanicsByName);
                        break;
                    case 3:
                        List<KyThuatVien> mechanicsBySalaryAsc = mechanicController.sortBySalary(true);
                        System.out.println("Danh sach ky thuat vien (Xep theo luong Tang dan):");
                        com.mycompany.quanlygara.util.DinhDangBang.printMechanics(mechanicsBySalaryAsc);
                        break;
                    case 4:
                        List<KyThuatVien> mechanicsBySalaryDesc = mechanicController.sortBySalary(false);
                        System.out.println("Danh sach ky thuat vien (Xep theo luong Giam dan):");
                        com.mycompany.quanlygara.util.DinhDangBang.printMechanics(mechanicsBySalaryDesc);
                        break;
                    case 5:
                        System.out.print("Nhap ID Ky thuat vien can cap nhat: ");
                        int editMechId = Integer.parseInt(sc.nextLine());
                        KyThuatVien editMech = mechanicController.layTheoId(editMechId);
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
                        KyThuatVien delMech = mechanicController.layTheoId(deleteMechId);
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
                        int countPart = 0;
                        while (true) {
                            try {
                                System.out.print("=> So luong: ");
                                countPart = Integer.parseInt(sc.nextLine());
                                if (countPart <= 0) {
                                    System.out.println("So luong phai lon hon 0!");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Vui long nhap so nguyen hop le!");
                            }
                        }
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
                        com.mycompany.quanlygara.util.DinhDangBang.printParts(partsPriceAsc);
                        break;
                    case 3:
                        List<LinhKien> partsPriceDesc = partController.sortByPrice(false);
                        System.out.println("Kho linh kien (Sap xep gia Giam dan):");
                        com.mycompany.quanlygara.util.DinhDangBang.printParts(partsPriceDesc);
                        break;
                    case 4:
                        List<LinhKien> partsQtyAsc = partController.sortByQuantity(true);
                        System.out.println("Kho linh kien (Sap xep ton kho Tang dan):");
                        com.mycompany.quanlygara.util.DinhDangBang.printParts(partsQtyAsc);
                        break;
                    case 5:
                        System.out.print("Nhap ten linh kien can tim kiem: ");
                        String partNameKeyword = com.mycompany.quanlygara.util.TienIchChuoi
                                .removeAccents(sc.nextLine().trim());
                        List<LinhKien> partsByName = partController.searchByName(partNameKeyword);
                        if (partsByName.isEmpty()) {
                            System.out.println("Khong tim thay linh kien nao.");
                        } else {
                            com.mycompany.quanlygara.util.DinhDangBang.printParts(partsByName);
                        }
                        break;
                    case 6:
                        System.out.print("Nhap Ma Linh kien can cap nhat: ");
                        String editPartId = com.mycompany.quanlygara.util.TienIchChuoi
                                .removeAccents(sc.nextLine().trim());
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
                        String deletePartId = com.mycompany.quanlygara.util.TienIchChuoi
                                .removeAccents(sc.nextLine().trim());
                        LinhKien delPart = partController.layTheoId(deletePartId);
                        if (delPart == null) {
                            System.out.println("Linh kien khong ton tai!");
                        } else {
                            partController.xoa(deletePartId);
                            System.out.println("Da xoa linh kien thanh cong.");
                        }
                        break;
                    case 8:
                        double minPrice = 0;
                        double maxPrice = 0;
                        while (true) {
                            try {
                                System.out.print("Nhap gia thap nhat: ");
                                minPrice = Double.parseDouble(sc.nextLine());
                                if (minPrice < 0) {
                                    System.out.println("Gia thap nhat phai >= 0!");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Vui long nhap so thuc hop le!");
                            }
                        }
                        while (true) {
                            try {
                                System.out.print("Nhap gia cao nhat: ");
                                maxPrice = Double.parseDouble(sc.nextLine());
                                if (maxPrice < 0) {
                                    System.out.println("Gia cao nhat phai >= 0!");
                                    continue;
                                }
                                if (maxPrice < minPrice) {
                                    System.out.println("Gia cao nhat phai >= gia thap nhat ("
                                            + String.format("%,.0f", minPrice) + " VND)!");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Vui long nhap so thuc hop le!");
                            }
                        }
                        List<LinhKien> partsByPrice = partController.searchByPriceRange(minPrice, maxPrice);
                        if (partsByPrice.isEmpty()) {
                            System.out.println("Khong tim thay linh kien nao trong khoang gia nay.");
                        } else {
                            com.mycompany.quanlygara.util.DinhDangBang.printParts(partsByPrice);
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
            System.out.println("5. Cap nhat trang thai Phieu (RECEIVING -> REPAIRING -> COMPLETED)");
            System.out.println("6. Xoa hang muc (Linh kien/Dich vu) khoi Phieu sua chua");
            System.out.println("0. Quay lai Menu chinh");
            System.out.println("===============================================================");
            System.out.print("Nhap lua chon (0-6): ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("--- Danh sach Xe hien co ---");
                        List<Xe> availableVehicles = vehicleController.layTatCa();
                        com.mycompany.quanlygara.util.DinhDangBang.printVehicles(availableVehicles);
                        System.out.println("--- Danh sach Ky thuat vien dang ranh ---");
                        List<KyThuatVien> availableMechs = mechanicController.layTatCa().stream()
                                .filter(m -> "Dang ranh".equalsIgnoreCase(m.getStatus()))
                                .collect(java.util.stream.Collectors.toList());
                        com.mycompany.quanlygara.util.DinhDangBang.printMechanics(availableMechs);
                        System.out.println("--------------------------------");
                        PhieuSuaChua order = new PhieuSuaChua();
                        order.nhapInfo(sc);
                        String lp = order.getVehicle() != null ? order.getVehicle().getLicensePlate() : "";
                        Xe v = vehicleController.layTheoId(lp);
                        if (v == null) {
                            System.out.println("Loi: Xe co bien so '" + lp
                                    + "' chua duoc dang ky tiep nhan! Vui long them xe truoc.");
                            break;
                        }
                        int mId = order.getMechanic() != null ? order.getMechanic().getId() : 0;
                        KyThuatVien m = mechanicController.layTheoId(mId);
                        if (m == null) {
                            System.out.println("Loi: Khong tim thay ky thuat vien co ID = " + mId);
                            break;
                        }
                        repairOrderController.themMoi(order);
                        System.out.println("Lap phieu sua chua thanh cong! ID Phieu: " + order.getOrderId());
                        break;
                    case 2:
                        List<PhieuSuaChua> orders = repairOrderController.layTatCa();
                        System.out.println("Danh sach phieu sua chua:");
                        com.mycompany.quanlygara.util.DinhDangBang.printRepairOrders(orders);
                        break;
                    case 3:
                        System.out.print("Nhap ID Phieu sua chua can them linh kien/dich vu: ");
                        int orderId = Integer.parseInt(sc.nextLine());
                        PhieuSuaChua oObj = repairOrderController.layTheoId(orderId);
                        if (oObj == null) {
                            System.out.println("Phieu sua chua khong ton tai!");
                            break;
                        }
                        if (oObj.getStatus().equals("COMPLETED")) {
                            System.out.println("Loi: Phieu sua chua nay da hoan thanh va chot don, khong the sua doi!");
                            break;
                        }

                        if (currentUser.getRole().equals("KyThuat") && oObj.getMechanic() != null) {
                            if (currentUser.getId() != oObj.getMechanic().getId()) {
                                System.out.println(
                                        "Loi: Tu choi quyen! Ban khong the thao tac voi Phieu sua chua do tho khac phu trach.");
                                break;
                            }
                        }

                        System.out.println("Chon loai hang muc can them:");
                        System.out.println("1. Linh kien (Phu tung)");
                        System.out.println("2. Dich vu (Cong tho)");
                        System.out.print("Nhap lua chon (1-2): ");
                        int typeChoice = Integer.parseInt(sc.nextLine());

                        ChiTietPhieuSua detail = new ChiTietPhieuSua();
                        detail.setOrderId(orderId);

                        if (typeChoice == 1) {
                            System.out.println("--- Danh sach Linh kien ---");
                            com.mycompany.quanlygara.util.DinhDangBang.printParts(partController.layTatCa());
                            System.out.print("Nhap Ma Linh kien: ");
                            String maLK = com.mycompany.quanlygara.util.TienIchChuoi.removeAccents(sc.nextLine().trim());
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
                            com.mycompany.quanlygara.util.DinhDangBang.printServices(dichVuDAO.layTatCa());
                            System.out.print("Nhap Ma Dich vu: ");
                            String maDV = com.mycompany.quanlygara.util.TienIchChuoi.removeAccents(sc.nextLine().trim());
                            DichVu dv = dichVuDAO.layTheoId(maDV);
                            if (dv == null) {
                                System.out.println("Loi: Khong tim thay dich vu voi ma: " + maDV);
                                break;
                            }
                            detail.setMaHangMuc(maDV);
                            detail.setLoaiHangMuc("DICHVU");
                            detail.setDonGiaThucTe(dv.getDonGia());
                            detail.setSoLuong(1); 
                        } else {
                            System.out.println("Lua chon loai khong hop le!");
                            break;
                        }

                        
                        try {
                            repairOrderController.themMoiChiTietVaTruKho(detail);
                            System.out.println("Da them vao phieu sua chua!");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            break;
                        }

                        
                        if (oObj.getStatus().equals("RECEIVING")) {
                            oObj.setStatus("REPAIRING");
                            repairOrderController.capNhat(oObj);
                            System.out.println("Trang thai phieu tu dong chuyen sang: DANG SUA.");
                        }
                        break;
                    case 4:
                        System.out.print("Nhap ID Phieu sua chua can xem chi tiet: ");
                        int showOrderId = Integer.parseInt(sc.nextLine());
                        PhieuSuaChua ro = repairOrderController.layTheoId(showOrderId);
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
                                    ro.getEntryDate() != null
                                            ? new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                                                    .format(ro.getEntryDate())
                                            : "N/A",
                                    ro.getExitDate() != null
                                            ? new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                                                    .format(ro.getExitDate())
                                            : "N/A");
                            System.out.printf("| Tho may:  %-15s | Trang thai: %-38s |\n",
                                    ro.getMechanic() != null ? ro.getMechanic().getName() : "N/A",
                                    ro.getFriendlyStatus());
                            System.out.printf("| Ngoai quan luc nhan: %-52s |\n",
                                    ro.getVisualCondition() != null && !ro.getVisualCondition().isEmpty()
                                            ? ro.getVisualCondition()
                                            : "N/A");
                            System.out.println("=".repeat(81));
                            System.out.printf("| %-77s |\n",
                                    "               --- CHI TIET LINH KIEN & CONG VIEC (DA HINH) ---");
                            System.out.println("-".repeat(81));

                            List<HangMuc> listChiTiet = repairOrderController.getDanhSachChiTiet(showOrderId);
                            List<ChiTietPhieuSua> rawDetails = repairOrderController.getDetailsByOrderId(showOrderId);

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
                                    double thanhTien = hm.tinhThanhTien(qty); 

                                    String typeStr = (hm instanceof LinhKien) ? "Linh kien" : "Dich vu";
                                    String nameStr = hm.getTen() != null
                                            ? (hm.getTen().length() > 26 ? hm.getTen().substring(0, 23) + "..."
                                                    : hm.getTen())
                                            : "";
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
                                System.out.printf("| Tong tien linh kien: %-56s |\n",
                                        String.format("%,.0f", totalParts) + " VND");
                                System.out.printf("| Tong tien cong:      %-56s |\n",
                                        String.format("%,.0f", totalLabor) + " VND");
                                System.out.printf("| Tong thanh toan:     %-56s |\n",
                                        String.format("%,.0f", totalParts + totalLabor) + " VND");
                                System.out.println("=".repeat(81));
                            }
                        }
                        break;
                    case 5:
                        System.out.print("Nhap ID Phieu sua chua can cap nhat trang thai: ");
                        int editStatusId = Integer.parseInt(sc.nextLine());
                        PhieuSuaChua orderStatus = repairOrderController.layTheoId(editStatusId);
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
                    case 6:
                        System.out.print("Nhap ID Phieu sua chua can xoa hang muc: ");
                        int delOrderId = Integer.parseInt(sc.nextLine());
                        PhieuSuaChua delOrder = repairOrderController.layTheoId(delOrderId);
                        if (delOrder == null) {
                            System.out.println("Phieu sua chua khong ton tai!");
                            break;
                        }
                        if (delOrder.getStatus().equals("COMPLETED")) {
                            System.out.println("Loi: Phieu sua chua nay da hoan thanh, khong the sua doi!");
                            break;
                        }
                        if (currentUser.getRole().equals("KyThuat") && delOrder.getMechanic() != null) {
                            if (currentUser.getId() != delOrder.getMechanic().getId()) {
                                System.out.println(
                                        "Loi: Tu choi quyen! Ban khong the thao tac voi Phieu sua chua do tho khac phu trach.");
                                break;
                            }
                        }
                        System.out.print("Nhap Ma Hang muc (Linh kien / Dich vu) can xoa: ");
                        String delMa = sc.nextLine().trim();
                        try {
                            repairOrderController.xoaChiTiet(delOrderId, delMa);
                            System.out.println("Da xoa hang muc khoi phieu sua chua va hoan lai kho (neu co)!");
                        } catch (Exception e) {
                            System.out.println("Loi: " + e.getMessage());
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
                        PhieuSuaChua ro = repairOrderController.layTheoId(orderId);
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

                        HoaDon existingInv = invoiceController.getByOrderId(orderId);
                        if (existingInv != null) {
                            System.out.println("Phieu sua chua nay da duoc thanh toan truoc do!");
                            System.out.println("Chi tiet hoa don: " + existingInv);
                            break;
                        }

                        HoaDon generated = invoiceController.generateInvoice(orderId);
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
                        List<HoaDon> invoices = invoiceController.layTatCa();
                        System.out.println("Danh sach hoa don da thanh toan:");
                        com.mycompany.quanlygara.util.DinhDangBang.printInvoices(invoices);
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
                        String from = com.mycompany.quanlygara.util.TienIchChuoi.removeAccents(sc.nextLine().trim());
                        System.out.print("Nhap den ngay (yyyy-MM-dd, hoac Enter de lay het): ");
                        String to = com.mycompany.quanlygara.util.TienIchChuoi.removeAccents(sc.nextLine().trim());
                        double revenue = reportController.getRevenue(from, to);
                        System.out.println("----------------------------------------------");
                        System.out.println("TONG DOANH THU THU DUOC: " + String.format("%,.0f", revenue) + " VND");
                        System.out.println("----------------------------------------------");
                        break;
                    case 2:
                        int limitParts = 0;
                        while (true) {
                            try {
                                System.out.print("Nhap so luong linh kien can lay (limit): ");
                                limitParts = Integer.parseInt(sc.nextLine());
                                if (limitParts <= 0) {
                                    System.out.println("Limit phai lon hon 0!");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Vui long nhap so nguyen hop le!");
                            }
                        }
                        Map<String, Integer> partsReport = reportController.getMostUsedParts(limitParts);
                        System.out.println("=== TOP LINH KIEN SU DUNG NHIEU NHAT ===");
                        int idx1 = 1;
                        for (Map.Entry<String, Integer> entry : partsReport.entrySet()) {
                            System.out.println(idx1++ + ". " + entry.getKey() + " | So luong: " + entry.getValue());
                        }
                        break;
                    case 3:
                        int limitVehicles = 0;
                        while (true) {
                            try {
                                System.out.print("Nhap so luong xe can lay (limit): ");
                                limitVehicles = Integer.parseInt(sc.nextLine());
                                if (limitVehicles <= 0) {
                                    System.out.println("Limit phai lon hon 0!");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Vui long nhap so nguyen hop le!");
                            }
                        }
                        Map<String, Integer> vehiclesReport = reportController.getMostRepairedVehicles(limitVehicles);
                        System.out.println("=== TOP XE DUOC SUA CHUA NHIEU NHAT ===");
                        int idx2 = 1;
                        for (Map.Entry<String, Integer> entry : vehiclesReport.entrySet()) {
                            System.out.println(idx2++ + ". " + entry.getKey() + " | So lan sua: " + entry.getValue());
                        }
                        break;
                    case 4:
                        int limitMechs = 0;
                        while (true) {
                            try {
                                System.out.print("Nhap so luong ky thuat vien can lay (limit): ");
                                limitMechs = Integer.parseInt(sc.nextLine());
                                if (limitMechs <= 0) {
                                    System.out.println("Limit phai lon hon 0!");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Vui long nhap so nguyen hop le!");
                            }
                        }
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
                        int countDichVu = 0;
                        while (true) {
                            try {
                                System.out.print("=> So luong: ");
                                countDichVu = Integer.parseInt(sc.nextLine());
                                if (countDichVu <= 0) {
                                    System.out.println("So luong phai lon hon 0!");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Vui long nhap so nguyen hop le!");
                            }
                        }
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
                        com.mycompany.quanlygara.util.DinhDangBang.printServices(list);
                        break;
                    case 3:
                        System.out.print("Nhap ten dich vu can tim: ");
                        String kw = com.mycompany.quanlygara.util.TienIchChuoi.removeAccents(sc.nextLine().trim());
                        List<DichVu> searchResult = dichVuDAO.searchByName(kw);
                        if (searchResult.isEmpty()) {
                            System.out.println("Khong tim thay dich vu nao.");
                        } else {
                            com.mycompany.quanlygara.util.DinhDangBang.printServices(searchResult);
                        }
                        break;
                    case 4:
                        System.out.print("Nhap Ma Dich vu can cap nhat: ");
                        String ma = com.mycompany.quanlygara.util.TienIchChuoi.removeAccents(sc.nextLine().trim());
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
                        String deleteMa = com.mycompany.quanlygara.util.TienIchChuoi.removeAccents(sc.nextLine().trim());
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
