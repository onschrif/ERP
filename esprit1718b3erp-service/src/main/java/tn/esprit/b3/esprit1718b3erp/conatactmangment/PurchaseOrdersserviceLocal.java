package tn.esprit.b3.esprit1718b3erp.conatactmangment;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b3.esprit1718b3erp.entities.PurchaseOrder;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Local
public interface PurchaseOrdersserviceLocal extends IGenericDAO<PurchaseOrder> {
	public List<PurchaseOrder>  Findorderbysupplier(int id);
	public List Nbrproduct(int id);
	public List BestSuppliers();
	public List CountOrderWIthDate2();
	 public List<PurchaseOrder> FindAllPOWaiting();
	 public List<PurchaseOrder>  FindAllByProduct(int idPrd) ;
}
