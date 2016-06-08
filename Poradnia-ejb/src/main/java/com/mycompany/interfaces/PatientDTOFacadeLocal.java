/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interfaces;

import com.mycompany.model.PatientDTO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Karol
 */
@Local
public interface PatientDTOFacadeLocal
{

    void create(PatientDTO patientDTO);

    void edit(PatientDTO patientDTO);

    void remove(PatientDTO patientDTO);

    PatientDTO find(Object id);

    List<PatientDTO> findAll();

    List<PatientDTO> findRange(int[] range);

    int count();
    
    PatientDTO checkUser(String userName, String password);
    
}
