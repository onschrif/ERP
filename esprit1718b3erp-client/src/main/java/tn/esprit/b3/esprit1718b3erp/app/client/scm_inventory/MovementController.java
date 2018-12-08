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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.Duration;
import tn.esprit.b3.esprit1718b3erp.app.client.login.Login;
import tn.esprit.b3.esprit1718b3erp.entities.BillOfMaterial;
import tn.esprit.b3.esprit1718b3erp.entities.InventoryLocation;
import tn.esprit.b3.esprit1718b3erp.entities.InventoryMouvement;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.scm_InventoryServices.InventoryMovementServicesRemote;
import tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesRemote;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;




public class MovementController implements Initializable {
	
	@FXML
    private TableView<InventoryMouvement> tablemouvement;

    @FXML
    private TableColumn<InventoryMouvement, String> col1;

    @FXML
    private TableColumn<InventoryMouvement, String> col2;

    @FXML
    private TableColumn<InventoryMouvement, String> col3;

    @FXML
    private TableColumn<InventoryMouvement, String> col4;

    @FXML
    private TableColumn<InventoryMouvement, String> col5;

    @FXML
    private JFXTextField search;
    
    @FXML
    private BarChart barchart;
    @FXML
    private AreaChart areachart;

    String jndiNameP = "esprit1718b3erp-ear/esprit1718b3erp-service/ProductServices!tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesRemote";
	ServiceLocator s = ServiceLocator.getInstance();
	ProductServicesRemote productServiceRemote = (ProductServicesRemote) s.getProxy(jndiNameP);

    String jndiNameM = "esprit1718b3erp-ear/esprit1718b3erp-service/InventoryMovementServices!tn.esprit.b3.esprit1718b3erp.scm_InventoryServices.InventoryMovementServicesRemote";
	InventoryMovementServicesRemote inventoryMovementServicesRemote = (InventoryMovementServicesRemote) s.getProxy(jndiNameM);

    
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
    	act();  	
    	dash=false;
    	
    	//BarChart
    	 XYChart.Series series1 = new XYChart.Series();
    	  series1.setName("Number of Movement"); 
    	  
    	List<InventoryMouvement> l = inventoryMovementServicesRemote.FindNbrMvtPrd();
  		for (int i = 0; i < l.size(); i++) {
  			System.out.println("nbr = "+l.get(i).getNbrMvtPrd() + " // pere = "+l.get(i).getProduct().getName() );
  			series1.getData().add(new XYChart.Data(l.get(i).getProduct().getName(), l.get(i).getNbrMvtPrd()));
  		}
  		barchart.getData().add(series1);
  		
  		//AreaChart
  		SetSellValueFromTableViewToTextEdit();
          
    
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
	
	public void act() {
		col3.setCellValueFactory(new PropertyValueFactory<InventoryMouvement, String>("operationName"));
		col4.setCellValueFactory(new PropertyValueFactory<InventoryMouvement, String>("quantity"));
		col5.setCellValueFactory(new PropertyValueFactory<InventoryMouvement, String>("date"));
		col1.setCellValueFactory(new Callback<CellDataFeatures<InventoryMouvement,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<InventoryMouvement, String> param) {
                return new SimpleStringProperty(param.getValue().getProduct().getRef());
            }
        });
		
		col2.setCellValueFactory(new Callback<CellDataFeatures<InventoryMouvement,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<InventoryMouvement, String> param) {
                return new SimpleStringProperty(param.getValue().getProduct().getName());
            }
        });
		
		List<InventoryMouvement> listP = null;

		listP = inventoryMovementServicesRemote.findAll();
		System.out.println("List of Mvt Act");

		ObservableList<InventoryMouvement> items = FXCollections.observableArrayList(listP);

		tablemouvement.setItems(items);
	}
	
	@FXML
	void Refresh(ActionEvent event) {
		search.setText("");
		act();
	}
	
	@FXML
    void searchMouvement(KeyEvent event) {

		ObservableList table = tablemouvement.getItems();
		search.textProperty()
				.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
					if (oldValue != null && (newValue.length() < oldValue.length())) {
						tablemouvement.setItems(table);
					}
					String value = newValue.toLowerCase();
					ObservableList<InventoryMouvement> subentries = FXCollections.observableArrayList();

					long count = tablemouvement.getColumns().stream().count();
					for (int i = 0; i < tablemouvement.getItems().size(); i++) {
						for (int j = 0; j < count; j++) {
							String entry = "" + tablemouvement.getColumns().get(j).getCellData(i);
							if (entry.toLowerCase().contains(value)) {
								subentries.add(tablemouvement.getItems().get(i));
								break;
							}
						}
					}
					tablemouvement.setItems(subentries);
				});

    }


	public void initAreaChar(int idPrd){
		
  		XYChart.Series seriesProduct= new XYChart.Series();

		System.out.println("///////");

        List<InventoryMouvement> ll = inventoryMovementServicesRemote.FindMvtProduct(idPrd);
        int qtint = ll.get(0).getQuantity();
        String dtint = ll.get(0).getDate()+"";
    	System.out.println("Datee = "+dtint + " // qte = "+qtint );
    	
    	seriesProduct.getData().add(new XYChart.Data(dtint, qtint));
    	seriesProduct.setName(ll.get(0).getProduct().getName());

    	
  		for (int i = 1; i < ll.size(); i++) {
  		//	System.out.println("Datee = "+ll.get(i).getDate() + " // qte = "+ll.get(i).getQuantity() );
  			int qte; String dt ;
  			if (ll.get(i).getOperationName().equals("update increment")){qtint = qtint+ll.get(i).getQuantity();}
  			else {qtint = qtint-ll.get(i).getQuantity();}
  			dt = ll.get(i).getDate()+"";
  			System.out.println("Datee = "+dt + " // qte = "+qtint );
  			
  			seriesProduct.getData().add(new XYChart.Data(dt, qtint));
  		}
		System.out.println("///////");
		 areachart.getData().add(seriesProduct);
        
	}
	
	public void SetSellValueFromTableViewToTextEdit() {
		tablemouvement.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				InventoryMouvement il =  tablemouvement.getSelectionModel().getSelectedItem() ;
				areachart.getData().clear();
				initAreaChar(il.getProduct().getIdProduct());
//				etfadr.setText(il.getAdress());
//				etfname.setText(il.getLocationName());
				
			}
			
		});
	}
}
