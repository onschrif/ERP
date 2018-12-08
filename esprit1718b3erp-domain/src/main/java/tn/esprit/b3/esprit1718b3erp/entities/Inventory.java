package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Inventory
 *
 */
@Entity

public class Inventory implements Serializable {

	   
	@Id
	private int idInventory;
	private String quantity;
	private static final long serialVersionUID = 1L;

	public Inventory() {
		super();
	}   
	public int getIdInventory() {
		return this.idInventory;
	}

	public void setIdInventory(int idInventory) {
		this.idInventory = idInventory;
	}   
	public String getQuantity() {
		return this.quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
   
}
