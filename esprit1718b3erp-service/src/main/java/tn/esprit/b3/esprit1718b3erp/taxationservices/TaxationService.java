package tn.esprit.b3.esprit1718b3erp.taxationservices;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import tn.esprit.b3.esprit1718b3erp.entities.Taxation;
import tn.esprit.b3.esprit1718b3erp.entities.User;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;

/**
 * Session Bean implementation class TaxationService
 */
@Stateless
@LocalBean
public class TaxationService extends GenericDAO<Taxation> implements TaxationServiceRemote, TaxationServiceLocal {

    /**
     * Default constructor. 
     */
    public TaxationService() {
		super(Taxation.class);
    }

}
