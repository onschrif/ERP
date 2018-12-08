package tn.esprit.b3.esprit1718b3erp.projectservices;

import javax.ejb.Local;

import tn.esprit.b3.esprit1718b3erp.entities.SharedDocument;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Local
public interface SharedDocumentServiceLocal extends IGenericDAO<SharedDocument> {
	public SharedDocument findByName(String name);
}
