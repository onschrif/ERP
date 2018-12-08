package tn.esprit.b3.esprit1718b3erp.scm_purchaseServices;

import java.util.List;
import javax.ejb.Remote;
import tn.esprit.b3.esprit1718b3erp.entities.BillOfMaterial;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Remote
public interface BillOfMaterialServicesRemote extends IGenericDAO<BillOfMaterial>{
	
	public List<BillOfMaterial> FindAllBillGroupeByPere();

	
	public	List<BillOfMaterial> FindAllBillWithNbrComponent()	;
	
	public void DeleteBill(Product p ) ;
	 public List<BillOfMaterial> FindComponentBill(Product p );
	
}
