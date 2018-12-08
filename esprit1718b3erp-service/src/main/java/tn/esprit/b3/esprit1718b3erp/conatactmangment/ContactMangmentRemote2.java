package tn.esprit.b3.esprit1718b3erp.conatactmangment;

import javax.ejb.Remote;


import tn.esprit.b3.esprit1718b3erp.entities.Suppliers;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Remote
public interface ContactMangmentRemote2 extends IGenericDAO<Suppliers>  {

}
