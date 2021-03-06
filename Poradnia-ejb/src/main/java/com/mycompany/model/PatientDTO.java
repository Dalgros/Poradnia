/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 *@author Marcin Kaczorowski, Karol Nowicki
 */
@Entity
@Table(name = "PATIENT")
@NamedQueries({
    @NamedQuery(name = "check patient",
            query = "select p from PatientDTO p where p.userName = ?1 and p.password = ?2"),
    @NamedQuery(name = "find patients by doctor",
           query = "select p from PatientDTO p where ?1 in (p.doctors)")
})
public class PatientDTO implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Size(max = 30)
    @Column(name = "FIRSTNAME")
    private String firstName;
    
    @Size(max = 40)
    @Column(name = "LASTNAME")
    private String lastName;
    
    @Size(max = 75)
    @Column(name = "ADDRESS")
    private String address;
    
    @Column(name = "PHONENUMBER")
    private Integer phoneNumber;
    
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;
    
    @Size(max = 20)
    @Column(name = "USERNAME")
    private String userName;
    
    @Size(max = 20)
    @Column(name = "PASSWORD")
    private String password;
    
    @OneToMany(mappedBy = "patient", cascade={CascadeType.ALL})
    private List<VisitDTO> visits = new LinkedList<>();
    
    @ManyToMany(mappedBy="patients")
    private List<DoctorDTO> doctors = new LinkedList<>();

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public Integer getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public List<VisitDTO> getVisits()
    {
        return visits;
    }

    public void setVisits(List<VisitDTO> visits)
    {
        this.visits = visits;
    }

    public List<DoctorDTO> getDoctors()
    {
        return doctors;
    }

    public void setDoctors(List<DoctorDTO> doctors)
    {
        this.doctors = doctors;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientDTO))
        {
            return false;
        }
        PatientDTO other = (PatientDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.mycompany.model.PatientDTO[ id=" + id + " ]";
    }
    
}
