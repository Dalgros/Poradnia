/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao.jpa;

import com.mycompany.interfaces.PlaceDTOFacadeLocal;
import com.mycompany.model.PlaceDTO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Marcin Kaczorowski, Karol Nowicki
 */
@Stateless
public class PlaceDTOFacade extends AbstractFacade<PlaceDTO> implements PlaceDTOFacadeLocal
{

    @PersistenceContext(unitName = "com.mycompany_Poradnia-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public PlaceDTOFacade()
    {
        super(PlaceDTO.class);
    }
    
}
