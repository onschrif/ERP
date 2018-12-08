package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Booking
 *
 */
@Entity

public class Booking implements Serializable {

	   
	@Id
	private int idBoocking;
	private String dateBoocking;
	private static final long serialVersionUID = 1L;

	public Booking() {
		super();
	}   
	public int getIdBoocking() {
		return this.idBoocking;
	}

	public void setIdBoocking(int idBoocking) {
		this.idBoocking = idBoocking;
	}   
	public String getDateBoocking() {
		return this.dateBoocking;
	}

	public void setDateBoocking(String dateBoocking) {
		this.dateBoocking = dateBoocking;
	}
   
}
