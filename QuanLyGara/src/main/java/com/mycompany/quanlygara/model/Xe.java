
package com.mycompany.quanlygara.model;

import java.util.Scanner;


public class Xe {
    
    private String licensePlate;
    
    private String brand;
    
    private String model;
    
    private int productionYear;
    
    private ChuXe ChuXe;
    
    private String color;
    
    private String condition;
    
    private boolean isDeleted;

    
    public Xe() {
    }

    
    public Xe(String licensePlate, String brand, String model, int productionYear, ChuXe ChuXe, String color,
            String condition) {
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.ChuXe = ChuXe;
        this.color = color;
        this.condition = condition;
        this.isDeleted = false;
    }

    
    public String getColor() {
        return color;
    }

    
    public void setColor(String color) {
        this.color = color;
    }

    
    public String getCondition() {
        return condition;
    }

    
    public void setCondition(String condition) {
        this.condition = condition;
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

    
    public ChuXe getOwner() {
        return ChuXe;
    }

    
    public void setOwner(ChuXe ChuXe) {
        this.ChuXe = ChuXe;
    }

    
    public static String getProvinceName(String code) {
        switch (code) {
            case "11":
                return "Cao Bang";
            case "12":
                return "Lang Son";
            case "14":
                return "Quang Ninh";
            case "15":
            case "16":
                return "Hai Phong";
            case "17":
                return "Thai Binh";
            case "18":
                return "Nam Dinh";
            case "19":
                return "Phu Tho";
            case "20":
                return "Thai Nguyen";
            case "21":
                return "Yen Bai";
            case "22":
                return "Tuyen Quang";
            case "23":
                return "Ha Giang";
            case "24":
                return "Lao Cai";
            case "25":
                return "Lai Chau";
            case "26":
                return "Son La";
            case "27":
                return "Dien Bien";
            case "28":
                return "Hoa Binh";
            case "29":
            case "30":
            case "31":
            case "32":
            case "33":
            case "40":
                return "Ha Noi";
            case "34":
                return "Hai Duong";
            case "35":
                return "Ninh Binh";
            case "36":
                return "Thanh Hoa";
            case "37":
                return "Nghe An";
            case "38":
                return "Ha Tinh";
            case "39":
            case "60":
            case "93":
                return "Dong Nai";
            case "41":
            case "50":
            case "51":
            case "52":
            case "53":
            case "54":
            case "55":
            case "56":
            case "57":
            case "58":
            case "59":
            case "61":
            case "72":
                return "TP. Ho Chi Minh";
            case "43":
                return "Da Nang";
            case "47":
                return "Dak Lak";
            case "48":
                return "Dak Nong";
            case "49":
                return "Lam Dong";
            case "62":
                return "Long An";
            case "63":
                return "Tien Giang";
            case "64":
                return "Vinh Long";
            case "65":
                return "Can Tho";
            case "66":
                return "Dong Thap";
            case "67":
                return "An Giang";
            case "68":
                return "Kien Giang";
            case "69":
                return "Ca Mau";
            case "70":
                return "Tay Ninh";
            case "71":
                return "Ben Tre";
            case "73":
                return "Quang Binh";
            case "74":
                return "Quang Tri";
            case "75":
                return "Thua Thien Hue";
            case "76":
                return "Quang Ngai";
            case "77":
                return "Binh Dinh";
            case "78":
                return "Phu Yen";
            case "79":
                return "Khanh Hoa";
            case "81":
                return "Gia Lai";
            case "82":
                return "Kon Tum";
            case "83":
                return "Soc Trang";
            case "84":
                return "Tra Vinh";
            case "85":
                return "Ninh Thuan";
            case "86":
                return "Binh Thuan";
            case "88":
                return "Vinh Phuc";
            case "89":
                return "Hung Yen";
            case "90":
                return "Ha Nam";
            case "92":
                return "Quang Nam";
            case "94":
                return "Bac Lieu";
            case "95":
                return "Hau Giang";
            case "97":
                return "Bac Kan";
            case "98":
                return "Bac Giang";
            case "99":
                return "Bac Ninh";
            default:
                return null;
        }
    }

    
    public static String getMilitaryUnit(String prefix) {
        switch (prefix) {
            case "AA":
                return "Quan khu 1";
            case "AB":
                return "Quan khu 2";
            case "AC":
                return "Quan khu 3";
            case "AD":
                return "Quan khu 4";
            case "AE":
                return "Quan khu 5";
            case "AK":
                return "Quan khu 7";
            case "AM":
                return "Quan khu 9";
            case "AN":
                return "Binh doan 11";
            case "AP":
                return "Binh doan 12";
            case "AT":
                return "Binh doan 15";
            case "AV":
                return "Binh doan 18";
            case "AX":
                return "Binh doan 16";
            case "BB":
                return "Binh chung Tang Thiet giap";
            case "BC":
                return "Binh chung Cong binh";
            case "BH":
                return "Binh chung Hoa hoc";
            case "BK":
                return "Binh chung Dac cong";
            case "BP":
                return "Binh chung Phao binh";
            case "BS":
                return "Binh chung Thong tin";
            case "BT":
                return "Binh chung Thong tin lien lac";
            case "HA":
                return "Hoc vien Luc quan";
            case "HB":
                return "Hoc vien Quoc phong";
            case "HC":
                return "Hoc vien Quan y";
            case "HD":
                return "Hoc vien Ky thuat Quan su";
            case "HE":
                return "Hoc vien Hau can";
            case "HT":
                return "Truong Si quan Luc quan 1";
            case "HQ":
                return "Hai quan";
            case "KA":
                return "Quan doan 1";
            case "KB":
                return "Quan doan 2";
            case "KC":
                return "Quan doan 3";
            case "KD":
                return "Quan doan 4";
            case "KK":
                return "Tong cuc Ky thuat";
            case "KP":
                return "Tong cuc Hau can";
            case "KV":
                return "Tong cuc Cong nghiep Quoc phong";
            case "PA":
                return "Cuc Doi ngoai Bo Quoc phong";
            case "PP":
                return "Bo Tong Tham muu";
            case "PX":
                return "Tong cuc Chinh tri";
            case "QA":
                return "Quan chung Phong khong - Khong quan";
            case "QB":
                return "Bo Tu lenh Bien phong";
            case "QC":
                return "Bo Tu lenh Canh sat bien";
            case "QD":
                return "Bo Tu lenh Thu do Ha Noi";
            case "QH":
                return "Tong cuc Tinh bao Quoc phong";
            case "TC":
                return "Tong cuc Cong nghiep Quoc phong";
            case "TH":
                return "Tong cuc Hau can";
            case "TT":
                return "Bo Tong Tham muu";
            case "VT":
                return "Viettel";
            default:
                return "Don vi Quan doi nhan dan Viet Nam";
        }
    }

    
    public static boolean isValidPlate(String inputPlate) {
        if (inputPlate == null)
            return false;
        String cleanPlate = inputPlate.trim().toUpperCase();

        
        if (cleanPlate.matches("^[A-Z]{2}[- ]?([0-9]{2}[- ]?[0-9]{2}|[0-9]{4,5})$")) {
            return true;
        }

        
        if (cleanPlate.matches("^([0-9]{2})([A-Z]{1,2}[0-9]?)[- ]?([0-9]{3,5}|[0-9]{3}\\.[0-9]{2})$")) {
            String provCode = cleanPlate.substring(0, 2);
            return getProvinceName(provCode) != null;
        }

        return false;
    }

    
    public void nhapInfo(Scanner sc) {
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
            String inputPlate = com.mycompany.quanlygara.util.TienIchChuoi.removeAccents(sc.nextLine().trim())
                    .toUpperCase();

            if (inputPlate.isEmpty()) {
                System.out.println("Bien so xe khong duoc de trong!");
                continue;
            }

            boolean isValid = false;
            String classification = "";
            String provinceOrUnit = "";

            if (plateTypeChoice.equals("2")) {
                
                
                if (inputPlate.matches("^[A-Z]{2}[- ]?([0-9]{2}[- ]?[0-9]{2}|[0-9]{4,5})$")) {
                    isValid = true;
                    classification = "Bien quan su (Quan doi)";
                    String prefix = inputPlate.substring(0, 2);
                    provinceOrUnit = getMilitaryUnit(prefix);
                } else {
                    System.out.println("Loi: Bien so quan doi khong dung dinh dang! Vi du: AA-12-34.");
                }
            } else {
                
                if (inputPlate.matches("^([0-9]{2})([A-Z]{1,2}[0-9]?)[- ]?([0-9]{3,5}|[0-9]{3}\\.[0-9]{2})$")) {
                    String provCode = inputPlate.substring(0, 2);
                    String provName = getProvinceName(provCode);
                    if (provName != null) {
                        isValid = true;
                        provinceOrUnit = provName;
                        classification = "Bien dan su (Trang, Vang, Xanh...)";
                    } else {
                        System.out.println("Loi: Hai chu so dau tien '" + provCode
                                + "' khong phai ma tinh/thanh pho hop le cua Viet Nam!");
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
        while (this.brand.length() < 2) {
            System.out.print("Hang xe qua ngan (it nhat 2 ky tu)! Moi nhap lai: ");
            this.brand = sc.nextLine().trim();
        }

        System.out.print("Nhap mau xe (model): ");
        this.model = sc.nextLine().trim();
        while (this.model.length() < 2) {
            System.out.print("Mau xe qua ngan (it nhat 2 ky tu)! Moi nhap lai: ");
            this.model = sc.nextLine().trim();
        }
        int currentYear = java.time.Year.now().getValue();
        while (true) {
            try {
                System.out.print("Nhap nam san xuat: ");
                this.productionYear = Integer.parseInt(sc.nextLine());
                if (this.productionYear < 1886 || this.productionYear > currentYear) {
                    System.out.println("Nam san xuat phai tu 1886 den " + currentYear + "!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so nguyen hop le!");
            }
        }
        System.out.print("Nhap mau sac xe (color): ");
        this.color = sc.nextLine().trim();
        while (this.color.length() < 2) {
            System.out.print("Mau sac khong hop le (it nhat 2 ky tu)! Moi nhap lai: ");
            this.color = sc.nextLine().trim();
        }

        System.out.print("Nhap tinh trang xe luc tiep nhan: ");
        this.condition = com.mycompany.quanlygara.util.TienIchChuoi.removeAccents(sc.nextLine().trim());
        while (this.condition.isEmpty()) {
            System.out.print("Tinh trang xe khong duoc de trong! Moi nhap lai: ");
            this.condition = com.mycompany.quanlygara.util.TienIchChuoi.removeAccents(sc.nextLine().trim());
        }

        
        int inputOwnerId = 0;
        while (true) {
            try {
                System.out.println("--- Danh sach Chu xe hien co ---");
                com.mycompany.quanlygara.controller.ChuXeDAO ownerDAO = new com.mycompany.quanlygara.controller.ChuXeDAO();
                java.util.List<ChuXe> availableOwners = ownerDAO.layTatCa();
                com.mycompany.quanlygara.util.DinhDangBang.printCustomers(availableOwners);
                System.out.println("--------------------------------");
                
                System.out.print("Nhap ID chu xe: ");
                inputOwnerId = Integer.parseInt(sc.nextLine());
                if (inputOwnerId <= 0) {
                    System.out.println("ID chu xe phai la so nguyen duong. Vui long nhap lai!");
                    continue;
                }
                
                boolean exists = false;
                for (ChuXe c : availableOwners) {
                    if (c.getId() == inputOwnerId) {
                        exists = true;
                        this.ChuXe = c;
                        break;
                    }
                }
                
                if (!exists) {
                    System.out.println("Loi: ID chu xe khong ton tai! Vui long nhap dung ID tu danh sach tren.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so nguyen hop le!");
            } catch (Exception e) {
                System.out.println("Loi lay du lieu chu xe: " + e.getMessage());
            }
        }
    }

    
    @Override
    public String toString() {
        return "Xe [License Plate: " + licensePlate + ", Brand: " + brand + ", Model: " + model
                + ", Production Year: " + productionYear + ", Color: " + color + ", Condition: " + condition
                + ", ChuXe: " + (ChuXe != null ? ChuXe.getName() : "null") + "]";
    }
}
