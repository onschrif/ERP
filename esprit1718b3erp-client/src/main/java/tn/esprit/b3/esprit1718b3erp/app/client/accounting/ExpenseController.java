package tn.esprit.b3.esprit1718b3erp.app.client.accounting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
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
import tn.esprit.b3.esprit1718b3erp.employeeservices.EmplServiceRemote;
import tn.esprit.b3.esprit1718b3erp.entities.Assets;
import tn.esprit.b3.esprit1718b3erp.entities.BankAccount;
import tn.esprit.b3.esprit1718b3erp.entities.Expense;
import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.entities.Payement;
import tn.esprit.b3.esprit1718b3erp.entities.Sess;
import tn.esprit.b3.esprit1718b3erp.expenseservices.ExpenseServiceRemote;
import tn.esprit.b3.esprit1718b3erp.services.UserServiceRemote;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

public class ExpenseController implements Initializable {

	@FXML
	AnchorPane AssetPane;
	@FXML
	JFXTextField nametf, pricetf, capacitytf, consumptiontf, searchtf;
	@FXML
	DatePicker purchasedt;
	@FXML
	ComboBox<String> typecb, statecb, respcb, paycb;
	@FXML
	JFXSlider arsl;
	@FXML
	TableView<Expense> tbexpense;
	@FXML
	private TableColumn<Expense, String> col1;
	@FXML
	private TableColumn<Expense, String> col2, col3, col4;
	@FXML
	private GridPane assetgrid;
    @FXML
    private JFXButton btn1,btn2,btn3;
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
	ExpenseServiceRemote ExSR = (ExpenseServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/ExpenseService!tn.esprit.b3.esprit1718b3erp.expenseservices.ExpenseServiceRemote");
	AccountingServiceRemote AcSR = (AccountingServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/AccountingService!tn.esprit.b3.esprit1718b3erp.accountingservices.AccountingServiceRemote");

	public void assetload() {
		col1.setCellValueFactory(new Callback<CellDataFeatures<Expense, String>, ObservableValue<String>>() {

			public ObservableValue<String> call(CellDataFeatures<Expense, String> param) {
				return new SimpleStringProperty(param.getValue().getEmp().toString());
			}
		});
		col2.setCellValueFactory(new PropertyValueFactory<>("amount"));
		col3.setCellValueFactory(new PropertyValueFactory<>("type"));
		col4.setCellValueFactory(new PropertyValueFactory<>("status"));
		tbexpense.setItems(null);
		ObservableList<Expense> items = FXCollections.observableArrayList(ExSR.findAll());
		tbexpense.setItems(items);
	}

	public void refresh() {
		ObservableList<Expense> items = FXCollections.observableArrayList(ExSR.findAll());

		tbexpense.setItems(items);

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		lbUser.setText(Sess.current.getFirstName()+" "+Sess.current.getLastName());

		dash = false;
		List<String> State = Arrays.asList("Accepted", "Pending", "Refused");
		List<String> Type = Arrays.asList("Mission", "Advertising", "Purchase", "Other");
		for (int i = 0; i < ESR.findAll().size(); i++) {
			respcb.getItems().add(ESR.findAll().get(i).toString());
		}
		for (int i = 0; i < AcSR.findAll().size(); i++) {
			paycb.getItems().add(AcSR.findAll().get(i).getBIC());
		}
		paycb.getItems().add("Cash");
		statecb.getItems().addAll(State);
		typecb.getItems().addAll(Type);
		statecb.getSelectionModel().selectFirst();
		typecb.getSelectionModel().selectFirst();
		assetload();

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
	public void expenseadd(ActionEvent action) throws ParseException {
		Expense ex=new Expense(Float.valueOf(pricetf.getText()),statecb.getSelectionModel().getSelectedItem(),typecb.getSelectionModel().getSelectedItem(),purchasedt.getValue().toString(),ASR.fillemp(ESR.findAll(), respcb.getSelectionModel().getSelectedIndex()),paycb.getSelectionModel().getSelectedItem());
		refresh();
		clear();
		if(ex.getStatus().equals("Accepted"))
        {
        	if(ex.getSource().equals("Cash") && (BankAccount.getCash()-ex.getAmount())<0)
        	{
        		notf("Operation Failed","Not Enough Funds");
        	}
        	else if (ex.getSource().equals("Cash") && (BankAccount.getCash()-ex.getAmount())>=0)
        	{
        		
        		notf("Operation Successful","Cash Withdrawal was Successful");
    			ExSR.save(ex);
        		refresh();
        		clear();

        	}
        	else
        	{
        	BankAccount bk=AcSR.find(AcSR.getbankid(ex.getSource()));
        	bk.setBalance(bk.getBalance()-ex.getAmount());
        	AcSR.update(bk);
    		notf("Operation Successful","Bank Withdrawal was Successful");
			ExSR.save(ex);
    		refresh();
    		clear();

			try(FileWriter fw = new FileWriter(DashboardController.dest, true);
				    BufferedWriter bw = new BufferedWriter(fw);
				    PrintWriter out = new PrintWriter(bw))
				{
				    out.println("The Expense "+ex.getType()+"was added at "+LocalDateTime.now());
				} catch (IOException e) {
				}
        	}
        }
		else
		{
			ExSR.save(ex);
    		notf("Operation Successful","Expense Registered");
    		refresh();
    		clear();

		}
	}

	@FXML
	public void expensedelete(ActionEvent action) {
		Expense exp=ExSR.find(((Expense) tbexpense.getSelectionModel().getSelectedItem()).getId());
		ExSR.delete(ExSR.find(((Expense) tbexpense.getSelectionModel().getSelectedItem()).getId()));
		clear();
		refresh();
		
		try(FileWriter fw = new FileWriter(DashboardController.dest, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println("The Expense "+exp.getType()+"was deleted at "+LocalDateTime.now());
			} catch (IOException e) {
			}
	}

	@FXML
	public void expenseedit(ActionEvent action) throws ParseException {
		Expense ex=ExSR.find(((Expense) tbexpense.getSelectionModel().getSelectedItem()).getId());
		Expense ex2=new Expense(Float.valueOf(pricetf.getText()),statecb.getSelectionModel().getSelectedItem(),typecb.getSelectionModel().getSelectedItem(),purchasedt.getValue().toString(),ASR.fillemp(ESR.findAll(), respcb.getSelectionModel().getSelectedIndex()),paycb.getSelectionModel().getSelectedItem());
        ex2.setId(ex.getId());
        ExSR.update(ex2);
        if(ex2.getStatus().equals("Accepted"))
        {
        	if(ex2.getSource().equals("Cash") && (BankAccount.getCash()-ex2.getAmount())<0)
        	{
        		notf("Operation Failed","Not Enough Funds");
        	}
        	else
        	{
        	BankAccount bk=AcSR.find(AcSR.getbankid(ex2.getSource()));
        	bk.setBalance(bk.getBalance()-ex2.getAmount());
        	AcSR.update(bk);
        	}
        }
		refresh();
		clear();
		
		try(FileWriter fw = new FileWriter(DashboardController.dest, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println("The Expense "+ex2.getType()+"was edited at "+LocalDateTime.now());
			} catch (IOException e) {
			}
	}

	@FXML
	public void expenseclear(ActionEvent action) {
		clear();
		refresh();
    	btn1.setDisable(false);
    	btn2.setDisable(false);
    	btn3.setDisable(false);

	}

	@FXML
	public void expensesearch(KeyEvent event) {
		ObservableList tableboutique = tbexpense.getItems();
		searchtf.textProperty()
				.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
					if (oldValue != null && (newValue.length() < oldValue.length())) {
						tbexpense.setItems(tableboutique);
					}
					String value = newValue.toLowerCase();
					ObservableList<Expense> subentries = FXCollections.observableArrayList();

					long count = tbexpense.getColumns().stream().count();
					for (int i = 0; i < tbexpense.getItems().size(); i++) {
						for (int j = 0; j < count; j++) {
							String entry = "" + tbexpense.getColumns().get(j).getCellData(i);
							if (entry.toLowerCase().contains(value)) {
								subentries.add(tbexpense.getItems().get(i));
								break;
							}
						}
					}
					tbexpense.setItems(subentries);
				});
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
        respcb.getSelectionModel().clearSelection();
        typecb.getSelectionModel().clearSelection();
        pricetf.setText("");
        paycb.getSelectionModel().clearSelection();
        statecb.getSelectionModel().clearSelection();
        searchtf.setText("");
	}

	@FXML
	public void fill() {
		Expense s = ExSR.find(((Expense) tbexpense.getSelectionModel().getSelectedItem()).getId());
        respcb.getSelectionModel().select(s.getEmp().toString());
        typecb.getSelectionModel().select(s.getType());
        pricetf.setText(s.getAmount()+"");
        paycb.getSelectionModel().select(s.getSource());
        try{
		LocalDate localDate = LocalDate.parse(s.getDate());
        purchasedt.setValue(localDate);
        }
        catch(NullPointerException e)
        {
        	
        }
        statecb.getSelectionModel().select(s.getStatus());
        if(s.getStatus().equals("Accepted"))
        {
        	btn1.setDisable(true);
        	btn2.setDisable(true);
        	btn3.setDisable(true);
        }
        else
        {
        	btn1.setDisable(false);
        	btn2.setDisable(false);
        	btn3.setDisable(false);

        }
	}

	@FXML
	public void selection(ActionEvent event) {

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
