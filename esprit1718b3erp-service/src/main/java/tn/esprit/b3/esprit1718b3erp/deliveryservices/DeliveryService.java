package tn.esprit.b3.esprit1718b3erp.deliveryservices;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.b3.esprit1718b3erp.entities.BankAccount;
import tn.esprit.b3.esprit1718b3erp.entities.BillOfMaterial;
import tn.esprit.b3.esprit1718b3erp.entities.Client;
import tn.esprit.b3.esprit1718b3erp.entities.Delivery;
import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.entities.Payement;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;

/**
 * Session Bean implementation class OrderService
 */
@Stateless
@LocalBean
public class DeliveryService extends GenericDAO<Delivery> implements DeliveryServiceRemote {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
	
    public DeliveryService() {
		super(Delivery.class);
    }


	 @Override
	 public int caldel(String firstName){
	    	List<Delivery> ba = new ArrayList<Delivery>();
		 Query query = em.createQuery("SELECT C from Delivery C where C.deliveryAdress=:FirstName");
      	query.setParameter("FirstName", firstName);
         return query.getResultList().size();
}
	
   
	
}   
           

