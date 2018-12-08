package tn.esprit.b3.esprit1718b3erp.app.client.contactmangment;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.ContactMangmentRemote;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.ContactMangmentRemote2;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.OrdersserviceRemote;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.ProdcutmouvRemote;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.ProductserviceRemote;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.PromotionserviceRemote;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.PurchaseOrdersserviceRemote;
import tn.esprit.b3.esprit1718b3erp.entities.BillOfMaterial;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;

public class StatController implements Initializable {
    @FXML
    private JFXButton menuinsaclient;
	@FXML
	private AnchorPane drawPane;
	@FXML
	private AnchorPane opacitypane;
	@FXML
	private TreeTableColumn<String, String> stat;

	@FXML
	private LineChart<?, ?> lineChart;

	@FXML
	private PieChart Piechart2;

	@FXML
	private BarChart<?, ?> Barchart1;

	@FXML
	private AnchorPane ahmedclientpane;

	@FXML
	private BarChart<?, ?> Barchart;

	@FXML
	private ImageView drawimage;
	@FXML
	private ImageView scrennshot;
	@FXML
	private JFXButton menulogout;
	@FXML
	private JFXButton menupromotion;
	@FXML
	private JFXButton menuclientmangmet;
	@FXML
	private JFXButton menusuppliersmangment;
	@FXML
	private TreeTableView<String> treestat;
	ServiceLocator s = ServiceLocator.getInstance(); // Service Locator is invocated one time and can be used multiple
														// times.
	ProductserviceRemote CMPR = (ProductserviceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/Productservice!tn.esprit.b3.esprit1718b3erp.conatactmangment.ProductserviceRemote");
	PromotionserviceRemote CMPROM = (PromotionserviceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/Promotionservice!tn.esprit.b3.esprit1718b3erp.conatactmangment.PromotionserviceRemote");
	ProdcutmouvRemote CMPM = (ProdcutmouvRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/Prodcutmouv!tn.esprit.b3.esprit1718b3erp.conatactmangment.ProdcutmouvRemote");
	ContactMangmentRemote2 CMS = (ContactMangmentRemote2) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/ContactMangmentService2!tn.esprit.b3.esprit1718b3erp.conatactmangment.ContactMangmentRemote2");
	PurchaseOrdersserviceRemote CMPO = (PurchaseOrdersserviceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/PurchaseOrdersservice!tn.esprit.b3.esprit1718b3erp.conatactmangment.PurchaseOrdersserviceRemote");
	OrdersserviceRemote CMRR = (OrdersserviceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/Ordersservice!tn.esprit.b3.esprit1718b3erp.conatactmangment.OrdersserviceRemote");
	ContactMangmentRemote CMR = (ContactMangmentRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/ContactMangmentService!tn.esprit.b3.esprit1718b3erp.conatactmangment.ContactMangmentRemote");

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
	    void goinsclient(ActionEvent event) throws IOException {
		 Parent root = FXMLLoader.load(getClass().getResource("InsClient.fxml"));
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

		List<Object[]> bestclientlist = null;
		bestclientlist = CMRR.BestClient();
		String client, countoperationclient;
		XYChart.Series series = new XYChart.Series();
		for (int i = 0; i < bestclientlist.size(); i++) {
			countoperationclient = Arrays.deepToString(bestclientlist.get(i)).substring(1, 2);
			client = Arrays.deepToString(bestclientlist.get(i)).substring(4,
					Arrays.deepToString(bestclientlist.get(i)).length() - 1);
			series.getData().addAll(new XYChart.Data<>(client, Integer.parseInt(countoperationclient)));
		}
		Barchart.getData().addAll(series);

		List<Object[]> bestsupplist = null;
		bestsupplist = CMPO.BestSuppliers();
		String supp, countoperationsupp;
		XYChart.Series series1 = new XYChart.Series();
		for (int i = 0; i < bestsupplist.size(); i++) {

			countoperationsupp = Arrays.deepToString(bestsupplist.get(i)).substring(1, 2);
			supp = Arrays.deepToString(bestsupplist.get(i)).substring(4,
					Arrays.deepToString(bestsupplist.get(i)).length() - 1);
			series1.getData().addAll(new XYChart.Data<>(supp, Integer.parseInt(countoperationsupp)));
		}
		Barchart1.getData().addAll(series1);

		List<Object[]> listorder = null;
		listorder = CMRR.CountOrderWIthDate();
		String date1, countorder;
		List<Object[]> listpurorder = null;
		listpurorder = CMPO.CountOrderWIthDate2();
		String date2, countpurorder;
		XYChart.Series series2 = new XYChart.Series();
		XYChart.Series series3 = new XYChart.Series();
		for (int i = 0; i < listorder.size(); i++) {
			int index2 = Arrays.deepToString(listorder.get(i)).indexOf(",");
			countorder = Arrays.deepToString(listorder.get(i)).substring(1, index2);
			date1 = Arrays.deepToString(listorder.get(i)).substring(4,
					Arrays.deepToString(listorder.get(i)).length() - 1);
			series2.getData().addAll(new XYChart.Data(date1, Integer.parseInt(countorder)));
		}

		for (int i = 0; i < listpurorder.size(); i++) {
			int index = Arrays.deepToString(listpurorder.get(i)).indexOf(",");
			countpurorder = Arrays.deepToString(listpurorder.get(i)).substring(1, index);
			date2 = Arrays.deepToString(listpurorder.get(i)).substring(4,
					Arrays.deepToString(listpurorder.get(i)).length() - 1);
			series3.getData().addAll(new XYChart.Data(date2, Integer.parseInt(countpurorder)));
		}

		lineChart.getData().addAll(series2, series3);
		
		//pie chart 
		List<Stat> liststat = new ArrayList<>();
		Stat stat1 = new Stat("Client", CMR.findAll().size());
		Stat stat2 = new Stat("Suppliers", CMS.findAll().size());
		Stat stat3 = new Stat("Promotion", CMPROM.findAll().size());
		Stat stat4 = new Stat("Orders", CMRR.findAll().size());
		Stat stat5 = new Stat("PurchaseOrders", CMPO.findAll().size());
		liststat.add(stat1);
		liststat.add(stat2);
		liststat.add(stat3);
		liststat.add(stat4);
		liststat.add(stat5);

		ObservableList<PieChart.Data> listt = FXCollections.observableArrayList();
		for (int i = 0; i < liststat.size(); i++) {
			listt.addAll(new PieChart.Data(liststat.get(i).getStatname(), liststat.get(i).getNbr()));
		}
		Piechart2.setData(listt);
		Piechart2.setStartAngle(90);
		//tree table view
		TreeItem<String> root = new TreeItem<>("STATISTICS");

		TreeItem<String> fils = new TreeItem<>(Integer.toString(liststat.get(0).getNbr()));
		TreeItem<String> fils2 = new TreeItem<>(Integer.toString(liststat.get(1).getNbr()));
		TreeItem<String> fils3 = new TreeItem<>(Integer.toString(liststat.get(2).getNbr()));
		TreeItem<String> fils4 = new TreeItem<>(Integer.toString(liststat.get(3).getNbr()));
		TreeItem<String> parent1 = new TreeItem<>(liststat.get(0).getStatname());
		TreeItem<String> parent2 = new TreeItem<>(liststat.get(1).getStatname());
		TreeItem<String> parent3 = new TreeItem<>(liststat.get(2).getStatname());
		TreeItem<String> parent4 = new TreeItem<>(liststat.get(4).getStatname());
		parent1.getChildren().setAll(fils);
		parent2.getChildren().setAll(fils2);
		parent3.getChildren().setAll(fils3);
		parent4.getChildren().setAll(fils4);
		root.getChildren().setAll(parent1, parent2, parent3, parent4);
		stat.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<String, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<String, String> param) {
						return new SimpleStringProperty(param.getValue().getValue());
					}

				});
		;

		treestat.setRoot(root);
		treestat.setShowRoot(false);
		//screnn shot
		scrennshot.setOnMouseClicked(event9 -> {		
				Robot robot;
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
				LocalDate localDate = LocalDate.now();
				try {
					robot = new Robot();
					String a=dtf.format(localDate).toString();
					  BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
				        ImageIO.write(screenShot, "JPG", new File("stat.jpg"));
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      

        });
	}
}
