package tn.esprit.b3.esprit1718b3erp.app.client.accounting;

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

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import org.controlsfx.control.Notifications;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.Duration;
import tn.esprit.b3.esprit1718b3erp.accountingservices.AccountingServiceRemote;
import tn.esprit.b3.esprit1718b3erp.app.client.login.Login;
import tn.esprit.b3.esprit1718b3erp.assetservices.AssetServiceRemote;
import tn.esprit.b3.esprit1718b3erp.clientservices.ClientServiceRemote;
import tn.esprit.b3.esprit1718b3erp.employeeservices.EmplServiceRemote;
import tn.esprit.b3.esprit1718b3erp.entities.Assets;
import tn.esprit.b3.esprit1718b3erp.entities.BankAccount;
import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.entities.Payement;
import tn.esprit.b3.esprit1718b3erp.entities.Sess;
import tn.esprit.b3.esprit1718b3erp.payementservices.PayementServiceRemote;
import tn.esprit.b3.esprit1718b3erp.salesservices.salesServiceRemote;
import tn.esprit.b3.esprit1718b3erp.services.UserServiceRemote;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

public class InvoiceController implements Initializable {

	@FXML
	AnchorPane AssetPane;
	@FXML
	JFXTextField nametf, pricetf, capacitytf, consumptiontf;
	@FXML
	DatePicker purchasedt;
	@FXML
	ComboBox<String> typecb, statecb, respcb;
	@FXML
	JFXSlider arsl;
	@FXML
	TableView tbinvoice;
	@FXML
	private TableColumn<Orders, String> col1;
	@FXML
	private TableColumn<Orders, String>col3, col4;
	@FXML
	private TableColumn<Orders, String> col2;
	@FXML
	private GridPane assetgrid;
	@FXML
	private JFXButton btn;
	@FXML
	private Label lbUser;

	static Boolean dash;

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
	salesServiceRemote SSR = (salesServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/salesService!tn.esprit.b3.esprit1718b3erp.salesservices.salesServiceRemote");
	ClientServiceRemote CSR = (ClientServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/ClientService!tn.esprit.b3.esprit1718b3erp.clientservices.ClientServiceRemote");
	AccountingServiceRemote AcSR = (AccountingServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/AccountingService!tn.esprit.b3.esprit1718b3erp.accountingservices.AccountingServiceRemote");
	PayementServiceRemote PSR = (PayementServiceRemote) s.getProxy("esprit1718b3erp-ear/esprit1718b3erp-service/PayementService!tn.esprit.b3.esprit1718b3erp.payementservices.PayementServiceRemote");

	public void invload() {
		col1.setCellValueFactory(new PropertyValueFactory<>("referenceOrder"));
		col2.setCellValueFactory(new Callback<CellDataFeatures<Orders, String>, ObservableValue<String>>() {

			public ObservableValue<String> call(CellDataFeatures<Orders, String> param) {
				return new SimpleStringProperty(param.getValue().getC().getCompany());
			}
		});
		col3.setCellValueFactory(new PropertyValueFactory<>("orderAmount"));
		col4.setCellValueFactory(new PropertyValueFactory<>("orderState"));
		tbinvoice.setItems(null);
		ObservableList<Orders> items = FXCollections.observableArrayList(SSR.findAll());
		tbinvoice.setItems(items);
	}

	public void refresh() {
		ObservableList<Orders> items = FXCollections.observableArrayList(SSR.findAll());

		tbinvoice.setItems(items);

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		lbUser.setText(Sess.current.getFirstName()+" "+Sess.current.getLastName());

		dash = false;
		
		for (int i = 0; i < CSR.findAll().size(); i++) {
			respcb.getItems().add(CSR.findAll().get(i).getCompany()); 
		}
		for (int i = 0; i < AcSR.findAll().size(); i++) {
			typecb.getItems().add(AcSR.findAll().get(i).getBIC()); 
		}
		List<String> State = Arrays.asList("Pending", "Paid");
		
		statecb.getItems().addAll(State);
		
		typecb.getItems().add("Cash");
		invload();
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
		TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), AssetPane);
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

	@FXML
	public void addpayment(ActionEvent action) throws ParseException {
		Orders s = SSR.find(((Orders) tbinvoice.getSelectionModel().getSelectedItem()).getIdOrder());
	    s.setOrderState(statecb.getSelectionModel().getSelectedItem());
	    SSR.update(s);
	    Date today=new Date();
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = new Date();
        if(statecb.getSelectionModel().getSelectedItem().equals("Paid"))
        {
	    if(typecb.getSelectionModel().getSelectedItem().equals("Cash"))
	    	{
	    	Payement p=new Payement("Cash-in","TF-"+today.hashCode()+"-C",dateFormat.format(date),Float.valueOf(pricetf.getText()),s.getC(),AcSR.find(AcSR.getbankid(typecb.getSelectionModel().getSelectedItem())));
	    	PSR.save(p);
            notf("Payment Successful","Cash-in was successfully registered");

	    	try(FileWriter fw = new FileWriter(DashboardController.dest, true);
				    BufferedWriter bw = new BufferedWriter(fw);
				    PrintWriter out = new PrintWriter(bw))
				{
				    out.println("The Payement "+p.getRef()+"was added at "+LocalDateTime.now());
				} catch (IOException e) {
				}
	    	}
	    else
	    {
	    	BankAccount bk=AcSR.find(AcSR.getbankid(typecb.getSelectionModel().getSelectedItem()));

	    	Payement p=new Payement("Wire","TF-"+today.hashCode()+"-W",dateFormat.format(date),Float.valueOf(pricetf.getText()),s.getC(),bk);
	    	bk.setBalance(bk.getBalance()+Float.valueOf(pricetf.getText()));
	    	AcSR.update(bk);
	    	PSR.save(p);
	        notf("Payment Successful","Bank Wire was successfully registered");

	    	try(FileWriter fw = new FileWriter(DashboardController.dest, true);
    			    BufferedWriter bw = new BufferedWriter(fw);
    			    PrintWriter out = new PrintWriter(bw))
    			{
    			    out.println("The Payement "+p.getRef()+"was added at "+LocalDateTime.now());
    			} catch (IOException e) {
    			}
	    }
        }
        else
        notf("Payment Failed","State was not changed");
        
		refresh();

		try(FileWriter fw = new FileWriter(DashboardController.dest, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println("The Payement "+s.getReferenceOrder()+"was added at "+LocalDateTime.now());
			} catch (IOException e) {
			}
	}




	@FXML
	public void assetclear(ActionEvent action) {
		clear();
		refresh();
	}

	

	@FXML
	public void refr(ActionEvent event) {
		refresh();
	}

	void notf(String s, String s2) {
		Notifications notificationbuilder = Notifications.create().title(s).text(s2).hideAfter(Duration.seconds(5))
				.position(Pos.BOTTOM_RIGHT);
		notificationbuilder.darkStyle();
		notificationbuilder.show();
	}

	public void clear() {
	}

	@FXML
	public void fill() {
		Orders s = SSR.find(((Orders) tbinvoice.getSelectionModel().getSelectedItem()).getIdOrder());
        respcb.getSelectionModel().select(s.getC().getCompany());
        respcb.setEditable(false);
        nametf.setText(s.getReferenceOrder());
        nametf.setEditable(false);
        pricetf.setText(s.getOrderAmount()+"");
        pricetf.setEditable(false);
        if(s.getOrderState().equals("Paid"))
        	{        
        	statecb.setEditable(false);
            btn.setDisable(true);

        	}
        else
        btn.setDisable(false);

        
	}
	
	@FXML
	public void toBalance(ActionEvent event) throws IOException {
		trans("../accounting/Balance.fxml", event);
	}
	
	@FXML
	public void toTax(ActionEvent event) throws IOException {
		trans("../accounting/Taxation.fxml", event);
	}
	
	@FXML
	public void toInv(ActionEvent event) throws IOException {
		trans("../accounting/Invoice.fxml", event);
	}
	
	@FXML
	public void toExp(ActionEvent event) throws IOException {
		trans("../accounting/Expense.fxml", event);
	}
	
	@FXML
	public void toAsset(ActionEvent event) throws IOException {
		trans("../accounting/Assets.fxml", event);
	}
	
	@FXML
	public void toBanking(ActionEvent event) throws IOException {
		trans("../accounting/Banking.fxml", event);
	}

	@FXML
	public void toDashboard(ActionEvent event) throws IOException {
		trans("../accounting/Dashboard.fxml", event);
	}
	
	@FXML
	public void toCash(ActionEvent event) throws IOException {
		trans("../accounting/Cash.fxml", event);
	}
	


}
