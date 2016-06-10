/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interfaces;

import com.mycompany.model.TermDTO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Marcin Kaczorowski, Karol Nowicki
 */
@Local
public interface TermDTOFacadeLocal
{

    void create(TermDTO termDTO);

    void edit(TermDTO termDTO);

    void remove(TermDTO termDTO);

    TermDTO find(Object id);

    List<TermDTO> findAll();

    List<TermDTO> findRange(int[] range);

    int count();
    
}
