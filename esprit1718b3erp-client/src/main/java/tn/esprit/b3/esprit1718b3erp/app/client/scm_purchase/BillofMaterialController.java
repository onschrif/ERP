package tn.esprit.b3.esprit1718b3erp.app.client.scm_purchase;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.Duration;
import tn.esprit.b3.esprit1718b3erp.app.client.login.Login;
import tn.esprit.b3.esprit1718b3erp.entities.BillOfMaterial;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.BillOfMaterialServicesRemote;
import tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesRemote;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXTextField;

public class BillofMaterialController implements Initializable {

	@FXML
	private JFXTextField search;
	
	@FXML
	private TableView<BillOfMaterial> tableBill;

	@FXML
	private TableColumn<BillOfMaterial, String> col0;
	
	@FXML
	private TableColumn<BillOfMaterial, String> col1;

	@FXML
	private TableColumn<BillOfMaterial, String> col2;
	
	@FXML
	private TableView<BillOfMaterial> tableComponent;

	@FXML
	private TableColumn<BillOfMaterial, String> col10;

	@FXML
	private TableColumn<BillOfMaterial, String> col20;
	
	@FXML
	private TableColumn<BillOfMaterial, String> col30;

	@FXML
	private TableColumn<BillOfMaterial, String> col40;

	@FXML
	private TableColumn<BillOfMaterial, String> col50;

	
    @FXML
    private TreeTableView<Product> treeBill;

    @FXML
    private TreeTableColumn<Product, String> bill;

    @FXML
    private TreeTableColumn<Product, String> qte;
	
	@FXML
	private AnchorPane dashpane;
	static Boolean dash = false;
		 

	ServiceLocator s = ServiceLocator.getInstance();
		
	String jndiNameP = "esprit1718b3erp-ear/esprit1718b3erp-service/ProductServices!tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesRemote";
	ProductServicesRemote productServiceRemote = (ProductServicesRemote) s.getProxy(jndiNameP);

	String jndiNameBM = "esprit1718b3erp-ear/esprit1718b3erp-service/BillOfMaterialServices!tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.BillOfMaterialServicesRemote";
	BillOfMaterialServicesRemote billOfMaterialServicesRemote=(BillOfMaterialServicesRemote) s.getProxy(jndiNameBM);
	
	 
	@FXML
	public void dsh(ActionEvent event) {
		TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), dashpane);
		if (dash) {
			translateTransition1.setByX(-250);
			dash = false;
		} else {
			translateTransition1.setByX(+250);
			dash = true;
		}
		translateTransition1.play();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		dash = false;

		act();
		InitTreeTableView();
	}

	@FXML
	public void close(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	public void minimize(ActionEvent event) {
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
	public void toPurchaseOrder(ActionEvent event) throws IOException {
		to(event, "PurchaseOrder.fxml", "Purchase Order");
	}
	
	
	@FXML
	public void toSuppliers(ActionEvent event) throws IOException {
		to(event, "SuppliersList.fxml", "Suppliers");
	}

	
	@FXML
	public void addBill(ActionEvent event) throws IOException {
		to(event, "BillofMaterialAdd.fxml", "Bill of Material");
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
		
		col1.setCellValueFactory(new Callback<CellDataFeatures<BillOfMaterial,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<BillOfMaterial, String> param) {
                return new SimpleStringProperty(param.getValue().getProductPere().getName());
            }
        });
		col0.setCellValueFactory(new Callback<CellDataFeatures<BillOfMaterial,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<BillOfMaterial, String> param) {
                return new SimpleStringProperty(param.getValue().getProductPere().getRef());
            }
        });
		col2.setCellValueFactory(new PropertyValueFactory<BillOfMaterial, String>("nbrComponent"));

		List<BillOfMaterial> listL = new ArrayList<BillOfMaterial>();

		listL = billOfMaterialServicesRemote.FindAllBillWithNbrComponent();
		System.out.println("List of Bill Act" +listL.size());

		ObservableList<BillOfMaterial> items = FXCollections.observableArrayList(listL);

		tableBill.setItems(items);
		
	}
	
	@FXML
	public void searchBill(KeyEvent event) {
		ObservableList table = tableBill.getItems();
		search.textProperty()
				.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
					if (oldValue != null && (newValue.length() < oldValue.length())) {
						tableBill.setItems(table);
					}
					String value = newValue.toLowerCase();
					ObservableList<BillOfMaterial> subentries = FXCollections.observableArrayList();

					long count = tableBill.getColumns().stream().count();
					for (int i = 0; i < tableBill.getItems().size(); i++) {
						for (int j = 0; j < count; j++) {
							String entry = "" + tableBill.getColumns().get(j).getCellData(i);
							if (entry.toLowerCase().contains(value)) {
								subentries.add(tableBill.getItems().get(i));
								break;
							}
						}
					}
					tableBill.setItems(subentries);
				});
	}
	
	@FXML
	void Refresh(ActionEvent event) {
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
	
	@FXML
	void deleteBill(ActionEvent event) {

		
		if (tableBill.getSelectionModel().getSelectedItem() != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation ");
			alert.setHeaderText(" Would You Like to DELETE this Bill of Material ?? ");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				Product p = tableBill.getSelectionModel().getSelectedItem().getProductPere();
				System.out.println("Prd Pere to delete" +p.getName());
				billOfMaterialServicesRemote.DeleteBill(p);
				notification("Operation Succeeded !! ", "   DELETED with Succes  ");
				System.out.println("DELETED with Succes");
				act();
				InitTreeTableView();
				actComponent(null); 
			}

		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("ERROR");
			alert.setHeaderText(" Select the Bill Of Material that You want to DELETE ! ");

			alert.showAndWait();
		}
	}
	
	
	
	
	public void actComponent(Product p) {
		
		col10.setCellValueFactory(new Callback<CellDataFeatures<BillOfMaterial,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<BillOfMaterial, String> param) {
                return new SimpleStringProperty(param.getValue().getProductFils().getName());
            }
        });
		col20.setCellValueFactory(new Callback<CellDataFeatures<BillOfMaterial,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<BillOfMaterial, String> param) {
                return new SimpleStringProperty(param.getValue().getProductFils().getRef());
            }
        });
		col30.setCellValueFactory(new Callback<CellDataFeatures<BillOfMaterial,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<BillOfMaterial, String> param) {
                return new SimpleStringProperty(param.getValue().getProductFils().getPrice()+"");
            }
        });
		
		col40.setCellValueFactory(new PropertyValueFactory<BillOfMaterial, String>("quantity"));

		col50.setCellValueFactory(new PropertyValueFactory<BillOfMaterial, String>("priceT"));

		List<BillOfMaterial> listL = new ArrayList<BillOfMaterial>();

		listL = billOfMaterialServicesRemote.FindComponentBill(p);
		System.out.println("List of component Act" +listL.size());

		ObservableList<BillOfMaterial> items = FXCollections.observableArrayList(listL);

		tableComponent.setItems(items);
		
	}
	@FXML
	void viewBill (ActionEvent event) throws IOException {
		
		if (tableBill.getSelectionModel().getSelectedItem() != null) {
		Product p = tableBill.getSelectionModel().getSelectedItem().getProductPere();
		System.out.println("Prd Pere to delete" +p.getName());
		actComponent(p); 
		}
		else {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("ERROR");
		alert.setHeaderText(" Select the Bill Of Material that You want to VIEW ! ");

		alert.showAndWait();
	}
	}
	
	
	private void InitTreeTableView() {
		 TreeItem<Product> root=new TreeItem<Product>();
		 List<Product> produitFini = productServiceRemote.FindAllProductParent();
		 root.getChildren().size();
		 TreeItem<Product> newItemarticlePere;
		 TreeItem<Product> newItemarticleFils=null;
		
		 for(int i=0;i<produitFini.size();i++) {
			 System.out.println("//\\");
//			 List<Product> articlePere=produitFini;
			 newItemarticlePere=new TreeItem<>(produitFini.get(i));
			 System.out.println("Product Pere "+newItemarticlePere.getValue().getName());
			 root.getChildren().add(newItemarticlePere)  ;
		 
	//	 System.out.println( root.getChildren().size()+"");
			 
		 
		 ArrayDeque <TreeItem<Product>> queue=new ArrayDeque<>();
		 queue.add(newItemarticlePere);
		
		 System.out.println("queue "+queue.getLast().getValue().getName());
		 while(!queue.isEmpty()) {
		
				TreeItem<Product> TreeItemHead=queue.getFirst();
				 queue.removeFirst();
			//	 System.out.println("TreeItemHead "+TreeItemHead.getValue().getName());
				 List<BillOfMaterial> listNomenclatureFils=billOfMaterialServicesRemote.FindComponentBill(TreeItemHead.getValue());
			//	 System.out.println("size Bill " + listNomenclatureFils.size());
				 for(int j=0;j<listNomenclatureFils.size();j++) {
					 Product pr = productServiceRemote.find(listNomenclatureFils.get(j).getProductFils().getIdProduct());
					 System.out.println("pr" +pr);
					 newItemarticleFils=new TreeItem<>(pr);
					 TreeItemHead.getChildren().add(newItemarticleFils);
					 queue.addLast(newItemarticleFils);		
		
				 }
			 }
		 
		 }
		 
		 	
		 bill.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Product,String>,ObservableValue<String>>(){
	            @Override
	            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Product, String> param) {
	                return new SimpleStringProperty(param.getValue().getValue().getName()+" ("+param.getValue().getValue().getRef()+")");
	            }
	        });
		 treeBill.setRoot(root);
		 treeBill.setShowRoot(false);
		 
	}
	
}
