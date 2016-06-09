/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cdi;

import com.mycompany.interfaces.AdminDTOFacadeLocal;
import com.mycompany.interfaces.DoctorDTOFacadeLocal;
import com.mycompany.interfaces.PatientDTOFacadeLocal;
import com.mycompany.model.AdminDTO;
import com.mycompany.model.DoctorDTO;
import com.mycompany.model.PatientDTO;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marcin
 */
@Named("login")
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    @EJB
    private AdminDTOFacadeLocal adminDTOFacade;
    @EJB
    private DoctorDTOFacadeLocal doctorDTOFacade;
    @EJB
    private PatientDTOFacadeLocal patientDTOFacade;

    private Object user;

    private String username;
    private String password;
    private String message;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public String validateUsernamePassword() {
        user = (AdminDTO) adminDTOFacade.checkUser(username, password);

        if (user == null) {
            user = (DoctorDTO) doctorDTOFacade.checkUser(username, password);
        }

        if (user == null) {
            user = (PatientDTO) patientDTOFacade.checkUser(username, password);
        }

        if (user != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("username", user);
            if (user instanceof AdminDTO) {
                return "/admin/index.xhtml?faces-redirect=true";
            } else if (user instanceof DoctorDTO) {
                return "/doctor/index.xhtml?faces-redirect=true";
            } else if (user instanceof PatientDTO) {
                return "/patient/index.xhtml?faces-redirect=true";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Nieprawidłowe dane",
                            "Proszę podaj poprawną nazwę użytkownika oraz hasło"));
            return "/login.xhtml";
        }

        return "/login.xhtml";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        System.out.println("LOGOUTLOGOUT");
        return "/login.xhtml?faces-redirect=true";
    }

}
