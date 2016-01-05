package com.cinchwallet.adminportal.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

	// http://crunchify.com/java-mailapi-example-send-an-email-via-gmail-smtp/
	static Session session;

	static {
		final String username = "manojks029@gmail.com";
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
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse("manojks029@gmail.com"));
			message.setSubject("Password Reset for webPOS");

			String emailBody = "Your password for webPOS has been reset. " + "<br>" +
					"Given below are the detail : " +
					"<br> " +
					"<br>Username :" + userName +
					"<br>Password :" + password +
					"<br><br>Regards, " +
					"<br>Cinchwallet Admin";
			message.setContent(emailBody, "text/html");

			Transport.send(message);

			System.out.println("Done");
			status = true;
		} catch (MessagingException e) {
			
		} catch (UnsupportedEncodingException e) {
			
		} catch (Exception e) {
			
		}
		return status;
	}

	public static void main(String[] args) {
		sendEmail("test", "test", "m.manojsingh@gmail.com");
	}
}