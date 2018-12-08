package tn.esprit.b3.esprit1718b3erp.salesservices;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.b3.esprit1718b3erp.entities.BankAccount;
import tn.esprit.b3.esprit1718b3erp.entities.Client;
import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Expense;
import tn.esprit.b3.esprit1718b3erp.entities.Opportunity;
import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;

/**
 * Session Bean implementation class opportunityService
 */
@Stateless
@LocalBean
public class opportunityService extends GenericDAO<Opportunity>implements opportunityServiceRemote, opportunityServiceLocal {

	@PersistenceContext
	private EntityManager em;
	public opportunityService() {
		super(Opportunity.class);
    }
	
	 public Employee FindEmployeeByFirst(String firstName){
      	 Query query = em.createQuery("SELECT C from Employee C where C.firstName=:FirstName");
        	query.setParameter("FirstName", firstName);
   	    Object c=query.getSingleResult();
   	    Employee c2=(Employee)c;
   	    return c2;
  	
      }
	
	 @Override
	 public int calopp(String firstName){
	    	List<Opportunity> ba = new ArrayList<Opportunity>();
		 Query query = em.createQuery("SELECT C from Opportunity C where C.opoortunityStatus=:FirstName");
        	query.setParameter("FirstName", firstName);
           return query.getResultList().size();
 }
      }
   		
           	
      
  

