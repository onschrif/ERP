package tn.esprit.b3.esprit1718b3erp.clientservices;

import javax.ejb.Remote;

import tn.esprit.b3.esprit1718b3erp.entities.Client;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Remote
public interface ClientServiceRemote extends IGenericDAO<Client> {

}
