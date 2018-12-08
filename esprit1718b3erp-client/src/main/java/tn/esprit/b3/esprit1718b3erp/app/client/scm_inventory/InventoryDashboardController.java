package tn.esprit.b3.esprit1718b3erp.app.client.scm_inventory;

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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import tn.esprit.b3.esprit1718b3erp.app.client.login.Login;
import tn.esprit.b3.esprit1718b3erp.loginservices.LoginServiceRemote;
import tn.esprit.b3.esprit1718b3erp.services.UserServiceRemote;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;




public class InventoryDashboardController implements Initializable {
	
	
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
	
	@FXML
	public void toPurchaseOrder(ActionEvent event) throws IOException {
		to(event, "../scm_purchase/PurchaseOrder.fxml", "Purchase Order");
	}
}
