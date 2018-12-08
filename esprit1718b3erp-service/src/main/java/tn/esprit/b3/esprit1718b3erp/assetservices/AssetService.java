package tn.esprit.b3.esprit1718b3erp.assetservices;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.jboss.shrinkwrap.api.asset.Asset;

import tn.esprit.b3.esprit1718b3erp.entities.Assets;
import tn.esprit.b3.esprit1718b3erp.entities.BankAccount;
import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Payement;
import tn.esprit.b3.esprit1718b3erp.entities.User;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;

/**
 * Session Bean implementation class EmployeeService
 */
@Stateless
@LocalBean
public class AssetService extends GenericDAO<Assets> implements AssetServiceRemote, AssetServiceLocal {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public AssetService() {
		super(Assets.class);
	}

	@Override
	public Boolean Input_ctrl(Assets asset) throws ParseException {
		Assets emp = null;
		Boolean test = false;
		String str_date = asset.getDateOfPurchase();
		DateFormat formatter;
		Date date;
		formatter = new SimpleDateFormat("yyyy-mm-dd");
		date = formatter.parse(str_date);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		Date today = new Date();
		if (today.after(date)) {
			test = true;
		}
		return test;
		
		
	}
	


	public Employee fillemp(List<Employee> emp,int j) throws IndexOutOfBoundsException
	{
		j=0;
		List<Employee> list = new ArrayList<Employee>();
		 for(int i=0; i<emp.size();i++)
		 {
			 list.add(emp.get(i));
		 }
		return list.get(j);
		
	}
	
	public String getStringField(Object o) {
	    return ((Employee) o).toString();
	}
	
	public List<BankAccount> findAllUsers(){
    	String jpql="Select u from BankAccount u";
		Query query=entityManager.createQuery(jpql);
		return query.getResultList();
		
}
	
	
}
