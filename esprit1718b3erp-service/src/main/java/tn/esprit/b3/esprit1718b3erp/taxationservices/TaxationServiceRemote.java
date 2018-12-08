package tn.esprit.b3.esprit1718b3erp.taxationservices;

import javax.ejb.Remote;

import tn.esprit.b3.esprit1718b3erp.entities.Taxation;
import tn.esprit.b3.esprit1718b3erp.entities.User;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Remote
public interface TaxationServiceRemote extends IGenericDAO<Taxation> {

}
