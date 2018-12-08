package tn.esprit.b3.esprit1718b3erp.app.client.employee;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HRDashboardController {


    @FXML
    private ImageView imv;
    
    @FXML
    private JFXHamburger hamberger;
    
    @FXML
    private JFXDrawer drawer;
    
    
    @FXML
    private JFXButton btnEmployee;
    
    @FXML
    private VBox vbox;

    @FXML
    private JFXButton btnStatistic;
    
    @FXML
    private JFXButton btnLeaves;

    @FXML
    private JFXButton btnPaymentsheet;

    
    @FXML
    public void initialize(){
    	Image mainImagee = new Image("https://images.unsplash.com/photo-1518081461904-9d8f136351c2?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=8a815e999d0f593c8c8bbcb6473b0d39&auto=format&fit=crop&w=1377&q=80");
    	 imv.setImage(mainImagee);
    	 
    	 drawer.setSidePane(vbox);
    	 HamburgerBackArrowBasicTransition burgerTask = new HamburgerBackArrowBasicTransition(hamberger);
    	 burgerTask.setRate(-1);
    	 hamberger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
    		 burgerTask.setRate(burgerTask.getRate() * -1);
    		 burgerTask.play();
    		 
    		 if(drawer.isShown()){
    			 drawer.close();
    		 }else{
    			 drawer.open(); 
    		 }	 
    		 
    	 });	 
    }


    @FXML
    void employeeDash(ActionEvent event) {
    	try{
    		
    	    Parent root = FXMLLoader.load(getClass().getResource("EmployeeDashboard.fxml"));
    	    Scene scene = new Scene(root); 
    	    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  
    	    app_stage.setScene(scene);
    	    app_stage.show();
    	    }
    	    catch(IOException e)
    	         {
    	            System.out.println(e);
    	         }
    }
    

    @FXML
    void showStatistic(ActionEvent event) {
    	
    	try{
    		
    	    Parent root = FXMLLoader.load(getClass().getResource("Statistic.fxml"));
    	    Scene scene = new Scene(root); 
    	    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  
    	    app_stage.setScene(scene);
    	    app_stage.show();
    	    }
    	    catch(IOException e)
    	         {
    	            System.out.println(e);
    	         }
    	}
    

    @FXML
    void showLeaves(ActionEvent event) {
    	
    	try{
    		
    	    Parent root = FXMLLoader.load(getClass().getResource("../leaves/LeavesAllEmployees.fxml"));
    	    Scene scene = new Scene(root); 
    	    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  
    	    app_stage.setScene(scene);
    	    app_stage.show();
    	    }
    	    catch(IOException e)
    	         {
    	            System.out.println(e);
    	         }
    }
    

    @FXML
    void showPaymentsheet(ActionEvent event) {
    	
    	try{
    		
    	    Parent root = FXMLLoader.load(getClass().getResource("../paymentsheet/validatePaymentsheet.fxml"));
    	    Scene scene = new Scene(root); 
    	    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  
    	    app_stage.setScene(scene);
    	    app_stage.show();
    	    }
    	    catch(IOException e)
    	         {
    	            System.out.println(e);
    	         }
    
    }  
}
