package tn.esprit.b3.esprit1718b3erp.scm_purchaseServices;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Local
public interface ProductServicesLocal extends IGenericDAO<Product> {

	public List<Product> FindAllProductParent();

	public List<Product> FindAllProductComponent();

	public Product FindProductByRef(String ref);

	public int numberFinishedProduct();

	public int numberSemifinishedProduct();

	public int numberRawMaterial();

	public int numberPackaging();

	public List<Product> FindAllStcok();
	
	public List<Product> FindAllNotStcok();
	
	public List<Product> FindAllAdjustment();
	
	public List<Product> FindAllNotAdjustment();
	
	
	public List<Product> FindAllAdjustmentAlert();
}
