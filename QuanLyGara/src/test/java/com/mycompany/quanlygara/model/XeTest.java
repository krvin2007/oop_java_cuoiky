package com.mycompany.quanlygara.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class XeTest {

    @Test
    public void testVehicleCreation() {
        ChuXe owner = new ChuXe(1, "Nguyen Van A", "0123456789", "Ha Noi", "a@example.com");
        Xe vehicle = new Xe("29A-12345", "Toyota", "Vios", 2020, owner, "Red", "Good");
        
        assertEquals("29A-12345", vehicle.getLicensePlate());
        assertEquals("Toyota", vehicle.getBrand());
        assertEquals("Vios", vehicle.getModel());
        assertEquals(2020, vehicle.getProductionYear());
        assertEquals(owner, vehicle.getOwner());
        assertEquals("Red", vehicle.getColor());
        assertEquals("Good", vehicle.getCondition());
    }
}
