package tn.esprit.b3.esprit1718b3erp.app.client.scm_purchase;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import tn.esprit.b3.esprit1718b3erp.app.client.login.Login;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.entities.Session;
import tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesRemote;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Tooltip;

public class ProductController implements Initializable {

	@FXML
	private TableView<Product> tableProduct;

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
	JFXTextField search;

	@FXML
	JFXButton btview;

	@FXML
	JFXButton btdelete;

	@FXML
	JFXButton btedit;

	@FXML
	private Pagination pagination;

	@FXML
	private AnchorPane dashpane;
	static Boolean dash = false;

	String jndiNameP = "esprit1718b3erp-ear/esprit1718b3erp-service/ProductServices!tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesRemote";
	ServiceLocator s = ServiceLocator.getInstance();
	ProductServicesRemote productServiceRemote = (ProductServicesRemote) s.getProxy(jndiNameP);

	private int dataSize;
	private int rowsPerPage = 12;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		btdelete.setTooltip(new Tooltip("Delete selected product"));
		btedit.setTooltip(new Tooltip("edit selected product"));
		btview.setTooltip(new Tooltip("view selected product"));
		// nbr de produit
		List<Product> listP = null;
		listP = productServiceRemote.findAll();
		dataSize = listP.size();

		// initialisation pagination

//		pagination.setPageCount(dataSize / rowsPerPage + 1);
//		pagination.setCurrentPageIndex(0);

		dash = false;

		// initialisation table view
		col1.setCellValueFactory(new PropertyValueFactory<Product, String>("ref"));
		col2.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		col3.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
		col4.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
		col5.setCellValueFactory(new PropertyValueFactory<Product, String>("nature"));
		col6.setCellValueFactory(new PropertyValueFactory<Product, String>("type"));

		System.out.println("List of Product");
		ObservableList<Product> items = FXCollections.observableArrayList(listP);
		tableProduct.setItems(items);
	}

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
	
	@FXML
	public void toPurchaseOrder(ActionEvent event) throws IOException {
		to(event, "PurchaseOrder.fxml", "Purchase Order");
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
	public void toSuppliers(ActionEvent event) throws IOException {
		to(event, "SuppliersList.fxml", "Suppliers");
	}
	
	@FXML
	void addProduct(ActionEvent event) throws IOException {
		to(event, "ProductAdd.fxml", "Add Product");	
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
	
	void notification(String str1, String str2) {
		Notifications notificationbuilder = Notifications.create()
				.title(str1)
				.text(str2).hideAfter(Duration.seconds(5))
				.position(Pos.TOP_LEFT);
		
		notificationbuilder.showConfirm();
	}

	public void act() {
		col1.setCellValueFactory(new PropertyValueFactory<Product, String>("ref"));
		col2.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		col3.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
		col4.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
		col5.setCellValueFactory(new PropertyValueFactory<Product, String>("nature"));
		col6.setCellValueFactory(new PropertyValueFactory<Product, String>("type"));

		List<Product> listP = null;

		listP = productServiceRemote.findAll();
		System.out.println("List of Product Act");

		ObservableList<Product> items = FXCollections.observableArrayList(listP);

		tableProduct.setItems(items);
	}

	@FXML
	void deleteProduct(ActionEvent event) {

		if (tableProduct.getSelectionModel().getSelectedItem() != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation ");
			alert.setHeaderText(" Would You Like to DELETE this Product ?? ");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				int x = tableProduct.getSelectionModel().getSelectedItem().getIdProduct();
				Product p = productServiceRemote.find(x);
				productServiceRemote.delete(p);
				notification("Operation Succeeded !! ", "   DELETED with Succes  ");
				System.out.println("DELETED with Succes");
				act();
			}

		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("ERROR");
			alert.setHeaderText(" Select the Product that You want to DELETE ! ");

			alert.showAndWait();
		}
	}

	@FXML
	void editProduct(ActionEvent event) throws IOException {
		if (tableProduct.getSelectionModel().getSelectedItem() != null) {

			int x = tableProduct.getSelectionModel().getSelectedItem().getIdProduct();
			Session.setId_prd(x);
			to(event, "ProductEdit.fxml", "Edit Product");

		}

		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("ERROR");
			alert.setHeaderText(" Select the Product that You want to EDIT ! ");
			alert.showAndWait();
		}

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
	void viewProduct (ActionEvent event) throws IOException {
		if (tableProduct.getSelectionModel().getSelectedItem() != null) {

			int x = tableProduct.getSelectionModel().getSelectedItem().getIdProduct();
			Session.setId_prd(x);
			to(event, "ProductView.fxml", "View Product");

		}

		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("ERROR");
			alert.setHeaderText(" Select the Product that You want to VIEW ! ");
			alert.showAndWait();
		}

	}

	@FXML
	void Refresh(ActionEvent event) {
		// System.out.println("Ref");
		search.setText("");
		act();

	}

}
