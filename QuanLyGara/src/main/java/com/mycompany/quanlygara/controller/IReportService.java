/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlygara.controller;

import java.util.Map;

/**
 *
 * @author ManhQuynh
 */
public interface IReportService {
    // Tính tổng doanh thu trong một khoảng thời gian cụ thể (từ ngày đến ngày)
    double getRevenue(String fromDate, String toDate) throws Exception;
    
    // Lấy danh sách các linh kiện được sử dụng nhiều nhất cùng với số lượng tương ứng
    Map<String, Integer> getMostUsedParts(int limit) throws Exception;
    
    // Lấy danh sách các xe được sửa chữa nhiều nhất trong gara
    Map<String, Integer> getMostRepairedVehicles(int limit) throws Exception;
    
    // Lấy danh sách các kỹ thuật viên hoạt động tích cực nhất (xử lý nhiều phiếu nhất)
    Map<String, Integer> getMostActiveMechanics(int limit) throws Exception;
}

