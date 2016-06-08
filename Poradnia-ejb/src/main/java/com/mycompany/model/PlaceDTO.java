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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Karol
 */
@Entity
@Table(name="PLACE")

public class PlaceDTO implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Size(max = 20)
    @Column(name = "CITY")
    private String city;
    
    @Size(max = 20)
    @Column(name = "STREET")
    private String street;
    
    @Column(name = "BUILDINGNUMBER")
    private Integer buildingNumber;
    
    @Size(max = 40)
    @Column(name = "DESCRIPTION")
    private String description;
    
    @Column(name = "ROOMNUMBER")
    private Integer roomNumber;
    
    @OneToMany(mappedBy = "place", cascade={CascadeType.ALL})
    private List<VisitDTO> visits = new LinkedList<>();

    public List<VisitDTO> getVisits()
    {
        return visits;
    }

    public void setVisits(List<VisitDTO> visits)
    {
        this.visits = visits;
    }

    public PlaceDTO()
    {
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public Integer getBuildingNumber()
    {
        return buildingNumber;
    }

    public void setBuildingNumber(Integer buildingNumber)
    {
        this.buildingNumber = buildingNumber;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getRoomNumber()
    {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber)
    {
        this.roomNumber = roomNumber;
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
        if (!(object instanceof PlaceDTO))
        {
            return false;
        }
        PlaceDTO other = (PlaceDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.mycompany.model.PlaceDTO[ id=" + id + " ]";
    }
    
}
