/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cdi;

import com.mycompany.interfaces.DoctorDTOFacadeLocal;
import com.mycompany.interfaces.PatientDTOFacadeLocal;
import com.mycompany.interfaces.PlaceDTOFacadeLocal;
import com.mycompany.interfaces.TermDTOFacadeLocal;
import com.mycompany.interfaces.VisitDTOFacadeLocal;
import com.mycompany.mail.SendMailEjbLocal;
import com.mycompany.model.DoctorDTO;
import com.mycompany.model.PatientDTO;
import com.mycompany.model.PlaceDTO;
import com.mycompany.model.VisitDTO;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Marcin Kaczorowski, Karol Nowicki
 */
@Named("visit")
@SessionScoped
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

    @EJB
    private PlaceDTOFacadeLocal placeDTOFacade;

    private static Logger log = Logger.getLogger(PlaceBean.class.getName());

    private String selectedTerm;
    private String selectedDoctor;
    private String selectedPatient;
    private String selectedPlace;

    private VisitDTO editable;

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

    public String getSelectedPlace() {
        return selectedPlace;
    }

    public void setSelectedPlace(String selectedPlace) {
        this.selectedPlace = selectedPlace;
    }

    public VisitDTO getEditable() {
        return editable;
    }

    public void setEditable(VisitDTO editable) {
        this.editable = editable;
    }

    public String editAction(VisitDTO visit) {
        this.editable = visitDTOFacade.find(visit.getId());
        return null;
    }

    public boolean isEditable(VisitDTO visit) {
        if (editable != null && visit != null) {
            return visit.getId().equals(editable.getId());
        }
        return false;
    }

    public String saveChanges() {
        System.out.println("sfdsdfsdfsd");
        editable.setDoctor((DoctorDTO) doctorDTOFacade.find(Integer.parseInt(selectedDoctor.substring(0, selectedDoctor.indexOf(" | ")))));
        editable.setPatient((PatientDTO) patientDTOFacade.find(Integer.parseInt(selectedPatient.substring(0, selectedPatient.indexOf(" | ")))));

        editable.setPlace((PlaceDTO) placeDTOFacade.find(Integer.parseInt(selectedPlace.substring(0, selectedPlace.indexOf(" | ")))));
        editable.setTerm(termDTOFacade.find(Integer.parseInt(selectedTerm.substring(0, selectedTerm.indexOf(" | ")))));

        visitDTOFacade.edit(editable);
        editable = null;
        return null;
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

        visit.setPlace((PlaceDTO) placeDTOFacade.find(Integer.parseInt(selectedPlace.substring(0, selectedPlace.indexOf(" | ")))));
        visit.setTerm(termDTOFacade.find(Integer.parseInt(selectedTerm.substring(0, selectedTerm.indexOf(" | ")))));

        visitDTOFacade.create(visit);

        log.info("Dodano nową wizytyę dla pacjenta " + visit.getPatient().getFirstName() + " " + visit.getPatient().getLastName());

        String subject = "Przypomnienie o wizycie lekarskiej";

        String body = "Witaj " + visit.getPatient().getFirstName() + "!\n"
                + "Umówiono Pana/Panią na wizytę lekarską u lekarza " + visit.getDoctor().getFirstName() + " " + visit.getDoctor().getLastName() + ".\n"
                + "Wizyta odbedzie się w przychodni pry w mieście " + visit.getPlace().getCity() + " pod adresem " + visit.getPlace().getStreet() + " "
                + visit.getPlace().getBuildingNumber() + " w pokoju numer " + visit.getPlace().getRoomNumber() + ".\n"
                + "Termin wizyty: " + visit.getTerm().getDate() + " o godzinie " + visit.getTerm().getTime() + "\n"
                + "Dziękujemy za korzystanie z usług naszej poradni.\n"
                + "Z poważaniem,\n"
                + "Sekretariat poradni lekarskiej.";

        sendMailEjb.sendMail(visit.getPatient().getEmail(), subject, body);

        return "visits.xhtml?faces-redirect=true";
    }

}
