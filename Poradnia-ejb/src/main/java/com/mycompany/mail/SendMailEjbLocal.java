/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mail;

import javax.ejb.Local;

/**
 *
 * @author Karol
 */
@Local
public interface SendMailEjbLocal
{
    void sendMail(String to, String subject, String body);
}
