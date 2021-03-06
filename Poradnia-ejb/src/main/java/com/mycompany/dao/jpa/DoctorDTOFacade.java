/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao.jpa;

import com.mycompany.interfaces.DoctorDTOFacadeLocal;
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
public class DoctorDTOFacade extends AbstractFacade<DoctorDTO> implements DoctorDTOFacadeLocal
{

    @PersistenceContext(unitName = "com.mycompany_Poradnia-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public DoctorDTOFacade()
    {
        super(DoctorDTO.class);
    }

    @Override
    public DoctorDTO checkUser(String userName, String password)
    {
        em = getEntityManager();
        DoctorDTO result = null;

        try
        {
            Query query = em.createNamedQuery("check doctor");
            query.setParameter(1, userName);
            query.setParameter(2, password);
            result = (DoctorDTO) query.getSingleResult();
            return result;
        } catch (NoResultException e)
        {
            return null;
        }
    }

    @Override
    public List<DoctorDTO> findDoctors(PatientDTO patient)
    {
        List<DoctorDTO> list = findAll();
        List<DoctorDTO> result = new LinkedList<DoctorDTO>();

        for (DoctorDTO doctor : list)
        {
            for (PatientDTO item : doctor.getPatients())
            {
                if (item.equals(patient))
                {
                    result.add(doctor);
                    break;

                }

            }
        }
        return result;
    }
}


