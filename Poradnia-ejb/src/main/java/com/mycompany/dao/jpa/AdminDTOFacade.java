/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao.jpa;

import com.mycompany.interfaces.AdminDTOFacadeLocal;
import com.mycompany.model.AdminDTO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Karol
 */
@Stateless
public class AdminDTOFacade extends AbstractFacade<AdminDTO> implements AdminDTOFacadeLocal
{

    @PersistenceContext(unitName = "com.mycompany_Poradnia-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public AdminDTOFacade()
    {
        super(AdminDTO.class);
    }
    
    @Override
    public AdminDTO checkUser(String userName, String password)
    {
        em = getEntityManager();
        AdminDTO result = null;
        
        try
        {
            Query query = em.createNamedQuery("check admin");
            query.setParameter(1, userName);
            query.setParameter(2, password);
            result = (AdminDTO)query.getSingleResult();
            return result;
        } 
        catch(NoResultException e) 
        {
            return null;
        }
    }
    
}
