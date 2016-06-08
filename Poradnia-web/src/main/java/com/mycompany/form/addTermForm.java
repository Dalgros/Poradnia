/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.form;

import java.util.Date;
import javax.annotation.ManagedBean;
import javax.inject.Named;

/**
 *
 * @author Marcin
 */
@Named("addTerm")
@ManagedBean
public class addTermForm {
    
    private Date date = new Date();
 
    public Date getDate() {
        return date;
    }
 
    public void setDate(Date date) {
        this.date = date;
    }
    
}
