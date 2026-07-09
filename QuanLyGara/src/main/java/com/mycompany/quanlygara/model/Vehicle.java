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
    // Biển số xe (License Plate)
    private String licensePlate;
    // Hãng xe (Brand)
    private String brand;
    // Mẫu xe (Model)
    private String model;
    // Năm sản xuất (Production Year)
    private int productionYear;
    // Khách hàng - Chủ xe (Customer/Owner)
    private Customer Customer;
    // Màu sắc (Color)
    private String color;
    // Tình trạng xe (Condition)
    private String condition;
    // Trạng thái đã xóa (Is Deleted)
    private boolean isDeleted;

    // Khởi tạo đối tượng Vehicle mới
    public Vehicle() {
    }

    // Khởi tạo đối tượng Vehicle mới
    public Vehicle(String licensePlate, String brand, String model, int productionYear, Customer Customer, String color, String condition) {
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.Customer = Customer;
        this.color = color;
        this.condition = condition;
        this.isDeleted = false;
    }

    // Lấy giá trị của thuộc tính Color
    public String getColor() {
        return color;
    }

    // Cập nhật giá trị cho thuộc tính Color
    public void setColor(String color) {
        this.color = color;
    }

    // Lấy giá trị của thuộc tính Condition
    public String getCondition() {
        return condition;
    }

    // Cập nhật giá trị cho thuộc tính Condition
    public void setCondition(String condition) {
        this.condition = condition;
    }

    // Lấy trạng thái boolean của thuộc tính Deleted
    public boolean isDeleted() {
        return isDeleted;
    }

    // Cập nhật giá trị cho thuộc tính Deleted
    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    // Lấy giá trị của thuộc tính LicensePlate
    public String getLicensePlate() {
        return licensePlate;
    }

    // Cập nhật giá trị cho thuộc tính LicensePlate
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    // Lấy giá trị của thuộc tính Brand
    public String getBrand() {
        return brand;
    }

    // Cập nhật giá trị cho thuộc tính Brand
    public void setBrand(String brand) {
        this.brand = brand;
    }

    // Lấy giá trị của thuộc tính Model
    public String getModel() {
        return model;
    }

    // Cập nhật giá trị cho thuộc tính Model
    public void setModel(String model) {
        this.model = model;
    }

    // Lấy giá trị của thuộc tính ProductionYear
    public int getProductionYear() {
        return productionYear;
    }

    // Cập nhật giá trị cho thuộc tính ProductionYear
    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    // Lấy giá trị của thuộc tính Owner
    public Customer getOwner() {
        return Customer;
    }

    // Cập nhật giá trị cho thuộc tính Owner
    public void setOwner(Customer Customer) {
        this.Customer = Customer;
    }

    // Lấy giá trị của thuộc tính ProvinceName
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

    // Lấy giá trị của thuộc tính MilitaryUnit
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

    // Kiểm tra cấu trúc biển số xe Việt Nam (Biển dân sự hoặc quân đội)
    public static boolean isValidPlate(String inputPlate) {
        if (inputPlate == null) return false;
        String cleanPlate = inputPlate.trim().toUpperCase();
        
        // 1. Kiểm tra biển quân đội (2 chữ cái + dãy số)
        if (cleanPlate.matches("^[A-Z]{2}[- ]?([0-9]{2}[- ]?[0-9]{2}|[0-9]{4,5})$")) {
            return true;
        }
        
        // 2. Kiểm tra biển dân sự (Mã tỉnh/thành gồm 2 chữ số + sêri + số thứ tự)
        if (cleanPlate.matches("^([0-9]{2})([A-Z]{1,2}[0-9]?)[- ]?([0-9]{3,5}|[0-9]{3}\\.[0-9]{2})$")) {
            String provCode = cleanPlate.substring(0, 2);
            return getProvinceName(provCode) != null;
        }
        
        return false;
    }

    // Nhập thông tin cho đối tượng từ giao diện Console
    public void nhapInfo(Scanner sc) {
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
            String inputPlate = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim()).toUpperCase();
            
            if (inputPlate.isEmpty()) {
                System.out.println("Bien so xe khong duoc de trong!");
                continue;
            }
            
            boolean isValid = false;
            String classification = "";
            String provinceOrUnit = "";
            
            if (plateTypeChoice.equals("2")) {
                // Military plate constraint: must start with 2 uppercase letters followed by digits
                if (inputPlate.matches("^[A-Z]{2}[- ]?([0-9]{2}[- ]?[0-9]{2}|[0-9]{4,5})$")) {
                    isValid = true;
                    classification = "Bien quan su (Quan doi)";
                    String prefix = inputPlate.substring(0, 2);
                    provinceOrUnit = getMilitaryUnit(prefix);
                } else {
                    System.out.println("Loi: Bien so quan doi khong dung dinh dang! Vi du: AA-12-34.");
                }
            } else {
                // Civil plates constraint: starts with 2 digits of valid province
                if (inputPlate.matches("^([0-9]{2})([A-Z]{1,2}[0-9]?)[- ]?([0-9]{3,5}|[0-9]{3}\\.[0-9]{2})$")) {
                    String provCode = inputPlate.substring(0, 2);
                    String provName = getProvinceName(provCode);
                    if (provName != null) {
                        isValid = true;
                        provinceOrUnit = provName;
                        classification = "Bien dan su (Trang, Vang, Xanh...)";
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
        this.brand = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
        while (this.brand.isEmpty()) {
            System.out.print("Hang xe khong duoc de trong! Moi nhap lai: ");
            this.brand = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
        }

        System.out.print("Nhap mau xe (model): ");
        this.model = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
        while (this.model.isEmpty()) {
            System.out.print("Mau xe khong duoc de trong! Moi nhap lai: ");
            this.model = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
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
        System.out.print("Nhap mau sac xe (color): ");
        this.color = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
        while (this.color.isEmpty()) {
            System.out.print("Mau sac khong duoc de trong! Moi nhap lai: ");
            this.color = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
        }

        System.out.print("Nhap tinh trang xe luc tiep nhan: ");
        this.condition = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
        while (this.condition.isEmpty()) {
            System.out.print("Tinh trang xe khong duoc de trong! Moi nhap lai: ");
            this.condition = com.mycompany.quanlygara.util.StringUtils.removeAccents(sc.nextLine().trim());
        }

        // Note: Customer should be set by the caller fetching from DAO based on input ID.
        int inputOwnerId = 0;
        while (true) {
            System.out.print("Nhap ID chu xe: ");
            try {
                inputOwnerId = Integer.parseInt(sc.nextLine());
                if (inputOwnerId <= 0) {
                    System.out.println("ID chu xe phai la so nguyen duong. Vui long nhap lai!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so nguyen hop le!");
            }
        }
        this.Customer = new Customer();
        this.Customer.setId(inputOwnerId); // Temporary ID wrapper
    }

    // Trả về chuỗi đại diện chứa thông tin của đối tượng
    @Override
    public String toString() {
        return "Vehicle [License Plate: " + licensePlate + ", Brand: " + brand + ", Model: " + model + ", Production Year: " + productionYear + ", Color: " + color + ", Condition: " + condition + ", Customer: " + (Customer != null ? Customer.getName() : "null") + "]";
    }
}
