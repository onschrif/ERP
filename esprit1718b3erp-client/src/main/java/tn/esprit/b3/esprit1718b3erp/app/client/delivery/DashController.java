package tn.esprit.b3.esprit1718b3erp.app.client.delivery;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import org.controlsfx.control.Notifications;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import tn.esprit.b3.esprit1718b3erp.app.client.login.Login;
import tn.esprit.b3.esprit1718b3erp.assetservices.AssetServiceRemote;
import tn.esprit.b3.esprit1718b3erp.deliveryservices.DeliveryServiceRemote;
import tn.esprit.b3.esprit1718b3erp.employeeservices.EmplServiceRemote;
import tn.esprit.b3.esprit1718b3erp.entities.Assets;
import tn.esprit.b3.esprit1718b3erp.entities.Sess;
import tn.esprit.b3.esprit1718b3erp.orderservices.OrderServiceRemote;
import tn.esprit.b3.esprit1718b3erp.salesservices.opportunityServiceRemote;
import tn.esprit.b3.esprit1718b3erp.services.UserServiceRemote;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

public class DashController implements Initializable {

    Boolean dash;	
    @FXML
    private AnchorPane dashpane;
    @FXML
    private PieChart pie1,pie2,pie3,pie4;
	ServiceLocator s = ServiceLocator.getInstance(); // Service Locator is
														// invocated one time
														// and can be used
														// multiple times.
	AssetServiceRemote ASR = (AssetServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/AssetService!tn.esprit.b3.esprit1718b3erp.assetservices.AssetServiceRemote");
	UserServiceRemote USR = (UserServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/UserService!tn.esprit.b3.esprit1718b3erp.services.UserServiceRemote");
	EmplServiceRemote ESR = (EmplServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/EmplService!tn.esprit.b3.esprit1718b3erp.employeeservices.EmplServiceRemote");

	DeliveryServiceRemote DSR = (DeliveryServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/DeliveryService!tn.esprit.b3.esprit1718b3erp.deliveryservices.DeliveryServiceRemote");
	opportunityServiceRemote OPSR = (opportunityServiceRemote)s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/opportunityService!tn.esprit.b3.esprit1718b3erp.salesservices.opportunityServiceRemote");
	OrderServiceRemote OSR = (OrderServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/OrderService!tn.esprit.b3.esprit1718b3erp.orderservices.OrderServiceRemote");

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	dash=false;
	try {
		assetval(pie1,"Our Fleet");
		opp(pie2,"Opportunities");
		delv(pie3,"Delivery by region");
		ord(pie4,"Orders status");

	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println(OPSR.calopp("Won"));
	}

	
	// Common Block

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
	public void home(ActionEvent event) throws IOException {
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
	}

	@FXML
	public void dsh(ActionEvent event) {
		TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), dashpane);
		if (dash) {
			translateTransition1.setByX(-250);
			dash = false;
		} else {
			translateTransition1.setByX(+250);
			dash = true;
		}
		translateTransition1.play();
	}

	// End of Common Block

	public int type(String s)
	{
		int a=0;
		for(int i=0;i<ASR.findAll().size();i++)
		{
			if(ASR.findAll().get(i).getType().equals(s))
			a+=1;
			
		}
		return a;
	}
	
	
	public void incomechart(PieChart p,String s) throws ParseException {

		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Advertising",20),
				new PieChart.Data("Mission", 20),
				new PieChart.Data("Purchase", 20), new PieChart.Data("Other", 20));
		p.setTitle(s);
		p.setData(pieChartData);

	}
	
	public void assetval(PieChart p,String s) throws ParseException {

		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Functional"+" ("+type("Functional")+")",type("Functional")),
				new PieChart.Data("Out of Order"+ "("+type("Out of Order")+")", type("Out of Order")),
				new PieChart.Data("In maintenance "+"("+type("In maintenance")+")", type("In maintenance")), new PieChart.Data("Retired "+"("+type("Retired")+")", type("Retired")), new PieChart.Data("Reserve "+"("+type("Reserve")+")", type("Reserve")));
		p.setTitle(s);
		p.setData(pieChartData);

	}
	
	public void opp(PieChart p,String s) throws ParseException {

		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Won"+"("+OPSR.calopp("Won")+")",OPSR.calopp("Won")),
				new PieChart.Data("On Progression"+"("+OPSR.calopp("On Progression")+")", OPSR.calopp("On Progression")),
				new PieChart.Data("Lost"+"("+OPSR.calopp("Lost")+")", OPSR.calopp("Lost")));
		p.setTitle(s);	
		p.setData(pieChartData);

	}
	
	public void ord(PieChart p,String s) throws ParseException {

		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Waiting"+"("+OSR.calord("Waiting")+")",OSR.calord("Waiting")),
				new PieChart.Data("Confirmed"+"("+OPSR.calopp("Confirmed")+")", OSR.calord("Confirmed")),
				new PieChart.Data("Cancelled"+"("+OSR.calord("Cancelled")+")", OSR.calord("Cancelled")),
				new PieChart.Data("Paid"+"("+OSR.calord("Paid")+")", OSR.calord("Paid")),new PieChart.Data("Pending"+"("+OSR.calord("Pending")+")", OSR.calord("Pending")),new PieChart.Data("To be delivered"+"("+OSR.calord("To be delivered")+")", OSR.calord("To be delivered")),new PieChart.Data("Delivered"+"("+OSR.calord("Delivered")+")", OSR.calord("Delivered")));
		p.setTitle(s);	
		p.setData(pieChartData);

	}

	public void delv(PieChart p,String s) throws ParseException {

		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Sfax"+"("+DSR.caldel("Sfax ")+")",DSR.caldel("Sfax")),
				new PieChart.Data("Bizerte"+"("+DSR.caldel("Bizerte")+")", DSR.caldel("Bizerte")),
				new PieChart.Data("Sousse"+"("+DSR.caldel("Sousse")+")", DSR.caldel("Sousse")));
		p.setTitle(s);	
		p.setData(pieChartData);

	}
	
}
