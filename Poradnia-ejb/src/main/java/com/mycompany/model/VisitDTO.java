/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Karol
 */
@Entity
@Table(name = "VISIT")
@NamedQueries({
    @NamedQuery(name = "find visits by patient",
            query = "select v from VisitDTO v where v.patient = ?1"),
    @NamedQuery(name = "find visits by doctor",
            query = "select v from VisitDTO v")
})
public class VisitDTO implements Serializable
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne @JoinColumn(name = "ID_DOCTOR", referencedColumnName = "ID")
    private DoctorDTO doctor;
    
    @ManyToOne @JoinColumn(name = "ID_PATIENT", referencedColumnName = "ID")
    private PatientDTO patient;
    
    @ManyToOne @JoinColumn(name = "ID_TERM", referencedColumnName = "ID")
    private TermDTO term;
    
    @ManyToOne @JoinColumn(name = "ID_PLACE", referencedColumnName = "ID")
    private PlaceDTO place;
    
    public VisitDTO()
    {
    }

    public PatientDTO getPatient()
    {
        return patient;
    }

    public void setPatient(PatientDTO patient)
    {
        this.patient = patient;
    }

    public TermDTO getTerm()
    {
        return term;
    }

    public void setTerm(TermDTO term)
    {
        this.term = term;
    }

    public PlaceDTO getPlace()
    {
        return place;
    }

    public void setPlace(PlaceDTO place)
    {
        this.place = place;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public DoctorDTO getDoctor()
    {
        return doctor;
    }

    public void setDoctor(DoctorDTO doctor)
    {
        this.doctor = doctor;
    }
    
    
   
}
