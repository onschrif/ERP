package tn.esprit.b3.esprit1718b3erp.app.client.scm_purchase;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import tn.esprit.b3.esprit1718b3erp.app.client.login.Login;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.ContactMangmentRemote2;
import tn.esprit.b3.esprit1718b3erp.entities.Suppliers;

import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;




public class SuppliersListController implements Initializable {
	
	@FXML
	private TableView<Suppliers> tableSupplier;

	@FXML
	private TableColumn<Suppliers, String> col1;

	@FXML
	private TableColumn<Suppliers, String> col2;

	@FXML
	private TableColumn<Suppliers, String> col3;

	@FXML
	private TableColumn<Suppliers, String> col4;

	@FXML
	JFXTextField search;
	
    @FXML 
    private AnchorPane dashpane;
    static Boolean dash=false;
    
    
    String jndiNameS = "esprit1718b3erp-ear/esprit1718b3erp-service/ContactMangmentService2!tn.esprit.b3.esprit1718b3erp.conatactmangment.ContactMangmentRemote2";
	ServiceLocator s = ServiceLocator.getInstance();
	ContactMangmentRemote2 contactMangmentRemote2 = (ContactMangmentRemote2) s.getProxy(jndiNameS);

	
    @FXML
    public void dsh(ActionEvent event)
    {
        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), dashpane);
        if(dash)
        {
        translateTransition1.setByX(-250);
        dash=false;
        }
        else
        {translateTransition1.setByX(+250);
        dash=true;
        }
        translateTransition1.play();    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	act() ;
    	dash=false;
    }
   
	
	@FXML
	public void close(ActionEvent event)
	{
		Platform.exit();
	}
	
	@FXML
	public void minimize(ActionEvent event)
	{
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
		to(event, "PurchaseDashboard.fxml", "Home");
	}

	@FXML
	public void toProduct(ActionEvent event) throws IOException {
		to(event, "Product.fxml", "Product");

	}

	@FXML
	public void toBillofMaterial(ActionEvent event) throws IOException {
		to(event, "BillofMaterial.fxml", "Bill of Material");
	}
	

	@FXML
	public void logout(ActionEvent event) throws IOException {
		to(event, "../login/Login.fxml", "Login");
	}
	
	@FXML
	public void toPurchaseOrder(ActionEvent event) throws IOException {
		to(event, "PurchaseOrder.fxml", "Purchase Order");
	}
	
	@FXML
	public void toOrder(ActionEvent event) throws IOException {
		to(event, "OrdersList.fxml", "Orders");
	}
	
	@FXML
	public void toLocation(ActionEvent event) throws IOException {
		to(event, "../scm_inventory/Location.fxml", " Inventory Location");

	}

	@FXML
	public void toMovement(ActionEvent event) throws IOException {
		to(event, "../scm_inventory/Movement.fxml", "Inventory Movement");
	}
	
	@FXML
	public void toUpdateInventoy(ActionEvent event) throws IOException {
		to(event, "../scm_inventory/UpdateInventory.fxml", "Update Inventoy");
	}
	
	
	@FXML
	public void toAdjustment(ActionEvent event) throws IOException {
		to(event, "../scm_inventory/Adjustment.fxml", "Adjustment Inventoy");
	}
	
	
	@FXML
	public void toSuppliers(ActionEvent event) throws IOException {
		to(event, "SuppliersList.fxml", "Suppliers");
	}
	
	
	public void act() {
		col1.setCellValueFactory(new PropertyValueFactory<Suppliers, String>("name"));
		col2.setCellValueFactory(new PropertyValueFactory<Suppliers, String>("adress"));
		col3.setCellValueFactory(new PropertyValueFactory<Suppliers, String>("phone"));
		col4.setCellValueFactory(new PropertyValueFactory<Suppliers, String>("email"));

		List<Suppliers> listS = null;

		listS = contactMangmentRemote2.findAll();
		System.out.println("List of Supplier Act");

		ObservableList<Suppliers> items = FXCollections.observableArrayList(listS);

		tableSupplier.setItems(items);
	}
	@FXML
	void Refresh(ActionEvent event) {
		search.setText("");
		act();

	}
	@FXML
	public void searchSupplier(KeyEvent event) {
		ObservableList table = tableSupplier.getItems();
		search.textProperty()
				.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
					if (oldValue != null && (newValue.length() < oldValue.length())) {
						tableSupplier.setItems(table);
					}
					String value = newValue.toLowerCase();
					ObservableList<Suppliers> subentries = FXCollections.observableArrayList();

					long count = tableSupplier.getColumns().stream().count();
					for (int i = 0; i < tableSupplier.getItems().size(); i++) {
						for (int j = 0; j < count; j++) {
							String entry = "" + tableSupplier.getColumns().get(j).getCellData(i);
							if (entry.toLowerCase().contains(value)) {
								subentries.add(tableSupplier.getItems().get(i));
								break;
							}
						}
					}
					tableSupplier.setItems(subentries);
				});

	}

	

}
