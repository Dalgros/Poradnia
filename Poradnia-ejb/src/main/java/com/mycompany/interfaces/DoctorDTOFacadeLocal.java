/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interfaces;

import com.mycompany.model.DoctorDTO;
import com.mycompany.model.PatientDTO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Karol
 */
@Local
public interface DoctorDTOFacadeLocal
{

    void create(DoctorDTO doctorDTO);

    void edit(DoctorDTO doctorDTO);

    void remove(DoctorDTO doctorDTO);

    DoctorDTO find(Object id);

    List<DoctorDTO> findAll();

    List<DoctorDTO> findRange(int[] range);

    int count();
    
    DoctorDTO checkUser(String userName, String password);
    
    List<DoctorDTO> findDoctors(PatientDTO patient);
    
}
