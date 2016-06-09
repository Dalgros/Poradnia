/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao.jpa;

import com.mycompany.interfaces.VisitDTOFacadeLocal;
import com.mycompany.model.DoctorDTO;
import com.mycompany.model.PatientDTO;
import com.mycompany.model.VisitDTO;
import java.util.List;
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
    
    @Override
    public List<VisitDTO> findByPatient(PatientDTO patient)
    {
        em = getEntityManager();
        List<VisitDTO> result;

        try
        {
            Query query = em.createNamedQuery("find visits by patient");
            query.setParameter(1, patient);
            
            result = query.getResultList();
            return result;
            
        } catch (NoResultException e)
        {
            return null;
        }
    }
    
    @Override
    public List<VisitDTO> findByDoctor(DoctorDTO doctor)
    {
        em = getEntityManager();
        List<VisitDTO> result;

        try
        {
            Query query = em.createNamedQuery("find visits by doctor");
            query.setParameter(1, doctor);
            
            result = query.getResultList();
            return result;
            
        } catch (NoResultException e)
        {
            return null;
        }
    }
    
}
