package tn.esprit.b3.esprit1718b3erp.app.client.contactmangment;

import static org.junit.Assert.assertNotNull;

import java.awt.Color;
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

import javax.sound.midi.Soundbank;

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
import javafx.scene.chart.LineChart;
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
import tn.esprit.b3.esprit1718b3erp.conatactmangment.ContactMangmentRemote;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.OrdersserviceRemote;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.ProductserviceRemote;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.PromotionserviceRemote;
import tn.esprit.b3.esprit1718b3erp.entities.Client;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.entities.Promotion;
import tn.esprit.b3.esprit1718b3erp.utilities.ServiceLocator;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.scene.chart.AreaChart;

public class ClientCrudController2 implements Initializable{
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String FILE_HEADER = "id,firstName,lastName,phone,email,company,type,note";
    @FXML
    private Label firstnamelb;

    @FXML
    private JFXButton menuclientmangmet;

    @FXML
    private Label phonelb;
    @FXML
    private JFXButton menuinsaclient;
    @FXML
    private AnchorPane ahmedclientpane;

    @FXML
    private Label notelb;

    @FXML
    private Label emaillb;

    @FXML
    private JFXButton menusuppliersmangment;

    @FXML
    private JFXButton csvbtn;

    @FXML
    private Label lastnamelb;

    @FXML
    private JFXButton menustatistics;

    @FXML
    private JFXButton menupromotion;

    @FXML
    private AnchorPane drawPane;

    @FXML
    private AnchorPane opacitypane;

    @FXML
    private Label companylb;

    @FXML
    private ImageView drawimage;
  
    @FXML
    private ImageView qrimg;

    @FXML
    private JFXButton menulogout;

    @FXML
    private Label typelb;
    
    @FXML
    private TableColumn<Fav,String> productnamecol;
    @FXML
    private TableColumn<Fav,Integer> quantitycol;
    @FXML
    private TableView<Fav> regulartabview;
    @FXML
    private ImageView back;

    @FXML
    private PieChart Piechart;
    @FXML
    private BarChart<?, ?> Barchart;

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
    
     FileWriter fileWriter = new FileWriter(client.getFirstName().concat(client.getLastName().concat(".csv")));
     fileWriter.append(FILE_HEADER.toString());
     fileWriter.append(NEW_LINE_SEPARATOR);
     fileWriter.append(String.valueOf(client.getIdClient()));
     fileWriter.append(COMMA_DELIMITER);
     fileWriter.append(String.valueOf(client.getFirstName()));
     fileWriter.append(COMMA_DELIMITER);
     fileWriter.append(String.valueOf(client.getLastName()));
     fileWriter.append(COMMA_DELIMITER);
     fileWriter.append(String.valueOf(client.getPhone()));
     fileWriter.append(COMMA_DELIMITER);
     fileWriter.append(String.valueOf(client.getCompany()));
     fileWriter.append(COMMA_DELIMITER);
     fileWriter.append(String.valueOf(client.getEmail()));
     fileWriter.append(COMMA_DELIMITER);
     fileWriter.append(String.valueOf(client.getType()));
     fileWriter.append(COMMA_DELIMITER);
     fileWriter.append(String.valueOf(client.getNote()));
     fileWriter.append(NEW_LINE_SEPARATOR);
     fileWriter.flush();
     fileWriter.close();

    }

    ServiceLocator s=ServiceLocator.getInstance(); // Service Locator is invocated one time and can be used multiple times.
	ContactMangmentRemote CMR=(ContactMangmentRemote) s.getProxy("esprit1718b3erp-ear/esprit1718b3erp-service/ContactMangmentService!tn.esprit.b3.esprit1718b3erp.conatactmangment.ContactMangmentRemote");
	OrdersserviceRemote CMRR=(OrdersserviceRemote) s.getProxy("esprit1718b3erp-ear/esprit1718b3erp-service/Ordersservice!tn.esprit.b3.esprit1718b3erp.conatactmangment.OrdersserviceRemote");

	Client client = CMR.find(Load.getSend());
	void qrcode() {
		 
	        QRCodeWriter qrCodeWriter = new QRCodeWriter();
	        String myWeb = "* Type : client \n *firstname :" + client.getFirstName() +"\n *lastname : "+client.getLastName() +"\n *PHONE : "
	                + client.getPhone()+ "\n *Company : " + client.getCompany()
	                + " \n *EMAIL : " + client.getEmail()+" \n *TYPE : "+client.getType()+" \n *NOTE : "+client.getNote();

	        int width = 300;
	        int height = 300;
	        String fileType ="png";

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
	PromotionserviceRemote CMPROM = (PromotionserviceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/Promotionservice!tn.esprit.b3.esprit1718b3erp.conatactmangment.PromotionserviceRemote");
    ProductserviceRemote CMPR = (ProductserviceRemote) s.getProxy(
			"esprit1718b3erp-ear/esprit1718b3erp-service/Productservice!tn.esprit.b3.esprit1718b3erp.conatactmangment.ProductserviceRemote");
	SendMail send=new SendMail();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 opacitypane.setVisible(false);

	        FadeTransition fadeTransition=new FadeTransition(Duration.seconds(0.5),opacitypane);
	        fadeTransition.setFromValue(1);
	        fadeTransition.setToValue(0);
	        fadeTransition.play();

	        TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(0.5),drawPane);
	        translateTransition.setByX(-600);
	        translateTransition.play();
	        drawimage.setOnMouseClicked(event -> {


	            opacitypane.setVisible(true);

	            FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(0.5),opacitypane);
	            fadeTransition1.setFromValue(0);
	            fadeTransition1.setToValue(0.15);
	            fadeTransition1.play();

	            TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(0.5),drawPane);
	            translateTransition1.setByX(+600);
	            translateTransition1.play();
	        });

	        opacitypane.setOnMouseClicked(event -> {



	            FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(0.5),opacitypane);
	            fadeTransition1.setFromValue(0.15);
	            fadeTransition1.setToValue(0);
	            fadeTransition1.play();

	            fadeTransition1.setOnFinished(event1 -> {
	                opacitypane.setVisible(false);
	            });


	            TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(0.5),drawPane);
	            translateTransition1.setByX(-600);
	            translateTransition1.play();
	        });
	        back.setOnMouseClicked(event -> {
	        	 Parent root;
				try {
					root = FXMLLoader.load(getClass().getResource("clientcrud.fxml"));
					  Scene scene = new Scene(root);
			             Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  
			             app_stage.setScene(scene);
			             app_stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
	           
	            });
	        Logger logger = Logger.getLogger("testlog");
      List<Object[]> l=null;
      
     
      List<Fav> ll = new ArrayList<Fav>();
      l=CMRR.FavProduct(Load.getSend());
      int nbr=0;
      for(int i=0;i<l.size();i++)
      {  
    	 // System.out.println(Arrays.deepToString(l.get(i)));
         String count,id,name;
         count=Arrays.deepToString(l.get(i)).substring(1,2);
         id=Arrays.deepToString(l.get(i)).substring(4,5);
        // System.out.println(id);
        // System.out.println(count);
         Product product =CMR.FindProduct(Integer.parseInt(id));
         name=product.getName();
         productnamecol.setCellValueFactory(new PropertyValueFactory<>("name"));
         quantitycol.setCellValueFactory(new PropertyValueFactory<>("count"));
        // fav.setCount(Integer.parseInt(count));
         //fav.setName(name);
         Fav fav=new Fav(name,Integer.parseInt(count));
         ll.add(fav);
         nbr++;
        // System.out.println(fav.getName());
         //System.out.println(fav.getCount());
         }
    
      Date d=new Date(1111, 11, 1);
      ObservableList<PieChart.Data> listt= FXCollections.observableArrayList();
       for(int i = 0; i < ll.size(); i++){
              listt.addAll(new PieChart.Data(ll.get(i).getName(),ll.get(i).getCount()));
           }
      Piechart.setData(listt);
      Piechart.setStartAngle(90);
     
       XYChart.Series series=new XYChart.Series();   
for(int i = 0; i < ll.size(); i++){
	float s=(ll.get(i).getCount())/nbr;
	Double doubleResult =(double) s;
          series.getData().addAll(new XYChart.Data<>(ll.get(i).getName(),doubleResult));}
      Barchart.getData().addAll(series);       
   if(ll.size()>3) {
	   for(int k=4;k<ll.size();k++)
	   ll.remove(k);
   }
         try {
             regulartabview.setItems(null);
         	ObservableList<Fav> items = FXCollections.observableArrayList(ll);
         	regulartabview.setItems(items);
        }
        catch (Exception e) {

     	   logger.log(Level.INFO,"problem show client tabview");
     
      }
         /*
	 Client client = CMR.find(Load.getSend());
	 List<Promotion> listpromotion=CMPROM.findAll();
	 for(int i=0;i<listpromotion.size();i++)
	 {
		 Product product=CMPR.find(listpromotion.get(i).getIdProduct());
		 for(int j=0;j<ll.size();j++)
		 {
			 if(product.getName().equals(ll.get(i).getName())) {
				 send.Promotion(client.getEmail(), product.getName());
			 }
		 }
		
	 }*/
    	 firstnamelb.setText(client.getFirstName());
    	 lastnamelb.setText(client.getLastName());
    	 phonelb.setText(Integer.toString(client.getPhone()));
    	 companylb.setText(client.getCompany());
    	 emaillb.setText(client.getEmail());
    	 notelb.setText(Float.toString(client.getNote()));
    	 typelb.setText(client.getType());
    qrcode();		
	}

   
}
