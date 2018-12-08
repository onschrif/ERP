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
import tn.esprit.b3.esprit1718b3erp.entities.InventoryLocation;
import tn.esprit.b3.esprit1718b3erp.entities.InventoryMouvement;
import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
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




public class ordersListController implements Initializable {
	
	@FXML
    private TableView<Orders> tableorders;

    @FXML
    private TableColumn<Orders, String> col2;

    @FXML
    private TableColumn<Orders, String> col3;

    @FXML
    private TableColumn<Orders, String> col4;

    @FXML
    private TableColumn<Orders, String> col5;

    @FXML
    private TableColumn<Orders, String> col7;

    @FXML
    private TableColumn<Orders, String> col8;

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
    private JFXTextField tfinvdate;

    @FXML
    private JFXTextField tfRecdate;
   
    @FXML
    private JFXTextField tfstateinti;
    
    @FXML
    private JFXToggleButton etbstate;

    @FXML
    private JFXTextField tfqteorder;

//    @FXML
//    PRIVATE JFXCOMBOBOX<STRING> CBSTATE;
    
    @FXML 
    private AnchorPane dashpane;
    static Boolean dash=false;
    
    String jndiNameP = "esprit1718b3erp-ear/esprit1718b3erp-service/ProductServices!tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesRemote";
	ServiceLocator s = ServiceLocator.getInstance();
	ProductServicesRemote productServiceRemote = (ProductServicesRemote) s.getProxy(jndiNameP);
    
    String jndiNameM = "esprit1718b3erp-ear/esprit1718b3erp-service/Manufacturing1!tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.Manufacturing1Remote";
    Manufacturing1Remote manufacturing1Remote = (Manufacturing1Remote) s.getProxy(jndiNameM);
    
    String jndiNameMv = "esprit1718b3erp-ear/esprit1718b3erp-service/InventoryMovementServices!tn.esprit.b3.esprit1718b3erp.scm_InventoryServices.InventoryMovementServicesRemote";
	InventoryMovementServicesRemote inventoryMovementServicesRemote = (InventoryMovementServicesRemote) s.getProxy(jndiNameMv);

    
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
	public void toProduct(ActionEvent event) throws IOException {
		to(event, "Product.fxml", "Product");

	}

	@FXML
	public void toPurchaseOrder(ActionEvent event) throws IOException {
		to(event, "PurchaseOrder.fxml", "Purchase Order");
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

		col2.setCellValueFactory(new PropertyValueFactory<>("referenceOrder"));
		col3.setCellValueFactory(new PropertyValueFactory<>("RecommendedDate"));
		col4.setCellValueFactory(new PropertyValueFactory<>("invoiceDate"));
		col5.setCellValueFactory(new PropertyValueFactory<>("quantityToOrder"));
		col7.setCellValueFactory(new PropertyValueFactory<>("orderState"));
		col8.setCellValueFactory(new Callback<CellDataFeatures<Orders,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<Orders, String> param) {
                return new SimpleStringProperty(param.getValue().getP().getName());
            }
        });
		List<Orders> listO = null;

		listO = manufacturing1Remote.FindAllorderW();
		System.out.println("List of orders Act");

		ObservableList<Orders> items = FXCollections.observableArrayList(listO);

		tableorders.setItems(items);
		
	
	}
	
	@FXML
    void searchOrders(KeyEvent event) {
		ObservableList table = tableorders.getItems();
		search.textProperty()
				.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
					if (oldValue != null && (newValue.length() < oldValue.length())) {
						tableorders.setItems(table);
					}
					String value = newValue.toLowerCase();
					ObservableList<Orders> subentries = FXCollections.observableArrayList();

					long count = tableorders.getColumns().stream().count();
					for (int i = 0; i < tableorders.getItems().size(); i++) {
						for (int j = 0; j < count; j++) {
							String entry = "" + tableorders.getColumns().get(j).getCellData(i);
							if (entry.toLowerCase().contains(value)) {
								subentries.add(tableorders.getItems().get(i));
								break;
							}
						}
					}
					tableorders.setItems(subentries);
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
		 tableorders.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {

					Orders o =  tableorders.getSelectionModel().getSelectedItem() ;
					tfname.setText(o.getP().getName());
					tfref.setText(o.getP().getRef());
					tfqtestock.setText(o.getP().getQuantity()+"");
					
					tfinvdate.setText(o.getInvoiceDate());
					tfRecdate.setText(o.getRecommendedDate());
					tfqteorder.setText(o.getQuantityToOrder()+"");
					
					tfstateinti.setText(o.getOrderState());
					
					
					btadd.setDisable(false);
//					tfnbr1.setText(p.getQuantity()+"");
//					btedit.setDisable(false);
//					btmoins1.setDisable(false);
//					btplus1.setDisable(false);
				}
				
			});
		}
	    
	 void ClearEdit(){
		 
		   tfname.setText("");
			tfref.setText("");
			tfqtestock.setText("");
			
			tfinvdate.setText("");
			tfRecdate.setText("");
			tfqteorder.setText("");
			
			tfstateinti.setText("");
			
			etbstate.setSelected(false);
	 }
	 
	 @FXML
		public void add(ActionEvent event)  {
			
    		 String state = "Confirmed";
			if(etbstate.isSelected()){state = "Canceled";}
			
			 Orders o =  tableorders.getSelectionModel().getSelectedItem() ;

			
			int qteOrd , qteStk ;
			qteStk = o.getP().getQuantity();
			qteOrd = o.getQuantityToOrder();
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation ");

			if (tableorders.getSelectionModel().getSelectedItem() != null) {

				
				if (qteStk >= qteOrd) {
					alert.setHeaderText(" Would You Like to CONFIRMED this order and Remode Quantity from Stock ?? ");
					
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK) {
				   	Product p = o.getP();
					 o.setOrderState(state);
					 int qteRem = qteStk -qteOrd;
					 p.setQuantity(qteRem);
					 manufacturing1Remote.update(o);
					 productServiceRemote.update(p);
					 
					 //mvt
					 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						Date date = new Date();
				    	InventoryMouvement mvt1 = new InventoryMouvement("update decrease",date,qteOrd,p);
				    	inventoryMovementServicesRemote.save(mvt1);
					 
					 
					 notification("Operation Succeeded !! ", "   CONFIRMED with Succes  ");
						ClearEdit();
						act();
						System.out.println("UPDATED with Succes");
					}
				}
				else {
					
					 
							alert.setHeaderText(" Would You Like to CANCELED this order and Do Manufacturing ?? ");
							
							Optional<ButtonType> result = alert.showAndWait();
							if (result.get() == ButtonType.OK) {
								
								 o.setOrderState(state);
								 manufacturing1Remote.update(o);

								 notification("Operation Succeeded !! ", "   CANCELED with Succes  ");
									ClearEdit();
									act();
								 //add Manuf
							}
					
					
				}
				
			}

			else {
				Alert alert1 = new Alert(AlertType.WARNING);
				alert1.setTitle("ERROR");
				alert1.setHeaderText(" Select Order that You want to Treat ! ");
				alert1.showAndWait();
			}
		}

}


//INSERT INTO `erp`.`orders` (`idOrder`, `RecommendedDate`, `discountOrder`, `invoiceDate`, `orderAmount`, `orderState`, `quantityToOrder`, `quotationDate`, `referenceOrder`, `idClient`, `idProduct`) VALUES (NULL, '20/05/2018', '', '12/04/2018', '', 'Waiting', '20', NULL, NULL, '1', '3');
