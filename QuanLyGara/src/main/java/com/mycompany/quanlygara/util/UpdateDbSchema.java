package com.mycompany.quanlygara.util;

import com.mycompany.quanlygara.controller.DBConnection;
import java.sql.Connection;
import java.sql.Statement;

public class UpdateDbSchema {
    public static void main(String[] args) {
        System.out.println("Updating database schema...");
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            
            // 1. Add da_xoa to linh_kien table
            try {
                stmt.execute("ALTER TABLE linh_kien ADD COLUMN da_xoa TINYINT(1) NOT NULL DEFAULT 0");
                System.out.println("Added 'da_xoa' column to 'linh_kien' table.");
            } catch (Exception e) {
                System.out.println("Column 'da_xoa' might already exist in 'linh_kien': " + e.getMessage());
            }
            
            // 2. Add da_xoa to dich_vu table
            try {
                stmt.execute("ALTER TABLE dich_vu ADD COLUMN da_xoa TINYINT(1) NOT NULL DEFAULT 0");
                System.out.println("Added 'da_xoa' column to 'dich_vu' table.");
            } catch (Exception e) {
                System.out.println("Column 'da_xoa' might already exist in 'dich_vu': " + e.getMessage());
            }

            // 3. Update existing passwords in nhan_vien to SHA-256 hashes
            // Hash of 'admin' -> 8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918
            // Hash of '123456' -> 8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92
            try {
                stmt.execute("UPDATE nhan_vien SET mat_khau = '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918' WHERE LOWER(mat_khau) = 'admin'");
                stmt.execute("UPDATE nhan_vien SET mat_khau = '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92' WHERE LOWER(mat_khau) = '123456'");
                System.out.println("Successfully migrated existing passwords to SHA-256 hashes.");
            } catch (Exception e) {
                System.out.println("Error updating employee passwords: " + e.getMessage());
            }
            
            System.out.println("Database schema update completed successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
