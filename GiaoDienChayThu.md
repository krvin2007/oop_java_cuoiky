# KẾT QUẢ CHẠY THỬ HỆ THỐNG QUẢN LÝ GARA Ô TÔ

Dưới đây là kết quả chạy thử nghiệm tất cả các tính năng của hệ thống Quản lý Gara Ô tô kết nối trực tiếp với Cơ sở dữ liệu MySQL (XAMPP).

---

## 1. GIAO DIỆN ĐĂNG NHẬP HỆ THỐNG
```text
====================================================
       CHAO MUNG DEN HE THONG QUAN LY GARA          
====================================================

--- VUI LONG DANG NHAP ---
Username (nhap 'exit' de thoat): admin
Password: admin
Dang nhap thanh cong! Xin chao QuanLy admin
```

---

## 2. MENU CHÍNH CỦA QUẢN TRỊ VIÊN (ADMIN)
```text
====================================================
        HE THONG QUAN LY GARA SUA CHUA O TO         
====================================================
1. Quan ly Xe va Chu xe
2. Quan ly Ky thuat vien (Mechanic)
3. Quan ly Kho linh kien (LinhKien)
4. Quan ly Phieu sua chua (Repair Order)
5. Thanh toan & Hoa don (Invoice)
6. Bao cao thong ke doanh thu & hieu suat
7. Quan ly Danh muc Dich vu (DichVu)
8. Doi mat khau
0. Dang xuat (Logout)
====================================================
Nhap lua chon cua ban (0-8): 
```

---

## 3. CHỨC NĂNG 1: XEM DANH SÁCH CHỦ XE & DANH SÁCH XE
```text
--- MENU QUAN LY XE & CHU XE ---
1. Them Chu xe moi (Owner)
2. Them Xe moi (Vehicle)
3. Xem danh sach Chu xe
4. Xem danh sach Xe
5. Tim kiem xe theo Bien so
6. Tim kiem xe theo Ten chu xe
7. Cap nhat thong tin Chu xe
8. Xoa Xe
0. Quay lai Menu chinh
--------------------------------
Nhap lua chon (0-8): 3

Danh sach chu xe:
Owner [ID: 1, Name: Manh Quynh, Phone: 0987654321, Address: HCM]
Owner [ID: 2, Name: thanh tai, Phone: 0397062429, Address: tan binh]

Nhap lua chon (0-8): 4

Danh sach xe trong gara:
Vehicle [License Plate: 12345, Brand: BMW, Model: Bugatti Chiron, Production Year: 2017, Owner: Manh Quynh]
```

---

## 4. CHỨC NĂNG 2: XEM DANH SÁCH KỸ THUẬT VIÊN (SẮP XẾP)
```text
--- MENU QUAN LY KY THUAT VIEN ---
1. Them Ky thuat vien
2. Xem danh sach Ky thuat vien (Sap xep theo ten)
3. Xem danh sach Ky thuat vien (Sap xep theo luong tang dan)
4. Xem danh sach Ky thuat vien (Sap xep theo luong giam dan)
5. Cap nhat thong tin Ky thuat vien
6. Xoa Ky thuat vien
0. Quay lai Menu chinh
---------------------------------
Nhap lua chon (0-6): 2

Danh sach ky thuat vien (Xep theo ten):
Mechanic [ID: 4, Name: Thomay User, Phone: 0900000004, Spec: Chung, Salary: 5.000.000, Status: Đang rảnh]
```

---

## 5. CHỨC NĂNG 3: XEM KHO LINH KIỆN (SẮP XẾP THEO GIÁ TĂNG DẦN)
```text
--- MENU QUAN LY KHO LINH KIEN ---
1. Them Linh kien moi
2. Xem danh sach linh kien (Sap xep theo gia ban tang dan)
3. Xem danh sach linh kien (Sap xep theo gia ban giam dan)
4. Xem danh sach linh kien (Sap xep theo ton kho tang dan)
5. Tim kiem linh kien theo Ten
6. Cap nhat Linh kien
7. Xoa Linh kien
0. Quay lai Menu chinh
---------------------------------
Nhap lua chon (0-7): 2

Kho linh kien (Sap xep gia Tang dan):
LinhKien [Ma: LK002, Ten: Loc nhot Bosch, Don gia: 120.000 VND, So luong ton: 15]
LinhKien [Ma: LK003, Ten: Loc gio dong co Denso, Don gia: 180.000 VND, So luong ton: 10]
LinhKien [Ma: LK001, Ten: Nhot Castrol Edge 5W-30, Don gia: 250.000 VND, So luong ton: 20]
LinhKien [Ma: LK004, Ten: Ma phanh truoc Brembo, Don gia: 950.000 VND, So luong ton: 8]
LinhKien [Ma: LK005, Ten: Lop Michelin Pilot Sport 4, Don gia: 2.800.000 VND, So luong ton: 4]
```

---

## 6. CHỨC NĂNG 4: DANH SÁCH PHIẾU SỬA CHỮA & CHI TIẾT CÔNG VIỆC (ĐA HÌNH)
```text
--- MENU PHIEU SUA CHUA ---
1. Lap Phieu sua chua moi (Tiep nhan xe)
2. Xem danh sach tat ca Phieu sua chua
3. Them Linh kien / Dich vu sua chua (Cho xe)
4. Xem chi tiet cong viec cua Phieu sua chua
5. Cap nhat trang thai Phieu (RECEIVING -> REPAIRING -> COMPLETED)
0. Quay lai Menu chinh
---------------------------
Nhap lua chon (0-5): 2

Danh sach phieu sua chua:
RepairOrder [ID: 1, Vehicle: 12345, Entry: 2026-07-08 15:00:00, Exit: 2026-07-08 18:30:00, Mechanic: Thomay User, Status: COMPLETED, Details: 2 items]
RepairOrder [ID: 2, Vehicle: 12345, Entry: 2026-07-08 19:00:00, Exit: N/A, Mechanic: Thomay User, Status: REPAIRING, Details: 1 items]

Nhap lua chon (0-5): 4
Nhap ID Phieu sua chua can xem chi tiet: 1

=== THONG TIN PHIEU SUA CHUA ===
RepairOrder [ID: 1, Vehicle: 12345, Entry: 2026-07-08 15:00:00, Exit: 2026-07-08 18:30:00, Mechanic: Thomay User, Status: COMPLETED, Details: 2 items]

--- CHI TIET LINH KIEN & CONG VIEC (SU DUNG TINH DA HINH) ---
- [Dich Vu] Thay nhot may | Ma: DV001 | Don gia (Tron goi): 150.000 VND | Thanh tien: 150.000 VND
- [Linh Kien] Nhot Castrol Edge 5W-30 | Ma: LK001 | So luong: 1 | Don gia: 250.000 VND | Thanh tien: 250.000 VND
--------------------------------------
Tong tien linh kien: 250.000 VND
Tong tien cong: 150.000 VND
```

---

## 7. CHỨC NĂNG 5: THANH TOÁN & HOÁ ĐƠN ĐÃ XUẤT (GỒM THUẾ VAT)
```text
--- MENU QUAN LY THANH TOAN & HOA DON ---
1. Thanh toan va Xuat hoa don cho xe
2. Xem danh sach tat ca hoa don da thanh toan
0. Quay lai Menu chinh
-----------------------------------------
Nhap lua chon (0-2): 2

Danh sach hoa don da thanh toan:
Invoice [ID: 1, OrderID: 1, Payment Date: 2026-07-08 18:30:00, Total Amount: 440.000 VND]
```

---

## 8. CHỨC NĂNG 6: BÁO CÁO THỐNG KÊ DOANH THU & HIỆU SUẤT GARA
```text
--- MENU BAO CAO THONG KE ---
1. Thong ke doanh thu theo khoang thoi gian
2. Thong ke Top linh kien ban nhieu nhat
3. Thong ke Top xe sua chua nhieu nhat
4. Thong ke Top ky thuat vien lam viec nhieu nhat
0. Quay lai Menu chinh
-----------------------------
Nhap lua chon (0-4): 1
Nhap tu ngay (yyyy-MM-dd, hoac Enter de lay het): [Enter]
Nhap den ngay (yyyy-MM-dd, hoac Enter de lay het): [Enter]
----------------------------------------------
TONG DOANH THU THU DUOC: 440.000 VND
----------------------------------------------

Nhap lua chon (0-4): 2
Nhap so luong linh kien can lay (limit): 5
=== TOP LINH KIEN SU DUNG NHIEU NHAT ===
1. Nhot Castrol Edge 5W-30 | So luong: 1

Nhap lua chon (0-4): 3
Nhap so luong xe can lay (limit): 5
=== TOP XE DUOC SUA CHUA NHIEU NHAT ===
1. 12345 - BMW Bugatti Chiron | So lan sua: 2

Nhap lua chon (0-4): 4
Nhap so luong ky thuat vien can lay (limit): 5
=== TOP KY THUAT VIEN DUOC GIAO NHIEU VIEC ===
1. Thomay User | So phieu dam nhan: 2
```

---

## 9. CHỨC NĂNG 7: XEM DANH MỤC DỊCH VỤ SỬA CHỮA
```text
--- MENU QUAN LY DANH MUC DICH VU ---
1. Them Dich vu moi
2. Xem danh sach Dich vu
3. Tim kiem dich vu theo Ten
4. Cap nhat thong tin Dich vu
5. Xoa Dich vu
0. Quay lai Menu chinh
-------------------------------------
Nhap lua chon (0-5): 2

Danh sach dich vu cua gara:
DichVu [Ma: DV001, Ten: Thay nhot may, Don gia: 150.000 VND]
DichVu [Ma: DV002, Ten: Can chinh thuoc lai, Don gia: 300.000 VND]
DichVu [Ma: DV003, Ten: Rua xe hut bui, Don gia: 80.000 VND]
DichVu [Ma: DV004, Ten: Son dam can truoc, Don gia: 800.000 VND]
DichVu [Ma: DV005, Ten: Thay loc gio dong co, Don gia: 50.000 VND]
```

---

## 10. ĐĂNG XUẤT & THOÁT HỆ THỐNG
```text
Nhap lua chon cua ban (0-8): 0
Da dang xuat thanh cong!

--- VUI LONG DANG NHAP ---
Username (nhap 'exit' de thoat): exit
Cam on ban da su dung chuong trinh!
```
