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
import javafx.scene.control.ButtonType;
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
import tn.esprit.b3.esprit1718b3erp.conatactmangment.PurchaseOrdersserviceRemote;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.entities.PurchaseOrder;
import tn.esprit.b3.esprit1718b3erp.loginservices.LoginServiceRemote;
import tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesRemote;
import tn.esprit.b3.esprit1718b3erp.services.UserServiceRemote;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;




public class AdjustmentAlertController implements Initializable {
	
	@FXML
	private TableView<Product> tableProduct;

	@FXML
	private TableColumn<Product, String> col1;

	@FXML
	private TableColumn<Product, String> col2;

	@FXML
	private TableColumn<Product, String> col5;
	
	@FXML
	private TableColumn<Product, String> col6;
	
	@FXML
	private TableColumn<Product, String> col7;
	
	@FXML
	private JFXTextField search;

	@FXML
	private Label lblError;
	
	@FXML
    private JFXButton btadd;
	
	@FXML
	private JFXTextField tfref;

	@FXML
	private JFXTextField tfname;

	@FXML
	private JFXTextField tfqtestock;

	@FXML
	private JFXDatePicker dateP;

	@FXML
	private JFXTextField tfqttoP;

	@FXML
	private JFXButton btplus;

	@FXML
	private JFXButton btmoins;

	
	@FXML
    private AnchorPane dashpane;
    static Boolean dash=false;
    
    String jndiNameP = "esprit1718b3erp-ear/esprit1718b3erp-service/ProductServices!tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesRemote";
	ServiceLocator s = ServiceLocator.getInstance();
	ProductServicesRemote productServiceRemote = (ProductServicesRemote) s.getProxy(jndiNameP);

	String jndiNamePO = "esprit1718b3erp-ear/esprit1718b3erp-service/PurchaseOrdersservice!tn.esprit.b3.esprit1718b3erp.conatactmangment.PurchaseOrdersserviceRemote";
	PurchaseOrdersserviceRemote ordersserviceRemote = (PurchaseOrdersserviceRemote) s.getProxy(jndiNamePO);

	
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
    	 lblError.setVisible(false);
    	 act();
    	 SetSellValueFromTableViewToTextEdit();
    	 btadd.setDisable(true);
    	
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
		col1.setCellValueFactory(new PropertyValueFactory<Product, String>("ref"));
		col2.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		col5.setCellValueFactory(new PropertyValueFactory<Product, String>("quantity"));
		col6.setCellValueFactory(new PropertyValueFactory<Product, String>("MinQuantity"));
		col7.setCellValueFactory(new PropertyValueFactory<Product, String>("MaxQuantity"));
		List<Product> listP = null;

		listP = productServiceRemote.FindAllAdjustmentAlert();
		System.out.println("List of Product  alert  Act");

		ObservableList<Product> items = FXCollections.observableArrayList(listP);

		tableProduct.setItems(items);
	}
	
	@FXML
	public void searchProduct(KeyEvent event) {
		ObservableList table = tableProduct.getItems();
		search.textProperty()
				.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
					if (oldValue != null && (newValue.length() < oldValue.length())) {
						tableProduct.setItems(table);
					}
					String value = newValue.toLowerCase();
					ObservableList<Product> subentries = FXCollections.observableArrayList();

					long count = tableProduct.getColumns().stream().count();
					for (int i = 0; i < tableProduct.getItems().size(); i++) {
						for (int j = 0; j < count; j++) {
							String entry = "" + tableProduct.getColumns().get(j).getCellData(i);
							if (entry.toLowerCase().contains(value)) {
								subentries.add(tableProduct.getItems().get(i));
								break;
							}
						}
					}
					tableProduct.setItems(subentries);
				});

	}
	
	@FXML
	void Refresh(ActionEvent event) {
		search.setText("");
		act();
	}
	void notification(String str1, String str2) {
		Notifications notificationbuilder = Notifications.create().title(str1).text(str2).hideAfter(Duration.seconds(5))
				.position(Pos.TOP_LEFT);

		notificationbuilder.showConfirm();
	}
	
	
	
	
	public void SetSellValueFromTableViewToTextEdit() {
    	tableProduct.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				Product p =  tableProduct.getSelectionModel().getSelectedItem() ;
				
				tfname.setText(p.getName());
				tfref.setText(p.getRef());
				tfqtestock.setText(p.getQuantity()+"");
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				dateP.setValue(LocalDate.now());
				int qtP = p.getMaxQuantity()-p.getQuantity();
				tfqttoP.setText(qtP+"");
				btadd.setDisable(false);
			}
			
		});
	}
	
	
	public boolean Verif() {
		boolean v = true;
		
		if (tfqttoP.getText().length() == 0) {
			v = false;
		} 
		
		
		
		
		
		if ( v==false ) {lblError.setVisible(true);}
		else {lblError.setVisible(false);}
		return v;
	}
	
	
	void Clear(){
		tfname.setText("");
		tfref.setText("");
		tfqtestock.setText("");
		tfqttoP.setText("");
		dateP.getEditor().clear();
		btadd.setDisable(true);
	}
	@FXML
	void PurchaseOrder(ActionEvent event) throws IOException{
		
		if (Verif() == true) {
			
			int qtP = Integer.parseInt(tfqttoP.getText());
			Product p =  tableProduct.getSelectionModel().getSelectedItem() ;
			
			int qtstk = p.getQuantity();
			int qtmin = p.getMinQuantity();
			int qtmax = p.getMaxQuantity();
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation ");
			//maxi le stock
			if (qtP == (qtmax-qtstk)) {
				alert.setHeaderText(" Would You Like to Confirme Purchase Order !! Quantity to Purchase is sufficient to Maximise Stock  ");
				
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					Date dtP = java.sql.Date.valueOf(dateP.getValue());
					PurchaseOrder po = new PurchaseOrder(dtP,"Waiting","Out of Stock",qtP,p);
					ordersserviceRemote.save(po);
					
					
					notification("Operation Succeeded !! ", " Purchase Order SENDED with Succes  ");
					Clear();
					to(event, "Product.fxml", "Product");
					System.out.println("ADDED with Succes");
				}
			}
			
			// qt bin el min wel max 
			else if ( (qtP < (qtmax-qtstk)) && (qtP > (qtmin-qtstk)) ) {
				
				alert.setHeaderText("Would You Like to Confirme Purchase Order !! Quantity to Purchase allows to avoid the Repture in Stock ");
				
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					Date dtP = java.sql.Date.valueOf(dateP.getValue());
					PurchaseOrder po = new PurchaseOrder(dtP,"Waiting","Out of Stock",qtP,p);
					ordersserviceRemote.save(po);
					
					
					notification("Operation Succeeded !! ", "   Purchase Order SENDED with Succes   ");
					Clear();
					to(event, "Product.fxml", "Product");
					System.out.println("ADDED with Succes");
				}
			}
			
			//matouselech ll min > mana3mlou chay
			else if ( qtP <= (qtmin-qtstk)){
				Alert alert1 = new Alert(AlertType.ERROR);
				alert1.setTitle("Error ");
				alert1.setHeaderText(" Quantity to Purchase is not sufficient !! It does not prevent the Rupture in Stock");
			//	Clear();
				alert1.showAndWait();
			}
			
			else if ( qtP > (qtmax-qtstk)){
				Alert alert2 = new Alert(AlertType.ERROR);
				alert2.setTitle("Error ");
				alert2.setHeaderText(" Quantity to Purchase is superior to quantity max !!");
			//	Clear();
				alert2.showAndWait();
			}
			
				

				//Product p =  tableProduct.getSelectionModel().getSelectedItem() ;
				
			
		}
	}
	
	public boolean estUnEntier(String chaine) {
		try {
			Integer.parseInt(chaine);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}
	
	@FXML
	void plus(ActionEvent event) {
		String nbr = tfqttoP.getText();
		if (estUnEntier(nbr)) {
			tfqttoP.setText(Integer.parseInt(nbr) + 1 + "");
		} else {
			tfqttoP.setText(0 + "");
		}
	}
	
	@FXML
	void moins(ActionEvent event) {
		String nbr = tfqttoP.getText();
		if (estUnEntier(nbr)) {
			if (Integer.parseInt(nbr) == 0) {
			} else {
				tfqttoP.setText(Integer.parseInt(nbr) - 1 + "");
			}
		} else {
			tfqttoP.setText(0 + "");
		}

	}
		
}

