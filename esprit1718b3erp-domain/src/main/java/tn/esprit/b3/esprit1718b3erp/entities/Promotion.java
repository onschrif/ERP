package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Promotion
 *
 */
@Entity

public class Promotion implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPromotion;
	private int idProduct;
	private Date startdate;
	private Date enddate;
	private int quantity;
	private static final long serialVersionUID = 1L;

	public Promotion() {
		super();
	}

	public Promotion(int idPromotion, int idProduct, Date startdate, Date enddate, int quantity) {
		super();
		this.idPromotion = idPromotion;
		this.idProduct = idProduct;
		this.startdate = startdate;
		this.enddate = enddate;
		this.quantity = quantity;
	}

	public Promotion(int idProduct, Date startdate, Date enddate, int quantity) {
		super();
		this.idProduct = idProduct;
		this.startdate = startdate;
		this.enddate = enddate;
		this.quantity = quantity;
	}

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

   
}
