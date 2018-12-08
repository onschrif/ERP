package tn.esprit.b3.esprit1718b3erp.conatactmangment;

import java.util.List;

import javax.ejb.Local;


import tn.esprit.b3.esprit1718b3erp.entities.Client;
import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Local
public interface ContactMangmentLocal  extends IGenericDAO<Client>{
	 public List<Client> FindAllClient();
	 public Product FindProduct(int id);
}
