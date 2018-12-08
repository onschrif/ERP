package tn.esprit.b3.esprit1718b3erp.salesservices;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.b3.esprit1718b3erp.entities.Client;
import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Opportunity;
import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.entities.User;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;

/**
 * Session Bean implementation class salesService
 */
@Stateless
@LocalBean
public class salesService extends GenericDAO<Orders> implements salesServiceRemote, salesServiceLocal {
	@PersistenceContext
	private EntityManager entityManager;

    /**
     * Default constructor. 
     */
    public salesService() {
    	super(Orders.class);
    }
    
	@Override
	public Client findClientNameById(int login) {
		Client user = null;
		try {
			user = entityManager.createQuery("SELECT u FROM Client u WHERE u.idClient=:l", Client.class)
					.setParameter("l", login).getSingleResult();
		} catch (Exception e) {
		}
		return user;
	}
	
	public Employee findEmployeeNameByJobPosition(String jb) {
		Employee user = null;
		try {
			user = entityManager.createQuery("SELECT u FROM Employee u WHERE u.jobPosition=:l", Employee.class)
					.setParameter("l", jb).getSingleResult();
		} catch (Exception e) {
		}
		return user;
	}
	
public Product FindProductById(int i)
{
	Product user = null;
	try {
		user = entityManager.createQuery("SELECT u FROM Product u WHERE u.idProduct=:l", Product.class)
				.setParameter("l", i).getSingleResult();
	} catch (Exception e) {
	}
	return user;
}

}
