package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BillOfMaterialEmb implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "idProductPere")
	private Integer idProductPere;
	@Column(name = "idProductFils")
	private Integer idProductFils;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProductFils == null) ? 0 : idProductFils.hashCode());
		result = prime * result + ((idProductPere == null) ? 0 : idProductPere.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BillOfMaterialEmb other = (BillOfMaterialEmb) obj;
		if (idProductFils == null) {
			if (other.idProductFils != null)
				return false;
		} else if (!idProductFils.equals(other.idProductFils))
			return false;
		if (idProductPere == null) {
			if (other.idProductPere != null)
				return false;
		} else if (!idProductPere.equals(other.idProductPere))
			return false;
		return true;
	}

	
	
	public BillOfMaterialEmb() {
		super();
	}

	public BillOfMaterialEmb(Integer idProductPere, Integer idProductFils) {
		super();
		this.idProductPere = idProductPere;
		this.idProductFils = idProductFils;
	}

	public Integer getIdProductPere() {
		return idProductPere;
	}

	public void setIdProductPere(Integer idProductPere) {
		this.idProductPere = idProductPere;
	}

	public Integer getIdProductFils() {
		return idProductFils;
	}

	public void setIdProductFils(Integer idProductFils) {
		this.idProductFils = idProductFils;
	}
	
	

}
