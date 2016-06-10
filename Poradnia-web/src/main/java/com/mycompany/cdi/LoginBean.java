/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cdi;

import com.mycompany.filter.AuthorizationFilter;
import com.mycompany.interfaces.AdminDTOFacadeLocal;
import com.mycompany.interfaces.DoctorDTOFacadeLocal;
import com.mycompany.interfaces.PatientDTOFacadeLocal;
import com.mycompany.model.AdminDTO;
import com.mycompany.model.DoctorDTO;
import com.mycompany.model.PatientDTO;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Marcin Kaczorowski, Karol Nowicki
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
    
    private static Logger log = Logger.getLogger(LoginBean.class.getName());

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
        user = adminDTOFacade.checkUser(username, password);

        if (user == null) {
            user = doctorDTOFacade.checkUser(username, password);
        }

        if (user == null) {
            user = patientDTOFacade.checkUser(username, password);
        }

        if (user != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("username", user);
            if (user instanceof AdminDTO) {
                log.fine("Zalogował się administrator" + ((AdminDTO) user).getUserName());
                return "/admin/index.xhtml?faces-redirect=true";
            } else if (user instanceof DoctorDTO) {
                log.fine("Zalogował się lekarz" + ((DoctorDTO) user).getUserName());
                return "/doctor/index.xhtml?faces-redirect=true";
            } else if (user instanceof PatientDTO) {
                log.fine("Zalogował się pacjent" + ((PatientDTO) user).getUserName());
                return "/patient/index.xhtml?faces-redirect=true";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Nieprawidłowe dane",
                            "Proszę podaj poprawną nazwę użytkownika oraz hasło"));
            log.warning("Nieprawidłowe dane logowania");
            return "/login.xhtml";
        }

        return "/login.xhtml";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        log.fine("Wylogował się użytkownik");
        return "/login.xhtml?faces-redirect=true";
    }

}
