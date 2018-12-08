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
import tn.esprit.b3.esprit1718b3erp.entities.Product;
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

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;




public class AdjustmentController implements Initializable {
	
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
	private JFXSlider sliderMin;

	@FXML
	private JFXSlider sliderMax;
	
	@FXML
	private JFXComboBox<String> cbPdt;
		
	@FXML
    private JFXTextField tfPrd;

    @FXML
    private Label alblError1;

    @FXML
    private JFXSlider asliderMin1;

    @FXML
    private JFXSlider asliderMax1;

    @FXML
	private JFXButton btedit;
    
	@FXML
	private Label lblError;

	@FXML
    private AnchorPane dashpane;
    static Boolean dash=false;
    
    String jndiNameP = "esprit1718b3erp-ear/esprit1718b3erp-service/ProductServices!tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesRemote";
	ServiceLocator s = ServiceLocator.getInstance();
	ProductServicesRemote productServiceRemote = (ProductServicesRemote) s.getProxy(jndiNameP);

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
    	 act();
    	 intiConboBoxPrd();
    	 SetSellValueFromTableViewToTextEdit();
//    	 cbMethResupply.getItems().addAll("With Quantity" ,"With Date");
//    	 acbMethResupply1.getItems().addAll("With Quantity" ,"With Date");
    	 lblError.setVisible(false);
    	 alblError1.setVisible(false);
    	 tfPrd.setDisable(true);
    	 btedit.setDisable(true);
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
	public void toPurchaseOrder(ActionEvent event) throws IOException {
		to(event, "../scm_purchase/PurchaseOrder.fxml", "Purchase Order");
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

		listP = productServiceRemote.FindAllAdjustment();
		System.out.println("List of Product  Adj Act");

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
	
	void ClearAdd() {

		sliderMin.setValue(10);
		sliderMax.setValue(50);		
		cbPdt.getSelectionModel().select(-1);
		//cbMethResupply.getSelectionModel().select(-1);
	}
	public boolean VerifAdd() {
		boolean v = true ;

		// product
		if (cbPdt.getSelectionModel().isEmpty()==true) {
			v = false;
		} 
		
		int min , max ;
		min = Math.round((float)sliderMin.getValue()); 
		max = Math.round((float)sliderMax.getValue());
	
		// Qte max
		if (Math.round((float)sliderMax.getValue()) == 0) {
			v = false;
		} 
		// Qte min > max
		if (min > max) 	{
			v = false;
			lblError.setText("                      Quantity min > Quantity max !!");
		} 
			
//		// methode
//		if (cbMethResupply.getSelectionModel().isEmpty() == true) {
//			v = false;
//		}

		if (v == false) {
			lblError.setVisible(true);
		} else {
			lblError.setVisible(false);
		}
		return v;
	}
	@FXML
	void addAdjustment(ActionEvent event) {
		
		if (VerifAdd() == true) {
    		
			String refProductSelect = cbPdt.getSelectionModel().getSelectedItem();
			Product p =productServiceRemote.FindProductByRef(refProductSelect);
			// Integer.parseInt(sliderMin.getValue()+""); 
			int qtMin = Math.round((float)sliderMin.getValue());
			int qtMax = Math.round((float)sliderMax.getValue());
			//String  MethodResupplySelect = cbMethResupply.getSelectionModel().getSelectedItem();
			p.setMinQuantity(qtMin);
			p.setMaxQuantity(qtMax);
			//p.setMethodResupply(MethodResupplySelect);
			
	    	productServiceRemote.update(p);
			
		notification("Operation Succeeded !! ", "   ADDED with Succes  ");
		ClearAdd();			
    	intiConboBoxPrd();
		act();
		System.out.println("ADDED with Succes");
		}
	}

	void intiConboBoxPrd(){
    	
    	cbPdt.getSelectionModel().clearSelection();
    	cbPdt.getItems().clear();
    	List<Product> ListProduct = new ArrayList<>();
    	ListProduct = productServiceRemote.FindAllNotAdjustment();
    	for (Product p : ListProduct) {
    		cbPdt.getItems().addAll(p.getRef()+"");
		}
    }
	
	
	public void SetSellValueFromTableViewToTextEdit() {
    	tableProduct.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				Product p =  tableProduct.getSelectionModel().getSelectedItem() ;
			//	String MethSupp = p.getMethodResupply();
				int cbselect =-1;
//				if (MethSupp.equals("With Quantity")) cbselect = 0;
//				else if (MethSupp.equals("With Date")) cbselect = 1;
				tfPrd.setText(p.getRef());
				asliderMin1.setValue(p.getMinQuantity());
				asliderMax1.setValue(p.getMaxQuantity());
			//	acbMethResupply1.getSelectionModel().select(cbselect);
				btedit.setDisable(false);

			}
			
		});
	}
	
	 public boolean Verifedit() {
			boolean v = true ;
			//qté
			int min , max ;
			min = Math.round((float)asliderMin1.getValue()); 
			max = Math.round((float)asliderMax1.getValue());
		
			// Qte max
			if (Math.round((float)asliderMax1.getValue()) == 0) {
				v = false;
			} 
			// Qte min > max
			if (min > max) 	{
				v = false;
				alblError1.setText("                      Quantity min > Quantity max !!");
			} 
			
			if ( v==false ) {alblError1.setVisible(true);}
			else {alblError1.setVisible(false);}
			return v;
		}
	    void ClearEdit(){
	    	tfPrd.setText("");
	    	asliderMin1.setValue(10);
			asliderMax1.setValue(50);
			btedit.setDisable(true);
		//	acbMethResupply1.getSelectionModel().select(-1);
			
			
		}
	    
	@FXML
	void editAdjustment(ActionEvent event) {
		if (Verifedit() == true) {
			Product p =productServiceRemote.FindProductByRef(tfPrd.getText());
			int qtMin = Math.round((float)asliderMin1.getValue());
			int qtMax = Math.round((float)asliderMax1.getValue());
			//String  MethodResupplySelect = acbMethResupply1.getSelectionModel().getSelectedItem();
			p.setMinQuantity(qtMin);
			p.setMaxQuantity(qtMax);
		//	p.setMethodResupply(MethodResupplySelect);
			
	    	productServiceRemote.update(p);
	    	
	    	notification("Operation Succeeded !! ", "   UPDETED with Succes  ");
	    	ClearEdit();
	    	act();

		}
	    	
	}

    @FXML
    void deleteAdjustment(ActionEvent event) {
    	
    	
    	if (tableProduct.getSelectionModel().getSelectedItem() != null) {
    		Product p =  tableProduct.getSelectionModel().getSelectedItem() ;
    		p.setMinQuantity(0);
    		p.setMaxQuantity(0);
    		//p.setMethodResupply(null);	
        	productServiceRemote.update(p);
       	
        	notification("Operation Succeeded !! ", "   REMOVED with Succes  ");
        	act();
		}

		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("ERROR");
			alert.setHeaderText(" Select the Product that You want to REMOVE Adjustment ! ");
			alert.showAndWait();
		}
    }
	

    @FXML
    void treatAdjustment(ActionEvent event) throws IOException {
		
		if (tableProduct.getSelectionModel().getSelectedItem() != null) {

			int x = tableProduct.getSelectionModel().getSelectedItem().getIdProduct();
			Session.setId_prd(x);
			to(event, "AdjustmentTreat.fxml", "View Adjustment Inventoy");
		
		}

		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("ERROR");
			alert.setHeaderText(" Select the Product that You want to View ! ");
			alert.showAndWait();
		}
	}
	
    @FXML
    void listAlert(ActionEvent event) throws IOException {
    	
    	to(event, "AdjustmentAlert.fxml", "Alert Inventoy");
		
    }
		
		
		
}

