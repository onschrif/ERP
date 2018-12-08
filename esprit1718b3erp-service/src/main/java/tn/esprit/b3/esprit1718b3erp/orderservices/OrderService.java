package tn.esprit.b3.esprit1718b3erp.orderservices;

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
import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Opportunity;
import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.entities.Payement;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;

/**
 * Session Bean implementation class OrderService
 */
@Stateless
@LocalBean
public class OrderService extends GenericDAO<Orders> implements OrderServiceRemote, OrderServiceLocal {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public OrderService() {
		super(Orders.class);
    }
    public Client FindClientByCompany(String company){
      	 Query query = em.createQuery("SELECT C from Client C where C.company=:Company");
        	query.setParameter("Company", company);
   	    Object c=query.getSingleResult();
   	    Client c2=(Client)c;
   	    return c2;
   		
           	
      }
    
    @Override
	public void del(Orders entity) {
        em.createQuery("DELETE FROM Orders e WHERE e.id = :id")
        .setParameter("id", entity.getIdOrder())
        .executeUpdate();

	}
    
    
    public Product findIdProduct(String name)
    { Query query = em.createQuery("SELECT P from Product P where P.name=:Name");
    query.setParameter("Name", name);
	    Object p=query.getSingleResult();
	    Product p2=(Product)p;
	    return p2;
		
    }
    
    public List<Employee> findAllComm()
    {    		
    	
    	List<Employee> ba = new ArrayList<>();
    	Query query = em.createQuery("SELECT P from Employee P");
		ba=query.getResultList();
    	    return ba;

    }
    @Override
    public List<String> FindOrderByReference(){
     List<String> listRef= new ArrayList<>();
    	Query query = em.createQuery("SELECT DISTINCT C.referenceOrder from Orders AS c");
    	
    	listRef=query.getResultList();
  	    return listRef;
  		
          	
     }
    @Override
    public List<Orders> findAllOrders()
    {    		
    	
    	List<Orders> ba = new ArrayList<>();
    	Query query = em.createQuery("SELECT P from Orders P ");
    	
    	ba=query.getResultList();
    	    return ba;

    }
    
    
    public List<Orders> FindOrdrsByReference1(String reference){
    	List<Orders> ba = new ArrayList<>();
        Query query = em.createQuery("SELECT B from Orders B  where B.referenceOrder=:reference");
        query.setParameter("reference", reference);
        ba= query.getResultList();
        return ba;
        }
    public Orders  FindOrdrsByReference2(String reference){
        Query query = em.createQuery("SELECT B from Orders B  where B.referenceOrder=:reference");
        Object p=query.setParameter("reference", reference).getFirstResult();
	    Orders p2=(Orders)p;
	    return p2;
		
        }
	
	 @Override
	 public int calord(String firstName){
	    	List<Orders> ba = new ArrayList<Orders>();
		 Query query = em.createQuery("SELECT C from Orders C where C.orderState=:FirstName");
       	query.setParameter("FirstName", firstName);
          return query.getResultList().size();
}
	
}   
           

