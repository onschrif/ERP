package tn.esprit.b3.esprit1718b3erp.app.client.employee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import tn.esprit.b3.esprit1718b3erp.app.client.login.Login;
import tn.esprit.b3.esprit1718b3erp.employeeservices.EmplServiceRemote;
import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Leaves;
import tn.esprit.b3.esprit1718b3erp.leavesservices.LeavesServiceRemote;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

public class DashboardController {
    @FXML
    private AnchorPane dashpane;
	static Boolean dash = false;

    @FXML
    private Label lbTitulo;

    @FXML
    private ToggleButton btCatalogacao;

    @FXML
    private ToggleGroup grupoMenus;

    @FXML
    private VBox boxCatalogacao;

    @FXML
    private ToggleButton btCatalogar;

    @FXML
    private ToggleGroup grupoCatalogacao;

    @FXML
    private ToggleButton btEstratigrafia;

    @FXML
    private ToggleButton btColecao;

    @FXML
    private ToggleButton btColecao1;

    @FXML
    private ToggleButton btVisitas;

    @FXML
    private VBox boxVisitas;

    @FXML
    private ToggleButton btEmprestimos;

    @FXML
    private VBox boxEmprestimo;

    @FXML
    private ToggleButton btUtilitarios;

    @FXML
    private Label lbUser;
    
    @FXML
    private Label employeecount;
    
    @FXML
    private Label malestaff;
    
    @FXML
    private Label femalestaff;
    
    @FXML
    private PieChart piechartDepartment;
    
    @FXML
    private PieChart piechartGender;
    
    @FXML
    private PieChart piechartMarital;
    
    @FXML
    private BarChart<String, Integer> barchart;

    @FXML
    private CategoryAxis agex;

    @FXML
    private NumberAxis employeesy;
    
    @FXML
    private StackedBarChart<String, Integer> stackedbarchart;

    @FXML
    private CategoryAxis xdepartment;

    @FXML
    private NumberAxis yemployeess;

    @FXML
    private Label caption;
    
    @FXML
    private TableView<Employee> salary;

    @FXML
    private TableColumn<Employee, String> fNameCol;

    @FXML
    private TableColumn<Employee, String> lNameCol;

    @FXML
    private TableColumn<Employee, String> positionCol;

    @FXML
    private TableColumn<Employee, Float> salaryCol;

    @FXML
    private Label total;

    ServiceLocator s=ServiceLocator.getInstance();
   	EmplServiceRemote ESR=(EmplServiceRemote) s.getProxy("esprit1718b3erp-ear/esprit1718b3erp-service/EmplService!tn.esprit.b3.esprit1718b3erp.employeeservices.EmplServiceRemote");
    LeavesServiceRemote E=(LeavesServiceRemote) s.getProxy("esprit1718b3erp-ear/esprit1718b3erp-service/LeavesService!tn.esprit.b3.esprit1718b3erp.leavesservices.LeavesServiceRemote");
	 
   	List<Employee> employees;
   	List<Leaves> leaves;
   	
   	private ObservableList<Employee> data;
   	ObservableList<PieChart.Data>piechartData;
   	XYChart.Series set;
    
    @FXML
    public void initialize(){
    	dash = false;
    	employees = ESR.findallEmployees();
    	leaves = E.findAllLeaves();
    	employeecount.setText(String.valueOf(employeeCount()));
    	malestaff.setText(String.valueOf(maleStaff()));
    	femalestaff.setText(String.valueOf(femaleStaff()));
    	statisticDepartment();
    	statisticGender();
    	statisticAge();
    	statisticMarital();
    	leaveAmountByDepartment();
    	loadEmployees();
    	total.setText(String.valueOf(calculSalary()));
    }
    
    public int employeeCount(){
    	int count = 0;
    	for(Employee e : employees){
    		count ++;
    	}
    	return count;
    }
    
    public int maleStaff(){
    	int count = 0;
    	for(Employee e : employees){
    		if(e.getGender().equalsIgnoreCase("Male")){
    			count++;
    		}
    	}
    	return count;
    }
    
    public int femaleStaff(){
    	int count = 0;
    	for(Employee e : employees){
    		if(e.getGender().equalsIgnoreCase("Female")){
    			count++;
    		}
    	}
    	return count;
    }
    
    public void statisticDepartment(){
    	int production = 0;
    	int marketing = 0;
    	int accounting = 0;
    	int comunication = 0;
    	int logistics = 0;
    	piechartData = FXCollections.observableArrayList();
    	for(Employee e : employees){
    		if(e.getDepartment().equalsIgnoreCase("Production")){
    			production++;
    		}
    		if(e.getDepartment().equalsIgnoreCase("Marketing")){
    			marketing++;
    		}
    		if(e.getDepartment().equalsIgnoreCase("Accounting")){
    			accounting++;
    		}
    		if(e.getDepartment().equalsIgnoreCase("Comunication")){
    			comunication++;
    		}
    		if(e.getDepartment().equalsIgnoreCase("Logistics")){
    			logistics ++;
    		}
    	}
    	piechartData.addAll(
    			new PieChart.Data("Production", production),
    			new PieChart.Data("Marketing", marketing),
    			new PieChart.Data("Accounting", accounting),
    			new PieChart.Data("Comunication", comunication),
    			new PieChart.Data("Logistics", logistics)
    			);
    	piechartDepartment.setData(piechartData);
    	percentage(piechartDepartment);
    }
    public void statisticAge(){
    	set = new XYChart.Series<>();
    	int a = 0;
    	int b = 0;
    	int c = 0;
    	int d = 0;
    	
    	for(Employee e : employees){
    		
    		if(e.getAge() >= 20 && e.getAge() < 30){
    			a++;
    		}
    		if(e.getAge() >= 30 && e.getAge() < 40){
    			b++;
    		}
    		if(e.getAge() >= 40 && e.getAge() < 50){
    			c++;
    		}
    		if(e.getAge() >= 50 && e.getAge() < 60){
    			d++;
    		}
    	}
    	
    	set.getData().addAll(
    			new XYChart.Data("20<= Age <30", a),
    			new XYChart.Data("30<= Age <40", b),
    			new XYChart.Data("40<= Age <50", c),
    			new XYChart.Data("50<= Age <60", d));
    	
    	barchart.getData().add(set);
    }
    
    public void statisticGender(){
    	piechartData = FXCollections.observableArrayList();
    	int countf = 0;
    	int countm = 0;
    	
    	for(Employee e : employees){
    		
    		if(e.getGender().equalsIgnoreCase("Female")){
    			countf++;
    		}else{
    			countm++;
    		}
    	}
    	
    	piechartData.addAll(
    			new PieChart.Data("Female", countf),
    			new PieChart.Data("Male", countm)
    			);
    	piechartGender.setData(piechartData);
    	percentage(piechartGender);	 	
    }
    
    public void statisticMarital(){
    	piechartData = FXCollections.observableArrayList();
    	int countf = 0;
    	int countm = 0;
    	int countn = 0;
    	
    	for(Employee e : employees){
    		
    		if(e.getMarital().equalsIgnoreCase("Single")){
    			countf++;
    		}
    		if(e.getMarital().equalsIgnoreCase("Married")){
    			countm++;
    		}
    		if(e.getMarital().equalsIgnoreCase("Divorced")){
    			countn++;
    		}
    	}
    	
    	piechartData.addAll(
    			new PieChart.Data("Single", countf),
    			new PieChart.Data("Married", countm),
    			new PieChart.Data("Divorced", countn)
    			);
    	piechartMarital.setData(piechartData);
    	percentage(piechartMarital);	
    	
    }
    
    public void leaveAmountByDepartment(){
    	int a1 = 0;
    	int s1 = 0;
    	int an1 = 0;
    	int a2 = 0;
    	int s2 = 0;
    	int an2 = 0;
    	int a3 = 0;
    	int s3 = 0;
    	int an3 = 0;
    	int a4 = 0;
    	int s4 = 0;
    	int an4 = 0;
    	int a5 = 0;
    	int s5 = 0;
    	int an5 = 0;
    	List<Leaves> list1 = new ArrayList<>();
    	List<Leaves> list2 = new ArrayList<>();
    	List<Leaves> list3 = new ArrayList<>();
    	List<Leaves> list4 = new ArrayList<>();
    	List<Leaves> list5 = new ArrayList<>();
    	for(Leaves e : leaves){
    		String department =  e.getEmployee().getDepartment();
    		String status = e.getStatus();
    		if(status.equalsIgnoreCase("Approved")){
    			if(department.equalsIgnoreCase("Accounting")){
        			list1.add(e);
        		}
        		if(department.equalsIgnoreCase("Production")){
        			list2.add(e);
        		}
        		if(department.equalsIgnoreCase("Marketing")){
        			list3.add(e);
        		}
        		if(department.equalsIgnoreCase("Comunication")){
        			list4.add(e);
        		}
        		if(department.equalsIgnoreCase("Logistics")){
        			list5.add(e);
        		}
    		}
    	}
    	for(Leaves e : list1){
    		if(e.getType().equalsIgnoreCase("Annual Leave")){
    			a1++;
    		}
    		if(e.getType().equalsIgnoreCase("Sick Leave")){
    			s1++;
    		}
    		if(e.getType().equalsIgnoreCase("Anniversary Leave")){
    			an1++;
    		}
    	}
    	for(Leaves e : list2){
    		if(e.getType().equalsIgnoreCase("Annual Leave")){
    			a2++;
    		}
    		if(e.getType().equalsIgnoreCase("Sick Leave")){
    			s2++;
    		}
    		if(e.getType().equalsIgnoreCase("Anniversary Leave")){
    			an2++;
    		}
    	}
    	for(Leaves e : list3){
    		if(e.getType().equalsIgnoreCase("Annual Leave")){
    			a3++;
    		}
    		if(e.getType().equalsIgnoreCase("Sick Leave")){
    			s3++;
    		}
    		if(e.getType().equalsIgnoreCase("Anniversary Leave")){
    			an3++;
    		}
    	 }
    	for(Leaves e : list4){
    		if(e.getType().equalsIgnoreCase("Annual Leave")){
    			a4++;
    		}
    		if(e.getType().equalsIgnoreCase("Sick Leave")){
    			s4++;
    		}
    		if(e.getType().equalsIgnoreCase("Anniversary Leave")){
    			an4++;
    		}
    	 }
    	for(Leaves e : list5){
    		if(e.getType().equalsIgnoreCase("Annual Leave")){
    			a5++;
    		}
    		if(e.getType().equalsIgnoreCase("Sick Leave")){
    			s5++;
    		}
    		if(e.getType().equalsIgnoreCase("Anniversary Leave")){
    			an5++;
    		}
    	 }
    	xdepartment.setCategories(FXCollections.<String>observableArrayList(Arrays.asList
    	         ("Accounting", "Production", "Marketing", "Comunication", "Logistics"))); 
        XYChart.Series<String, Integer> series1 = new XYChart.Series<>();  
        series1.setName("Annual Leave"); 
        series1.getData().add(new XYChart.Data<>("Accounting", a1)); 
        series1.getData().add(new XYChart.Data<>("Production", a2));  
        series1.getData().add(new XYChart.Data<>("Marketing", a3)); 
        series1.getData().add(new XYChart.Data<>("Comunication", a4)); 
        series1.getData().add(new XYChart.Data<>("Logistics", a5)); 
           
        XYChart.Series<String, Integer> series2 = new XYChart.Series<>(); 
        series2.setName("Sick Leave"); 
        series2.getData().add(new XYChart.Data<>("Accounting", s1)); 
        series2.getData().add(new XYChart.Data<>("Production", s2));  
        series2.getData().add(new XYChart.Data<>("Marketing", s3)); 
        series2.getData().add(new XYChart.Data<>("Comunication", s4)); 
        series2.getData().add(new XYChart.Data<>("Logistics", s5)); 
        
        XYChart.Series<String, Integer> series3 = new XYChart.Series<>(); 
        series3.setName("Anniversary Leave"); 
        series3.getData().add(new XYChart.Data<>("Accounting", an1)); 
        series3.getData().add(new XYChart.Data<>("Production", an2));  
        series3.getData().add(new XYChart.Data<>("Marketing", an3)); 
        series3.getData().add(new XYChart.Data<>("Comunication", an4)); 
        series3.getData().add(new XYChart.Data<>("Logistics", an5));
        
        stackedbarchart.getData().addAll(series1, series2, series3); 
    }
    
    public void percentage(PieChart x){
    	caption.setTextFill(Color.DARKORANGE);
    	caption.setStyle("-fx-font: 24 arial;");
    	for (final PieChart.Data data : x.getData()) {
    	    data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
    	        new EventHandler<MouseEvent>() {
    	            @Override public void handle(MouseEvent e) {
                        double total = 0;
                        for (PieChart.Data d : x.getData()) {
                            total += d.getPieValue();
                        }
                        String text = String.format("%.1f%%", 100*data.getPieValue()/total) ;
                        caption.setText(text);
    	             }
    	        });
    	}
    }
    
    public void loadEmployees(){
    	data = FXCollections.observableArrayList();
    	List<Employee> employees = ESR.findallEmployees();
    	for(Employee e : employees){
	    	data.add(e);
    	}
    	 fNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    	 lNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    	 positionCol.setCellValueFactory(new PropertyValueFactory<>("jobPosition"));
    	 salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));
    	 salary.setItems(null);
    	 salary.setItems(data);
    }
    
    public float calculSalary(){
    	float total = 0;
    	for(Employee e : employees){
    		total += e.getSalary();
    	}
    	return total;
    }
    
    @FXML
    void close(ActionEvent event) {
    	Platform.exit();
    }

    @FXML
    void dsh(ActionEvent event) {
    	TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), dashpane);
		if (dash) {
			translateTransition1.setByX(-250);
			dash = false;
		} else {
			translateTransition1.setByX(+250);
			dash = true;
		}
		translateTransition1.play();
    }

    @FXML
    void home(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(this.getClass().getResource("../employee/Dashboard.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().addAll(Login.class.getResource("style.css").toExternalForm());
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(scene);
		Login.scr(root, app_stage);
		app_stage.show();
    }

    @FXML
    void minimize(ActionEvent event) {
		Node source = (Node) event.getSource();
		Window theStage = source.getScene().getWindow();
		((Stage) theStage).setIconified(true);
    }

	public void trans(String s, ActionEvent event) throws IOException {
		{
			Parent root = FXMLLoader.load(this.getClass().getResource(s));
			Scene scene = new Scene(root);
			scene.getStylesheets().addAll(Login.class.getResource("style.css").toExternalForm());
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(scene);
			Login.scr(root, app_stage);
			app_stage.show();
		}
	}
    @FXML
    void toAssets(ActionEvent event) throws IOException {
    	trans("../paymentsheet/validatePaymentsheet.fxml", event);
    }

    @FXML
    void toBalance(ActionEvent event) throws IOException {
    	
    }

    @FXML
    void toBanking(ActionEvent event) {

    }

    @FXML
    void toCash(ActionEvent event) throws IOException {
    	trans("../leaves/LeavesAllEmployees.fxml", event);
    }

    @FXML
    void toExp(ActionEvent event) {

    }

    @FXML
    void toInv(ActionEvent event) throws IOException {
    	trans("../employee/EmployeeDashboard.fxml", event);
    }

    @FXML
    void toTax(ActionEvent event) {

    }
}
