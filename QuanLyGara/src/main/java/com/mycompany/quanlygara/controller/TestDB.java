package com.mycompany.quanlygara.controller;

import java.util.*;

public class TestDB {
    // Phương thức main - Điểm bắt đầu chạy chương trình
    public static void main(String[] args) {
        try {
            ReportController report = new ReportController();
            System.out.println("Testing getMostActiveMechanics...");
            Map<String, Integer> activeMechs = report.getMostActiveMechanics(5);
            System.out.println("Success! Size: " + activeMechs.size());
        } catch (Exception e) {
            System.out.println("Testing getMostActiveMechanics FAILED:");
            e.printStackTrace();
        }
    }
}

