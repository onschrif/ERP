package tn.esprit.b3.esprit1718b3erp.app.client.employee;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.esprit.b3.esprit1718b3erp.employeeservices.EmplServiceRemote;
import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

public class EmployeeController {
	
	private ObservableList<String> maritalList = FXCollections.observableArrayList("Single","Married","Divorced");
	private ObservableList<String> genderList = FXCollections.observableArrayList("Male","Female");
	private ObservableList<String> departmentList = FXCollections.observableArrayList("Production","Marketing","Accounting","Comunication","Logistics");

    @FXML
    private JFXButton btnDiscardE;

    @FXML
    private JFXButton btnSaveE;
    
    @FXML
    private JFXTextField banktxt;
    
    @FXML
    private JFXTextField salarytxt;

    @FXML
    private JFXTextField registNumtxt;

    @FXML
    private JFXTextField nationalitytxt;
    
    @FXML
    private JFXTextField nictxt;
    
    @FXML
    private JFXTextField fName;

    @FXML
    private JFXTextField lName;

    @FXML
    private JFXDatePicker dateofBirth;

    @FXML
    private JFXTextField agetxt;

    @FXML
    private ChoiceBox<String> maritaltxt;

    @FXML
    private JFXTextField emailtxt;

    @FXML
    private JFXTextField mobiletxt;

    @FXML
    private JFXTextField phonetxt;

    @FXML
    private JFXTextField addresstxt;

    @FXML
    private ChoiceBox<String> departmenttxt;

    @FXML
    private JFXTextField jobtxt;

    @FXML
    private JFXTextField managertxt;
    
    @FXML
    private ChoiceBox<String> genderboxtxt;
    
    List<Employee> employees;
    
    ServiceLocator s=ServiceLocator.getInstance();
	EmplServiceRemote ESR=(EmplServiceRemote) s.getProxy("esprit1718b3erp-ear/esprit1718b3erp-service/EmplService!tn.esprit.b3.esprit1718b3erp.employeeservices.EmplServiceRemote");
	
    
    @FXML
    public void initialize(){
    	maritaltxt.setValue("Single");
    	maritaltxt.setItems(maritalList);
    	
    	genderboxtxt.setValue("Male");
    	genderboxtxt.setItems(genderList);
    	
    	departmenttxt.setValue("Production");
    	departmenttxt.setItems(departmentList);
    	
    	employees = ESR.findallEmployees();
    }
    
    @FXML
    void addEmployee(ActionEvent event) {
    	if(isInputvalid()){
	    	Employee employee = new Employee();
	    	employee.setIdEmployee(registNumtxt.getText());
	    	employee.setNic(Integer.valueOf(nictxt.getText()));
	    	employee.setFirstName(fName.getText());
	    	employee.setLastName(lName.getText()); 
	    	employee.setGender(genderboxtxt.getValue());
	    	employee.setAge(Integer.valueOf(agetxt.getText()));
	    	employee.setMarital(maritaltxt.getValue());
	    	employee.setNationality(nationalitytxt.getText());
	    	employee.setEmail(emailtxt.getText());
	    	employee.setPhone(Integer.valueOf(phonetxt.getText()));
	    	employee.setMobile(Integer.valueOf(mobiletxt.getText()));
	    	employee.setAddress(addresstxt.getText());
	    	employee.setDepartment(departmenttxt.getValue());
	    	employee.setJobPosition(jobtxt.getText());
	    	employee.setManager(managertxt.getText());
	    	employee.setSalary(Float.valueOf(salarytxt.getText()));
	    	employee.setRib(Integer.valueOf(banktxt.getText()));
	    	ESR.addEmployee(employee);
	    	Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("SUCCESS");
    		alert.setHeaderText("SAVED");
    		alert.setContentText("Employee has been registered successfully");
    		alert.showAndWait();
    		clearFields();
    		showEmployeeDashboard(event);
    	}
    }
    
    @FXML
    void discardE(ActionEvent event) {
    	clearFields();
    	showEmployeeDashboard(event);
    }
    
    @FXML
    void showAge(MouseEvent event) {
    	Calendar now = Calendar.getInstance();
    	int year = now.get(Calendar.YEAR);
    	int birthday = dateofBirth.getValue().getYear();
    	int age = year - birthday;
    	agetxt.setText(Integer.toString(age));
    	
    }
    
    @FXML
    void showManager(MouseEvent event) {
    	if(departmenttxt.getValue().equalsIgnoreCase("Production")){
    		managertxt.setText("Maissa Amiri");
    	}
    	if(departmenttxt.getValue().equalsIgnoreCase("Marketing")){
    		managertxt.setText("Ashley Presley");
    	}
    	if(departmenttxt.getValue().equalsIgnoreCase("Accounting")){
    		managertxt.setText("Houcem Maiza");
    	}
    	if(departmenttxt.getValue().equalsIgnoreCase("Comunication")){
    		managertxt.setText("Antoine Langlais");
    	}
    	if(departmenttxt.getValue().equalsIgnoreCase("Logistics")){
    		managertxt.setText("Famke Jenssens");
    	}
    }
    
    public boolean isInputvalid(){
    	
    	String errorMessage = "";
    	
    	if(isIdExist()){
    		errorMessage += "Registration Number exists\n";
    	}
    	if(registNumtxt.getText() == null || registNumtxt.getText().length() == 0){
    		errorMessage += "No Valid Registration Number\n";
    	}
    	if(nictxt.getText() == null || nictxt.getText().length() == 0 || !nictxt.getText().matches("^[-+]?\\d+(\\.\\d+)?$")){
    		errorMessage += "No Valid National Identity Number\n";
    	}
    	if(fName.getText() == null || fName.getText().length() == 0){
    		errorMessage += "No Valid FirstName\n";
    	}
    	if(lName.getText() == null || lName.getText().length() == 0){
    		errorMessage += "No Valid Last Name\n";
    	}
    	if(nationalitytxt.getText() == null || nationalitytxt.getText().length() == 0){
    		errorMessage += "No Valid Nationality\n";
    	}
    	/*if(emailtxt.getText() == null || emailtxt.getText().length() == 0 || emailtxt.getText().indexOf('@') < 0 || emailtxt.getText().indexOf('.') < 0){
    		errorMessage += "No Valid Email\n";
    	}*/
    	if(emailtxt.getText() == null || emailtxt.getText().length() == 0 || !isEmailValid(emailtxt.getText())){
    		errorMessage += "No Valid Email\n";
    	}
    	if(mobiletxt.getText() == null || mobiletxt.getText().length() == 0 || (!mobiletxt.getText().matches("^[-+]?\\d+(\\.\\d+)?$"))){
    		errorMessage += "No Valid Mobile\n";
    	}
    	if(phonetxt.getText() == null || phonetxt.getText().length() == 0 || (!phonetxt.getText().matches("^[-+]?\\d+(\\.\\d+)?$"))){
    		errorMessage += "No Valid Phone\n";
    	}
    	if(addresstxt.getText() == null || addresstxt.getText().length() == 0){
    		errorMessage += "No Valid Address\n";
    	}
    	if(jobtxt.getText() == null || jobtxt.getText().length() == 0){
    		errorMessage += "No Valid Job Position\n";
    	}
    	if(managertxt.getText() == null || managertxt.getText().length() == 0){
    		errorMessage += "No Valid Manager\n";
    	}
    	if(salarytxt.getText() == null || salarytxt.getText().length() == 0 || (salarytxt.getText().matches("^[-+]?\\d+(\\.\\d+)?$") == false)){
    		errorMessage += "No Valid Salary\n";
    	}
    	if(banktxt.getText() == null || banktxt.getText().length() == 0 || (banktxt.getText().matches("^[-+]?\\d+(\\.\\d+)?$") == false)){
    		errorMessage += "No Valid Bank Account\n";
    	}

    	if(errorMessage.length() == 0){
    		return true;
    	}else{
    		
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERROR");
    		alert.setHeaderText("Invalid Input");
    		alert.setContentText(errorMessage);
    		alert.showAndWait();
    		return false;
    	}
    	
    }
    
    public boolean isIdExist(){
    	int count = 0;
    	for(Employee e : employees){
    		if(e.getIdEmployee().equalsIgnoreCase(registNumtxt.getText())){
    			count++;
    		}
    	}
    	
    	if(count != 0){
    		return true;
    	}else{
    		return false;
    	}
    	
    }
    
    public void clearFields(){
    	registNumtxt.setText(null);
    	nictxt.setText(null);
    	fName.setText(null);
    	lName.setText(null);
    	agetxt.setText(null);
    	nationalitytxt.setText(null);
    	emailtxt.setText(null);
    	phonetxt.setText(null);
    	mobiletxt.setText(null);
    	addresstxt.setText(null);
    	jobtxt.setText(null);
    	managertxt.setText(null);
    	salarytxt.setText(null);
    	banktxt.setText(null);
    }
    
    public boolean isEmailValid(String email){

        String REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
    public void showEmployeeDashboard(ActionEvent event){
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
}
