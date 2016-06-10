/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao.jpa;

import com.mycompany.interfaces.PatientDTOFacadeLocal;
import com.mycompany.model.DoctorDTO;
import com.mycompany.model.PatientDTO;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Marcin Kaczorowski, Karol Nowicki
 */
@Stateless
public class PatientDTOFacade extends AbstractFacade<PatientDTO> implements PatientDTOFacadeLocal
{

    @PersistenceContext(unitName = "com.mycompany_Poradnia-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public PatientDTOFacade()
    {
        super(PatientDTO.class);
    }
    
    @Override
    public PatientDTO checkUser(String userName, String password)
    {
        em = getEntityManager();
        PatientDTO result = null;
        
        try
        {
            Query query = em.createNamedQuery("check patient");
            query.setParameter(1, userName);
            query.setParameter(2, password);
            result = (PatientDTO)query.getSingleResult();
            return result;
        } 
        catch(NoResultException e) 
        {
            return null;
        }
    }
    
    @Override
    public List<PatientDTO> findPatients(DoctorDTO doctor)
    {
        List<PatientDTO> list = findAll();
        List<PatientDTO> result = new LinkedList<PatientDTO>();
        
        for(PatientDTO patient : list) {
            for(DoctorDTO item : patient.getDoctors()) {
                if(item.equals(doctor)) {
                    result.add(patient);
                    break;
                }
            }
        }
        
        return result;
    }
    
}
