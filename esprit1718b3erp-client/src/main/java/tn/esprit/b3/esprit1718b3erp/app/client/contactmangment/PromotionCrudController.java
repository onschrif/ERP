package tn.esprit.b3.esprit1718b3erp.app.client.contactmangment;

import java.io.IOException;
import java.net.URL;
import java.nio.channels.GatheringByteChannel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.ChronoZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javassist.expr.NewArray;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.ContactMangmentRemote;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.OrdersserviceRemote;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.ProdcutmouvRemote;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.ProductserviceRemote;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.PromotionserviceRemote;
import tn.esprit.b3.esprit1718b3erp.entities.Client;
import tn.esprit.b3.esprit1718b3erp.entities.InventoryMouvement;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.entities.Production;
import tn.esprit.b3.esprit1718b3erp.entities.Promotion;
import tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesRemote;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

public class PromotionCrudController implements Initializable {

	@FXML
	private Label pricelb;
    @FXML
    private JFXButton menuinsaclient;
	@FXML
	private TableColumn<Product, String> namecol1;

	@FXML
	private Label naturelb;

	@FXML
	private JFXTextField searchproduct;

	@FXML
	private DatePicker startdate;

	@FXML
	private AnchorPane ahmedclientpane;

	@FXML
	private JFXButton deletepromotionbtn;

	@FXML
	private TableColumn<Product, Double> pricecol;

	@FXML
	private JFXButton addpromotion;

	@FXML
	private TableColumn<Product, String> naturecol;

	@FXML
	private TableView<Promo> idtabviewpromotion;

	@FXML
	private TableColumn<Promotion, Integer> quantitycol1;

	@FXML
	private TableColumn<Promotion, DatePicker> startdatecol1;

	@FXML
	private JFXTextField quantitytf;

	@FXML
	private TableColumn<Promotion, DatePicker> enddatecol1;

	@FXML
	private ImageView drawimage;

	@FXML
	private TableColumn<Promotion, Integer> idpromotioncol1;

	@FXML
	private TableColumn<Product, String> typecol;

	@FXML
	private TableView<Product> idtabviewproduct;

	@FXML
	private TableColumn<Product, Integer> idproductcol;

	@FXML
	private DatePicker closedate;

	@FXML
	private TableColumn<Promotion, Integer> idproductcol1;

	@FXML
	private TableColumn<Product, String> categorycol;
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
	private Label categorylb;

	@FXML
	private JFXTextField searchPromotion;

	@FXML
	private JFXButton clearbtn;

	@FXML
	private TableColumn<Product, String> namecol;

	@FXML
	private Label namelb;

	@FXML
	private JFXButton etditpromotionbtn;

	@FXML
	private Label typelb;

	@FXML
	private AnchorPane opacitypane;

	@FXML
	private AnchorPane drawPane;
	Logger logger = Logger.getLogger("testlog");
	Notification notif = new Notification();

	@FXML
	void goclientmang(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("clientcrud.fxml"));
		Scene scene = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(scene);
		app_stage.show();
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
	void searchactionproduct(ActionEvent event) {
		ObservableList tableboutique = idtabviewproduct.getItems();
		searchproduct.textProperty()
				.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
					if (oldValue != null && (newValue.length() < oldValue.length())) {
						idtabviewproduct.setItems(tableboutique);
					}
					String value = newValue.toLowerCase();
					ObservableList<Product> subentries = FXCollections.observableArrayList();

					long count = idtabviewproduct.getColumns().stream().count();
					for (int i = 0; i < idtabviewproduct.getItems().size(); i++) {
						for (int j = 0; j < count; j++) {
							String entry = "" + idtabviewproduct.getColumns().get(j).getCellData(i);
							if (entry.toLowerCase().contains(value)) {
								subentries.add(idtabviewproduct.getItems().get(i));
								break;
							}
						}
					}
					idtabviewproduct.setItems(subentries);
				});
	}

	ServiceLocator s = ServiceLocator.getInstance(); // Service Locator is invocated one time and can be used multiple
														// times.
	ProductserviceRemote CMPR = (ProductserviceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/Productservice!tn.esprit.b3.esprit1718b3erp.conatactmangment.ProductserviceRemote");
	PromotionserviceRemote CMPROM = (PromotionserviceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/Promotionservice!tn.esprit.b3.esprit1718b3erp.conatactmangment.PromotionserviceRemote");
	ProdcutmouvRemote CMPM = (ProdcutmouvRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/Prodcutmouv!tn.esprit.b3.esprit1718b3erp.conatactmangment.ProdcutmouvRemote");
	ContactMangmentRemote CMR=(ContactMangmentRemote) s.getProxy("esprit1718b3erp-ear/esprit1718b3erp-service/ContactMangmentService!tn.esprit.b3.esprit1718b3erp.conatactmangment.ContactMangmentRemote");
	OrdersserviceRemote CMRR=(OrdersserviceRemote) s.getProxy("esprit1718b3erp-ear/esprit1718b3erp-service/Ordersservice!tn.esprit.b3.esprit1718b3erp.conatactmangment.OrdersserviceRemote");
	SendMail send=new SendMail();
	public void SendPromotion(Promotion p) {
		List<Object[]> l=null;
		List<Fav> ll = new ArrayList<Fav>();
		List<Client> listclient=null;
		listclient=CMR.findAll();
		for(int h=0;h<listclient.size();h++)
		{
	      l=CMRR.FavProduct(listclient.get(h).getIdClient());
	      for(int i=0;i<l.size();i++)
	      {  
	         String count,id,name;
	         count=Arrays.deepToString(l.get(i)).substring(1,2);
	         id=Arrays.deepToString(l.get(i)).substring(4,5);
	         Product product =CMR.FindProduct(Integer.parseInt(id));
	         name=product.getName();
	         Fav fav=new Fav(name,Integer.parseInt(count));
	         ll.add(fav);
	         }
	     
	    	  for(int j=0;j<ll.size();j++)
	 		 {
	    		  Product product=CMPR.find(p.getIdProduct());
	 		 
	 			 if(product.getName().equals(ll.get(j).getName())) {
	 				 send.Promotion(listclient.get(h).getEmail(),product.getName());
	 			 }}
	 	 
	    
	      }
	}
	@FXML
	void addpromotionButtonAction(ActionEvent event) {
		if(test()) {
		Date start = Date.from(startdate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date close = Date.from(closedate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		// &&(startdate.getValue().isBefore(localDate)||(startdate.getValue().equals(localDate)))
		if (startdate.getValue().isBefore(closedate.getValue())) {
			if (idtabviewproduct.getSelectionModel().getSelectedItem() != null) {
				Promotion promotion = new Promotion(
						idtabviewproduct.getSelectionModel().getSelectedItem().getIdProduct(), start, close,
						Integer.parseInt(quantitytf.getText()));
				CMPROM.save(promotion);
				SendPromotion(promotion);
				clear();
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

			} else {
				Alert alerte1 = new Alert(Alert.AlertType.CONFIRMATION);
				alerte1.setTitle("confirmation");
				alerte1.setHeaderText("select product");
				Optional<ButtonType> result = alerte1.showAndWait();
			}
		} else {
			Alert alerte1 = new Alert(Alert.AlertType.CONFIRMATION);
			alerte1.setTitle("confirmation");
			alerte1.setHeaderText("start date after close date");
			Optional<ButtonType> result = alerte1.showAndWait();
		}}

	}

	@FXML
	void deletepromtionButtonAction(ActionEvent event) {
		if (idtabviewproduct.getSelectionModel().getSelectedItem() != null) {
			Alert alerte1 = new Alert(Alert.AlertType.CONFIRMATION);
			alerte1.setTitle("confirmation");
			alerte1.setHeaderText("select promotion");
			Optional<ButtonType> result1 = alerte1.showAndWait();
			idtabviewproduct.getSelectionModel().select(null);
		} else if (idtabviewpromotion.getSelectionModel().getSelectedItem() != null) {
			idtabviewproduct.getSelectionModel().select(null);
			Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
			alerte.setTitle("confirmation");
			alerte.setHeaderText("wloud you delete promotion ?");
			Optional<ButtonType> result = alerte.showAndWait();
			if (result.get() == ButtonType.OK) {
				CMPROM.delete(CMPROM.find(idtabviewpromotion.getSelectionModel().getSelectedItem().getIdPromotion()));
				clear();
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

			}
		}

	}
	 public boolean test (){
         if((quantitytf.getText().equals(""))||(Integer.parseInt(quantitytf.getText())==0)||(!(quantitytf.getText().matches("[0-9]*")))){
    
     notif.notf_false("PROBLEM","INVALID QUANTITY");
        return false;
    }

         if((startdate.getValue()==null)||(startdate.getValue().isBefore(localDate))){
    	     
   	      notif.notf_false("PROBLEM","INVALID Startdate");
   	         return false;
   	     }
         if((closedate.getValue()==null)){
    	     
      	      notif.notf_false("PROBLEM","INVALID closedate");
      	         return false;
      	     }
            
       return true;
   }
	@FXML
	void editpromotionButtonAction(ActionEvent event) {
		Date start = Date.from(startdate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date close = Date.from(closedate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
		alerte.setTitle("confirmation");
		alerte.setHeaderText("would you update promotion");
		Optional<ButtonType> result = alerte.showAndWait();
		if (result.get() == ButtonType.OK) {
			TableRow<Client> row = new TableRow<>();
			if (startdate.getValue().isBefore(closedate.getValue())) {
				Promotion promotion = new Promotion(
						idtabviewpromotion.getSelectionModel().getSelectedItem().getIdPromotion(),
						idtabviewpromotion.getSelectionModel().getSelectedItem().getIdProduct(), start, close,
						Integer.parseInt(quantitytf.getText()));
				CMPROM.update(promotion);
				notif.notf_right("Upadate promotion", "successfully updated");
				clear();
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

			}
		}
	}

	@FXML
	void clearButtonAction(ActionEvent event) {
		clear();
	}

	@FXML
	void mousepressedproduct(MouseEvent event) {
		namelb.setText(idtabviewproduct.getSelectionModel().getSelectedItem().getName());
		pricelb.setText(Double.toString(idtabviewproduct.getSelectionModel().getSelectedItem().getPrice()));
		categorylb.setText(idtabviewproduct.getSelectionModel().getSelectedItem().getCategory());
		typelb.setText(idtabviewproduct.getSelectionModel().getSelectedItem().getType());
		naturelb.setText(idtabviewproduct.getSelectionModel().getSelectedItem().getNature());
	}

	@FXML
	void mousepressedpromotion(MouseEvent event) {
		Product p = CMPR.find(idtabviewpromotion.getSelectionModel().getSelectedItem().getIdProduct());
		namelb.setText(p.getName());
		pricelb.setText(Double.toString(p.getPrice()));
		categorylb.setText(p.getCategory());
		typelb.setText(p.getType());
		naturelb.setText(p.getNature());

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

	public void clear() {
		startdate.setValue(null);
		closedate.setValue(null);
		quantitytf.setText("");
		namelb.setText("");
		pricelb.setText("");
		naturelb.setText("");
		categorylb.setText("");
		typelb.setText("");
	}

	/*
	 * 
	 * // System.out.println(DateTimeFormatter.ofPattern("dd").format(localDate));
	 * // Product pro =
	 * CMPR.findProduct(mouvlist.get().getProduct().getIdProduct());
	 * System.out.println(date.getYear()); System.out.println(localDate.getYear());
	 */
	
	public Boolean testdate(Date date1)
	{ 
		
		LocalDate date = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		if(date.getYear()==localDate.getYear())
		{
			if(localDate.getMonthValue()>3)
			{
				for(int i=(localDate.getMonthValue()-1);i>(localDate.getMonthValue()-4);i--)
				{
					if(date.getMonthValue()==i)
						return true;
				}
			}
		}
			else if((localDate.getMonthValue()==3)&&(date.getYear()==(localDate.getYear()-1)))
			{
				if((date.getMonthValue()==2)||(date.getMonthValue()==1)||(date.getMonthValue()==12))
				{
				return true;	
				}
			}
			else if((localDate.getMonthValue()==2)&&(date.getYear()==(localDate.getYear()-1)))
			{
				if((date.getMonthValue()==1)||(date.getMonthValue()==11)||(date.getMonthValue()==12))
				{
				return true;	
				}
			}
			else if((localDate.getMonthValue()==1)&&(date.getYear()==(localDate.getYear()-1)))
			{
				if((date.getMonthValue()==10)||(date.getMonthValue()==11)||(date.getMonthValue()==12))
				{
				return true;	
				}
			}
		
		return false;
	}

	LocalDate localDate = LocalDate.now();
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

	public List<Product> findpromotion(List<Product> productlist) {
		List<InventoryMouvement> mouvlist = null;
		List<Product> p = new ArrayList<Product>();
		for (int i = 0; i < productlist.size(); i++) {
			mouvlist = CMPM.FindProductMouvment(productlist.get(i).getIdProduct());
			if (mouvlist.size() != 0) {
				for (int j = 0; j < mouvlist.size(); j++) {
					LocalDate date = mouvlist.get(j).getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					if (!testdate(mouvlist.get(j).getDate())) {
						p.add(productlist.get(i));
					}
					else {
						p.remove(productlist.get(i));
						return p;
					}
				}
			}

		}
		Set<Product> hs = new HashSet<>();
		hs.addAll(p);
		p.clear();
		p.addAll(hs);

		return p;
	}

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

		idproductcol.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
		namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
		typecol.setCellValueFactory(new PropertyValueFactory<>("type"));
		categorycol.setCellValueFactory(new PropertyValueFactory<>("category"));
		naturecol.setCellValueFactory(new PropertyValueFactory<>("nature"));
		pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));
		try {
			idtabviewproduct.setItems(null);
			ObservableList<Product> items = FXCollections.observableArrayList(findpromotion(CMPR.findAll()));
			idtabviewproduct.setItems(items);
		} catch (Exception e) {
			logger.log(Level.INFO, "problem show prodcut tabview prom");
		}

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

	}

}
