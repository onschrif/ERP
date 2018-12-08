package tn.esprit.b3.esprit1718b3erp.conatactmangment;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b3.esprit1718b3erp.entities.InventoryMouvement;
import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.entities.User;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;

/**
 * Session Bean implementation class Productservice
 */
@Stateless
@LocalBean
public class Productservice extends GenericDAO<Product> implements ProductserviceRemote, ProductserviceLocal {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public Productservice() {
       	super(Product.class);
    }
    @Override
	public Product findProduct(int id) {
		Product product = null;
		try {
			product = em.createQuery("SELECT u FROM Product u WHERE u.idProduct=:l", Product.class)
					.setParameter("l", id).getSingleResult();
		} catch (Exception e) {
		}
		return product;
	}
	

}
