package tn.esprit.b3.esprit1718b3erp.app.client.sales;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.ejb.EJB;
import javax.ejb.FinderException;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.logging.Logger;
import org.controlsfx.control.Notifications;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumnBase;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.Duration;
import tn.esprit.b3.esprit1718b3erp.app.client.login.Login;
import tn.esprit.b3.esprit1718b3erp.assetservices.AssetServiceRemote;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.ContactMangmentRemote;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.OrdersserviceRemote;
import tn.esprit.b3.esprit1718b3erp.entities.Assets;
import tn.esprit.b3.esprit1718b3erp.entities.Client;
import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Opportunity;
import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.entities.User;
import tn.esprit.b3.esprit1718b3erp.orderservices.OrderServiceRemote;
import tn.esprit.b3.esprit1718b3erp.salesservices.salesServiceRemote;
import tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesRemote;
import tn.esprit.b3.esprit1718b3erp.services.UserServiceRemote;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

public class Sales2Controller implements Initializable {

	@FXML
	AnchorPane SalesPane;
@FXML
JFXTextField searchtf;
	static Boolean dash;
	@FXML
	GridPane ordergrid;
	@FXML
	Button addbtn, addorder;
	@FXML
	JFXComboBox<String> prodcb, clientTF;
	@FXML
	JFXTextField  nb1;
	@FXML
	DatePicker RecDate, QuotationDate;
	@FXML
	TableView<Orders> tbOrder;
	@FXML
	private TableColumn<Orders, String> col1, col2, col3, col4, col5, col6,col7,col8;

	static int n = 5;
	int r = n - 5;

	GenericDAO<Product> prod;
	ServiceLocator s = ServiceLocator.getInstance(); // Service Locator is

	OrderServiceRemote ASR = (OrderServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/OrderService!tn.esprit.b3.esprit1718b3erp.orderservices.OrderServiceRemote");

	ProductServicesRemote PSR = (ProductServicesRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/ProductServices!tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesRemote");

	ContactMangmentRemote client = (ContactMangmentRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/ContactMangmentService!tn.esprit.b3.esprit1718b3erp.conatactmangment.ContactMangmentRemote");

	salesServiceRemote SSR = (salesServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/salesService!tn.esprit.b3.esprit1718b3erp.salesservices.salesServiceRemote");

	OrdersserviceRemote CMRR=(OrdersserviceRemote) s.getProxy("esprit1718b3erp-ear/esprit1718b3erp-service/Ordersservice!tn.esprit.b3.esprit1718b3erp.conatactmangment.OrdersserviceRemote");

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		try {
			for (int i = 0; i < client.findAll().size(); i++) {
				clientTF.getItems().add(client.findAll().get(i).getCompany());
			}
		} catch (NullPointerException e) {
		}

		try {
			for (int i = 0; i < PSR.findAll().size(); i++) {
				prodcb.getItems().add(PSR.findAll().get(i).getName());
			}
		} catch (NullPointerException e) {

		}
		dash = false;

		orderLoad();

		addbtn.setOnAction(e -> {
			if (n < 9) {
				JFXComboBox<String> bx = new JFXComboBox<String>();
				JFXTextField tf = new JFXTextField();
				Pane p = new Pane();
				p.getChildren().add(bx);
				p.getChildren().add(tf);
				tf.setLayoutX(312);
				tf.setLayoutY(5);
				bx.setLayoutX(0);
				bx.setLayoutY(5);
				tf.setPrefSize(50, 31);
				tf.setPromptText("###");
				bx.setPrefSize(300, 31);
				bx.setPromptText("Product");
				ordergrid.addRow(n, p);
				n += 1;

				// Node n1 ;

				for (int i = 0; i < PSR.findAll().size(); i++) {
					bx.getItems().add(PSR.findAll().get(i).getName());
				}

			}
		});

		if(Orders.getOpc()!=null)
		{
		clientTF.getSelectionModel().select(Orders.getOpc());
		Orders.setOpc(null);
		}
		}

	public void clear() {
		nb1.setText("");

		
		clientTF.getSelectionModel().clearSelection();
		prodcb.getSelectionModel().clearSelection();
	}

	// Common Block

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
	public void toOpportunty(ActionEvent event) throws IOException
	{
		trans("../sales/opportunity.fxml", event);
	
		}
	@FXML
	public void toDeliv(ActionEvent event) throws IOException
	{
		trans("../delivery/dashbord.fxml", event);
	
		}
	@FXML
	public void toDevis(ActionEvent event) throws IOException
	{
		trans("../sales/Devis.fxml", event);
	
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

	@FXML
	public void dsh(ActionEvent event) {
		TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), SalesPane);
		if (dash) {
			translateTransition1.setByX(-250);
			dash = false;
		} else {
			translateTransition1.setByX(+250);
			dash = true;
		}
		translateTransition1.play();
	}

	GenericDAO<Orders> od;

	@FXML
	public void addOrder(ActionEvent action) throws ParseException {

		try {
			String s = prodcb.getSelectionModel().getSelectedItem();
			String sa = System.currentTimeMillis() + "";
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
			LocalDate localDate = LocalDate.now();
			Double ds = ASR.findIdProduct(prodcb.getSelectionModel().getSelectedItem()).getDiscount();
			Orders order =
					new Orders(ASR.FindClientByCompany(clientTF.getSelectionModel().getSelectedItem()),
					ASR.findIdProduct(prodcb.getSelectionModel().getSelectedItem()), "ORD-" + Math.abs(sa.hashCode()),
					localDate.toString()
					, RecDate.getValue().toString(), 
					
					ASR.findIdProduct(prodcb.getSelectionModel().getSelectedItem()).getDiscount(), Integer.valueOf(nb1.getText()),"waiting",(float)ASR.findIdProduct(prodcb.getSelectionModel().getSelectedItem()).getPrice()*Float.valueOf(nb1.getText()));
			ASR.save(order);
			
			int iter = 3;
			for (int i = 0; i < n - 5; i++) {
				Pane p = new Pane();
				p = (Pane) ordergrid.getChildren().get(iter);
				JFXComboBox bx = new JFXComboBox<String>();
				JFXTextField tf = new JFXTextField();
				tf = (JFXTextField) p.getChildren().get(1);
				bx = (JFXComboBox) p.getChildren().get(0);
			//	double d = ASR.findIdProduct((String) bx.getSelectionModel().getSelectedItem()).getDiscount();
			
				
				
			
				Orders order1 = new Orders(ASR.FindClientByCompany(clientTF.getSelectionModel().getSelectedItem()),
						ASR.findIdProduct((String) bx.getSelectionModel().getSelectedItem()),
						"ORD-" + Math.abs(sa.hashCode()), localDate.toString(), RecDate.getValue().toString(),
						ASR.findIdProduct((String) bx.getSelectionModel().getSelectedItem()).getDiscount(),
						Integer.valueOf(tf.getText()),"Waiting",(float)ASR.findIdProduct((String) bx.getSelectionModel().getSelectedItem()).getPrice()*Float.valueOf(tf.getText()));
				
				ASR.save(order1);
				//System.out.println(d);
				//d=0.00d;
				//double d = ASR.findIdProduct((String) bx.getSelectionModel().getSelectedItem()).getDiscount();
				
				
			
				iter += 1;

			}
		}

		catch (NumberFormatException E) {

		}
        catch(NullPointerException e)
		{
        	
		}
		refresh();
	}

	@FXML
	public void EditOrder(ActionEvent action) {

		try {
			String s = prodcb.getSelectionModel().getSelectedItem();
			String sa = System.currentTimeMillis() + "";
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
			LocalDate localDate = LocalDate.now();

			Orders order = new Orders(ASR.FindClientByCompany(clientTF.getSelectionModel().getSelectedItem()),
					ASR.findIdProduct(prodcb.getSelectionModel().getSelectedItem()), "ORD-" + Math.abs(sa.hashCode()),
					localDate.toString(), RecDate.getValue().toString(), QuotationDate.getValue().toString(),
					 Integer.valueOf(nb1.getText()));
			int l = tbOrder.getSelectionModel().getSelectedItem().getIdOrder();
			order.setIdOrder(l);
			ASR.update(order);
		}

		catch (NumberFormatException E) {

		}
		refresh();
	}

	@FXML
	public void DelOrder(ActionEvent event) {
		Orders od=ASR.find(((Orders) tbOrder.getSelectionModel().getSelectedItem()).getIdOrder());
		System.out.println(od.toString());
		ASR.del(od);
		refresh();		
	}

	@FXML
	public void clearOrder(ActionEvent event)
	{
	refresh();
	
	
	
	}
	
	@FXML
	public void Ordersearch(KeyEvent event) {
		ObservableList tableboutique = tbOrder.getItems();
		searchtf.textProperty()
				.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
					if (oldValue != null && (newValue.length() < oldValue.length())) {
						tbOrder.setItems(tableboutique);
					}
					String value = newValue.toLowerCase();
					ObservableList<Orders> subentries = FXCollections.observableArrayList();

					long count = tbOrder.getColumns().stream().count();
					for (int i = 0; i < tbOrder.getItems().size(); i++) {
						for (int j = 0; j < count; j++) {
							String entry = "" + tbOrder.getColumns().get(j).getCellData(i);
							if (entry.toLowerCase().contains(value)) {
								subentries.add(tbOrder.getItems().get(i));
								break;
							}
						}
					}
					tbOrder.setItems(subentries);
				});
	}
	
	public void refresh() {
		tbOrder.setItems(null);
		ObservableList<Orders> items = FXCollections.observableArrayList(ASR.findAll());
		tbOrder.setItems(items);

	}

	public void orderLoad() {
		col1.setCellValueFactory(new PropertyValueFactory<>("idOrder"));
		col2.setCellValueFactory(new PropertyValueFactory<>("referenceOrder"));
		col3.setCellValueFactory(new PropertyValueFactory<>("RecommendedDate"));
		col4.setCellValueFactory(new PropertyValueFactory<>("invoiceDate"));
		col5.setCellValueFactory(new PropertyValueFactory<>("quantityToOrder"));
		col6.setCellValueFactory(new PropertyValueFactory<>("discountOrder"));
		col7.setCellValueFactory(new PropertyValueFactory<>("orderState"));
		col8.setCellValueFactory(new Callback<CellDataFeatures<Orders, String>, ObservableValue<String>>() {

			public ObservableValue<String> call(CellDataFeatures<Orders, String> param) {
				return new SimpleStringProperty(param.getValue().getP().getName());
			}
		});	

		tbOrder.setItems(null);
		ObservableList<Orders> items = FXCollections.observableArrayList(ASR.findAll());
		tbOrder.setItems(items);
	}

	public void refreshOrder() {
		ObservableList<Orders> items = FXCollections.observableArrayList(ASR.findAll());

		tbOrder.setItems(items);
	}

	@FXML
	public void fill(MouseEvent event) {
		Orders o = ASR.find(((Orders) tbOrder.getSelectionModel().getSelectedItem()).getIdOrder());
		LocalDate localDate = LocalDate.parse(o.getRecommendedDate());
		Client s = SSR.findClientNameById(o.getC().getIdClient());
		System.out.println("Test " + s);

		Product p = SSR.FindProductById(o.getP().getIdProduct());
		clientTF.getSelectionModel().select(s.getCompany());

		//discountTF.setText(String.valueOf(o.getDiscountOrder()));

		prodcb.getSelectionModel().select(p.getName());
		nb1.setText(String.valueOf(o.getQuantityToOrder()));
		RecDate.setValue(localDate);

	}

	@FXML
	public void orderedit(ActionEvent action) throws ParseException {

		// String s = typecb.getSelectionModel().getSelectedItem();
		String sa = System.currentTimeMillis() + "";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		LocalDate localDate = LocalDate.now();
		Orders order = ASR.find(((Orders) tbOrder.getSelectionModel().getSelectedItem()).getIdOrder());

		Orders order1 = new Orders(ASR.FindClientByCompany(clientTF.getSelectionModel().getSelectedItem()),
				ASR.findIdProduct(prodcb.getSelectionModel().getSelectedItem()), "ORD-" + Math.abs(sa.hashCode()),
				localDate.toString(), RecDate.getValue().toString(), QuotationDate.getValue().toString(),
				ASR.findIdProduct(prodcb.getSelectionModel().getSelectedItem()).getDiscount(), Integer.valueOf(nb1.getText()));

		order = order1;
		order.setIdOrder(((Orders) tbOrder.getSelectionModel().getSelectedItem()).getIdOrder());

		refreshOrder();

		clear();
	}

	
	
}



// End of Common Block
