package tn.esprit.b3.esprit1718b3erp.projectservices;

import javax.ejb.Remote;

import tn.esprit.b3.esprit1718b3erp.entities.SharedDocument;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Remote
public interface SharedDocumentServiceRemote extends IGenericDAO<SharedDocument> {
	public SharedDocument findByName(String name);
}
