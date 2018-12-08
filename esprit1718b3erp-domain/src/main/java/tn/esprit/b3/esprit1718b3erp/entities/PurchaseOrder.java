package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: PurchaseOrder
 *
 */
@Entity

public class PurchaseOrder implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int IdPurchaseOrder;
	private Date date;
	private String status;
	private String type;
	private int QuantityToPurchase ;
	private static final long serialVersionUID = 1L;
	@ManyToOne 
	@JoinColumn(name="idSuppliers",referencedColumnName="idSuppliers",insertable=true,updatable=true)
	private Suppliers sup;
	@ManyToOne 
	@JoinColumn(name="idProduct",referencedColumnName="idProduct",insertable=true,updatable=true)
	private Product pro;
	public PurchaseOrder() {
		super();
	}   
	
	
	
	public PurchaseOrder(Date date, String status, String type, int quantityToPurchase, Product pro) {
		super();
		this.date = date;
		this.status = status;
		this.type = type;
		QuantityToPurchase = quantityToPurchase;
		this.pro = pro;
	}




	public int getIdPurchaseOrder() {
		return this.IdPurchaseOrder;
	}

	public void setIdPurchaseOrder(int IdPurchaseOrder) {
		this.IdPurchaseOrder = IdPurchaseOrder;
	}   
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}   
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public Suppliers getSup() {
		return sup;
	}
	public void setSup(Suppliers sup) {
		this.sup = sup;
	}
	public Product getPro() {
		return pro;
	}
	public void setPro(Product pro) {
		this.pro = pro;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}



	public int getQuantityToPurchase() {
		return QuantityToPurchase;
	}



	public void setQuantityToPurchase(int quantityToPurchase) {
		QuantityToPurchase = quantityToPurchase;
	}
	

   
}
