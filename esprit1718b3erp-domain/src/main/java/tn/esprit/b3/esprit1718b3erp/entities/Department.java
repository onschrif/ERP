package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Department
 *
 */
@Entity

public class Department implements Serializable {

	   
	@Id
	private int idDepartment;
	private String departmentName;
	private static final long serialVersionUID = 1L;

	public Department() {
		super();
	}   
	public int getIdDepartment() {
		return this.idDepartment;
	}

	public void setIdDepartment(int idDepartment) {
		this.idDepartment = idDepartment;
	}   
	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
   
}
