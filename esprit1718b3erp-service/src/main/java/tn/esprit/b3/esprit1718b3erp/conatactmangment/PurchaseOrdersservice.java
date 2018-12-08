package tn.esprit.b3.esprit1718b3erp.conatactmangment;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.b3.esprit1718b3erp.entities.InventoryMouvement;
import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.entities.Promotion;
import tn.esprit.b3.esprit1718b3erp.entities.PurchaseOrder;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;

/**
 * Session Bean implementation class PurchaseOrdersservice
 */
@Stateless
@LocalBean
public class PurchaseOrdersservice extends GenericDAO<PurchaseOrder> implements PurchaseOrdersserviceRemote, PurchaseOrdersserviceLocal {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public PurchaseOrdersservice() {
        // TODO Auto-generated constructor stub
    	super(PurchaseOrder.class);
    }
    @Override
   	public List<PurchaseOrder>  Findorderbysupplier(int id) {
       	Query qry = em.createNativeQuery("SELECT * FROM PurchaseOrder WHERE idSuppliers=:l",PurchaseOrder.class).setParameter("l", id);		
   		 return	qry.getResultList();	
   	}
    @Override
	public List Nbrproduct(int id) {
		Query qry = em.createNativeQuery("SELECT count(p.name),p.name FROM PurchaseOrder o ,Product p WHERE (o.idProduct=p.idProduct AND o.idSuppliers=:l) GROUP BY o.idProduct").setParameter("l", id);		
			 return	qry.getResultList();	
	}   
    @Override
	public List BestSuppliers() {
		Query qry = em.createNativeQuery("SELECT COUNT(s.name),s.name from PurchaseOrder o, Suppliers s where o.idSuppliers=s.idSuppliers GROUP BY o.idSuppliers");		
			 return	qry.getResultList();	
	} 
    @Override
   	public List CountOrderWIthDate2() {
   		Query qry = em.createNativeQuery("SELECT COUNT(IdPurchaseOrder),date FROM PurchaseOrder GROUP BY date");		
   			 return	qry.getResultList();	
   	}   
   

    public List<PurchaseOrder> FindAllPOWaiting(){
        Query query = em.createQuery("SELECT O from PurchaseOrder O where O.status ='Waiting' ");
		return query.getResultList();
        }
    
    
   	public List<PurchaseOrder>  FindAllByProduct(int idPrd) {
       	Query qry = em.createQuery("SELECT O from PurchaseOrder O where O.pro.idProduct=:idP",PurchaseOrder.class).setParameter("idP", idPrd);		
   		 return	qry.getResultList();	
   	}
}
