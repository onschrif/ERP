package tn.esprit.b3.esprit1718b3erp.app.client.contactmangment;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.Duration;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.ContactMangmentRemote2;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.PurchaseOrdersserviceRemote;
import tn.esprit.b3.esprit1718b3erp.entities.Client;
import tn.esprit.b3.esprit1718b3erp.entities.Suppliers;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class SuppliersCrudController implements Initializable {
	@FXML
	private TableColumn<Suppliers, String> adresscol;
    @FXML
    private JFXButton menuinsaclient;
	@FXML
	private JFXButton etditsuppbtn;

	@FXML
	private AnchorPane ahmedclientpane;

	@FXML
	private JFXButton addsuppbtn;

	@FXML
	private Label emaillb;

	@FXML
	private JFXButton menusuppliersmangment;

	@FXML
	private JFXButton menustatistics;

	@FXML
	private Label adressnamelb;

	@FXML
	private JFXTextField adressclienttf;

	@FXML
	private ImageView drawimage;

	@FXML
	private AnchorPane opacitypane;

	@FXML
	private TableColumn<Suppliers, Integer> phonecol;

	@FXML
	private JFXButton menuclientmangmet;

	@FXML
	private Label phonelb;

	@FXML
	private JFXButton deletesuppbtn;

	@FXML
	private JFXTextField nameclienttf;

	@FXML
	private JFXTextField phoneclienttf;

	@FXML
	private TableColumn<Suppliers, Integer> idcol;

	@FXML
	private JFXButton clearbtn;

	@FXML
	private JFXButton menupromotion;

	@FXML
	private TableColumn<Suppliers, String> namecol;

	@FXML
	private AnchorPane drawPane;

	@FXML
	private JFXTextField emailclienttf;

	@FXML
	private TableColumn<Suppliers, String> emailcol;

	@FXML
	private TableView<Suppliers> idtabviewsupp;

	@FXML
	private Label namelb;

	@FXML
	private JFXButton menulogout;

	@FXML
	private JFXTextField searchsupp;

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
	void searchaction(ActionEvent event) {
		ObservableList tableboutique = idtabviewsupp.getItems();
		searchsupp.textProperty()
				.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
					if (oldValue != null && (newValue.length() < oldValue.length())) {
						idtabviewsupp.setItems(tableboutique);
					}
					String value = newValue.toLowerCase();
					ObservableList<Suppliers> subentries = FXCollections.observableArrayList();

					long count = idtabviewsupp.getColumns().stream().count();
					for (int i = 0; i < idtabviewsupp.getItems().size(); i++) {
						for (int j = 0; j < count; j++) {
							String entry = "" + idtabviewsupp.getColumns().get(j).getCellData(i);
							if (entry.toLowerCase().contains(value)) {
								subentries.add(idtabviewsupp.getItems().get(i));
								break;
							}
						}
					}
					idtabviewsupp.setItems(subentries);
				});
	}

	@FXML
	void addsuppButtonAction(ActionEvent event) {
		if(test()){
		Suppliers suppliers = new Suppliers(nameclienttf.getText(), adressclienttf.getText(),
				Integer.parseInt(phoneclienttf.getText()), emailclienttf.getText());
		CMS.save(suppliers);
		clear();
		try {
			idtabviewsupp.setItems(null);
			ObservableList<Suppliers> items = FXCollections.observableArrayList(CMS.findAll());
			idtabviewsupp.setItems(items);
		} catch (Exception e) {

			logger.log(Level.INFO, "problem show supplier tabview");
		}}
	}

	@FXML
	void deleteButtonAction(ActionEvent event) {
		Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
		alerte.setTitle("confirmation");
		alerte.setHeaderText("wloud you delete supplier ?");
		Optional<ButtonType> result = alerte.showAndWait();
		if (result.get() == ButtonType.OK) {
			CMS.delete(CMS.find(idtabviewsupp.getSelectionModel().getSelectedItem().getIdSuppliers()));
			clear();
			idcol.setCellValueFactory(new PropertyValueFactory<>("idSuppliers"));
			namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
			adresscol.setCellValueFactory(new PropertyValueFactory<>("adress"));
			phonecol.setCellValueFactory(new PropertyValueFactory<>("phone"));
			emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
			try {
				idtabviewsupp.setItems(null);
				ObservableList<Suppliers> items = FXCollections.observableArrayList(CMS.findAll());
				idtabviewsupp.setItems(items);
			} catch (Exception e) {

				logger.log(Level.INFO, "problem show supplier tabview");
			}
		}
	}

	@FXML
	void editButtonAction(ActionEvent event) {
		Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
		alerte.setTitle("confirmation");
		alerte.setHeaderText("would you update supplier");
		Optional<ButtonType> result = alerte.showAndWait();
		if (result.get() == ButtonType.OK) {
			TableRow<Suppliers> row = new TableRow<>();
			if(test()){
			Suppliers suppliers = new Suppliers(idtabviewsupp.getSelectionModel().getSelectedItem().getIdSuppliers(),
					nameclienttf.getText(), adressclienttf.getText(), Integer.parseInt(phoneclienttf.getText()),
					emailclienttf.getText());
			CMS.update(suppliers);
			clear();
			try {
				idtabviewsupp.setItems(null);
				ObservableList<Suppliers> items = FXCollections.observableArrayList(CMS.findAll());
				idtabviewsupp.setItems(items);
			} catch (Exception e) {

				logger.log(Level.INFO, "problem show supplier tabview");
			}}
		}
	}
	Notification notif=new Notification();
	 public boolean test (){
         if((nameclienttf.getText().equals(""))){
    
     notif.notf_false("PROBLEM","INVALID FIRST NAME");
        return false;
    }
         if((adressclienttf.getText().equals(""))){
    	     
   	      notif.notf_false("PROBLEM","INVALID ADRESS");
   	         return false;
   	     }
          if((phoneclienttf.getText().equals(""))||(phoneclienttf.getText().length()!=8)||(!(phoneclienttf.getText().matches("[0-9]*")))){
    
       	   notif.notf_false("PROBLEM","INVALID TELEPHONE NUMBER");
        return false;
    }
          if ((!emailclienttf.getText().matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+" ))||(emailclienttf.getText().equals(""))) {
              notif.notf_false("PROBLEM","INVALID EMAIL");
              return false;
          }
       return true;
   }
	public void clear() {
		nameclienttf.setText("");
		namelb.setText("");
		adressclienttf.setText("");
		adressnamelb.setText("");
		phoneclienttf.setText("");
		phonelb.setText("");
		emailclienttf.setText("");
		emaillb.setText("");
	}

	@FXML
	void clearButtonAction(ActionEvent event) {
		clear();
	}

	@FXML
	void mousepressed(MouseEvent event) {
		nameclienttf.setText(idtabviewsupp.getSelectionModel().getSelectedItem().getName());
		namelb.setText(idtabviewsupp.getSelectionModel().getSelectedItem().getName());
		adressclienttf.setText(idtabviewsupp.getSelectionModel().getSelectedItem().getAdress());
		adressnamelb.setText(idtabviewsupp.getSelectionModel().getSelectedItem().getAdress());
		phoneclienttf.setText(Integer.toString(idtabviewsupp.getSelectionModel().getSelectedItem().getPhone()));
		phonelb.setText(Integer.toString(idtabviewsupp.getSelectionModel().getSelectedItem().getPhone()));
		emailclienttf.setText(idtabviewsupp.getSelectionModel().getSelectedItem().getEmail());
		emaillb.setText(idtabviewsupp.getSelectionModel().getSelectedItem().getEmail());

	}

	Logger logger = Logger.getLogger("testlog");
	ServiceLocator s = ServiceLocator.getInstance(); // Service Locator is invocated one time and can be used multiple
														// times.
	ContactMangmentRemote2 CMS = (ContactMangmentRemote2) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/ContactMangmentService2!tn.esprit.b3.esprit1718b3erp.conatactmangment.ContactMangmentRemote2");
	PurchaseOrdersserviceRemote CMPO = (PurchaseOrdersserviceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/PurchaseOrdersservice!tn.esprit.b3.esprit1718b3erp.conatactmangment.PurchaseOrdersserviceRemote");
	
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

		// init
		
		idcol.setCellValueFactory(new PropertyValueFactory<>("idSuppliers"));
		namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
		adresscol.setCellValueFactory(new PropertyValueFactory<>("adress"));
		phonecol.setCellValueFactory(new PropertyValueFactory<>("phone"));
		emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
		try {
			idtabviewsupp.setItems(null);
			ObservableList<Suppliers> items = FXCollections.observableArrayList(CMS.findAll());
			idtabviewsupp.setItems(items);
		} catch (Exception e) {

			logger.log(Level.INFO, "problem show client tabview");
		}
		idtabviewsupp.setRowFactory(new Callback<TableView<Suppliers>, TableRow<Suppliers>>() {
			@Override
			public TableRow<Suppliers> call(TableView<Suppliers> tableView) {
				final TableRow<Suppliers> row = new TableRow<>();
				final ContextMenu contextMenu = new ContextMenu();
				final MenuItem showMenuItem = new MenuItem("Show");

				showMenuItem.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						Load.setSend2(idtabviewsupp.getSelectionModel().getSelectedItem().getIdSuppliers());
						FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("showsupplier.fxml"));
						Parent root1;
						try {
							root1 = (Parent) fxmlLoader.load();
							Stage stage = new Stage();
							stage.setScene(new Scene(root1));
							stage.show();
							Stage stage2 = (Stage) idtabviewsupp.getScene().getWindow();
							stage2.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				});
				contextMenu.getItems().add(showMenuItem);
				// Set context menu on row, but use a binding to make it only show for non-empty
				// rows:
				row.contextMenuProperty()
						.bind(Bindings.when(row.emptyProperty()).then((ContextMenu) null).otherwise(contextMenu));
				return row;
			}
		});

	}

}
