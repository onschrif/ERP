package tn.esprit.b3.esprit1718b3erp.app.client.scm_inventory;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.Duration;
import tn.esprit.b3.esprit1718b3erp.app.client.login.Login;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.PurchaseOrdersservice;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.PurchaseOrdersserviceRemote;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.entities.PurchaseOrder;
import tn.esprit.b3.esprit1718b3erp.entities.Session;
import tn.esprit.b3.esprit1718b3erp.loginservices.LoginServiceRemote;
import tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesRemote;
import tn.esprit.b3.esprit1718b3erp.services.UserServiceRemote;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;


import com.jfoenix.controls.JFXTextField;

public class AdjustmentTreatController implements Initializable {

	@FXML
	private BarChart barchart;

	@FXML
	private JFXTextField tfref;

	@FXML
	private JFXTextField tfname;
	
	@FXML
    private TableView<PurchaseOrder> tablePurchase;

    @FXML
    private TableColumn<PurchaseOrder, String> col1;

    @FXML
    private TableColumn<PurchaseOrder, String> col2;

    @FXML
    private TableColumn<PurchaseOrder, String> col3;

    @FXML
    private TableColumn<PurchaseOrder, String> col4;

	@FXML
	private AnchorPane dashpane;
	static Boolean dash = false;

	String jndiNameP = "esprit1718b3erp-ear/esprit1718b3erp-service/ProductServices!tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesRemote";
	ServiceLocator s = ServiceLocator.getInstance();
	ProductServicesRemote productServiceRemote = (ProductServicesRemote) s.getProxy(jndiNameP);

	String jndiNamePO = "esprit1718b3erp-ear/esprit1718b3erp-service/PurchaseOrdersservice!tn.esprit.b3.esprit1718b3erp.conatactmangment.PurchaseOrdersserviceRemote";
	PurchaseOrdersserviceRemote ordersserviceRemote = (PurchaseOrdersserviceRemote)s.getProxy(jndiNamePO);
	
	Product p = productServiceRemote.find(Session.getId_prd());

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

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		dash = false;

		// BarChart
		XYChart.Series series1 = new XYChart.Series();
		// series1.setName(" Min ");
		series1.getData().add(new XYChart.Data(" Min ", p.getMinQuantity()));

		// series1.setName(" in Stock ");
		series1.getData().add(new XYChart.Data(" in Stock ", p.getQuantity()));

		// series1.setName(" Max ");
		series1.getData().add(new XYChart.Data(" Max ", p.getMaxQuantity()));

		barchart.getData().add(series1);
		
		
		
		tfname.setText(p.getName());
		tfref.setText(p.getRef());
		//tfmth.setText(p.getMethodResupply());
		
		act();
		
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

	public void to(ActionEvent event, String str, String title) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource(str));
		Scene scene = new Scene(root);
		scene.getStylesheets().addAll(Login.class.getResource("style.css").toExternalForm());
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(scene);
		app_stage.setTitle(title);
		app_stage.show();
	}

	@FXML
	public void home(ActionEvent event) throws IOException {
		to(event, "../scm_purchase/PurchaseDashboard.fxml", "Home");
	}

	@FXML
	public void toProduct(ActionEvent event) throws IOException {
		to(event, "../scm_purchase/Product.fxml", "Product");

	}

	@FXML
	public void toBillofMaterial(ActionEvent event) throws IOException {
		to(event, "../scm_purchase/BillofMaterial.fxml", "Bill of Material");
	}

	@FXML
	public void toSuppliers(ActionEvent event) throws IOException {
		to(event, "../scm_purchase/SuppliersList.fxml", "Suppliers");
	}

	@FXML
	public void logout(ActionEvent event) throws IOException {
		to(event, "../login/Login.fxml", "Login");
	}

	@FXML
	public void toOrder(ActionEvent event) throws IOException {
		to(event, "../scm_purchase/OrdersList.fxml", "Orders");
	}

	@FXML
	public void toLocation(ActionEvent event) throws IOException {
		to(event, "Location.fxml", " Inventory Location");

	}
	@FXML
	public void toPurchaseOrder(ActionEvent event) throws IOException {
		to(event, "../scm_purchase/PurchaseOrder.fxml", "Purchase Order");
	}
	

	@FXML
	public void toMovement(ActionEvent event) throws IOException {
		to(event, "Movement.fxml", "Inventory Movement");
	}

	@FXML
	public void toUpdateInventoy(ActionEvent event) throws IOException {
		to(event, "UpdateInventory.fxml", "Update Inventoy");
	}

	@FXML
	public void toAdjustment(ActionEvent event) throws IOException {
		to(event, "Adjustment.fxml", "Adjustment Inventoy");
	}
	
public void act() {

	
		
		col1.setCellValueFactory(new PropertyValueFactory<>("date"));
		col2.setCellValueFactory(new PropertyValueFactory<>("type"));
		col3.setCellValueFactory(new PropertyValueFactory<>("status"));
		col4.setCellValueFactory(new PropertyValueFactory<>("QuantityToPurchase"));
		
		List<PurchaseOrder> listO = new ArrayList<PurchaseOrder>();

		//listO = ordersserviceRemote.findAll();
		
		listO = ordersserviceRemote.FindAllByProduct(p.getIdProduct());
		System.out.println("List of PO Act");

		ObservableList<PurchaseOrder> items = FXCollections.observableArrayList(listO);

		tablePurchase.setItems(items);
		
	
	}
	
	

}
