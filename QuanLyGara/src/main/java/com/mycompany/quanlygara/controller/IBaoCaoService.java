
package com.mycompany.quanlygara.controller;

import java.util.Map;


public interface IBaoCaoService {
    
    double getRevenue(String fromDate, String toDate) throws Exception;
    
    
    Map<String, Integer> getMostUsedParts(int limit) throws Exception;
    
    
    Map<String, Integer> getMostRepairedVehicles(int limit) throws Exception;
    
    
    Map<String, Integer> getMostActiveMechanics(int limit) throws Exception;
}

