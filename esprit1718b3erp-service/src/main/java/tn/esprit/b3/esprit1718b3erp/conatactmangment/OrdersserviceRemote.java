package tn.esprit.b3.esprit1718b3erp.conatactmangment;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b3.esprit1718b3erp.entities.Client;
import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Remote
public interface OrdersserviceRemote extends IGenericDAO<Orders>{
	public	List FavProduct(int id);
	public List BestClient();
	public List CountOrderWIthDate();
}
