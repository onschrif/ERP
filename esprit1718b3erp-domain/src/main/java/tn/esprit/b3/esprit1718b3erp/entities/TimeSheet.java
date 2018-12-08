package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: TimeSheet
 *
 */
@Entity

public class TimeSheet implements Serializable {

	   
	@Id
	private int idTimeSheet;
	private static final long serialVersionUID = 1L;

	public TimeSheet() {
		super();
	}   
	public int getIdTimeSheet() {
		return this.idTimeSheet;
	}

	public void setIdTimeSheet(int idTimeSheet) {
		this.idTimeSheet = idTimeSheet;
	}
   
}
