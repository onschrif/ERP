package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Assets
 *
 */
@Entity

public class Assets implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int idAsset;
	private String name;
	private String ref;

	private String type;
	private float priceOnPurchase;
	private String state;
	private float amortizationRate;
	@ManyToOne
	@JoinColumn(name="Responsable")
	private Employee emp;
	
	
	public Employee getEmployee() {
		return emp;
	}

	public void setEmployee(Employee employee) {
		this.emp = employee;
	}
	@Column(nullable = false, columnDefinition = "float default 0")
	private float capacity;
	@Column(nullable = false, columnDefinition = "float default 0")
	private float consumption;
	public int getIdAsset() {
		return idAsset;
	}

	public Assets(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	public void setIdAsset(int idAsset) {
		this.idAsset = idAsset;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getPriceOnPurchase() {
		return priceOnPurchase;
	}

	public void setPriceOnPurchase(float priceOnPurchase) {
		this.priceOnPurchase = priceOnPurchase;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public float getAmortizationRate() {
		return amortizationRate;
	}

	public void setAmortizationRate(float amortizationRate) {
		this.amortizationRate = amortizationRate;
	}

	public float getCapacity() {
		return capacity;
	}

	public void setCapacity(float capacity) {
		this.capacity = capacity;
	}

	public float getConsumption() {
		return consumption;
	}

	public void setConsumption(float consumption) {
		this.consumption = consumption;
	}

	public String getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(String dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}
	private String dateOfPurchase;
	
	private static final long serialVersionUID = 1L;

	public Assets() {
		super();
	}

	public Assets(String name, String type, float priceOnPurchase, String state, float amortizationRate, float capacity,
			float consumption, String dateOfPurchase,String ref) {
		super();
		this.name = name;
		this.type = type;
		this.priceOnPurchase = priceOnPurchase;
		this.state = state;
		this.amortizationRate = amortizationRate;
		this.capacity = capacity;
		this.consumption = consumption;
		this.dateOfPurchase = dateOfPurchase;
		this.ref=ref;
	}
	
	public Assets(String name, String type, float priceOnPurchase, String state, float amortizationRate, float capacity,
			float consumption, String dateOfPurchase,String ref,Employee emp) {
		super();
		this.name = name;
		this.type = type;
		this.priceOnPurchase = priceOnPurchase;
		this.state = state;
		this.amortizationRate = amortizationRate;
		this.capacity = capacity;
		this.consumption = consumption;
		this.dateOfPurchase = dateOfPurchase;
		this.ref=ref;
		this.emp=emp;
	}

}
