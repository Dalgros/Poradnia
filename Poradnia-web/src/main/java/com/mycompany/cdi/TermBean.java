/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cdi;

import com.mycompany.interfaces.TermDTOFacadeLocal;
import com.mycompany.model.TermDTO;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Marcin Kaczorowski, Karol Nowicki
 */
@Named("term")
@RequestScoped
public class TermBean implements Serializable {

    @EJB
    private TermDTOFacadeLocal termDTOFacade;
    
    private static Logger log = Logger.getLogger(PlaceBean.class.getName());

    private String date;
    private String time;

    private String message;

    public List<TermDTO> getTerms() {
        return termDTOFacade.findAll();
    }

    public TermDTO getTerm(Integer id) {
        return termDTOFacade.find(id);
    }
    
    public List<String> getStringTerms() {
        List<String> result = new LinkedList<String>();
        for(TermDTO term : getTerms()) {
            result.add(term.getId() + " | Godzina: " + term.getTime() + ", data: " + term.getDate().getDate() + "." + term.getDate().getMonth() + "." + (term.getDate().getYear() + 1900));
        }
        return result;
    }

    public String delete(Integer id) {
        termDTOFacade.remove(termDTOFacade.find(id));

        return "";
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String submit() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date parsed = format.parse(date);
            java.sql.Date sql = new java.sql.Date(parsed.getTime());

            TermDTO term = new TermDTO();
            term.setDate(sql);
            term.setTime(time);
            termDTOFacade.create(term);
            
            log.info("Dodano termin " + term.getTime() + " " + term.getDate());

            return "terms.xhtml?faces-redirect=true";
        } catch (ParseException ex) {
            Logger.getLogger(TermBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Nieprawidłowe dane",
                        "Nieprawidłowy format danych"));

        return "addTerm.xhtml?faces-redirect=true";
    }

}
