package tn.esprit.b3.esprit1718b3erp.scm_purchaseServices;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;

/**
 * Session Bean implementation class ProductServices
 */
@Stateless
public class ProductServices extends GenericDAO<Product> implements ProductServicesRemote, ProductServicesLocal {

	@PersistenceContext
	private EntityManager em;


    public ProductServices() {
    	super(Product.class);
    }
    
	public List<Product> FindAllProductParent() {
		Query query = em.createQuery("SELECT P from Product P where P.nature = 'Finished Product' OR  P.nature='Semi-finished Product' ");
		return query.getResultList();
	}

	public List<Product> FindAllProductComponent() {
		Query query = em.createQuery("SELECT P from Product P where P.nature = 'Raw Material' OR  P.nature='Semi-finished Product' OR  P.nature='Packaging' ");
		return query.getResultList();

	}

	public Product FindProductByRef(String ref) {
		Query query = em.createQuery("SELECT P from Product P where P.ref=:reference");
		query.setParameter("reference", ref);
		return (Product) query.getSingleResult();

	}

	public int numberFinishedProduct() {
		int x;
		Query query = em.createQuery("SELECT P from Product P where P.nature='Finished Product' ");
		return query.getResultList().size();
	}

	public int numberSemifinishedProduct() {
		int x;
		Query query = em.createQuery("SELECT P from Product P where P.nature='Semi-finished Product' ");
		return query.getResultList().size();
	}

	public int numberRawMaterial() {
		int x;
		Query query = em.createQuery("SELECT P from Product P where P.nature='Raw Material' ");
		return query.getResultList().size();
	}

	public int numberPackaging() {
		int x;
		Query query = em.createQuery("SELECT P from Product P where P.nature='Packaging' ");
		return query.getResultList().size();

	}
	
	
	public List<Product> FindAllStcok(){
        Query query = em.createQuery("SELECT P from Product P where P.quantity != 0 ");
		return query.getResultList();
        }
	
	public List<Product> FindAllNotStcok(){
        Query query = em.createQuery("SELECT P from Product P where P.quantity = 0 ");
		return query.getResultList();
        }
    
	
	public List<Product> FindAllAdjustment(){
		Query query = em.createQuery("SELECT P from Product P where P.MinQuantity != 0  OR P.MaxQuantity != 0  ");
		return query.getResultList();
	}
	
	public List<Product> FindAllAdjustmentAlert(){
		Query query = em.createQuery("SELECT P from Product P where (P.MinQuantity != 0  OR P.MaxQuantity != 0) AND  P.quantity < P.MinQuantity ");
		return query.getResultList();
	}
	
	public List<Product> FindAllNotAdjustment(){
		Query query = em.createQuery("SELECT P from Product P where P.MinQuantity = 0  OR P.MaxQuantity = 0  ");
		return query.getResultList();
	}

}
