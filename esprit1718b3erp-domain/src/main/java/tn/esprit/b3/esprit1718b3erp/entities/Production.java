package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ProductionUnit
 *
 */
@Entity

public class Production implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idProduction;
	private int qteToProduce;
	
	private Date startDay ;
	private Date EndDay;
	
	private int numberHeures;
	
	
	@ManyToOne(cascade=CascadeType.MERGE)
	private Product pr_product ;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	private Employee pr_employee ;
	
	private static final long serialVersionUID = 1L;

	public Production() {
		super();
	}

	public int getIdProduction() {
		return idProduction;
	}

	public void setIdProduction(int idProduction) {
		this.idProduction = idProduction;
	}

	public Date getStartDay() {
		return startDay;
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	public Date getEndDay() {
		return EndDay;
	}

	public void setEndDay(Date endDay) {
		EndDay = endDay;
	}

	public int getNumberHeures() {
		return numberHeures;
	}

	public void setNumberHeures(int numberHeures) {
		this.numberHeures = numberHeures;
	}

	public Product getPr_product() {
		return pr_product;
	}

	public void setPr_product(Product pr_product) {
		this.pr_product = pr_product;
	}

	public Employee getPr_employee() {
		return pr_employee;
	}

	public void setPr_employee(Employee pr_employee) {
		this.pr_employee = pr_employee;
	}

	public int getQteToProduce() {
		return qteToProduce;
	}

	public void setQteToProduce(int qteToProduce) {
		this.qteToProduce = qteToProduce;
	}   
	
	
	
	
}
