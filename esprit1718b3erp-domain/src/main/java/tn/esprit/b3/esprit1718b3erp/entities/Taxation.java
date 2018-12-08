package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Taxation
 *
 */
@Entity

public class Taxation implements Serializable {

	   
	@Id
	private int idTaxation;
	private String name;
	private String state;
	private float vat_value;
	private static final long serialVersionUID = 1L;

	public Taxation() {
		super();
	}   
	public int getIdTaxation() {
		return this.idTaxation;
	}

	public void setIdTaxation(int idTaxation) {
		this.idTaxation = idTaxation;
	}   
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}   
	public float getVat_value() {
		return this.vat_value;
	}

	public void setVat_value(float vat_value) {
		this.vat_value = vat_value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Taxation(String name, String state, float vat_value) {
		super();
		this.name = name;
		this.state = state;
		this.vat_value = vat_value;
	}
	
	
	
}
