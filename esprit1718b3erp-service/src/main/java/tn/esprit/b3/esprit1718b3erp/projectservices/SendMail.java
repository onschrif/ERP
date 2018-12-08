package tn.esprit.b3.esprit1718b3erp.projectservices;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	 
		static Properties properties;
		static Session session;
		static MimeMessage message;
		
		public static void generateAndSendEmail(String ch) throws AddressException, MessagingException {

			// Step1 : Préparer les mail server properties
			properties = System.getProperties();
			
			properties.put("mail.smtp.port","587"); 
			properties.put("mail.smtp.auth","true"); 
			properties.put("mail.smtp.starttls.enable","true"); 
			
			// Step2 : get mail session
			session = Session.getDefaultInstance(properties, null);
			message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("azzalachheb@gmail.com"));
			message.setFrom("covoiturage.devit@gmail.com");
			message.setSubject("Deadline alert");
			
			String emailBody = "Dear Mrs/Mr,"+ "\n"+" There are less 6 days left to the deadline of your project \n Regards";
			
			message.setContent(emailBody, "text/html");
			
			// Step3 : Send the mail
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", "covoiturage.devit@gmail.com", "azza1964");
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			}
}
