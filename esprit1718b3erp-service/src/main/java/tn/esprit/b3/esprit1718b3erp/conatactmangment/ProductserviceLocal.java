package tn.esprit.b3.esprit1718b3erp.conatactmangment;

import javax.ejb.Local;

import tn.esprit.b3.esprit1718b3erp.entities.InventoryMouvement;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Local
public interface ProductserviceLocal  extends IGenericDAO<Product> {
	public Product findProduct(int id) ;
}
