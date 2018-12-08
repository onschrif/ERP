package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Resupply
 *
 */
@Entity

public class Resupply implements Serializable {

	   
	@Id
	private int idResupply;
	private double price;
	private String quantity;
	private static final long serialVersionUID = 1L;

	public Resupply() {
		super();
	}   
	public int getIdResupply() {
		return this.idResupply;
	}

	public void setIdResupply(int idResupply) {
		this.idResupply = idResupply;
	}   
	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}   
	public String getQuantity() {
		return this.quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
   
}
