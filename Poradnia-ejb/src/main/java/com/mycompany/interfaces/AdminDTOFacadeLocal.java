/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interfaces;

import com.mycompany.model.AdminDTO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Marcin Kaczorowski, Karol Nowicki
 */
@Local
public interface AdminDTOFacadeLocal
{

    void create(AdminDTO adminDTO);

    void edit(AdminDTO adminDTO);

    void remove(AdminDTO adminDTO);

    AdminDTO find(Object id);

    List<AdminDTO> findAll();

    List<AdminDTO> findRange(int[] range);

    int count();
   
    AdminDTO checkUser(String userName, String password);
    
    void flush();
    
}
