package tn.esprit.b3.esprit1718b3erp.app.client.contactmangment;

import java.util.Date;

public class Promo {
	private int idPromotion;
	private int idProduct;
	private String name;
	private Date startdate;
	private Date enddate;
	private int quantity;
	public int getIdPromotion() {
		return idPromotion;
	}
	public void setIdPromotion(int idPromotion) {
		this.idPromotion = idPromotion;
	}
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Promo(int idPromotion, int idProduct, String name, Date startdate, Date enddate, int quantity) {
		super();
		this.idPromotion = idPromotion;
		this.idProduct = idProduct;
		this.name = name;
		this.startdate = startdate;
		this.enddate = enddate;
		this.quantity = quantity;
	}
	
}
