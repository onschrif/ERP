package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

//import javafx.scene.control.CheckBox;


/**
 * Entity implementation class for Entity: Product
 *
 */
@Entity

public class Product implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idProduct;
	private String name;
	private String ref;
	private long EAN13 ;
	private int purchesed;
	private int sold;
	private String type;
	private double price;
	private double discount;
	private String category;
	private String um;
	private String nature ;
	private String description ;
	
	private int quantityToOrder;
	@OneToMany(mappedBy="p",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Orders> orders;
	@OneToMany(mappedBy="pro",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<PurchaseOrder> purchaseorder;
	
	//Stock 
	private int quantity;
	private int MinQuantity;
	private int MaxQuantity;	
	private String MethodResupply;
	private int weight;
	private static final long serialVersionUID = 1L;


	//master
	//@ManyToMany(mappedBy="products",fetch=FetchType.EAGER)
	//private List<BillOfMaterial> BillOfMaterials;
	
	public List<Orders> getOrders() {
		return orders;
	}


	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}


	@OneToMany(mappedBy="ProductFils",fetch=FetchType.EAGER)
	private List<BillOfMaterial> billOfMaterials1;
	
	@OneToMany(mappedBy="ProductPere",fetch=FetchType.EAGER)
	private List<BillOfMaterial> billOfMaterials;
	
	
	@ManyToOne(cascade=CascadeType.MERGE)
	private InventoryLocation inventoryLocation ;


	@OneToMany(mappedBy="product")
	private List<InventoryMouvement> inventoryMouvements ;
	
	@OneToMany(mappedBy="pr_product")
	private List<Production> production  ;

	
	public Product() {
		super();
	}
	

	public Product(String name, String ref, long eAN13, int purchesed, int sold, String type, double price,
			double discount, String category, String um, String nature, String description, int qte) {
		super();
		this.name = name;
		this.ref = ref;
		EAN13 = eAN13;
		this.purchesed = purchesed;
		this.sold = sold;
		this.type = type;
		this.price = price;
		this.discount = discount;
		this.category = category;
		this.um = um;
		this.nature = nature;
		this.description = description;
		this.quantity=qte;
	}


	public Product(int idProduct, String name, String type, double price, String category, String nature) {
		super();
		this.idProduct = idProduct;
		this.name = name;
		this.type = type;
		this.price = price;
		this.category = category;
		this.nature = nature;
	}


	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	public int getQuantityToOrder() {
		return quantityToOrder;
	}


	public void setQuantityToOrder(int quantityToOrder) {
		this.quantityToOrder = quantityToOrder;
	}

	public Product(int idProduct, String name, String ref, long eAN13, int purchesed, int sold, String type,
			double price, double discount, String category, String um, String nature, String description, int quantity,
			int quantityToOrder, List<BillOfMaterial> billOfMaterials1, List<BillOfMaterial> billOfMaterials) {
		super();
		this.idProduct = idProduct;
		this.name = name;
		this.ref = ref;
		EAN13 = eAN13;
		this.purchesed = purchesed;
		this.sold = sold;
		this.type = type;
		this.price = price;
		this.discount = discount;
		this.category = category;
		this.um = um;
		this.nature = nature;
		this.description = description;
		this.quantity = quantity;
		this.quantityToOrder = quantityToOrder;
		this.billOfMaterials1 = billOfMaterials1;
		this.billOfMaterials = billOfMaterials;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public long getEAN13() {
		return EAN13;
	}

	public void setEAN13(long eAN13) {
		EAN13 = eAN13;
	}

	public int getPurchesed() {
		return purchesed;
	}

	public void setPurchesed(int purchesed) {
		this.purchesed = purchesed;
	}

	public int getSold() {
		return sold;
	}

	public void setSold(int sold) {
		this.sold = sold;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUm() {
		return um;
	}

	public void setUm(String um) {
		this.um = um;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}   

	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public List<BillOfMaterial> getBillOfMaterials1() {
		return billOfMaterials1;
	}


	public void setBillOfMaterials1(List<BillOfMaterial> billOfMaterials1) {
		this.billOfMaterials1 = billOfMaterials1;
	}


	public List<BillOfMaterial> getBillOfMaterials() {
		return billOfMaterials;
	}


	public void setBillOfMaterials(List<BillOfMaterial> billOfMaterials) {
		this.billOfMaterials = billOfMaterials;
	}


	public int getMinQuantity() {
		return MinQuantity;
	}


	public void setMinQuantity(int minQuantity) {
		MinQuantity = minQuantity;
	}


	public int getMaxQuantity() {
		return MaxQuantity;
	}


	public void setMaxQuantity(int maxQuantity) {
		MaxQuantity = maxQuantity;
	}


	public InventoryLocation getInventoryLocation() {
		return inventoryLocation;
	}


	public void setInventoryLocation(InventoryLocation inventoryLocation) {
		this.inventoryLocation = inventoryLocation;
	}


	public List<InventoryMouvement> getInventoryMouvements() {
		return inventoryMouvements;
	}


	public void setInventoryMouvements(List<InventoryMouvement> inventoryMouvements) {
		this.inventoryMouvements = inventoryMouvements;
	}


	public String getMethodResupply() {
		return MethodResupply;
	}


	public void setMethodResupply(String methodResupply) {
		MethodResupply = methodResupply;
	}


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}

	
}
