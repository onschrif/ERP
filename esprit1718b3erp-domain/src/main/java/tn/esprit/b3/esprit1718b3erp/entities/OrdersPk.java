package tn.esprit.b3.esprit1718b3erp.entities;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
/**
 * Entity implementation class for Entity: OrdersPk
 *
 */
@Embeddable

public class OrdersPk implements Serializable {
	private int idProduct;
	private int idClient;
	private Date date;
	
	private static final long serialVersionUID = 1L;

	public OrdersPk() {
		super();
	}

	
	public OrdersPk(int idProduct, int idClient, Date date) {
		super();
		this.idProduct = idProduct;
		this.idClient = idClient;
		this.date = date;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + idClient;
		result = prime * result + idProduct;
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
		OrdersPk other = (OrdersPk) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (idClient != other.idClient)
			return false;
		if (idProduct != other.idProduct)
			return false;
		return true;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}


		
}
