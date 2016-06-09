/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interfaces;

import com.mycompany.model.DoctorDTO;
import com.mycompany.model.PatientDTO;
import com.mycompany.model.VisitDTO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Karol
 */
@Local
public interface VisitDTOFacadeLocal
{

    void create(VisitDTO visitDTO);

    void edit(VisitDTO visitDTO);

    void remove(VisitDTO visitDTO);

    VisitDTO find(Object id);

    List<VisitDTO> findAll();

    List<VisitDTO> findRange(int[] range);

    int count();
    
    List<VisitDTO> findByPatient(PatientDTO patient);
            
    List<VisitDTO> findByDoctor(DoctorDTO doctor);
    
}
