package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Opportunity
 *
 */
@Entity

public class Opportunity implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idOpportunity;
	private String startOpportunityDtae;
	private String opoortunityStatus;
	private String CloseOpprtunityDate;
	private int Progression;
	public String getCloseOpprtunityDate() {
		return CloseOpprtunityDate;
	}
	public void setCloseOpprtunityDate(String closeOpprtunityDate) {
		CloseOpprtunityDate = closeOpprtunityDate;
	}
	public int getProgression() {
		return Progression;
	}
	public void setProgression(int progression) {
		Progression = progression;
	}
	@OneToOne
	private Orders o;
	@ManyToOne
	private Employee E;
	@ManyToOne
	private Client c;
	

	private static final long serialVersionUID = 1L;

	public Opportunity() {
		super();
	}   
	public int getIdOpportunity() {
		return this.idOpportunity;
	}

	public void setIdOpportunity(int idOpportunity) {
		this.idOpportunity = idOpportunity;
	}
	public Employee getE() {
		return E;
	}
	public void setE(Employee e) {
		E = e;
	}
	public String getStartOpportunityDtae() {
		return startOpportunityDtae;
	}
	public void setStartOpportunityDtae(String startOpportunityDtae) {
		this.startOpportunityDtae = startOpportunityDtae;
	}
	public String getOpoortunityStatus() {
		return opoortunityStatus;
	}
	public void setOpoortunityStatus(String opoortunityStatus) {
		this.opoortunityStatus = opoortunityStatus;
	}

	public Orders getO() {
		return o;
	}
	public void setO(Orders o) {
		this.o = o;
	}
	public Client getC() {
		return c;
	}
	public void setC(Client c) {
		this.c = c;
	}
	public Opportunity(String startOpportunityDtae, String opoortunityStatus, 
			int progression, Employee e, Client c) {
		super();
		this.startOpportunityDtae = startOpportunityDtae;
		this.opoortunityStatus = opoortunityStatus;
		
		Progression = progression;
		E = e;
		this.c = c;
	}
   
}
