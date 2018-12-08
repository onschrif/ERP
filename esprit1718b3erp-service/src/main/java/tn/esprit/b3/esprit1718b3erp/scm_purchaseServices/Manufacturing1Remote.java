package tn.esprit.b3.esprit1718b3erp.scm_purchaseServices;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Remote
public interface Manufacturing1Remote extends IGenericDAO<Orders> {
	
	public List<Orders> FindAllorderW();

}
