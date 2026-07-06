# Sơ đồ Lớp (Class Diagram) - Quản Lý Gara

Dưới đây là sơ đồ lớp cho dự án Quản lý Gara, được viết dưới dạng Mermaid. Bạn có thể xem trực tiếp trên GitHub hoặc các công cụ hỗ trợ Markdown.

```mermaid
classDiagram
    %% --- Interfaces ---
    class IRepository~T~ {
        <<interface>>
        +themMoi(item: T) void
        +capNhat(item: T) void
        +xoa(id: Object) void
        +layTatCa() List~T~
        +layTheoId(id: Object) T
    }

    class IReportService {
        <<interface>>
        +getMostRepairedVehicles(limit: int) Map~String, Integer~
    }

    %% --- Abstract Classes & Inheritance ---
    class Person {
        <<abstract>>
        -id : int
        -name : String
        -phone : String
        -address : String
        +nhapInfo(sc: Scanner) void
        +getRoleDescription()* String
    }

    class Owner {
        +getRoleDescription() String
    }

    class Mechanic {
        -specialization : String
        -salary : double
        -status : String
        +getRoleDescription() String
    }

    Person <|-- Owner
    Person <|-- Mechanic

    class HangMuc {
        <<abstract>>
        -maHangMuc : String
        -tenHangMuc : String
        +tinhThanhTien(soLuong: int)* double
    }

    class LinhKien {
        -donGia : double
        -soLuongTon : int
        +tinhThanhTien(soLuong: int) double
    }

    class DichVu {
        -giaDichVu : double
        +tinhThanhTien(soLuong: int) double
    }

    HangMuc <|-- LinhKien
    HangMuc <|-- DichVu

    class Exception {
        <<class>>
    }

    class DuplicateMaException {
        +DuplicateMaException(message: String)
    }

    Exception <|-- DuplicateMaException

    %% --- Core Entities ---
    class Vehicle {
        -licensePlate : String
        -brand : String
        -model : String
        -ownerId : int
    }

    class RepairOrder {
        -orderId : int
        -licensePlate : String
        -mechanicId : int
        -orderDate : Date
        -status : String
    }

    class RepairOrderDetail {
        -orderId : int
        -maHangMuc : String
        -soLuong : int
    }

    class Invoice {
        -invoiceId : int
        -orderId : int
        -totalAmount : double
        -paymentDate : Date
    }

    %% --- DAO Classes (Controllers) ---
    class ChuXeDAO
    class KyThuatVienDAO {
        +sortBySalary()
        +sortByName()
    }
    class XeDAO {
        +searchByLicensePlate()
        +searchByOwnerName()
    }
    class LinhKienDAO {
        +sortByPrice()
        +sortByQuantity()
    }
    class DichVuDAO
    class PhieuSuaChuaDAO
    class HoaDonDAO

    IRepository <|.. ChuXeDAO : implements
    IRepository <|.. KyThuatVienDAO : implements
    IRepository <|.. XeDAO : implements
    IRepository <|.. LinhKienDAO : implements
    IRepository <|.. DichVuDAO : implements
    IRepository <|.. PhieuSuaChuaDAO : implements
    IRepository <|.. HoaDonDAO : implements

    class ReportController {
        +getMostRepairedVehicles(limit: int) Map
    }
    IReportService <|.. ReportController : implements

    class DBConnection {
        +getConnection() Connection
    }

    %% --- Relationships (Associations) ---
    Owner "1" -- "*" Vehicle : owns
    Vehicle "1" -- "*" RepairOrder : has
    Mechanic "1" -- "*" RepairOrder : assigned to
    RepairOrder "1" *-- "*" RepairOrderDetail : contains
    RepairOrderDetail "*" -- "1" HangMuc : refers to
    RepairOrder "1" -- "1" Invoice : billed in
```

---

## Cấu trúc thư mục (Dạng cây)

```text
Quản Lý Gara (OOP Project)
│
├── model/ (Thực thể & Dữ liệu)
│   ├── Person (Abstract)
│   │   ├── Owner (Kế thừa Person)
│   │   └── Mechanic (Kế thừa Person)
│   │
│   ├── HangMuc (Abstract)
│   │   ├── LinhKien (Kế thừa HangMuc)
│   │   └── DichVu (Kế thừa HangMuc)
│   │
│   ├── Vehicle
│   ├── RepairOrder
│   ├── RepairOrderDetail
│   ├── Invoice
│   └── DuplicateMaException (Kế thừa Exception)
│
└── controller/ (Xử lý nghiệp vụ & Database)
    ├── IRepository<T> (Interface CRUD Generic)
    │   ├── ChuXeDAO
    │   ├── KyThuatVienDAO
    │   ├── XeDAO
    │   ├── PhieuSuaChuaDAO
    │   ├── LinhKienDAO
    │   ├── DichVuDAO
    │   └── HoaDonDAO
    │
    ├── IReportService (Interface Báo cáo)
    │   └── ReportController
    │
    └── DBConnection (Kết nối MySQL)
```
