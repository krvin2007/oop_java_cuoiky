
package com.mycompany.quanlygara.controller;

import java.util.List;


public interface IKhoLuuTru<T> {
    
    void themMoi(T item) throws Exception;
    
    
    void capNhat(T item) throws Exception;
    
    
    void xoa(Object id) throws Exception;
    
    
    List<T> layTatCa() throws Exception;
    
    
    T layTheoId(Object id) throws Exception;
}

