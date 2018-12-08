package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Delivery
 *
 */
@Entity

public class Delivery implements Serializable {

	   
	@Id
	private int idDelivery;
	private String deliveryDate;
	private String deliveryAdress;
	private int toDeliverQuantity;
	@ManyToOne
	private Orders o;
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getDeliveryAdress() {
		return deliveryAdress;
	}
	public void setDeliveryAdress(String deliveryAdress) {
		this.deliveryAdress = deliveryAdress;
	}
	public int getToDeliverQuantity() {
		return toDeliverQuantity;
	}
	public void setToDeliverQuantity(int toDeliverQuantity) {
		this.toDeliverQuantity = toDeliverQuantity;
	}

	private static final long serialVersionUID = 1L;

	public Delivery() {
		super();
	}   
	public int getIdDelivery() {
		return this.idDelivery;
	}

	public void setIdDelivery(int idDelivery) {
		this.idDelivery = idDelivery;
	}
   
}
