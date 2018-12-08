package tn.esprit.b3.esprit1718b3erp.app.client.login;


import java.awt.Color;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.event.ActionEvent;
/**
 *
 * @author Houcem
 */
public class Login extends Application {

    private static double xOffset	 = 0;
    private static double yOffset = 0;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));        
        stage.initStyle(StageStyle.TRANSPARENT);  
        Scene scene = new Scene(root);
        stage.setScene(scene);   
        scene.getStylesheets().addAll(Login.class.getResource("style.css").toExternalForm());
        javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResource("../assets/VM.png").toExternalForm());
        stage.getIcons().add(image);
        stage.setTitle("Verax Magus - An ERP just for you");
        scr(root,stage);
        stage.show();
    }

    public static void scr(Parent root,Stage stage)
    {
         root.setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        }
    });
    
    //move around here
    root.setOnMouseDragged(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        }
    });
    }
    

	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
