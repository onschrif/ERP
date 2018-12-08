package tn.esprit.b3.esprit1718b3erp.app.client.scm_purchase;

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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
import tn.esprit.b3.esprit1718b3erp.entities.InventoryLocation;
import tn.esprit.b3.esprit1718b3erp.entities.InventoryMouvement;
import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.entities.PurchaseOrder;
import tn.esprit.b3.esprit1718b3erp.loginservices.LoginServiceRemote;
import tn.esprit.b3.esprit1718b3erp.orderservices.OrderServiceRemote;
import tn.esprit.b3.esprit1718b3erp.scm_InventoryServices.InventoryMovementServicesRemote;
import tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.Manufacturing1Remote;
import tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesRemote;
import tn.esprit.b3.esprit1718b3erp.services.UserServiceRemote;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;




public class PurchaseOrderController implements Initializable {
	
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
    private TableColumn<PurchaseOrder, String> col5;

    @FXML
    private TableColumn<PurchaseOrder, String> col6;

    @FXML
    private TableColumn<PurchaseOrder, String> col7;

    @FXML
    private JFXTextField search;
	
    @FXML
    private JFXTextField tfref;
    
    @FXML
    private JFXButton btadd;

    @FXML
    private JFXTextField tfname;

    @FXML
    private JFXTextField tfqtestock;

    @FXML
    private JFXTextField tfdate;

    @FXML
    private JFXTextField tftype;

    @FXML
    private JFXTextField tfqtToPO;
   
    @FXML
    private JFXTextField tfstateinti;
    
    @FXML
    private JFXToggleButton etbstate;

   

//    @FXML
//    PRIVATE JFXCOMBOBOX<STRING> CBSTATE;
    
    @FXML 
    private AnchorPane dashpane;
    static Boolean dash=false;
    
    String jndiNameP = "esprit1718b3erp-ear/esprit1718b3erp-service/ProductServices!tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesRemote";
	ServiceLocator s = ServiceLocator.getInstance();
	ProductServicesRemote productServiceRemote = (ProductServicesRemote) s.getProxy(jndiNameP);
    
    
	String jndiNamePO = "esprit1718b3erp-ear/esprit1718b3erp-service/PurchaseOrdersservice!tn.esprit.b3.esprit1718b3erp.conatactmangment.PurchaseOrdersserviceRemote";
	PurchaseOrdersserviceRemote purchaseOrdersserviceRemote = (PurchaseOrdersserviceRemote) s.getProxy(jndiNamePO);

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
    	 dash=false;
    	 act();
    	 SetSellValueFromTableViewToTextEdit();
    	 btadd.setDisable(true);
    	// cbstate.getItems().addAll("Waiting","Canceled","Confirmed");
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
	
	public void act() {

		
		col1.setCellValueFactory(new Callback<CellDataFeatures<PurchaseOrder,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<PurchaseOrder, String> param) {
                return new SimpleStringProperty(param.getValue().getPro().getRef());
            }
        });
		col2.setCellValueFactory(new Callback<CellDataFeatures<PurchaseOrder,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<PurchaseOrder, String> param) {
                return new SimpleStringProperty(param.getValue().getPro().getName());
            }
        });
		col3.setCellValueFactory(new Callback<CellDataFeatures<PurchaseOrder,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<PurchaseOrder, String> param) {
                return new SimpleStringProperty(param.getValue().getPro().getQuantity()+"");
            }
        });

		
		col4.setCellValueFactory(new PropertyValueFactory<>("date"));
		col5.setCellValueFactory(new PropertyValueFactory<>("type"));
		col6.setCellValueFactory(new PropertyValueFactory<>("status"));
		col7.setCellValueFactory(new PropertyValueFactory<>("QuantityToPurchase"));
		
		List<PurchaseOrder> listO = null;

		listO = purchaseOrdersserviceRemote.FindAllPOWaiting();
		System.out.println("List of orders Act");

		ObservableList<PurchaseOrder> items = FXCollections.observableArrayList(listO);

		tablePurchase.setItems(items);
		
	
	}
	
	@FXML
    void searchOrders(KeyEvent event) {
		ObservableList table = tablePurchase.getItems();
		search.textProperty()
				.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
					if (oldValue != null && (newValue.length() < oldValue.length())) {
						tablePurchase.setItems(table);
					}
					String value = newValue.toLowerCase();
					ObservableList<PurchaseOrder> subentries = FXCollections.observableArrayList();

					long count = tablePurchase.getColumns().stream().count();
					for (int i = 0; i < tablePurchase.getItems().size(); i++) {
						for (int j = 0; j < count; j++) {
							String entry = "" + tablePurchase.getColumns().get(j).getCellData(i);
							if (entry.toLowerCase().contains(value)) {
								subentries.add(tablePurchase.getItems().get(i));
								break;
							}
						}
					}
					tablePurchase.setItems(subentries);
				});
    }
	
	@FXML
	public void Refresh(ActionEvent event)  {
		search.setText("");
		act();

	}
	
	void notification(String str1, String str2) {
		Notifications notificationbuilder = Notifications.create()
				.title(str1)
				.text(str2).hideAfter(Duration.seconds(5))
				.position(Pos.TOP_LEFT);
		
		notificationbuilder.showConfirm();
	}
	
	 public void SetSellValueFromTableViewToTextEdit() {
		 tablePurchase.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {

					PurchaseOrder po =  tablePurchase.getSelectionModel().getSelectedItem() ;
					tfname.setText(po.getPro().getName());
					tfref.setText(po.getPro().getRef());
					tfqtestock.setText(po.getPro().getQuantity()+"");
					
					tfdate.setText(po.getDate()+"");
					tfqtToPO.setText(po.getQuantityToPurchase()+"");
					tftype.setText(po.getType());
					
					
					
					tfstateinti.setText(po.getStatus());
					
					btadd.setDisable(false);


				}
				
			});
		}
	    
	 void Clear(){
		 
		   tfname.setText("");
			tfref.setText("");
			tfqtestock.setText("");
			
			tfdate.setText("");
			tfqtToPO.setText("");
			tftype.setText("");
			
			tfstateinti.setText("");
			
			etbstate.setSelected(false);
			
			btadd.setDisable(false);
	 }
	 
	 @FXML
		public void add(ActionEvent event)  {
		 
		 String state = "Confirmed";
			if(etbstate.isSelected()){state = "Canceled";}
			
		 
		 PurchaseOrder po =  tablePurchase.getSelectionModel().getSelectedItem() ;
		 Product prd = po.getPro();
		 Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation ");
			
			if(!etbstate.isSelected()){

				alert.setHeaderText(" Would You Like to CONFIRMED this Purchase order and add Stock ?? ");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					
					//Purchase Order
					po.setStatus("Confirmed");
					purchaseOrdersserviceRemote.update(po);
					
					//Product
					prd.setQuantity(prd.getQuantity()+po.getQuantityToPurchase());
					productServiceRemote.update(prd);
					//mvt
			    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
			    	InventoryMouvement mvt1 = new InventoryMouvement("update increment",date,po.getQuantityToPurchase(),prd);
			    	inventoryMovementServicesRemote.save(mvt1);
			    	
			    	notification("Operation Succeeded !! ", "  Purchase order CONFIRMED with Succes  ");
			    	Clear();
			    	act();
				}
				
			}
			else {
				
				alert.setHeaderText(" Would You Like to CANCELED this  Purchase order and add Stock ?? ");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					po.setStatus("Canceled");
					purchaseOrdersserviceRemote.update(po);
					notification("Operation Succeeded !! ", "   Purchase order CANCELED with Succes  ");
			    	Clear();
			    	act();
				}
				
			}
	 }
			
    		
	   @FXML
	    void change(ActionEvent event) {
		   if(etbstate.isSelected()){btadd.setText("Canceled");}
		   else if(!etbstate.isSelected()){btadd.setText("Confirmed");}
		   
	    }
		 
}




