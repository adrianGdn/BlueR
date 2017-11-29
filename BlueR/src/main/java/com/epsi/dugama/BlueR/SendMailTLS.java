package com.epsi.dugama.BlueR;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Allow to send an email with TLS.
 * @author Alexis
 */
public class SendMailTLS {

	public static void main(String[] args) {
		String sendTo = "test@test.com";
		sendMail(sendTo);
	}
	
	public static void sendMail(String sendTo)
	{
		final String senderMail = "noreply@alexis-dubus.com"; //the sender email
		final String senderPassword = "NoReply-alexis-dubus"; //the sender email password

		Properties props = new Properties(); //the params for the server connection
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "ns0.ovh.net");
		props.put("mail.smtp.port", "587");

		//create a session with the username and password
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderMail, senderPassword);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderMail));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(sendTo));
			message.setSubject("Sujet du mail"); //the email header
			
			String htmlMessage = "<h1>Test envoi mail</h1>" + 
					"<p>Ceci est un test d'envoi de mail</p>";
			message.setContent(htmlMessage, "text/html; charset=utf-8"); //set the message content

			Transport.send(message); //final action to send the message

			System.out.println("email send");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}