# PHÂN TÍCH CƠ SỞ DỮ LIỆU DỰ ÁN QUẢN LÝ GARA
**File Database:** `c:\Users\CHAU TUAN KIET\Desktop\OOP_CKKK\Data\quangara.sql`
**Hệ quản trị CSDL:** MySQL / MariaDB

Tài liệu này trình bày chi tiết về cấu trúc của từng bảng trong cơ sở dữ liệu `quangara`, bao gồm các thuộc tính, Khóa chính (Primary Key - PK), Khóa ngoại (Foreign Key - FK), và các mối quan hệ (Relationships) giữa các thực thể.

---

## 1. TỔNG QUAN CÁC BẢNG (TABLES)
Cơ sở dữ liệu bao gồm **8 bảng** chính được thiết kế chuẩn hóa để quản lý toàn diện quy trình hoạt động của Gara:
1. `chu_xe` (Chủ xe - Khách hàng)
2. `xe` (Xe ô tô)
3. `nhan_vien` (Nhân viên: Quản lý, Kế toán, Thủ kho, Thợ máy)
4. `dich_vu` (Hạng mục Dịch vụ)
5. `linh_kien` (Hạng mục Phụ tùng / Linh kiện)
6. `phieu_sua_chua` (Phiếu sửa chữa)
7. `chi_tiet_phieu_sua` (Chi tiết phiếu sửa chữa)
8. `hoa_don` (Hóa đơn thanh toán)

---

## 2. CHI TIẾT CẤU TRÚC VÀ CÁC RÀNG BUỘC (CONSTRAINTS)

### 2.1. Bảng `chu_xe` (Khách Hàng)
*   **Mô tả:** Lưu trữ thông tin cá nhân của chủ phương tiện.
*   **Khóa chính (PK):** `id` (INT, Tự tăng - AUTO_INCREMENT)
*   **Thuộc tính nổi bật:**
    *   `phone` (VARCHAR): Số điện thoại (Được thiết lập là UNIQUE KEY để tránh trùng lặp khách hàng).
    *   `da_xoa` (TINYINT): Cờ đánh dấu xóa mềm (Soft Delete), giá trị mặc định là 0.

### 2.2. Bảng `xe` (Xe Ô Tô)
*   **Mô tả:** Quản lý danh sách các phương tiện mang đến Gara.
*   **Khóa chính (PK):** `bien_so` (VARCHAR - Biển số xe)
*   **Khóa ngoại (FK):**
    *   `ma_chu_xe` (INT): Liên kết với `id` của bảng `chu_xe`.
*   **Mối quan hệ:** Một chủ xe (`chu_xe`) có thể sở hữu nhiều chiếc xe (`xe`) ➔ **Quan hệ 1-N (One-to-Many)**.

### 2.3. Bảng `nhan_vien` (Nhân Viên)
*   **Mô tả:** Chứa thông tin của tất cả nhân viên làm việc trong Gara, bao gồm hệ thống phân quyền (role) và tài khoản đăng nhập.
*   **Khóa chính (PK):** `id` (INT, Tự tăng - AUTO_INCREMENT)
*   **Thuộc tính nổi bật:**
    *   `username` và `phone` (VARCHAR): Đều là UNIQUE KEY. Đảm bảo mỗi nhân viên có một tài khoản duy nhất.
    *   `role` (VARCHAR): Vai trò của nhân viên (Ví dụ: `QuanLy`, `KeToan`, `ThuKho`, `KyThuat`).
    *   `chuyen_mon`: Chuyên môn của kỹ thuật viên.

### 2.4. Bảng `dich_vu` và `linh_kien` (Hạng Mục Sửa Chữa)
*   **Bảng `dich_vu`:**
    *   **Khóa chính (PK):** `ma` (VARCHAR)
*   **Bảng `linh_kien`:**
    *   **Khóa chính (PK):** `ma` (VARCHAR)
    *   **Thuộc tính:** Có thêm cột `so_luong_ton` (Tồn kho) và `vi_tri` (Vị trí lưu trữ).

### 2.5. Bảng `phieu_sua_chua` (Phiếu Sửa Chữa)
*   **Mô tả:** Nơi ghi nhận quá trình tiếp nhận và phân công sửa chữa xe.
*   **Khóa chính (PK):** `ma_phieu` (INT, Tự tăng)
*   **Khóa ngoại (FK):**
    *   `bien_so` (VARCHAR): Tham chiếu tới bảng `xe` (Xe nào đang được sửa).
    *   `ma_tho_may` (INT): Tham chiếu tới `id` trong bảng `nhan_vien` (Thợ nào đảm nhận).
*   **Mối quan hệ:**
    *   1 Xe (`xe`) có thể có nhiều Phiếu sửa chữa (qua các lần khác nhau) ➔ **1-N**.
    *   1 Thợ máy (`nhan_vien`) có thể đảm nhận nhiều Phiếu sửa chữa ➔ **1-N**.

### 2.6. Bảng `chi_tiet_phieu_sua` (Chi Tiết Phiếu Sửa)
*   **Mô tả:** Lưu lại danh sách các linh kiện và dịch vụ đã sử dụng cho một phiếu sửa chữa cụ thể.
*   **Khóa chính (PK):** `id` (INT, Tự tăng)
*   **Khóa ngoại (FK):**
    *   `ma_phieu` (INT): Tham chiếu tới bảng `phieu_sua_chua`.
*   **Ràng buộc Unique:** `uq_order_item` trên (`ma_phieu`, `ma_hang_muc`, `loai_hang_muc`). Đảm bảo trong một phiếu sửa chữa, không nhập trùng lặp một mã linh kiện hay dịch vụ (nếu dùng nhiều thì chỉ cập nhật cột `so_luong`).
*   **Mối quan hệ:** 1 Phiếu sửa chữa (`phieu_sua_chua`) chứa nhiều Chi tiết phiếu (`chi_tiet_phieu_sua`) ➔ **1-N**.

### 2.7. Bảng `hoa_don` (Hóa Đơn)
*   **Mô tả:** Hóa đơn thanh toán cuối cùng.
*   **Khóa chính (PK):** `ma_hoa_don` (INT, Tự tăng)
*   **Khóa ngoại (FK):**
    *   `ma_phieu` (INT): Tham chiếu tới `phieu_sua_chua`.
*   **Đặc điểm quan trọng:**
    *   `ma_phieu` được đặt là **UNIQUE KEY** trong bảng này.
    *   **Mối quan hệ:** Điều này ép buộc quan hệ **1-1 (One-to-One)** giữa Phiếu sửa chữa và Hóa đơn. Mỗi phiếu sửa chữa chỉ xuất duy nhất một hóa đơn.

---

## 3. TỔNG KẾT QUY TẮC RÀNG BUỘC (RESTRICT)
Trong toàn bộ file thiết lập CSDL, phần khai báo Foreign Key đều được gán thuộc tính `ON DELETE RESTRICT`. 
*   **Ý nghĩa:** CSDL sẽ chặn (không cho phép) việc xóa các dữ liệu gốc nếu chúng đang được sử dụng ở các bảng liên quan.
*   **Ví dụ:** Không thể xóa thông tin một khách hàng (`chu_xe`) nếu xe của họ đang tồn tại trong hệ thống. Không thể xóa một thợ máy (`nhan_vien`) nếu ID của thợ này đang nằm trong một `phieu_sua_chua` (Phiếu sửa chữa). 

Cách làm này đảm bảo Tính Toàn Vẹn Dữ Liệu (Data Integrity), ngăn chặn tuyệt đối lỗi "chỉ mục mồ côi" (orphan records) trong hệ thống quản lý gara ô tô.
