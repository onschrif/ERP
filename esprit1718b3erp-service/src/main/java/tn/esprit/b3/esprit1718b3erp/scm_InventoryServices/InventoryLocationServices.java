package tn.esprit.b3.esprit1718b3erp.scm_InventoryServices;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.b3.esprit1718b3erp.entities.InventoryLocation;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;


@Stateless
public class InventoryLocationServices extends GenericDAO<InventoryLocation> implements InventoryLocationServicesRemote, InventoryLocationServicesLocal {

	@PersistenceContext
	private EntityManager em;


    public InventoryLocationServices() {
    	super(InventoryLocation.class);
    }

    
    public List<InventoryLocation> FindAllLocationEnable(){
        Query query = em.createQuery("SELECT L from InventoryLocation L where L.state ='enable' ");
		return query.getResultList();
        }
    
    
    public InventoryLocation FindLocationByName(String name) {
		Query query = em.createQuery("SELECT L from InventoryLocation L where L.locationName=:name");
		query.setParameter("name", name);
		return (InventoryLocation) query.getSingleResult();
	}
    
}
