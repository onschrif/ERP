package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: PaymentSheet
 *
 */
@Entity

public class PaymentSheet implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPaymentSheet;
	@Temporal(TemporalType.DATE)
	private Date date;
	private float cnam;
	private float salaryBrute;
	private String status;
	@ManyToOne
	private Employee employee;
	private static final long serialVersionUID = 1L;

	public PaymentSheet() {
		super();
	}   
	public int getIdPaymentSheet() {
		return this.idPaymentSheet;
	}

	public void setIdPaymentSheet(int idPaymentSheet) {
		this.idPaymentSheet = idPaymentSheet;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getCnam() {
		return cnam;
	}
	public void setCnam(float cnam) {
		this.cnam = cnam;
	}
	public float getSalaryBrute() {
		return salaryBrute;
	}
	public void setSalaryBrute(float salaryBrute) {
		this.salaryBrute = salaryBrute;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}   
}
