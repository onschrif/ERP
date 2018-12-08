package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Suppliers
 *
 */
@Entity

public class Suppliers implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSuppliers;
	private String name;
	private String adress;
	private int phone;
	private String email;
	private static final long serialVersionUID = 1L;
	@OneToMany(mappedBy="sup",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<PurchaseOrder> purchaseorder;
	public Suppliers(int idSuppliers, String name, String adress, int phone, String email) {
		super();
		this.idSuppliers = idSuppliers;
		this.name = name;
		this.adress = adress;
		this.phone = phone;
		this.email = email;
	}
	public Suppliers(String name, String adress, int phone, String email) {
		super();
		this.name = name;
		this.adress = adress;
		this.phone = phone;
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Suppliers() {
		super();
	}   
	public int getIdSuppliers() {
		return this.idSuppliers;
	}

	public void setIdSuppliers(int idSuppliers) {
		this.idSuppliers = idSuppliers;
	}   
	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}   
	public int getPhone() {
		return this.phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}   
	
	
   
}
