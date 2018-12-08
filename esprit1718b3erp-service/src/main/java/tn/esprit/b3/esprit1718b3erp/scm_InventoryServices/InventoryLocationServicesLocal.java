package tn.esprit.b3.esprit1718b3erp.scm_InventoryServices;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b3.esprit1718b3erp.entities.InventoryLocation;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Local
public interface InventoryLocationServicesLocal  extends IGenericDAO<InventoryLocation>{

	

	 public List<InventoryLocation> FindAllLocationEnable();
	 
	  public InventoryLocation FindLocationByName(String name);
	 
}
