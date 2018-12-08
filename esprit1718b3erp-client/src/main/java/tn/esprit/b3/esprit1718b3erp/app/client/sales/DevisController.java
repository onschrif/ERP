package tn.esprit.b3.esprit1718b3erp.app.client.sales;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import tn.esprit.b3.esprit1718b3erp.app.client.login.Login;
import tn.esprit.b3.esprit1718b3erp.entities.BillOfMaterial;
import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.orderservices.OrderServiceRemote;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

public class DevisController implements Initializable {
	ServiceLocator s = ServiceLocator.getInstance();
	@FXML
	JFXComboBox<String> refc;
	@FXML
	private Button bt1;
	@FXML
	private GridPane gridP;
	@FXML
	AnchorPane DevisPane;
	@FXML
	Pane tablepane1;
	@FXML
	Label total,quotation,ref,clientLabel;
	static Boolean dash;

	// int size=0;
 
	// static int n = 5;
	// int r = n - 5;

	OrderServiceRemote ASR = (OrderServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/OrderService!tn.esprit.b3.esprit1718b3erp.orderservices.OrderServiceRemote");

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dash=false;
		refc.getItems().addAll(ASR.FindOrderByReference());
		refc.setOnAction((e) -> {

			// List<Orders> lorders = new ArrayList<>();
			// lorders
			// =ASR.FindOrdrsByReference1((String)refc.getSelectionModel().getSelectedItem());

		});

	}
	
	@FXML
	public void toOrders(ActionEvent event) throws IOException
	{
		trans("../sales/orders.fxml", event);
	
	}
	
	@FXML
	public void toOpportunty(ActionEvent event) throws IOException
	{
		trans("../sales/opportunity.fxml", event);
	
	}
	@FXML
	public void close(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	public void minimize(ActionEvent event) {
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
	public void generate(ActionEvent event) {
		gridP.getChildren().clear();
		float total1 = 0;
		LocalDate localDate = LocalDate.now();
		quotation.setText(localDate.toString());
		ref.setText(refc.getSelectionModel().getSelectedItem());
		clientLabel.setText(ASR.FindOrdrsByReference1(refc.getSelectionModel().getSelectedItem()).get(0).getC().getCompany());
		for (int i = 0; i < ASR.FindOrdrsByReference1(refc.getSelectionModel().getSelectedItem()).size(); i++) {
			Label l1 = new Label(
					ASR.FindOrdrsByReference1(refc.getSelectionModel().getSelectedItem()).get(i).getP().getName());

			Label l2 = new Label(
					ASR.FindOrdrsByReference1(refc.getSelectionModel().getSelectedItem()).get(i).getQuantityToOrder()
							+ "");
			Label l3 = new Label(
					ASR.FindOrdrsByReference1(refc.getSelectionModel().getSelectedItem()).get(i).getP().getPrice()
							+ "");
			Label l4 = new Label(
					ASR.FindOrdrsByReference1(refc.getSelectionModel().getSelectedItem()).get(i).getOrderAmount() + "");
			l4.setText(ASR.FindOrdrsByReference1(refc.getSelectionModel().getSelectedItem()).get(i).getOrderAmount() + "");
			total1 += ASR.FindOrdrsByReference1(refc.getSelectionModel().getSelectedItem()).get(i).getOrderAmount();
			// total1 =(float) (total1
			// +(ASR.FindOrdrsByReference1(refc.getSelectionModel().getSelectedItem()).get(i).getQuantityToOrder()*ASR.FindOrdrsByReference1(refc.getSelectionModel().getSelectedItem()).get(i).getP().getPrice()));
			String tot = String.valueOf(total1);

			Pane p = new Pane();
			p.getChildren().add(l1);
			p.getChildren().add(l2);
			p.getChildren().add(l3);
			p.getChildren().add(l4);

			l1.setLayoutX(60);
			l1.setLayoutY(20);
			l2.setLayoutX(299);
			l2.setLayoutY(20);
			l3.setLayoutX(548);
			l3.setLayoutY(20);
			l4.setLayoutX(757);
			l4.setLayoutY(20);
			l4.setFont(new Font("Arial", 12));
			l1.setFont(new Font("Arial", 12));
			l2.setFont(new Font("Arial", 12));
			l3.setFont(new Font("Arial", 12));
			gridP.addRow(i, p);
			total.setText(tot+" TND");

		}
	}

	@FXML
	public void dsh(ActionEvent event) {
		TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5),DevisPane);
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
		public void logout(ActionEvent event) throws IOException {
			trans("../login/Login.fxml", event);
		}
	  
	  void notf(String s, String s2) {
			Notifications notificationbuilder = Notifications.create().title(s).text(s2).hideAfter(Duration.seconds(5))
					.position(Pos.BOTTOM_RIGHT);
			notificationbuilder.darkStyle();
			notificationbuilder.show();
		}
	  
	  int c =0;
		
		@FXML
		public void convert (ActionEvent event) {

				 try{
					 
					 LocalDate localDate1 = LocalDate.now();
					 Document document = new Document();
					 String date= localDate1.toString();
					 String reference =refc.getSelectionModel().getSelectedItem();
					 String company =ASR.FindOrdrsByReference1(refc.getSelectionModel().getSelectedItem()).get(0).getC().getCompany();
					 String company_phone =ASR.FindOrdrsByReference1(refc.getSelectionModel().getSelectedItem()).get(0).getC().getPhone()+"";

					 PdfWriter.getInstance(document,new FileOutputStream(reference+".pdf"));
					 document.open();
					 document.add(new Paragraph("Sales Quotation",FontFactory.getFont(FontFactory.TIMES_BOLD,18,BaseColor.RED)));
					 document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));

					 document.add(new Paragraph("                                                                                                                                                "));

					 document.add(new Paragraph("From VERAS MAGUS",FontFactory.getFont(FontFactory.TIMES_BOLD,15,BaseColor.BLUE)));
					 document.add(new Paragraph("Megrine Buisness Center, Saint-Gobain Tunisia                                                                                     Date : " + date,FontFactory.getFont(FontFactory.TIMES_BOLD,9,BaseColor.BLACK)));
					 document.add(new Paragraph("Phone : (+216) 71 123 789 | Fax :(+216) 71 582 478                                                                                 Quotation # : " + reference,FontFactory.getFont(FontFactory.TIMES_BOLD,9,BaseColor.BLACK)));
					 document.add(new Paragraph("                                                                                                                                                "));

					 document.add(new Paragraph("Quotation For",FontFactory.getFont(FontFactory.TIMES_BOLD,11,BaseColor.BLUE)));
					 document.add(new Paragraph(company,FontFactory.getFont(FontFactory.TIMES_BOLD,9,BaseColor.BLACK)));
					 document.add(new Paragraph(company_phone,FontFactory.getFont(FontFactory.TIMES_BOLD,9,BaseColor.BLACK)));

					 
					 document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
					 document.add(new Paragraph("                                                                                                                                                "));

					 PdfPTable table = new PdfPTable(4);
					 PdfPTable table2 = new PdfPTable(4);
					 PdfPTable table3 = new PdfPTable(4);
					 PdfPTable table4 = new PdfPTable(4);
					 PdfPTable table1 = new PdfPTable(4);
					 PdfPTable table5 = new PdfPTable(4);
					 PdfPTable table0 = new PdfPTable(4);


					 PdfPCell cell = new PdfPCell(new Paragraph("   Product Name    |        Quantity         |     Price Per Item   |          Total"))	;
					 cell.setColspan(4);
					 cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					 cell.setBackgroundColor(BaseColor.GRAY);
					 table.addCell(cell);	
	       			 gridP.getChildren().clear();
				float total1 = 0;
				LocalDate localDate = LocalDate.now();
				quotation.setText(localDate.toString());
				ref.setText(refc.getSelectionModel().getSelectedItem());
				clientLabel.setText(ASR.FindOrdrsByReference1(refc.getSelectionModel().getSelectedItem()).get(0).getC().getCompany());
				for (int i = 0; i < ASR.FindOrdrsByReference1(refc.getSelectionModel().getSelectedItem()).size(); i++) {
					Label l1 = new Label(
							ASR.FindOrdrsByReference1(refc.getSelectionModel().getSelectedItem()).get(i).getP().getName());

					Label l2 = new Label(
							ASR.FindOrdrsByReference1(refc.getSelectionModel().getSelectedItem()).get(i).getQuantityToOrder()
									+ "");
					Label l3 = new Label(
							ASR.FindOrdrsByReference1(refc.getSelectionModel().getSelectedItem()).get(i).getP().getPrice()
									+ "");
					Label l4 = new Label(
							ASR.FindOrdrsByReference1(refc.getSelectionModel().getSelectedItem()).get(i).getOrderAmount() + "");
					l4.setText(ASR.FindOrdrsByReference1(refc.getSelectionModel().getSelectedItem()).get(i).getOrderAmount() + "");
					total1 += ASR.FindOrdrsByReference1(refc.getSelectionModel().getSelectedItem()).get(i).getOrderAmount();
					// total1 =(float) (total1
					// +(ASR.FindOrdrsByReference1(refc.getSelectionModel().getSelectedItem()).get(i).getQuantityToOrder()*ASR.FindOrdrsByReference1(refc.getSelectionModel().getSelectedItem()).get(i).getP().getPrice()));
					String tot = String.valueOf(total1);

					Pane p = new Pane();
					p.getChildren().add(l1);
					p.getChildren().add(l2);
					p.getChildren().add(l3);
					p.getChildren().add(l4);

					l1.setLayoutX(60);
					l1.setLayoutY(20);
					l2.setLayoutX(299);
					l2.setLayoutY(20);
					l3.setLayoutX(548);
					l3.setLayoutY(20);
					l4.setLayoutX(757);
					l4.setLayoutY(20);
					l4.setFont(new Font("Arial", 12));
					l1.setFont(new Font("Arial", 12));
					l2.setFont(new Font("Arial", 12));
					l3.setFont(new Font("Arial", 12));
					gridP.addRow(i, p);
					total.setText(tot+" TND");
			c=c+1;
		if(i==2)
		{
		table2.addCell(l1.getText());
		table2.addCell(l2.getText());			
		table2.addCell(l3.getText());			
		table2.addCell(l4.getText());	
		}
		if(i==4)
		{
	    table4.addCell(l1.getText());	
	    table4.addCell(l2.getText());		 
	    table4.addCell(l3.getText());		 
	    table4.addCell(l4.getText());	
		}
		if(i==3)
		{
	    table3.addCell(l1.getText());	
	    table3.addCell(l2.getText());		 
	    table3.addCell(l3.getText());		 
	    table3.addCell(l4.getText());	
		}
		if(i==1)
		{
	    table1.addCell(l1.getText());	
	    table1.addCell(l2.getText());		 
	    table1.addCell(l3.getText());		 
	    table1.addCell(l4.getText());	
		}
		if(i==5)
		{
	    table5.addCell(l1.getText());	
	    table5.addCell(l2.getText());		 
	    table5.addCell(l3.getText());		 
	    table5.addCell(l4.getText());	
		}
		if(i==0)
		{
	    table0.addCell(l1.getText());	
	    table0.addCell(l2.getText());		 
	    table0.addCell(l3.getText());		 
	    table0.addCell(l4.getText());	
		}
		
	 
	   
		

		
	   // com.itextpdf.text.List list = new com.itextpdf.text.List(true,20);
	    //list.add("First Item ");
	    //list.add("Seconf Item");
	    //document.add(list);

				
				}
				notf("Quotation Saved","Your Quotation has been saved succefully");

				if(c==1)
				{
					document.add(table);
					document.add(table0);
					 document.add(new Paragraph("                                                                                                                 Total : "+total.getText(),FontFactory.getFont(FontFactory.TIMES_BOLD,13,BaseColor.BLACK)));

					document.close();
		
				}
				if(c==2)
				{

					document.add(table);
					document.add(table1);
					document.add(table0);
					 document.add(new Paragraph("                                                                                                                 Total : "+total.getText(),FontFactory.getFont(FontFactory.TIMES_BOLD,13,BaseColor.BLACK)));

					document.close();
					
				}
				if(c==3)
				{

					document.add(table);
					document.add(table1);
					document.add(table2);
					document.add(table0);
					 document.add(new Paragraph("                                                                                                                 Total : "+total.getText(),FontFactory.getFont(FontFactory.TIMES_BOLD,13,BaseColor.BLACK)));

					document.close();
					
				}
				if(c==4)
				{

					document.add(table);
					document.add(table1);
					document.add(table2);
					document.add(table3);
					document.add(table0);
					 document.add(new Paragraph("                                                                                                                 Total : "+total.getText(),FontFactory.getFont(FontFactory.TIMES_BOLD,13,BaseColor.BLACK)));

					document.close();
					
				}
				
				if(c==5)
				{

					document.add(table);
					document.add(table0);
					document.add(table1);
					document.add(table2);
					document.add(table3);
					document.add(table4);

					 document.add(new Paragraph("                                                                                                                 Total : "+total.getText(),FontFactory.getFont(FontFactory.TIMES_BOLD,13,BaseColor.BLACK)));

					document.close();
					
				}
				
				if(c==6)
				{

					document.add(table);
					document.add(table1);
					document.add(table2);
					document.add(table3);
					document.add(table4);
					document.add(table0);
					 document.add(new Paragraph("                                                                                                                 Total : "+total.getText(),FontFactory.getFont(FontFactory.TIMES_BOLD,13,BaseColor.BLACK)));

					document.close();
					
				}
						
			}
			
			catch(Exception e ){
				
			}
					
			}
	
	 
		
	    
	  
		
}
