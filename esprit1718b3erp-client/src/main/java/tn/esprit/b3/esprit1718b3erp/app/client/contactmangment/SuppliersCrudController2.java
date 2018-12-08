package tn.esprit.b3.esprit1718b3erp.app.client.contactmangment;

import java.awt.Color;
import javafx.event.ActionEvent;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.jfoenix.controls.JFXButton;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.ContactMangmentRemote2;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.ProductserviceRemote;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.PurchaseOrdersserviceRemote;
import tn.esprit.b3.esprit1718b3erp.entities.Client;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.entities.PurchaseOrder;
import tn.esprit.b3.esprit1718b3erp.entities.Suppliers;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;

public class SuppliersCrudController2 implements Initializable {
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String FILE_HEADER = "id,Name,adress,phone,email";
    @FXML
    private JFXButton menuinsaclient;
	@FXML
	private Label firstnamelb;

	@FXML
	private JFXButton menuclientmangmet;

	@FXML
	private Label adresslb;

	@FXML
	private Label phonelb;

	@FXML
	private ImageView back;

	@FXML
	private AnchorPane ahmedclientpane;

	@FXML
	private JFXButton csvbtn;

	@FXML
	private Label emaillb;

	@FXML
	private ImageView qrimg;

	@FXML
	private JFXButton menusuppliersmangment;

	@FXML
	private JFXButton menustatistics;

	@FXML
	private JFXButton menupromotion;

	@FXML
	private AnchorPane opacitypane;

	@FXML
	private AnchorPane drawPane;

	@FXML
	private Label companylb;

	@FXML
	private ImageView drawimage;

	@FXML
	private JFXButton menulogout;

	@FXML
	private PieChart Piechart;
	@FXML
	private BarChart<?, ?> Barchart;
	@FXML
	private TableView<History> historyview;

	@FXML
	private TableColumn<History, Date> datecol;
	@FXML
	private TableColumn<History, Integer> quantitycol;
	@FXML
	private TableColumn<History, String> productnamecol;

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
	    void goinsclient(ActionEvent event) throws IOException {
		 Parent root = FXMLLoader.load(getClass().getResource("InsClient.fxml"));
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
	void csvButtonAction(ActionEvent event) throws IOException {
		Suppliers suppliers = CMS.find(Load.getSend2());
		FileWriter fileWriter = new FileWriter(suppliers.getName().concat(".csv"));
		fileWriter.append(FILE_HEADER.toString());
		fileWriter.append(NEW_LINE_SEPARATOR);
		fileWriter.append(String.valueOf(suppliers.getIdSuppliers()));
		fileWriter.append(COMMA_DELIMITER);
		fileWriter.append(String.valueOf(suppliers.getName()));
		fileWriter.append(COMMA_DELIMITER);
		fileWriter.append(String.valueOf(suppliers.getAdress()));
		fileWriter.append(COMMA_DELIMITER);
		fileWriter.append(String.valueOf(suppliers.getPhone()));
		fileWriter.append(COMMA_DELIMITER);
		fileWriter.append(String.valueOf(suppliers.getEmail()));
		fileWriter.append(NEW_LINE_SEPARATOR);
		fileWriter.flush();
		fileWriter.close();

	}

	Logger logger = Logger.getLogger("testlog");
	ServiceLocator s = ServiceLocator.getInstance(); // Service Locator is invocated one time and can be used multiple
														// times.
	ContactMangmentRemote2 CMS = (ContactMangmentRemote2) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/ContactMangmentService2!tn.esprit.b3.esprit1718b3erp.conatactmangment.ContactMangmentRemote2");
	Suppliers suppliers = CMS.find(Load.getSend2());

	void qrcode() {

		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		String myWeb = "* Type : supplier \n *name :" + suppliers.getName() + "\n *phone : " + suppliers.getPhone()
				+ "\n *Email : " + suppliers.getEmail() + "\n *Adress : " + suppliers.getAdress();
		int width = 300;
		int height = 300;
		String fileType = "png";

		BufferedImage bufferedImage = null;
		try {
			BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
			bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			bufferedImage.createGraphics();

			Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, width, height);
			graphics.setColor(Color.BLACK);

			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					if (byteMatrix.get(i, j)) {
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}

		} catch (WriterException ex) {

		}

		qrimg.setImage(SwingFXUtils.toFXImage(bufferedImage, null));

	}

	ProductserviceRemote CMPR = (ProductserviceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/Productservice!tn.esprit.b3.esprit1718b3erp.conatactmangment.ProductserviceRemote");
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
		back.setOnMouseClicked(event -> {
			Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("supplierscrud.fxml"));
				Scene scene = new Scene(root);
				Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				app_stage.setScene(scene);
				app_stage.show();
			} catch (IOException e) {

				e.printStackTrace();
			}
			Logger logger = Logger.getLogger("testlog");
		});
		List<PurchaseOrder> purchaseOrderslist = CMPO.Findorderbysupplier(Load.getSend2());
		List<History> historieslist = new ArrayList<>();
		for (int i = 0; i < purchaseOrderslist.size(); i++) {
			History history = new History(CMPR.find(purchaseOrderslist.get(i).getPro().getIdProduct()).getName(),
					CMPR.find(purchaseOrderslist.get(i).getPro().getIdProduct()).getQuantity(),
					purchaseOrderslist.get(i).getDate());
			historieslist.add(history);
		}
		productnamecol.setCellValueFactory(new PropertyValueFactory<>("name"));
		quantitycol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		datecol.setCellValueFactory(new PropertyValueFactory<>("date"));
		try {
			historyview.setItems(null);
			ObservableList<History> items = FXCollections.observableArrayList(historieslist);
			historyview.setItems(items);
		} catch (Exception e) {

			logger.log(Level.INFO, "problem show client tabview");
		}
		ObservableList<PieChart.Data> listt = FXCollections.observableArrayList();
		for (int i = 0; i < historieslist.size(); i++) {
			listt.addAll(new PieChart.Data(historieslist.get(i).getName(), historieslist.get(i).getQuantity()));
		}
		Piechart.setData(listt);
		Piechart.setStartAngle(90);
		List<Object[]> l = null;

		l = CMPO.Nbrproduct(Load.getSend2());
		String count, namel;
		XYChart.Series series = new XYChart.Series();
		for (int i = 0; i < l.size(); i++) {
			count = Arrays.deepToString(l.get(i)).substring(1, 2);
			namel = Arrays.deepToString(l.get(i)).substring(4, Arrays.deepToString(l.get(i)).length() - 1);
			series.getData().addAll(new XYChart.Data<>(namel, Integer.parseInt(count)));
		}
		Barchart.getData().addAll(series);

		firstnamelb.setText(suppliers.getName());
		adresslb.setText(suppliers.getAdress());
		phonelb.setText(Integer.toString(suppliers.getPhone()));
		emaillb.setText(suppliers.getEmail());
		qrcode();
	}

}
