/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlygara.controller;

import java.util.List;

/**
 *
 * @author ManhQuynh
 */
public interface IRepository<T> {
    // Thêm mới một bản ghi vào cơ sở dữ liệu
    void themMoi(T item) throws Exception;
    
    // Cập nhật thông tin một bản ghi đã tồn tại trong cơ sở dữ liệu
    void capNhat(T item) throws Exception;
    
    // Xóa một bản ghi khỏi cơ sở dữ liệu dựa trên mã định danh (ID)
    void xoa(Object id) throws Exception;
    
    // Lấy danh sách tất cả các bản ghi có trong cơ sở dữ liệu
    List<T> layTatCa() throws Exception;
    
    // Lấy thông tin chi tiết của một bản ghi dựa trên mã định danh (ID)
    T layTheoId(Object id) throws Exception;
}

