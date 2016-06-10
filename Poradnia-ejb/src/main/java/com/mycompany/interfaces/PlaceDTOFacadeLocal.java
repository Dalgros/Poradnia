/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interfaces;

import com.mycompany.model.PlaceDTO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Marcin Kaczorowski, Karol Nowicki
 */
@Local
public interface PlaceDTOFacadeLocal
{

    void create(PlaceDTO placeDTO);

    void edit(PlaceDTO placeDTO);

    void remove(PlaceDTO placeDTO);

    PlaceDTO find(Object id);

    List<PlaceDTO> findAll();

    List<PlaceDTO> findRange(int[] range);

    int count();
    
}
