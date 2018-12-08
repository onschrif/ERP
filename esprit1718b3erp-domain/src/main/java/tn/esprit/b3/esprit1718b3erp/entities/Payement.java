package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Payement
 *
 */
@Entity

public class Payement implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPayement;
	private String type;
	private String ref;
	private String PaymentDate;
	private Float amount;
	@ManyToOne
	@JoinColumn(name="Client")
	private Client C;
	@ManyToOne
	@JoinColumn(name="Beneficiary")
	private BankAccount b;
	
	public Client getC() {
		return C;
	}
	public void setC(Client c) {
		C = c;
	}
	public BankAccount getB() {
		return b;
	}
	public void setB(BankAccount b) {
		this.b = b;
	}
	private String description;
	private static final long serialVersionUID = 1L;

	public Payement() {
		super();
	}   
	public int getIdPayement() {
		return this.idPayement;
	}

	public void setIdPayement(int idPayement) {
		this.idPayement = idPayement;
	}   
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getPaymentDate() {
		return PaymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		PaymentDate = paymentDate;
	}
	public Payement(String type, String ref, String paymentDate, Float amount, Client c, BankAccount b) {
		super();
		this.type = type;
		this.ref = ref;
		PaymentDate = paymentDate;
		this.amount = amount;
		C = c;
		this.b = b;
	}
	public Payement(String type, String ref, String paymentDate, Float amount, Client c) {
		super();
		this.type = type;
		this.ref = ref;
		PaymentDate = paymentDate;
		this.amount = amount;
		C = c;
	}


	
	
	
}
