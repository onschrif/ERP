package tn.esprit.b3.esprit1718b3erp.scm_InventoryServices;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b3.esprit1718b3erp.entities.InventoryLocation;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Remote
public interface InventoryLocationServicesRemote extends IGenericDAO<InventoryLocation>{


	 public List<InventoryLocation> FindAllLocationEnable();
	 
	 public InventoryLocation FindLocationByName(String name);
	 
	 
}

