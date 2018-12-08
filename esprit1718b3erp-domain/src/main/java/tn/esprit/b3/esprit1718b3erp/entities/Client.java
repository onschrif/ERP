package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Client
 *
 */
@Entity

public class Client implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idClient;
	private String firstName;
	private String lastName;
	private int phone;
	private String email;
	private String company;
	private String type;
	private float note ;
	@OneToMany(mappedBy="c")
	private List<Opportunity> lOpportunity;
	@OneToMany(mappedBy="c",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Orders> orders;
	@OneToMany(mappedBy="C")
	private List<Payement> pay;
	private static final long serialVersionUID = 1L;
    
	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public Client() {
		super();
	}   
	
	public Client(int idClient, String firstName, String lastName, int phone, String email, String company, String type) {
		super();
		this.idClient = idClient;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.company = company;
		this.type = type;
	
	}

	public Client(String firstName, String lastName, int phone, String email, String company, String type,
			float note) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.company = company;
		this.type = type;
		this.note = note;
	}

	public int getIdClient() {
		return this.idClient;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getPhone() {
		return phone;
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
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getNote() {
		return note;
	}
	public void setNote(float note) {
		this.note = note;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
   
}
