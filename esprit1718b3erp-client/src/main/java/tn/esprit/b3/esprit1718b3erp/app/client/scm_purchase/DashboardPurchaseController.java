package tn.esprit.b3.esprit1718b3erp.app.client.scm_purchase;

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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
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
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.loginservices.LoginServiceRemote;
import tn.esprit.b3.esprit1718b3erp.scm_InventoryServices.InventoryLocationServicesRemote;
import tn.esprit.b3.esprit1718b3erp.scm_InventoryServices.InventoryMovementServicesRemote;
import tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesRemote;
import tn.esprit.b3.esprit1718b3erp.services.UserServiceRemote;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;




public class DashboardPurchaseController implements Initializable {
	
	String jndiNameP = "esprit1718b3erp-ear/esprit1718b3erp-service/ProductServices!tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesRemote";
	ServiceLocator s = ServiceLocator.getInstance();
	ProductServicesRemote productServiceRemote = (ProductServicesRemote) s.getProxy(jndiNameP);

	
	 String jndiNameS = "esprit1718b3erp-ear/esprit1718b3erp-service/ContactMangmentService2!tn.esprit.b3.esprit1718b3erp.conatactmangment.ContactMangmentRemote2";
	 ContactMangmentRemote2 contactMangmentRemote2 = (ContactMangmentRemote2) s.getProxy(jndiNameS);

	 String jndiNameL = "esprit1718b3erp-ear/esprit1718b3erp-service/InventoryLocationServices!tn.esprit.b3.esprit1718b3erp.scm_InventoryServices.InventoryLocationServicesRemote";
	InventoryLocationServicesRemote inventoryLocationServicesRemote = (InventoryLocationServicesRemote) s.getProxy(jndiNameL);

	 String jndiNameM = "esprit1718b3erp-ear/esprit1718b3erp-service/InventoryMovementServices!tn.esprit.b3.esprit1718b3erp.scm_InventoryServices.InventoryMovementServicesRemote";
		InventoryMovementServicesRemote inventoryMovementServicesRemote = (InventoryMovementServicesRemote) s.getProxy(jndiNameM);

	
	@FXML
	private TableView<Product> tableProduct;
	@FXML
	private TableColumn<Product, String> col1;
	@FXML
	private TableColumn<Product, String> col2;
	@FXML
	private TableColumn<Product, String> col3;
	@FXML
	private TableColumn<Product, String> col5;
	@FXML
	private TableColumn<Product, String> col6;
	
    @FXML
    private Label lblnbrsupp;
    
    @FXML
    private Label lblnbrStock;

    @FXML
    private Label lblnbrMvt;

    @FXML
    private Label lblnbrLocation;
   
    @FXML
    private Label lblnbrprd;
    @FXML
    private Label lblnbrpurch;

	
	@FXML
	private PieChart pie;

    @FXML 
    private AnchorPane dashpane;
    static Boolean dash=false;
    
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
    	
    	List<String> State = new ArrayList<String>();
    	State.add("In progress");State.add("validated");State.add("Retired");
    	//cbrfq.getItems().addAll(State);
    	
    	lblnbrsupp.setText(contactMangmentRemote2.findAll().size()+"");
    	lblnbrpurch.setText("22");
    	lblnbrprd.setText(productServiceRemote.findAll().size()+"");
    	
    	lblnbrLocation.setText(inventoryLocationServicesRemote.findAll().size()+"");
    	lblnbrMvt.setText(inventoryMovementServicesRemote.findAll().size()+"");
    	lblnbrStock.setText(productServiceRemote.FindAllStcok().size()+"");
    	
    	
    	// initialisation table view
    	List<Product> listP = null;
		listP = productServiceRemote.findAll();
		col1.setCellValueFactory(new PropertyValueFactory<Product, String>("ref"));
		col2.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		col3.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
		col5.setCellValueFactory(new PropertyValueFactory<Product, String>("nature"));
		col6.setCellValueFactory(new PropertyValueFactory<Product, String>("type"));
		System.out.println("List of Product Home");
		ObservableList<Product> items = FXCollections.observableArrayList(listP);
		tableProduct.setItems(items);
		
      dash=false;
      //product chart
      int nbrFP = productServiceRemote.numberFinishedProduct();
      int nbrFSP = productServiceRemote.numberSemifinishedProduct();
      int nbrRM = productServiceRemote.numberRawMaterial();
      int nbrP = productServiceRemote.numberPackaging();
    	 ObservableList<PieChart.Data> pieChartData =
    	            FXCollections.observableArrayList(
    	            new PieChart.Data("Raw Material ("+nbrRM+")", nbrRM),
    	            new PieChart.Data("Semi-finished ("+nbrFSP+")", nbrFSP),
    	            new PieChart.Data("Finished Product ("+nbrFP+")", nbrFP),
    	 			new PieChart.Data("Packaging ("+nbrP+")", nbrP));
    	 pie.setData(pieChartData);
    	
  
    	      
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
	public void toPurchaseOrder(ActionEvent event) throws IOException {
		to(event, "PurchaseOrder.fxml", "Purchase Order");
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
	public void toSuppliers(ActionEvent event) throws IOException {
		to(event, "SuppliersList.fxml", "Suppliers");
	}
	
	@FXML
	public void logout(ActionEvent event) throws IOException {
		to(event, "../login/Login.fxml", "Login");
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
	
	
}
