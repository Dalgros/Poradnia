/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mail;

import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Karol
 */
@Stateless
public class SendMailEjb implements SendMailEjbLocal
{

    @Resource(name = "mail/mySession")
    private Session session;

    @Asynchronous
    public void sendMail(String to, String subject, String body)
    {
        MimeMessage m = new MimeMessage(session);
        try
        {
            InternetAddress[] address =
            {
                new InternetAddress(to)
            };
            m.setFrom(); // pobrany z ustawien Session
            m.setRecipients(Message.RecipientType.TO, address);
            m.setSubject(subject);
            m.setSentDate(new Date());
            m.setText(body);
            Transport.send(m, "user1357", "user1357password");
        } catch (MessagingException e)
        {
            //log
            //log.log(Level.SEVERE, "Error while sending mail", e);
        }
    }
}
