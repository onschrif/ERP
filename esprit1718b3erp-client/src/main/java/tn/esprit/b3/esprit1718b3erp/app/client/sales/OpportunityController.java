package tn.esprit.b3.esprit1718b3erp.app.client.sales;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import tn.esprit.b3.esprit1718b3erp.app.client.login.Login;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.ContactMangmentRemote;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.OrdersserviceRemote;
import tn.esprit.b3.esprit1718b3erp.employeeservices.EmplServiceRemote;
import tn.esprit.b3.esprit1718b3erp.entities.Opportunity;
import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.entities.Payement;
import tn.esprit.b3.esprit1718b3erp.entities.Assets;
import tn.esprit.b3.esprit1718b3erp.entities.Client;
import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.orderservices.OrderServiceRemote;
import tn.esprit.b3.esprit1718b3erp.salesservices.opportunityService;
import tn.esprit.b3.esprit1718b3erp.salesservices.opportunityServiceRemote;
import tn.esprit.b3.esprit1718b3erp.salesservices.salesServiceRemote;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

public class OpportunityController implements Initializable {
	@FXML
	AnchorPane opportunityPane;
	@FXML
	private TableColumn<Opportunity,String>col2;
	@FXML
	private TableColumn<Opportunity,String>col3;
	@FXML
	private TableColumn<Opportunity, String> col1, col4, col5, col6, col7;
	@FXML
	private Button btn1,btn2,btn3,btn4,btn5;
	@FXML
	JFXComboBox<String> statuscb,prog,comcb, ccb;
	@FXML
	TableView tbOps;
	static Boolean dash;
	@FXML
	DatePicker RecDate;
	
	
	
	ServiceLocator s = ServiceLocator.getInstance();
	opportunityServiceRemote OPSR = (opportunityServiceRemote)s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/opportunityService!tn.esprit.b3.esprit1718b3erp.salesservices.opportunityServiceRemote");
	salesServiceRemote SSR = (salesServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/salesService!tn.esprit.b3.esprit1718b3erp.salesservices.salesServiceRemote");
	ContactMangmentRemote client = (ContactMangmentRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/ContactMangmentService!tn.esprit.b3.esprit1718b3erp.conatactmangment.ContactMangmentRemote");
	OrderServiceRemote ASR = (OrderServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/OrderService!tn.esprit.b3.esprit1718b3erp.orderservices.OrderServiceRemote");
	OrdersserviceRemote CMRR=(OrdersserviceRemote) s.getProxy("esprit1718b3erp-ear/esprit1718b3erp-service/Ordersservice!tn.esprit.b3.esprit1718b3erp.conatactmangment.OrdersserviceRemote");
	EmplServiceRemote ESR = (EmplServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/EmplService!tn.esprit.b3.esprit1718b3erp.employeeservices.EmplServiceRemote");

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dash = false;
		statuscb.getItems().addAll(
			    "Won",
			    "On Progression",
			    "Lost"
			);
		prog.getItems().addAll(
			    "10",
			    "20",
			    "30",
			    "40",
			    "50",
			    "60",
			    "70",
			    "80",
			    "90"
			);
		try {
		for(int i=0;i<ESR.findAll().size();i++)
		{

			if(ESR.findAll().get(i).getJobPosition().equals("salesAgent"))
			comcb.getItems().add(ESR.findAll().get(i).getFirstName());
		}
		} catch (NullPointerException e) {
		}
		try {
			for (int i = 0; i < client.findAll().size(); i++) {
				ccb.getItems().add(client.findAll().get(i).getCompany());
			}
		} catch (NullPointerException e) {
		}

		
		oppLoad();
		btn5.setDisable(true);
		prog.setDisable(true);
		statuscb.setOnAction((e) -> {
			  if(statuscb.getSelectionModel().getSelectedItem().equals("On Progression") )
            {
             prog.setDisable(false);
            }
			  else
			  {
				  prog.setDisable(true);
			  }
			  
			  
	     
		});
		

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
	public void toOrders(ActionEvent event) throws IOException
	{
		trans("../sales/orders.fxml", event);
	    	
		}
	@FXML
	public void toDevis(ActionEvent event) throws IOException
	{
		trans("../sales/Devis.fxml", event);
	
		}
	
	@FXML
	public void dsh(ActionEvent event) {
		TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), opportunityPane);
		if (dash) {
			translateTransition1.setByX(-250);
			dash = false;
		} else {
			translateTransition1.setByX(+250);
			dash = true;
		}
		translateTransition1.play();
	}
	
	public void refresh() {
		tbOps.setItems(null);
		ObservableList<Opportunity> items = FXCollections.observableArrayList(OPSR.findAll());
		tbOps.setItems(items);

    }
	
	
	
	@FXML
	public void addOpportunity(ActionEvent action) throws ParseException {

		try {
		
			String s = comcb.getSelectionModel().getSelectedItem();
			//String sa = System.currentTimeMillis() + "";
			//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
			LocalDate localDate = LocalDate.now();
			
			
			
			
			//Opportunity opp1 = new Opportunity(RecDate.getValue().toString(),
				//	statuscb.getSelectionModel().getSelectedItem(),
					//Integer.valueOf(prog.getSelectionModel().getSelectedItem()),
					//OPSR.FindEmployeeByFirst(comcb.getSelectionModel().getSelectedItem()),
					//ASR.FindClientByCompany(ccb.getSelectionModel().getSelectedItem()));
			 
		   
			   if(statuscb.getSelectionModel().getSelectedItem().equals("Won") )
               {
               
            	Opportunity opp = new Opportunity(localDate.toString(),
    					statuscb.getSelectionModel().getSelectedItem(),
    					100,
    					OPSR.FindEmployeeByFirst(comcb.getSelectionModel().getSelectedItem()),
    					ASR.FindClientByCompany(ccb.getSelectionModel().getSelectedItem()));
            	opp.setCloseOpprtunityDate(LocalDate.now().toString());
            	OPSR.save(opp);
            	
               	
               }
			   
			   if(statuscb.getSelectionModel().getSelectedItem().equals("Lost") )
               {
              
            	Opportunity opp = new Opportunity(localDate.toString(),
    					statuscb.getSelectionModel().getSelectedItem(),
    					0,
    					OPSR.FindEmployeeByFirst(comcb.getSelectionModel().getSelectedItem()),
    					ASR.FindClientByCompany(ccb.getSelectionModel().getSelectedItem()));
            	opp.setCloseOpprtunityDate(LocalDate.now().toString());
            	OPSR.save(opp);
            	
               	
               }
			   
			   if(statuscb.getSelectionModel().getSelectedItem().equals("On Progression") )
               {
               
            	Opportunity opp = new Opportunity(localDate.toString(),
    					statuscb.getSelectionModel().getSelectedItem(),
    					Integer.valueOf(prog.getSelectionModel().getSelectedItem()),
    					OPSR.FindEmployeeByFirst(comcb.getSelectionModel().getSelectedItem()),
    					ASR.FindClientByCompany(ccb.getSelectionModel().getSelectedItem()));
            	opp.setCloseOpprtunityDate(LocalDate.now().toString());
            	OPSR.save(opp);
        
               	
               }
		   
				//	OPSR.save(opp);
			
		}

		catch (NumberFormatException E) {

		}

		refresh();
	}
	
	@FXML
	public void fill(MouseEvent event)
	{
		Opportunity op=OPSR.find(((Opportunity) tbOps.getSelectionModel().getSelectedItem()).getIdOpportunity());
        comcb.getSelectionModel().select(op.getE().getFirstName());
        ccb.getSelectionModel().select(op.getC().getCompany());
		statuscb.getSelectionModel().select(op.getOpoortunityStatus());
		prog.getSelectionModel().select(op.getProgression()+"");
		if(op.getOpoortunityStatus().equals("Won"))
		{
			btn5.setDisable(false);
          	btn5.setOnAction((action) -> {
          		try {
					Orders.setOpc(op.getC().getCompany());

					trans("../sales/orders.fxml", action);
				} catch (Exception e) {
					e.printStackTrace();
				}
    				});
		}
		else
		{
			btn5.setDisable(true);
		}
		
	}
	
	@FXML
	public void editOpportunity(ActionEvent action) throws ParseException {

		try {
		
			String s = comcb.getSelectionModel().getSelectedItem();
			//String sa = System.currentTimeMillis() + "";
			//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
			//LocalDate localDate = LocalDate.now();
			
			
			
			
			Opportunity opp = new Opportunity("",
					statuscb.getSelectionModel().getSelectedItem(),
					Integer.valueOf(prog.getSelectionModel().getSelectedItem()),
					OPSR.FindEmployeeByFirst(comcb.getSelectionModel().getSelectedItem()),
					ASR.FindClientByCompany(ccb.getSelectionModel().getSelectedItem()));
           			Opportunity as=OPSR.find(((Opportunity) tbOps.getSelectionModel().getSelectedItem()).getIdOpportunity());
                    opp.setIdOpportunity(as.getIdOpportunity());
                    
                    
                    if(statuscb.getSelectionModel().getSelectedItem().equals("Won") || statuscb.getSelectionModel().getSelectedItem().equals("Lost"))
                    {
                    	opp.setCloseOpprtunityDate(LocalDate.now().toString());   
                    	prog.setDisable(true);
                    	statuscb.setDisable(true);
                    	//RecDate.setDisable(true);
                    	ccb.setDisable(true);
                    	comcb.setDisable(true);
                    	
                    }
                        OPSR.update(opp);

		}

		catch (NumberFormatException E) {

		}

		refresh();
	}
	
	
	
	public void oppLoad() {
		col1.setCellValueFactory(new PropertyValueFactory<>("idOpportunity"));
		col2.setCellValueFactory(new Callback<CellDataFeatures<Opportunity, String>, ObservableValue<String>>() {

			public ObservableValue<String> call(CellDataFeatures<Opportunity, String> param) {
				return new SimpleStringProperty(param.getValue().getC().getCompany());
			}
		});		
		col3.setCellValueFactory(new Callback<CellDataFeatures<Opportunity, String>, ObservableValue<String>>() {

			public ObservableValue<String> call(CellDataFeatures<Opportunity, String> param) {
				return new SimpleStringProperty(param.getValue().getE().toString());
			}
		});	
		col4.setCellValueFactory(new PropertyValueFactory<>("startOpportunityDtae"));
		col5.setCellValueFactory(new PropertyValueFactory<>("CloseOpprtunityDate"));
		col6.setCellValueFactory(new PropertyValueFactory<>("opoortunityStatus"));
		col7.setCellValueFactory(new PropertyValueFactory<>("Progression"));
		tbOps.setItems(null);
		ObservableList<Opportunity> items = FXCollections.observableArrayList(OPSR.findAll());
		tbOps.setItems(items);
	}

	@FXML
	public void DeleteOpportunity(ActionEvent action) {

		try {
			
			OPSR.delete(OPSR.find(((Opportunity) tbOps.getSelectionModel().getSelectedItem()).getIdOpportunity()));
			
			refresh();
			
			Orders od=CMRR.find(1);
			System.out.println(od.toString());
			CMRR.delete(od);
		}

		catch (NumberFormatException E) {

		}
		
	}



}
