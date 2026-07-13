# Giải Thích Các File Java Trong Project

Tài liệu này giải thích chi tiết chức năng, thuộc tính, phương thức và các liên kết của từng file `.java` trong thư mục `src`. Nó được tổ chức theo từng package (folder) trong dự án.

# Folder: `controller`

## `BaoCaoController.java`

- **Vị trí (Folder):** Nằm trong package `...controller`
- **Mục đích (Dùng để làm gì):** Lớp Controller điều khiển luồng dữ liệu, xử lý logic nghiệp vụ và kết nối giữa view và model.
- **Thuộc tính:**
  - Không có hoặc kế thừa từ lớp cha.
- **Phương thức:**
  - `Các phương thức getter/setter cho các thuộc tính.`
- **Liên kết đến file nào (Dependencies):**
  - Thường là các class chuẩn của Java.

---

## `ChuXeDAO.java`

- **Vị trí (Folder):** Nằm trong package `...controller`
- **Mục đích (Dùng để làm gì):** Lớp DAO (Data Access Object) chịu trách nhiệm tương tác với cơ sở dữ liệu, thực hiện các thao tác thêm, sửa, xóa, và truy vấn dữ liệu.
- **Thuộc tính:**
  - Không có hoặc kế thừa từ lớp cha.
- **Phương thức:**
  - `void themMoi(ChuXe ChuXe)`
  - `void capNhat(ChuXe ChuXe)`
  - `void xoa(Object id)`
  - `List<ChuXe> layTatCa()`
  - `ChuXe layTheoId(Object id)`
  - `List<ChuXe> searchByName(String keyword)`
  - `ChuXe mapRow(ResultSet rs)`
  - `List<ChuXe> layDanhSachDaXoa()`
  - `void khoiPhuc(int id)`
  - `void xoaVinhVien(int id)`
- **Liên kết đến file nào (Dependencies):**
  - `ChuXe.java`
  - `DuplicateMaException.java`

---

## `DichVuDAO.java`

- **Vị trí (Folder):** Nằm trong package `...controller`
- **Mục đích (Dùng để làm gì):** Lớp DAO (Data Access Object) chịu trách nhiệm tương tác với cơ sở dữ liệu, thực hiện các thao tác thêm, sửa, xóa, và truy vấn dữ liệu.
- **Thuộc tính:**
  - Không có hoặc kế thừa từ lớp cha.
- **Phương thức:**
  - `void themMoi(DichVu dv)`
  - `void capNhat(DichVu dv)`
  - `void xoa(Object id)`
  - `List<DichVu> layTatCa()`
  - `DichVu layTheoId(Object id)`
  - `List<DichVu> searchByName(String keyword)`
  - `List<DichVu> querySql(String sql, String param)`
  - `DichVu mapRow(ResultSet rs)`
- **Liên kết đến file nào (Dependencies):**
  - `DichVu.java`

---

## `HoaDonDAO.java`

- **Vị trí (Folder):** Nằm trong package `...controller`
- **Mục đích (Dùng để làm gì):** Lớp DAO (Data Access Object) chịu trách nhiệm tương tác với cơ sở dữ liệu, thực hiện các thao tác thêm, sửa, xóa, và truy vấn dữ liệu.
- **Thuộc tính:**
  - Không có hoặc kế thừa từ lớp cha.
- **Phương thức:**
  - `void themMoi(HoaDon invoice)`
  - `void capNhat(HoaDon invoice)`
  - `void xoa(Object id)`
  - `List<HoaDon> layTatCa()`
  - `HoaDon layTheoId(Object id)`
  - `HoaDon generateInvoice(int orderId)`
  - `List<HoaDon> querySql(String sql, Integer idParam)`
  - `HoaDon mapRow(ResultSet rs)`
  - `Các phương thức getter/setter cho các thuộc tính.`
- **Liên kết đến file nào (Dependencies):**
  - `ChiTietPhieuSua.java`
  - `HoaDon.java`

---

## `IBaoCaoService.java`

- **Vị trí (Folder):** Nằm trong package `...controller`
- **Mục đích (Dùng để làm gì):** Interface định nghĩa các phương thức (contract) cho các lớp dịch vụ hoặc kho lưu trữ triển khai.
- **Thuộc tính:**
  - Không có hoặc kế thừa từ lớp cha.
- **Phương thức:**
  - Không có.
- **Liên kết đến file nào (Dependencies):**
  - Thường là các class chuẩn của Java.

---

## `IKhoLuuTru.java`

- **Vị trí (Folder):** Nằm trong package `...controller`
- **Mục đích (Dùng để làm gì):** Interface định nghĩa các phương thức (contract) cho các lớp dịch vụ hoặc kho lưu trữ triển khai.
- **Thuộc tính:**
  - Không có hoặc kế thừa từ lớp cha.
- **Phương thức:**
  - Không có.
- **Liên kết đến file nào (Dependencies):**
  - Thường là các class chuẩn của Java.

---

## `KetNoiCSDL.java`

- **Vị trí (Folder):** Nằm trong package `...controller`
- **Mục đích (Dùng để làm gì):** Lớp tiện ích cung cấp các hàm dùng chung (static) như kết nối CSDL, định dạng dữ liệu, xử lý chuỗi.
- **Thuộc tính:**
  - `String URL`
  - `String USER`
  - `String PASSWORD`
- **Phương thức:**
  - `Các phương thức getter/setter cho các thuộc tính.`
- **Liên kết đến file nào (Dependencies):**
  - Thường là các class chuẩn của Java.

---

## `KiemTraCSDL.java`

- **Vị trí (Folder):** Nằm trong package `...controller`
- **Mục đích (Dùng để làm gì):** Lớp Model/Entity đại diện cho thực thể KiemTraCSDL, chứa thông tin và các phương thức truy cập dữ liệu (getter/setter).
- **Thuộc tính:**
  - Không có hoặc kế thừa từ lớp cha.
- **Phương thức:**
  - `void main(String[] args)`
- **Liên kết đến file nào (Dependencies):**
  - Thường là các class chuẩn của Java.

---

## `KyThuatVienDAO.java`

- **Vị trí (Folder):** Nằm trong package `...controller`
- **Mục đích (Dùng để làm gì):** Lớp DAO (Data Access Object) chịu trách nhiệm tương tác với cơ sở dữ liệu, thực hiện các thao tác thêm, sửa, xóa, và truy vấn dữ liệu.
- **Thuộc tính:**
  - Không có hoặc kế thừa từ lớp cha.
- **Phương thức:**
  - `void themMoi(KyThuatVien mechanic)`
  - `void capNhat(KyThuatVien mechanic)`
  - `void xoa(Object id)`
  - `List<KyThuatVien> layTatCa()`
  - `KyThuatVien layTheoId(Object id)`
  - `List<KyThuatVien> sortBySalary(boolean ascending)`
  - `List<KyThuatVien> sortByName()`
  - `List<KyThuatVien> querySql(String sql, Integer idParam)`
  - `KyThuatVien mapRow(ResultSet rs)`
- **Liên kết đến file nào (Dependencies):**
  - `DuplicateMaException.java`
  - `KyThuatVien.java`

---

## `LinhKienDAO.java`

- **Vị trí (Folder):** Nằm trong package `...controller`
- **Mục đích (Dùng để làm gì):** Lớp DAO (Data Access Object) chịu trách nhiệm tương tác với cơ sở dữ liệu, thực hiện các thao tác thêm, sửa, xóa, và truy vấn dữ liệu.
- **Thuộc tính:**
  - Không có hoặc kế thừa từ lớp cha.
- **Phương thức:**
  - `void themMoi(LinhKien lk)`
  - `void capNhat(LinhKien lk)`
  - `void xoa(Object id)`
  - `List<LinhKien> layTatCa()`
  - `LinhKien layTheoId(Object id)`
  - `List<LinhKien> searchByName(String keyword)`
  - `List<LinhKien> searchByPriceRange(double minPrice, double maxPrice)`
  - `void deductQuantity(String ma, int qtyToDeduct)`
  - `List<LinhKien> sortByPrice(boolean ascending)`
  - `List<LinhKien> sortByQuantity(boolean ascending)`
  - `List<LinhKien> querySql(String sql, String param)`
  - `LinhKien mapRow(ResultSet rs)`
- **Liên kết đến file nào (Dependencies):**
  - `LinhKien.java`
  - `LinhKienHetHangException.java`

---

## `NhanVienDAO.java`

- **Vị trí (Folder):** Nằm trong package `...controller`
- **Mục đích (Dùng để làm gì):** Lớp DAO (Data Access Object) chịu trách nhiệm tương tác với cơ sở dữ liệu, thực hiện các thao tác thêm, sửa, xóa, và truy vấn dữ liệu.
- **Thuộc tính:**
  - Không có hoặc kế thừa từ lớp cha.
- **Phương thức:**
  - `NhanVien login(String username, String password)`
  - `boolean updatePassword(String username, String newPassword)`
  - `Các phương thức getter/setter cho các thuộc tính.`
- **Liên kết đến file nào (Dependencies):**
  - `NhanVien.java`

---

## `PhieuSuaChuaDAO.java`

- **Vị trí (Folder):** Nằm trong package `...controller`
- **Mục đích (Dùng để làm gì):** Lớp DAO (Data Access Object) chịu trách nhiệm tương tác với cơ sở dữ liệu, thực hiện các thao tác thêm, sửa, xóa, và truy vấn dữ liệu.
- **Thuộc tính:**
  - Không có hoặc kế thừa từ lớp cha.
- **Phương thức:**
  - `void themMoi(PhieuSuaChua order)`
  - `void capNhat(PhieuSuaChua order)`
  - `void xoa(Object id)`
  - `void updateMechanicStatusSafe(int mechanicId)`
  - `List<PhieuSuaChua> layTatCa()`
  - `PhieuSuaChua layTheoId(Object id)`
  - `void addDetail(ChiTietPhieuSua detail)`
  - `void xoaChiTiet(int orderId, String maHangMuc)`
  - `void themMoiChiTietVaTruKho(ChiTietPhieuSua detail)`
  - `PhieuSuaChua mapRow(ResultSet rs)`
  - `Các phương thức getter/setter cho các thuộc tính.`
- **Liên kết đến file nào (Dependencies):**
  - `ChiTietPhieuSua.java`
  - `DichVu.java`
  - `HangMuc.java`
  - `KyThuatVien.java`
  - `LinhKien.java`
  - `LinhKienHetHangException.java`
  - `PhieuSuaChua.java`

---

## `XeDAO.java`

- **Vị trí (Folder):** Nằm trong package `...controller`
- **Mục đích (Dùng để làm gì):** Lớp DAO (Data Access Object) chịu trách nhiệm tương tác với cơ sở dữ liệu, thực hiện các thao tác thêm, sửa, xóa, và truy vấn dữ liệu.
- **Thuộc tính:**
  - Không có hoặc kế thừa từ lớp cha.
- **Phương thức:**
  - `void themMoi(Xe vehicle)`
  - `void capNhat(Xe vehicle)`
  - `void xoa(Object id)`
  - `List<Xe> layTatCa()`
  - `Xe layTheoId(Object id)`
  - `List<Xe> searchByLicensePlate(String lp)`
  - `List<Xe> searchByOwnerName(String ownerName)`
  - `Xe mapRow(ResultSet rs)`
  - `List<Xe> layDanhSachDaXoa()`
  - `void khoiPhuc(String lp)`
  - `void xoaVinhVien(String lp)`
- **Liên kết đến file nào (Dependencies):**
  - `BienSoKhongHopLeException.java`
  - `KhongTimThayXeException.java`
  - `Xe.java`

---

# Folder: `exception`

## `BienSoKhongHopLeException.java`

- **Vị trí (Folder):** Nằm trong package `...exception`
- **Mục đích (Dùng để làm gì):** Lớp ngoại lệ tùy chỉnh, dùng để xử lý các lỗi nghiệp vụ cụ thể trong ứng dụng.
- **Thuộc tính:**
  - Không có hoặc kế thừa từ lớp cha.
- **Phương thức:**
  - Không có.
- **Liên kết đến file nào (Dependencies):**
  - Thường là các class chuẩn của Java.

---

## `KhongTimThayXeException.java`

- **Vị trí (Folder):** Nằm trong package `...exception`
- **Mục đích (Dùng để làm gì):** Lớp ngoại lệ tùy chỉnh, dùng để xử lý các lỗi nghiệp vụ cụ thể trong ứng dụng.
- **Thuộc tính:**
  - Không có hoặc kế thừa từ lớp cha.
- **Phương thức:**
  - Không có.
- **Liên kết đến file nào (Dependencies):**
  - Thường là các class chuẩn của Java.

---

## `LinhKienHetHangException.java`

- **Vị trí (Folder):** Nằm trong package `...exception`
- **Mục đích (Dùng để làm gì):** Lớp ngoại lệ tùy chỉnh, dùng để xử lý các lỗi nghiệp vụ cụ thể trong ứng dụng.
- **Thuộc tính:**
  - Không có hoặc kế thừa từ lớp cha.
- **Phương thức:**
  - Không có.
- **Liên kết đến file nào (Dependencies):**
  - Thường là các class chuẩn của Java.

---

# Folder: `model`

## `ChiTietPhieuSua.java`

- **Vị trí (Folder):** Nằm trong package `...model`
- **Mục đích (Dùng để làm gì):** Lớp Model/Entity đại diện cho thực thể ChiTietPhieuSua, chứa thông tin và các phương thức truy cập dữ liệu (getter/setter).
- **Thuộc tính:**
  - `int orderId`
  - `String maHangMuc`
  - `String loaiHangMuc`
  - `double donGiaThucTe`
  - `int soLuong`
- **Phương thức:**
  - `void nhapInfo(Scanner sc)`
  - `Các phương thức getter/setter cho các thuộc tính.`
- **Liên kết đến file nào (Dependencies):**
  - Thường là các class chuẩn của Java.

---

## `ChuXe.java`

- **Vị trí (Folder):** Nằm trong package `...model`
- **Mục đích (Dùng để làm gì):** Lớp Model/Entity đại diện cho thực thể ChuXe, chứa thông tin và các phương thức truy cập dữ liệu (getter/setter).
- **Thuộc tính:**
  - `String email`
- **Phương thức:**
  - `void nhapInfo(Scanner sc)`
  - `Các phương thức getter/setter cho các thuộc tính.`
- **Liên kết đến file nào (Dependencies):**
  - Thường là các class chuẩn của Java.

---

## `DichVu.java`

- **Vị trí (Folder):** Nằm trong package `...model`
- **Mục đích (Dùng để làm gì):** Lớp Model/Entity đại diện cho thực thể DichVu, chứa thông tin và các phương thức truy cập dữ liệu (getter/setter).
- **Thuộc tính:**
  - Không có hoặc kế thừa từ lớp cha.
- **Phương thức:**
  - `double tinhThanhTien(int soLuong)`
  - `void nhapInfo(Scanner sc)`
- **Liên kết đến file nào (Dependencies):**
  - Thường là các class chuẩn của Java.

---

## `DuplicateMaException.java`

- **Vị trí (Folder):** Nằm trong package `...model`
- **Mục đích (Dùng để làm gì):** Lớp ngoại lệ tùy chỉnh, dùng để xử lý các lỗi nghiệp vụ cụ thể trong ứng dụng.
- **Thuộc tính:**
  - Không có hoặc kế thừa từ lớp cha.
- **Phương thức:**
  - Không có.
- **Liên kết đến file nào (Dependencies):**
  - Thường là các class chuẩn của Java.

---

## `HangMuc.java`

- **Vị trí (Folder):** Nằm trong package `...model`
- **Mục đích (Dùng để làm gì):** Lớp Model/Entity đại diện cho thực thể HangMuc, chứa thông tin và các phương thức truy cập dữ liệu (getter/setter).
- **Thuộc tính:**
  - `String ma`
  - `String ten`
  - `double donGia`
- **Phương thức:**
  - `void nhapInfo(Scanner sc)`
  - `double tinhThanhTien(int soLuong)`
  - `Các phương thức getter/setter cho các thuộc tính.`
- **Liên kết đến file nào (Dependencies):**
  - Thường là các class chuẩn của Java.

---

## `HoaDon.java`

- **Vị trí (Folder):** Nằm trong package `...model`
- **Mục đích (Dùng để làm gì):** Lớp Model/Entity đại diện cho thực thể HoaDon, chứa thông tin và các phương thức truy cập dữ liệu (getter/setter).
- **Thuộc tính:**
  - `int invoiceId`
  - `PhieuSuaChua repairOrder`
  - `Date paymentDate`
  - `double totalPartCost`
  - `double totalLaborCost`
  - `double vatRate`
  - `double totalAmount`
  - `SimpleDateFormat sdf`
- **Phương thức:**
  - `void calculateTotal()`
  - `void printInvoice()`
  - `void nhapInfo(Scanner sc)`
  - `Các phương thức getter/setter cho các thuộc tính.`
- **Liên kết đến file nào (Dependencies):**
  - `PhieuSuaChua.java`

---

## `KeToan.java`

- **Vị trí (Folder):** Nằm trong package `...model`
- **Mục đích (Dùng để làm gì):** Lớp Model/Entity đại diện cho thực thể KeToan, chứa thông tin và các phương thức truy cập dữ liệu (getter/setter).
- **Thuộc tính:**
  - Không có hoặc kế thừa từ lớp cha.
- **Phương thức:**
  - `Các phương thức getter/setter cho các thuộc tính.`
- **Liên kết đến file nào (Dependencies):**
  - Thường là các class chuẩn của Java.

---

## `KyThuatVien.java`

- **Vị trí (Folder):** Nằm trong package `...model`
- **Mục đích (Dùng để làm gì):** Lớp Model/Entity đại diện cho thực thể KyThuatVien, chứa thông tin và các phương thức truy cập dữ liệu (getter/setter).
- **Thuộc tính:**
  - `String spec`
- **Phương thức:**
  - `void nhapInfo(Scanner sc)`
  - `Các phương thức getter/setter cho các thuộc tính.`
- **Liên kết đến file nào (Dependencies):**
  - Thường là các class chuẩn của Java.

---

## `LinhKien.java`

- **Vị trí (Folder):** Nằm trong package `...model`
- **Mục đích (Dùng để làm gì):** Lớp Model/Entity đại diện cho thực thể LinhKien, chứa thông tin và các phương thức truy cập dữ liệu (getter/setter).
- **Thuộc tính:**
  - `int soLuongTon`
  - `String location`
- **Phương thức:**
  - `double tinhThanhTien(int soLuong)`
  - `void nhapInfo(Scanner sc)`
  - `Các phương thức getter/setter cho các thuộc tính.`
- **Liên kết đến file nào (Dependencies):**
  - Thường là các class chuẩn của Java.

---

## `Nguoi.java`

- **Vị trí (Folder):** Nằm trong package `...model`
- **Mục đích (Dùng để làm gì):** Lớp Model/Entity đại diện cho thực thể Nguoi, chứa thông tin và các phương thức truy cập dữ liệu (getter/setter).
- **Thuộc tính:**
  - `int id`
  - `String name`
  - `String phone`
  - `String address`
  - `boolean isDeleted`
- **Phương thức:**
  - `boolean isDeleted()`
  - `void nhapInfo(Scanner sc)`
  - `Các phương thức getter/setter cho các thuộc tính.`
- **Liên kết đến file nào (Dependencies):**
  - Thường là các class chuẩn của Java.

---

## `NhanVien.java`

- **Vị trí (Folder):** Nằm trong package `...model`
- **Mục đích (Dùng để làm gì):** Lớp Model/Entity đại diện cho thực thể NhanVien, chứa thông tin và các phương thức truy cập dữ liệu (getter/setter).
- **Thuộc tính:**
  - `String username`
  - `String password`
  - `String role`
  - `double salary`
  - `String status`
- **Phương thức:**
  - `Các phương thức getter/setter cho các thuộc tính.`
- **Liên kết đến file nào (Dependencies):**
  - Thường là các class chuẩn của Java.

---

## `PhieuSuaChua.java`

- **Vị trí (Folder):** Nằm trong package `...model`
- **Mục đích (Dùng để làm gì):** Lớp Model/Entity đại diện cho thực thể PhieuSuaChua, chứa thông tin và các phương thức truy cập dữ liệu (getter/setter).
- **Thuộc tính:**
  - `int orderId`
  - `Xe vehicle`
  - `Date entryDate`
  - `Date exitDate`
  - `KyThuatVien mechanic`
  - `String status`
  - `String visualCondition`
  - `SimpleDateFormat sdf`
- **Phương thức:**
  - `void addDetail(ChiTietPhieuSua detail)`
  - `void nhapInfo(Scanner sc)`
  - `Các phương thức getter/setter cho các thuộc tính.`
- **Liên kết đến file nào (Dependencies):**
  - `ChiTietPhieuSua.java`
  - `KyThuatVien.java`
  - `Xe.java`

---

## `QuanLy.java`

- **Vị trí (Folder):** Nằm trong package `...model`
- **Mục đích (Dùng để làm gì):** Lớp Model/Entity đại diện cho thực thể QuanLy, chứa thông tin và các phương thức truy cập dữ liệu (getter/setter).
- **Thuộc tính:**
  - Không có hoặc kế thừa từ lớp cha.
- **Phương thức:**
  - `Các phương thức getter/setter cho các thuộc tính.`
- **Liên kết đến file nào (Dependencies):**
  - Thường là các class chuẩn của Java.

---

## `ThuKho.java`

- **Vị trí (Folder):** Nằm trong package `...model`
- **Mục đích (Dùng để làm gì):** Lớp Model/Entity đại diện cho thực thể ThuKho, chứa thông tin và các phương thức truy cập dữ liệu (getter/setter).
- **Thuộc tính:**
  - Không có hoặc kế thừa từ lớp cha.
- **Phương thức:**
  - `Các phương thức getter/setter cho các thuộc tính.`
- **Liên kết đến file nào (Dependencies):**
  - Thường là các class chuẩn của Java.

---

## `Xe.java`

- **Vị trí (Folder):** Nằm trong package `...model`
- **Mục đích (Dùng để làm gì):** Lớp Model/Entity đại diện cho thực thể Xe, chứa thông tin và các phương thức truy cập dữ liệu (getter/setter).
- **Thuộc tính:**
  - `String licensePlate`
  - `String brand`
  - `String model`
  - `int productionYear`
  - `ChuXe ChuXe`
  - `String color`
  - `String condition`
  - `boolean isDeleted`
- **Phương thức:**
  - `boolean isDeleted()`
  - `boolean isValidPlate(String inputPlate)`
  - `void nhapInfo(Scanner sc)`
  - `Các phương thức getter/setter cho các thuộc tính.`
- **Liên kết đến file nào (Dependencies):**
  - `ChuXe.java`

---

# Folder: `util`

## `CapNhatCSDL.java`

- **Vị trí (Folder):** Nằm trong package `...util`
- **Mục đích (Dùng để làm gì):** Lớp tiện ích cung cấp các hàm dùng chung (static) như kết nối CSDL, định dạng dữ liệu, xử lý chuỗi.
- **Thuộc tính:**
  - Không có hoặc kế thừa từ lớp cha.
- **Phương thức:**
  - `void main(String[] args)`
- **Liên kết đến file nào (Dependencies):**
  - `KetNoiCSDL.java`

---

## `DinhDangBang.java`

- **Vị trí (Folder):** Nằm trong package `...util`
- **Mục đích (Dùng để làm gì):** Lớp tiện ích cung cấp các hàm dùng chung (static) như kết nối CSDL, định dạng dữ liệu, xử lý chuỗi.
- **Thuộc tính:**
  - Không có hoặc kế thừa từ lớp cha.
- **Phương thức:**
  - `void printCustomers(List<ChuXe> list)`
  - `void printVehicles(List<Xe> list)`
  - `void printMechanics(List<KyThuatVien> list)`
  - `void printParts(List<LinhKien> list)`
  - `void printServices(List<DichVu> list)`
  - `void printRepairOrders(List<PhieuSuaChua> list)`
  - `void printInvoices(List<HoaDon> list)`
  - `String truncate(String str, int len)`
- **Liên kết đến file nào (Dependencies):**
  - `ChuXe.java`
  - `DichVu.java`
  - `HoaDon.java`
  - `KyThuatVien.java`
  - `LinhKien.java`
  - `PhieuSuaChua.java`
  - `Xe.java`

---

## `LuongNhapKhongDau.java`

- **Vị trí (Folder):** Nằm trong package `...util`
- **Mục đích (Dùng để làm gì):** Lớp tiện ích cung cấp các hàm dùng chung (static) như kết nối CSDL, định dạng dữ liệu, xử lý chuỗi.
- **Thuộc tính:**
  - `InputStream original`
  - `byte[] buffer`
  - `int bufferIndex`
- **Phương thức:**
  - `int read()`
  - `int read(byte[] b, int off, int len)`
  - `int fillBuffer()`
- **Liên kết đến file nào (Dependencies):**
  - Thường là các class chuẩn của Java.

---

## `TienIchChuoi.java`

- **Vị trí (Folder):** Nằm trong package `...util`
- **Mục đích (Dùng để làm gì):** Lớp tiện ích cung cấp các hàm dùng chung (static) như kết nối CSDL, định dạng dữ liệu, xử lý chuỗi.
- **Thuộc tính:**
  - Không có hoặc kế thừa từ lớp cha.
- **Phương thức:**
  - `String removeAccents(String str)`
  - `boolean hasAccents(String str)`
  - `String hashPassword(String password)`
- **Liên kết đến file nào (Dependencies):**
  - Thường là các class chuẩn của Java.

---

# Folder: `view`

## `GiaoDienConsole.java`

- **Vị trí (Folder):** Nằm trong package `...view`
- **Mục đích (Dùng để làm gì):** Lớp giao diện người dùng trên console, hiển thị menu và nhận đầu vào từ người dùng.
- **Thuộc tính:**
  - `Scanner sc`
  - `ChuXeDAO ownerController`
  - `XeDAO vehicleController`
  - `KyThuatVienDAO mechanicController`
  - `LinhKienDAO partController`
  - `PhieuSuaChuaDAO repairOrderController`
  - `HoaDonDAO invoiceController`
  - `BaoCaoController reportController`
  - `DichVuDAO dichVuDAO`
  - `NhanVienDAO employeeDAO`
  - `NhanVien currentUser`
- **Phương thức:**
  - `void main(String[] args)`
  - `void start()`
  - `void changePassword()`
  - `void showMainMenu()`
  - `void menuVehicleAndOwner()`
  - `void menuThungRac()`
  - `void menuMechanic()`
  - `void menuPart()`
  - `void menuRepairOrder()`
  - `void menuInvoice()`
  - `void menuReport()`
  - `void menuDichVu()`
- **Liên kết đến file nào (Dependencies):**
  - `BaoCaoController.java`
  - `ChuXeDAO.java`
  - `DichVuDAO.java`
  - `HoaDonDAO.java`
  - `KyThuatVienDAO.java`
  - `LinhKienDAO.java`
  - `NhanVien.java`
  - `NhanVienDAO.java`
  - `PhieuSuaChuaDAO.java`
  - `XeDAO.java`

---

