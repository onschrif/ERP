package tn.esprit.b3.esprit1718b3erp.orderservices;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b3.esprit1718b3erp.entities.Client;
import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Remote
public interface OrderServiceRemote extends IGenericDAO<Orders> {
	public Client FindClientByCompany(String company);
	public Product findIdProduct(String name);
    public List<Employee> findAllComm();
    public void del(Orders entity);
    public List<String> FindOrderByReference();
    public List<Orders> findAllOrders();
    public Orders  FindOrdrsByReference2(String reference);
    public List<Orders> FindOrdrsByReference1(String reference);
    public int calord(String firstName);
}
