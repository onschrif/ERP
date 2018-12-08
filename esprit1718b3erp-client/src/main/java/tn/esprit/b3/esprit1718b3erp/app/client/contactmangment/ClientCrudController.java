package tn.esprit.b3.esprit1718b3erp.app.client.contactmangment;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import tn.esprit.b3.esprit1718b3erp.conatactmangment.ContactMangmentRemote;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.ContactMangmentRemote2;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.OrdersserviceRemote;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.ProductserviceRemote;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.PromotionserviceRemote;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.PurchaseOrdersserviceRemote;
import tn.esprit.b3.esprit1718b3erp.entities.Client;
import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.entities.Promotion;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.Node;

public class ClientCrudController implements Initializable {
	@FXML
	private JFXTextField companyclienttf;
    @FXML
    private JFXButton menuinsaclient;
	@FXML
	private Label firstnamelb;

	@FXML
	private TableColumn<Client, String> lastnamecol;

	@FXML
	private JFXButton deleteclientbtn;

	@FXML
	private JFXTextField lastnameclienttf;

	@FXML
	private Label emaillb;

	@FXML
	private JFXButton menusuppliersmangment;

	@FXML
	private JFXButton menustatistics;

	@FXML
	private TableColumn<Client, String> firstnamecol;

	@FXML
	private TableColumn<Client, String> companycol;

	@FXML
	private TableColumn<Client, Integer> idcol;

	@FXML
	private AnchorPane ahmedclientpane;

	@FXML
	private TableColumn<Client, String> typecol;

	@FXML
	private TableColumn<Client, Integer> phonecol;

	@FXML
	private JFXButton menuclientmangmet;

	@FXML
	private Label phonelb;

	@FXML
	private JFXButton addclientbtn;

	@FXML
	private TableColumn<Client, Float> notecol;

	@FXML
	private JFXTextField firstnameclienttf;

	@FXML
	private JFXButton etditclientbtn;

	@FXML
	private JFXComboBox<String> typeclientcombo;

	@FXML
	private Label notelb;

	@FXML
	private JFXTextField phoneclienttf;

	@FXML
	private JFXButton clearbtn;

	@FXML
	private Label lastnamelb;

	@FXML
	private JFXButton menupromotion;

	@FXML
	private JFXTextField searchclient;

	@FXML
	private AnchorPane opacitypane;

	@FXML
	private AnchorPane drawPane;

	@FXML
	private JFXTextField emailclienttf;

	@FXML
	private Label companylb;

	@FXML
	private TableColumn<Client, String> emailcol;

	@FXML
	private TableView<Client> idtabviewclient;

	@FXML
	private JFXButton menulogout;

	@FXML
	private Label typelb;

	@FXML
	private ImageView drawimage;
	Notification notif = new Notification();
	Logger logger = Logger.getLogger("testlog");

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
	void addclientButtonAction(ActionEvent event) {
		if (test()) {
			Client client = new Client(firstnameclienttf.getText(), lastnameclienttf.getText(),
					Integer.parseInt(phoneclienttf.getText()), emailclienttf.getText(), companyclienttf.getText(),
					typeclientcombo.getSelectionModel().getSelectedItem(), 0f);
			CMR.save(client);
			clear();
			try {
				idtabviewclient.setItems(null);
				ObservableList<Client> items = FXCollections.observableArrayList(CMR.findAll());
				idtabviewclient.setItems(items);
			} catch (Exception e) {

				logger.log(Level.INFO, "problem show client tabview");
			}
		}
	}

	public boolean test() {
		if ((firstnameclienttf.getText().equals(""))) {

			notif.notf_false("PROBLEM", "INVALID FIRST NAME");
			return false;
		}
		if ((lastnameclienttf.getText().equals(""))) {

			notif.notf_false("PROBLEM", "INVALID LAST NAME");
			return false;
		}
		if ((companyclienttf.getText().equals(""))) {

			notif.notf_false("PROBLEM", "INVALID COMPANY");
			return false;
		}
		if ((typeclientcombo.getSelectionModel().getSelectedItem().equals(""))) {

			notif.notf_false("PROBLEM", "INVALID TYPE");
			return false;
		}
		if ((phoneclienttf.getText().equals("")) || (phoneclienttf.getText().length() != 8)
				|| (!(phoneclienttf.getText().matches("[0-9]*")))) {

			notif.notf_false("PROBLEM", "INVALID TELEPHONE NUMBER");
			return false;
		}
		if ((!emailclienttf.getText()
				.matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+"))
				|| (emailclienttf.getText().equals(""))) {
			notif.notf_false("PROBLEM", "INVALID EMAIL");
			return false;
		}
		return true;
	}

	@FXML
	void deleteclientButtonAction(ActionEvent event) {
		Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
		alerte.setTitle("confirmation");
		alerte.setHeaderText("wloud you delete client ?");
		Optional<ButtonType> result = alerte.showAndWait();
		if (result.get() == ButtonType.OK) {
			CMR.delete(CMR.find(idtabviewclient.getSelectionModel().getSelectedItem().getIdClient()));
			clear();
			typeclientcombo.getItems().addAll("Loyal Customer", "Normal Customer");
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
				ObservableList<Client> items = FXCollections.observableArrayList(CMR.findAll());
				idtabviewclient.setItems(items);
			} catch (Exception e) {

				logger.log(Level.INFO, "problem show client tabview");
			}
		}
	}

	@FXML
	void editclientButtonAction(ActionEvent event) {

		Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
		alerte.setTitle("confirmation");
		alerte.setHeaderText("would you update client");
		Optional<ButtonType> result = alerte.showAndWait();
		if (result.get() == ButtonType.OK) {
			TableRow<Client> row = new TableRow<>();
			if (test()) {
				Client client = new Client(idtabviewclient.getSelectionModel().getSelectedItem().getIdClient(),
						firstnameclienttf.getText(), lastnameclienttf.getText(),
						Integer.parseInt(phoneclienttf.getText()), emailclienttf.getText(), companyclienttf.getText(),
						typeclientcombo.getSelectionModel().getSelectedItem());
				CMR.update(client);
				notif.notf_right("Upadate Client", "successfully updated");
				clear();
				try {
					idtabviewclient.setItems(null);
					ObservableList<Client> items = FXCollections.observableArrayList(CMR.findAll());
					idtabviewclient.setItems(items);
				} catch (Exception e) {

					logger.log(Level.INFO, "problem show client tabview");
				}
			}
		}

	}

	public void clear() {
		firstnameclienttf.setText("");
		firstnamelb.setText("");
		lastnameclienttf.setText("");
		lastnamelb.setText("");
		phoneclienttf.setText("");
		phonelb.setText("");
		companyclienttf.setText("");
		companylb.setText("");
		emailclienttf.setText("");
		emaillb.setText("");
		notelb.setText("");
		typelb.setText("");
	}

	@FXML
	void clearButtonAction(ActionEvent event) {
		clear();
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
		firstnameclienttf.setText(idtabviewclient.getSelectionModel().getSelectedItem().getFirstName());
		firstnamelb.setText(idtabviewclient.getSelectionModel().getSelectedItem().getFirstName());
		lastnameclienttf.setText(idtabviewclient.getSelectionModel().getSelectedItem().getLastName());
		lastnamelb.setText(idtabviewclient.getSelectionModel().getSelectedItem().getLastName());
		phoneclienttf.setText(Integer.toString(idtabviewclient.getSelectionModel().getSelectedItem().getPhone()));
		phonelb.setText(Integer.toString(idtabviewclient.getSelectionModel().getSelectedItem().getPhone()));
		companyclienttf.setText(idtabviewclient.getSelectionModel().getSelectedItem().getCompany());
		companylb.setText(idtabviewclient.getSelectionModel().getSelectedItem().getCompany());
		emailclienttf.setText(idtabviewclient.getSelectionModel().getSelectedItem().getEmail());
		emaillb.setText(idtabviewclient.getSelectionModel().getSelectedItem().getEmail());
		notelb.setText(Float.toString(idtabviewclient.getSelectionModel().getSelectedItem().getNote()));
		typelb.setText(idtabviewclient.getSelectionModel().getSelectedItem().getType());
	}

	ServiceLocator s = ServiceLocator.getInstance(); // Service Locator is invocated one time and can be used multiple
														// times.
	OrdersserviceRemote CMRR = (OrdersserviceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/Ordersservice!tn.esprit.b3.esprit1718b3erp.conatactmangment.OrdersserviceRemote");
	ContactMangmentRemote CMR = (ContactMangmentRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/ContactMangmentService!tn.esprit.b3.esprit1718b3erp.conatactmangment.ContactMangmentRemote");
	PromotionserviceRemote CMPROM = (PromotionserviceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/Promotionservice!tn.esprit.b3.esprit1718b3erp.conatactmangment.PromotionserviceRemote");
	 ProductserviceRemote CMPR = (ProductserviceRemote) s.getProxy(
				"esprit1718b3erp-ear/esprit1718b3erp-service/Productservice!tn.esprit.b3.esprit1718b3erp.conatactmangment.ProductserviceRemote");
		SendMail send=new SendMail();
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
		List<Object[]> bestclientlist = null;
		bestclientlist = CMRR.BestClient();
		String client, countoperationclient;
		List<Client> listclient=null;
		listclient=CMR.findAll();
		for(int k=0;k<listclient.size();k++)
		{
		for (int i = 0; i < bestclientlist.size(); i++) {
			countoperationclient = Arrays.deepToString(bestclientlist.get(i)).substring(1, 2);
			client = Arrays.deepToString(bestclientlist.get(i)).substring(4,
					Arrays.deepToString(bestclientlist.get(i)).length() - 1);
			if(listclient.get(k).getFirstName().equals(client))
			{
				Float d=0f;
				d=(float) ((Integer.parseInt(countoperationclient)*10)/(CMRR.findAll().size()));
				listclient.get(k).setNote(d);
				CMR.update(listclient.get(k));
			}
		}}
		

		// init
		typeclientcombo.getItems().addAll("Loyal Customer", "Normal Customer");
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
			ObservableList<Client> items = FXCollections.observableArrayList(CMR.findAll());
			idtabviewclient.setItems(items);
		} catch (Exception e) {

			logger.log(Level.INFO, "problem show client tabview client crud controlleur");
		}
		idtabviewclient.setRowFactory(new Callback<TableView<Client>, TableRow<Client>>() {
			@Override
			public TableRow<Client> call(TableView<Client> tableView) {
				final TableRow<Client> row = new TableRow<>();
				final ContextMenu contextMenu = new ContextMenu();
				final MenuItem showMenuItem = new MenuItem("Show");

				showMenuItem.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						Load.setSend(idtabviewclient.getSelectionModel().getSelectedItem().getIdClient());
						FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("showclient.fxml"));
						Parent root1;
						try {
							root1 = (Parent) fxmlLoader.load();
							Stage stage = new Stage();
							stage.setScene(new Scene(root1));
							stage.show();
							Stage stage2 = (Stage) idtabviewclient.getScene().getWindow();
							stage2.close();
						} catch (IOException e) {
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
