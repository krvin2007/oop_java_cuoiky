===========================================================================
                            CẤU TRÚC THƯ MỤC
===========================================================================
Quản Lý Gara
│
├── model/
│   ├── Person (abstract)
│   ├── Owner
│   ├── Mechanic
│   ├── HangMuc (abstract)
│   ├── LinhKien
│   ├── DichVu
│   ├── Vehicle
│   ├── RepairOrder
│   ├── RepairOrderDetail
│   ├── Invoice
│   └── DuplicateMaException
│
├── controller/
│   ├── IRepository<T> (interface)
│   ├── IReportService (interface)
│   ├── DBConnection
│   ├── ReportController
│   ├── ChuXeDAO
│   ├── KyThuatVienDAO
│   ├── XeDAO
│   ├── PhieuSuaChuaDAO
│   ├── LinhKienDAO
│   ├── DichVuDAO
│   └── HoaDonDAO
│
└── view/
    └── (Các lớp giao diện Menu Console / Giao diện người dùng / Main)


===========================================================================
                          PACKAGE: CONTROLLER
===========================================================================

+-------------------------------------------------------------------------+
|                              <<interface>>                              |
|                              IRepository<T>                             |
+-------------------------------------------------------------------------+
| + themMoi(item: T): void                                                |
| + capNhat(item: T): void                                                |
| + xoa(id: Object): void                                                 |
| + layTatCa(): List<T>                                                   |
| + layTheoId(id: Object): T                                              |
+-------------------------------------------------------------------------+
     ^                  ^                   ^
     .                  .                   . (implements - hiện thực hóa)
     .                  .                   .
+----------+      +------------+      +-----------+
| ChuXeDAO |      | KyThuatVien|      |  XeDAO    | ... (và các DAO khác)
+----------+      |    DAO     |      +-----------+
                  +------------+


===========================================================================
                              PACKAGE: MODEL
===========================================================================

+-------------------------------------------------------------------------+
|                               <<abstract>>                              |
|                                 Person                                  |
+-------------------------------------------------------------------------+
| - id: int                                                               |
| - name: String                                                          |
| - phone: String                                                         |
| - address: String                                                       |
+-------------------------------------------------------------------------+
| + Person()                                                              |
| + Person(id: int, name: String, phone: String, address: String)         |
| + getId(): int / + setId(id: int): void                                 |
| + getName(): String / + setName(name: String): void                     |
| + getPhone(): String / + setPhone(phone: String): void                  |
| + getAddress(): String / + setAddress(address: String): void            |
| + nhapInfo(sc: Scanner): void                                           |
| + toString(): String                                                    |
| + getRoleDescription(): String <<abstract>>                             |
+-------------------------------------------------------------------------+
                    ^                            ^
                    |                            |
                    |      Extends (Kế thừa)     |
    +---------------+                            +---------------+
    |                                                            |
+---------------------------------------+    +---------------------------------------+
|                 Owner                 |    |               Mechanic                |
+---------------------------------------+    +---------------------------------------+
| (Kế thừa thuộc tính từ Person)        |    | - specialization: String              |
|                                       |    | - salary: double                      |
|                                       |    | - status: String                      |
+---------------------------------------+    +---------------------------------------+
| + Owner()                             |    | + Mechanic()                          |
| + Owner(id: int, name: String,        |    | + Mechanic(id: int, name: String,     |
|         phone: String, address: String|    |            phone: String,             |
| + nhapInfo(sc: Scanner): void         |    |            address: String,           |
| + toString(): String                  |    |            specialization: String,    |
| + getRoleDescription(): String        |    |            salary: double,            |
|                                       |    |            status: String)            |
|                                       |    | + getSpecialization(): String / ...   |
|                                       |    | + nhapInfo(sc: Scanner): void         |
|                                       |    | + toString(): String                  |
|                                       |    | + getRoleDescription(): String        |
+---------------------------------------+    +---------------------------------------+


---------------------------------------------------------------------------


+-------------------------------------------------------------------------+
|                               <<abstract>>                              |
|                                 HangMuc                                 |
+-------------------------------------------------------------------------+
| - maHangMuc: String                                                     |
| - tenHangMuc: String                                                    |
+-------------------------------------------------------------------------+
| + HangMuc()                                                             |
| + HangMuc(maHangMuc: String, tenHangMuc: String)                        |
| + getMaHangMuc(): String / + setMaHangMuc(maHangMuc: String): void      |
| + getTenHangMuc(): String / + setTenHangMuc(tenHangMuc: String): void   |
| + tinhThanhTien(soLuong: int): double <<abstract>>                      |
+-------------------------------------------------------------------------+
                    ^                            ^
                    |                            |
                    |      Extends (Kế thừa)     |
    +---------------+                            +---------------+
    |                                                            |
+---------------------------------------+    +---------------------------------------+
|               LinhKien                |    |                DichVu                 |
+---------------------------------------+    +---------------------------------------+
| - donGia: double                      |    | - giaDichVu: double                   |
| - soLuongTon: int                     |    |                                       |
+---------------------------------------+    +---------------------------------------+
| + LinhKien()                          |    | + DichVu()                            |
| + LinhKien(maHangMuc, tenHangMuc,     |    | + DichVu(maHangMuc, tenHangMuc,       |
|            donGia, soLuongTon)        |    |          giaDichVu)                   |
| + getDonGia(): double / ...           |    | + getGiaDichVu(): double / ...        |
| + tinhThanhTien(soLuong: int): double |    | + tinhThanhTien(soLuong: int): double |
+---------------------------------------+    +---------------------------------------+


---------------------------------------------------------------------------


+---------------------------------------+    +---------------------------------------+
|                Vehicle                |    |              RepairOrder              |
+---------------------------------------+    +---------------------------------------+
| - licensePlate: String                |    | - orderId: int                        |
| - brand: String                       |    | - licensePlate: String                |
| - model: String                       |    | - mechanicId: int                     |
| - ownerId: int                        |    | - orderDate: Date                     |
+---------------------------------------+    | - status: String                      |
| + Vehicle()                           |    +---------------------------------------+
| + getLicensePlate(): String / ...     |    | + RepairOrder()                       |
| + nhapInfo(sc: Scanner): void         |    | + getOrderId(): int / ...             |
+---------------------------------------+    | + nhapInfo(sc: Scanner): void         |
                                             +---------------------------------------+


===========================================================================
                               PACKAGE: VIEW
===========================================================================

+-------------------------------------------------------------------------+
|                                 MainUI                                  |
|                             (Menu Console)                              |
+-------------------------------------------------------------------------+
| + main(args: String[]): void                                            |
| + showMenu(): void                                                      |
| + handleUserInput(): void                                               |
+-------------------------------------------------------------------------+
