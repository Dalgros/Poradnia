/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cdi;

import com.mycompany.interfaces.PatientDTOFacadeLocal;
import com.mycompany.model.DoctorDTO;
import com.mycompany.model.PatientDTO;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Karol
 */
@Named("patient")
@SessionScoped
public class PatientBean implements Serializable {

    @EJB
    private PatientDTOFacadeLocal patientDTOFacade;

    private static Logger log = Logger.getLogger(PatientBean.class.getName());

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String email;
    private String username;
    private String password;
    private String password2;

    private PatientDTO editable;

    public List<PatientDTO> getPatients() {
        return patientDTOFacade.findAll();
    }

    public List<PatientDTO> getPatientsByDoctors(DoctorDTO doctor) {
        return patientDTOFacade.findPatients(doctor);
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

    ; public void setPatientDTOFacade(PatientDTOFacadeLocal patientDTOFacade) {
        this.patientDTOFacade = patientDTOFacade;
    }

    public List<PatientDTO> getPatients(DoctorDTO doctor) {
        List<PatientDTO> list = getPatients();
        List<PatientDTO> result = new LinkedList<PatientDTO>();
        for (PatientDTO patient : list) {
            for (DoctorDTO doc : patient.getDoctors()) {
                if (doc.equals(doctor)) {
                    result.add(patient);
                }
            }
        }
        return result;
    }

    public List<String> getStringPatients() {
        List<String> result = new LinkedList<String>();
        for (PatientDTO patient : getPatients()) {
            result.add(patient.getId() + " | " + patient.getFirstName() + " " + patient.getLastName());
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

    public PatientDTO getEditable() {
        return editable;
    }

    public void setEditable(PatientDTO editable) {
        this.editable = editable;
    }
    
    public String editAction(PatientDTO doctor) {
        editable = doctor;
        return null;
    }
    
    public boolean isEditable(PatientDTO doctor) {
        return doctor.equals(editable);
    }
    
    public String saveChanges() {
        patientDTOFacade.edit(editable);
        editable = null; 
        return null;
    }

    public String submit() {
        return submit(null);
    }

    public String submit(DoctorDTO doctor) {
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
        patient.setUserName(username);

        List<DoctorDTO> docs = new LinkedList<DoctorDTO>();
        docs.add(doctor);
        patient.setDoctors(docs);

        patientDTOFacade.create(patient);

        log.info("Dodano pacjenta " + patient.getFirstName() + " " + patient.getLastName());

        return "patients.xhtml?faces-redirect=true";
    }

}
