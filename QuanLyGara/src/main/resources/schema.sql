CREATE TABLE IF NOT EXISTS employees (
  id INT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  phone VARCHAR(20) NOT NULL,
  address VARCHAR(255),
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(50) NOT NULL,
  specialization VARCHAR(255),
  salary DOUBLE NOT NULL DEFAULT 0,
  status VARCHAR(50) NOT NULL DEFAULT 'Đang rảnh',
  is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS owners (
  id INT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  phone VARCHAR(20) NOT NULL UNIQUE,
  address VARCHAR(255),
  is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS vehicles (
  license_plate VARCHAR(20) PRIMARY KEY,
  brand VARCHAR(100),
  model VARCHAR(100),
  production_year INT,
  owner_id INT NOT NULL,
  is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS repair_orders (
  order_id INT PRIMARY KEY,
  license_plate VARCHAR(20) NOT NULL,
  entry_date TIMESTAMP,
  exit_date TIMESTAMP,
  mechanic_id INT NOT NULL,
  status VARCHAR(50) NOT NULL DEFAULT 'RECEIVING'
);

CREATE TABLE IF NOT EXISTS repair_order_details (
  id INT PRIMARY KEY,
  order_id INT NOT NULL,
  ma_hang_muc VARCHAR(20) NOT NULL,
  loai_hang_muc VARCHAR(20) NOT NULL,
  don_gia_thuc_te DOUBLE NOT NULL DEFAULT 0,
  so_luong INT NOT NULL DEFAULT 1
);

CREATE TABLE IF NOT EXISTS dich_vu (
  ma VARCHAR(20) PRIMARY KEY,
  ten VARCHAR(255) NOT NULL,
  don_gia DOUBLE NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS linh_kien (
  ma VARCHAR(20) PRIMARY KEY,
  ten VARCHAR(255) NOT NULL,
  don_gia DOUBLE NOT NULL DEFAULT 0,
  so_luong_ton INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS invoices (
  invoice_id INT PRIMARY KEY,
  order_id INT NOT NULL UNIQUE,
  payment_date TIMESTAMP,
  total_part_cost DOUBLE NOT NULL DEFAULT 0,
  total_labor_cost DOUBLE NOT NULL DEFAULT 0,
  vat_rate DOUBLE NOT NULL DEFAULT 0.1,
  total_amount DOUBLE NOT NULL DEFAULT 0
);

INSERT INTO employees (id, name, phone, address, username, password, role, specialization, salary, status) VALUES
(1, 'Admin User', '0900000001', 'System', 'admin', 'admin', 'QuanLy', NULL, 0, 'Đang rảnh'),
(2, 'Ketoan User', '0900000002', 'System', 'ketoan', '123456', 'KeToan', NULL, 0, 'Đang rảnh'),
(3, 'Thukho User', '0900000003', 'System', 'thukho', '123456', 'ThuKho', NULL, 0, 'Đang rảnh'),
(4, 'Thomay User', '0900000004', 'System', 'thomay', '123456', 'KyThuat', 'Chung', 5000000, 'Đang rảnh');

INSERT INTO owners (id, name, phone, address) VALUES
(1, 'Manh Quynh', '0987654321', 'HCM');

INSERT INTO vehicles (license_plate, brand, model, production_year, owner_id) VALUES
('12345', 'BMW', 'Bugatti Chiron', 2017, 1);
