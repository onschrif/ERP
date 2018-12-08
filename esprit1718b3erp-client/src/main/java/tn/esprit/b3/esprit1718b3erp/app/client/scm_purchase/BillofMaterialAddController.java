package tn.esprit.b3.esprit1718b3erp.app.client.scm_purchase;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import tn.esprit.b3.esprit1718b3erp.app.client.login.Login;
import tn.esprit.b3.esprit1718b3erp.entities.BillOfMaterial;
import tn.esprit.b3.esprit1718b3erp.entities.BillOfMaterialEmb;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.entities.ProductCk;
import tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.BillOfMaterialServicesRemote;
import tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesRemote;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

public class BillofMaterialAddController implements Initializable {

	@FXML
	private TableView<ProductCk> tableProduct;

	@FXML
	private TableColumn<ProductCk, JFXComboBox<String>> col0;
	
	@FXML
	private TableColumn<ProductCk, String> col1;

	@FXML
	private TableColumn<ProductCk, String> col2;

	@FXML
	private TableColumn<ProductCk, String> col3;

	@FXML
	private TableColumn<ProductCk, JFXTextField> col4;

	@FXML
	private JFXComboBox<String> cbproductparent;

	@FXML
	private JFXTextField tfname;
	@FXML
	private JFXTextField tfprice;
	@FXML
	private JFXTextField tfnature;
	
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

		//Remove Bill deja cree
		List<BillOfMaterial> listBillPrRem = new ArrayList<>(); 
		listBillPrRem = billOfMaterialServicesRemote.FindAllBillGroupeByPere();
		for (BillOfMaterial billOfMaterial : listBillPrRem) {
			System.out.println("Pere remove : " +billOfMaterial.getProductPere().getName());
		}

		//add ComboBox
		List<Product> listParent = new ArrayList<Product>();
		listParent = productServiceRemote.FindAllProductParent();
		System.out.println("nbr list Parent : " +listParent.size());
		
		List<String> ProductRef = new ArrayList<>();

				for (int i = 0; i < listParent.size(); i++) {				
					String itemPN = listParent.get(i).getRef();
				     ProductRef.add(itemPN);
				}
				for (int j = 0; j < listBillPrRem.size(); j++) {
					String itemPNR = listBillPrRem.get(j).getProductPere().getRef();
					if (ProductRef.contains(itemPNR)) ProductRef.remove(itemPNR);
				}
		cbproductparent.getItems().addAll(ProductRef);

		
//		//list Component product
//		List<Product> listComponent = new ArrayList<Product>();
//		listComponent = productServiceRemote.FindAllProductComponent();
//
//		
//		
//		//table Component product 
//		List<ProductCk> listP = new ArrayList<>(); 
//
//		for (int i = 0; i < listComponent.size(); i++) {
//			Product p = listComponent.get(i);
//			ProductCk pk = new ProductCk(p.getIdProduct(), p.getName(), p.getRef(),p.getPrice());
//			listP.add(pk);
//		}
//
//
//		
//		col0.setCellValueFactory(new PropertyValueFactory<ProductCk, JFXComboBox<String>>("selectck"));
//		col1.setCellValueFactory(new PropertyValueFactory<ProductCk, String>("ref"));
//		col2.setCellValueFactory(new PropertyValueFactory<ProductCk, String>("name"));
//		col3.setCellValueFactory(new PropertyValueFactory<ProductCk, String>("price"));
//		col4.setCellValueFactory(new PropertyValueFactory<ProductCk, JFXTextField>("tf"));
//
//		System.out.println("List of Product Comp");
//		ObservableList<ProductCk> items = FXCollections.observableArrayList(listP);
//
//		tableProduct.setItems(items);
		ProductCk pck =new ProductCk();
		//act(pck);
		

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
	
	@FXML
	public void toPurchaseOrder(ActionEvent event) throws IOException {
		to(event, "PurchaseOrder.fxml", "Purchase Order");
	}
	
	@FXML
    void addBoM(ActionEvent event) throws IOException {
		
		
		//recupere Parent Product
		String prdSelectRef = cbproductparent.getSelectionModel().getSelectedItem().toString();
		Product PrdParent =productServiceRemote.FindProductByRef(prdSelectRef);
		System.out.println("produit Parent select : " +PrdParent.getName());
		
		//recuperer la liste de produit de Tableview
	    ////	ObservableList<ProductCk> tbPrd = FXCollections.observableArrayList();
		List<ProductCk> tbPrd = new ArrayList<>(); 
		tbPrd.addAll(tableProduct.getItems());
		System.out.println("nbr prd dans Tableview = " + tbPrd.size());

		//verifier de la liste si le produit est selectionnez et l'ajouter dans une liste tbPrdCkd
		////  ObservableList<ProductCk> tbPrdCkd = FXCollections.observableArrayList();
		List<ProductCk> tbPrdCkd = new ArrayList<>(); 
		for (ProductCk ppd : tbPrd) {
			if (ppd.getSelectck().isSelected())
			{
				tbPrdCkd.add(ppd);		
			}
		}
		System.out.println("nbr prd dans Tableview Cheked = " +tbPrdCkd.size());
		
		double priceTPrdP = 0;
		
		for (ProductCk pp : tbPrdCkd) {
			System.out.println("ProductCk select : : "+pp.toString());		
			Product PrdFils = productServiceRemote.find(pp.getIdProduct());
			System.out.println("produuFils select " +PrdFils.getIdProduct() +PrdFils.getName());		
			BillOfMaterialEmb billEmb= new BillOfMaterialEmb(PrdParent.getIdProduct(), PrdFils.getIdProduct());
			BillOfMaterial bill= new BillOfMaterial();
			bill.setBillOfMaterialEmb(billEmb);
			bill.setQuantity(Integer.parseInt(pp.getTf().getText()));
			priceTPrdP= priceTPrdP+ Integer.parseInt(pp.getTf().getText())*PrdFils.getPrice();
			bill.setPriceT(Integer.parseInt(pp.getTf().getText())*PrdFils.getPrice());
     		billOfMaterialServicesRemote.save(bill);
	
			System.out.println("Bill ADDED ");
		}
		PrdParent.setPrice(priceTPrdP);
		productServiceRemote.update(PrdParent);
		to(event, "BillofMaterial.fxml", "Bill of Material");

		
//VERIFICATIION		
//		if (tfname.getText().length() == 0){
//			Alert alert = new Alert(AlertType.WARNING);
//			alert.setTitle("ERROR");
//			alert.setHeaderText(" Select the Parent Product ! ");
//
//			alert.showAndWait();
//		}
//		else if (tbPrdCkd.size() == 0){
//			Alert alert = new Alert(AlertType.WARNING);
//			alert.setTitle("ERROR");
//			alert.setHeaderText(" Select one comp ! ");
//
//			alert.showAndWait();
//		}
		
		
	}

	public void act(ProductCk pkRemove) {
		//list Component product
				List<Product> listComponent = new ArrayList<Product>();
				listComponent = productServiceRemote.FindAllProductComponent();

				
				
				//table Component product 
				List<ProductCk> listP = new ArrayList<>(); 

				for (int i = 0; i < listComponent.size(); i++) {
					Product p = listComponent.get(i);
					ProductCk pk = new ProductCk(p.getIdProduct(), p.getName(), p.getRef(),p.getPrice());
					if (!pk.equals(pkRemove)) listP.add(pk);
				}

				col0.setCellValueFactory(new PropertyValueFactory<ProductCk, JFXComboBox<String>>("selectck"));
				col1.setCellValueFactory(new PropertyValueFactory<ProductCk, String>("ref"));
				col2.setCellValueFactory(new PropertyValueFactory<ProductCk, String>("name"));
				col3.setCellValueFactory(new PropertyValueFactory<ProductCk, String>("price"));
				col4.setCellValueFactory(new PropertyValueFactory<ProductCk, JFXTextField>("tf"));

				System.out.println("List of Product Act");
				ObservableList<ProductCk> items = FXCollections.observableArrayList(listP);

				tableProduct.setItems(items);
	}
	
	@FXML
    void changeInfo(ActionEvent event) {
		//recuperer l item selectionnez
		String RefProductSelect = cbproductparent.getSelectionModel().getSelectedItem().toString();
		//System.out.println(RefProductSelect);
		
		//remplir les tf
		Product p =productServiceRemote.FindProductByRef(RefProductSelect);
		// System.out.println("nom" + p.getName());
		tfname.setText(p.getName());
		tfnature.setText(p.getNature());
		tfprice.setText(p.getPrice()+"");
		
		//removeitem from table
		ProductCk pk = new ProductCk(p.getIdProduct(), p.getName(), p.getRef(),p.getPrice());
		
		
		act(pk);
	}
	

	@FXML
    void test(ActionEvent event) {

		List<BillOfMaterial> l = billOfMaterialServicesRemote.FindAllBillWithNbrComponent();
		for (int i = 0; i < l.size(); i++) {
			System.out.println("nbr = "+l.get(i).getNbrComponent() + " // pere = "+l.get(i).getProductPere().getName() );
		}
    }

}
