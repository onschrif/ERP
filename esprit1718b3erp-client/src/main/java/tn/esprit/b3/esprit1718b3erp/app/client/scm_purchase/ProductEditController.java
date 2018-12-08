package tn.esprit.b3.esprit1718b3erp.app.client.scm_purchase;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import java.sql.SQLException;


public class ProductEditController implements Initializable {
	
	
    
    @FXML 
    private AnchorPane dashpane;
    static Boolean dash=false;
    
    @FXML
    private JFXTextField tfname;

    @FXML
    private JFXTextField tfref;

    @FXML
    private JFXTextField tfean;

    @FXML
    private JFXTextField tfprice;

    @FXML
    private JFXCheckBox ckpurchesed;

    @FXML
    private JFXCheckBox cksold;

    @FXML
    private JFXComboBox<String> cbnature;

    @FXML
    private JFXComboBox<String> cbtype;

    @FXML
    private JFXTextField tfcat;

    @FXML
    private JFXTextField tfum;

    @FXML
    private JFXTextField tfdesc;
   
    @FXML
    private Label lblError;
    
	String jndiNameP = "esprit1718b3erp-ear/esprit1718b3erp-service/ProductServices!tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesRemote";
	ServiceLocator s=ServiceLocator.getInstance(); 
	ProductServicesRemote productServiceRemote=(ProductServicesRemote) s.getProxy(jndiNameP);

	String strnature ,strtype ,strcategory ,strum="",strdesc="";
	int purchesed=0 , sold=0 ;
	long lgean =0 ; 
	double dbprice ;
	
	Product p = productServiceRemote.find(Session.getId_prd());
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

   	 dash=false;

   	lblError.setVisible(false);
   	
   	tfname.setText(p.getName());
   	tfref.setText(p.getRef());
   	tfean.setText(p.getEAN13()+"");
   	tfprice.setText(p.getPrice()+"");
   	if (p.getPurchesed()==1) {ckpurchesed.setSelected(true);}
   	if (p.getSold()==1) {cksold.setSelected(true);}
   	tfcat.setText(p.getCategory());
   	cbnature.setValue(p.getNature());
   	cbtype.setValue(p.getType());
   	tfdesc.setText(p.getDescription()+"");
   	tfum.setText(p.getUm()+"");
   	
    }
   
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
        translateTransition1.play(); 
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
	
	public boolean Verif() 
    {
		boolean v = true ;
		
		List<Product> listProductt = new ArrayList<Product>();
		List<Product> listPr = new ArrayList<Product>();
		listPr = productServiceRemote.findAll();
		
		//remove this prd
		Product prd = productServiceRemote.find(Session.getId_prd());  
		listProductt.remove(prd);
		
		for (Product lee : listPr) {if ( lee.getIdProduct() != Session.getId_prd() ){listProductt.add(lee);}}
		
		//for (Product lp : listProductt) {System.out.println(lp.getName());}
		// Name
//		boolean trv = true;
//		for (Product l : listPrd) {
//			if (l.getName().equals(tfname.getText())) {
//				trv = false;
//				break;
//			}
//		}

		if (tfname.getText().length() == 0) {
			v = false;
		}
//		else {
//			if (trv == false) {
//				lblError.setText("The NAME already exists !");
//				v = false;
//			} else {
//				lblError.setText("All the fields with an asterisk (*) are obligatory");
//				v = true;
//			}
//		}
        
		// Ref
		boolean trv1 = true;
		for (Product ll : listProductt) {
			if (ll.getRef().equals(tfref.getText())) {
				trv1 = false;
				break;
			}
		}

		if (tfref.getText().length() == 0) {
			v = false;
		} else {
			if (trv1 == false) {
				lblError.setText("The REFERENCE already exists !");
				v = false;
			} else {
				lblError.setText("All the fields with an asterisk (*) are obligatory");
				v = true;
			}
		}
		
		// EAN		
				if (tfean.getText().length() == 0) {
					v = false;
				} 		

				// price
				if (tfprice.getText().length() == 0) {
					v = false;
				} 
				

				// nature
				if (cbnature.getSelectionModel().isEmpty() == true)  {
					v = false;
				} 
				
				// category
				if (tfcat.getText().length() == 0) {
					v = false;
				} 

				// type
				if (cbtype.getSelectionModel().isEmpty() == true)  {
					v = false;
				} 
       //um
       //Desc 
       
       //Recupere les info
		 
				 if(ckpurchesed.isSelected()==true){purchesed=1;}
				 if(cksold.isSelected()==true) {sold=1;}
				 
				
				
				 if(tfum.getText().length() != 0) {strum = tfum.getText();}
				 if(tfdesc.getText().length() != 0) {strdesc = tfdesc.getText();}
				 if (cbnature.getSelectionModel().isEmpty() == false)  {
						strnature = cbnature.getSelectionModel().getSelectedItem().toString();
					} 
					if (cbtype.getSelectionModel().isEmpty() == false) {
						strtype = cbtype.getSelectionModel().getSelectedItem().toString();
					}
		 
		 if (tfcat.getText().length() != 0) {
				strcategory = tfcat.getText();
			} 
			if (ckpurchesed.isSelected() == true) {
				purchesed = 1;
			}
			if (cksold.isSelected() == true) {
				sold = 1;
			}

			if (tfum.getText().length() != 0) {
				strum = tfum.getText();
			}
			if (tfdesc.getText().length() != 0) {
				strdesc = tfdesc.getText();
			}
			

			if ( v==false ) {lblError.setVisible(true);}
			else {lblError.setVisible(false);}
			return v;
    }

    @FXML
    void editProduct(ActionEvent event) throws IOException {

    	
		 //les informations saisies sont correctes
    	 if (Verif() == true ){
    		 
    		  Product product = new Product(tfname.getText(),tfref.getText(), Long.parseLong(tfean.getText()), purchesed, sold, strtype, Double.parseDouble(tfprice.getText()), 0, strcategory, strum,strnature, strdesc,0);
    		  product.setIdProduct(Session.getId_prd());
    		  //add Product
    		  
    	    	productServiceRemote.update(product);
    	    	notification("Operation Succeeded !! ", "   UPDETED with Succes  ");
    	    	System.out.println("UPDETED with Succes");
    	    	
    	    	to(event, "Product.fxml", "Product");

    	      
    	    		
    	    
    	
    	 }
    }
    
 

}
