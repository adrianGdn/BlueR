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
		String sendTo = "contact@alexis-dubus.com";
		sendMail(sendTo);
	}
	
	public static boolean sendMail(String sendTo)
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
			message.setSubject("Réduction exceptionelle sur pc portable gamer MSI!"); //the email header
			
			String htmlMessage = "<h1>Bonjour cher client</h1>" + 
					  "<p>Seriez-vous intéressé par le produit suivant :</p>"
					+ "<p>PC portable gamer MSI GS73 7RE-007XFR à 1,1298 € : -30% !</p>"
					+ "<img src=\"https://www.materiel.net/live/391812.350.350.jpg\" alt=\"Ordinateur portable MSI GS73 7RE-007XFR\">"
					+ "<li>Ecran : 17.3''</li>"
					+ "<li>Processeur Intel® Core™ i7-7700HQ (2,80 GHz)</li>"
					+ "<li>RAM : 8 Go</li>"
					+ "<li>SSD : 128 Go</li>"
					+ "<li>Disque dur : 1 To</li>"
					+ "<li>Carte graphique : NVIDIA GeForce GTX 1050 Ti</li>"
					+ "<a href='https://www.materiel.net/pc-portable/msi-gs73-7re-007xfr-144760.html'>Acheter cet article</a>";
			message.setContent(htmlMessage, "text/html; charset=utf-8"); //set the message content

			Transport.send(message); //final action to send the message

			return true;

		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
}