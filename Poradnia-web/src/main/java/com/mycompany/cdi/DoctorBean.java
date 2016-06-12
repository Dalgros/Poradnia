/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cdi;

import com.mycompany.filter.AuthorizationFilter;
import com.mycompany.model.DoctorDTO;
import com.mycompany.interfaces.DoctorDTOFacadeLocal;
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
 * @author Marcin Kaczorowski, Karol Nowicki
 */
@Named("doctor")
@SessionScoped
public class DoctorBean implements Serializable {

    @EJB
    private DoctorDTOFacadeLocal doctorDTOFacade;

    private static Logger log = Logger.getLogger(AuthorizationFilter.class.getName());
    
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String email;
    private String username;
    private String password;
    private String password2;
    private String specialization;
    
    private DoctorDTO editable;

    public String create(String fName, String lName) {
        DoctorDTO doctor = new DoctorDTO();
        doctor.setFirstName(fName);
        doctor.setLastName(lName);

        doctorDTOFacade.create(doctor);
        
        log.info("Utworzono lekarza " + fName + " " + lName);

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
        
        log.info("Usunięto lekarza o identyfikatorze " + id);
        
        return "";
    }

    public String edit(Integer id) {
        doctorDTOFacade.edit(doctorDTOFacade.find(id));

        log.info("Edytowano lekarza o identyfikatorze " + id);
        
        return "";
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public DoctorDTO getEditable() {
        return editable;
    }

    public void setEditable(DoctorDTO editable) {
        this.editable = editable;
    }
    
    public String editAction(DoctorDTO doctor) {
        editable = doctor;
        return null;
    }
    
    public boolean isEditable(DoctorDTO doctor) {
        return doctor.equals(editable);
    }
    
    public String saveChanges() {
        doctorDTOFacade.edit(editable);
        editable = null; 
        return null;
    }

    public String submit() {
        if (!password.equals(password2)) {
            log.fine("Podano różne hasła podczas rejestracji lekarza");
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

        log.info("Dodano lekarza " + doctor.getFirstName() + " " + doctor.getLastName());
        
        return "doctors.xhtml?faces-redirect=true";
    }

}
