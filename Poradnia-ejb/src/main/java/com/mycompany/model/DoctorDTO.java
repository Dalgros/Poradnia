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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Karol
 */
@Entity
@Table(name = "DOCTOR")
@NamedQueries({
    @NamedQuery(name = "check doctor",
            query = "select d from DoctorDTO d where d.userName = ?1 and d.password = ?2"),
    @NamedQuery(name = "find doctors by patient",
            query = "select d from DoctorDTO d")
    
})
public class DoctorDTO implements Serializable
{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 20)
    @Column(name = "FIRSTNAME")
    private String firstName;
    
    @Size(max = 20)
    @Column(name = "LASTNAME")
    private String lastName;
    
    @Size(max =40)
    @Column(name = "ADDRESS")
    private String address;
    
    @Column(name = "PHONENUMBER")
    private Integer phoneNumber;
    
    @Size(max = 30)
    @Column(name = "EMAIL")
    private String email;
    
    @Size(max = 20)
    @Column(name = "SPECIALIZATION")
    private String specialization;
    
    @Size(max = 20)
    @Column(name = "USERNAME")
    private String userName;
    
    @Size(max = 20)
    @Column(name = "PASSWORD")
    private String password;

    @ManyToMany
    @JoinTable(
      name="PATIENT",
      joinColumns=@JoinColumn(name="ID_DOCTOR", referencedColumnName="ID"),
      inverseJoinColumns=@JoinColumn(name="ID_PATIENT", referencedColumnName="ID"))
    private List<PatientDTO> patients = new LinkedList<>();

    @OneToMany(mappedBy = "doctor", cascade={CascadeType.ALL})
    private List<VisitDTO> visits = new LinkedList<>();
    
    

    public DoctorDTO()
    {
        
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

    public String getSpecialization()
    {
        return specialization;
    }

    public void setSpecialization(String specialization)
    {
        this.specialization = specialization;
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

    public List<PatientDTO> getPatients()
    {
        return patients;
    }

    public void setPatients(List<PatientDTO> patients)
    {
        this.patients = patients;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

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

    public List<VisitDTO> getVisits()
    {
        return visits;
    }

    public void setVisits(List<VisitDTO> visits)
    {
        this.visits = visits;
    }

    public DoctorDTO(Integer id)
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
        if (!(object instanceof DoctorDTO))
        {
            return false;
        }
        DoctorDTO other = (DoctorDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.mycompany.model.DoctorDTO[ id=" + id + " ]";
    }

    

    
    
    
}