package tn.esprit.b3.esprit1718b3erp.scm_InventoryServices;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.b3.esprit1718b3erp.entities.BillOfMaterial;
import tn.esprit.b3.esprit1718b3erp.entities.InventoryMouvement;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesLocal;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;

/**
 * Session Bean implementation class MovementServices
 */
@Stateless
@LocalBean
public class InventoryMovementServices  extends GenericDAO<InventoryMouvement> implements InventoryMovementServicesRemote, InventoryMovementServicesLocal {

	@PersistenceContext
	private EntityManager em;
	
	

	@EJB
	private ProductServicesLocal productServicesLocal;

    /**
     * Default constructor. 
     */
    public InventoryMovementServices() {
    	super(InventoryMouvement.class)  ;
    	}

    public	List<InventoryMouvement> FindNbrMvtPrd()	{
	 	List<InventoryMouvement> listM = new ArrayList<InventoryMouvement>();

		 TypedQuery<Object[]>	qry	= em.createQuery(" select P.idProduct , count(M) from InventoryMouvement M , Product P "
		 		+ "where P.idProduct=M.product.idProduct GROUP BY P.idProduct",	Object[].class);
		 if	(!qry.getResultList().isEmpty())	{
		 	 List<Object[]>	tdata	=	qry.getResultList();	
		 	 for	(Object[]	t	:	tdata)	{
		 		InventoryMouvement m = new InventoryMouvement();
		 		m.setNbrMvtPrd(Integer.parseInt(t[1].toString()));
		 		
		 		int idPrd = Integer.parseInt(t[0].toString());
		 		Product p = new Product();
		 		p = productServicesLocal.find(idPrd);
		 		m.setProduct(p);
		 		
		 		listM.add(m);

		 	}
		 }
		 return	listM;	
	}
    
    

    public	List<InventoryMouvement> FindNbrMvt()	{
	 	List<InventoryMouvement> listM = new ArrayList<InventoryMouvement>();

		 TypedQuery<Object[]>	qry	= em.createQuery(" select M.product.idProduct, count(M) from InventoryMouvement M GROUP BY M.product.idProduct",	Object[].class);
		 if	(!qry.getResultList().isEmpty())	{
		 	 List<Object[]>	tdata	=	qry.getResultList();	
		 	 for	(Object[]	t	:	tdata)	{
		 		InventoryMouvement m = new InventoryMouvement();
		 		m.setNbrMvtPrd(Integer.parseInt(t[1].toString()));
		 		
		 		int idPrd = Integer.parseInt(t[0].toString());
		 		Product p = new Product();
		 		p = productServicesLocal.find(idPrd);
		 		m.setProduct(p);
		 		
		 		listM.add(m);

		 	}
		 }
		 return	listM;	
	}
    
    public	List<InventoryMouvement> FindMvtProduct(int idP)	{
    	 Query query = em.createQuery("select M from InventoryMouvement M where M.product.idProduct=:idP").setParameter("idP",idP);
			return query.getResultList();
	        
    }
    
}
