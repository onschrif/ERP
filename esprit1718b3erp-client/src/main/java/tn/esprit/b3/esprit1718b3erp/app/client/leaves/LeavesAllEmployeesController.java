package tn.esprit.b3.esprit1718b3erp.app.client.leaves;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import tn.esprit.b3.esprit1718b3erp.employeeservices.EmplServiceRemote;
import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Leaves;
import tn.esprit.b3.esprit1718b3erp.leavesservices.LeavesServiceRemote;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

public class LeavesAllEmployeesController {
	
	 @FXML
	 private TableView<Leaves> leavesalltab;

	 @FXML
	 private TableColumn<Leaves, Employee> fNameCol;

	 @FXML
	 private TableColumn<Leaves, Employee> lNameCol;

	 @FXML
	 private TableColumn<Leaves, Date> startdateCol;

	 @FXML
	 private TableColumn<Leaves, Date> finaldateCol;

	 @FXML
	 private TableColumn<Leaves, Integer> requesteddaysCol;

	 @FXML
	 private TableColumn<Leaves, Integer> restleavesCol;

	 @FXML
	 private TableColumn<Leaves, String> typeCol;

	 @FXML
	 private TableColumn<Leaves, String> descriptionCol;

	 @FXML
	 private TableColumn<Leaves, String> statusCol;
	 
	 @FXML
	 private JFXButton btndiscard;

	 @FXML
	 private JFXButton approvebtn;

	 @FXML
	 private JFXButton declinebtn;

	 @FXML
	 private JFXTextField filtertxt;
	 
	 private ObservableList<Leaves> data;
	 
	 ServiceLocator s=ServiceLocator.getInstance();
	 EmplServiceRemote ESR=(EmplServiceRemote) s.getProxy("esprit1718b3erp-ear/esprit1718b3erp-service/EmplService!tn.esprit.b3.esprit1718b3erp.employeeservices.EmplServiceRemote");
	 LeavesServiceRemote E=(LeavesServiceRemote) s.getProxy("esprit1718b3erp-ear/esprit1718b3erp-service/LeavesService!tn.esprit.b3.esprit1718b3erp.leavesservices.LeavesServiceRemote");
	 
	 
	 @FXML
	 public void initialize(){
		 loadLeavesFromDataBase();
		 filter();
	 }

	 @FXML
	 void approveLeave(ActionEvent event) {
		 Leaves leave = new Leaves();
		 leave = leavesalltab.getSelectionModel().getSelectedItem();
		 leave.setStatus("Approved");
		 E.updateL(leave);
		 Alert alert = new Alert(AlertType.INFORMATION);
		 alert.setTitle("SUCCESS");
		 alert.setHeaderText("SAVED");
		 alert.setContentText("Employee "+leave.getEmployee().getFirstName()+" "+leave.getEmployee().getLastName()+"'s request is approved successfully");
		 alert.showAndWait();
		 loadLeavesFromDataBase();
		 filter();
	 }

	 @FXML
	 void declineLeave(ActionEvent event) {
		 Leaves leave = new Leaves();
		 leave = leavesalltab.getSelectionModel().getSelectedItem();
		 leave.setStatus("Declined");
		 E.updateL(leave);
		 Alert alert = new Alert(AlertType.INFORMATION);
		 alert.setTitle("SUCCESS");
		 alert.setHeaderText("SAVED");
		 alert.setContentText("Employee "+leave.getEmployee().getFirstName()+" "+leave.getEmployee().getLastName()+" request is declined successfully");
		 alert.showAndWait();
		 loadLeavesFromDataBase();
		 filter();
	 }
	 
	 public void loadLeavesFromDataBase(){
		 data = FXCollections.observableArrayList();
		 List<Leaves> leaves = E.findAllLeaves();
		 for(Leaves l : leaves){
			 data.add(l);
		 }
		 fNameCol.setCellValueFactory(new PropertyValueFactory<>("employee"));
		 lNameCol.setCellValueFactory(new PropertyValueFactory<>("employee"));
		 fNameCol.setCellFactory(new Callback<TableColumn<Leaves,Employee>, TableCell<Leaves,Employee>>() {
			
			@Override
			public TableCell<Leaves, Employee> call(TableColumn<Leaves, Employee> param) {
		        final TableCell<Leaves,Employee> cell = new TableCell<Leaves,Employee>()
		        {
		            @Override
		            public void updateItem(final Employee item, boolean empty)
		            {
		                super.updateItem(item, empty);
		                if (empty)
		                {
		                    this.setText(""); 
		                }
		                else
		                {
		                    this.setText(item.getFirstName());
		                }
		            }
		        };
		        return cell;
			}
		});
		lNameCol.setCellFactory(new Callback<TableColumn<Leaves,Employee>, TableCell<Leaves,Employee>>() {
				
			@Override
			public TableCell<Leaves, Employee> call(TableColumn<Leaves, Employee> param) {
		        final TableCell<Leaves,Employee> cell = new TableCell<Leaves,Employee>()
		        {
		            @Override
		            public void updateItem(final Employee item, boolean empty)
		            {
		                super.updateItem(item, empty);
		                if (empty)
		                {
		                    this.setText(""); 
		                }
		                else
		                {
		                    this.setText(item.getLastName());
		                }
		            }
		        };
		        return cell;
			}
		});
		 startdateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
		 finaldateCol.setCellValueFactory(new PropertyValueFactory<>("finalDate"));
		 requesteddaysCol.setCellValueFactory(new PropertyValueFactory<>("daysRequested"));
		 restleavesCol.setCellValueFactory(new PropertyValueFactory<>("restOfLeaves"));
		 typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
		 descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
		 statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
		 leavesalltab.setItems(null);
    	 leavesalltab.setItems(data);
	 }
	 
	 public void filter(){
		 FilteredList<Leaves> filteredData = new FilteredList<>(data, p -> true);
	   	 	filtertxt.textProperty().addListener((observable, oldValue, newValue) -> {
	         filteredData.setPredicate(leaves -> {
	             
	             if (newValue == null || newValue.isEmpty()) {
	                 return true;
	             }
	             String lowerCaseFilter = newValue.toLowerCase();

	             if (leaves.getStatus().toLowerCase().contains(lowerCaseFilter)) {
	                 return true; 
	             } 
	             return false;
	         });
	     });
	   	SortedList<Leaves> sortedData = new SortedList<>(filteredData);
	   	sortedData.comparatorProperty().bind(leavesalltab.comparatorProperty());
	   	leavesalltab.setItems(sortedData);
	 }
	 
	 @FXML
	 void showDashboard(ActionEvent event) {
		 try{
	    		
			 Parent root = FXMLLoader.load(getClass().getResource("../employee/Dashboard.fxml"));
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
