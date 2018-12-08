package tn.esprit.b3.esprit1718b3erp.scm_purchaseServices;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Local
public interface Manufacturing1Local extends IGenericDAO<Orders>{
	
	public List<Orders> FindAllorderW();

}
