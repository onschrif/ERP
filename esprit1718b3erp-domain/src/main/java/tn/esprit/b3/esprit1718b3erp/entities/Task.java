package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Task
 *
 */
@Entity

public class Task implements Serializable {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idTask;
	private String name;
	private String description;
	private int planifiedHoures;
	private int completedHoures;
	private String state="onGoing";
	private String deadline;
	private static final long serialVersionUID = 1L;
	@ManyToOne//(cascade=CascadeType.MERGE)
	private Employee employee;
	@ManyToOne//(cascade=CascadeType.MERGE)
	private Project project;

	public Task(String name, String description, int planifiedHoures, int completedHoures,
			String deadline, Employee employee, Project project) {
		super();
		this.name = name;
		this.description = description;
		this.planifiedHoures = planifiedHoures;
		this.completedHoures = completedHoures;
		this.deadline = deadline;
		this.employee = employee;
		this.project = project;
	}

	public Task() {
		super();
	}

	public Task(String name, String description, int planifiedHoures, int completedHoures, String deadline) {
		super();
		this.name = name;
		this.description = description;
		this.planifiedHoures = planifiedHoures;
		this.completedHoures = completedHoures;
		this.deadline = deadline;
	}

	public int getIdTask() {
		return this.idTask;
	}

	public void setIdTask(int idTask) {
		this.idTask = idTask;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPlanifiedHoures() {
		return planifiedHoures;
	}

	public void setPlanifiedHoures(int planifiedHoures) {
		this.planifiedHoures = planifiedHoures;
	}

	public int getCompletedHoures() {
		return completedHoures;
	}

	public void setCompletedHoures(int completedHoures) {
		this.completedHoures = completedHoures;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}
