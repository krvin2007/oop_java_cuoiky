-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th7 10, 2026 lúc 01:09 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlygara`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dich_vu`
--

CREATE TABLE `dich_vu` (
  `ma` varchar(20) NOT NULL,
  `ten` varchar(255) NOT NULL,
  `don_gia` double NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `employees`
--

CREATE TABLE `employees` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(50) NOT NULL,
  `specialization` varchar(255) DEFAULT NULL,
  `salary` double NOT NULL DEFAULT 0,
  `status` varchar(50) NOT NULL DEFAULT 'Đang rảnh',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `employees`
--

INSERT INTO `employees` (`id`, `name`, `phone`, `address`, `username`, `password`, `role`, `specialization`, `salary`, `status`, `is_deleted`) VALUES
(1, 'Admin User', '0900000001', 'System', 'admin', 'admin', 'QuanLy', NULL, 0, 'Đang rảnh', 0),
(2, 'Ketoan User', '0900000002', 'System', 'ketoan', '123456', 'KeToan', NULL, 0, 'Đang rảnh', 0),
(3, 'Thukho User', '0900000003', 'System', 'thukho', '123456', 'ThuKho', NULL, 0, 'Đang rảnh', 0),
(4, 'Thomay User', '0900000004', 'System', 'thomay', '123456', 'KyThuat', 'Chung', 5000000, 'Đang rảnh', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `invoices`
--

CREATE TABLE `invoices` (
  `invoice_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `payment_date` datetime DEFAULT NULL,
  `total_part_cost` double NOT NULL DEFAULT 0,
  `total_labor_cost` double NOT NULL DEFAULT 0,
  `vat_rate` double NOT NULL DEFAULT 0.1,
  `total_amount` double NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `linh_kien`
--

CREATE TABLE `linh_kien` (
  `ma` varchar(20) NOT NULL,
  `ten` varchar(255) NOT NULL,
  `don_gia` double NOT NULL DEFAULT 0,
  `so_luong_ton` int(11) NOT NULL DEFAULT 0,
  `location` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `owners`
--

CREATE TABLE `owners` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `owners`
--

INSERT INTO `owners` (`id`, `name`, `phone`, `address`, `email`, `is_deleted`) VALUES
(1, 'q', '0909090909', 'p', 'abc@gmail.com', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `repair_orders`
--

CREATE TABLE `repair_orders` (
  `order_id` int(11) NOT NULL,
  `license_plate` varchar(20) NOT NULL,
  `entry_date` datetime DEFAULT NULL,
  `exit_date` datetime DEFAULT NULL,
  `mechanic_id` int(11) NOT NULL,
  `status` varchar(50) NOT NULL DEFAULT 'RECEIVING',
  `visual_condition` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `repair_order_details`
--

CREATE TABLE `repair_order_details` (
  `id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `ma_hang_muc` varchar(20) NOT NULL,
  `loai_hang_muc` varchar(20) NOT NULL,
  `don_gia_thuc_te` double NOT NULL DEFAULT 0,
  `so_luong` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `vehicles`
--

CREATE TABLE `vehicles` (
  `license_plate` varchar(20) NOT NULL,
  `brand` varchar(100) DEFAULT NULL,
  `model` varchar(100) DEFAULT NULL,
  `production_year` int(11) DEFAULT NULL,
  `owner_id` int(11) NOT NULL,
  `color` varchar(50) DEFAULT NULL,
  `condition_receipt` varchar(255) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `dich_vu`
--
ALTER TABLE `dich_vu`
  ADD PRIMARY KEY (`ma`);

--
-- Chỉ mục cho bảng `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `phone` (`phone`);

--
-- Chỉ mục cho bảng `invoices`
--
ALTER TABLE `invoices`
  ADD PRIMARY KEY (`invoice_id`),
  ADD UNIQUE KEY `order_id` (`order_id`);

--
-- Chỉ mục cho bảng `linh_kien`
--
ALTER TABLE `linh_kien`
  ADD PRIMARY KEY (`ma`);

--
-- Chỉ mục cho bảng `owners`
--
ALTER TABLE `owners`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `phone` (`phone`);

--
-- Chỉ mục cho bảng `repair_orders`
--
ALTER TABLE `repair_orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `fk_order_vehicle` (`license_plate`),
  ADD KEY `fk_order_mechanic` (`mechanic_id`);

--
-- Chỉ mục cho bảng `repair_order_details`
--
ALTER TABLE `repair_order_details`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uq_order_item` (`order_id`,`ma_hang_muc`,`loai_hang_muc`);

--
-- Chỉ mục cho bảng `vehicles`
--
ALTER TABLE `vehicles`
  ADD PRIMARY KEY (`license_plate`),
  ADD KEY `fk_vehicle_owner` (`owner_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `employees`
--
ALTER TABLE `employees`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `invoices`
--
ALTER TABLE `invoices`
  MODIFY `invoice_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `owners`
--
ALTER TABLE `owners`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `repair_orders`
--
ALTER TABLE `repair_orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `repair_order_details`
--
ALTER TABLE `repair_order_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `invoices`
--
ALTER TABLE `invoices`
  ADD CONSTRAINT `fk_invoice_order` FOREIGN KEY (`order_id`) REFERENCES `repair_orders` (`order_id`);

--
-- Các ràng buộc cho bảng `repair_orders`
--
ALTER TABLE `repair_orders`
  ADD CONSTRAINT `fk_order_mechanic` FOREIGN KEY (`mechanic_id`) REFERENCES `employees` (`id`),
  ADD CONSTRAINT `fk_order_vehicle` FOREIGN KEY (`license_plate`) REFERENCES `vehicles` (`license_plate`);

--
-- Các ràng buộc cho bảng `repair_order_details`
--
ALTER TABLE `repair_order_details`
  ADD CONSTRAINT `fk_detail_order` FOREIGN KEY (`order_id`) REFERENCES `repair_orders` (`order_id`);

--
-- Các ràng buộc cho bảng `vehicles`
--
ALTER TABLE `vehicles`
  ADD CONSTRAINT `fk_vehicle_owner` FOREIGN KEY (`owner_id`) REFERENCES `owners` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
