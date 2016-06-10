/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao.jpa;

import com.mycompany.interfaces.TermDTOFacadeLocal;
import com.mycompany.model.TermDTO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Marcin Kaczorowski, Karol Nowicki
 */
@Stateless
public class TermDTOFacade extends AbstractFacade<TermDTO> implements TermDTOFacadeLocal
{

    @PersistenceContext(unitName = "com.mycompany_Poradnia-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public TermDTOFacade()
    {
        super(TermDTO.class);
    }
    
}
