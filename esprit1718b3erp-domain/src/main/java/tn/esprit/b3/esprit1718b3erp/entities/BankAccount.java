package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import java.lang.Float;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: BankAccount
 *
 */
@Entity

public class BankAccount implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String BIC;
	private String BankName;
	private Float Balance;
	private String Type;
	private String Status;
	private String Country;
	private String BRef;
	@OneToMany
	List<Payement> p;
	@OneToMany(mappedBy="b")
	private List<Payement> pay;
	private static final long serialVersionUID = 1L;
    static float cash=0;
	public static float getCash() {
		return cash;
	}
	public static void setCash(float cash) {
		BankAccount.cash = cash;
	}
	public BankAccount() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getBIC() {
		return this.BIC;
	}

	public void setBIC(String BIC) {
		this.BIC = BIC;
	}    
	public Float getBalance() {
		return this.Balance;
	}

	public void setBalance(Float Balance) {
		this.Balance = Balance;
	}   
	public String getType() {
		return this.Type;
	}

	public void setType(String Type) {
		this.Type = Type;
	}   
	public String getStatus() {
		return this.Status;
	}

	public void setStatus(String Status) {
		this.Status = Status;
	}
	public String getBankName() {
		return BankName;
	}
	public void setBankName(String bankName) {
		BankName = bankName;
	}
	public BankAccount(String bIC, String bankName, Float balance, String type, String status, String country,
			String bRef) {
		super();
		BIC = bIC;
		BankName = bankName;
		Balance = balance;
		Type = type;
		Status = status;
		Country = country;
		BRef = bRef;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getBRef() {
		return BRef;
	}
	public void setBRef(String bRef) {
		BRef = bRef;
	}
	
	@Override
	public String toString()
	{
		return this.BankName+" - "+this.Type;
		
	}
   
}
