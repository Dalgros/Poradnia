/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cdi;

import com.mycompany.interfaces.PlaceDTOFacadeLocal;
import com.mycompany.model.PlaceDTO;
import java.io.Serializable;
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
public class PlaceBean implements Serializable
{

    @EJB
    private PlaceDTOFacadeLocal placeDTOFacade;
    
         public List<PlaceDTO> getPlaces()
    {
        return placeDTOFacade.findAll();
    }

    public PlaceDTO getPlace(Integer id)
    {
        return placeDTOFacade.find(id);
    }

    public String delete(Integer id)
    {
        placeDTOFacade.remove(placeDTOFacade.find(id));
        
        return "success";
    }
    
}
