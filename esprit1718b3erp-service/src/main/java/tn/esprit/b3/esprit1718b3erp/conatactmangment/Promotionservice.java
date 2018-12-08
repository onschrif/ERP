package tn.esprit.b3.esprit1718b3erp.conatactmangment;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.entities.Promotion;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;

/**
 * Session Bean implementation class Promotionservice
 */
@Stateless
@LocalBean
public class Promotionservice extends GenericDAO<Promotion> implements PromotionserviceRemote, PromotionserviceLocal {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public Promotionservice() {
       	super(Promotion.class);
    }

}
