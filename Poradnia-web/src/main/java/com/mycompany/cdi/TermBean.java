/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cdi;

import com.mycompany.interfaces.TermDTOFacadeLocal;
import com.mycompany.model.TermDTO;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Karol
 */
@Named("term")
@RequestScoped
public class TermBean implements Serializable
{

    @EJB
    private TermDTOFacadeLocal termDTOFacade;
    
         public List<TermDTO> getTerms()
    {
        return termDTOFacade.findAll();
    }

    public TermDTO getTerm(Integer id)
    {
        return termDTOFacade.find(id);
    }

    public String delete(Integer id)
    {
        termDTOFacade.remove(termDTOFacade.find(id));
        
        return "success";
    }
    
}
