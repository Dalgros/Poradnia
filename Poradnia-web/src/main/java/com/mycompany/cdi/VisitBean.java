/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cdi;

import com.mycompany.interfaces.VisitDTOFacadeLocal;
import com.mycompany.model.VisitDTO;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Karol
 */
@Named("visit")
@RequestScoped
public class VisitBean implements Serializable
{

    @EJB
    private VisitDTOFacadeLocal visitDTOFacade;
    
    public List<VisitDTO> getVisits()
    {
        return visitDTOFacade.findAll();
    }

    public VisitDTO getVisit(Integer id)
    {
        return visitDTOFacade.find(id);
    }

    public String delete(Integer id)
    {
        visitDTOFacade.remove(visitDTOFacade.find(id));
        
        return "success";
    }
    
}
