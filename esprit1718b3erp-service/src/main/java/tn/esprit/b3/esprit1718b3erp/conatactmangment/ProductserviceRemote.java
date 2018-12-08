package tn.esprit.b3.esprit1718b3erp.conatactmangment;

import javax.ejb.Remote;

import tn.esprit.b3.esprit1718b3erp.entities.InventoryMouvement;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Remote
public interface ProductserviceRemote extends  IGenericDAO<Product>{
	public Product findProduct(int id) ;
}
