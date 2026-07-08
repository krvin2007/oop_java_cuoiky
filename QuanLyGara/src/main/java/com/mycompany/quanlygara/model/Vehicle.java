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
    private boolean isDeleted;

    public Vehicle() {
    }

    public Vehicle(String licensePlate, String brand, String model, int productionYear, Owner owner) {
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.owner = owner;
        this.isDeleted = false;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
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

    public static String getProvinceName(String code) {
        switch (code) {
            case "11": return "Cao Bằng";
            case "12": return "Lạng Sơn";
            case "14": return "Quảng Ninh";
            case "15": case "16": return "Hải Phòng";
            case "17": return "Thái Bình";
            case "18": return "Nam Định";
            case "19": return "Phú Thọ";
            case "20": return "Thái Nguyên";
            case "21": return "Yên Bái";
            case "22": return "Tuyên Quang";
            case "23": return "Hà Giang";
            case "24": return "Lào Cai";
            case "25": return "Lai Châu";
            case "26": return "Sơn La";
            case "27": return "Điện Biên";
            case "28": return "Hòa Bình";
            case "29": case "30": case "31": case "32": case "33": case "40": return "Hà Nội";
            case "34": return "Hải Dương";
            case "35": return "Ninh Bình";
            case "36": return "Thanh Hóa";
            case "37": return "Nghệ An";
            case "38": return "Hà Tĩnh";
            case "39": case "60": case "93": return "Đồng Nai";
            case "41": case "50": case "51": case "52": case "53": case "54": case "55": case "56": case "57": case "58": case "59": case "61": case "72": return "TP. Hồ Chí Minh";
            case "43": return "Đà Nẵng";
            case "47": return "Đắk Lắk";
            case "48": return "Đắk Nông";
            case "49": return "Lâm Đồng";
            case "62": return "Long An";
            case "63": return "Tiền Giang";
            case "64": return "Vĩnh Long";
            case "65": return "Cần Thơ";
            case "66": return "Đồng Tháp";
            case "67": return "An Giang";
            case "68": return "Kiên Giang";
            case "69": return "Cà Mau";
            case "70": return "Tây Ninh";
            case "71": return "Bến Tre";
            case "73": return "Quảng Bình";
            case "74": return "Quảng Trị";
            case "75": return "Thừa Thiên Huế";
            case "76": return "Quảng Ngãi";
            case "77": return "Bình Định";
            case "78": return "Phú Yên";
            case "79": return "Khánh Hòa";
            case "81": return "Gia Lai";
            case "82": return "Kon Tum";
            case "83": return "Sóc Trăng";
            case "84": return "Trà Vinh";
            case "85": return "Ninh Thuận";
            case "86": return "Bình Thuận";
            case "88": return "Vĩnh Phúc";
            case "89": return "Hưng Yên";
            case "90": return "Hà Nam";
            case "92": return "Quảng Nam";
            case "94": return "Bạc Liêu";
            case "95": return "Hậu Giang";
            case "97": return "Bắc Kạn";
            case "98": return "Bắc Giang";
            case "99": return "Bắc Ninh";
            default: return null;
        }
    }

    public static String getMilitaryUnit(String prefix) {
        switch (prefix) {
            case "AA": return "Quân khu 1";
            case "AB": return "Quân khu 2";
            case "AC": return "Quân khu 3";
            case "AD": return "Quân khu 4";
            case "AE": return "Quân khu 5";
            case "AK": return "Quân khu 7";
            case "AM": return "Quân khu 9";
            case "AN": return "Binh đoàn 11";
            case "AP": return "Binh đoàn 12";
            case "AT": return "Binh đoàn 15";
            case "AV": return "Binh đoàn 18";
            case "AX": return "Binh đoàn 16";
            case "BB": return "Binh chủng Tăng Thiết giáp";
            case "BC": return "Binh chủng Công binh";
            case "BH": return "Binh chủng Hóa học";
            case "BK": return "Binh chủng Đặc công";
            case "BP": return "Binh chủng Pháo binh";
            case "BS": return "Binh chủng Thông tin";
            case "BT": return "Binh chủng Thông tin liên lạc";
            case "HA": return "Học viện Lục quân";
            case "HB": return "Học viện Quốc phòng";
            case "HC": return "Học viện Quân y";
            case "HD": return "Học viện Kỹ thuật Quân sự";
            case "HE": return "Học viện Hậu cần";
            case "HT": return "Trường Sĩ quan Lục quân 1";
            case "HQ": return "Hải quân";
            case "KA": return "Quân đoàn 1";
            case "KB": return "Quân đoàn 2";
            case "KC": return "Quân đoàn 3";
            case "KD": return "Quân đoàn 4";
            case "KK": return "Tổng cục Kỹ thuật";
            case "KP": return "Tổng cục Hậu cần";
            case "KV": return "Tổng cục Công nghiệp Quốc phòng";
            case "PA": return "Cục Đối ngoại Bộ Quốc phòng";
            case "PP": return "Bộ Tổng Tham mưu";
            case "PX": return "Tổng cục Chính trị";
            case "QA": return "Quân chủng Phòng không - Không quân";
            case "QB": return "Bộ Tư lệnh Biên phòng";
            case "QC": return "Bộ Tư lệnh Cảnh sát biển";
            case "QD": return "Bộ Tư lệnh Thủ đô Hà Nội";
            case "QH": return "Tổng cục Tình báo Quốc phòng";
            case "TC": return "Tổng cục Công nghiệp Quốc phòng";
            case "TH": return "Tổng cục Hậu cần";
            case "TT": return "Bộ Tổng Tham mưu";
            case "VT": return "Viettel";
            default: return "Đơn vị Quân đội nhân dân Việt Nam";
        }
    }

    public void nhapInfo(Scanner sc) {
        while (true) {
            System.out.println("--- Chon loai bien so xe ---");
            System.out.println("1. Bien mau trang (Ca nhan, doanh nghiep)");
            System.out.println("2. Bien mau vang (Kinh doanh van tai)");
            System.out.println("3. Bien mau vang chu do (Khu kinh te dac biet/cua khau)");
            System.out.println("4. Bien mau xanh (Co quan hanh chinh nha nuoc, chinh tri - xa hoi)");
            System.out.println("5. Bien mau do (Quan doi nhan dan)");
            System.out.print("Nhap lua chon cua ban (1-5): ");
            String plateTypeChoice = sc.nextLine().trim();
            if (!plateTypeChoice.matches("[1-5]")) {
                System.out.println("Lua chon khong hop le! Vui long nhap tu 1 den 5.");
                continue;
            }
            
            System.out.print("Nhap bien so xe (Vi du: 29A-123.45 hoac AA-12-34): ");
            String inputPlate = sc.nextLine().trim().toUpperCase();
            
            if (inputPlate.isEmpty()) {
                System.out.println("Bien so xe khong duoc de trong!");
                continue;
            }
            
            boolean isValid = false;
            String classification = "";
            String provinceOrUnit = "";
            
            if (plateTypeChoice.equals("5")) {
                // Military plate constraint: must start with 2 uppercase letters followed by digits
                if (inputPlate.matches("^[A-Z]{2}[- ]?([0-9]{2}[- ]?[0-9]{2}|[0-9]{4,5})$")) {
                    isValid = true;
                    classification = "Bien do (Quan doi nhan dan)";
                    String prefix = inputPlate.substring(0, 2);
                    provinceOrUnit = getMilitaryUnit(prefix);
                } else {
                    System.out.println("Loi: Bien so quan doi khong dung dinh dang! Vi du: AA-12-34.");
                }
            } else {
                // Civil plates (1, 2, 3, 4) constraint: starts with 2 digits of valid province
                if (inputPlate.matches("^([0-9]{2})([A-Z]{1,2}[0-9]?)[- ]?([0-9]{3,5}|[0-9]{3}\\.[0-9]{2})$")) {
                    String provCode = inputPlate.substring(0, 2);
                    String provName = getProvinceName(provCode);
                    if (provName != null) {
                        isValid = true;
                        provinceOrUnit = provName;
                        if (plateTypeChoice.equals("1")) classification = "Bien trang (Ca nhan, doanh nghiep)";
                        else if (plateTypeChoice.equals("2")) classification = "Bien vang (Kinh doanh van tai)";
                        else if (plateTypeChoice.equals("3")) classification = "Bien vang chu do (Khu kinh te dac biet/cua khau)";
                        else if (plateTypeChoice.equals("4")) classification = "Bien xanh (Co quan nha nuoc)";
                    } else {
                        System.out.println("Loi: Hai chu so dau tien '" + provCode + "' khong phai ma tinh/thanh pho hop le cua Viet Nam!");
                    }
                } else {
                    System.out.println("Loi: Dinh dang bien so xe khong hop le! Vi du: 29A-123.45.");
                }
            }
            
            if (isValid) {
                this.licensePlate = inputPlate;
                System.out.println(">> Xac thuc bien so thanh cong!");
                System.out.println("   - Phan loai: " + classification);
                System.out.println("   - Noi cap / Don vi: " + provinceOrUnit);
                break;
            }
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
