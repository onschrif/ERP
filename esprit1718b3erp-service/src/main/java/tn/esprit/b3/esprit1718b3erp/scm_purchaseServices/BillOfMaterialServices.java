package tn.esprit.b3.esprit1718b3erp.scm_purchaseServices;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.b3.esprit1718b3erp.entities.BillOfMaterial;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.entities.User;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;

/**
 * Session Bean implementation class BillOfMaterialServices
 */
@Stateless
public class BillOfMaterialServices extends GenericDAO<BillOfMaterial>  implements BillOfMaterialServicesRemote, BillOfMaterialServicesLocal {

	@PersistenceContext
	private EntityManager em;
	
	@EJB
	private ProductServicesLocal productServicesLocal;

	/**
	 * Default constructor.
	 */
	 public BillOfMaterialServices() {
		super(BillOfMaterial.class);
	}
	 
	 public List<BillOfMaterial> FindAllBillGroupeByPere(){
	        Query query = em.createQuery("SELECT B from BillOfMaterial B group by idProductPere ");
			return query.getResultList();
	        
	        }


	 
	 public	List<BillOfMaterial> FindAllBillWithNbrComponent()	{
	 	List<BillOfMaterial> rslt = new ArrayList<BillOfMaterial>();

		 TypedQuery<Object[]>	qry	= em.createQuery(" select p.idProduct , count(B) from BillOfMaterial B , Product P "
		 		+ "where P.idProduct=idProductPere GROUP BY P.idProduct",	Object[].class);
		 if	(!qry.getResultList().isEmpty())	{
		 	 List<Object[]>	tdata	=	qry.getResultList();	
		 	 for	(Object[]	t	:	tdata)	{
		 		BillOfMaterial b = new BillOfMaterial();
		 		b.setNbrComponent(Integer.parseInt(t[1].toString()));
		 		
		 		int idPrdPere = Integer.parseInt(t[0].toString());
		 		Product productPere = new Product();
		 		productPere = productServicesLocal.find(idPrdPere);
		 		b.setProductPere(productPere);
		 		
		 		rslt.add(b);
//		 	 	 HashMap	resultMap	=	new	HashMap();	
//		 	 	 resultMap.put("title",	t[0]);	
//		 	 	 resultMap.put("count",	t[1]);	
//		 	 	 data.add(resultMap);
		 	}
		 }
		 return	rslt;	
	}
	 
	 public void DeleteBill(Product p ) {

		 Query query = em.createQuery("SELECT B from BillOfMaterial B where idProductPere=:prd", BillOfMaterial.class)
			.setParameter("prd", p);
	     List<BillOfMaterial> listB = query.getResultList() ;
		 
	     for (BillOfMaterial b: listB) {  
	    	    em.remove(b);
	    	}
		} 
	 
	 public List<BillOfMaterial> FindComponentBill(Product p ) {

		 Query query = em.createQuery("SELECT B from BillOfMaterial B where idProductPere=:prd", BillOfMaterial.class)
			.setParameter("prd", p);
	     return query.getResultList() ;
		 
		} 
	 
}
