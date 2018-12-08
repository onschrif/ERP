package tn.esprit.b3.esprit1718b3erp.app.client.login;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import tn.esprit.b3.esprit1718b3erp.employeeservices.EmplServiceRemote;
import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Sess;
import tn.esprit.b3.esprit1718b3erp.loginservices.LoginServiceRemote;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class LoginController implements Initializable {

	// Declarations
	@FXML
	private TextField logintf;
	@FXML
	private PasswordField pstf;
	@FXML
	private Label logmsg;

	ServiceLocator s = ServiceLocator.getInstance(); // Service Locator is
														// invocated one time
														// and can be used
														// multiple times.
	LoginServiceRemote LSR = (LoginServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/LoginService!tn.esprit.b3.esprit1718b3erp.loginservices.LoginServiceRemote");
	EmplServiceRemote EmSR = (EmplServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/EmplService!tn.esprit.b3.esprit1718b3erp.employeeservices.EmplServiceRemote");

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		for (int i = 0; i < EmSR.findAll().size(); i++) {
			System.out.println(EmSR.findAll().get(i).getFirstName() + EmSR.findAll().get(i).getNic());
			if (EmSR.findAll().get(i).getPassword() == null) {
				Employee Em = EmSR.findAll().get(i);
				Em.setPassword(LSR.encrypt(Em.getFirstName() + Em.getNic()));
				EmSR.update(Em);
			}
		}
	}

	@FXML
	public void close(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	public void minimize(ActionEvent event) {
		Node source = (Node) event.getSource();
		Window theStage = source.getScene().getWindow();
		((Stage) theStage).setIconified(true);
		try {
			send(event);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void login(ActionEvent event) {
		if (LSR.login(logintf.getText(), LSR.encrypt(pstf.getText()))) {

			try {
				Employee emp = LSR.returnemp(logintf.getText());
				Sess.current = emp;
				switch (emp.getDepartment()) {
				case ("HR"):
					toHR(event);
					break;
				case ("Accounting"):
					toAccounting(event);

					break;
				case ("Contact Management"):
					toContact(event);

					break;
				case ("Sales"):
					toSales(event);

					break;
				case ("Project Management"):
					toProject(event);

					break;

				case ("Supply Chain"):
					toSupply(event);

					break;

				default:
					logmsg.setText("Error in regitering employee,please verify syntax");
					;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		logmsg.setText("The password or login entered are incorrect");
	}

	public void trans(String s, ActionEvent event) throws IOException {
		{
			Parent root = FXMLLoader.load(this.getClass().getResource(s));
			Scene scene = new Scene(root);
			scene.getStylesheets().addAll(Login.class.getResource("style.css").toExternalForm());
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(scene);
			Login.scr(root, app_stage);
			app_stage.show();
		}
	}

	@FXML
	public void toAccounting(ActionEvent event) throws IOException {
		trans("../accounting/Dashboard.fxml", event);
	}

	@FXML
	public void toSales(ActionEvent event) throws IOException {
		trans("../sales/orders.fxml", event);
	}

	@FXML
	public void toSupply(ActionEvent event) throws IOException {
		trans("../scm_purchase/PurchaseDashboard.fxml", event);
	}

	@FXML
	public void toContact(ActionEvent event) throws IOException {
		trans("../contactmangment/clientcrud.fxml", event);
	}

	@FXML
	public void toHR(ActionEvent event) throws IOException {
		trans("../employee/HRMainDashboard.fxml", event);
	}

	@FXML
	public void toProject(ActionEvent event) throws IOException {
		trans("../projectManagement/ProjectDashboard.fxml", event);
	}

	
	
	@SuppressWarnings("restriction")
	private void send(ActionEvent event) throws MalformedURLException, ProtocolException, IOException {
		String s = "Hello ";
		try {

			String host = "smtp.gmail.com";
			String user = "houcem.maiza@esprit.tn";
			String pass = "palaiseau";
			String to = "houcem@gentech.tn";
			String from = "houcem.maiza@esprit.tn";
			String subject = "This is confirmation number for your expertprogramming account. Please insert this number to activate your account.";
			String messageText = "Your passowrd : " + s;
			boolean sessionDebug = false;
			System.out.print("Hello");
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
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setText(messageText);

			Transport transport = mailSession.getTransport("smtp");
			transport.connect(host, user, pass);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			System.out.println("message send successfully");
		} catch (Exception ex) {

		}
	}
}
