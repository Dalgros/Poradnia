/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cdi;

import com.mycompany.interfaces.PatientDTOFacadeLocal;
import com.mycompany.model.DoctorDTO;
import com.mycompany.model.PatientDTO;
import com.mycompany.model.VisitDTO;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Karol
 */
@Named("patient")
@RequestScoped
public class PatientBean implements Serializable {

    @EJB
    private PatientDTOFacadeLocal patientDTOFacade;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String email;
    private String username;
    private String password;
    private String password2;

    public List<PatientDTO> getPatients() {
        return patientDTOFacade.findAll();
    }

    public PatientDTO getPatient(Integer id) {
        return patientDTOFacade.find(id);
    }

    public String delete(Integer id) {
        patientDTOFacade.remove(patientDTOFacade.find(id));

        return "";
    }

    public PatientDTOFacadeLocal getPatientDTOFacade() {
        return patientDTOFacade;
    }

    public void setPatientDTOFacade(PatientDTOFacadeLocal patientDTOFacade) {
        this.patientDTOFacade = patientDTOFacade;
    }
    
    public List<PatientDTO> getPatients(DoctorDTO doctor) {
        List<PatientDTO> list = getPatients();
        List<PatientDTO> result = new LinkedList<PatientDTO>();
        for(PatientDTO patient : list) {
            for(DoctorDTO doc : patient.getDoctors()) {
                if(doc.equals(doctor))
                    result.add(patient);
            }
        }
        return result;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String submit() {
        if (!password.equals(password2)) {
            return "addPatient.xhtml?faces-redirect=true";
        }

        PatientDTO patient = new PatientDTO();
        patient.setAddress(address);
        patient.setEmail(email);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setPassword(password);
        patient.setPhoneNumber(Integer.parseInt(phoneNumber));
        patient.setSpecialization(email);
        patient.setUserName(username);
        
        List<DoctorDTO> doctors = new LinkedList<DoctorDTO>();
        FacesContext context = FacesContext.getCurrentInstance();
        doctors.add((DoctorDTO) context.getExternalContext().getSessionMap().get("username"));
        patient.setDoctors(doctors);
        
        patientDTOFacade.create(patient);

        return "patients.xhtml?faces-redirect=true";
    }

}
