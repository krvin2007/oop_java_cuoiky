<<<<<<< HEAD
# PHÂN TÍCH CHUYÊN SÂU DỰ ÁN QUẢN LÝ GARA Ô TÔ
**Thư mục gốc:** `d:\oop_java_cuoiky\QuanLyGara`

Tài liệu này phân tích chi tiết từng lớp, từng phương thức, cơ chế hoạt động và cách các thành phần tương tác với nhau trong dự án dựa trên mô hình **MVC kết hợp DAO Pattern**.

---

## 1. TỔNG QUAN KIẾN TRÚC & DATABASE
Dự án được chia thành 3 package chính:
*   `model`: Chứa các thực thể (Entity) đại diện cho dữ liệu thực tế và các quy tắc kiểm duyệt đầu vào (Validation).
*   `controller`: Đóng vai trò là Tầng DAO (Data Access Object) xử lý logic nghiệp vụ phức tạp, truy xuất CSDL MySQL.
*   `view`: Chứa `ConsoleView.java`, quản lý giao diện dòng lệnh, điều hướng Menu và tương tác với người dùng.

**Kết nối CSDL:** Xử lý qua file `controller/DBConnection.java`. Sử dụng JDBC kết nối tới MySQL `jdbc:mysql://localhost:3306/quangara`.

---

## 2. PHÂN TÍCH TẦNG VIEW (GIAO DIỆN)
**File:** `view/ConsoleView.java` (Dài hơn 1000 dòng).
**Nhiệm vụ:** Điểm khởi chạy của chương trình (chứa hàm `main`), quản lý đăng nhập và hệ thống Menu.

*   **Hệ thống Đăng nhập (Dòng ~38 - 85):** 
    *   Hàm `login()` sử dụng vòng lặp `while(true)`. 
    *   Thực thi: Trích xuất và hiển thị danh sách tài khoản hiện có (`getAllAccounts()`), sau đó nhận username/password từ người dùng -> Gọi `employeeController.layTheoUsername(username)` -> So sánh mật khẩu -> Phân quyền dựa trên thuộc tính `role`. 
    *   Kết quả: Biến toàn cục `currentUser` lưu trữ thông tin phiên làm việc.
*   **Menu Động (Dòng ~90 - 150):** 
    *   Hàm `showMainMenu()` hiển thị các chức năng tùy thuộc vào `currentUser.getRole()`. Ví dụ: Kế toán (`KeToan`) chỉ thấy menu Hóa đơn và Báo cáo, không thấy menu Quản lý nhân viên.
*   **Kỹ thuật Xử lý Lỗi (Exception Handling):**
    *   Trong tất cả các hàm Menu con (VD: `menuVehicleAndOwner()`), dự án sử dụng cấu trúc `try-catch` lồng trong vòng lặp `for`.
    *   **Logic nổi bật:** Khi `DAO` ném lỗi (Ví dụ: Trùng SĐT), khối `catch` sẽ bắt lỗi in ra màn hình và dùng lệnh `i--;` (Ví dụ dòng ~230) để lùi vòng lặp lại, cho phép khách hàng nhập lại thông tin mà không bị mất lượt.

---

## 3. PHÂN TÍCH TẦNG MODEL (THỰC THỂ & RÀNG BUỘC)
Nằm trong package `model/`. Các lớp ở đây áp dụng nghiêm ngặt 4 tính chất của OOP (Đóng gói, Kế thừa, Đa hình, Trừu tượng).

### 3.1. Nhóm Lớp Con Người (Person - Customer - Employee)
*   **`Person.java` (Lớp Trừu tượng - Abstract):**
    *   *Nhiệm vụ:* Khuôn mẫu cho mọi con người trong hệ thống. Khai báo thuộc tính `private` (Đóng gói).
    *   *Xử lý (Dòng ~98):* Hàm `nhapInfo(Scanner sc)` bọc vòng lặp `while(true)`. Regex tên yêu cầu có từ 2 ký tự và hỗ trợ dấu tiếng Việt (`[\\p{L}\\s]+`). Regex số điện thoại `^(03|05|07|08|09)\\d{8}$` ép buộc nhập đúng định dạng nhà mạng Việt Nam. Độ dài địa chỉ phải từ 5 ký tự trở lên.
*   **`Customer.java` (Kế thừa Person):**
    *   *Nhiệm vụ:* Lưu Chủ xe.
    *   *Xử lý (Dòng ~50):* Ghi đè (Override) hàm `nhapInfo`. Gọi `super.nhapInfo(sc)` để tái sử dụng code nhập Tên, SĐT, Địa chỉ. Bổ sung vòng lặp Regex kiểm tra định dạng email chặt chẽ bắt buộc có tên miền (`^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$`).
*   **`Employee.java` và Nhóm Lớp Kế thừa (`Mechanic`, `Owner`, `Accountant`, `Storekeeper`):**
    *   *Nhiệm vụ:* Quản lý nhân sự. Mở rộng thêm `username`, `password`, `salary`, `status`.
    *   `Mechanic.java` (Kỹ thuật viên): Bổ sung hàm nhập liệu bắt buộc `username`, `password` không được rỗng và `salary >= 0`.

### 3.2. Nhóm Lớp Hạng Mục Sửa Chữa (HangMuc - LinhKien - DichVu)
*   **`HangMuc.java` (Lớp Trừu tượng - Abstract):**
    *   *Xử lý:* Định nghĩa hàm abstract `tinhThanhTien(int soLuong)`.
*   **`LinhKien.java` & `DichVu.java`:**
    *   *Tính Đa hình:* `LinhKien` triển khai hàm tính tiền = `Đơn giá * Số lượng`. Trong khi đó, `DichVu` (Tính công trọn gói) chỉ trả về `= Đơn giá`.
    *   *Ràng buộc (Validation):* Ép kiểu `Double.parseDouble(sc.nextLine())` trong khối `try-catch` để chặn lỗi nhập chữ vào ô nhập tiền.

### 3.3. Nhóm Lớp Xe và Nghiệp vụ (`Vehicle`, `RepairOrder`, `Invoice`)
*   **`Vehicle.java` (Xe Ô tô):**
    *   *Xử lý (Dòng ~218):* Tích hợp bộ quy tắc phân loại 5 loại Biển số xe VN (Trắng, Vàng, Đỏ...) bằng Regex cực kỳ chi tiết. Bắt buộc ID Chủ xe (owner) phải là số nguyên dương lớn hơn 0.
*   **`RepairOrder.java` (Phiếu sửa chữa) & `Invoice.java` (Hóa đơn):**
    *   Sử dụng `SimpleDateFormat` để ép kiểu và parse chuỗi ngày tháng (yyyy-MM-dd HH:mm:ss). Nếu người dùng bỏ trống, tự động cấp phát `new Date()` (lấy thời gian hiện tại).

---

## 4. PHÂN TÍCH TẦNG CONTROLLER & DAO (DATABASE & NGHIỆP VỤ)
Nằm trong package `controller/`. Các lớp DAO implement Interface `IRepository<T>` (Sử dụng Generic). Sử dụng `PreparedStatement` để chống SQL Injection.

### 4.1. `ChuXeDAO.java` & `XeDAO.java` (Quản lý Khách hàng - Xe)
*   **Thêm mới (`themMoi`):** (Dòng ~23 `ChuXeDAO.java`) 
    *   *Nghiệp vụ chặn trùng lắp:* Trước khi thực thi `INSERT`, hàm tạo lệnh `SELECT id FROM owners WHERE phone = ?`. Nếu tồn tại, lập tức `throw new Exception("Số điện thoại đã tồn tại!")`. Cấu trúc này đẩy ngoại lệ lên `ConsoleView` để xử lý lùi biến đếm `i--`.
*   **Xóa mềm (Soft Delete):** (Dòng ~102)
    *   Thực hiện kiểm tra khóa ngoại (Foreign Key). Nếu khách hàng đang có xe trong xưởng (`SELECT COUNT(*) FROM vehicles WHERE owner_id = ?`), ném lỗi từ chối xóa. Nếu an toàn, thực hiện lệnh `UPDATE owners SET is_deleted = TRUE`.

### 4.2. `PhieuSuaChuaDAO.java` & `LinhKienDAO.java` (Nghiệp vụ Cốt lõi)
*   **Tạo Phiếu (`themMoi`):** Khi tạo `RepairOrder`, DAO lập tức gọi sang `KyThuatVienDAO` để đổi trạng thái của người thợ máy (Mechanic) sang `Đang bận` (`UPDATE employees SET status = 'Đang bận'`). Trạng thái Phiếu là `RECEIVING`.
*   **Cập nhật và Xóa Phiếu:** Kiểm tra chặt chẽ, từ chối xóa phiếu nếu đã có Hóa đơn (`Invoice`), từ chối chỉnh sửa nếu phiếu đã hoàn tất (`COMPLETED`). Tự động tính toán lại trạng thái thợ máy (`Đang bận`/`Đang rảnh`) dựa trên tổng số xe thợ đó đang đảm nhiệm, tránh sai lệch trạng thái thực tế.
*   **Thêm/Xóa Hạng Mục Sửa Chữa (Giao dịch - Database Transaction):** 
    *   *Cơ chế cực kỳ quan trọng:* Tại hàm `themMoiChiTietVaTruKho` và `xoaChiTiet`, hệ thống thực thi lệnh `INSERT`/`DELETE` vào bảng `repair_order_details` và `UPDATE` giảm/tăng tồn kho trong bảng `linh_kien` cùng lúc.
    *   *Thực thi trong code:* Tắt tính năng tự động lưu (`conn.setAutoCommit(false)`). Nếu có lỗi (Ví dụ kho hết hàng, ném `PartOutOfStockException`), thực thi `conn.rollback()` để hoàn tác mọi thay đổi. Nếu thành công 100%, thực thi `conn.commit()`.

### 4.3. `ReportController.java` (Báo cáo Thống kê)
*   *Nhiệm vụ:* Đảm nhiệm vai trò Thống kê doanh thu, TOP xe, TOP thợ.
*   *Thực thi (Ví dụ `getRevenueByPeriod`):* (Dòng ~31). Sử dụng cấu trúc lệnh SQL `SELECT SUM(total_amount) ... FROM invoices WHERE payment_date BETWEEN ? AND ?`. Truyền hai mốc thời gian (Start Date và End Date) vào để DB tự tính toán và trả về con số.
*   *Thực thi (Ví dụ `getMostRepairedVehicles`):* (Dòng ~87). Dùng lệnh SQL `JOIN` bảng `repair_orders` với bảng `vehicles`, kết hợp mệnh đề `GROUP BY` biển số xe và `ORDER BY COUNT(...) DESC LIMIT 5` để lấy ra top 5 xe sửa nhiều nhất. Dữ liệu được đưa vào cấu trúc `LinkedHashMap` để giữ nguyên thứ tự sắp xếp và in ra màn hình.

---

## 5. LUỒNG THỰC THI (FLOW) MỘT NGHIỆP VỤ HOÀN CHỈNH
**Ví dụ: Khách hàng vào sửa xe và Thanh toán.**
1. **View:** Lễ tân chọn Menu 2 (Quản lý Xe). Gõ thông tin xe.
2. **Model:** `Vehicle.nhapInfo()` ép lễ tân gõ đúng chuẩn biển số VN, năm sản xuất hợp lệ.
3. **Controller:** `XeDAO.themMoi()` lưu xe vào DB.
4. **View:** Lễ tân chọn Menu 4 (Phiếu Sửa Chữa), tạo phiếu.
5. **Controller:** `PhieuSuaChuaDAO` tạo phiếu, đổi trạng thái thợ máy sang bận.
6. **View:** Thợ máy chọn thêm linh kiện "Nhớt Castrol" vào phiếu.
7. **Controller:** `PhieuSuaChuaDAO` dùng Transaction, trừ kho "Nhớt Castrol", thêm vào chi tiết phiếu.
8. **View:** Xe sửa xong, chọn Menu 5 (Hóa Đơn), nhập mã phiếu vừa hoàn thành.
9. **Controller:** `HoaDonDAO.themMoi()` quét toàn bộ chi tiết, phân tách tổng tiền linh kiện (totalPartCost) và tiền công dịch vụ (totalLaborCost), cộng thêm 10% VAT, lưu vào bảng `invoices`. Trả thợ máy về trạng thái "Đang rảnh". 
10. **Kết thúc.** Dữ liệu đã an toàn trên CSDL MySQL.
=======
# PHÂN TÍCH CHUYÊN SÂU DỰ ÁN QUẢN LÝ GARA Ô TÔ
**Thư mục gốc:** `d:\oop_java_cuoiky\QuanLyGara`

Tài liệu này phân tích chi tiết từng lớp, từng phương thức, cơ chế hoạt động và cách các thành phần tương tác với nhau trong dự án dựa trên mô hình **MVC kết hợp DAO Pattern**.

---

## 1. TỔNG QUAN KIẾN TRÚC & DATABASE
Dự án được chia thành 3 package chính:
*   `model`: Chứa các thực thể (Entity) đại diện cho dữ liệu thực tế và các quy tắc kiểm duyệt đầu vào (Validation).
*   `controller`: Đóng vai trò là Tầng DAO (Data Access Object) xử lý logic nghiệp vụ phức tạp, truy xuất CSDL MySQL.
*   `view`: Chứa `ConsoleView.java`, quản lý giao diện dòng lệnh, điều hướng Menu và tương tác với người dùng.

**Kết nối CSDL:** Xử lý qua file `controller/DBConnection.java`. Sử dụng JDBC kết nối tới MySQL `jdbc:mysql://localhost:3306/quangara`.

---

## 2. PHÂN TÍCH TẦNG VIEW (GIAO DIỆN)
**File:** `view/ConsoleView.java` (Dài hơn 1000 dòng).
**Nhiệm vụ:** Điểm khởi chạy của chương trình (chứa hàm `main`), quản lý đăng nhập và hệ thống Menu.

*   **Hệ thống Đăng nhập (Dòng ~38 - 85):** 
    *   Hàm `login()` sử dụng vòng lặp `while(true)`. 
    *   Thực thi: Nhận username/password từ người dùng -> Gọi `employeeController.layTheoUsername(username)` -> So sánh mật khẩu -> Phân quyền dựa trên thuộc tính `role`. 
    *   Kết quả: Biến toàn cục `currentUser` lưu trữ thông tin phiên làm việc.
*   **Menu Động (Dòng ~90 - 150):** 
    *   Hàm `showMainMenu()` hiển thị các chức năng tùy thuộc vào `currentUser.getRole()`. Ví dụ: Kế toán (`KeToan`) chỉ thấy menu Hóa đơn và Báo cáo, không thấy menu Quản lý nhân viên.
*   **Kỹ thuật Xử lý Lỗi (Exception Handling):**
    *   Trong tất cả các hàm Menu con (VD: `menuVehicleAndOwner()`), dự án sử dụng cấu trúc `try-catch` lồng trong vòng lặp `for`.
    *   **Logic nổi bật:** Khi `DAO` ném lỗi (Ví dụ: Trùng SĐT), khối `catch` sẽ bắt lỗi in ra màn hình và dùng lệnh `i--;` (Ví dụ dòng ~230) để lùi vòng lặp lại, cho phép khách hàng nhập lại thông tin mà không bị mất lượt.

---

## 3. PHÂN TÍCH TẦNG MODEL (THỰC THỂ & RÀNG BUỘC)
Nằm trong package `model/`. Các lớp ở đây áp dụng nghiêm ngặt 4 tính chất của OOP (Đóng gói, Kế thừa, Đa hình, Trừu tượng).

### 3.1. Nhóm Lớp Con Người (Person - Customer - Employee)
*   **`Person.java` (Lớp Trừu tượng - Abstract):**
    *   *Nhiệm vụ:* Khuôn mẫu cho mọi con người trong hệ thống. Khai báo thuộc tính `private` (Đóng gói).
    *   *Xử lý (Dòng ~98):* Hàm `nhapInfo(Scanner sc)` bọc vòng lặp `while(true)`. Regex số điện thoại `^(03|05|07|08|09)\d{8}$` ép buộc nhập đúng định dạng nhà mạng Việt Nam.
*   **`Customer.java` (Kế thừa Person):**
    *   *Nhiệm vụ:* Lưu Chủ xe.
    *   *Xử lý (Dòng ~50):* Ghi đè (Override) hàm `nhapInfo`. Gọi `super.nhapInfo(sc)` để tái sử dụng code nhập Tên, SĐT, Địa chỉ. Bổ sung vòng lặp Regex kiểm tra định dạng email `^[A-Za-z0-9+_.-]+@(.+)$`.
*   **`Employee.java` và Nhóm Lớp Kế thừa (`Mechanic`, `Owner`, `Accountant`, `Storekeeper`):**
    *   *Nhiệm vụ:* Quản lý nhân sự. Mở rộng thêm `username`, `password`, `salary`, `status`.
    *   `Mechanic.java` (Kỹ thuật viên): Bổ sung hàm nhập liệu bắt buộc `username`, `password` không được rỗng và `salary >= 0`.

### 3.2. Nhóm Lớp Hạng Mục Sửa Chữa (HangMuc - LinhKien - DichVu)
*   **`HangMuc.java` (Lớp Trừu tượng - Abstract):**
    *   *Xử lý:* Định nghĩa hàm abstract `tinhThanhTien(int soLuong)`.
*   **`LinhKien.java` & `DichVu.java`:**
    *   *Tính Đa hình:* `LinhKien` triển khai hàm tính tiền = `Đơn giá * Số lượng`. Trong khi đó, `DichVu` (Tính công trọn gói) chỉ trả về `= Đơn giá`.
    *   *Ràng buộc (Validation):* Ép kiểu `Double.parseDouble(sc.nextLine())` trong khối `try-catch` để chặn lỗi nhập chữ vào ô nhập tiền.

### 3.3. Nhóm Lớp Xe và Nghiệp vụ (`Vehicle`, `RepairOrder`, `Invoice`)
*   **`Vehicle.java` (Xe Ô tô):**
    *   *Xử lý (Dòng ~218):* Tích hợp bộ quy tắc phân loại 5 loại Biển số xe VN (Trắng, Vàng, Đỏ...) bằng Regex cực kỳ chi tiết. Bắt buộc ID Chủ xe (owner) phải là số nguyên dương lớn hơn 0.
*   **`RepairOrder.java` (Phiếu sửa chữa) & `Invoice.java` (Hóa đơn):**
    *   Sử dụng `SimpleDateFormat` để ép kiểu và parse chuỗi ngày tháng (yyyy-MM-dd HH:mm:ss). Nếu người dùng bỏ trống, tự động cấp phát `new Date()` (lấy thời gian hiện tại).

---

## 4. PHÂN TÍCH TẦNG CONTROLLER & DAO (DATABASE & NGHIỆP VỤ)
Nằm trong package `controller/`. Các lớp DAO implement Interface `IRepository<T>` (Sử dụng Generic). Sử dụng `PreparedStatement` để chống SQL Injection.

### 4.1. `ChuXeDAO.java` & `XeDAO.java` (Quản lý Khách hàng - Xe)
*   **Thêm mới (`themMoi`):** (Dòng ~23 `ChuXeDAO.java`) 
    *   *Nghiệp vụ chặn trùng lắp:* Trước khi thực thi `INSERT`, hàm tạo lệnh `SELECT id FROM owners WHERE phone = ?`. Nếu tồn tại, lập tức `throw new Exception("Số điện thoại đã tồn tại!")`. Cấu trúc này đẩy ngoại lệ lên `ConsoleView` để xử lý lùi biến đếm `i--`.
*   **Xóa mềm (Soft Delete):** (Dòng ~102)
    *   Thực hiện kiểm tra khóa ngoại (Foreign Key). Nếu khách hàng đang có xe trong xưởng (`SELECT COUNT(*) FROM vehicles WHERE owner_id = ?`), ném lỗi từ chối xóa. Nếu an toàn, thực hiện lệnh `UPDATE owners SET is_deleted = TRUE`.

### 4.2. `PhieuSuaChuaDAO.java` & `LinhKienDAO.java` (Nghiệp vụ Cốt lõi)
*   **Tạo Phiếu (`themMoi`):** Khi tạo `RepairOrder`, DAO lập tức gọi sang `KyThuatVienDAO` để đổi trạng thái của người thợ máy (Mechanic) sang `Đang bận` (`UPDATE employees SET status = 'Đang bận'`). Trạng thái Phiếu là `RECEIVING`.
*   **Thêm Hạng Mục Sửa Chữa (Giao dịch - Database Transaction):** 
    *   *Cơ chế cực kỳ quan trọng:* Tại hàm `addDetail` của phiếu sửa chữa, hệ thống thực thi 2 lệnh SQL: (1) `INSERT` vào bảng `repair_order_details` và (2) `UPDATE` giảm trừ tồn kho trong bảng `parts` (Linh kiện).
    *   *Thực thi trong code:* Tắt tính năng tự động lưu (`conn.setAutoCommit(false)`). Nếu có lỗi (Ví dụ kho hết hàng), thực thi `conn.rollback()` để hoàn tác mọi thay đổi. Nếu thành công 100%, thực thi `conn.commit()`.

### 4.3. `ReportController.java` (Báo cáo Thống kê)
*   *Nhiệm vụ:* Đảm nhiệm vai trò Thống kê doanh thu, TOP xe, TOP thợ.
*   *Thực thi (Ví dụ `getRevenueByPeriod`):* (Dòng ~31). Sử dụng cấu trúc lệnh SQL `SELECT SUM(total_amount) ... FROM invoices WHERE payment_date BETWEEN ? AND ?`. Truyền hai mốc thời gian (Start Date và End Date) vào để DB tự tính toán và trả về con số.
*   *Thực thi (Ví dụ `getMostRepairedVehicles`):* (Dòng ~87). Dùng lệnh SQL `JOIN` bảng `repair_orders` với bảng `vehicles`, kết hợp mệnh đề `GROUP BY` biển số xe và `ORDER BY COUNT(...) DESC LIMIT 5` để lấy ra top 5 xe sửa nhiều nhất. Dữ liệu được đưa vào cấu trúc `LinkedHashMap` để giữ nguyên thứ tự sắp xếp và in ra màn hình.

---

## 5. LUỒNG THỰC THI (FLOW) MỘT NGHIỆP VỤ HOÀN CHỈNH
**Ví dụ: Khách hàng vào sửa xe và Thanh toán.**
1. **View:** Lễ tân chọn Menu 2 (Quản lý Xe). Gõ thông tin xe.
2. **Model:** `Vehicle.nhapInfo()` ép lễ tân gõ đúng chuẩn biển số VN, năm sản xuất hợp lệ.
3. **Controller:** `XeDAO.themMoi()` lưu xe vào DB.
4. **View:** Lễ tân chọn Menu 4 (Phiếu Sửa Chữa), tạo phiếu.
5. **Controller:** `PhieuSuaChuaDAO` tạo phiếu, đổi trạng thái thợ máy sang bận.
6. **View:** Thợ máy chọn thêm linh kiện "Nhớt Castrol" vào phiếu.
7. **Controller:** `PhieuSuaChuaDAO` dùng Transaction, trừ kho "Nhớt Castrol", thêm vào chi tiết phiếu.
8. **View:** Xe sửa xong, chọn Menu 5 (Hóa Đơn), nhập mã phiếu vừa hoàn thành.
9. **Controller:** `HoaDonDAO.themMoi()` quét toàn bộ chi tiết, phân tách tổng tiền linh kiện (totalPartCost) và tiền công dịch vụ (totalLaborCost), cộng thêm 10% VAT, lưu vào bảng `invoices`. Trả thợ máy về trạng thái "Đang rảnh". 
10. **Kết thúc.** Dữ liệu đã an toàn trên CSDL MySQL.
>>>>>>> 439cf6e6bbb4328043699ead1d271f191832b748
