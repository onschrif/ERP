package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: BillsOfMaterials
 *
 */
@Entity

public class BillOfMaterial implements Serializable {

	@EmbeddedId
	private BillOfMaterialEmb billOfMaterialEmb;


	private double priceT;
	private int quantity;
	private static final long serialVersionUID = 1L;

	@Transient
	private int nbrComponent;
	
	//@ManyToMany(fetch=FetchType.EAGER)
	//private List<Product> products ;
	
	
	@ManyToOne
	@JoinColumn(name="idProductFils",referencedColumnName="idProduct",insertable=false,updatable=false)
	private Product ProductFils;
	
	@ManyToOne
	@JoinColumn(name="idProductPere",referencedColumnName="idProduct",insertable=false,updatable=false)
	private Product ProductPere;


	
	
	
	public BillOfMaterial() {
		super();
	}


	public BillOfMaterial(Product productFils, Product productPere, int quantity,double priceT) {
		super();
		this.priceT = priceT;
		ProductFils = productFils;
		ProductPere = productPere;
		this.quantity = quantity;
	}
	
	


	public BillOfMaterial(BillOfMaterialEmb billOfMaterialEmb, int quantity, double priceT) {
		super();
		this.billOfMaterialEmb = billOfMaterialEmb;
		this.priceT = priceT;
		this.quantity = quantity;
	}


	public double getPriceT() {
		return priceT;
	}

	public void setPriceT(double priceT) {
		this.priceT = priceT;
	}

	public Product getProductFils() {
		return ProductFils;
	}

	public void setProductFils(Product productFils) {
		ProductFils = productFils;
	}

	public Product getProductPere() {
		return ProductPere;
	}

	public void setProductPere(Product productPere) {
		ProductPere = productPere;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public BillOfMaterialEmb getBillOfMaterialEmb() {
		return billOfMaterialEmb;
	}


	public void setBillOfMaterialEmb(BillOfMaterialEmb billOfMaterialEmb) {
		this.billOfMaterialEmb = billOfMaterialEmb;
	}


	public int getNbrComponent() {
		return nbrComponent;
	}


	public void setNbrComponent(int nbrComponent) {
		this.nbrComponent = nbrComponent;
	}

	
	

	
   
}
