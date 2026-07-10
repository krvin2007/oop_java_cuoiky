-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th7 10, 2026 lúc 02:56 PM
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
-- Cơ sở dữ liệu: `phpmyadmin`
--
CREATE DATABASE IF NOT EXISTS `phpmyadmin` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `phpmyadmin`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `pma__bookmark`
--

CREATE TABLE `pma__bookmark` (
  `id` int(10) UNSIGNED NOT NULL,
  `dbase` varchar(255) NOT NULL DEFAULT '',
  `user` varchar(255) NOT NULL DEFAULT '',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `query` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Bookmarks';

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `pma__central_columns`
--

CREATE TABLE `pma__central_columns` (
  `db_name` varchar(64) NOT NULL,
  `col_name` varchar(64) NOT NULL,
  `col_type` varchar(64) NOT NULL,
  `col_length` text DEFAULT NULL,
  `col_collation` varchar(64) NOT NULL,
  `col_isNull` tinyint(1) NOT NULL,
  `col_extra` varchar(255) DEFAULT '',
  `col_default` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Central list of columns';

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `pma__column_info`
--

CREATE TABLE `pma__column_info` (
  `id` int(5) UNSIGNED NOT NULL,
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `table_name` varchar(64) NOT NULL DEFAULT '',
  `column_name` varchar(64) NOT NULL DEFAULT '',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `mimetype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `transformation` varchar(255) NOT NULL DEFAULT '',
  `transformation_options` varchar(255) NOT NULL DEFAULT '',
  `input_transformation` varchar(255) NOT NULL DEFAULT '',
  `input_transformation_options` varchar(255) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Column information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `pma__designer_settings`
--

CREATE TABLE `pma__designer_settings` (
  `username` varchar(64) NOT NULL,
  `settings_data` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Settings related to Designer';

--
-- Đang đổ dữ liệu cho bảng `pma__designer_settings`
--

INSERT INTO `pma__designer_settings` (`username`, `settings_data`) VALUES
('root', '{\"angular_direct\":\"direct\",\"relation_lines\":\"true\",\"snap_to_grid\":\"off\",\"full_screen\":\"off\",\"side_menu\":\"false\"}');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `pma__export_templates`
--

CREATE TABLE `pma__export_templates` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) NOT NULL,
  `export_type` varchar(10) NOT NULL,
  `template_name` varchar(64) NOT NULL,
  `template_data` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved export templates';

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `pma__favorite`
--

CREATE TABLE `pma__favorite` (
  `username` varchar(64) NOT NULL,
  `tables` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Favorite tables';

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `pma__history`
--

CREATE TABLE `pma__history` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `username` varchar(64) NOT NULL DEFAULT '',
  `db` varchar(64) NOT NULL DEFAULT '',
  `table` varchar(64) NOT NULL DEFAULT '',
  `timevalue` timestamp NOT NULL DEFAULT current_timestamp(),
  `sqlquery` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='SQL history for phpMyAdmin';

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `pma__navigationhiding`
--

CREATE TABLE `pma__navigationhiding` (
  `username` varchar(64) NOT NULL,
  `item_name` varchar(64) NOT NULL,
  `item_type` varchar(64) NOT NULL,
  `db_name` varchar(64) NOT NULL,
  `table_name` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Hidden items of navigation tree';

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `pma__pdf_pages`
--

CREATE TABLE `pma__pdf_pages` (
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `page_nr` int(10) UNSIGNED NOT NULL,
  `page_descr` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='PDF relation pages for phpMyAdmin';

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `pma__recent`
--

CREATE TABLE `pma__recent` (
  `username` varchar(64) NOT NULL,
  `tables` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Recently accessed tables';

--
-- Đang đổ dữ liệu cho bảng `pma__recent`
--

INSERT INTO `pma__recent` (`username`, `tables`) VALUES
('root', '[{\"db\":\"quanlydiemrenluyen\",\"table\":\"nguoi_dung\"},{\"db\":\"quanlydiemrenluyen\",\"table\":\"tham_gia_hoat_dong\"},{\"db\":\"quanlydiemrenluyen\",\"table\":\"sinh_vien\"},{\"db\":\"quanlydiemrenluyen\",\"table\":\"nhat_ky_he_thong\"},{\"db\":\"quanlydiemrenluyen\",\"table\":\"hoc_ky\"},{\"db\":\"quanlydiemrenluyen\",\"table\":\"hoat_dong\"},{\"db\":\"quanlydiemrenluyen\",\"table\":\"don_vi\"},{\"db\":\"quanlydiemrenluyen\",\"table\":\"chi_tiet_phieu_diem\"},{\"db\":\"quanlydiemrenluyen\",\"table\":\"lop\"},{\"db\":\"quanlydiemrenluyen\",\"table\":\"khieu_nai\"}]');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `pma__relation`
--

CREATE TABLE `pma__relation` (
  `master_db` varchar(64) NOT NULL DEFAULT '',
  `master_table` varchar(64) NOT NULL DEFAULT '',
  `master_field` varchar(64) NOT NULL DEFAULT '',
  `foreign_db` varchar(64) NOT NULL DEFAULT '',
  `foreign_table` varchar(64) NOT NULL DEFAULT '',
  `foreign_field` varchar(64) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Relation table';

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `pma__savedsearches`
--

CREATE TABLE `pma__savedsearches` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) NOT NULL DEFAULT '',
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `search_name` varchar(64) NOT NULL DEFAULT '',
  `search_data` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved searches';

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `pma__table_coords`
--

CREATE TABLE `pma__table_coords` (
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `table_name` varchar(64) NOT NULL DEFAULT '',
  `pdf_page_number` int(11) NOT NULL DEFAULT 0,
  `x` float UNSIGNED NOT NULL DEFAULT 0,
  `y` float UNSIGNED NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table coordinates for phpMyAdmin PDF output';

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `pma__table_info`
--

CREATE TABLE `pma__table_info` (
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `table_name` varchar(64) NOT NULL DEFAULT '',
  `display_field` varchar(64) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `pma__table_uiprefs`
--

CREATE TABLE `pma__table_uiprefs` (
  `username` varchar(64) NOT NULL,
  `db_name` varchar(64) NOT NULL,
  `table_name` varchar(64) NOT NULL,
  `prefs` text NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Tables'' UI preferences';

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `pma__tracking`
--

CREATE TABLE `pma__tracking` (
  `db_name` varchar(64) NOT NULL,
  `table_name` varchar(64) NOT NULL,
  `version` int(10) UNSIGNED NOT NULL,
  `date_created` datetime NOT NULL,
  `date_updated` datetime NOT NULL,
  `schema_snapshot` text NOT NULL,
  `schema_sql` text DEFAULT NULL,
  `data_sql` longtext DEFAULT NULL,
  `tracking` set('UPDATE','REPLACE','INSERT','DELETE','TRUNCATE','CREATE DATABASE','ALTER DATABASE','DROP DATABASE','CREATE TABLE','ALTER TABLE','RENAME TABLE','DROP TABLE','CREATE INDEX','DROP INDEX','CREATE VIEW','ALTER VIEW','DROP VIEW') DEFAULT NULL,
  `tracking_active` int(1) UNSIGNED NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Database changes tracking for phpMyAdmin';

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `pma__userconfig`
--

CREATE TABLE `pma__userconfig` (
  `username` varchar(64) NOT NULL,
  `timevalue` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `config_data` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User preferences storage for phpMyAdmin';

--
-- Đang đổ dữ liệu cho bảng `pma__userconfig`
--

INSERT INTO `pma__userconfig` (`username`, `timevalue`, `config_data`) VALUES
('root', '2026-07-10 12:55:08', '{\"Console\\/Mode\":\"collapse\",\"lang\":\"vi\"}');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `pma__usergroups`
--

CREATE TABLE `pma__usergroups` (
  `usergroup` varchar(64) NOT NULL,
  `tab` varchar(64) NOT NULL,
  `allowed` enum('Y','N') NOT NULL DEFAULT 'N'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User groups with configured menu items';

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `pma__users`
--

CREATE TABLE `pma__users` (
  `username` varchar(64) NOT NULL,
  `usergroup` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Users and their assignments to user groups';

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `pma__central_columns`
--
ALTER TABLE `pma__central_columns`
  ADD PRIMARY KEY (`db_name`,`col_name`);

--
-- Chỉ mục cho bảng `pma__column_info`
--
ALTER TABLE `pma__column_info`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `db_name` (`db_name`,`table_name`,`column_name`);

--
-- Chỉ mục cho bảng `pma__designer_settings`
--
ALTER TABLE `pma__designer_settings`
  ADD PRIMARY KEY (`username`);

--
-- Chỉ mục cho bảng `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_user_type_template` (`username`,`export_type`,`template_name`);

--
-- Chỉ mục cho bảng `pma__favorite`
--
ALTER TABLE `pma__favorite`
  ADD PRIMARY KEY (`username`);

--
-- Chỉ mục cho bảng `pma__history`
--
ALTER TABLE `pma__history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `username` (`username`,`db`,`table`,`timevalue`);

--
-- Chỉ mục cho bảng `pma__navigationhiding`
--
ALTER TABLE `pma__navigationhiding`
  ADD PRIMARY KEY (`username`,`item_name`,`item_type`,`db_name`,`table_name`);

--
-- Chỉ mục cho bảng `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  ADD PRIMARY KEY (`page_nr`),
  ADD KEY `db_name` (`db_name`);

--
-- Chỉ mục cho bảng `pma__recent`
--
ALTER TABLE `pma__recent`
  ADD PRIMARY KEY (`username`);

--
-- Chỉ mục cho bảng `pma__relation`
--
ALTER TABLE `pma__relation`
  ADD PRIMARY KEY (`master_db`,`master_table`,`master_field`),
  ADD KEY `foreign_field` (`foreign_db`,`foreign_table`);

--
-- Chỉ mục cho bảng `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_savedsearches_username_dbname` (`username`,`db_name`,`search_name`);

--
-- Chỉ mục cho bảng `pma__table_coords`
--
ALTER TABLE `pma__table_coords`
  ADD PRIMARY KEY (`db_name`,`table_name`,`pdf_page_number`);

--
-- Chỉ mục cho bảng `pma__table_info`
--
ALTER TABLE `pma__table_info`
  ADD PRIMARY KEY (`db_name`,`table_name`);

--
-- Chỉ mục cho bảng `pma__table_uiprefs`
--
ALTER TABLE `pma__table_uiprefs`
  ADD PRIMARY KEY (`username`,`db_name`,`table_name`);

--
-- Chỉ mục cho bảng `pma__tracking`
--
ALTER TABLE `pma__tracking`
  ADD PRIMARY KEY (`db_name`,`table_name`,`version`);

--
-- Chỉ mục cho bảng `pma__userconfig`
--
ALTER TABLE `pma__userconfig`
  ADD PRIMARY KEY (`username`);

--
-- Chỉ mục cho bảng `pma__usergroups`
--
ALTER TABLE `pma__usergroups`
  ADD PRIMARY KEY (`usergroup`,`tab`,`allowed`);

--
-- Chỉ mục cho bảng `pma__users`
--
ALTER TABLE `pma__users`
  ADD PRIMARY KEY (`username`,`usergroup`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `pma__column_info`
--
ALTER TABLE `pma__column_info`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `pma__history`
--
ALTER TABLE `pma__history`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  MODIFY `page_nr` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- Cơ sở dữ liệu: `quangara`
--
CREATE DATABASE IF NOT EXISTS `quangara` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `quangara`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chi_tiet_phieu_sua`
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
-- Đang đổ dữ liệu cho bảng `chi_tiet_phieu_sua`
--

INSERT INTO `chi_tiet_phieu_sua` (`id`, `ma_phieu`, `ma_hang_muc`, `loai_hang_muc`, `don_gia_thuc_te`, `so_luong`) VALUES
(1, 1, 'LK04', 'LINHKIEN', 800000, 1),
(2, 1, 'DV01', 'DICHVU', 100000, 1),
(3, 2, 'LK02', 'LINHKIEN', 120000, 4),
(4, 2, 'DV03', 'DICHVU', 500000, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chu_xe`
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
-- Đang đổ dữ liệu cho bảng `chu_xe`
--

INSERT INTO `chu_xe` (`id`, `ten`, `sdt`, `dia_chi`, `email`, `da_xoa`) VALUES
(1, 'Nguyen Van A', '0912345678', '123 Tran Hung Dao, TP.HCM', 'nva@gmail.com', 0),
(2, 'Le Thi B', '0987654321', '456 Le Loi, Ha Noi', 'ltb@gmail.com', 0),
(3, 'Tran Van C', '0901112223', '789 Nguyen Hue, Da Nang', 'tvc@gmail.com', 0),
(4, '1', '0808080808', '1', '1@gmail.com', 1),
(5, 'aa', '0909090909', '�iioi', 'fbbfbb@gmail.com', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dich_vu`
--

CREATE TABLE `dich_vu` (
  `ma` varchar(20) NOT NULL,
  `ten` varchar(255) NOT NULL,
  `don_gia` double NOT NULL DEFAULT 0,
  `da_xoa` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `dich_vu`
--

INSERT INTO `dich_vu` (`ma`, `ten`, `don_gia`, `da_xoa`) VALUES
('DV01', 'Thay dau nhot', 100000, 0),
('DV02', 'Kiem tra phanh', 150000, 0),
('DV03', 'Bao duong tong the', 500000, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoa_don`
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
-- Đang đổ dữ liệu cho bảng `hoa_don`
--

INSERT INTO `hoa_don` (`ma_hoa_don`, `ma_phieu`, `ngay_thanh_toan`, `tong_tien_linh_kien`, `tong_tien_cong`, `thue_vat`, `tong_tien`) VALUES
(1, 1, '2026-07-01 12:00:00', 800000, 100000, 0.1, 990000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `linh_kien`
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
-- Đang đổ dữ liệu cho bảng `linh_kien`
--

INSERT INTO `linh_kien` (`ma`, `ten`, `don_gia`, `so_luong_ton`, `vi_tri`, `da_xoa`) VALUES
('LK01', 'Loc dau dong co', 250000, 50, 'Ke A1', 0),
('LK02', 'Bugi NGK', 120000, 100, 'Ke A2', 0),
('LK03', 'Ma phanh truoc', 550000, 30, 'Ke B1', 0),
('LK04', 'Dau nhot Castrol', 800000, 20, 'Kho nhot', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhan_vien`
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
-- Đang đổ dữ liệu cho bảng `nhan_vien`
--

INSERT INTO `nhan_vien` (`id`, `ten`, `sdt`, `dia_chi`, `ten_dang_nhap`, `mat_khau`, `vai_tro`, `chuyen_mon`, `luong`, `trang_thai`, `da_xoa`) VALUES
(1, 'Admin User', '0900000001', 'System', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'QuanLy', NULL, 0, 'Dang ranh', 0),
(2, 'Ketoan User', '0900000002', 'System', 'ketoan', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'KeToan', NULL, 0, 'Dang ranh', 0),
(3, 'Thukho User', '0900000003', 'System', 'thukho', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'ThuKho', NULL, 0, 'Dang ranh', 0),
(4, 'Thomay User', '0900000004', 'System', 'thomay', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'KyThuat', 'Chung', 5000000, 'Dang ranh', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieu_sua_chua`
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
-- Đang đổ dữ liệu cho bảng `phieu_sua_chua`
--

INSERT INTO `phieu_sua_chua` (`ma_phieu`, `bien_so`, `ngay_vao`, `ngay_ra`, `ma_tho_may`, `trang_thai`, `tinh_trang_ben_ngoai`) VALUES
(1, '51A-123.45', '2026-07-01 08:30:00', '2026-07-01 11:30:00', 4, 'COMPLETED', 'Tray xuoc nhe'),
(2, '29A-678.90', '2026-07-02 09:00:00', NULL, 4, 'IN_PROGRESS', 'Bao duong dinh ky');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `xe`
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
-- Đang đổ dữ liệu cho bảng `xe`
--

INSERT INTO `xe` (`bien_so`, `hang_xe`, `dong_xe`, `nam_san_xuat`, `ma_chu_xe`, `mau_sac`, `tinh_trang_tiep_nhan`, `da_xoa`) VALUES
('29A-678.90', 'Honda', 'Civic', 2022, 2, 'Den', 'Bao duong dinh ky', 0),
('43A-111.22', 'Mazda', 'CX-5', 2021, 3, 'Do', 'Hong dong co', 0),
('51A-123.45', 'Toyota', 'Vios', 2020, 1, 'Trang', 'Tray xuoc nhe', 0);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chi_tiet_phieu_sua`
--
ALTER TABLE `chi_tiet_phieu_sua`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uq_order_item` (`ma_phieu`,`ma_hang_muc`,`loai_hang_muc`);

--
-- Chỉ mục cho bảng `chu_xe`
--
ALTER TABLE `chu_xe`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `sdt` (`sdt`);

--
-- Chỉ mục cho bảng `dich_vu`
--
ALTER TABLE `dich_vu`
  ADD PRIMARY KEY (`ma`);

--
-- Chỉ mục cho bảng `hoa_don`
--
ALTER TABLE `hoa_don`
  ADD PRIMARY KEY (`ma_hoa_don`),
  ADD UNIQUE KEY `ma_phieu` (`ma_phieu`);

--
-- Chỉ mục cho bảng `linh_kien`
--
ALTER TABLE `linh_kien`
  ADD PRIMARY KEY (`ma`);

--
-- Chỉ mục cho bảng `nhan_vien`
--
ALTER TABLE `nhan_vien`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ten_dang_nhap` (`ten_dang_nhap`),
  ADD UNIQUE KEY `sdt` (`sdt`);

--
-- Chỉ mục cho bảng `phieu_sua_chua`
--
ALTER TABLE `phieu_sua_chua`
  ADD PRIMARY KEY (`ma_phieu`),
  ADD KEY `fk_order_vehicle` (`bien_so`),
  ADD KEY `fk_order_mechanic` (`ma_tho_may`);

--
-- Chỉ mục cho bảng `xe`
--
ALTER TABLE `xe`
  ADD PRIMARY KEY (`bien_so`),
  ADD KEY `fk_vehicle_owner` (`ma_chu_xe`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chi_tiet_phieu_sua`
--
ALTER TABLE `chi_tiet_phieu_sua`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `chu_xe`
--
ALTER TABLE `chu_xe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `hoa_don`
--
ALTER TABLE `hoa_don`
  MODIFY `ma_hoa_don` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `nhan_vien`
--
ALTER TABLE `nhan_vien`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `phieu_sua_chua`
--
ALTER TABLE `phieu_sua_chua`
  MODIFY `ma_phieu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chi_tiet_phieu_sua`
--
ALTER TABLE `chi_tiet_phieu_sua`
  ADD CONSTRAINT `fk_detail_order` FOREIGN KEY (`ma_phieu`) REFERENCES `phieu_sua_chua` (`ma_phieu`);

--
-- Các ràng buộc cho bảng `hoa_don`
--
ALTER TABLE `hoa_don`
  ADD CONSTRAINT `fk_invoice_order` FOREIGN KEY (`ma_phieu`) REFERENCES `phieu_sua_chua` (`ma_phieu`);

--
-- Các ràng buộc cho bảng `phieu_sua_chua`
--
ALTER TABLE `phieu_sua_chua`
  ADD CONSTRAINT `fk_order_mechanic` FOREIGN KEY (`ma_tho_may`) REFERENCES `nhan_vien` (`id`),
  ADD CONSTRAINT `fk_order_vehicle` FOREIGN KEY (`bien_so`) REFERENCES `xe` (`bien_so`);

--
-- Các ràng buộc cho bảng `xe`
--
ALTER TABLE `xe`
  ADD CONSTRAINT `fk_vehicle_owner` FOREIGN KEY (`ma_chu_xe`) REFERENCES `chu_xe` (`id`);
--
-- Cơ sở dữ liệu: `quanlydiemrenluyen`
--
CREATE DATABASE IF NOT EXISTS `quanlydiemrenluyen` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `quanlydiemrenluyen`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chi_tiet_phieu_diem`
--

CREATE TABLE `chi_tiet_phieu_diem` (
  `ma_phieu_diem` int(11) NOT NULL,
  `ma_tieu_chi` int(11) NOT NULL,
  `diem_he_thong` int(11) DEFAULT 0,
  `diem_thuc_te` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `don_vi`
--

CREATE TABLE `don_vi` (
  `ma_don_vi` int(11) NOT NULL,
  `ten_don_vi` varchar(255) NOT NULL,
  `loai_don_vi` varchar(50) DEFAULT 'Khoa',
  `ma_truong_khoa` int(11) DEFAULT NULL,
  `ma_pho_khoa` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `don_vi`
--

INSERT INTO `don_vi` (`ma_don_vi`, `ten_don_vi`, `loai_don_vi`, `ma_truong_khoa`, `ma_pho_khoa`) VALUES
(1, 'Phòng Đào tạo', 'Phòng ban', NULL, NULL),
(2, 'Phòng Tuyển sinh', 'Phòng ban', NULL, NULL),
(3, 'Phòng Công tác Sinh viên', 'Phòng ban', NULL, NULL),
(4, 'Phòng Tổ chức Hành chính', 'Phòng ban', NULL, NULL),
(5, 'Phòng Kế hoạch - Tài chính', 'Phòng ban', NULL, NULL),
(6, 'Phòng Khảo thí - Đảm bảo chất lượng', 'Phòng ban', NULL, NULL),
(7, 'Phòng Truyền thông', 'Phòng ban', NULL, NULL),
(8, 'Phòng Công nghệ thông tin', 'Phòng ban', NULL, NULL),
(9, 'Khoa Công nghệ Thông tin - Điện tử', 'Khoa', NULL, NULL),
(10, 'Khoa Kinh tế', 'Khoa', NULL, NULL),
(11, 'Khoa Đại cương', 'Khoa', NULL, NULL),
(12, 'Trung tâm Quan hệ Doanh nghiệp và Hỗ trợ Sinh viên', 'Phòng ban', NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoat_dong`
--

CREATE TABLE `hoat_dong` (
  `ma_hoat_dong` int(11) NOT NULL,
  `ten_hoat_dong` varchar(255) NOT NULL,
  `mo_ta` text DEFAULT NULL,
  `thoi_gian_bat_dau` datetime DEFAULT NULL,
  `thoi_gian_ket_thuc` datetime DEFAULT NULL,
  `diem_cong` int(11) DEFAULT 0,
  `ma_tieu_chi` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoc_ky`
--

CREATE TABLE `hoc_ky` (
  `ma_hoc_ky` int(11) NOT NULL,
  `ten_hoc_ky` varchar(50) NOT NULL,
  `nam_hoc` varchar(20) NOT NULL,
  `trang_thai` varchar(20) DEFAULT 'Mở'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khieu_nai`
--

CREATE TABLE `khieu_nai` (
  `ma_khieu_nai` int(11) NOT NULL,
  `ma_phieu_diem` int(11) NOT NULL,
  `ma_tieu_chi` int(11) NOT NULL,
  `noi_dung_khieu_nai` text NOT NULL,
  `link_minh_chung` varchar(500) DEFAULT NULL,
  `trang_thai_duyet` varchar(50) DEFAULT 'Chờ duyệt',
  `ly_do_tu_choi` text DEFAULT NULL,
  `ngay_tao` datetime DEFAULT current_timestamp(),
  `nguoi_duyet_id` int(11) DEFAULT NULL,
  `ngay_duyet` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `lop`
--

CREATE TABLE `lop` (
  `ma_lop` varchar(20) NOT NULL,
  `ten_lop` varchar(255) NOT NULL,
  `ma_don_vi` int(11) NOT NULL,
  `ma_cvht` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nguoi_dung`
--

CREATE TABLE `nguoi_dung` (
  `ma_nguoi_dung` int(11) NOT NULL,
  `ten_dang_nhap` varchar(50) NOT NULL,
  `mat_khau` varchar(255) NOT NULL,
  `ho_ten` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `trang_thai` varchar(20) DEFAULT 'Hoạt động',
  `yeu_cau_doi_mat_khau` tinyint(1) DEFAULT 1,
  `ma_vai_tro` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nguoi_dung`
--

INSERT INTO `nguoi_dung` (`ma_nguoi_dung`, `ten_dang_nhap`, `mat_khau`, `ho_ten`, `email`, `trang_thai`, `yeu_cau_doi_mat_khau`, `ma_vai_tro`) VALUES
(1, 'sysadmin', '$2a$10$wYQWb1n.yT2E/5T8F.jPLeqDYK1mciltvtcmb0yHVLMuivJoVtHZyvfr', 'Administrator', 'admin@school.edu.vn', 'Hoạt động', 0, 6);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhat_ky_he_thong`
--

CREATE TABLE `nhat_ky_he_thong` (
  `ma_nhat_ky` int(11) NOT NULL,
  `ma_nguoi_dung` int(11) DEFAULT NULL,
  `hanh_dong` varchar(255) NOT NULL,
  `doi_tuong_tac_dong` varchar(255) DEFAULT NULL,
  `ghi_chu` text DEFAULT NULL,
  `thoi_gian` datetime DEFAULT current_timestamp(),
  `ip_thiet_bi` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieu_diem`
--

CREATE TABLE `phieu_diem` (
  `ma_phieu_diem` int(11) NOT NULL,
  `ma_sinh_vien` varchar(20) NOT NULL,
  `ma_hoc_ky` int(11) NOT NULL,
  `diem_hoc_tap` float DEFAULT 0,
  `diem_hoc_tap_quy_doi` int(11) DEFAULT 0,
  `diem_hoat_dong_tam_tinh` int(11) DEFAULT 0,
  `diem_tong_hop` int(11) DEFAULT 0,
  `xep_loai` varchar(50) DEFAULT NULL,
  `trang_thai` varchar(50) DEFAULT 'Chờ khiếu nại'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sinh_vien`
--

CREATE TABLE `sinh_vien` (
  `ma_sinh_vien` varchar(20) NOT NULL,
  `ma_nguoi_dung` int(11) NOT NULL,
  `ma_lop` varchar(20) NOT NULL,
  `nien_khoa` varchar(20) DEFAULT NULL,
  `he_dao_tao` varchar(50) DEFAULT NULL,
  `chuc_vu` varchar(50) DEFAULT 'Sinh viên'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tham_gia_hoat_dong`
--

CREATE TABLE `tham_gia_hoat_dong` (
  `ma_sinh_vien` varchar(20) NOT NULL,
  `ma_hoat_dong` int(11) NOT NULL,
  `thoi_gian_diem_danh` datetime DEFAULT NULL,
  `trang_thai` varchar(50) DEFAULT 'Đã tham gia'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tieu_chi`
--

CREATE TABLE `tieu_chi` (
  `ma_tieu_chi` int(11) NOT NULL,
  `ma_tieu_chi_cha` int(11) DEFAULT NULL,
  `ten_tieu_chi` varchar(500) NOT NULL,
  `diem_toi_da` int(11) NOT NULL,
  `loai_du_lieu` varchar(50) DEFAULT NULL,
  `thu_tu` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `vai_tro`
--

CREATE TABLE `vai_tro` (
  `ma_vai_tro` int(11) NOT NULL,
  `ten_vai_tro` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `vai_tro`
--

INSERT INTO `vai_tro` (`ma_vai_tro`, `ten_vai_tro`) VALUES
(1, 'SINH_VIÊN'),
(2, 'BAN_CÁN_SỰ'),
(3, 'CVHT'),
(4, 'ADMIN_CTSV'),
(5, 'ĐƠN_VỊ_TỔ_CHỨC'),
(6, 'SYSADMIN');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chi_tiet_phieu_diem`
--
ALTER TABLE `chi_tiet_phieu_diem`
  ADD PRIMARY KEY (`ma_phieu_diem`,`ma_tieu_chi`),
  ADD KEY `ma_tieu_chi` (`ma_tieu_chi`);

--
-- Chỉ mục cho bảng `don_vi`
--
ALTER TABLE `don_vi`
  ADD PRIMARY KEY (`ma_don_vi`),
  ADD KEY `ma_truong_khoa` (`ma_truong_khoa`),
  ADD KEY `ma_pho_khoa` (`ma_pho_khoa`);

--
-- Chỉ mục cho bảng `hoat_dong`
--
ALTER TABLE `hoat_dong`
  ADD PRIMARY KEY (`ma_hoat_dong`),
  ADD KEY `ma_tieu_chi` (`ma_tieu_chi`);

--
-- Chỉ mục cho bảng `hoc_ky`
--
ALTER TABLE `hoc_ky`
  ADD PRIMARY KEY (`ma_hoc_ky`);

--
-- Chỉ mục cho bảng `khieu_nai`
--
ALTER TABLE `khieu_nai`
  ADD PRIMARY KEY (`ma_khieu_nai`),
  ADD KEY `ma_phieu_diem` (`ma_phieu_diem`),
  ADD KEY `ma_tieu_chi` (`ma_tieu_chi`),
  ADD KEY `nguoi_duyet_id` (`nguoi_duyet_id`);

--
-- Chỉ mục cho bảng `lop`
--
ALTER TABLE `lop`
  ADD PRIMARY KEY (`ma_lop`),
  ADD KEY `ma_don_vi` (`ma_don_vi`),
  ADD KEY `ma_cvht` (`ma_cvht`);

--
-- Chỉ mục cho bảng `nguoi_dung`
--
ALTER TABLE `nguoi_dung`
  ADD PRIMARY KEY (`ma_nguoi_dung`),
  ADD UNIQUE KEY `ten_dang_nhap` (`ten_dang_nhap`),
  ADD KEY `ma_vai_tro` (`ma_vai_tro`);

--
-- Chỉ mục cho bảng `nhat_ky_he_thong`
--
ALTER TABLE `nhat_ky_he_thong`
  ADD PRIMARY KEY (`ma_nhat_ky`),
  ADD KEY `ma_nguoi_dung` (`ma_nguoi_dung`);

--
-- Chỉ mục cho bảng `phieu_diem`
--
ALTER TABLE `phieu_diem`
  ADD PRIMARY KEY (`ma_phieu_diem`),
  ADD UNIQUE KEY `uk_sinhvien_hocky` (`ma_sinh_vien`,`ma_hoc_ky`),
  ADD KEY `ma_hoc_ky` (`ma_hoc_ky`);

--
-- Chỉ mục cho bảng `sinh_vien`
--
ALTER TABLE `sinh_vien`
  ADD PRIMARY KEY (`ma_sinh_vien`),
  ADD UNIQUE KEY `ma_nguoi_dung` (`ma_nguoi_dung`),
  ADD KEY `ma_lop` (`ma_lop`);

--
-- Chỉ mục cho bảng `tham_gia_hoat_dong`
--
ALTER TABLE `tham_gia_hoat_dong`
  ADD PRIMARY KEY (`ma_sinh_vien`,`ma_hoat_dong`),
  ADD KEY `ma_hoat_dong` (`ma_hoat_dong`);

--
-- Chỉ mục cho bảng `tieu_chi`
--
ALTER TABLE `tieu_chi`
  ADD PRIMARY KEY (`ma_tieu_chi`),
  ADD KEY `ma_tieu_chi_cha` (`ma_tieu_chi_cha`);

--
-- Chỉ mục cho bảng `vai_tro`
--
ALTER TABLE `vai_tro`
  ADD PRIMARY KEY (`ma_vai_tro`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `don_vi`
--
ALTER TABLE `don_vi`
  MODIFY `ma_don_vi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT cho bảng `hoat_dong`
--
ALTER TABLE `hoat_dong`
  MODIFY `ma_hoat_dong` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `hoc_ky`
--
ALTER TABLE `hoc_ky`
  MODIFY `ma_hoc_ky` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `khieu_nai`
--
ALTER TABLE `khieu_nai`
  MODIFY `ma_khieu_nai` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `nguoi_dung`
--
ALTER TABLE `nguoi_dung`
  MODIFY `ma_nguoi_dung` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `nhat_ky_he_thong`
--
ALTER TABLE `nhat_ky_he_thong`
  MODIFY `ma_nhat_ky` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `phieu_diem`
--
ALTER TABLE `phieu_diem`
  MODIFY `ma_phieu_diem` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `tieu_chi`
--
ALTER TABLE `tieu_chi`
  MODIFY `ma_tieu_chi` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `vai_tro`
--
ALTER TABLE `vai_tro`
  MODIFY `ma_vai_tro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chi_tiet_phieu_diem`
--
ALTER TABLE `chi_tiet_phieu_diem`
  ADD CONSTRAINT `chi_tiet_phieu_diem_ibfk_1` FOREIGN KEY (`ma_phieu_diem`) REFERENCES `phieu_diem` (`ma_phieu_diem`) ON DELETE CASCADE,
  ADD CONSTRAINT `chi_tiet_phieu_diem_ibfk_2` FOREIGN KEY (`ma_tieu_chi`) REFERENCES `tieu_chi` (`ma_tieu_chi`);

--
-- Các ràng buộc cho bảng `don_vi`
--
ALTER TABLE `don_vi`
  ADD CONSTRAINT `don_vi_ibfk_1` FOREIGN KEY (`ma_truong_khoa`) REFERENCES `nguoi_dung` (`ma_nguoi_dung`),
  ADD CONSTRAINT `don_vi_ibfk_2` FOREIGN KEY (`ma_pho_khoa`) REFERENCES `nguoi_dung` (`ma_nguoi_dung`);

--
-- Các ràng buộc cho bảng `hoat_dong`
--
ALTER TABLE `hoat_dong`
  ADD CONSTRAINT `hoat_dong_ibfk_1` FOREIGN KEY (`ma_tieu_chi`) REFERENCES `tieu_chi` (`ma_tieu_chi`);

--
-- Các ràng buộc cho bảng `khieu_nai`
--
ALTER TABLE `khieu_nai`
  ADD CONSTRAINT `khieu_nai_ibfk_1` FOREIGN KEY (`ma_phieu_diem`) REFERENCES `phieu_diem` (`ma_phieu_diem`) ON DELETE CASCADE,
  ADD CONSTRAINT `khieu_nai_ibfk_2` FOREIGN KEY (`ma_tieu_chi`) REFERENCES `tieu_chi` (`ma_tieu_chi`),
  ADD CONSTRAINT `khieu_nai_ibfk_3` FOREIGN KEY (`nguoi_duyet_id`) REFERENCES `nguoi_dung` (`ma_nguoi_dung`);

--
-- Các ràng buộc cho bảng `lop`
--
ALTER TABLE `lop`
  ADD CONSTRAINT `lop_ibfk_1` FOREIGN KEY (`ma_don_vi`) REFERENCES `don_vi` (`ma_don_vi`),
  ADD CONSTRAINT `lop_ibfk_2` FOREIGN KEY (`ma_cvht`) REFERENCES `nguoi_dung` (`ma_nguoi_dung`);

--
-- Các ràng buộc cho bảng `nguoi_dung`
--
ALTER TABLE `nguoi_dung`
  ADD CONSTRAINT `nguoi_dung_ibfk_1` FOREIGN KEY (`ma_vai_tro`) REFERENCES `vai_tro` (`ma_vai_tro`);

--
-- Các ràng buộc cho bảng `nhat_ky_he_thong`
--
ALTER TABLE `nhat_ky_he_thong`
  ADD CONSTRAINT `nhat_ky_he_thong_ibfk_1` FOREIGN KEY (`ma_nguoi_dung`) REFERENCES `nguoi_dung` (`ma_nguoi_dung`) ON DELETE SET NULL;

--
-- Các ràng buộc cho bảng `phieu_diem`
--
ALTER TABLE `phieu_diem`
  ADD CONSTRAINT `phieu_diem_ibfk_1` FOREIGN KEY (`ma_sinh_vien`) REFERENCES `sinh_vien` (`ma_sinh_vien`),
  ADD CONSTRAINT `phieu_diem_ibfk_2` FOREIGN KEY (`ma_hoc_ky`) REFERENCES `hoc_ky` (`ma_hoc_ky`);

--
-- Các ràng buộc cho bảng `sinh_vien`
--
ALTER TABLE `sinh_vien`
  ADD CONSTRAINT `sinh_vien_ibfk_1` FOREIGN KEY (`ma_nguoi_dung`) REFERENCES `nguoi_dung` (`ma_nguoi_dung`),
  ADD CONSTRAINT `sinh_vien_ibfk_2` FOREIGN KEY (`ma_lop`) REFERENCES `lop` (`ma_lop`);

--
-- Các ràng buộc cho bảng `tham_gia_hoat_dong`
--
ALTER TABLE `tham_gia_hoat_dong`
  ADD CONSTRAINT `tham_gia_hoat_dong_ibfk_1` FOREIGN KEY (`ma_sinh_vien`) REFERENCES `sinh_vien` (`ma_sinh_vien`),
  ADD CONSTRAINT `tham_gia_hoat_dong_ibfk_2` FOREIGN KEY (`ma_hoat_dong`) REFERENCES `hoat_dong` (`ma_hoat_dong`);

--
-- Các ràng buộc cho bảng `tieu_chi`
--
ALTER TABLE `tieu_chi`
  ADD CONSTRAINT `tieu_chi_ibfk_1` FOREIGN KEY (`ma_tieu_chi_cha`) REFERENCES `tieu_chi` (`ma_tieu_chi`);
--
-- Cơ sở dữ liệu: `quanlykhachsan`
--
CREATE DATABASE IF NOT EXISTS `quanlykhachsan` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `quanlykhachsan`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `makh` varchar(10) NOT NULL,
  `hoten` varchar(100) NOT NULL,
  `sdt` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`makh`),
  ADD UNIQUE KEY `idx_sdt` (`sdt`);
--
-- Cơ sở dữ liệu: `quanlykhacsan`
--
CREATE DATABASE IF NOT EXISTS `quanlykhacsan` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `quanlykhacsan`;
--
-- Cơ sở dữ liệu: `shop_quanao`
--
CREATE DATABASE IF NOT EXISTS `shop_quanao` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `shop_quanao`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `brands`
--

CREATE TABLE `brands` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `brands`
--

INSERT INTO `brands` (`id`, `name`, `logo`, `created_at`) VALUES
(1, 'Nike', 'nike.png', '2026-06-15 01:21:26'),
(2, 'Adidas', 'adidas.png', '2026-06-15 01:21:26'),
(3, 'Puma', 'puma.png', '2026-06-15 01:21:26'),
(4, 'Uniqlo', 'uniqlo.png', '2026-06-15 01:21:26'),
(5, 'Zara', 'zara.png', '2026-06-15 01:21:26'),
(6, 'H&M', 'hm.png', '2026-06-15 01:21:26'),
(7, 'Routine', 'routine.png', '2026-06-15 01:21:26'),
(8, 'Yody', 'yody.png', '2026-06-15 01:21:26'),
(9, 'Canifa', 'canifa.png', '2026-06-15 01:21:26'),
(10, 'Việt Tiến', 'viettien.png', '2026-06-15 01:21:26');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `carts`
--

CREATE TABLE `carts` (
  `id` bigint(20) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart_items`
--

CREATE TABLE `cart_items` (
  `id` bigint(20) NOT NULL,
  `cart_id` bigint(20) NOT NULL,
  `variant_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `sort` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `categories`
--

INSERT INTO `categories` (`id`, `name`, `url`, `sort`, `description`, `created_at`) VALUES
(1, 'Áo thun', 'ao-thun', '0', 'Các loại áo thun', '2026-06-15 01:21:26'),
(2, 'Áo sơ mi', 'ao-so-mi', '0', 'Các loại áo sơ mi', '2026-06-15 01:21:26'),
(3, 'Quần jean', 'quan-jean', '0', 'Các loại quần jean', '2026-06-15 01:21:26'),
(4, 'Quần tây', 'quan-tay', '0', 'Các loại quần tây', '2026-06-15 01:21:26'),
(5, 'Áo khoác', 'ao-khoac', '0', 'Các loại áo khoác', '2026-06-15 01:21:26'),
(6, 'Váy', 'vay', '0', 'Các loại váy', '2026-06-15 01:21:26'),
(7, 'Đồ thể thao', 'do-the-thao', '0', 'Trang phục thể thao', '2026-06-15 01:21:26'),
(8, 'Áo hoodie', 'ao-hoodie', '0', 'Áo hoodie thời trang', '2026-06-15 01:21:26'),
(9, 'Đồ mặc nhà', 'do-mac-nha', '0', 'Trang phục mặc nhà', '2026-06-15 01:21:26'),
(10, 'Phụ kiện', 'phu-kien', '0', 'Các loại phụ kiện', '2026-06-15 01:21:26'),
(11, 'Trang chủ', '', '1', 'Trang chủ', '2026-06-15 01:46:14');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `colors`
--

CREATE TABLE `colors` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `color_code` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `colors`
--

INSERT INTO `colors` (`id`, `name`, `color_code`) VALUES
(1, 'Đen', '#000000'),
(2, 'Trắng', '#FFFFFF'),
(3, 'Đỏ', '#FF0000'),
(4, 'Xanh dương', '#0000FF'),
(5, 'Xanh lá', '#00FF00'),
(6, 'Vàng', '#FFFF00'),
(7, 'Xám', '#808080'),
(8, 'Hồng', '#FFC0CB'),
(9, 'Nâu', '#8B4513'),
(10, 'Cam', '#FFA500');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `coupons`
--

CREATE TABLE `coupons` (
  `id` int(11) NOT NULL,
  `code` varchar(50) DEFAULT NULL,
  `discount_percent` decimal(5,2) DEFAULT NULL,
  `max_discount` decimal(12,2) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `coupons`
--

INSERT INTO `coupons` (`id`, `code`, `discount_percent`, `max_discount`, `start_date`, `end_date`, `quantity`) VALUES
(1, 'SALE10', 10.00, 100000.00, '2026-01-01 00:00:00', '2026-12-31 00:00:00', 100),
(2, 'SALE15', 15.00, 150000.00, '2026-01-01 00:00:00', '2026-12-31 00:00:00', 100),
(3, 'SALE20', 20.00, 200000.00, '2026-01-01 00:00:00', '2026-12-31 00:00:00', 100),
(4, 'VIP10', 10.00, 100000.00, '2026-01-01 00:00:00', '2026-12-31 00:00:00', 50),
(5, 'VIP15', 15.00, 150000.00, '2026-01-01 00:00:00', '2026-12-31 00:00:00', 50),
(6, 'NEWUSER', 5.00, 50000.00, '2026-01-01 00:00:00', '2026-12-31 00:00:00', 200),
(7, 'FREESHIP', 3.00, 30000.00, '2026-01-01 00:00:00', '2026-12-31 00:00:00', 150),
(8, 'SUMMER', 12.00, 120000.00, '2026-01-01 00:00:00', '2026-12-31 00:00:00', 100),
(9, 'WINTER', 18.00, 180000.00, '2026-01-01 00:00:00', '2026-12-31 00:00:00', 100),
(10, 'SPECIAL', 25.00, 250000.00, '2026-01-01 00:00:00', '2026-12-31 00:00:00', 30);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customers`
--

CREATE TABLE `customers` (
  `id` int(11) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password_hash` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `customers`
--

INSERT INTO `customers` (`id`, `full_name`, `phone`, `email`, `password_hash`, `address`, `created_at`) VALUES
(1, 'Nguyễn Văn A', '0900000001', 'a@gmail.com', '123456', 'HCM', '2026-06-15 01:21:26'),
(2, 'Trần Văn B', '0900000002', 'b@gmail.com', '123456', 'HCM', '2026-06-15 01:21:26'),
(3, 'Lê Văn C', '0900000003', 'c@gmail.com', '123456', 'Đà Nẵng', '2026-06-15 01:21:26'),
(4, 'Phạm Văn D', '0900000004', 'd@gmail.com', '123456', 'Hà Nội', '2026-06-15 01:21:26'),
(5, 'Hoàng Văn E', '0900000005', 'e@gmail.com', '123456', 'Cần Thơ', '2026-06-15 01:21:26'),
(6, 'Nguyễn Thị F', '0900000006', 'f@gmail.com', '123456', 'HCM', '2026-06-15 01:21:26'),
(7, 'Trần Thị G', '0900000007', 'g@gmail.com', '123456', 'Đồng Nai', '2026-06-15 01:21:26'),
(8, 'Lê Thị H', '0900000008', 'h@gmail.com', '123456', 'Bình Dương', '2026-06-15 01:21:26'),
(9, 'Phạm Thị I', '0900000009', 'i@gmail.com', '123456', 'Long An', '2026-06-15 01:21:26'),
(10, 'Hoàng Thị K', '0900000010', 'k@gmail.com', '123456', 'Vũng Tàu', '2026-06-15 01:21:26');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `employees`
--

CREATE TABLE `employees` (
  `id` int(11) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password_hash` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','MANAGER','STAFF') DEFAULT 'STAFF',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `employees`
--

INSERT INTO `employees` (`id`, `full_name`, `username`, `password_hash`, `role`, `created_at`) VALUES
(1, 'Admin System', 'admin', '$2a$10$X.xM.zF1b9.BvR.a0XU5X.eFv0a21o0f/3N.f7e52P.qF.71W2iKq', 'ADMIN', '2026-06-15 01:21:26'),
(2, 'Manager 1', 'manager1', '123456', 'MANAGER', '2026-06-15 01:21:26'),
(3, 'Staff 1', 'staff1', '123456', 'STAFF', '2026-06-15 01:21:26'),
(4, 'Staff 2', 'staff2', '123456', 'STAFF', '2026-06-15 01:21:26'),
(5, 'Staff 3', 'staff3', '123456', 'STAFF', '2026-06-15 01:21:26'),
(6, 'Staff 4', 'staff4', '123456', 'STAFF', '2026-06-15 01:21:26'),
(7, 'Staff 5', 'staff5', '123456', 'STAFF', '2026-06-15 01:21:26'),
(8, 'Staff 6', 'staff6', '123456', 'STAFF', '2026-06-15 01:21:26'),
(9, 'Staff 7', 'staff7', '123456', 'STAFF', '2026-06-15 01:21:26'),
(10, 'Staff 8', 'staff8', '123456', 'STAFF', '2026-06-15 01:21:26');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `coupon_id` int(11) DEFAULT NULL,
  `order_date` datetime DEFAULT current_timestamp(),
  `total_amount` decimal(12,2) DEFAULT NULL,
  `discount_amount` decimal(12,2) DEFAULT NULL,
  `final_amount` decimal(12,2) DEFAULT NULL,
  `status` enum('PENDING','CONFIRMED','SHIPPING','COMPLETED','CANCELLED') DEFAULT 'PENDING',
  `shipping_address` text DEFAULT NULL,
  `note` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `orders`
--

INSERT INTO `orders` (`id`, `customer_id`, `coupon_id`, `order_date`, `total_amount`, `discount_amount`, `final_amount`, `status`, `shipping_address`, `note`) VALUES
(1, 1, 1, '2026-06-15 08:21:26', 500000.00, 50000.00, 450000.00, 'COMPLETED', 'HCM', NULL),
(2, 2, 2, '2026-06-15 08:21:26', 700000.00, 105000.00, 595000.00, 'COMPLETED', 'HCM', NULL),
(3, 3, 3, '2026-06-15 08:21:26', 900000.00, 180000.00, 720000.00, 'SHIPPING', 'Đà Nẵng', NULL),
(4, 4, NULL, '2026-06-15 08:21:26', 450000.00, 0.00, 450000.00, 'PENDING', 'Hà Nội', NULL),
(5, 5, 1, '2026-06-15 08:21:26', 800000.00, 80000.00, 720000.00, 'COMPLETED', 'Cần Thơ', NULL),
(6, 6, 2, '2026-06-15 08:21:26', 650000.00, 97500.00, 552500.00, 'CONFIRMED', 'HCM', NULL),
(7, 7, NULL, '2026-06-15 08:21:26', 350000.00, 0.00, 350000.00, 'PENDING', 'Đồng Nai', NULL),
(8, 8, 5, '2026-06-15 08:21:26', 1000000.00, 150000.00, 850000.00, 'COMPLETED', 'Bình Dương', NULL),
(9, 9, 6, '2026-06-15 08:21:26', 500000.00, 25000.00, 475000.00, 'SHIPPING', 'Long An', NULL),
(10, 10, 7, '2026-06-15 08:21:26', 600000.00, 18000.00, 582000.00, 'COMPLETED', 'Vũng Tàu', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order_details`
--

CREATE TABLE `order_details` (
  `id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `variant_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` decimal(12,2) NOT NULL,
  `subtotal` decimal(12,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `order_details`
--

INSERT INTO `order_details` (`id`, `order_id`, `variant_id`, `quantity`, `price`, `subtotal`) VALUES
(1, 1, 1, 2, 220000.00, 440000.00),
(2, 2, 2, 2, 220000.00, 440000.00),
(3, 3, 3, 3, 270000.00, 810000.00),
(4, 4, 4, 1, 400000.00, 400000.00),
(5, 5, 5, 1, 500000.00, 500000.00),
(6, 6, 6, 1, 550000.00, 550000.00),
(7, 7, 7, 1, 650000.00, 650000.00),
(8, 8, 8, 2, 620000.00, 1240000.00),
(9, 9, 9, 1, 450000.00, 450000.00),
(10, 10, 10, 1, 400000.00, 400000.00);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `payments`
--

CREATE TABLE `payments` (
  `id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `payment_method` enum('COD','BANKING','MOMO','ZALOPAY','VNPAY') DEFAULT NULL,
  `amount` decimal(12,2) DEFAULT NULL,
  `payment_date` datetime DEFAULT NULL,
  `status` enum('PENDING','PAID','FAILED') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `payments`
--

INSERT INTO `payments` (`id`, `order_id`, `payment_method`, `amount`, `payment_date`, `status`) VALUES
(1, 1, 'COD', 450000.00, '2026-06-15 08:21:26', 'PAID'),
(2, 2, 'BANKING', 595000.00, '2026-06-15 08:21:26', 'PAID'),
(3, 3, 'MOMO', 720000.00, '2026-06-15 08:21:26', 'PAID'),
(4, 4, 'COD', 450000.00, NULL, 'PENDING'),
(5, 5, 'VNPAY', 720000.00, '2026-06-15 08:21:26', 'PAID'),
(6, 6, 'ZALOPAY', 552500.00, '2026-06-15 08:21:26', 'PAID'),
(7, 7, 'COD', 350000.00, NULL, 'PENDING'),
(8, 8, 'BANKING', 850000.00, '2026-06-15 08:21:26', 'PAID'),
(9, 9, 'MOMO', 475000.00, '2026-06-15 08:21:26', 'PAID'),
(10, 10, 'VNPAY', 582000.00, '2026-06-15 08:21:26', 'PAID');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `brand_id` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `description` text DEFAULT NULL,
  `material` varchar(100) DEFAULT NULL,
  `gender` enum('Nam','Nữ','Unisex') DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE') DEFAULT 'ACTIVE',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`id`, `category_id`, `brand_id`, `name`, `description`, `material`, `gender`, `status`, `created_at`) VALUES
(1, 1, 1, 'Áo thun Nike Basic', 'Áo thun nam', 'Cotton', 'Nam', 'ACTIVE', '2026-06-15 01:21:26'),
(2, 1, 2, 'Áo thun Adidas Sport', 'Áo thể thao', 'Polyester', 'Nam', 'ACTIVE', '2026-06-15 01:21:26'),
(3, 2, 10, 'Áo sơ mi Việt Tiến', 'Sơ mi công sở', 'Cotton', 'Nam', 'ACTIVE', '2026-06-15 01:21:26'),
(4, 3, 5, 'Quần Jean Zara', 'Jean thời trang', 'Jean', 'Unisex', 'ACTIVE', '2026-06-15 01:21:26'),
(5, 4, 10, 'Quần Tây Việt Tiến', 'Quần công sở', 'Kaki', 'Nam', 'ACTIVE', '2026-06-15 01:21:26'),
(6, 5, 4, 'Áo khoác Uniqlo', 'Áo khoác chống nắng', 'Dù', 'Unisex', 'ACTIVE', '2026-06-15 01:21:26'),
(7, 6, 5, 'Váy Zara Flower', 'Váy nữ', 'Lụa', 'Nữ', 'ACTIVE', '2026-06-15 01:21:26'),
(8, 7, 3, 'Bộ thể thao Puma', 'Đồ thể thao', 'Polyester', 'Unisex', 'ACTIVE', '2026-06-15 01:21:26'),
(9, 8, 6, 'Áo Hoodie H&M', 'Hoodie trẻ trung', 'Nỉ', 'Unisex', 'ACTIVE', '2026-06-15 01:21:26'),
(10, 9, 8, 'Đồ mặc nhà Yody', 'Mặc nhà thoải mái', 'Cotton', 'Unisex', 'ACTIVE', '2026-06-15 01:21:26');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product_images`
--

CREATE TABLE `product_images` (
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `product_images`
--

INSERT INTO `product_images` (`id`, `product_id`, `image_url`) VALUES
(1, 1, 'https://images.pexels.com/photos/996329/pexels-photo-996329.jpeg'),
(2, 1, 'https://images.pexels.com/photos/428340/pexels-photo-428340.jpeg'),
(3, 2, 'https://images.pexels.com/photos/1040945/pexels-photo-1040945.jpeg'),
(4, 3, 'https://images.pexels.com/photos/428338/pexels-photo-428338.jpeg'),
(5, 4, 'https://images.pexels.com/photos/1598505/pexels-photo-1598505.jpeg'),
(6, 5, 'https://images.pexels.com/photos/6311392/pexels-photo-6311392.jpeg'),
(7, 6, 'https://images.pexels.com/photos/7679720/pexels-photo-7679720.jpeg'),
(8, 7, 'https://images.pexels.com/photos/291762/pexels-photo-291762.jpeg'),
(9, 8, 'https://images.pexels.com/photos/2529148/pexels-photo-2529148.jpeg'),
(10, 9, 'https://images.pexels.com/photos/1124465/pexels-photo-1124465.jpeg'),
(11, 10, 'https://images.pexels.com/photos/6311605/pexels-photo-6311605.jpeg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product_variants`
--

CREATE TABLE `product_variants` (
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `color_id` int(11) NOT NULL,
  `size_id` int(11) NOT NULL,
  `sku` varchar(100) DEFAULT NULL,
  `price` decimal(12,2) NOT NULL,
  `sale_price` decimal(12,2) DEFAULT NULL,
  `stock` int(11) DEFAULT 0,
  `image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `product_variants`
--

INSERT INTO `product_variants` (`id`, `product_id`, `color_id`, `size_id`, `sku`, `price`, `sale_price`, `stock`, `image`) VALUES
(1, 1, 1, 3, 'NK001-BLK-M', 250000.00, 220000.00, 100, 'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab'),
(2, 1, 2, 4, 'NK001-WHT-L', 250000.00, 220000.00, 80, 'https://images.unsplash.com/photo-1576566588028-4147f3842f27'),
(3, 2, 4, 3, 'AD001-BLU-M', 300000.00, 270000.00, 50, 'https://images.unsplash.com/photo-1512436991641-6745cdb1723f'),
(4, 3, 2, 4, 'VT001-WHT-L', 450000.00, 400000.00, 60, 'https://images.unsplash.com/photo-1603252109303-2751441dd157'),
(5, 4, 4, 9, 'ZR001-BLU-32', 550000.00, 500000.00, 40, 'https://images.unsplash.com/photo-1542272604-787c3835535d'),
(6, 5, 1, 4, 'VT002-BLK-L', 600000.00, 550000.00, 30, 'https://images.unsplash.com/photo-1473966968600-fa801b869a1a'),
(7, 6, 7, 5, 'UQ001-GRY-XL', 700000.00, 650000.00, 25, 'https://images.unsplash.com/photo-1523398002811-999ca8dec234'),
(8, 7, 8, 3, 'ZR002-PNK-M', 650000.00, 620000.00, 20, 'https://images.unsplash.com/photo-1496747611176-843222e1e57c'),
(9, 8, 3, 4, 'PM001-RED-L', 500000.00, 450000.00, 35, 'https://images.unsplash.com/photo-1515886657613-9f3515b0c78f'),
(10, 9, 1, 5, 'HM001-BLK-XL', 450000.00, 400000.00, 45, 'https://images.unsplash.com/photo-1503342217505-b0a15ec3261c');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `reviews`
--

CREATE TABLE `reviews` (
  `id` bigint(20) NOT NULL,
  `product_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `rating` int(11) DEFAULT NULL CHECK (`rating` between 1 and 5),
  `comment` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `reviews`
--

INSERT INTO `reviews` (`id`, `product_id`, `customer_id`, `rating`, `comment`, `created_at`) VALUES
(1, 1, 1, 5, 'Rất đẹp', '2026-06-15 01:21:26'),
(2, 2, 2, 4, 'Mặc thoải mái', '2026-06-15 01:21:26'),
(3, 3, 3, 5, 'Chất lượng tốt', '2026-06-15 01:21:26'),
(4, 4, 4, 4, 'Đúng mô tả', '2026-06-15 01:21:26'),
(5, 5, 5, 5, 'Rất hài lòng', '2026-06-15 01:21:26'),
(6, 6, 6, 3, 'Tạm ổn', '2026-06-15 01:21:26'),
(7, 7, 7, 4, 'Đẹp', '2026-06-15 01:21:26'),
(8, 8, 8, 5, 'Rất thích', '2026-06-15 01:21:26'),
(9, 9, 9, 4, 'Mặc vừa', '2026-06-15 01:21:26'),
(10, 10, 10, 5, 'Sẽ mua lại', '2026-06-15 01:21:26');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sizes`
--

CREATE TABLE `sizes` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `sizes`
--

INSERT INTO `sizes` (`id`, `name`) VALUES
(1, 'XS'),
(2, 'S'),
(3, 'M'),
(4, 'L'),
(5, 'XL'),
(6, 'XXL'),
(7, '28'),
(8, '30'),
(9, '32'),
(10, '34');

-- --------------------------------------------------------

--
-- Cấu trúc đóng vai cho view `vw_product_list`
-- (See below for the actual view)
--
CREATE TABLE `vw_product_list` (
`id` int(11)
,`name` varchar(255)
,`category_id` int(11)
,`category_url` varchar(255)
,`image` varchar(255)
,`price` decimal(12,2)
,`stock` decimal(32,0)
,`status` enum('ACTIVE','INACTIVE')
,`created_at` timestamp
);

-- --------------------------------------------------------

--
-- Cấu trúc cho view `vw_product_list`
--
DROP TABLE IF EXISTS `vw_product_list`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_product_list`  AS SELECT `p`.`id` AS `id`, `p`.`name` AS `name`, `p`.`category_id` AS `category_id`, `c`.`url` AS `category_url`, (select `pi`.`image_url` from `product_images` `pi` where `pi`.`product_id` = `p`.`id` limit 1) AS `image`, (select min(`pv`.`price`) from `product_variants` `pv` where `pv`.`product_id` = `p`.`id`) AS `price`, (select sum(`pv`.`stock`) from `product_variants` `pv` where `pv`.`product_id` = `p`.`id`) AS `stock`, `p`.`status` AS `status`, `p`.`created_at` AS `created_at` FROM (`products` `p` left join `categories` `c` on(`p`.`category_id` = `c`.`id`)) ;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `brands`
--
ALTER TABLE `brands`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `carts`
--
ALTER TABLE `carts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Chỉ mục cho bảng `cart_items`
--
ALTER TABLE `cart_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cart_id` (`cart_id`),
  ADD KEY `variant_id` (`variant_id`);

--
-- Chỉ mục cho bảng `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `colors`
--
ALTER TABLE `colors`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `coupons`
--
ALTER TABLE `coupons`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`);

--
-- Chỉ mục cho bảng `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `coupon_id` (`coupon_id`);

--
-- Chỉ mục cho bảng `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `variant_id` (`variant_id`);

--
-- Chỉ mục cho bảng `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`);

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category_id` (`category_id`),
  ADD KEY `brand_id` (`brand_id`);

--
-- Chỉ mục cho bảng `product_images`
--
ALTER TABLE `product_images`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`);

--
-- Chỉ mục cho bảng `product_variants`
--
ALTER TABLE `product_variants`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `sku` (`sku`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `color_id` (`color_id`),
  ADD KEY `size_id` (`size_id`);

--
-- Chỉ mục cho bảng `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Chỉ mục cho bảng `sizes`
--
ALTER TABLE `sizes`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `brands`
--
ALTER TABLE `brands`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `carts`
--
ALTER TABLE `carts`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `cart_items`
--
ALTER TABLE `cart_items`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT cho bảng `colors`
--
ALTER TABLE `colors`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `coupons`
--
ALTER TABLE `coupons`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `customers`
--
ALTER TABLE `customers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `employees`
--
ALTER TABLE `employees`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `order_details`
--
ALTER TABLE `order_details`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `payments`
--
ALTER TABLE `payments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `product_images`
--
ALTER TABLE `product_images`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT cho bảng `product_variants`
--
ALTER TABLE `product_variants`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `reviews`
--
ALTER TABLE `reviews`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `sizes`
--
ALTER TABLE `sizes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `carts`
--
ALTER TABLE `carts`
  ADD CONSTRAINT `carts_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`);

--
-- Các ràng buộc cho bảng `cart_items`
--
ALTER TABLE `cart_items`
  ADD CONSTRAINT `cart_items_ibfk_1` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`),
  ADD CONSTRAINT `cart_items_ibfk_2` FOREIGN KEY (`variant_id`) REFERENCES `product_variants` (`id`);

--
-- Các ràng buộc cho bảng `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`coupon_id`) REFERENCES `coupons` (`id`);

--
-- Các ràng buộc cho bảng `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `order_details_ibfk_2` FOREIGN KEY (`variant_id`) REFERENCES `product_variants` (`id`);

--
-- Các ràng buộc cho bảng `payments`
--
ALTER TABLE `payments`
  ADD CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Các ràng buộc cho bảng `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  ADD CONSTRAINT `products_ibfk_2` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id`);

--
-- Các ràng buộc cho bảng `product_images`
--
ALTER TABLE `product_images`
  ADD CONSTRAINT `product_images_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Các ràng buộc cho bảng `product_variants`
--
ALTER TABLE `product_variants`
  ADD CONSTRAINT `product_variants_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `product_variants_ibfk_2` FOREIGN KEY (`color_id`) REFERENCES `colors` (`id`),
  ADD CONSTRAINT `product_variants_ibfk_3` FOREIGN KEY (`size_id`) REFERENCES `sizes` (`id`);

--
-- Các ràng buộc cho bảng `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `reviews_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`);
--
-- Cơ sở dữ liệu: `test`
--
CREATE DATABASE IF NOT EXISTS `test` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `test`;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
