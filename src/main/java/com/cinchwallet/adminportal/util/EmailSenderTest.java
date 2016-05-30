package com.cinchwallet.adminportal.util;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class EmailSenderTest {

	static Session session;

	static {
		final String username = "manojks2906@gmail.com";
		final String password = "rahuls12";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
	}

	public static boolean sendEmail(String userName, String password, String toEmail) {
		boolean status = false;
		try {
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from-email@gmail.com", "Cinch Wallet"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			//should be cw admin email
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse("manojks2906@gmail.com"));
			message.setSubject("Password Reset for webPOS");

			String emailBody = "Your password for webPOS has been reset. " + "<br>" +
					"Given below are the detail : " +
					"<br> " +
					"<br>Username :" + userName +
					"<br>Password :" + password +
					"<br><br>Regards, " +
					"<br>Cinchwallet Admin";
			message.setContent(emailBody, "text/html");

			
			VelocityEngine ve = new VelocityEngine();
	        ve.init();
			Template template = ve.getTemplate("src/main/resources/email.vm");

			  VelocityContext velocityContext = new VelocityContext();
			  velocityContext.put("firstName", "Yashwant");
			  velocityContext.put("lastName", "Chavan");
			  velocityContext.put("location", "Pune");
			  
			  StringWriter stringWriter = new StringWriter();
			  
			  template.merge(velocityContext, stringWriter);
			  
			  //message.setText(stringWriter.toString());
			  message.setContent(stringWriter.toString(), "text/html");
			  
			  
			Transport.send(message);

			System.out.println("Done");
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public static void main(String[] args) {
		sendEmail("test", "test", "manojks2906@gmail.com");
	}
}