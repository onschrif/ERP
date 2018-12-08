package tn.esprit.b3.esprit1718b3erp.app.client.scm_purchase;

import javax.naming.NamingException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

		    @Override
		    public void start(Stage stage) throws Exception {
		    //  Parent root = FXMLLoader.load(getClass().getResource("PurchaseDashboard.fxml"));	
		    //  Parent root = FXMLLoader.load(getClass().getResource("../scm_inventory/Adjustment.fxml"));	
		      
		    Parent root = FXMLLoader.load(getClass().getResource("Product.fxml"));
		    //	Parent root = FXMLLoader.load(getClass().getResource("BillofMaterial.fxml"));
		    	
		   //	Parent root = FXMLLoader.load(getClass().getResource("ordersList.fxml"));

		        Scene scene = new Scene(root);		        
		        stage.setScene(scene);
		        stage.setTitle("Home");
		        stage.show();
		    }

		    /**
		     * @param args the command line arguments
		     */
		    public static void main(String[] args) throws NamingException {

		    	
		        launch(args);
		    }
		    
		}
