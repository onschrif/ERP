package tn.esprit.b3.esprit1718b3erp.conatactmangment;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.b3.esprit1718b3erp.entities.Client;
import tn.esprit.b3.esprit1718b3erp.entities.InventoryMouvement;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.entities.User;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;

/**
 * Session Bean implementation class Prodcutmouv
 */
@Stateless
@LocalBean
public class Prodcutmouv extends GenericDAO<InventoryMouvement> implements ProdcutmouvRemote, ProdcutmouvLocal {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public Prodcutmouv() {
    	super(InventoryMouvement.class);
    }
    @Override
	public List<InventoryMouvement>  FindProductMouvment(int id) {
    
    	Query qry = em.createNativeQuery("SELECT * from InventoryMouvement p WHERE p.product_idProduct=:l",InventoryMouvement.class).setParameter("l", id);		
		 return	qry.getResultList();	
	}

}
