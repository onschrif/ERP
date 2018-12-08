package tn.esprit.b3.esprit1718b3erp.app.client.accounting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import tn.esprit.b3.esprit1718b3erp.accountingservices.AccountingServiceRemote;
import tn.esprit.b3.esprit1718b3erp.app.client.login.Login;
import tn.esprit.b3.esprit1718b3erp.assetservices.AssetServiceRemote;
import tn.esprit.b3.esprit1718b3erp.employeeservices.EmplServiceRemote;
import tn.esprit.b3.esprit1718b3erp.entities.Assets;
import tn.esprit.b3.esprit1718b3erp.entities.Sess;
import tn.esprit.b3.esprit1718b3erp.entities.Taxation;
import tn.esprit.b3.esprit1718b3erp.expenseservices.ExpenseServiceRemote;
import tn.esprit.b3.esprit1718b3erp.taxationservices.TaxationServiceRemote;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

public class TaxationController implements Initializable {

	@FXML
	private AnchorPane taxap;
	static Boolean dash = false;
	@FXML
	ComboBox bankcb;
	@FXML
	Label gbplb, yenlb, eurlb, cnylb, cadlb,lbUser;
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
	public void dsh(ActionEvent event) {
		TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), taxap);
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

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		dash = false;
		lbUser.setText(Sess.current.getFirstName()+" "+Sess.current.getLastName());

		taxload();

		List<String> State = Arrays.asList("Active", "Inactive");
		statecb.getItems().addAll(State);
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
	public void taxadd(ActionEvent event) {
		Taxation tax = new Taxation(nametf.getText(),statecb.getSelectionModel().getSelectedItem(),Math.round((float)arsl.getValue()));
		TSR.save(tax);
		clear();
		
		try(FileWriter fw = new FileWriter(DashboardController.dest, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println("The Tax "+tax.getName()+"was added at"+LocalDate.now());
			} catch (IOException e) {
			}
	}

	
	
	@FXML
	public void taxedit(ActionEvent event) {
		
		Taxation tax = new Taxation(nametf.getText(),statecb.getSelectionModel().getSelectedItem(),Math.round((float)arsl.getValue()));
		Taxation tax2 = TSR.find(((Taxation) tbtax.getSelectionModel().getSelectedItem()).getIdTaxation());
		tax.setIdTaxation(tax2.getIdTaxation());
		TSR.update(tax);
		clear();
		refresh();
		
		try(FileWriter fw = new FileWriter(DashboardController.dest, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println("The Tax "+tax.getName()+"was edited at"+LocalDate.now());
			} catch (IOException e) {
			}

	}
	
	@FXML
	public void taxdelete(ActionEvent event) {
		Taxation tax = TSR.find(((Taxation) tbtax.getSelectionModel().getSelectedItem()).getIdTaxation());
		TSR.delete(tax);
		clear();
		
		try(FileWriter fw = new FileWriter(DashboardController.dest, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println("The Tax "+tax.getName()+"was deleted at"+LocalDate.now());
			} catch (IOException e) {
			}
	}
	
	public void clear() {
		nametf.setText("");
		statecb.getSelectionModel().clearSelection();
		refresh();
	}

	public void taxload() {
		col1.setCellValueFactory(new PropertyValueFactory<>("name"));
		col2.setCellValueFactory(new PropertyValueFactory<>("state"));
		col4.setCellValueFactory(new PropertyValueFactory<>("vat_value"));
		tbtax.setItems(null);
		ObservableList<Taxation> items = FXCollections.observableArrayList(TSR.findAll());
		tbtax.setItems(items);
	}

	public void refresh() {
		ObservableList<Taxation> items = FXCollections.observableArrayList(TSR.findAll());

		tbtax.setItems(items);

	}
	
	@FXML
	public void fill() {
		Taxation s = TSR.find(((Taxation) tbtax.getSelectionModel().getSelectedItem()).getIdTaxation());

		nametf.setText(s.getName());
		statecb.getSelectionModel().select(s.getState());
		arsl.setValue((double) s.getVat_value());
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
