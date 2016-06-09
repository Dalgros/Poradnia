/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cdi;

import com.mycompany.interfaces.PlaceDTOFacadeLocal;
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

}
