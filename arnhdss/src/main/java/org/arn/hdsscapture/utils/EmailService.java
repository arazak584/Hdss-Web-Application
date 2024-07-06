package org.arn.hdsscapture.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	
	@Autowired
    private JavaMailSender mailSender;

	public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("hdsscapture@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        try {
            mailSender.send(message);
            System.out.print("Message sent successfully...");
        } catch (MailException e) {
            // Log the error or take other appropriate action
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}
