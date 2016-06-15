/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Karol
 */
public class DoctorDTOTest
{
    
    public DoctorDTOTest()
    {
    }

    /**
     * Test of getAddress method, of class DoctorDTO.
     */
    @Test
    public void testGetAddress()
    {
        System.out.println("getAddress");
        DoctorDTO doctor = new DoctorDTO();
        doctor.setAddress("aaaa");
        
        String expResult = "aaaa";
        String notExpResult = "bbbb";
        String result = doctor.getAddress();
        assertEquals(expResult, result);
        assertNotEquals(notExpResult, result);
    }

    /**
     * Test of setAddress method, of class DoctorDTO.
     */
    @Test
    public void testSetAddress()
    {
        String address = "adres";
        DoctorDTO instance = new DoctorDTO();
        instance.setAddress(address);
        
        assert instance.getAddress()!=null;
       
    }

   
    
}
