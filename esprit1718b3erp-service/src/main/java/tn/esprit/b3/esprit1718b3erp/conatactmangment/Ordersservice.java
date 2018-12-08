package tn.esprit.b3.esprit1718b3erp.conatactmangment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.b3.esprit1718b3erp.entities.Client;
import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;

/**
 * Session Bean implementation class Ordersservice
 */
@Stateless
public class Ordersservice extends GenericDAO<Orders> implements OrdersserviceRemote, OrdersserviceLocal {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public Ordersservice() {
    	    	super(Orders.class);
  
    }

	@Override
	public List FavProduct(int id) {
		Query qry = em.createNativeQuery("SELECT COUNT(o.idClient),o.idProduct from Orders o  WHERE o.idClient=:l GROUP BY o.idProduct").setParameter("l", id);		
			 return	qry.getResultList();	
	}   
	@Override
	public List BestClient() {
		Query qry = em.createNativeQuery("SELECT COUNT(c.firstName),c.firstName from Orders o, Client c where o.idClient=c.idClient GROUP BY o.idClient");		
			 return	qry.getResultList();	
	}   
	   @Override
		public List CountOrderWIthDate() {
			Query qry = em.createNativeQuery("SELECT COUNT(idOrder),RecommendedDate FROM Orders GROUP by RecommendedDate");		
				 return	qry.getResultList();	
		}   
}
