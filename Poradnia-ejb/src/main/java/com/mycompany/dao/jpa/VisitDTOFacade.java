/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao.jpa;

import com.mycompany.interfaces.VisitDTOFacadeLocal;
import com.mycompany.model.VisitDTO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Karol
 */
@Stateless
public class VisitDTOFacade extends AbstractFacade<VisitDTO> implements VisitDTOFacadeLocal
{

    @PersistenceContext(unitName = "com.mycompany_Poradnia-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public VisitDTOFacade()
    {
        super(VisitDTO.class);
    }
    
}
