package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Qualification
 *
 */
@Entity

public class Qualification implements Serializable {

	   
	@Id
	private int idQualification;
	String Description;
	@ManyToOne//(cascade=CascadeType.MERGE)
	private Employee employee;
	@ManyToOne//(cascade=CascadeType.MERGE)
	private Project project;
	private static final long serialVersionUID = 1L;
	
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Qualification() {
		super();
	}   
	public int getIdQualification() {
		return this.idQualification;
	}

	public void setIdQualification(int idQualification) {
		this.idQualification = idQualification;
	}
   
}
