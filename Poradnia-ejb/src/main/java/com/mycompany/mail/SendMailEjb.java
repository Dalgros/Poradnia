/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mail;

import java.util.Date;
import java.util.Properties;
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
 * @author Marcin Kaczorowski, Karol Nowicki
 */
@Stateless
public class SendMailEjb implements SendMailEjbLocal
{
    @Override
    @Asynchronous
    public void sendMail(String to, String subject, String body)
    {
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "poczta.o2.pl");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.enable", "true");
        Session session = Session.getInstance(props);
        MimeMessage m = new MimeMessage(session);
        try
        {
            InternetAddress[] address =
            {
                new InternetAddress(to)
            };
            m.setFrom(new InternetAddress("user1357@o2.pl"));
            m.setRecipients(Message.RecipientType.TO, address);
            m.setSubject(subject);
            m.setSentDate(new Date());
            m.setText(body);
            Transport.send(m, "user1357", "user1357password");
        } catch (MessagingException e)
        {
            //log.log(Level.SEVERE, "Error while sending mail", e);
        }
    }
}
