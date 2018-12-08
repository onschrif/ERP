package tn.esprit.b3.esprit1718b3erp.app.client.employee;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.esprit.b3.esprit1718b3erp.employeeservices.EmplServiceRemote;
import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

public class EmployeeDashController {
	
    @FXML
    private TableView<Employee> EmployeesTab;
    
    @FXML
    private TableColumn<Employee, String> registNumCol;

    @FXML
    private TableColumn<Employee, String> fNameCol;
    
    @FXML
    private TableColumn<Employee, String> lNameCol;

    @FXML
    private TableColumn<Employee, String> addressCol;

    @FXML
    private TableColumn<Employee, Integer> mobileCol;

    @FXML
    private TableColumn<Employee, String> departmentCol;

    @FXML
    private TableColumn<Employee, String> jobCol;

    @FXML
    private TableColumn<Employee, String> managerCol;

    @FXML
    private TableColumn<Employee, String> bankCol;
    
    @FXML
    private JFXButton btnCreateE;

    @FXML
    private JFXButton btnEditE;

    @FXML
    private JFXButton btnDeleteE;
    
    @FXML
    private JFXButton btnSaveE;
    
    @FXML
    private JFXTextField fNametxt;

    @FXML
    private JFXTextField addresstxt;

    @FXML
    private JFXTextField departmenttxt;

    @FXML
    private JFXTextField managertxt;

    @FXML
    private JFXTextField lNametxt;

    @FXML
    private JFXTextField mobiletxt;

    @FXML
    private JFXTextField banktxt;

    @FXML
    private JFXTextField jobtxt;
    
    @FXML
    private JFXTextField input2;
    
    @FXML
    private JFXTextField input1;
    
    @FXML
    private ImageView home;
    
    
    private ObservableList<Employee> data;
    
    ServiceLocator t=ServiceLocator.getInstance();
  	EmplServiceRemote ESRR=(EmplServiceRemote) t.getProxy("esprit1718b3erp-ear/esprit1718b3erp-service/EmplService!tn.esprit.b3.esprit1718b3erp.employeeservices.EmplServiceRemote");
 
    @FXML
    public void initialize(){
    	loadFromDataBase();
    	filter();
    	Image homeImg = new Image("http://icons.iconarchive.com/icons/custom-icon-design/flatastic-8/256/Home-icon.png");
   	 	home.setImage(homeImg);
   	 	
    }
    
    @FXML
    void deleteE(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("DELETE");
    	alert.setHeaderText("DELETE EMPLOYEE");
    	alert.setContentText("Are you sure you want to delete the specified Employee?");
    	Optional<ButtonType> result = alert.showAndWait();
    	if(result.get() == ButtonType.OK){
        	Employee e = EmployeesTab.getSelectionModel().getSelectedItem();
        	data.remove(e);
        	ESRR.deleteE(e.getIdEmployee());
        	loadFromDataBase();
        	filter();
    	}
    	 	
    }
    
    @FXML
    void editE(ActionEvent event) {
    	Employee e = new Employee();
    	e = EmployeesTab.getSelectionModel().getSelectedItem();
    	fNametxt.setText(e.getFirstName());
    	lNametxt.setText(e.getLastName());
    	addresstxt.setText(e.getAddress());
    	mobiletxt.setText(String.valueOf(e.getMobile()));
    	departmenttxt.setText(e.getDepartment());
    	jobtxt.setText(e.getJobPosition());
    	managertxt.setText(e.getManager());
    	banktxt.setText(String.valueOf(e.getRib()));
    }
    
    @FXML
    void saveE(ActionEvent event) {
    	Employee e = new Employee();
    	e = EmployeesTab.getSelectionModel().getSelectedItem();
    	e.setFirstName(fNametxt.getText());
    	e.setLastName(lNametxt.getText());
    	e.setAddress(addresstxt.getText());
    	e.setMobile(Integer.valueOf(mobiletxt.getText()));
    	e.setDepartment(departmenttxt.getText());
    	e.setJobPosition(jobtxt.getText());
    	e.setManager(managertxt.getText());
    	e.setRib(Integer.valueOf(banktxt.getText()));
    	ESRR.updateE(e);
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("SUCCESS");
		alert.setHeaderText("SAVED");
		alert.setContentText("Employee has been updated successfully");
		alert.showAndWait();
		clearFields();
    	loadFromDataBase();
    	filter();
    	
    }
    
    @FXML
    void createE(ActionEvent event) {
    	showAddEmployee(event);
    }
    
    @FXML
    void showHRDash(MouseEvent event) {
    	try{
    		
    		Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
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
    
    
    public void loadFromDataBase(){
    	data = FXCollections.observableArrayList();
    	List<Employee> employees = ESRR.findallEmployees();
    	for(Employee e : employees){
	    	data.add(e);
    	}
    	 registNumCol.setCellValueFactory(new PropertyValueFactory<>("idEmployee"));
    	 fNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    	 lNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    	 addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
    	 mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	 departmentCol.setCellValueFactory(new PropertyValueFactory<>("department"));
    	 jobCol.setCellValueFactory(new PropertyValueFactory<>("jobPosition"));
    	 managerCol.setCellValueFactory(new PropertyValueFactory<>("manager"));
    	 bankCol.setCellValueFactory(new PropertyValueFactory<>("rib"));
    	 EmployeesTab.setItems(null);
    	 EmployeesTab.setItems(data);
    }
    
    public void clearFields(){

    	fNametxt.setText(null);
    	lNametxt.setText(null);
    	mobiletxt.setText(null);
    	addresstxt.setText(null);
    	departmenttxt.setText(null);
    	jobtxt.setText(null);
    	managertxt.setText(null);
    	banktxt.setText(null);
    }
    
    public void filter(){
    	FilteredList<Employee> filteredData = new FilteredList<>(data, p -> true);
   	 	input2.textProperty().addListener((observable, oldValue, newValue) -> {
         filteredData.setPredicate(employee -> {
             
             if (newValue == null || newValue.isEmpty()) {
                 return true;
             }

           
             String lowerCaseFilter = newValue.toLowerCase();

             if (employee.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                 return true; 
             } else if (employee.getDepartment().toLowerCase().contains(lowerCaseFilter)) {
                 return true; 
             }
             return false; 
         });
     });
   	 SortedList<Employee> sortedData = new SortedList<>(filteredData);
   	sortedData.comparatorProperty().bind(EmployeesTab.comparatorProperty());
   	EmployeesTab.setItems(sortedData);
    }
    
    public void showAddEmployee(ActionEvent event){
    	try{
    		
    	    Parent root = FXMLLoader.load(getClass().getResource("addEmployee.fxml"));
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


