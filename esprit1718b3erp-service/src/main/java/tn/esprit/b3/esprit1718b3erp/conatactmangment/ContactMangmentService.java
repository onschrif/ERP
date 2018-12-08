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
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.entities.User;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;

@Stateless
public class ContactMangmentService  extends GenericDAO<Client> implements ContactMangmentLocal,ContactMangmentRemote {
	@PersistenceContext
	private EntityManager em;
	   /**
     * Default constructor. 
     */
    public ContactMangmentService() {
    	super(Client.class);
	}
	@Override
	public List<Client> FindAllClient() {
		 Query query = em.createQuery("SELECT P from Client P");
			return query.getResultList();
	}
	@Override
	public Product FindProduct(int id) {
		Product product = null;
		try {
			product= em.createQuery("SELECT p FROM Product P WHERE P.idProduct=:l",Product.class)
					.setParameter("l", id).getSingleResult();
		} catch (Exception e) {
		}
		return product;	
	}

}
