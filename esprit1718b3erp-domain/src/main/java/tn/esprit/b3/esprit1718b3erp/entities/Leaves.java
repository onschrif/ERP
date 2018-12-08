package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Leaves
 *
 */
@Entity

public class Leaves implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idLeave;
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date finalDate;
	private int daysRequested;
	private int restOfLeaves;
	private String type;
	private String description;
	private String status;
	@ManyToOne
	private Employee employee;
	private static final long serialVersionUID = 1L;

	public Leaves() {
		super();
	}
	
	public int getIdLeave() {
		return this.idLeave;
	}

	public void setIdLeave(int idLeave) {
		this.idLeave = idLeave;
	}   
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}   
	public int getDaysRequested() {
		return this.daysRequested;
	}

	public void setDaysRequested(int daysRequested) {
		this.daysRequested = daysRequested;
	}   
	public int getRestOfLeaves() {
		return this.restOfLeaves;
	}

	public void setRestOfLeaves(int restOfLeaves) {
		this.restOfLeaves = restOfLeaves;
	}   
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
  
}
