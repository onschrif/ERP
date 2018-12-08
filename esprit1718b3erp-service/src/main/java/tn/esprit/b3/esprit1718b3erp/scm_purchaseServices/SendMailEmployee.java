package tn.esprit.b3.esprit1718b3erp.scm_purchaseServices;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import tn.esprit.b3.esprit1718b3erp.entities.Production;

public class SendMailEmployee {

	static Properties properties;
	static Session session;
	static MimeMessage message;
	
	
	public static void SendingEmail(String email,Production pro)  {
		
		try {
		// Step1 : Préparer les mail server properties
					properties = System.getProperties();
					
					properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

					properties.put("mail.smtp.port","587"); 
					properties.put("mail.smtp.auth","true"); 
					properties.put("mail.smtp.starttls.enable","true"); 
		
					properties.put("mail.smtp.starttls.required", "true");
				 
					
					
					
					// Step2 : get mail session
					session = Session.getDefaultInstance(properties, null);
					message = new MimeMessage(session);
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
					message.setFrom("devit1718@gmail.com");
					message.setSubject("Production");
					

					String emailBody = " <h2>Dear "+pro.getPr_employee().getFirstName()+ ", </h2><br>\n\n " 
				       	 + "<h4>You are assigned as manager to do this production </h4> <br>  "
						 + "<h4>ProductionID = " + pro.getIdProduction() + " </h4><br>\n "
						 + "<h4>Start Day = " + pro.getStartDay() + " </h4><br>\n\n  "
						 + "<h4>Deadline  = " + pro.getEndDay() + " </h4><br>\n\n  "
						 + "<h4>==> Durations =  " + pro.getNumberHeures() + " Heures </h4><br>\n\n  "
						 + "<h4>Product  = " + "Name : "+pro.getPr_product().getName() +"  Reference : "+ pro.getPr_product().getRef()  + " </h4><br>\n\n  "
						 + "<h4>Quantity To Produce	= " +pro.getQteToProduce() + " </h4><br>\n\n  "
						 + "<h4>Neded" + "</h4> <br>\n\n  "
						 + "<h3>Regards";
					
					message.setContent(emailBody, "text/html");
					
					// Step3 : Send the mail
					Transport transport = session.getTransport("smtp");
					transport.connect("smtp.gmail.com", "devit1718@gmail.com", "devit1718");
					transport.sendMessage(message, message.getAllRecipients());
					transport.close();
		}
		catch(Exception ex)
		   {
			System.err.println("kkkkkkkkkkk"+ex.getMessage());
		}
		
	}
}
