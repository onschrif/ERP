package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import java.lang.Float;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Expense
 *
 */
@Entity

public class Expense implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Float amount;
	private String status;
	private String Type;
	private String date;
	@ManyToOne
	@JoinColumn(name="Responsible")
	private Employee emp;
	private String Source;
	public String getSource() {
		return Source;
	}
	public void setSource(String source) {
		Source = source;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	private static final long serialVersionUID = 1L;

	public Expense() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public Float getAmount() {
		return this.amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}   
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}   
	public String getType() {
		return this.Type;
	}

	public void setType(String Type) {
		this.Type = Type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Expense(Float amount, String status, String type, String date, Employee emp, String source) {
		super();
		this.amount = amount;
		this.status = status;
		Type = type;
		this.date = date;
		this.emp = emp;
		Source = source;
	}
   
}
