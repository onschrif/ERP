package tn.esprit.b3.esprit1718b3erp.salesservices;

import javax.ejb.Remote;

import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Opportunity;
import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Remote
public interface opportunityServiceRemote extends IGenericDAO<Opportunity> {
	 public Employee FindEmployeeByFirst(String firstName);
	 public int calopp(String firstName);
}
