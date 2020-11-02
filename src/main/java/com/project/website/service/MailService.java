package com.project.website.service;

import org.springframework.stereotype.Service;

import com.project.website.model.User;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;

@Service
public class MailService {


	@Autowired
	private JavaMailSender mailSender;
	 
	@Autowired  
    private  MailContentBuilder mailContentBuilder;  
 
		@Async
      public void sendMail(User user) throws Exception {

    	 
		 MimeMessagePreparator preparator = new MimeMessagePreparator() 
		    {
		        public void prepare(MimeMessage mimeMessage) throws Exception 
		        {
		        	String token="acs-asdc-ss";
		            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
		            mimeMessage.setFrom(new InternetAddress("admin@gmail.com"));
		            mimeMessage.setSubject( "Please Activate your Account");
		            mimeMessage.setText(mailContentBuilder.build("please click on th below url to activate your account: "+
							"http://localhost:8085/api/v1/users/authenticate/"+token));
		             
		        }
		    };
		    
		        
        try {
            mailSender.send(preparator);
            
        } catch (MailException e) {

            throw new Exception("Exception occurred when sending mail to " + user.getEmail(), e);
        }
    }
}
