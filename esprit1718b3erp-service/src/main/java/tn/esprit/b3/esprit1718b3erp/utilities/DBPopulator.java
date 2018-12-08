
package tn.esprit.b3.esprit1718b3erp.utilities;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import tn.esprit.b3.esprit1718b3erp.conatactmangment.ContactMangmentLocal;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.ContactMangmentLocal2;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.OrdersserviceLocal;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.PurchaseOrdersserviceLocal;
import tn.esprit.b3.esprit1718b3erp.conatactmangment.PurchaseOrdersserviceRemote;
import tn.esprit.b3.esprit1718b3erp.entities.BillOfMaterial;
import tn.esprit.b3.esprit1718b3erp.entities.BillOfMaterialEmb;
import tn.esprit.b3.esprit1718b3erp.entities.Client;
import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.InventoryLocation;
import tn.esprit.b3.esprit1718b3erp.entities.InventoryMouvement;
import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.entities.OrdersPk;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.entities.PurchaseOrder;
import tn.esprit.b3.esprit1718b3erp.entities.Suppliers;
import tn.esprit.b3.esprit1718b3erp.entities.User;
import tn.esprit.b3.esprit1718b3erp.scm_InventoryServices.InventoryLocationServicesLocal;
import tn.esprit.b3.esprit1718b3erp.scm_InventoryServices.InventoryMovementServicesLocal;
import tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.BillOfMaterialServicesLocal;
import tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesLocal;
import tn.esprit.b3.esprit1718b3erp.services.UserServiceLocal;
import tn.esprit.b3.esprit1718erp.accessservices.EmployeeServiceLocal;

@Singleton
@Startup
public class DBPopulator {
	@EJB
	private UserServiceLocal userServiceLocal;
	@EJB
	private BillOfMaterialServicesLocal billOfMaterialServicesLocal;
	@EJB
	private ProductServicesLocal productServicesLocal;
	@EJB
	private InventoryLocationServicesLocal inventoryLocationServicesLocal;
	@EJB
	private InventoryMovementServicesLocal inventoryMovementServicesLocal;
	
	@EJB
	private ContactMangmentLocal contactMangmentLocal;
	@EJB
	private ContactMangmentLocal2 contactMangmentLocal2;
	@EJB
	private OrdersserviceLocal ordersservicelocal;
	
	@EJB
	private PurchaseOrdersserviceLocal purchaseOrdersserviceLocal;
	
	@EJB
	private EmployeeServiceLocal employeeServiceLocal;
	
	public DBPopulator() {
	}

	@PostConstruct
	public void init() throws ParseException {
//		User user = new User("user", "u", "u", "user@bitbox.tn");
//		userServiceLocal.update(user);
//		
//		//Location
//		InventoryLocation loc1 = new InventoryLocation("Depot Tunis", "Cite El Khadhra", "enable");
//		InventoryLocation loc2 = new InventoryLocation("Depot Sfax", " Route El Ain", "enable");
//		inventoryLocationServicesLocal.save(loc1);
//		inventoryLocationServicesLocal.save(loc2);
//
//		Product product = new Product("NameProduct1", "AAA111", 12345678, 1, 1, "Stored Product", 10, 0, "category1", "l", "Finished Product", "description of product 1",0);
//		Product product2 = new Product("NameProduct2", "BBB222", 87654321, 0, 1, "Consumable Product", 200, 0, "category2", "h", "Semi-finished Product", "description of product 2",0);
//		Product product3 = new Product("NameProduct3", "CCC333", 12345678, 1, 0, "Stored Product",30, 0, "category3", "kg", "Raw Material", "description of product 3",0);
//		Product product4 = new Product("NameProduct4", "DDD444", 19281928, 1, 0, "Stored Product",40, 0, "category3", "Kg", "Raw Material", "description of product 4",0);
//		Product product5 = new Product("NameProduct5", "EEE555", 55555555, 1, 1, "Stored Product",50, 0, "category1", "Kg", "Finished Product", "description of product 5",0);
//		Product product6 = new Product("NameProduct6", "FFF666", 66666666, 1, 1, "Stored Product",60, 0, "category2", "Kg", "Packaging", "description of product 6",0);
//		Product product7 = new Product("NameProduct7", "GGG777", 7777777, 1, 1, "Service",70, 0, "category1", "Heures", "Semi-finished Product", "description of product 7",0);
//		Product product8 = new Product("NameProduct8", "HHH888", 88888888, 1, 1, "Stored Product",80, 0, "category3", "Kg", "Raw Material", "description of product 8",0);
//		Product product9 = new Product("NameProduct9", "ZZZ999", 9999999, 1, 1, "Stored Product",90, 0, "category1", "Kg", "Raw Material", "description of product 9",0);
//
//		//Update Stock
//		product3.setQuantity(30);
//		product3.setInventoryLocation(loc1);
//		
//		product5.setQuantity(5);
//		product5.setInventoryLocation(loc1);
//		
//		product.setQuantity(1);
//		product.setInventoryLocation(loc2);
//		
//		product8.setQuantity(80);
//		product8.setInventoryLocation(loc1);
//				
//		//Movement
//		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		Date date0 = dateFormat.parse("2018/04/08 08:26:47");
//		Date date1 = dateFormat.parse("2018/04/10 10:55:08");
//		Date date2 = dateFormat.parse("2018/04/11 15:31:22");
//		Date date3 = dateFormat.parse("2018/04/12 16:26:50");
//		Date date = new Date();
//		InventoryMouvement mvt1 = new InventoryMouvement("Add New",date0,product3.getQuantity(),product3);
//		InventoryMouvement mvt2 = new InventoryMouvement("Add New",date,product5.getQuantity(),product5);
//		InventoryMouvement mvt3 = new InventoryMouvement("Add New",date,product.getQuantity(),product);
//		InventoryMouvement mvt4 = new InventoryMouvement("Add New",date,product8.getQuantity(),product8);
//		
//		inventoryMovementServicesLocal.save(mvt1);
//		inventoryMovementServicesLocal.save(mvt2);
//		inventoryMovementServicesLocal.save(mvt3);
//		inventoryMovementServicesLocal.save(mvt4);
//		
//		InventoryMouvement mvt31 = new InventoryMouvement("update decrease",date1,10,product3);
//		InventoryMouvement mvt32 = new InventoryMouvement("update decrease",date2,15,product3);
//		InventoryMouvement mvt33 = new InventoryMouvement("update increment",date3,45,product3);
//		
//		inventoryMovementServicesLocal.save(mvt31);
//		inventoryMovementServicesLocal.save(mvt32);
//		inventoryMovementServicesLocal.save(mvt33);
//		
//		//Adjustment
//		product3.setMinQuantity(20);
//		product3.setMaxQuantity(100);
//		
//		product5.setMinQuantity(10);
//		product5.setMaxQuantity(100);
//		
//		product.setMinQuantity(5);
//		product.setMaxQuantity(50);
//		
//		product8.setMinQuantity(50);
//		product8.setMaxQuantity(200);
//	
//		
//		productServicesLocal.save(product);
//		productServicesLocal.save(product2);
//		productServicesLocal.save(product3);
//		productServicesLocal.save(product4);
//		productServicesLocal.save(product5);
//		productServicesLocal.save(product6);
//		productServicesLocal.save(product7);
//		productServicesLocal.save(product8);
//		productServicesLocal.save(product9);
//		
//		
//
//		//BillOfMaterial
//		BillOfMaterialEmb billEmb= new BillOfMaterialEmb(2, 4);	
//		BillOfMaterialEmb billEmb1= new BillOfMaterialEmb(2, 6);	
//		BillOfMaterial bill= new BillOfMaterial(billEmb,2,40);
//		BillOfMaterial bill1= new BillOfMaterial(billEmb1,2,60);
//		billOfMaterialServicesLocal.save(bill);
//		billOfMaterialServicesLocal.save(bill1);
//		
//		//Orders
//		Orders o = new Orders("ORD123","2018/04/10","2018/05/01",20,"Waiting",product8);
//		Orders o1 = new Orders("ORD188","2018/04/08","2018/04/25",20,"Waiting",product5);
//		ordersservicelocal.save(o);
//		ordersservicelocal.save(o1);
//
//		
//		//Purchase Order 
//		PurchaseOrder po = new PurchaseOrder(date0,"Waiting","Out of Stock",20,product3);
//		PurchaseOrder po1 = new PurchaseOrder(date1,"Canceled","Out of Stock",100,product3);
//		PurchaseOrder po2 = new PurchaseOrder(date3,"Confirmed","Out of Stock",45,product3);
//		purchaseOrdersserviceLocal.save(po);
//		purchaseOrdersserviceLocal.save(po1);
//		purchaseOrdersserviceLocal.save(po2);
//		
//		
//		//Employee SupplyChain
//		Employee e = new Employee();
//		e.setIdEmployee("SC123");e.setFirstName("hamza");e.setNic(123);e.setDepartment("Supply Chain");e.setEmail("hamza@esprit.tn");
//		employeeServiceLocal.save(e);
//		
//		Client client = new Client("zzz","eeee",22222,"deeded@sz.fr","njhj","jhjhjhjh",1.2f);
//		contactMangmentLocal.update(client);
//		Suppliers suppliers=new Suppliers("STILL","MEGRINE",1234944,"STILL@ESPRIT.TN");
//	contactMangmentLocal2.update(suppliers);
      /*  Orders orders = new Orders(1,"11/12/12",client);
=======
	/*
		SimpleDateFormat dateformat=new SimpleDateFormat("dd/MM/yyyy");
		Date date=dateformat.parse("11/11/2017");
        OrdersPk ordersPk=new OrdersPk(product.getIdProduct(),client.getIdClient(),date);
        Orders orders=new Orders(ordersPk,client,product);
>>>>>>> branch 'master' of http://bitbox.tn:2015/root/esprit1718b3erp.git
        ordersservicelocal.update(orders);*/

	}
}
