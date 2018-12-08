package tn.esprit.b3.esprit1718b3erp.projectservices;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.b3.esprit1718b3erp.entities.SharedDocument;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;

/**
 * Session Bean implementation class SharedDocumentService
 */
@Stateless
public class SharedDocumentService extends GenericDAO<SharedDocument> implements SharedDocumentServiceRemote, SharedDocumentServiceLocal {
	@PersistenceContext
	EntityManager em;
	/**
	 * Default constructor.
	 */
	public SharedDocumentService() {
		super(SharedDocument.class);
	}
	public SharedDocument findByName(String name){
		Query query=em.createQuery("SELECT s FROM SharedDocument s WHERE s.title= :title");
		query.setParameter("title", name);
		return (SharedDocument)query.getSingleResult();
	}

}
