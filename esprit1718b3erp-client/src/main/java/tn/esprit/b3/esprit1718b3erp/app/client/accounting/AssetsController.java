package tn.esprit.b3.esprit1718b3erp.app.client.accounting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import org.controlsfx.control.Notifications;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import tn.esprit.b3.esprit1718b3erp.app.client.login.Login;
import tn.esprit.b3.esprit1718b3erp.assetservices.AssetServiceRemote;
import tn.esprit.b3.esprit1718b3erp.employeeservices.EmplServiceRemote;
import tn.esprit.b3.esprit1718b3erp.entities.Assets;
import tn.esprit.b3.esprit1718b3erp.entities.Sess;
import tn.esprit.b3.esprit1718b3erp.services.UserServiceRemote;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

public class AssetsController implements Initializable {

	@FXML
	AnchorPane AssetPane;
	@FXML
	JFXTextField nametf, pricetf, capacitytf, consumptiontf, searchtf;
	@FXML
	DatePicker purchasedt;
	@FXML
	ComboBox<String> typecb, statecb, respcb;
	@FXML
	JFXSlider arsl;
	@FXML
	TableView<Assets> tbassets;
	@FXML
	private TableColumn<Assets, String> col1;
	@FXML
	private TableColumn<Assets, String> col2, col3, col4;
	@FXML
	private GridPane assetgrid;
	@FXML
	private Label lbUser;
	static Boolean dash;

	ServiceLocator s = ServiceLocator.getInstance(); // Service Locator is
														// invocated one time
														// and can be used
														// multiple times.
	AssetServiceRemote ASR = (AssetServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/AssetService!tn.esprit.b3.esprit1718b3erp.assetservices.AssetServiceRemote");
	UserServiceRemote USR = (UserServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/UserService!tn.esprit.b3.esprit1718b3erp.services.UserServiceRemote");
	EmplServiceRemote ESR = (EmplServiceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/EmplService!tn.esprit.b3.esprit1718b3erp.employeeservices.EmplServiceRemote");

	
	public void assetload() {
		col1.setCellValueFactory(new PropertyValueFactory<>("ref"));
		col2.setCellValueFactory(new PropertyValueFactory<>("name"));
		col3.setCellValueFactory(new PropertyValueFactory<>("type"));
		col4.setCellValueFactory(new PropertyValueFactory<>("state"));
		tbassets.setItems(null);
		ObservableList<Assets> items = FXCollections.observableArrayList(ASR.findAll());
		tbassets.setItems(items);
	}

	public void refresh() {
		ObservableList<Assets> items = FXCollections.observableArrayList(ASR.findAll());

		tbassets.setItems(items);

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		lbUser.setText(Sess.current.getFirstName()+" "+Sess.current.getLastName());

		dash = false;
		List<String> State = Arrays.asList("Functional", "Out of Order", "In maintenance", "Retired","Reserve");
		List<String> Type = Arrays.asList("Utility Vehicle", "Staff Vehicle", "Office Gadget", "Estate", "Furniture",
				"Other");
		for (int i = 0; i < ESR.findAll().size(); i++) {
			respcb.getItems().add(ESR.findAll().get(i).toString());
		}

		statecb.getItems().addAll(State);
		typecb.getItems().addAll(Type);
		statecb.getSelectionModel().selectFirst();
		typecb.getSelectionModel().selectFirst();

		// Initial Call
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			int i;

			@Override
			public void run() {

				refresh();
				i++;
				if (i == 10) {
					t.cancel();
				}
			}
		}, 0, 6000000);

		assetload();
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
	public void home(ActionEvent event) throws IOException {
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
		TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), AssetPane);
		if (dash) {
			translateTransition1.setByX(-250);
			dash = false;
		} else {
			translateTransition1.setByX(+250);
			dash = true;
		}
		translateTransition1.play();
	}

	// End of Common Block

	@FXML
	public void addasset(ActionEvent action) throws ParseException {
		try {
			String s = typecb.getSelectionModel().getSelectedItem();
			System.out.println(respcb.getSelectionModel().getSelectedIndex());
			Assets asset = new Assets(nametf.getText(), statecb.getSelectionModel().getSelectedItem(),
					Float.valueOf(pricetf.getText()), typecb.getSelectionModel().getSelectedItem(),
					Math.round((float) arsl.getValue()), Float.valueOf(capacitytf.getText()),
					Float.valueOf(consumptiontf.getText()), purchasedt.getValue().toString(),
					"AS-" + s.substring(0, 3).toUpperCase() + "-" + nametf.getText().hashCode() + ASR.findAll().size(),
					ASR.fillemp(ESR.findAll(), respcb.getSelectionModel().getSelectedIndex()));
			if (ASR.Input_ctrl(asset)) {
				ASR.save(asset);
				refresh();
				notf("Operation Succeeded", "The asset was successfully added");
				clear();		
				try(FileWriter fw = new FileWriter(DashboardController.dest, true);
					    BufferedWriter bw = new BufferedWriter(fw);
					    PrintWriter out = new PrintWriter(bw))
					{
					    out.println("The Asset "+asset.getName()+"was added at "+LocalDateTime.now());
					} catch (IOException e) {
					}
			} else {
				notf("Operation Failed", "Chosen date is incorrect");

			}
		} catch (NumberFormatException E) {
		notf("Operation Failed", "One or more cases were not filled");
		if(nametf.getText()==null)
		{				notf("Operation Failed", "Name value is empty");
		}
		if(pricetf.getText()==null)
		{				notf("Operation Failed", "Price value is empty");
		}		
		if(consumptiontf.getText()==null)
		{				notf("Operation Failed", "Consumption value is empty");
		}
		if(capacitytf.getText()==null)
		{				notf("Operation Failed", "Capacity value is empty");
		}
		
		}
	}

	@FXML
	public void assetdelete(ActionEvent action) {
		Assets as=ASR.find(((Assets) tbassets.getSelectionModel().getSelectedItem()).getIdAsset());
		ASR.delete(ASR.find(((Assets) tbassets.getSelectionModel().getSelectedItem()).getIdAsset()));
		clear();
		refresh();
		
		try(FileWriter fw = new FileWriter(DashboardController.dest, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println("The Asset "+as.getName()+"was deleted at "+LocalDateTime.now());
			} catch (IOException e) {
			}
	}

	@FXML
	public void assetedit(ActionEvent action) throws ParseException {

		String s = typecb.getSelectionModel().getSelectedItem();

		Assets asset = ASR.find(((Assets) tbassets.getSelectionModel().getSelectedItem()).getIdAsset());
		Assets asset2 = new Assets(nametf.getText(), statecb.getSelectionModel().getSelectedItem(),
				Float.valueOf(pricetf.getText()), typecb.getSelectionModel().getSelectedItem(),
				Math.round((float) arsl.getValue()), Float.valueOf(capacitytf.getText()),
				Float.valueOf(consumptiontf.getText()), purchasedt.getValue().toString(),
				"AS-" + s.substring(0, 3).toUpperCase() + "-" + nametf.getText().hashCode() + ASR.findAll().size(),
				ASR.fillemp(ESR.findAll(), respcb.getSelectionModel().getSelectedIndex()));
		asset = asset2;
		asset.setIdAsset(((Assets) tbassets.getSelectionModel().getSelectedItem()).getIdAsset());
		if (ASR.Input_ctrl(asset)) {
			ASR.update(asset);
			refresh();
			notf("Edit Succeeded", "The asset has been edited");
			clear();
			
			try(FileWriter fw = new FileWriter(DashboardController.dest, true);
				    BufferedWriter bw = new BufferedWriter(fw);
				    PrintWriter out = new PrintWriter(bw))
				{
				    out.println("The Asset "+asset2.getName()+"was edited at "+LocalDateTime.now());
				} catch (IOException e) {
				}
		}

	}

	@FXML
	public void assetclear(ActionEvent action) {
		clear();
		refresh();
	}

	@FXML
	public void assetsearch(KeyEvent event) {
		ObservableList tableboutique = tbassets.getItems();
		searchtf.textProperty()
				.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
					if (oldValue != null && (newValue.length() < oldValue.length())) {
						tbassets.setItems(tableboutique);
					}
					String value = newValue.toLowerCase();
					ObservableList<Assets> subentries = FXCollections.observableArrayList();

					long count = tbassets.getColumns().stream().count();
					for (int i = 0; i < tbassets.getItems().size(); i++) {
						for (int j = 0; j < count; j++) {
							String entry = "" + tbassets.getColumns().get(j).getCellData(i);
							if (entry.toLowerCase().contains(value)) {
								subentries.add(tbassets.getItems().get(i));
								break;
							}
						}
					}
					tbassets.setItems(subentries);
				});
	}

	@FXML
	public void refr(ActionEvent event) {
		refresh();
	}

	void notf(String s, String s2) {
		Notifications notificationbuilder = Notifications.create().title(s).text(s2).hideAfter(Duration.seconds(5))
				.position(Pos.BOTTOM_RIGHT);
		notificationbuilder.darkStyle();
		notificationbuilder.show();
	}

	public void clear() {
		nametf.setText("");
		pricetf.setText("");
		capacitytf.setText("");
		consumptiontf.setText("");
		statecb.getSelectionModel().clearSelection();
		typecb.getSelectionModel().clearSelection();
	}

	@FXML
	public void fill() {
		Assets s = ASR.find(((Assets) tbassets.getSelectionModel().getSelectedItem()).getIdAsset());
		LocalDate localDate = LocalDate.parse(s.getDateOfPurchase());

		nametf.setText(s.getName());
		pricetf.setText(String.valueOf(s.getPriceOnPurchase()));
		capacitytf.setText(String.valueOf(s.getCapacity()));
		consumptiontf.setText(String.valueOf(s.getConsumption()));
		statecb.getSelectionModel().select(s.getState());
		typecb.getSelectionModel().select(s.getType());
		purchasedt.setValue(localDate);
		arsl.setValue((double) s.getAmortizationRate());
	}

	@FXML
	public void selection(ActionEvent event) {

		try {
			if (!typecb.getSelectionModel().getSelectedItem().equals("Utility Vehicle")) {
				consumptiontf.setEditable(false);
				consumptiontf.setText("0");
				capacitytf.setEditable(false);
				capacitytf.setText("0");
			} else {
				consumptiontf.setEditable(true);
				capacitytf.setEditable(true);

			}
		} catch (NullPointerException E) {
			System.out.println("Warning ! Null Pointer Warning");
		}
	}

	@FXML
	public void toBalance(ActionEvent event) throws IOException {
		trans("../accounting/Balance.fxml", event);
	}
	
	@FXML
	public void toTax(ActionEvent event) throws IOException {
		trans("../accounting/Taxation.fxml", event);
	}
	
	@FXML
	public void toInv(ActionEvent event) throws IOException {
		trans("../accounting/Invoice.fxml", event);
	}
	
	@FXML
	public void toExp(ActionEvent event) throws IOException {
		trans("../accounting/Expense.fxml", event);
	}
	
	@FXML
	public void toAsset(ActionEvent event) throws IOException {
		trans("../accounting/Assets.fxml", event);
	}
	
	@FXML
	public void toBanking(ActionEvent event) throws IOException {
		trans("../accounting/Banking.fxml", event);
	}

	@FXML
	public void toDashboard(ActionEvent event) throws IOException {
		trans("../accounting/Dashboard.fxml", event);
	}
	
	@FXML
	public void toCash(ActionEvent event) throws IOException {
		trans("../accounting/Cash.fxml", event);
	}
	
	
	
	
}
