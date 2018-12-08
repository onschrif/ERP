package tn.esprit.b3.esprit1718b3erp.conatactmangment;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Local
public interface OrdersserviceLocal  extends IGenericDAO<Orders>{
	public	List FavProduct(int id);
//	 public Orders FindProduct(int idclient,int idProduct);
	public List BestClient();
	public List CountOrderWIthDate();
}
