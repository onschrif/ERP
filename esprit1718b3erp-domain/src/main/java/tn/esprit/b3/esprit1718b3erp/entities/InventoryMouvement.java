package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: InventoryMouvement
 *
 */
@Entity

public class InventoryMouvement implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idInventoryMouvement;
	private String operationName;
	private Date date;
	private int quantity;
	
	@Transient
	private int nbrMvtPrd;
	
	private static final long serialVersionUID = 1L;
	
	
	
	public InventoryMouvement( String operationName, Date date, int quantity,
			Product product) {
		super();
		this.operationName = operationName;
		this.date = date;
		this.quantity = quantity;
		this.product = product;
	}

	@ManyToOne(cascade=CascadeType.MERGE)
	private Product product ;


	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public InventoryMouvement() {
		super();
	}   
	public int getIdInventoryMouvement() {
		return this.idInventoryMouvement;
	}

	public void setIdInventoryMouvement(int idInventoryMouvement) {
		this.idInventoryMouvement = idInventoryMouvement;
	}   
	public String getOperationName() {
		return this.operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}   
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
   
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getNbrMvtPrd() {
		return nbrMvtPrd;
	}
	public void setNbrMvtPrd(int nbrMvtPrd) {
		this.nbrMvtPrd = nbrMvtPrd;
	}
	
	
   
}