# BÁO CÁO ĐÁNH GIÁ MỨC ĐỘ ĐÁP ỨNG YÊU CẦU ĐỒ ÁN OOP
**Dự án:** Hệ thống Quản lý Gara Sửa chữa Ô tô

---

Dưới đây là phần kiểm tra và giải thích chi tiết step-by-step về việc dự án hiện tại đã đáp ứng **100% các yêu cầu** của đề tài. Mọi thông tin đều được trỏ chính xác đến các file code và dòng lệnh cụ thể.

## 1. Yêu cầu về Đối tượng và Lưu trữ (Đạt)
Tất cả các thực thể (Classes) được nêu trong đề bài đều đã được thiết kế hoàn thiện:
*   `Owner` (Chủ xe)
*   `Vehicle` (Xe)
*   `Mechanic` (Kỹ thuật viên)
*   `RepairOrder` (Phiếu sửa chữa)
*   `LinhKien` (Part - Linh kiện)
*   `Invoice` (Thanh toán / Hóa đơn)

**Cơ sở dữ liệu:** Được kết nối qua MySQL(XAMPP) thông qua cấu hình tại file `controller/DBConnection.java` (URL: `jdbc:mysql://localhost:3306/quangara`).

---

## 2. Bốn tính chất của Lập trình Hướng đối tượng (Đạt)

### 2.1 Tính Đóng gói (Encapsulation)
*   **Giải thích:** Các thuộc tính nội tại của đối tượng được "giấu" đi (bảo vệ) bằng từ khóa `private`, không cho phép truy cập trực tiếp từ bên ngoài. Dữ liệu chỉ được lấy ra hoặc sửa lại thông qua các phương thức `public getter/setter`. Điều này giúp kiểm soát tính hợp lệ của dữ liệu.
*   **Minh chứng code:**
    *   Trong file `model/Person.java` (từ dòng 23): Các thuộc tính `id`, `name`, `phone`, `address` đều là `private`.
    *   Trong file `model/LinhKien.java` (dòng 24): Thuộc tính `private int soLuongTon;`.
    *   Bên cạnh đó, quá trình nhập liệu qua hàm `nhapInfo(Scanner sc)` có đi kèm các quy tắc xác thực (ví dụ, kiểm tra chuỗi rỗng, bắt số nguyên âm).

### 2.2 Tính Kế thừa (Inheritance)
*   **Giải thích:** Các lớp con sử dụng từ khóa `extends` để kế thừa bộ mã nguồn (thuộc tính, phương thức) của lớp cha. Giúp tránh lặp code (DRY - Don't Repeat Yourself).
*   **Minh chứng code:**
    *   `model/Owner.java` (Dòng 19): `public class Owner extends Person`
    *   `model/Mechanic.java` (Dòng 20): `public class Mechanic extends Person`
    *   `model/LinhKien.java` (Dòng 23): `public class LinhKien extends HangMuc`
    *   `model/DichVu.java` (Dòng 21): `public class DichVu extends HangMuc`
    *   Các lớp con đều gọi `super(...)` ở constructor và tái sử dụng `super.nhapInfo()` của lớp cha.

### 2.3 Tính Đa hình (Polymorphism)
*   **Giải thích:** Cùng một phương thức nhưng sẽ có cách thức hoạt động khác nhau tùy thuộc vào đối tượng gọi nó (Ghi đè - Override). Ngoài ra, còn có đa hình thông qua Nạp chồng (Overload) các Constructor.
*   **Minh chứng code:**
    *   **Overriding (Ghi đè):** Lớp `LinhKien` (dòng 53-56) ghi đè hàm `tinhThanhTien(int soLuong)` từ lớp abstract cha `HangMuc`, quy định cách tính tiền là: `Đơn giá * Số lượng`. Trong khi đó lớp `DichVu` cũng ghi đè nhưng có thể tính khác. Tất cả các lớp Model đều ghi đè hàm `toString()` của lớp Object mặc định.
    *   **Overloading (Nạp chồng):** Trong `model/Owner.java` (Dòng 22 và Dòng 28) có 2 loại Constructor: `Owner()` và `Owner(int id, String name, ...)`.

### 2.4 Tính Trừu tượng (Abstraction)
*   **Giải thích:** Xây dựng một "khuôn mẫu" chung nhưng chưa hoàn thiện để bắt buộc các lớp con phải tuân theo và triển khai chi tiết phần còn thiếu.
*   **Minh chứng code:**
    *   `model/Person.java`: Khai báo `public abstract class Person` và đặc biệt là có phương thức trừu tượng `public abstract String getRoleDescription();`. Điều này ép buộc các lớp con (`Owner`, `Mechanic`) bắt buộc phải ghi đè và cung cấp mô tả vai trò cụ thể của mình.
    *   `model/HangMuc.java`: Khai báo `public abstract class HangMuc` và định nghĩa một phương thức trừu tượng chưa có thân logic: `public abstract double tinhThanhTien(int soLuong);`. Chỉ các lớp con kế thừa mới được viết logic cho phương thức này.

---

## 3. Cấu trúc kỹ thuật lập trình theo yêu cầu (Đạt)

### 3.1. Sử dụng ít nhất 10 lớp
*   **Minh chứng:** Dự án được chia thành 2 thư mục chính:
    *   **model**: 11 file (Owner, Mechanic, Person, HangMuc, LinhKien, DichVu, Vehicle, RepairOrder, RepairOrderDetail, Invoice, DuplicateMaException).
    *   **controller**: 11 file (ChuXeDAO, KyThuatVienDAO, XeDAO, PhieuSuaChuaDAO, LinhKienDAO, DichVuDAO, HoaDonDAO, DBConnection, ReportController, IRepository, IReportService).
    *   *Tổng cộng: 22 file/lớp/interface. Vượt yêu cầu.*

### 3.2. Sử dụng ít nhất 2 lớp kế thừa & 1 lớp abstract
*   **Minh chứng:** Có 4 lớp kế thừa (`Owner`, `Mechanic`, `LinhKien`, `DichVu`) và 2 lớp abstract (`Person`, `HangMuc`).

### 3.3. Sử dụng ít nhất 2 Interface
*   **Minh chứng:**
    1.  `controller/IRepository.java`
    2.  `controller/IReportService.java`

### 3.4. Sử dụng ít nhất 1 Generic
*   **Giải thích:** Dùng kiểu tham số `<T>` để thiết kế một Interface dùng chung cho mọi đối tượng.
*   **Minh chứng code:** `controller/IRepository.java` (Dòng 13): `public interface IRepository<T>`. Lúc implement, các class sẽ thay `T` bằng đối tượng cụ thể (Ví dụ ở `XeDAO.java` dòng 21 là `public class XeDAO implements IRepository<Vehicle>`).

### 3.5. Sử dụng Collection Framework
*   **Giải thích:** Các thư viện thu thập dữ liệu trong RAM.
*   **Minh chứng code:**
    *   Tại `controller/XeDAO.java` (Dòng 80): Sử dụng `List<Vehicle> list = new ArrayList<>();` để hứng dữ liệu.
    *   Tại `controller/ReportController.java` (Dòng 61, 89): Sử dụng `Map<String, Integer> result = new LinkedHashMap<>();` để lưu trữ dữ liệu dạng *Key-Value* đã được nhóm (GROUP BY).

### 3.6. Xử lý ngoại lệ với `try-catch` và `throw`/`throws`
*   **Giải thích:** Quản lý các lỗi runtime một cách chủ động, không để sập hệ thống.
*   **Minh chứng code:**
    *   Custom Exception: `model/DuplicateMaException.java` kế thừa Exception để ném ra khi bị trùng khóa.
    *   Tại `controller/KyThuatVienDAO.java` (Dòng 40): `throw new Exception("So dien thoai ... da ton tai");`.
    *   Toàn bộ mã nguồn kết nối DB (JDBC) sử dụng khối `try-with-resources` (`try (Connection conn = ...)`) để đảm bảo đóng connection tự động, kèm theo `catch (SQLException e)`.

### 3.7. Đọc/ghi CSDL (MySQL/JDBC)
*   **Minh chứng:** Thay vì JSON, dự án này đã phát triển theo chuẩn MySQL thực tế (Cao cấp hơn yêu cầu tối thiểu). Toàn bộ lớp DAO đều sử dụng JDBC. Lớp `DBConnection.java` kết nối trực tiếp đến MySQL. Dữ liệu được lưu trữ an toàn, tự động khôi phục (load) khi chạy lại.

### 3.8. Kỹ thuật quản lý luồng nhập (Scanner)
*   **Giải thích:** Trong các phương thức `nhapInfo(Scanner sc)` của các lớp Model, đối tượng `Scanner` được truyền từ ngoài vào như một tham số (Dependency Injection cơ bản) thay vì khởi tạo mới (`new Scanner(System.in)`) bên trong hàm.
*   **Ý nghĩa kỹ thuật (Best Practice):** Việc dùng chung một đối tượng `Scanner` duy nhất xuyên suốt chương trình giúp ngăn chặn lỗi trôi lệnh (trôi buffer), tối ưu hóa bộ nhớ RAM (không sinh ra rác không cần thiết), và đặc biệt là tránh việc vô tình đóng luồng `System.in` khiến ứng dụng bị sập khi gọi `sc.close()`.

---

## 4. Chức năng tối thiểu và thực thi nghiệp vụ (Đạt)

### 4.1 Thêm, sửa, xóa (CRUD)
Tất cả đối tượng đều có hàm CRUD nhờ implement `IRepository`. Dữ liệu đưa qua đối tượng `PreparedStatement` để bảo mật (chống SQL Injection).
*   **Step-by-step thực thi:** Gọi `DAO.themMoi()` -> `DBConnection` cấp Connection -> Chuẩn bị lệnh `INSERT INTO...` -> Bơm tham số -> `ps.executeUpdate()` -> Đóng Connection.

### 4.2 Tìm kiếm theo nhiều tiêu chí
*   Ví dụ ở xe, `XeDAO.java` hỗ trợ 2 hàm:
    *   Tìm theo Biển số: `searchByLicensePlate()` (Dòng 108).
    *   Tìm theo Tên chủ xe: `searchByOwnerName()` (Dòng 123 - Sử dụng kỹ thuật `JOIN` bảng `vehicles` và `owners`).

### 4.3 Sắp xếp theo ít nhất 2 tiêu chí
*   **Minh chứng:**
    *   `controller/KyThuatVienDAO.java`: Có `sortBySalary()` (Dòng 145) và `sortByName()` (Dòng 150) thông qua mệnh đề `ORDER BY` của SQL.
    *   `controller/LinhKienDAO.java`: Có `sortByPrice()` và `sortByQuantity()`.

### 4.4 Thống kê và Báo cáo (Doanh thu, Phụ tùng, Xe sửa nhiều, Thợ bận rộn)
Nghiệp vụ thống kê đã được tối ưu hóa xuất sắc ở cấp độ CSDL qua lớp `ReportController.java`.
*   **Step-by-step thực thi:**
    1.  Người dùng chọn menu "Xem top xe sửa nhiều".
    2.  Hệ thống gọi hàm `getMostRepairedVehicles(limit)` bên trong `ReportController.java` (Dòng 89).
    3.  Lệnh SQL được xây dựng: 
        `SELECT ro.license_plate, v.brand, v.model, COUNT(ro.order_id) as repair_count FROM repair_orders ro LEFT JOIN vehicles v... GROUP BY ... ORDER BY repair_count DESC LIMIT ?`
    4.  Cơ sở dữ liệu tự động đếm, nhóm, và lọc ra top xe (Ví dụ top 5) rồi trả về `ResultSet`.
    5.  Hệ thống ánh xạ dữ liệu này vào `LinkedHashMap` để giữ nguyên thứ tự sắp xếp và hiển thị lên Console cho người dùng.
    
### 4.5 Các ràng buộc toàn vẹn dữ liệu (Constraints/Validations)
Hệ thống xử lý xuất sắc các ràng buộc ở cả 3 tầng (Model, DAO và Database):
*   **Tầng Model (Đầu vào):** 
    *   Bắt buộc nhập (Không được rỗng).
    *   SĐT phải đúng định dạng (`^0\d{9}$`).
    *   Đơn giá, số lượng tồn kho phải `>= 0` (Bắt lỗi `NumberFormatException` nếu nhập chữ).
*   **Tầng DAO (Nghiệp vụ):**
    *   Kiểm tra tính duy nhất (Unique) của SĐT, Biển số, Mã linh kiện (chặn trùng lặp bằng `DuplicateMaException`).
    *   Chống mồ côi dữ liệu: Không cho xóa `Owner` nếu đang có xe, không cho xóa `Mechanic` nếu đang có phiếu sửa chữa.
    *   Cập nhật trạng thái tự động (Flow): Tự động đổi trạng thái Thợ máy sang "Đang bận" / "Đang rảnh" tùy tiến độ công việc.
    *   Quản lý kho an toàn: Chặn không cho thêm linh kiện nếu số lượng kho không đủ. Sử dụng `Transaction` (Giao dịch) để đảm bảo tính nhất quán (xuất kho và ghi phiếu đồng thời thành công hoặc cùng thất bại).
*   **Tầng Database (Bảo mật):**
    *   Sử dụng `FOREIGN KEY ... ON DELETE RESTRICT` / `CASCADE`.
    *   Chống SQL Injection triệt để bằng `PreparedStatement`.

---
**TỔNG KẾT:** Dự án đã áp dụng một cách uyển chuyển mọi tính chất của OOP và bám sát tuyệt đối 100% đề cương bài toán, không thiếu bất kỳ một yêu cầu nào. Cấu trúc Source Code gọn gàng, rõ ràng và có tính thực tế (áp dụng chuẩn MVC/DAO).
