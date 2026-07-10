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
│   └── Invoice
│
├── exception/
│   ├── DuplicateMaException
│   ├── InvalidPlateNumberException
│   ├── PartOutOfStockException
│   └── VehicleNotFoundException
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
| + getId(): int                                                          |
| + setId(id: int): void                                                  |
| + getName(): String                                                     |
| + setName(name: String): void                                           |
| + getPhone(): String                                                    |
| + setPhone(phone: String): void                                         |
| + getAddress(): String                                                  |
| + setAddress(address: String): void                                     |
| + isDeleted(): boolean                                                  |
| + setDeleted(isDeleted: boolean): void                                  |
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
| + getEmail(): String                  |    | + getUsername(): String               |
| + setEmail(email: String): void       |    | + setUsername(username: String): void |
| + nhapInfo(sc: Scanner): void         |    | + getPassword(): String               |
|                                       |    | + setPassword(password: String): void |
|                                       |    | + getRole(): String                   |
|                                       |    | + setRole(role: String): void         |
|                                       |    | + getSalary(): double                 |
|                                       |    | + setSalary(salary: double): void     |
|                                       |    | + getStatus(): String                 |
|                                       |    | + setStatus(status: String): void     |
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
                                                               | + getSpec()    |
                                                               | + setSpec(s)   |
                                                               +----------------+


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
| + getMa(): String                                                       |
| + setMa(ma: String): void                                               |
| + getTen(): String                                                      |
| + setTen(ten: String): void                                             |
| + getDonGia(): double                                                   |
| + setDonGia(donGia: double): void                                       |
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
| + getSoLuongTon(): int                |    | + tinhThanhTien(soLuong: int): double |
| + setSoLuongTon(soLuongTon: int): void|    |                                       |
| + getLocation(): String               |    |                                       |
| + setLocation(location: String): void |    |                                       |
| + tinhThanhTien(soLuong: int): double |    |                                       |
+---------------------------------------+    +---------------------------------------+


---------------------------------------------------------------------------


+---------------------------------------+    +---------------------------------------+
|                Vehicle                |    |              RepairOrder              |
+---------------------------------------+    +---------------------------------------+
| - licensePlate: String                |    | - orderId: int                        |
| - brand: String                       |    | - vehicle: Vehicle                    |
| - model: String                       |    | - entryDate: Date                     |
| - productionYear: int                 |    | - exitDate: Date                      |
| - color: String                       |    | - mechanic: Mechanic                  |
| - condition: String                   |    | - status: String                      |
| - Customer: Customer                  |    | - visualCondition: String             |
| - isDeleted: boolean                  |    | - details: List<RepairOrderDetail>    |
+---------------------------------------+    +---------------------------------------+
| + Vehicle()                           |    | + RepairOrder()                       |
| + getLicensePlate(): String           |    | + getOrderId(): int                   |
| + setLicensePlate(lp: String): void   |    | + setOrderId(id: int): void           |
| + getBrand(): String                  |    | + getVehicle(): Vehicle               |
| + setBrand(brand: String): void       |    | + setVehicle(v: Vehicle): void        |
| + getModel(): String                  |    | + getEntryDate(): Date                |
| + setModel(model: String): void       |    | + setEntryDate(d: Date): void         |
| + getProductionYear(): int            |    | + getExitDate(): Date                 |
| + setProductionYear(y: int): void     |    | + setExitDate(d: Date): void          |
| + getColor(): String                  |    | + getMechanic(): Mechanic             |
| + setColor(color: String): void       |    | + setMechanic(m: Mechanic): void      |
| + getCondition(): String              |    | + getStatus(): String                 |
| + setCondition(cond: String): void    |    | + setStatus(s: String): void          |
| + getOwner(): Customer                |    | + getVisualCondition(): String        |
| + setOwner(c: Customer): void         |    | + setVisualCondition(c: String): void |
| + isDeleted(): boolean                |    | + getDetails(): List<...>             |
| + setDeleted(del: boolean): void      |    | + setDetails(d: List<...>): void      |
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
