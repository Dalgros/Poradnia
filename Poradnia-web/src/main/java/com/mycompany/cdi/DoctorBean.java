/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cdi;

import com.mycompany.model.DoctorDTO;
import com.mycompany.interfaces.DoctorDTOFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputSecret;
import javax.faces.component.html.HtmlInputText;
import javax.inject.Named;

/**
 *
 * @author Karol
 */
@Named("doctor")
@RequestScoped
public class DoctorBean implements Serializable
{

    @EJB
    private DoctorDTOFacadeLocal doctorDTOFacade;

    public String create(String fName, String lName)
    {
        DoctorDTO doctor = new DoctorDTO();
        doctor.setFirstName(fName);
        doctor.setLastName(lName);

        doctorDTOFacade.create(doctor);

        return "success";
    }

    public List<DoctorDTO> getDoctors()
    {
        return doctorDTOFacade.findAll();
    }

    public DoctorDTO getProduct(Integer id)
    {
        return doctorDTOFacade.find(id);
    }

    public String delete(Integer id)
    {
        doctorDTOFacade.remove(doctorDTOFacade.find(id));
        
        return "success";
    }
    
    public String edit(Integer id)
    {
        doctorDTOFacade.edit(doctorDTOFacade.find(id));
        
        return "success";
    }

    public DoctorDTOFacadeLocal getDoctorDTOFacade()
    {
        return doctorDTOFacade;
    }

    public void setdoctorDTOFacade(DoctorDTOFacadeLocal doctorDTOFacade)
    {
        this.doctorDTOFacade = doctorDTOFacade;
    }

}
