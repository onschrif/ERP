package tn.esprit.b3.esprit1718b3erp.app.client.accounting;

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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.Duration;
import tn.esprit.b3.esprit1718b3erp.accountingservices.AccountingServiceRemote;
import tn.esprit.b3.esprit1718b3erp.app.client.login.Login;
import tn.esprit.b3.esprit1718b3erp.assetservices.AssetServiceRemote;
import tn.esprit.b3.esprit1718b3erp.entities.BankAccount;
import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Expense;
import tn.esprit.b3.esprit1718b3erp.entities.Payement;
import tn.esprit.b3.esprit1718b3erp.entities.Sess;
import tn.esprit.b3.esprit1718b3erp.expenseservices.ExpenseServiceRemote;
import tn.esprit.b3.esprit1718b3erp.loginservices.LoginServiceRemote;
import tn.esprit.b3.esprit1718b3erp.payementservices.PayementServiceRemote;
import tn.esprit.b3.esprit1718b3erp.services.UserServiceRemote;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CashController implements Initializable {

	@FXML
	private PieChart pie;
	@FXML
	private BarChart bar;
	@FXML
	private AnchorPane cashap;
	static Boolean dash = false;
	@FXML
    Label gbplb, yenlb, eurlb;
	@FXML
	ComboBox bankcb;
	@FXML
	TableView tbpay,expap;
	@FXML
	TableColumn<Payement, String> pcol1, pcol2, pcol3, pcol4, pcol5;
    @FXML
	TableColumn<Expense, String> ecol1, ecol2, ecol3, ecol4, ecol5;
    
	@FXML
	Label totallb,cashlb,balancelb,biclb,typelb;
	@FXML
	private Label lbUser;
	@FXML
	public void dsh(ActionEvent event) {
		TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), cashap);
		if (dash) {
			translateTransition1.setByX(-250);
			dash = false;
		} else {
			translateTransition1.setByX(+250);
			dash = true;
		}
		translateTransition1.play();
	}

	ServiceLocator s = ServiceLocator.getInstance(); // Service Locator is
	AssetServiceRemote ASR = (AssetServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/AssetService!tn.esprit.b3.esprit1718b3erp.assetservices.AssetServiceRemote");

	AccountingServiceRemote AcSR = (AccountingServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/AccountingService!tn.esprit.b3.esprit1718b3erp.accountingservices.AccountingServiceRemote");

	ExpenseServiceRemote ESR = (ExpenseServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/ExpenseService!tn.esprit.b3.esprit1718b3erp.expenseservices.ExpenseServiceRemote");

	PayementServiceRemote PSR = (PayementServiceRemote) s.getProxy("esprit1718b3erp-ear/esprit1718b3erp-service/PayementService!tn.esprit.b3.esprit1718b3erp.payementservices.PayementServiceRemote");

	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		dash=false;
		loadall2();
		loadall();
		gbplb.setText(BankAccount.getCash()*BankingController.converter("GBP")+" £");
		yenlb.setText(BankAccount.getCash()*BankingController.converter("JPY")+" ‎¥");
		eurlb.setText(BankAccount.getCash()*BankingController.converter("EUR")+" €");
		balancelb.setText(BankAccount.getCash()+" $");
		typelb.setText(ESR.findAll().size()+"");
		biclb.setText(PSR.listcash().size()+"");
		lbUser.setText(Sess.current.getFirstName()+" "+Sess.current.getLastName());

		
		
		
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


	public void loadall() {

		pcol2.setCellValueFactory(new PropertyValueFactory<>("PaymentDate"));
		pcol1.setCellValueFactory(new PropertyValueFactory<>("ref"));
		pcol4.setCellValueFactory(new Callback<CellDataFeatures<Payement, String>, ObservableValue<String>>() {

			public ObservableValue<String> call(CellDataFeatures<Payement, String> param) {
				return new SimpleStringProperty(param.getValue().getC().getCompany());
			}
		});
		pcol5.setCellValueFactory(new PropertyValueFactory<>("amount"));
		pcol3.setCellValueFactory(new Callback<CellDataFeatures<Payement, String>, ObservableValue<String>>() {

			public ObservableValue<String> call(CellDataFeatures<Payement, String> param) {
if(param.getValue().getB()==null)
				return new SimpleStringProperty("Cash");
else
	return new SimpleStringProperty(param.getValue().getB().getBankName());

			}
		});
		tbpay.setItems(null);
		
		ObservableList<Payement> items = FXCollections.observableArrayList(PSR.listcash());
		tbpay.setItems(items);

	}
	
	public void loadall2() {

		ecol1.setCellValueFactory(new PropertyValueFactory<>("date"));
		ecol2.setCellValueFactory(new PropertyValueFactory<>("Type"));
		ecol3.setCellValueFactory(new Callback<CellDataFeatures<Expense, String>, ObservableValue<String>>() {

			public ObservableValue<String> call(CellDataFeatures<Expense, String> param) {
				return new SimpleStringProperty(param.getValue().getEmp().toString());
			}
		});
		ecol4.setCellValueFactory(new PropertyValueFactory<>("amount"));
		ecol5.setCellValueFactory(new PropertyValueFactory<>("status"));

		expap.setItems(null);
		ObservableList<Expense> items = FXCollections.observableArrayList(ESR.findAll());
		expap.setItems(items);

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
