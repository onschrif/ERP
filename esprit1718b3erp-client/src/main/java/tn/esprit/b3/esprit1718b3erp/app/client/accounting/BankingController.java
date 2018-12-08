package tn.esprit.b3.esprit1718b3erp.app.client.accounting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.controlsfx.control.Notifications;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.jfoenix.controls.JFXTextField;

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
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.Duration;
import tn.esprit.b3.esprit1718b3erp.accountingservices.AccountingServiceRemote;
import tn.esprit.b3.esprit1718b3erp.app.client.login.Login;
import tn.esprit.b3.esprit1718b3erp.assetservices.AssetServiceRemote;
import tn.esprit.b3.esprit1718b3erp.entities.Assets;
import tn.esprit.b3.esprit1718b3erp.entities.BankAccount;
import tn.esprit.b3.esprit1718b3erp.entities.Client;
import tn.esprit.b3.esprit1718b3erp.entities.Payement;
import tn.esprit.b3.esprit1718b3erp.entities.Sess;
import tn.esprit.b3.esprit1718b3erp.expenseservices.ExpenseServiceRemote;
import tn.esprit.b3.esprit1718b3erp.payementservices.PayementServiceRemote;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

public class BankingController implements Initializable {
	static String acctype1 = "Hard Currency";
	static String acctype2 = "Normal";
	static int bn = -1;
	static Boolean dash;
	static Boolean addmn = false;
	IGenericDAO<BankAccount> d;
	@FXML
	AreaChart<Number, Number> ac;
	@FXML
	AnchorPane BankingPane, detap, cardap;
	@FXML
	Pane bankpane;
	@FXML
	GridPane bankgrid;
	@FXML
	ScrollPane scl;
	@FXML
	BarChart bar;
	@FXML
	ComboBox statcb, typecb, countrycb;
	@FXML
	Label gilb1, gilb2, gilb3, gilb4, balancelb, biclb, typelb, translb, gbplb, yenlb, eurlb;
	@FXML
	TableView<BankAccount> tbassets;
	@FXML
	TableView bdettb, alltb;
	@FXML
	private TableColumn<Payement, String> bncol1;
	@FXML
	private TableColumn<Payement, String> bncol2, bncol3, bncol4, bncol5, col1, col2, col3, col4, col5;
    @FXML
    private JFXTextField c1,c2,c3;
	@FXML
	private Label lbUser;
	
	ServiceLocator s = ServiceLocator.getInstance(); 
	AssetServiceRemote ASR = (AssetServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/AssetService!tn.esprit.b3.esprit1718b3erp.assetservices.AssetServiceRemote");
	AccountingServiceRemote AcSR = (AccountingServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/AccountingService!tn.esprit.b3.esprit1718b3erp.accountingservices.AccountingServiceRemote");
	PayementServiceRemote PSR = (PayementServiceRemote) s.getProxy("esprit1718b3erp-ear/esprit1718b3erp-service/PayementService!tn.esprit.b3.esprit1718b3erp.payementservices.PayementServiceRemote");

	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		lbUser.setText(Sess.current.getFirstName()+" "+Sess.current.getLastName());

		try(FileWriter fw = new FileWriter(DashboardController.dest, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println("the text");
			    out.println("more text");
			} catch (IOException e) {
			}

		
		bankgrid.setMaxWidth(8000);
		addmn = false;
		bn = -1;
		act();
		List<String> State = Arrays.asList("Active", "Inactive");
		List<String> Type = Arrays.asList(acctype2, acctype1);
		statcb.getItems().addAll(State);
		typecb.getItems().addAll(Type);
		String[] locales = Locale.getISOCountries();
		for (String countrylist : locales) {
			Locale obj = new Locale("", countrylist);
			countrycb.setItems(FXCollections.observableArrayList(locales));
		}
         
		loadall();

		dash = false;
		scl.setVbarPolicy(ScrollBarPolicy.NEVER);
		XYChart.Series dataSeries1 = new XYChart.Series();
		dataSeries1.setName("This Year");
		dataSeries1.getData().add(new XYChart.Data(acctype2, AcSR.balancecount(acctype2)));
		dataSeries1.getData().add(new XYChart.Data(acctype1, AcSR.balancecount(acctype1)));
		bar.getData().add(dataSeries1);
		gilb1.setText(AcSR.balancecount(acctype2) + " $");
		gilb2.setText(PSR.findAll().size()+" Transactions");
		gilb3.setText(AcSR.balancecount(acctype1) + " $");
		gilb4.setText(AcSR.findAll().size() + " Accounts");
	}
	// Common Block

	public static Float converter(String ss) {
		String info = "";
		Float f = 0f;
		try {
			String s = "https://spreadsheets.google.com/feeds/list/0Av2v4lMxiJ1AdE9laEZJdzhmMzdmcW90VWNfUTYtM2c/2/public/basic?alt=json";
			URL url1 = new URL(s);
			Scanner scan = new Scanner(url1.openStream());
			String str = new String();
			while (scan.hasNext()) {
				str += scan.nextLine();
			}
			scan.close();
			JSONObject obj = (JSONObject) JSONValue.parse(str);
			JSONObject obj1 = (JSONObject) obj.get("feed");
			JSONArray jsar = new JSONArray();
			jsar = (JSONArray) obj1.get("entry");
			for (int i = 0; i < 89; i++) {
				JSONObject obj2 = (JSONObject) jsar.get(i);
				JSONObject obj3 = (JSONObject) obj2.get("content");
				JSONObject obj4 = (JSONObject) obj2.get("title");
				if (obj4.get("$t").equals(ss)) {
					info = (String) obj3.get("$t");
					System.out.println(info);
					f = Float.valueOf(info.substring(8, info.length()));
					return f;
				}
			}

		} catch (IOException e) {
		}
		return f;
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

	public void arcchart(int i) throws ParseException {
		ac.getData().clear();
		XYChart.Series ds1 = new XYChart.Series();
		ac.getYAxis().setLabel("Balance");
		ac.getXAxis().setLabel("Month");
		ds1.setName("This Account");
		ds1.getData().add(new XYChart.Data("January", AcSR.getmonthlybalance(1, i)));
		ds1.getData().add(new XYChart.Data("April", AcSR.getmonthlybalance(4, i)));
		ds1.getData().add(new XYChart.Data("July", AcSR.getmonthlybalance(7, i)));
		ds1.getData().add(new XYChart.Data("October", AcSR.getmonthlybalance(10, i)));
		XYChart.Series ds2 = new XYChart.Series();
		ds2.getData().add(new XYChart.Data("January", AcSR.getmonthlyaverage(1)/AcSR.findAll().size()));
		ds2.getData().add(new XYChart.Data("April", AcSR.getmonthlyaverage(4)/AcSR.findAll().size()));
		ds2.getData().add(new XYChart.Data("July", AcSR.getmonthlyaverage(7)/AcSR.findAll().size()));
		ds2.getData().add(new XYChart.Data("October", AcSR.getmonthlyaverage(10)/AcSR.findAll().size()));
		ds2.setName("Average");

		ac.getData().add(ds1);
		ac.getData().add(ds2);
		ac.setAnimated(false);

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
		TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), BankingPane);
		if (dash) {
			translateTransition1.setByX(-250);
			dash = false;
		} else {
			translateTransition1.setByX(+250);
			dash = true;
		}
		translateTransition1.play();
	}

	@FXML
	public void add() {
		TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), BankingPane);
		translateTransition1.setByY(+181);
		if (!addmn)
			translateTransition1.play();
		addmn = true;
	}

	@FXML
	public void add2(ActionEvent event) {
		TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), BankingPane);
		translateTransition1.setByY(-181);
		translateTransition1.play();
		addmn = false;
	}

	public void act() throws NullPointerException {
		try {
			List<BankAccount> d = ASR.findAllUsers();
			for (int i = 0; i < d.size(); i++) {
				bn += 1;

				Pane P = new Pane();
				Button details = new Button("View details");

				if (bn == 0)
					P.getStyleClass().add("bank-acc");

				if (bn == 1 || (bn > 4 && bn % 5 == 0)) {
					P.getStyleClass().add("bank-acc1");

				}
				if (bn > 1 && bn % 2 == 0)
					P.getStyleClass().add("bank-acc2");
				if (bn > 2 && bn % 3 == 0 && bn % 6 != 0)
					P.getStyleClass().add("bank-acc3");
				if (bn > 3 && bn % 4 == 0)
					P.getStyleClass().add("bank-acc4");
				P.setPrefHeight(151);
				P.setPrefWidth(236);

				Label l1 = new Label(d.get(i).getBankName());
				Label l2 = new Label("Verax Magus");
				Label l3 = new Label(d.get(i).getBIC());
				Label l4 = new Label(d.get(i).getCountry());
				Label l5 = new Label("Balance");
				Label l6 = new Label(d.get(i).getBalance() + " $");
				Label l7 = new Label(d.get(i).getType());
				Float balance = d.get(i).getBalance().floatValue();
				details.getStyleClass().add("detailsb");
				details.setUnderline(true);
				l5.getStyleClass().add("bold-up");
				l1.getStyleClass().add("bold-up");
				l4.getStyleClass().add("bold-up");
				l1.getStyleClass().add("whites");
				l2.getStyleClass().add("whites");
				l3.getStyleClass().add("whites");
				l4.getStyleClass().add("whites");
				l5.getStyleClass().add("whites");
				l6.getStyleClass().add("whites");
				l6.setId("Balance");
				P.getChildren().add(0, l1);
				P.getChildren().add(1, l2);
				P.getChildren().add(2, l3);
				P.getChildren().add(3, l4);
				P.getChildren().add(4, l5);
				P.getChildren().add(5, l6);
				P.getChildren().add(6, details);
				l1.setLayoutX(15);
				l1.setLayoutY(10);
				l2.setLayoutX(15);
				l2.setLayoutY(60);
				l3.setLayoutX(15);
				l3.setLayoutY(40);
				l4.setLayoutX(180);
				l4.setLayoutY(10);
				l5.setLayoutX(150);
				l5.setLayoutY(90);
				l6.setLayoutX(142);
				l6.setLayoutY(110);
				details.setLayoutX(15);
				details.setLayoutY(90);
				bankgrid.addColumn(bn, P);
				details.setOnAction((event) -> {
					scl.setVisible(false);
					BankingPane.setVisible(false);
					detap.setVisible(true);
					balancelb.setText(l6.getText() + "");
					biclb.setText(l3.getText() + "");
					typelb.setText(l7.getText() + "");
					translb.setText(0 + "");
					gbplb.setText(converter("GBP") * balance + " £");
					yenlb.setText(converter("JPY") * balance + " ¥");
					eurlb.setText(converter("EUR") * balance + " €");
					SpecificBankload(AcSR.getbankid(l3.getText()));
					try {
						arcchart(AcSR.getbankid(l3.getText()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				System.out.println(bn);

			}
		} catch (NullPointerException E) {

		}
		Pane P = new Pane();
		P.getStyleClass().add("bank-acc");
		Button details = new Button("Add new account");
		details.getStyleClass().add("detailsb");
		details.setId("acad");
		details.setUnderline(true);
		bankgrid.addColumn(bn+1,P);
		P.setPrefHeight(151);
		P.setPrefWidth(236);
		P.getChildren().add(details);
		details.setLayoutX(25);
		details.setLayoutY(50);
		details.setOnAction((event) -> {
			add();
		});

	}

	public void SpecificBankload(int s) {
		bncol5.setCellValueFactory(new PropertyValueFactory<>("PaymentDate"));
		bncol1.setCellValueFactory(new PropertyValueFactory<>("ref"));
		bncol2.setCellValueFactory(new Callback<CellDataFeatures<Payement, String>, ObservableValue<String>>() {

			public ObservableValue<String> call(CellDataFeatures<Payement, String> param) {
				return new SimpleStringProperty(param.getValue().getC().getCompany());
			}
		});
		bncol3.setCellValueFactory(new PropertyValueFactory<>("amount"));
		bncol4.setCellValueFactory(new Callback<CellDataFeatures<Payement, String>, ObservableValue<String>>() {

			public ObservableValue<String> call(CellDataFeatures<Payement, String> param) {
				return new SimpleStringProperty(param.getValue().getB().getBankName());
			}
		});
		bdettb.setItems(null);
		ObservableList<Payement> items = FXCollections.observableArrayList(AcSR.findpay(s));
		bdettb.setItems(items);
	}

	public void loadall()  {

		col5.setCellValueFactory(new PropertyValueFactory<>("PaymentDate"));
		col1.setCellValueFactory(new PropertyValueFactory<>("ref"));
		col2.setCellValueFactory(new Callback<CellDataFeatures<Payement, String>, ObservableValue<String>>() {

			public ObservableValue<String> call(CellDataFeatures<Payement, String> param) {
				try{
				return new SimpleStringProperty(param.getValue().getC().getCompany());
				}
				catch(NullPointerException e)
				{
					
				}
				return null;
			}
		});
		col3.setCellValueFactory(new PropertyValueFactory<>("amount"));
		col4.setCellValueFactory(new Callback<CellDataFeatures<Payement, String>, ObservableValue<String>>() {

			public ObservableValue<String> call(CellDataFeatures<Payement, String> param) {
				try{

				return new SimpleStringProperty(param.getValue().getB().getBankName());
				}
				catch(NullPointerException e)
				{
					
				}
				return null;
			}
		});
		alltb.setItems(null);
		ObservableList<Payement> items = FXCollections.observableArrayList(AcSR.listpayement());
		alltb.setItems(items);

	}

	@FXML
	public void backup(ActionEvent event) {
		scl.setVisible(true);
		BankingPane.setVisible(true);
		detap.setVisible(false);

	}
	
	void notf(String s, String s2) {
		Notifications notificationbuilder = Notifications.create().title(s).text(s2).hideAfter(Duration.seconds(5))
				.position(Pos.BOTTOM_RIGHT);
		notificationbuilder.darkStyle();
		notificationbuilder.show();
	}
	
	@FXML void bankaccadd(ActionEvent event)
	{
		Locale loc = new Locale("",countrycb.getSelectionModel().getSelectedItem().toString());
		try{
		BankAccount bnkc=new BankAccount(c2.getText(),c1.getText(),Float.valueOf(c3.getText()),typecb.getSelectionModel().getSelectedItem().toString(),statcb.getSelectionModel().getSelectedItem().toString(),loc.getDisplayCountry(),
				"BK-"+c2.hashCode());
		AcSR.save(bnkc);
		
		add2(event);
		try {
			trans("../accounting/Banking.fxml", event);
		} catch (IOException e) {
			e.printStackTrace();
		}

		}
		catch(NullPointerException e)
		{
			notf("Operation Failed", "One or more cases are not filled");

		}
	}
	
	// End of Common Block
	
	
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
