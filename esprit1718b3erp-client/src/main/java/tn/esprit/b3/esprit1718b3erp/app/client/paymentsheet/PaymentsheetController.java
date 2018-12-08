package tn.esprit.b3.esprit1718b3erp.app.client.paymentsheet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import javafx.scene.control.TableCell;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tn.esprit.b3.esprit1718b3erp.employeeservices.EmplServiceRemote;
import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.PaymentSheet;
import tn.esprit.b3.esprit1718b3erp.paymentsheetservices.PaymentsheetServiceRemote;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

public class PaymentsheetController {

	  @FXML
	  private JFXTextField idemployeetxt;

	  @FXML
	  private JFXButton btnApply;

	  @FXML
	  private JFXTextField fNametxt;

	  @FXML
	  private JFXTextField lNametxt;

	  @FXML
	  private JFXTextField departmenttxt;

	  @FXML
	  private JFXTextField salarytxt;

	  @FXML
	  private JFXTextField cnamtxt;

	  @FXML
	  private JFXTextField salarybrutetxt;
	  
	  @FXML
	  private JFXDatePicker paymentsheeteDate;

	  @FXML
	  private JFXButton validatebtn;
	  
	  @FXML
	  private TableView<PaymentSheet> paymentsheetTab;

	  @FXML
	  private TableColumn<PaymentSheet, Employee> fNameCol;

	  @FXML
	  private TableColumn<PaymentSheet, Employee> lastNameCol;

	  @FXML
	  private TableColumn<PaymentSheet, Date> dateCol;

	  @FXML
	  private TableColumn<PaymentSheet, Employee> salaryCol;

	  @FXML
	  private TableColumn<PaymentSheet, Float> cnamCol;

	  @FXML
	  private TableColumn<PaymentSheet, Float> salarybruteCol;

	  @FXML
	  private TableColumn<PaymentSheet, String> statusCol;
	 
	  @FXML
	  private JFXButton discardbtn;
	  
	  ServiceLocator s=ServiceLocator.getInstance();
	  EmplServiceRemote ESR=(EmplServiceRemote) s.getProxy("esprit1718b3erp-ear/esprit1718b3erp-service/EmplService!tn.esprit.b3.esprit1718b3erp.employeeservices.EmplServiceRemote");
	  PaymentsheetServiceRemote E=(PaymentsheetServiceRemote) s.getProxy("esprit1718b3erp-ear/esprit1718b3erp-service/PaymentsheetService!tn.esprit.b3.esprit1718b3erp.paymentsheetservices.PaymentsheetServiceRemote");
		 
	  private ObservableList<PaymentSheet> datap;
	  
	  @FXML
	  public void initialize(){
		  loadAllPaymentsheet();
	  }

	  @FXML
	  void apply(ActionEvent event) {
		if(isIdValid()){
		  Employee employee = ESR.searchById(idemployeetxt.getText());
		  fNametxt.setText(employee.getFirstName());
		  lNametxt.setText(employee.getLastName());
		  departmenttxt.setText(employee.getDepartment());
		  salarytxt.setText(String.valueOf(employee.getSalary()));
		  if(Float.valueOf(salarytxt.getText())<= 500){
			  cnamtxt.setText("50");
		  }else{
			  cnamtxt.setText("100");
		  }
		  salarybrutetxt.setText(String.valueOf(calculSalaryBrute(Float.valueOf(salarytxt.getText()), Float.valueOf(cnamtxt.getText()))));
		}
	  }
	  
	  float calculSalaryBrute(float salary, float cnam){
		  return salary - cnam;
	  }

	  @FXML
	  void validateP(ActionEvent event) {
		  Calendar calender = Calendar.getInstance();
		  calender.set(paymentsheeteDate.getValue().getYear(), paymentsheeteDate.getValue().getMonthValue(),paymentsheeteDate.getValue().getDayOfMonth());
		  Date date = calender.getTime();
		  Employee employee = ESR.searchById(idemployeetxt.getText());
		  PaymentSheet paymentsheet = new PaymentSheet();
		  paymentsheet.setDate(date);
		  paymentsheet.setCnam(Float.valueOf(cnamtxt.getText()));
		  paymentsheet.setSalaryBrute(Float.valueOf(salarybrutetxt.getText()));
		  paymentsheet.setStatus("Validated");
		  paymentsheet.setEmployee(employee);
		  E.addP(paymentsheet);
		  Alert alert = new Alert(AlertType.INFORMATION);
		  alert.setTitle("SUCCESS");
		  alert.setHeaderText("SAVED");
		  alert.setContentText("Employee "+paymentsheet.getEmployee().getFirstName()+" "+paymentsheet.getEmployee().getLastName()+" paymentsheet is validated successfully");
		  alert.showAndWait();
		  loadAllPaymentsheet();
		  clearFields();
	  }
	  

	  public boolean isIdValid(){
		  Employee employee = ESR.searchById(idemployeetxt.getText());
		  if(employee != null && idemployeetxt.getText().length() !=0){
			  return true;
		  }else{
			  Alert alert = new Alert(AlertType.ERROR);
			  alert.setTitle("ERROR");
			  alert.setHeaderText("Invalid Registration Number");
			  alert.setContentText("Please verify you registration number");
			  alert.showAndWait();
			  return false;
		  } 
	  }
	  public void loadAllPaymentsheet(){
		  datap = FXCollections.observableArrayList();
		  List<PaymentSheet> paymentsheets = E.findallPaymentsheet();
		  for(PaymentSheet p : paymentsheets){
			  datap.add(p);
		  }
		  dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		  cnamCol.setCellValueFactory(new PropertyValueFactory<>("cnam"));
		  salarybruteCol.setCellValueFactory(new PropertyValueFactory<>("salaryBrute"));
		  statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
		  salaryCol.setCellValueFactory(new PropertyValueFactory<>("employee"));
		  fNameCol.setCellValueFactory(new PropertyValueFactory<>("employee"));
		  lastNameCol.setCellValueFactory(new PropertyValueFactory<>("employee"));
		  salaryCol.setCellFactory(new Callback<TableColumn<PaymentSheet,Employee>, TableCell<PaymentSheet,Employee>>() {
				
				@Override
				public TableCell<PaymentSheet, Employee> call(TableColumn<PaymentSheet, Employee> param) {
			        final TableCell<PaymentSheet,Employee> cell = new TableCell<PaymentSheet,Employee>()
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
			                    this.setText(String.valueOf(item.getSalary()));
			                }
			            }
			        };
			        return cell;
				}
			});
		  fNameCol.setCellFactory(new Callback<TableColumn<PaymentSheet,Employee>, TableCell<PaymentSheet,Employee>>() {
				
				@Override
				public TableCell<PaymentSheet, Employee> call(TableColumn<PaymentSheet, Employee> param) {
			        final TableCell<PaymentSheet,Employee> cell = new TableCell<PaymentSheet,Employee>()
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
		  lastNameCol.setCellFactory(new Callback<TableColumn<PaymentSheet,Employee>, TableCell<PaymentSheet,Employee>>() {
				
				@Override
				public TableCell<PaymentSheet, Employee> call(TableColumn<PaymentSheet, Employee> param) {
			        final TableCell<PaymentSheet,Employee> cell = new TableCell<PaymentSheet,Employee>()
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
			 paymentsheetTab.setItems(null);
			 paymentsheetTab.setItems(datap);
	  }
	  
	  public void clearFields(){
		  idemployeetxt.setText(null);
		  fNametxt.setText(null);
		  lNametxt.setText(null);
		  departmenttxt.setText(null);
		  salarytxt.setText(null);
		  cnamtxt.setText(null);
		  salarybrutetxt.setText(null);
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
