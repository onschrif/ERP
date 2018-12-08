package tn.esprit.b3.esprit1718b3erp.app.client.contactmangment;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
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
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.ContactMangmentRemote;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.OrdersserviceRemote;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.ProdcutmouvRemote;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.ProductserviceRemote;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.PromotionserviceRemote;
import tn.esprit.b3.esprit1718b3erp.entities.Client;
import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.entities.Promotion;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

public class InsClientCrudController implements Initializable {

	@FXML
	private Label firstnamelb;
    @FXML
    private JFXButton menuinsaclient;
	@FXML
	private TableColumn<Product, String> namecol1;

	@FXML
	private TableColumn<Client, String> lastnamecol;

	@FXML
	private AnchorPane ahmedclientpane;

	@FXML
	private Label emaillb;

	@FXML
	private TableView<Promo> idtabviewpromotion;

	@FXML
	private JFXButton sendbtn;
	@FXML
	private TableColumn<Promotion, Integer> quantitycol1;

	@FXML
	private TableColumn<Client, String> firstnamecol;

	@FXML
	private TableColumn<Promotion, DatePicker> startdatecol1;
	@FXML
	private TableColumn<Client, String> companycol;

	@FXML
	private TableColumn<Promotion, DatePicker> enddatecol1;

	@FXML
	private ImageView drawimage;

	@FXML
	private TableColumn<Promotion, Integer> idpromotioncol1;

	@FXML
	private TableColumn<Client, String> typecol;

	@FXML
	private TableColumn<Client, Integer> phonecol;

	@FXML
	private TableColumn<Promotion, Integer> idproductcol1;
	@FXML
	private AnchorPane drawPane;
	@FXML
	private Label phonelb;

	@FXML
	private TableColumn<Client, Float> notecol;

	@FXML
	private Label notelb;

	@FXML
	private JFXTextField searchPromotion;

	@FXML
	private TableColumn<Client, Integer> idcol;

	@FXML
	private Label lastnamelb;
	@FXML
	private AnchorPane opacitypane;
	@FXML
	private JFXTextField searchclient;
	
	@FXML
	private Label companylb;
	@FXML
	private JFXButton menulogout;
	@FXML
	private JFXButton menupromotion;
	@FXML
	private JFXButton menuclientmangmet;

	@FXML
	private JFXButton menusuppliersmangment;

	@FXML
	private JFXButton menustatistics;
	@FXML
	private TableColumn<Client, String> emailcol;
	@FXML
    private AreaChart areachart;
	@FXML
	private TableView<Client> idtabviewclient;

	Logger logger = Logger.getLogger("testlog");
	Notification notif = new Notification();
	@FXML
	private Label typelb;
	ServiceLocator s = ServiceLocator.getInstance();
	ProductserviceRemote CMPR = (ProductserviceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/Productservice!tn.esprit.b3.esprit1718b3erp.conatactmangment.ProductserviceRemote");
	SendMail send=new SendMail();
	
	@FXML
	void sendButtonAction(ActionEvent event) {
     Product product=CMPR.find(idtabviewpromotion.getSelectionModel().getSelectedItem().getIdProduct());
     String mail=idtabviewclient.getSelectionModel().getSelectedItem().getEmail();
     send.Promotion(mail,product.getName());
     
	}

	@FXML
	void goclientmang(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("clientcrud.fxml"));
		Scene scene = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(scene);
		app_stage.show();
	}

	@FXML
	void gosuppmang(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("supplierscrud.fxml"));
		Scene scene = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(scene);
		app_stage.show();
	}

	@FXML
	void gopromo(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Promotion.fxml"));
		Scene scene = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(scene);
		app_stage.show();
	}
	 
	@FXML
	void gostat(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("statistique.fxml"));
		Scene scene = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(scene);
		app_stage.show();
	}

	@FXML
	void logout(ActionEvent event) {
		Stage stage = (Stage) menulogout.getScene().getWindow();
		stage.close();
	}
	@FXML
	void mousepressedpromotion(ActionEvent event) {

	}
	 @FXML
	    void goinsclient(ActionEvent event) throws IOException {
		 Parent root = FXMLLoader.load(getClass().getResource("InsClient.fxml"));
			Scene scene = new Scene(root);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(scene);
			app_stage.show();
	    }
	@FXML
	void searchactiopromotion(ActionEvent event) {
		ObservableList tableboutique = idtabviewpromotion.getItems();
		searchPromotion.textProperty()
				.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
					if (oldValue != null && (newValue.length() < oldValue.length())) {
						idtabviewpromotion.setItems(tableboutique);
					}
					String value = newValue.toLowerCase();
					ObservableList<Promo> subentries = FXCollections.observableArrayList();

					long count = idtabviewpromotion.getColumns().stream().count();
					for (int i = 0; i < idtabviewpromotion.getItems().size(); i++) {
						for (int j = 0; j < count; j++) {
							String entry = "" + idtabviewpromotion.getColumns().get(j).getCellData(i);
							if (entry.toLowerCase().contains(value)) {
								subentries.add(idtabviewpromotion.getItems().get(i));
								break;
							}
						}
					}
					idtabviewpromotion.setItems(subentries);
				});

	}
	@FXML
	void searchactio(ActionEvent event) {
		ObservableList tableboutique = idtabviewclient.getItems();
		searchclient.textProperty()
				.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
					if (oldValue != null && (newValue.length() < oldValue.length())) {
						idtabviewclient.setItems(tableboutique);
					}
					String value = newValue.toLowerCase();
					ObservableList<Client> subentries = FXCollections.observableArrayList();

					long count = idtabviewclient.getColumns().stream().count();
					for (int i = 0; i < idtabviewclient.getItems().size(); i++) {
						for (int j = 0; j < count; j++) {
							String entry = "" + idtabviewclient.getColumns().get(j).getCellData(i);
							if (entry.toLowerCase().contains(value)) {
								subentries.add(idtabviewclient.getItems().get(i));
								break;
							}
						}
					}
					idtabviewclient.setItems(subentries);
				});
	}

	@FXML
	void mousepressed(MouseEvent event) {
		firstnamelb.setText(idtabviewclient.getSelectionModel().getSelectedItem().getFirstName());
		lastnamelb.setText(idtabviewclient.getSelectionModel().getSelectedItem().getLastName());
		phonelb.setText(Integer.toString(idtabviewclient.getSelectionModel().getSelectedItem().getPhone()));
		companylb.setText(idtabviewclient.getSelectionModel().getSelectedItem().getCompany());
		emaillb.setText(idtabviewclient.getSelectionModel().getSelectedItem().getEmail());
		notelb.setText(Float.toString(idtabviewclient.getSelectionModel().getSelectedItem().getNote()));
		typelb.setText(idtabviewclient.getSelectionModel().getSelectedItem().getType());
	}

	 // Service Locator is invocated one time and can be used multiple
	PromotionserviceRemote CMPROM = (PromotionserviceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/Promotionservice!tn.esprit.b3.esprit1718b3erp.conatactmangment.PromotionserviceRemote");
	ContactMangmentRemote CMR = (ContactMangmentRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/ContactMangmentService!tn.esprit.b3.esprit1718b3erp.conatactmangment.ContactMangmentRemote");
	OrdersserviceRemote CMRR = (OrdersserviceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/Ordersservice!tn.esprit.b3.esprit1718b3erp.conatactmangment.OrdersserviceRemote");
	LocalDate localDate = LocalDate.now();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		opacitypane.setVisible(false);

		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), opacitypane);
		fadeTransition.setFromValue(1);
		fadeTransition.setToValue(0);
		fadeTransition.play();

		TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), drawPane);
		translateTransition.setByX(-600);
		translateTransition.play();
		drawimage.setOnMouseClicked(event -> {

			opacitypane.setVisible(true);

			FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), opacitypane);
			fadeTransition1.setFromValue(0);
			fadeTransition1.setToValue(0.15);
			fadeTransition1.play();

			TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), drawPane);
			translateTransition1.setByX(+600);
			translateTransition1.play();
		});

		opacitypane.setOnMouseClicked(event -> {

			FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), opacitypane);
			fadeTransition1.setFromValue(0.15);
			fadeTransition1.setToValue(0);
			fadeTransition1.play();

			fadeTransition1.setOnFinished(event1 -> {
				opacitypane.setVisible(false);
			});

			TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), drawPane);
			translateTransition1.setByX(-600);
			translateTransition1.play();
		});

		// TODO Auto-generated method stub
		idpromotioncol1.setCellValueFactory(new PropertyValueFactory<>("idPromotion"));
		idproductcol1.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
		namecol1.setCellValueFactory(new PropertyValueFactory<>("name"));
		startdatecol1.setCellValueFactory(new PropertyValueFactory<>("startdate"));
		enddatecol1.setCellValueFactory(new PropertyValueFactory<>("enddate"));
		quantitycol1.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		List<Promotion> l=new ArrayList<>();
		l=CMPROM.findAll();
		List<Promo> ll=new ArrayList<>();
		for(int i=0;i<l.size();i++)
		{
			Promo e=new Promo(l.get(i).getIdPromotion(),l.get(i).getIdProduct(),CMPR.find(l.get(i).getIdProduct()).getName(), l.get(i).getStartdate(), l.get(i).getEnddate(),l.get(i).getQuantity());
			ll.add(e);
		}
		try {
			idtabviewpromotion.setItems(null);
			ObservableList<Promo> items = FXCollections.observableArrayList(ll);
			idtabviewpromotion.setItems(items);
		} catch (Exception e) {

			logger.log(Level.INFO, "problem show promotion tabview");
		}

		List<Client> listclient=new ArrayList<>();
		List<Orders> listorder=null;
		listorder=CMRR.findAll();
      for(int i=0;i<listorder.size();i++)
      {
    	   String year=listorder.get(i).getRecommendedDate().substring(0, 4);
    	  
    	   if(localDate.getYear()!=Integer.parseInt(year))
    	   {
    		   Client c=CMR.find(1);
    		   listclient.add(c);
    	     
    	   }
      }
      for(int i=0;i<listorder.size();i++)
      {
    	   String year=listorder.get(i).getRecommendedDate().substring(0, 4);
    	  
    	   if(localDate.getYear()==Integer.parseInt(year))
    	   {
    		  if(listclient.contains(listorder.get(i).getC()))
    			  listclient.remove(listorder.get(i).getC());
    	     
    	   }
      }
      idcol.setCellValueFactory(new PropertyValueFactory<>("idClient"));
		firstnamecol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		lastnamecol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		phonecol.setCellValueFactory(new PropertyValueFactory<>("phone"));
		emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
		companycol.setCellValueFactory(new PropertyValueFactory<>("company"));
		typecol.setCellValueFactory(new PropertyValueFactory<>("type"));
		notecol.setCellValueFactory(new PropertyValueFactory<>("note"));

		try {
			idtabviewclient.setItems(null);
			ObservableList<Client> items = FXCollections.observableArrayList(listclient);
			idtabviewclient.setItems(items);
		} catch (Exception e) {

			logger.log(Level.INFO, "problem show client tabview client crud controlleur");
		}
		XYChart.Series series = new XYChart.Series();  
				List<Promotion> listpromotion=null;
		listpromotion=CMPROM.findAll();
		series.setName("Promotion");
		for (int i =0; i < listpromotion.size(); i++) {
    
			series.getData().add(new XYChart.Data(String.valueOf(listpromotion.get(i).getIdProduct()),listpromotion.get(i).getQuantity()));
		}
		areachart.getData().addAll(series); 
	
		}
}