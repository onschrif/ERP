package tn.esprit.b3.esprit1718b3erp.salesservices;

import javax.ejb.Remote;

import tn.esprit.b3.esprit1718b3erp.entities.Client;
import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Remote
public interface salesServiceRemote extends IGenericDAO<Orders> {

	public Client findClientNameById(int login);
	public Employee findEmployeeNameByJobPosition(String jb);
	public Product FindProductById(int i);
}
