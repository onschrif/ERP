package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: InventoryLocation
 *
 */
@Entity

public class InventoryLocation implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idInventoryLocation;
	private String locationName;
	private String adress;
	private String state;
	private static final long serialVersionUID = 1L;
	
	
	@OneToMany(mappedBy="inventoryLocation")
	private List<Product> products ;


	public InventoryLocation() {
		super();
	}   
	
	
	
	
	public InventoryLocation(String locationName, String adress, String state) {
		super();
		this.locationName = locationName;
		this.adress = adress;
		this.state = state;
	}




	public int getIdInventoryLocation() {
		return this.idInventoryLocation;
	}

	public void setIdInventoryLocation(int idInventoryLocation) {
		this.idInventoryLocation = idInventoryLocation;
	}   
	public String getLocationName() {
		return this.locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}   
	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}   
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
   
}
