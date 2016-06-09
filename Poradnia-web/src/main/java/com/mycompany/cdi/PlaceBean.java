/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cdi;

import com.mycompany.interfaces.PlaceDTOFacadeLocal;
import com.mycompany.model.DoctorDTO;
import com.mycompany.model.PlaceDTO;
import com.mycompany.model.TermDTO;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Karol
 */
@Named("place")
@RequestScoped
public class PlaceBean implements Serializable {

    @EJB
    private PlaceDTOFacadeLocal placeDTOFacade;

    private String selectedPlace;
    private String city;
    private String street;
    private String buildingNumber;
    private String roomNumber;
    private String description;

    public List<PlaceDTO> getPlaces() {
        return placeDTOFacade.findAll();
    }

    public PlaceDTO getPlace(Integer id) {
        return placeDTOFacade.find(id);
    }

    public String delete(Integer id) {
        placeDTOFacade.remove(placeDTOFacade.find(id));

        return "";
    }

    public String getSelectedPlace() {
        return selectedPlace;
    }

    public void setSelectedPlace(String selectedPlace) {
        this.selectedPlace = selectedPlace;
    }

    public List<String> getStringTerms() {
        List<String> result = new LinkedList<String>();
        for (PlaceDTO place : getPlaces()) {
            result.add(place.getId() + " | Adres: " + place.getCity() + " " + place.getBuildingNumber() + ", pokój " + place.getRoomNumber() + " (" + place.getDescription() + ")");
        }
        return result;
    }

    public PlaceDTOFacadeLocal getPlaceDTOFacade() {
        return placeDTOFacade;
    }

    public void setPlaceDTOFacade(PlaceDTOFacadeLocal placeDTOFacade) {
        this.placeDTOFacade = placeDTOFacade;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    public String submit() {
        PlaceDTO place = new PlaceDTO();
        place.setBuildingNumber(Integer.parseInt(buildingNumber));
        place.setCity(city);
        place.setDescription(description);
        place.setRoomNumber(Integer.parseInt(roomNumber));
        place.setStreet(street);
        
        placeDTOFacade.create(place);
        
        return "places.xhtml?faces-redirect=true";
    }

}
