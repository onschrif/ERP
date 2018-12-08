package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Project
 *
 */
@Entity

public class Project implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idProject;
	@OneToMany(mappedBy = "project")//,cascade=CascadeType.MERGE)
	private List<Task> projectTasks;
	@OneToMany(mappedBy = "project")//,cascade=CascadeType.MERGE)
	private List<Qualification> projectQualifications;
	private String name;
	private String description;
	private String startDate;
	private String finishDate;
	private float advancement = (float) 0.0;
	private float budget = (float) 0.0;
	private String state = "planned";
	private static final long serialVersionUID = 1L;
	@ManyToMany//(fetch = FetchType.EAGER)
	private List<Employee> members;

	public Project(int idProject, String name, String description, String startDate, String finishDate) {
		super();
		this.idProject = idProject;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.finishDate = finishDate;
	}

	public Project(String name, String description, String startDate, String finishDate) {
		super();
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.finishDate = finishDate;
	}

	public Project() {
		super();
	}

	public int getIdProject() {
		return this.idProject;
	}

	public List<Task> getProjectTasks() {
		return projectTasks;
	}

	public void setProjectTasks(List<Task> projectTasks) {
		this.projectTasks = projectTasks;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getAdvancement() {
		return this.advancement;
	}

	public void setAdvancement(float advancement) {
		this.advancement = advancement;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Employee> getMembers() {
		return members;
	}

	public void setMembers(List<Employee> members) {
		this.members = members;
	}

	public float getBudget() {
		return budget;
	}

	public void setBudget(float budget) {
		this.budget = budget;
	}

}
