package tn.esprit.b3.esprit1718b3erp.app.client.scm_inventory;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import tn.esprit.b3.esprit1718b3erp.app.client.login.Login;
import tn.esprit.b3.esprit1718b3erp.entities.InventoryLocation;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.scm_InventoryServices.InventoryLocationServicesRemote;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;




public class LocationController implements Initializable {
	
	@FXML
	private TableView<InventoryLocation> tableLocation;

	@FXML
	private TableColumn<InventoryLocation, String> col1;

	@FXML
	private TableColumn<InventoryLocation, String> col2;

	@FXML
	private TableColumn<InventoryLocation, String> col3;
	
	@FXML
	JFXTextField search;
	
	@FXML
    private JFXTextField atfname;

    @FXML
    private JFXTextField atfadr;
    
    @FXML
    private JFXTextField etfname;

    @FXML
    private JFXTextField etfadr;

    @FXML
    private JFXToggleButton etbstate;
  
    @FXML
    private JFXButton btedit;
    
    @FXML
	private Label lblError;

    @FXML
   	private Label lblErroredit;
    
    @FXML 
    private AnchorPane dashpane;
    static Boolean dash=false;
    
    
    String jndiNameL = "esprit1718b3erp-ear/esprit1718b3erp-service/InventoryLocationServices!tn.esprit.b3.esprit1718b3erp.scm_InventoryServices.InventoryLocationServicesRemote";
	ServiceLocator s = ServiceLocator.getInstance();
	InventoryLocationServicesRemote inventoryLocationServicesRemote = (InventoryLocationServicesRemote) s.getProxy(jndiNameL);

    
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
    	 
    	lblError.setVisible(false);
    	lblErroredit.setVisible(false);
    	btedit.setDisable(true);
    	act();
    	SetSellValueFromTableViewToTextEdit();
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
		col1.setCellValueFactory(new PropertyValueFactory<InventoryLocation, String>("locationName"));
		col2.setCellValueFactory(new PropertyValueFactory<InventoryLocation, String>("adress"));
		col3.setCellValueFactory(new PropertyValueFactory<InventoryLocation, String>("state"));

		List<InventoryLocation> listL = null;

		listL = inventoryLocationServicesRemote.findAll();
		System.out.println("List of Location Act" +listL.size());

		ObservableList<InventoryLocation> items = FXCollections.observableArrayList(listL);

		tableLocation.setItems(items);
	}

	
	@FXML
	public void searchLocation(KeyEvent event) {
		ObservableList table = tableLocation.getItems();
		search.textProperty()
				.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
					if (oldValue != null && (newValue.length() < oldValue.length())) {
						tableLocation.setItems(table);
					}
					String value = newValue.toLowerCase();
					ObservableList<InventoryLocation> subentries = FXCollections.observableArrayList();

					long count = tableLocation.getColumns().stream().count();
					for (int i = 0; i < tableLocation.getItems().size(); i++) {
						for (int j = 0; j < count; j++) {
							String entry = "" + tableLocation.getColumns().get(j).getCellData(i);
							if (entry.toLowerCase().contains(value)) {
								subentries.add(tableLocation.getItems().get(i));
								break;
							}
						}
					}
					tableLocation.setItems(subentries);
				});

	}
	@FXML
	
public void Refresh(ActionEvent event)  {
		search.setText("");
		act();
		ClearEdit();
		ClearAdd();

	}


	
	public boolean VerifAdd() {
		boolean v = true;
		//name
		if (atfname.getText().length() == 0) {
			v = false;
		} 
		//adress
		if (atfadr.getText().length() == 0) {
			v = false;
		}
		if ( v==false ) {lblError.setVisible(true);}
		else {lblError.setVisible(false);}
		return v;
	}

	void notification(String str1, String str2) {
		Notifications notificationbuilder = Notifications.create()
				.title(str1)
				.text(str2).hideAfter(Duration.seconds(5))
				.position(Pos.TOP_LEFT);
		
		notificationbuilder.showConfirm();
	}
	
	void ClearAdd() {
		atfadr.setText("");
		atfname.setText("");
	}
	
	@FXML
	void addLocation(ActionEvent event) {
		if (VerifAdd() == true) {
			InventoryLocation il = new InventoryLocation(atfname.getText(),atfadr.getText(),"enable");
			inventoryLocationServicesRemote.save(il);
			notification("Operation Succeeded !! ", "   ADDED with Succes  ");
			ClearAdd();
			act();
			System.out.println("ADDED with Succes");
	}
	}

	public void SetSellValueFromTableViewToTextEdit() {
		tableLocation.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				InventoryLocation il =  tableLocation.getSelectionModel().getSelectedItem() ;
				etfadr.setText(il.getAdress());
				etfname.setText(il.getLocationName());
				if (il.getState().equals("disabled")){etbstate.setSelected(true);}
				else {etbstate.setSelected(false);}
				btedit.setDisable(false);
			}
			
		});
	}
	
	
	
	public boolean VerifEdit() {
		boolean v = true;
		//name
		if (etfname.getText().length() == 0) {
			v = false;
		}
		
		//adress
		if (etfadr.getText().length() == 0) {
			v = false;
		} 
		
		if ( v==false ) {lblErroredit.setVisible(true);}
		else {lblErroredit.setVisible(false);}
		return v;
	}

	void ClearEdit(){
		etfadr.setText("");
		etfname.setText("");
		etbstate.setSelected(false);
		btedit.setDisable(true);
	}
	@FXML
	void editLocation(ActionEvent event) {
		String state = "enable";
		if(etbstate.isSelected()){state = "disabled";}
		if (VerifEdit() == true) {
			InventoryLocation il = new InventoryLocation(etfname.getText(),etfadr.getText(),state);
			il.setIdInventoryLocation(tableLocation.getSelectionModel().getSelectedItem().getIdInventoryLocation());
			inventoryLocationServicesRemote.update(il);
			notification("Operation Succeeded !! ", "   UPDATED with Succes  ");
			ClearEdit();
			act();
			System.out.println("UPDATED with Succes");
	}
	}

}
