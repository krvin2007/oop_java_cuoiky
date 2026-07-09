package com.mycompany.quanlygara.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ManhQuynh
 */
public class DBConnection {
    // Đường dẫn kết nối cơ sở dữ liệu (Database URL)
    private static final String URL = "jdbc:mysql://localhost:3306/quangara?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
    // Tên đăng nhập cơ sở dữ liệu (Database Username)
    private static final String USER = "root";
    // Mật khẩu cơ sở dữ liệu (Database Password)
    private static final String PASSWORD = "";

    // Lấy giá trị của thuộc tính Connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

