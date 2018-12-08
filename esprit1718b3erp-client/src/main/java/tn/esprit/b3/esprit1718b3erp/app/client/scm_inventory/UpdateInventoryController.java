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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.Duration;
import tn.esprit.b3.esprit1718b3erp.app.client.login.Login;
import tn.esprit.b3.esprit1718b3erp.entities.InventoryLocation;
import tn.esprit.b3.esprit1718b3erp.entities.InventoryMouvement;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.scm_InventoryServices.InventoryLocationServicesRemote;
import tn.esprit.b3.esprit1718b3erp.scm_InventoryServices.InventoryMovementServicesRemote;
import tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesRemote;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;




public class UpdateInventoryController implements Initializable {
	
	@FXML
	private TableView<Product> tableProductStock;

	@FXML
	private TableColumn<Product, String> col1;

	@FXML
	private TableColumn<Product, String> col2;

	@FXML
	private TableColumn<Product, String> col3;

	@FXML
	private TableColumn<Product, String> col4;

	@FXML
	private TableColumn<Product, String> col5;
	
	@FXML
	private TableColumn<Product, String> col6;
	
	
	@FXML
	private	JFXTextField search;

	@FXML
	private JFXComboBox<String> cbPdt;
	
	@FXML
	private JFXComboBox<String> cbLocation;
	
	@FXML
	private JFXTextField tfnbr;

	@FXML
	private JFXTextField tfnbr1;

	@FXML
	private JFXButton btplus;

	@FXML
	private JFXButton btmoins;

	@FXML
	private JFXButton btedit;
	
	@FXML
	private JFXButton btplus1;
	
	@FXML
	private JFXButton btmoins1;
	
	
	@FXML
	private AnchorPane dashpane;

	@FXML
	private Label lblError;
	
	@FXML
	private JFXTextField tfref ;

	@FXML
	private Label lblErroredit;
	
	static Boolean dash = false;

	String jndiNameP = "esprit1718b3erp-ear/esprit1718b3erp-service/ProductServices!tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesRemote";
	ServiceLocator s = ServiceLocator.getInstance();
	ProductServicesRemote productServiceRemote = (ProductServicesRemote) s.getProxy(jndiNameP);

	 String jndiNameL = "esprit1718b3erp-ear/esprit1718b3erp-service/InventoryLocationServices!tn.esprit.b3.esprit1718b3erp.scm_InventoryServices.InventoryLocationServicesRemote";
	 InventoryLocationServicesRemote inventoryLocationServicesRemote = (InventoryLocationServicesRemote) s.getProxy(jndiNameL);

	 String jndiNameM = "esprit1718b3erp-ear/esprit1718b3erp-service/InventoryMovementServices!tn.esprit.b3.esprit1718b3erp.scm_InventoryServices.InventoryMovementServicesRemote";
	 InventoryMovementServicesRemote inventoryMovementServicesRemote = (InventoryMovementServicesRemote) s.getProxy(jndiNameM);

	
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
    	
    	btedit.setDisable(true);
    	btmoins1.setDisable(true);
    	btplus1.setDisable(true);
    	SetSellValueFromTableViewToTextEdit();
		lblError.setVisible(false);
		lblErroredit.setVisible(false);
		tfref.setDisable(true);
    	//init Combo box : product not in stock
		intiConboBoxStock();
    	
    	//init Combo box : Location enable
    	List<InventoryLocation> ListIL = new ArrayList<>();
    	ListIL = inventoryLocationServicesRemote.FindAllLocationEnable();
    	for (InventoryLocation il : ListIL) {
    		cbLocation.getItems().addAll(il.getLocationName());
		}
    	
    	act();  	
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
		col3.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
		col4.setCellValueFactory(new PropertyValueFactory<Product, String>("nature"));
		col5.setCellValueFactory(new PropertyValueFactory<Product, String>("quantity"));
		col6.setCellValueFactory(new Callback<CellDataFeatures<Product,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<Product, String> param) {
                return new SimpleStringProperty(param.getValue().getInventoryLocation().getLocationName());
            }
        });
		List<Product> listP = null;

		listP = productServiceRemote.FindAllStcok();
		System.out.println("List of Product Act");

		ObservableList<Product> items = FXCollections.observableArrayList(listP);

		tableProductStock.setItems(items);
	}

	@FXML
	void Refresh(ActionEvent event) {
		search.setText("");
		act();
	}
	
	@FXML
	public void searchProduct(KeyEvent event) {
		ObservableList table = tableProductStock.getItems();
		search.textProperty()
				.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
					if (oldValue != null && (newValue.length() < oldValue.length())) {
						tableProductStock.setItems(table);
					}
					String value = newValue.toLowerCase();
					ObservableList<Product> subentries = FXCollections.observableArrayList();

					long count = tableProductStock.getColumns().stream().count();
					for (int i = 0; i < tableProductStock.getItems().size(); i++) {
						for (int j = 0; j < count; j++) {
							String entry = "" + tableProductStock.getColumns().get(j).getCellData(i);
							if (entry.toLowerCase().contains(value)) {
								subentries.add(tableProductStock.getItems().get(i));
								break;
							}
						}
					}
					tableProductStock.setItems(subentries);
				});

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
	void moins(ActionEvent event) {
		String nbr = tfnbr.getText();
		if (estUnEntier(nbr)) {
			if (Integer.parseInt(nbr) == 0) {
			} else {
				tfnbr.setText(Integer.parseInt(nbr) - 1 + "");
			}
		} else {
			tfnbr.setText(0 + "");
		}

	}

	@FXML
	void plus(ActionEvent event) {
		String nbr = tfnbr.getText();
		if (estUnEntier(nbr)) {
			tfnbr.setText(Integer.parseInt(nbr) + 1 + "");
		} else {
			tfnbr.setText(0 + "");
		}
	}
	
	@FXML
	void plus1(ActionEvent event) {
		String nbr = tfnbr1.getText();
		if (estUnEntier(nbr)) {
			tfnbr1.setText(Integer.parseInt(nbr) + 1 + "");
		} else {
			tfnbr1.setText(0 + "");
		}
	}

	void notification(String str1, String str2) {
		Notifications notificationbuilder = Notifications.create().title(str1).text(str2).hideAfter(Duration.seconds(5))
				.position(Pos.TOP_LEFT);

		notificationbuilder.showConfirm();
	}
	@FXML
	void moins1(ActionEvent event) {
		String nbr = tfnbr1.getText();
		if (estUnEntier(nbr)) {
			if (Integer.parseInt(nbr) == 0) {
			} else {
				tfnbr1.setText(Integer.parseInt(nbr) - 1 + "");
			}
		} else {
			tfnbr1.setText(0 + "");
		}

	}

	void ClearAdd() {

		tfnbr.setText("0");
		cbPdt.getSelectionModel().select(-1);
		cbLocation.getSelectionModel().select(-1);
	}

	public boolean VerifAdd() {
		boolean v = true ;

		// product
		if (cbPdt.getSelectionModel().isEmpty()==true) {
			v = false;
		} 
		
		// Location
		if (cbLocation.getSelectionModel().isEmpty() ==true) {
			v = false;
		} 

		//qté
		if (  (tfnbr.getText().equals("0")) || (!estUnEntier(tfnbr.getText()) ) ) {
			v = false;
		} 
		
		if ( v==false ) {lblError.setVisible(true);}
		else {lblError.setVisible(false);}
		return v;
	}

	    void intiConboBoxStock(){
	    	
	    	cbPdt.getSelectionModel().clearSelection();
	    	cbPdt.getItems().clear();
	    	List<Product> ListProduct = new ArrayList<>();
	    	ListProduct = productServiceRemote.FindAllNotStcok();
	    	for (Product p : ListProduct) {
	    		cbPdt.getItems().addAll(p.getRef()+"");
			}
	    }
	    @FXML
	    void addStock(ActionEvent event) {
			
	    	//System.out.print("verif " +VerifAdd());
	    	if (VerifAdd() == true) {
	    		
				String refProductSelect = cbPdt.getSelectionModel().getSelectedItem();
				String nameLocationSelect = cbLocation.getSelectionModel().getSelectedItem();
				int qt = Integer.parseInt(tfnbr.getText());
				InventoryLocation inventoryLocation = inventoryLocationServicesRemote.FindLocationByName(nameLocationSelect);
				
				Product p =productServiceRemote.FindProductByRef(refProductSelect);
				p.setQuantity(qt);
				p.setInventoryLocation(inventoryLocation);
				
		    	productServiceRemote.update(p);
		    	//mvt
		    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
		    	InventoryMouvement mvt1 = new InventoryMouvement("Add New",date,qt,p);
		    	inventoryMovementServicesRemote.save(mvt1);
		    	//
				notification("Operation Succeeded !! ", "   ADDED with Succes  ");
				ClearAdd();			
		    	intiConboBoxStock();
				act();
				System.out.println("ADDED with Succes");
		}
	    }

	    public void SetSellValueFromTableViewToTextEdit() {
	    	tableProductStock.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {

					Product p =  tableProductStock.getSelectionModel().getSelectedItem() ;
					tfref.setText(p.getRef());
					tfnbr1.setText(p.getQuantity()+"");
					btedit.setDisable(false);
					btmoins1.setDisable(false);
					btplus1.setDisable(false);
				}
				
			});
		}
	    
	    public boolean Verifedit() {
			boolean v = true ;
			//qté
			if (  (tfnbr1.getText().equals("0")) || (!estUnEntier(tfnbr1.getText()) ) ) {
				v = false;
			} 
			
			if ( v==false ) {lblError.setVisible(true);}
			else {lblError.setVisible(false);}
			return v;
		}
	    void ClearEdit(){
	    	tfref.setText("");
			tfnbr1.setText("");
			btedit.setDisable(true);
			btplus1.setDisable(true);
			btmoins1.setDisable(true);
			
		}
	   
	    
	    @FXML
	    void editStock(ActionEvent event) {
	    	if (Verifedit() == true) {
	    	
	    	//Product p =  tableProductStock.getSelectionModel().getSelectedItem() ;
	    	Product p =productServiceRemote.FindProductByRef(tfref.getText());
	    	int qt = p.getQuantity();
	    	p.setQuantity(Integer.parseInt(tfnbr1.getText()));
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation ");
			int qtmvtinc = Integer.parseInt(tfnbr1.getText()) - qt ;
			int qtmvtdec = qt -Integer.parseInt(tfnbr1.getText())  ;
	    	//qte ++
	    	if (qt < Integer.parseInt(tfnbr1.getText())){
	    		
				alert.setHeaderText(" Would You Like to INCREMENT inventory ?? ");
				
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					productServiceRemote.update(p);
					//mvt
			    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
			    	InventoryMouvement mvt1 = new InventoryMouvement("update increment",date,qtmvtinc,p);
			    	inventoryMovementServicesRemote.save(mvt1);
			    	//
			    	notification("Operation Succeeded !! ", "   UPDETED with Succes  ");
			    	ClearEdit();
			    	act();
				}

	    	}
	    	//qté--
	    	else if  (qt > Integer.parseInt(tfnbr1.getText())){
	    		
	    		alert.setHeaderText(" Would You Like to DECREASE inventory ?? ");
	    	
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					productServiceRemote.update(p);
					//mvt
			    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
			    	InventoryMouvement mvt1 = new InventoryMouvement("update decrease",date,qtmvtdec,p);
			    	inventoryMovementServicesRemote.save(mvt1);
			    	//
			    	notification("Operation Succeeded !! ", "   UPDETED with Succes  ");
			    	ClearEdit();
			    	act();
				}
				
	    	}
	    	//qté +-
	    	else {
	    		Alert alert1 = new Alert(AlertType.ERROR);
				alert1.setTitle("Error ");
				alert1.setHeaderText(" Quantity is not changed  !!");
				ClearEdit();
				alert1.showAndWait();
	    		
	    	}
	    	
	    	}
	    }




}
