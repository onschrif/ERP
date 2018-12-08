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
import javafx.scene.chart.AreaChart;
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
import tn.esprit.b3.esprit1718b3erp.employeeservices.EmplServiceRemote;
import tn.esprit.b3.esprit1718b3erp.entities.Assets;
import tn.esprit.b3.esprit1718b3erp.entities.BankAccount;
import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Payement;
import tn.esprit.b3.esprit1718b3erp.entities.Sess;
import tn.esprit.b3.esprit1718b3erp.entities.Taxation;
import tn.esprit.b3.esprit1718b3erp.expenseservices.ExpenseServiceRemote;
import tn.esprit.b3.esprit1718b3erp.loginservices.LoginServiceRemote;
import tn.esprit.b3.esprit1718b3erp.services.UserServiceRemote;
import tn.esprit.b3.esprit1718b3erp.taxationservices.TaxationServiceRemote;
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
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;

public class BalanceController implements Initializable {

	@FXML
	private PieChart pie;
	@FXML
	private BarChart ac;
	@FXML
	private AnchorPane balap;
	static Boolean dash = false;
	@FXML
	ComboBox bankcb;
	@FXML
	Label lb1,lb2,lb3,lb4,lb5,lb6,lb7,lb8,lb9,lb10,lb11,datelb;
	@FXML
	Label gbplb, yenlb, eurlb,cnylb,cadlb;
	@FXML
	JFXSlider arsl;
	@FXML
	ComboBox<String> statecb;
	@FXML
	JFXTextField nametf;
	@FXML
	TableView<Taxation> tbtax;
	@FXML
	private TableColumn<Taxation, String> col1;
	@FXML
	private TableColumn<Taxation, String> col2;
	@FXML
	private TableColumn<Taxation, Float> col4;
	@FXML
	private Label lbUser;
	
	
	@FXML
	public void dsh(ActionEvent event) {
		TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), balap);
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

	EmplServiceRemote EmSR = (EmplServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/EmplService!tn.esprit.b3.esprit1718b3erp.employeeservices.EmplServiceRemote");
	
	TaxationServiceRemote TSR = (TaxationServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/TaxationService!tn.esprit.b3.esprit1718b3erp.taxationservices.TaxationServiceRemote");

	
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		lbUser.setText(Sess.current.getFirstName()+" "+Sess.current.getLastName());

		dash = false;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date today = new Date();

		datelb.setText(dateFormat.format(today).toString());
		lb1.setText(retbankacc()+" $");
		lb2.setText(BankAccount.getCash()+" $");
		lb3.setText(retassets()+" $");
		Float f=retbankacc()+retassets()+BankAccount.getCash();
        lb4.setText(f+" $");
        lb5.setText(retexpenses()+" $");
        lb6.setText(retamort()+" $");
        lb7.setText(retsalaries()+" $");
		Float f2=retexpenses()+retamort()+retsalaries();
        lb8.setText(f2+" $");
        lb9.setText(rettax()+" $");
        Float f3=f-(f2);
        lb10.setText(f3+" $");
        Float f4=f3-rettax();
        lb11.setText(f4+" $");
        eurlb.setText(BankingController.converter("EUR")*f4+" €");
        gbplb.setText(BankingController.converter("GBP")*f4+" £");
        yenlb.setText(BankingController.converter("JPY")*f4+" ¥‎");
        cnylb.setText(BankingController.converter("CNY")*f4+" 元");
        cadlb.setText(BankingController.converter("CAD")*f4+" C$");

        try {
			arcchart(f,f2,f4,rettax());
		} catch (ParseException e) {
		}
	}
	
	public float retbankacc()
	{
		float f=0f;
		for(int i=0;i<AcSR.findAll().size();i++)
		{
			f+=AcSR.findAll().get(i).getBalance();
		}
		return f;
		
	}
	
	public float retassets()
	{
		float f=0f;
		for(int i=0;i<ASR.findAll().size();i++)
		{
			f+=ASR.findAll().get(i).getPriceOnPurchase();
		}
		return f;
		
	}
	
	public float rettax()
	{
		float f=0f;
		for(int i=0;i<TSR.findAll().size();i++)
		{
         if(TSR.findAll().get(i).getName().equals("company"))
         {
        	 Float x=retbankacc()+retassets()+BankAccount.getCash();
        	 System.out.println(TSR.findAll().get(i).getVat_value());
        	 f=(float) ((TSR.findAll().get(i).getVat_value()*0.01)*(x));
         }
		}
		return f;
		
	}	
	
	
	public float retamort()
	{
		float f=0f;
		for(int i=0;i<ASR.findAll().size();i++)
		{
			f+=ASR.findAll().get(i).getPriceOnPurchase()*(ASR.findAll().get(i).getAmortizationRate()/100);
		}
		return f;
		
	}
	
	public float retexpenses()
	{
		float f=0f;
		for(int i=0;i<ESR.findAll().size();i++)
		{
			f+=ESR.findAll().get(i).getAmount();
		}
		return f;
	}
	
	public float retsalaries()
	{
		float f=0f;
		for(int i=0;i<ESR.findAll().size();i++)
		{
			f+=ESR.findAll().get(i).getAmount();
		}
		return f*12;
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
	
	
	
	public void arcchart(float a,float b,float c,float d) throws ParseException {
		ac.getData().clear();
		XYChart.Series ds1 = new XYChart.Series();
		ac.getYAxis().setLabel("Amount (in $)");
		ac.getXAxis().setLabel("Month");
		ds1.setName("Assets");
		ds1.getData().add(new XYChart.Data("2018",a));

		XYChart.Series ds2 = new XYChart.Series();
		ds2.getData().add(new XYChart.Data("2018",b));

		ds2.setName("Liabilities");

		XYChart.Series ds3 = new XYChart.Series();
		ds3.getData().add(new XYChart.Data("2018",c));

		ds3.setName("Net Income");
		
		XYChart.Series ds4 = new XYChart.Series();
		ds4.getData().add(new XYChart.Data("2018",d));
		ds4.setName("Taxes");

		
		ac.getData().add(ds1);
		ac.getData().add(ds2);
		ac.getData().add(ds3);
		ac.getData().add(ds4);

		ac.setAnimated(false);

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