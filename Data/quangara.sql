-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- May chu: 127.0.0.1
-- Thoi gian da tao: Th7 10, 2026 luc 02:56 PM
-- Phien ban may phuc vu: 10.4.32-MariaDB
-- Phien ban PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Co so du lieu: `quangara`
--
CREATE DATABASE IF NOT EXISTS `quangara` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `quangara`;

-- --------------------------------------------------------

--
-- Cau truc bang cho bang `chi_tiet_phieu_sua`
--

CREATE TABLE `chi_tiet_phieu_sua` (
  `id` int(11) NOT NULL,
  `ma_phieu` int(11) NOT NULL,
  `ma_hang_muc` varchar(20) NOT NULL,
  `loai_hang_muc` varchar(20) NOT NULL,
  `don_gia_thuc_te` double NOT NULL DEFAULT 0,
  `so_luong` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dang do du lieu cho bang `chi_tiet_phieu_sua`
--

INSERT INTO `chi_tiet_phieu_sua` (`id`, `ma_phieu`, `ma_hang_muc`, `loai_hang_muc`, `don_gia_thuc_te`, `so_luong`) VALUES
(1, 1, 'LK04', 'LINHKIEN', 800000, 1),
(2, 1, 'DV01', 'DICHVU', 100000, 1),
(3, 2, 'LK02', 'LINHKIEN', 120000, 4),
(4, 2, 'DV03', 'DICHVU', 500000, 1),
(5, 3, 'LK01', 'LINHKIEN', 250000, 2),
(6, 3, 'DV03', 'DICHVU', 500000, 1),
(7, 4, 'LK02', 'LINHKIEN', 120000, 2),
(8, 4, 'DV02', 'DICHVU', 150000, 1);

-- --------------------------------------------------------

--
-- Cau truc bang cho bang `chu_xe`
--

CREATE TABLE `chu_xe` (
  `id` int(11) NOT NULL,
  `ten` varchar(255) NOT NULL,
  `sdt` varchar(20) NOT NULL,
  `dia_chi` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `da_xoa` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dang do du lieu cho bang `chu_xe`
--

INSERT INTO `chu_xe` (`id`, `ten`, `sdt`, `dia_chi`, `email`, `da_xoa`) VALUES
(1, 'Nguyen Van A', '0912345678', '123 Tran Hung Dao, TP.HCM', 'nva@gmail.com', 0),
(2, 'Le Thi B', '0987654321', '456 Le Loi, Ha Noi', 'ltb@gmail.com', 0),
(3, 'Tran Van C', '0901112223', '789 Nguyen Hue, Da Nang', 'tvc@gmail.com', 0),
(4, '1', '0808080808', '1', '1@gmail.com', 1),
(5, 'aa', '0909090909', '�iioi', 'fbbfbb@gmail.com', 1);

-- --------------------------------------------------------

--
-- Cau truc bang cho bang `dich_vu`
--

CREATE TABLE `dich_vu` (
  `ma` varchar(20) NOT NULL,
  `ten` varchar(255) NOT NULL,
  `don_gia` double NOT NULL DEFAULT 0,
  `da_xoa` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dang do du lieu cho bang `dich_vu`
--

INSERT INTO `dich_vu` (`ma`, `ten`, `don_gia`, `da_xoa`) VALUES
('DV01', 'Thay dau nhot', 100000, 0),
('DV02', 'Kiem tra phanh', 150000, 0),
('DV03', 'Bao duong tong the', 500000, 0);

-- --------------------------------------------------------

--
-- Cau truc bang cho bang `hoa_don`
--

CREATE TABLE `hoa_don` (
  `ma_hoa_don` int(11) NOT NULL,
  `ma_phieu` int(11) NOT NULL,
  `ngay_thanh_toan` datetime DEFAULT NULL,
  `tong_tien_linh_kien` double NOT NULL DEFAULT 0,
  `tong_tien_cong` double NOT NULL DEFAULT 0,
  `thue_vat` double NOT NULL DEFAULT 0.1,
  `tong_tien` double NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dang do du lieu cho bang `hoa_don`
--

INSERT INTO `hoa_don` (`ma_hoa_don`, `ma_phieu`, `ngay_thanh_toan`, `tong_tien_linh_kien`, `tong_tien_cong`, `thue_vat`, `tong_tien`) VALUES
(1, 1, '2026-07-01 12:00:00', 800000, 100000, 0.1, 990000),
(2, 3, '2026-07-03 16:00:00', 500000, 500000, 0.1, 1100000),
(3, 4, '2026-07-04 17:30:00', 240000, 150000, 0.1, 429000);

-- --------------------------------------------------------

--
-- Cau truc bang cho bang `linh_kien`
--

CREATE TABLE `linh_kien` (
  `ma` varchar(20) NOT NULL,
  `ten` varchar(255) NOT NULL,
  `don_gia` double NOT NULL DEFAULT 0,
  `so_luong_ton` int(11) NOT NULL DEFAULT 0,
  `vi_tri` varchar(255) DEFAULT NULL,
  `da_xoa` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dang do du lieu cho bang `linh_kien`
--

INSERT INTO `linh_kien` (`ma`, `ten`, `don_gia`, `so_luong_ton`, `vi_tri`, `da_xoa`) VALUES
('LK01', 'Loc dau dong co', 250000, 50, 'Ke A1', 0),
('LK02', 'Bugi NGK', 120000, 100, 'Ke A2', 0),
('LK03', 'Ma phanh truoc', 550000, 30, 'Ke B1', 0),
('LK04', 'Dau nhot Castrol', 800000, 20, 'Kho nhot', 0);

-- --------------------------------------------------------

--
-- Cau truc bang cho bang `nhan_vien`
--

CREATE TABLE `nhan_vien` (
  `id` int(11) NOT NULL,
  `ten` varchar(255) NOT NULL,
  `sdt` varchar(20) NOT NULL,
  `dia_chi` varchar(255) DEFAULT NULL,
  `ten_dang_nhap` varchar(50) NOT NULL,
  `mat_khau` varchar(255) NOT NULL,
  `vai_tro` varchar(50) NOT NULL,
  `chuyen_mon` varchar(255) DEFAULT NULL,
  `luong` double NOT NULL DEFAULT 0,
  `trang_thai` varchar(50) NOT NULL DEFAULT 'Dang ranh',
  `da_xoa` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dang do du lieu cho bang `nhan_vien`
--

INSERT INTO `nhan_vien` (`id`, `ten`, `sdt`, `dia_chi`, `ten_dang_nhap`, `mat_khau`, `vai_tro`, `chuyen_mon`, `luong`, `trang_thai`, `da_xoa`) VALUES
(1, 'Admin User', '0900000001', 'System', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'QuanLy', NULL, 0, 'Dang ranh', 0),
(2, 'Ketoan User', '0900000002', 'System', 'ketoan', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'KeToan', NULL, 0, 'Dang ranh', 0),
(3, 'Thukho User', '0900000003', 'System', 'thukho', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'ThuKho', NULL, 0, 'Dang ranh', 0),
(4, 'Thomay User', '0900000004', 'System', 'thomay', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'KyThuat', 'Chung', 5000000, 'Dang ranh', 0),
(5, 'Nguyen Ky Thuat B', '0900000005', 'Da Nang', 'thomay2', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'KyThuat', 'Dien', 6000000, 'Dang ranh', 0),
(6, 'Tran Ky Thuat C', '0900000006', 'Can Tho', 'thomay3', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'KyThuat', 'Lanh', 5500000, 'Dang ranh', 0);

-- --------------------------------------------------------

--
-- Cau truc bang cho bang `phieu_sua_chua`
--

CREATE TABLE `phieu_sua_chua` (
  `ma_phieu` int(11) NOT NULL,
  `bien_so` varchar(20) NOT NULL,
  `ngay_vao` datetime DEFAULT NULL,
  `ngay_ra` datetime DEFAULT NULL,
  `ma_tho_may` int(11) NOT NULL,
  `trang_thai` varchar(50) NOT NULL DEFAULT 'RECEIVING',
  `tinh_trang_ben_ngoai` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dang do du lieu cho bang `phieu_sua_chua`
--

INSERT INTO `phieu_sua_chua` (`ma_phieu`, `bien_so`, `ngay_vao`, `ngay_ra`, `ma_tho_may`, `trang_thai`, `tinh_trang_ben_ngoai`) VALUES
(1, '51A-123.45', '2026-07-01 08:30:00', '2026-07-01 11:30:00', 4, 'COMPLETED', 'Tray xuoc nhe'),
(2, '29A-678.90', '2026-07-02 09:00:00', NULL, 4, 'IN_PROGRESS', 'Bao duong dinh ky'),
(3, '43A-111.22', '2026-07-03 10:00:00', '2026-07-03 15:30:00', 4, 'COMPLETED', 'Hong dong co'),
(4, '29A-678.90', '2026-07-04 14:00:00', '2026-07-04 17:00:00', 5, 'COMPLETED', 'Bao duong dinh ky');

-- --------------------------------------------------------

--
-- Cau truc bang cho bang `xe`
--

CREATE TABLE `xe` (
  `bien_so` varchar(20) NOT NULL,
  `hang_xe` varchar(100) DEFAULT NULL,
  `dong_xe` varchar(100) DEFAULT NULL,
  `nam_san_xuat` int(11) DEFAULT NULL,
  `ma_chu_xe` int(11) NOT NULL,
  `mau_sac` varchar(50) DEFAULT NULL,
  `tinh_trang_tiep_nhan` varchar(255) DEFAULT NULL,
  `da_xoa` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dang do du lieu cho bang `xe`
--

INSERT INTO `xe` (`bien_so`, `hang_xe`, `dong_xe`, `nam_san_xuat`, `ma_chu_xe`, `mau_sac`, `tinh_trang_tiep_nhan`, `da_xoa`) VALUES
('29A-678.90', 'Honda', 'Civic', 2022, 2, 'Den', 'Bao duong dinh ky', 0),
('43A-111.22', 'Mazda', 'CX-5', 2021, 3, 'Do', 'Hong dong co', 0),
('51A-123.45', 'Toyota', 'Vios', 2020, 1, 'Trang', 'Tray xuoc nhe', 0);

--
-- Chi muc cho cac bang da do
--

--
-- Chi muc cho bang `chi_tiet_phieu_sua`
--
ALTER TABLE `chi_tiet_phieu_sua`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uq_order_item` (`ma_phieu`,`ma_hang_muc`,`loai_hang_muc`);

--
-- Chi muc cho bang `chu_xe`
--
ALTER TABLE `chu_xe`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `sdt` (`sdt`);

--
-- Chi muc cho bang `dich_vu`
--
ALTER TABLE `dich_vu`
  ADD PRIMARY KEY (`ma`);

--
-- Chi muc cho bang `hoa_don`
--
ALTER TABLE `hoa_don`
  ADD PRIMARY KEY (`ma_hoa_don`),
  ADD UNIQUE KEY `ma_phieu` (`ma_phieu`);

--
-- Chi muc cho bang `linh_kien`
--
ALTER TABLE `linh_kien`
  ADD PRIMARY KEY (`ma`);

--
-- Chi muc cho bang `nhan_vien`
--
ALTER TABLE `nhan_vien`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ten_dang_nhap` (`ten_dang_nhap`),
  ADD UNIQUE KEY `sdt` (`sdt`);

--
-- Chi muc cho bang `phieu_sua_chua`
--
ALTER TABLE `phieu_sua_chua`
  ADD PRIMARY KEY (`ma_phieu`),
  ADD KEY `fk_order_vehicle` (`bien_so`),
  ADD KEY `fk_order_mechanic` (`ma_tho_may`);

--
-- Chi muc cho bang `xe`
--
ALTER TABLE `xe`
  ADD PRIMARY KEY (`bien_so`),
  ADD KEY `fk_vehicle_owner` (`ma_chu_xe`);

--
-- AUTO_INCREMENT cho cac bang da do
--

--
-- AUTO_INCREMENT cho bang `chi_tiet_phieu_sua`
--
ALTER TABLE `chi_tiet_phieu_sua`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bang `chu_xe`
--
ALTER TABLE `chu_xe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bang `hoa_don`
--
ALTER TABLE `hoa_don`
  MODIFY `ma_hoa_don` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bang `nhan_vien`
--
ALTER TABLE `nhan_vien`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bang `phieu_sua_chua`
--
ALTER TABLE `phieu_sua_chua`
  MODIFY `ma_phieu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Cac rang buoc cho cac bang da do
--

--
-- Cac rang buoc cho bang `chi_tiet_phieu_sua`
--
ALTER TABLE `chi_tiet_phieu_sua`
  ADD CONSTRAINT `fk_detail_order` FOREIGN KEY (`ma_phieu`) REFERENCES `phieu_sua_chua` (`ma_phieu`);

--
-- Cac rang buoc cho bang `hoa_don`
--
ALTER TABLE `hoa_don`
  ADD CONSTRAINT `fk_invoice_order` FOREIGN KEY (`ma_phieu`) REFERENCES `phieu_sua_chua` (`ma_phieu`);

--
-- Cac rang buoc cho bang `phieu_sua_chua`
--
ALTER TABLE `phieu_sua_chua`
  ADD CONSTRAINT `fk_order_mechanic` FOREIGN KEY (`ma_tho_may`) REFERENCES `nhan_vien` (`id`),
  ADD CONSTRAINT `fk_order_vehicle` FOREIGN KEY (`bien_so`) REFERENCES `xe` (`bien_so`);

--
-- Cac rang buoc cho bang `xe`
--
ALTER TABLE `xe`
  ADD CONSTRAINT `fk_vehicle_owner` FOREIGN KEY (`ma_chu_xe`) REFERENCES `chu_xe` (`id`);

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
