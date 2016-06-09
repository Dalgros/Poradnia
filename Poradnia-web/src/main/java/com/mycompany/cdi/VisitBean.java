/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cdi;

import com.mycompany.interfaces.DoctorDTOFacadeLocal;
import com.mycompany.interfaces.PatientDTOFacadeLocal;
import com.mycompany.interfaces.TermDTOFacadeLocal;
import com.mycompany.interfaces.VisitDTOFacadeLocal;
import com.mycompany.mail.SendMailEjbLocal;
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
@Named("visit")
@RequestScoped
public class VisitBean implements Serializable {

    @EJB
    private SendMailEjbLocal sendMailEjb;

    @EJB
    private TermDTOFacadeLocal termDTOFacade;

    @EJB
    private VisitDTOFacadeLocal visitDTOFacade;

    @EJB
    private PatientDTOFacadeLocal patientDTOFacade;

    @EJB
    private DoctorDTOFacadeLocal doctorDTOFacade;
    
    

    private String selectedTerm;
    private String selectedDoctor;
    private String selectedPatient;

    public List<VisitDTO> getVisits() {
        return visitDTOFacade.findAll();
    }

    public VisitDTO getVisit(Integer id) {
        return visitDTOFacade.find(id);
    }

    public String delete(Integer id) {
        visitDTOFacade.remove(visitDTOFacade.find(id));

        return "";
    }

    public List<VisitDTO> getVisitsByDoctors(DoctorDTO doctor) {
        return visitDTOFacade.findByDoctor(doctor);
    }

    public List<VisitDTO> getVisitsByPatients(PatientDTO patient) {
        return visitDTOFacade.findByPatient(patient);
    }

    public List<VisitDTO> getVisits(DoctorDTO doctor) {
        List<VisitDTO> list = getVisits();
        List<VisitDTO> result = new LinkedList<VisitDTO>();
        for (VisitDTO visit : list) {
            if (visit.getDoctor().equals(doctor)) {
                result.add(visit);
            }
        }
        return result;
    }

    public String getSelectedTerm() {
        return selectedTerm;
    }

    public void setSelectedTerm(String selectedTerm) {
        this.selectedTerm = selectedTerm;
    }

    public String getSelectedDoctor() {
        return selectedDoctor;
    }

    public void setSelectedDoctor(String selectedDoctor) {
        this.selectedDoctor = selectedDoctor;
    }

    public String getSelectedPatient() {
        return selectedPatient;
    }

    public void setSelectedPatient(String selectedPatient) {
        this.selectedPatient = selectedPatient;
    }

    public String submit(Integer id) {
        VisitDTO visit = new VisitDTO();

        FacesContext context = FacesContext.getCurrentInstance();
        if (selectedDoctor == null) {
            visit.setDoctor((DoctorDTO) context.getExternalContext().getSessionMap().get("username"));
        } else {
            visit.setDoctor((DoctorDTO) doctorDTOFacade.find(Integer.parseInt(selectedDoctor.substring(0, selectedDoctor.indexOf(" | ")))));
        }

        if (selectedPatient == null) {
            visit.setPatient((PatientDTO) patientDTOFacade.find(id));
        } else {
            visit.setPatient((PatientDTO) patientDTOFacade.find(Integer.parseInt(selectedPatient.substring(0, selectedPatient.indexOf(" | ")))));
        }

        visit.setTerm(termDTOFacade.find(Integer.parseInt(selectedTerm.substring(0, selectedTerm.indexOf(" | ")))));

        visitDTOFacade.create(visit);
        
        String subject = "Przypomnienie o wizycie lekarskiej";
        
        String body = "Witaj " + visit.getPatient().getFirstName() + "!\n" +
                "Umówiono Pana/Panią na wizytę lekarską u lekarza " + visit.getDoctor().getFirstName() + " " + visit.getDoctor().getLastName() + ".\n" + 
                "Wizyta odbedzie się w przychodni pry w mieście " + visit.getPlace().getCity() + " pod adresem " + visit.getPlace().getStreet() + " " + 
                visit.getPlace().getBuildingNumber() + " w pokoju numer " + visit.getPlace().getRoomNumber() + ".\n" +
                "Termin wizyty: " + visit.getTerm().getDate() + " o godzinie " + visit.getTerm().getTime() + "\n" + 
                "Dziękujemy za korzystanie z usług naszej poradni.\n" + 
                "Z poważaniem,"+
                "Sekretariat poradni lekarskiej.";
        
        sendMailEjb.sendMail(visit.getPatient().getEmail(), subject, body);

        return "visits.xhtml?faces-redirect=true";
    }

}
