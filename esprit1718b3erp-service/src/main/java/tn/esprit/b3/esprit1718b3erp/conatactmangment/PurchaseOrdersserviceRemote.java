package tn.esprit.b3.esprit1718b3erp.conatactmangment;

import java.util.List;

import javax.ejb.Remote;


import tn.esprit.b3.esprit1718b3erp.entities.PurchaseOrder;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Remote
public interface PurchaseOrdersserviceRemote extends IGenericDAO<PurchaseOrder> {
	public List<PurchaseOrder>  Findorderbysupplier(int id);
	public List Nbrproduct(int id);
	public List BestSuppliers();
	public List CountOrderWIthDate2();
	
	 public List<PurchaseOrder> FindAllPOWaiting();
	 public List<PurchaseOrder>  FindAllByProduct(int idPrd) ;
}

