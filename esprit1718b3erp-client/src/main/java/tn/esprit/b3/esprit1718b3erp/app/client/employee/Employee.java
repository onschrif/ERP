package tn.esprit.b3.esprit1718b3erp.app.client.employee;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Employee extends Application {

	@Override
	public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);   
        stage.setTitle("Human Resourcess Dashboard - Welcome");
        stage.show();	
	}

	public static void main(String[] args) {
		launch(args);
	}
}
