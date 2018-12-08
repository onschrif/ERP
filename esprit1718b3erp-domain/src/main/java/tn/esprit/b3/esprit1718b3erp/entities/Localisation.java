package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Localisation
 *
 */
@Entity

public class Localisation implements Serializable {

	   
	@Id
	private int idLocalisation;
	private float latitude;
	private float longitude;
	private static final long serialVersionUID = 1L;

	public Localisation() {
		super();
	}   
	public int getIdLocalisation() {
		return this.idLocalisation;
	}

	public void setIdLocalisation(int idLocalisation) {
		this.idLocalisation = idLocalisation;
	}   
	public float getLatitude() {
		return this.latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}   
	public float getLongitude() {
		return this.longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
   
}
