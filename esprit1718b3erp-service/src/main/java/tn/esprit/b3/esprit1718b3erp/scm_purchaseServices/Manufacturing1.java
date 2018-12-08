package tn.esprit.b3.esprit1718b3erp.scm_purchaseServices;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;

/**
 * Session Bean implementation class Manufacturing1
 */
@Stateless
public class Manufacturing1 extends GenericDAO<Orders>  implements Manufacturing1Remote, Manufacturing1Local {

	
		@PersistenceContext
		private EntityManager em;
    /**
     * Default constructor. 
     */
    public Manufacturing1() {
        // TODO Auto-generated constructor stub
    	super(Orders.class);
    }
    
    
    public List<Orders> FindAllorderW(){
        Query query = em.createQuery("SELECT O from Orders O where O.orderState ='Waiting' ");
		return query.getResultList();
        }
    
    

}
