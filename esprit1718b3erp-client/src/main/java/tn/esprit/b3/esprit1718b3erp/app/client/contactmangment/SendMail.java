package tn.esprit.b3.esprit1718b3erp.app.client.contactmangment;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class SendMail {
public void Promotion(String email,String productname)
{
	Notification notif=new Notification();
	try {
    String host ="smtp.gmail.com" ;
       String user = "ahmed.mdallel@esprit.tn";
       String pass = "kakashi01230123";
       String to =email;
       String from = "ahmed.mdallel@esprit.tn";
       String subject = "Promotion.";
       String messageText = "Your have Promotion in "+productname;
       boolean sessionDebug = false;
       Properties props = System.getProperties();
       props.put("mail.smtp.starttls.enable", "true");
       props.put("mail.smtp.host", host);
       props.put("mail.smtp.port", "587");
       props.put("mail.smtp.auth", "true");
       props.put("mail.smtp.starttls.required", "true");
props.put("mail.smtp.starttls.enable", "true"); 

       java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
       Session mailSession = Session.getDefaultInstance(props, null);
       mailSession.setDebug(sessionDebug);
       Message msg = new MimeMessage(mailSession);
       msg.setFrom(new InternetAddress(from));
       InternetAddress[] address = {new InternetAddress(to)};
       msg.setRecipients(Message.RecipientType.TO, address);
       msg.setSubject(subject); msg.setSentDate(new Date());
       msg.setText(messageText);
      Transport transport=mailSession.getTransport("smtp");
      transport.connect(host, user, pass);
      transport.sendMessage(msg, msg.getAllRecipients());
      transport.close();
   }
catch(Exception ex)
   {
       notif.notf_false("PROBLEM","INVALID EMAIL");}
}
}
