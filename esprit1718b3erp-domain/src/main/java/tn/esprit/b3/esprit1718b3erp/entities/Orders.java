package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Orders
 *
 */
@Entity
@Access(AccessType.FIELD)
public class Orders implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idOrder;
	private String referenceOrder;
	private String RecommendedDate;
	private String invoiceDate;
	private String quotationDate;
	private double discountOrder;
	private int quantityToOrder;
	private String orderState;
	private float orderAmount;
	private static String opc;
	
	
	public Orders() {
		super();
	}   
	
	@ManyToOne 
	@JoinColumn(name="idProduct",referencedColumnName="idProduct",insertable=true,updatable=true)
	private Product p;
	@ManyToOne
	@JoinColumn(name="idClient",referencedColumnName="idClient",insertable=true,updatable=true)
	private Client c;
	@OneToMany(mappedBy="o")
	private List<Delivery> lDelivery;
	@OneToOne(mappedBy="o")
	private Opportunity opp;

	
	
	public Orders(String ref ,String recommendedDate, String invoiceDate, int quantityToOrder, String orderState, Product p) {
		super();
		this.referenceOrder=ref;
		RecommendedDate = recommendedDate;
		this.invoiceDate = invoiceDate;
		this.quantityToOrder = quantityToOrder;
		this.orderState = orderState;
		this.p = p;
	}
	public String getReferenceOrder() {
		return referenceOrder;
	}
	public void setReferenceOrder(String referenceOrder) {
		this.referenceOrder = referenceOrder;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public int getQuantityToOrder() {
		return quantityToOrder;
	}
	public void setQuantityToOrder(int quantityToOrder) {
		this.quantityToOrder = quantityToOrder;
	}
	
	public String getQuotationDate() {
		return quotationDate;
	}
	public void setQuotationDate(String quotationDate) {
		this.quotationDate = quotationDate;
	}
	public List<Delivery> getlDelivery() {
		return lDelivery;
	}
	public void setlDelivery(List<Delivery> lDelivery) {
		this.lDelivery = lDelivery;
	}
	public Opportunity getOpp() {
		return opp;
	}
	public void setOpp(Opportunity opp) {
		this.opp = opp;
	}
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}   
	public String getRecommendedDate() {
		return this.RecommendedDate;
	}

	public void setRecommendedDate(String RecommendedDate) {
		this.RecommendedDate = RecommendedDate;
	}
	public Orders(String referenceOrder, String recommendedDate, String invoiceDate, String quotationDate) {
		super();
		this.idOrder = idOrder;
		this.referenceOrder = referenceOrder;
		RecommendedDate = recommendedDate;
		this.invoiceDate = invoiceDate;
		this.quotationDate = quotationDate;
		this.discountOrder = discountOrder;
		this.p = p;
		this.c = c;
		this.lDelivery = lDelivery;
		this.opp = opp;
	}
	public double getDiscountOrder() {
		return discountOrder;
	}
	public void setDiscountOrder(double discountOrder) {
		this.discountOrder = discountOrder;
	}
	public Orders(Client c, String referenceOrder, String recommendedDate, String quotationDate,
			double discountOrder) {
		super();
		this.c = c;
		this.referenceOrder = referenceOrder;
		this.RecommendedDate = recommendedDate;
		this.quotationDate = quotationDate;
		this.discountOrder = discountOrder;
	}
	public Orders(Client c,Product p, String referenceOrder, String recommendedDate, String quotationDate,
			double discountOrder) {
		super();
		this.c = c;
		this.p=p;
		this.referenceOrder = referenceOrder;
		this.RecommendedDate = recommendedDate;
		this.quotationDate = quotationDate;
		this.discountOrder = discountOrder;
	}
	public Orders(Client c, Product p,String referenceOrder, String invoiceDate, String recommendedDate, String quotationDate,
			double discountOrder,int quantityToOrder, String orderState) {
		super();
		this.referenceOrder = referenceOrder;
		RecommendedDate = recommendedDate;
		this.invoiceDate = invoiceDate;
		this.quotationDate = quotationDate;
		this.quantityToOrder = quantityToOrder;
		this.orderState = orderState;
		this.p = p;
		this.c = c;
		this.discountOrder = discountOrder;
	}
	public Orders(Client c, Product p,String referenceOrder, String invoiceDate, String recommendedDate,
			double discountOrder,int quantityToOrder, String orderState) {
		super();
		this.referenceOrder = referenceOrder;
		RecommendedDate = recommendedDate;
		this.invoiceDate = invoiceDate;
		this.quotationDate = quotationDate;
		
		this.orderState = orderState;
		this.p = p;
		this.c = c;
		this.discountOrder = discountOrder;
	}
	public Orders(Client c, Product p,String referenceOrder, String invoiceDate, String recommendedDate,
			double discountOrder,int quantityToOrder, String orderState, float orderAmount) {
		super();
		this.referenceOrder = referenceOrder;
		RecommendedDate = recommendedDate;
		this.invoiceDate = invoiceDate;
		this.quotationDate = quotationDate;
		this.quantityToOrder=quantityToOrder;
		this.orderState = orderState;
		this.p = p;
		this.c = c;
		this.discountOrder = discountOrder;
		this.orderAmount=orderAmount;
	}
	
	public Orders(Client c, Product p, String referenceOrder, String recommendedDate, String invoiceDate, String quotationDate,
			double discountOrder, int quantityToOrder) {
		super();
		
		this.referenceOrder = referenceOrder;
		RecommendedDate = recommendedDate;
		this.invoiceDate = invoiceDate;
		this.quotationDate = quotationDate;
		this.discountOrder = discountOrder;
		this.quantityToOrder = quantityToOrder;
		this.p = p;
		this.c = c;
		
	}
	
	public Orders(Client c,Product p,String invoicedate, String referenceOrder, String recommendedDate, String quotationDate,
			double discountOrder) {
		super();
		this.c = c;
		this.p=p;
		this.referenceOrder = referenceOrder;
		this.RecommendedDate = recommendedDate;
		this.quotationDate = quotationDate;
		this.discountOrder = discountOrder;
		this.invoiceDate=invoicedate;
	}
	public Product getP() {
		return p;
	}
	public void setP(Product p) {
		this.p = p;
	}
	public Client getC() {
		return c;
	}
	public void setC(Client c) {
		this.c = c;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public float getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(float orderAmount) {
		this.orderAmount = orderAmount;
	}
	public static String getOpc() {
		return opc;
	}
	public static void setOpc(String opc) {
		Orders.opc = opc;
	}
	

}
