/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cdi;

import com.mycompany.interfaces.PatientDTOFacadeLocal;
import com.mycompany.model.PatientDTO;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Karol
 */
@Named("patient")
@RequestScoped
public class PatientBean implements Serializable
{

    @EJB
    private PatientDTOFacadeLocal patientDTOFacade;
    
     public List<PatientDTO> getDoctors()
    {
        return patientDTOFacade.findAll();
    }

    public PatientDTO getProduct(Integer id)
    {
        return patientDTOFacade.find(id);
    }

    public String delete(Integer id)
    {
        patientDTOFacade.remove(patientDTOFacade.find(id));
        
        return "success";
    }
    
}
