===========================================================================
                            CẤU TRÚC THƯ MỤC
===========================================================================
Quản Lý Gara
│
├── model/
│   ├── Person (abstract)
│   ├── Employee (abstract)
│   ├── Customer
│   ├── Owner
│   ├── Accountant
│   ├── Storekeeper
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
│   ├── EmployeeDAO
│   ├── KyThuatVienDAO
│   ├── XeDAO
│   ├── PhieuSuaChuaDAO
│   ├── LinhKienDAO
│   ├── DichVuDAO
│   └── HoaDonDAO
│
└── view/
    └── ConsoleView (Giao diện bảng điều khiển chính)


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
| ChuXeDAO |      | EmployeeDAO|      |  XeDAO    | ... (và các DAO khác)
+----------+      +------------+      +-----------+


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
| - isDeleted: boolean                                                    |
+-------------------------------------------------------------------------+
| + Person()                                                              |
| + Person(id: int, name: String, phone: String, address: String)         |
| + nhapInfo(sc: Scanner): void                                           |
| + getRoleDescription(): String <<abstract>>                             |
+-------------------------------------------------------------------------+
                    ^                            ^
                    |                            |
                    |      Extends (Kế thừa)     |
    +---------------+                            +---------------+
    |                                                            |
+---------------------------------------+    +---------------------------------------+
|               Customer                |    |             Employee <<abstract>>     |
+---------------------------------------+    +---------------------------------------+
| - email: String                       |    | - username: String                    |
|                                       |    | - password: String                    |
|                                       |    | - role: String                        |
|                                       |    | - salary: double                      |
|                                       |    | - status: String                      |
+---------------------------------------+    +---------------------------------------+
| + Customer(...)                       |    | + Employee(...)                       |
| + getEmail(): String / ...            |    | + getUsername(): String / ...         |
| + nhapInfo(sc: Scanner): void         |    | + getRole(): String / ...             |
+---------------------------------------+    +---------------------------------------+
                                                                 ^
                                                                 | Extends
         +--------------------+--------------------+-------------+------+
         |                    |                    |                    |
+--------+-------+   +--------+-------+   +--------+-------+   +--------+-------+
|     Owner      |   |   Accountant   |   |  Storekeeper   |   |    Mechanic    |
+----------------+   +----------------+   +----------------+   +----------------+
| (Role: QuanLy) |   | (Role: KeToan) |   | (Role: ThuKho) |   | - spec: String |
+----------------+   +----------------+   +----------------+   +----------------+


---------------------------------------------------------------------------


+-------------------------------------------------------------------------+
|                               <<abstract>>                              |
|                                 HangMuc                                 |
+-------------------------------------------------------------------------+
| - ma: String                                                            |
| - ten: String                                                           |
| - donGia: double                                                        |
+-------------------------------------------------------------------------+
| + HangMuc()                                                             |
| + HangMuc(ma: String, ten: String, donGia: double)                      |
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
| - soLuongTon: int                     |    |                                       |
| - location: String                    |    |                                       |
+---------------------------------------+    +---------------------------------------+
| + LinhKien(...)                       |    | + DichVu(...)                         |
| + tinhThanhTien(soLuong: int): double |    | + tinhThanhTien(soLuong: int): double |
+---------------------------------------+    +---------------------------------------+


---------------------------------------------------------------------------


+---------------------------------------+    +---------------------------------------+
|                Vehicle                |    |              RepairOrder              |
+---------------------------------------+    +---------------------------------------+
| - licensePlate: String                |    | - orderId: int                        |
| - brand: String                       |    | - vehicle: Vehicle                    |
| - model: String                       |    | - mechanic: Employee                  |
| - productionYear: int                 |    | - orderDate: LocalDateTime            |
| - color: String                       |    | - status: String                      |
| - condition: String                   |    | - visualCondition: String             |
| - Customer: Customer                  |    | - isDeleted: boolean                  |
| - isDeleted: boolean                  |    |                                       |
+---------------------------------------+    +---------------------------------------+
| + Vehicle()                           |    | + RepairOrder()                       |
| + nhapInfo(sc: Scanner): void         |    | + nhapInfo(sc: Scanner): void         |
+---------------------------------------+    +---------------------------------------+


===========================================================================
                               PACKAGE: VIEW
===========================================================================

+-------------------------------------------------------------------------+
|                               ConsoleView                               |
|                             (Menu Console)                              |
+-------------------------------------------------------------------------+
| + start(): void                                                         |
| + login(): void                                                         |
| + showMainMenu(): void                                                  |
| - menuVehicleAndOwner(): void                                           |
| - menuMechanic(): void                                                  |
| - menuPart(): void                                                      |
| - menuRepairOrder(): void                                               |
| - menuInvoice(): void                                                   |
| - menuReport(): void                                                    |
+-------------------------------------------------------------------------+
