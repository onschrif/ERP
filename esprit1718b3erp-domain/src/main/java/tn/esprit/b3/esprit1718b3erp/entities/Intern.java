package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: intern
 *
 */
@Entity

public class Intern implements Serializable {

	   
	@Id
	private int IdIntern;
	private String firstName;
	private String lastName;
	private static final long serialVersionUID = 1L;

	public Intern() {
		super();
	}   
	public int getIdIntern() {
		return this.IdIntern;
	}

	public void setIdIntern(int IdIntern) {
		this.IdIntern = IdIntern;
	}   
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}   
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
   
}
