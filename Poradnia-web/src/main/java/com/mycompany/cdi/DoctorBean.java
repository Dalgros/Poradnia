/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cdi;

import com.mycompany.model.DoctorDTO;
import com.mycompany.interfaces.DoctorDTOFacadeLocal;
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
@Named("doctor")
@RequestScoped
public class DoctorBean implements Serializable {

    @EJB
    private DoctorDTOFacadeLocal doctorDTOFacade;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String email;
    private String username;
    private String password;
    private String password2;
    private String specialization;

    public String create(String fName, String lName) {
        DoctorDTO doctor = new DoctorDTO();
        doctor.setFirstName(fName);
        doctor.setLastName(lName);

        doctorDTOFacade.create(doctor);

        return "success";
    }

    public List<DoctorDTO> getDoctors() {
        return doctorDTOFacade.findAll();
    }

    public DoctorDTO getDoctor(Integer id) {
        return doctorDTOFacade.find(id);
    }

    public String delete(Integer id) {
        doctorDTOFacade.remove(doctorDTOFacade.find(id));

        return "";
    }

    public String edit(Integer id) {
        doctorDTOFacade.edit(doctorDTOFacade.find(id));

        return "success";
    }

    public List<DoctorDTO> getDoctorsByPatient(PatientDTO patient) {
        return doctorDTOFacade.findDoctors(patient);
    }

    public DoctorDTOFacadeLocal getDoctorDTOFacade() {
        return doctorDTOFacade;
    }

    public void setdoctorDTOFacade(DoctorDTOFacadeLocal doctorDTOFacade) {
        this.doctorDTOFacade = doctorDTOFacade;
    }

    public List<String> getStringDoctors() {
        List<String> result = new LinkedList<String>();
        for (DoctorDTO doctor : getDoctors()) {
            result.add(doctor.getId() + " | " + doctor.getFirstName() + " " + doctor.getLastName() + " (" + doctor.getSpecialization() + ")");
        }
        return result;
    }

    public String submit() {
        if (!password.equals(password2)) {
            return "addDoctor.xhtml?faces-redirect=true";
        }

        DoctorDTO doctor = new DoctorDTO();
        doctor.setAddress(address);
        doctor.setEmail(email);
        doctor.setFirstName(firstName);
        doctor.setLastName(lastName);
        doctor.setPassword(password);
        doctor.setPhoneNumber(Integer.parseInt(phoneNumber));
        doctor.setSpecialization(specialization);
        doctor.setUserName(username);

        doctorDTOFacade.create(doctor);

        return "doctors.xhtml?faces-redirect=true";
    }

}
