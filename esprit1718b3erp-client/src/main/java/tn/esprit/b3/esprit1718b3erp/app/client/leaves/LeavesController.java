package tn.esprit.b3.esprit1718b3erp.app.client.leaves;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import tn.esprit.b3.esprit1718b3erp.employeeservices.EmplServiceRemote;
import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Leaves;
import tn.esprit.b3.esprit1718b3erp.entities.PaymentSheet;
import tn.esprit.b3.esprit1718b3erp.leavesservices.LeavesServiceRemote;
import tn.esprit.b3.esprit1718b3erp.paymentsheetservices.PaymentsheetServiceRemote;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

public class LeavesController {
	
	 @FXML
	 private JFXDatePicker startdate;

	 @FXML
	 private JFXDatePicker finaldate;

	 @FXML
	 private JFXTextField requesteddaystxt;

	 @FXML
	 private JFXTextField restofleavestxt;

	 @FXML
	 private JFXTextField typetxt;

	 @FXML
	 private JFXTextArea descriptiontxt;

	 @FXML
	 private JFXTextField idemployeetxt;
	 
	 @FXML
	 private TableView<Leaves> leavestab;

	 @FXML
	 private TableColumn<Leaves, Date> startdatetab;

	 @FXML
	 private TableColumn<Leaves, Date> finaldatetab;

	 @FXML
	 private TableColumn<Leaves, Integer> requesteddaystab;

	 @FXML
	 private TableColumn<Leaves, Integer> restofleavetab;

	 @FXML
	 private TableColumn<Leaves, String> typetab;
	 
	 @FXML
	 private TableColumn<Leaves, String> statustab;
	 
	 @FXML
	 private Label caption;
	 
	 @FXML
	 private PieChart piechart;

	 @FXML
	 private JFXButton saveLbtn;

	 @FXML
	 private JFXButton discardLbtn;
	 
	 //Paymensheet
	 @FXML
	 private TableView<PaymentSheet> paymentsheetTab;

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
	 
	 
	 
	 
	 private ObservableList<Leaves> data;
	 private ObservableList<PaymentSheet> datap;
	 
	 ObservableList<PieChart.Data>piechartData;
	 
	 ServiceLocator s=ServiceLocator.getInstance();
	 EmplServiceRemote ESR=(EmplServiceRemote) s.getProxy("esprit1718b3erp-ear/esprit1718b3erp-service/EmplService!tn.esprit.b3.esprit1718b3erp.employeeservices.EmplServiceRemote");
	 LeavesServiceRemote E=(LeavesServiceRemote) s.getProxy("esprit1718b3erp-ear/esprit1718b3erp-service/LeavesService!tn.esprit.b3.esprit1718b3erp.leavesservices.LeavesServiceRemote");
	 PaymentsheetServiceRemote P=(PaymentsheetServiceRemote) s.getProxy("esprit1718b3erp-ear/esprit1718b3erp-service/PaymentsheetService!tn.esprit.b3.esprit1718b3erp.paymentsheetservices.PaymentsheetServiceRemote");
		
	 
	 List<Leaves> leaves;
	 private List<PaymentSheet> paymentsheets;
		
	 @FXML
	 public void initialize(){
		 leaves = E.findAllLeaves();
		 paymentsheets = P.findallPaymentsheet();
	 }

	 @FXML
	 void addLeave(ActionEvent event) {
		 if(isIdValid()){
			Calendar calender1 = Calendar.getInstance();
			calender1.set(startdate.getValue().getYear(), startdate.getValue().getMonthValue(),startdate.getValue().getDayOfMonth());
			Date startDate = calender1.getTime();
			Calendar calender2 = Calendar.getInstance();
			calender2.set(finaldate.getValue().getYear(), finaldate.getValue().getMonthValue(),finaldate.getValue().getDayOfMonth());
			Date finalDate = calender2.getTime();
			Employee employee = ESR.searchById(idemployeetxt.getText());
			Leaves leave = new Leaves(); 
			leave.setRestOfLeaves(Integer.valueOf(restofleavestxt.getText()));
			leave.setDaysRequested(Integer.valueOf(requesteddaystxt.getText()));
			leave.setType(typetxt.getText());
			leave.setDescription(descriptiontxt.getText());
			leave.setStartDate(startDate);
			leave.setFinalDate(finalDate);
			leave.setStatus("To be approved");
			leave.setEmployee(employee);
			if(Integer.valueOf(restofleavestxt.getText()) >= 0){
				E.addLeaves(leave);
			    Alert alert = new Alert(AlertType.INFORMATION);
			    alert.setTitle("SUCCESS");
			    alert.setHeaderText("SAVED");
			    alert.setContentText("You Request is currenty being verified");
			    alert.showAndWait();
			    loadLeave();
			    statistic();
			    clearFields();
			 }else{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setHeaderText("Invalid Leave");
				alert.setContentText("Your requested days off is invalid");
				alert.showAndWait();
			 }	 
		 }
	 }
	 
	 @FXML
	 void discardLeave(ActionEvent event) {
		 clearFields();
	 }
	 
	 @FXML
	 void apply(ActionEvent event) {
		 if(isIdValid()){
			 loadLeave();
			 statistic();
			 loadPaymensheet();
		 }
		 
	 }
	 
	 @FXML
	 void showRequestedDays(MouseEvent event) {
		 
		 Calendar calender = Calendar.getInstance();
		 calender.set(startdate.getValue().getYear(), startdate.getValue().getMonthValue(),startdate.getValue().getDayOfMonth());
		 Date startDate = calender.getTime();
		 calender.set(finaldate.getValue().getYear(), finaldate.getValue().getMonthValue(),finaldate.getValue().getDayOfMonth());
		 Date finalDate = calender.getTime();
		 long diff = finalDate.getTime() - startDate.getTime();
		 long diffDays = diff / (24 * 60 * 60 * 1000)+1;
		 int daysdiff = (int) diffDays;
		 requesteddaystxt.setText(String.valueOf(daysdiff));
		 
	 }
	 
	 @FXML
	 void showRestOfLeaves(MouseEvent event) {
		 
		 int restLeaves = InitilizeRestOfLeaves();
		 int requestedDays = Integer.valueOf(requesteddaystxt.getText());
		 restofleavestxt.setText(String.valueOf(restLeaves - requestedDays ));
		 
	 }
	 
	 public int InitilizeRestOfLeaves(){
		 
		 int init = 0;
		 Calendar now = Calendar.getInstance();
		 int day = now.get(Calendar.MONTH);
		 int month = now.get(Calendar.DATE);
		 List<Leaves> list =  new ArrayList<>();
		 
		 for(Leaves leave : leaves){
			 if(leave.getEmployee().getIdEmployee().equalsIgnoreCase(idemployeetxt.getText())){
				 list.add(leave);
			 } 
		 }
		 if(!list.isEmpty()){
			 if(day == 1 && month == 1){
				 Leaves l = list.get(list.size() - 1);
				 init = l.getRestOfLeaves() + 30; 
			 }else{
				 Leaves l = list.get(list.size() - 1);
				 init = l.getRestOfLeaves(); 
			 } 
		 }else{
			 init = 30;
		 }

		 return init;
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
	 
	 public void clearFields(){
		 startdate.setValue(null);
		 finaldate.setValue(null);
		 requesteddaystxt.setText(null);
		 restofleavestxt.setText(null);
		 typetxt.setText(null);
		 descriptiontxt.setText(null);
	 }
	 public void loadLeave(){
		 leaves = E.findAllLeaves();
		 data = FXCollections.observableArrayList();
		 for(Leaves leave : leaves){
			 if(leave.getEmployee().getIdEmployee().equalsIgnoreCase(idemployeetxt.getText())){
				 data.add(leave);
			 }
		 }
		 startdatetab.setCellValueFactory(new PropertyValueFactory<>("startDate"));
		 finaldatetab.setCellValueFactory(new PropertyValueFactory<>("finalDate"));
		 requesteddaystab.setCellValueFactory(new PropertyValueFactory<>("daysRequested"));
		 restofleavetab.setCellValueFactory(new PropertyValueFactory<>("restOfLeaves"));
		 typetab.setCellValueFactory(new PropertyValueFactory<>("type"));
		 statustab.setCellValueFactory(new PropertyValueFactory<>("status"));
		 leavestab.setItems(null);
		 leavestab.setItems(data);
	 }
	 
	 public void statistic(){
		 piechartData = FXCollections.observableArrayList();
		 int count1 = 0;
		 int count2 = 0;
		 int count3 = 0;
		 List<Leaves> list = new ArrayList<Leaves>();
		 for(Leaves leave : leaves){
			 if(leave.getEmployee().getIdEmployee().equalsIgnoreCase(idemployeetxt.getText())){
				 list.add(leave);
			 }
		 }
		 if(!list.isEmpty()){
			 for(Leaves l : list){
				 if(l.getStatus().equalsIgnoreCase("To be approved")){
					 count1++;
				 }
				 if(l.getStatus().equalsIgnoreCase("Approved")){
					 count2++;
				 }
				 if(l.getStatus().equalsIgnoreCase("Declined")){
					 count3++;
				 }
			 }
			 piechartData.addAll(
					 new PieChart.Data("To be approved", count1),
					 new PieChart.Data("Approved", count2),
					 new PieChart.Data("Declined", count3)
					 );
			 piechart.setData(piechartData); 
			 caption.setTextFill(Color.DARKORANGE);
			 caption.setStyle("-fx-font: 24 arial;");
			 for (final PieChart.Data data : piechart.getData()) {
				 data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
						 new EventHandler<MouseEvent>() {
					 @Override public void handle(MouseEvent e) {
						 double total = 0;
						 for (PieChart.Data d : piechart.getData()) {
							 total += d.getPieValue();
						 }
						 String text = String.format("%.1f%%", 100*data.getPieValue()/total) ;
						 caption.setText(text);
					 }
				 });
			 }			 
		 }else{
			 Alert alert = new Alert(AlertType.ERROR);
			 alert.setTitle("Warning");
			 alert.setHeaderText("You don't have any leaves yet");
			 alert.showAndWait();
		 }
	 }
	 
	 public void loadPaymensheet(){
		 datap = FXCollections.observableArrayList();
		 for(PaymentSheet paymentsheet : paymentsheets){
			 if(paymentsheet.getEmployee().getIdEmployee().equalsIgnoreCase(idemployeetxt.getText())){
				 datap.add(paymentsheet);
			 }
		 }
		 dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		 cnamCol.setCellValueFactory(new PropertyValueFactory<>("cnam"));
		 salarybruteCol.setCellValueFactory(new PropertyValueFactory<>("salaryBrute"));
		 statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
		 salaryCol.setCellValueFactory(new PropertyValueFactory<>("employee"));
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
		 paymentsheetTab.setItems(null);
		 paymentsheetTab.setItems(datap);
	 }

}

