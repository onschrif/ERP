package tn.esprit.b3.esprit1718b3erp.scm_purchaseServices;

import java.util.Date;

import javax.ejb.Remote;

import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Production;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Remote
public interface ProductionServicesRemote extends IGenericDAO<Production>{

	public Date endingManufacturingDate(Date startingDate, long duration, int hourlyPost);

	public Employee FindById(String id);

	public Boolean DisponibliliteRespo(Production production);

	public Date startingManufacturingDate(Date endingDate, long duration, int hourlyPost);
	
	// public TreeItem<Product> NeedCalculation(Product p );

}
