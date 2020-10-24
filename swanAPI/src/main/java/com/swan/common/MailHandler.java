package com.swan.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.swan.model.Mail;

@Service
public class MailHandler {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean mailSend(Mail mail) {
		
		try
		{
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(mail.getToMail());
	        message.setFrom(mail.getFromMail());
	        message.setSubject(mail.getMailSubject());
	        message.setText(mail.getMailContent());
	        
	        mailSender.send(message);
	        return true;
		}
		catch(Exception e)
		{
			System.out.println("Mail Send Fail : "+e.toString());
			return false;
		}
        
    }

}
