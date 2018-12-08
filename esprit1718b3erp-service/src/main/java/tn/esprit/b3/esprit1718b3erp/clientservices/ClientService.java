package tn.esprit.b3.esprit1718b3erp.clientservices;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b3.esprit1718b3erp.entities.Assets;
import tn.esprit.b3.esprit1718b3erp.entities.Client;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;

/**
 * Session Bean implementation class ClientService
 */
@Stateless
@LocalBean
public class ClientService extends GenericDAO<Client>  implements ClientServiceRemote, ClientServiceLocal {
	@PersistenceContext
	private EntityManager entityManager;

    /**
     * Default constructor. 
     */
    public ClientService() {
		super(Client.class);
    }
    
    
    

}
