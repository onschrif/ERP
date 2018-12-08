package tn.esprit.b3.esprit1718b3erp.scm_InventoryServices;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b3.esprit1718b3erp.entities.InventoryMouvement;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Local
public interface InventoryMovementServicesLocal  extends IGenericDAO<InventoryMouvement> {

	
	public	List<InventoryMouvement> FindNbrMvtPrd();
	
	   public	List<InventoryMouvement> FindNbrMvt();
	   
	   public	List<InventoryMouvement> FindMvtProduct(int idP);
}
