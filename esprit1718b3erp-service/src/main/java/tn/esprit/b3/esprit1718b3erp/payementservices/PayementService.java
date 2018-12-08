package tn.esprit.b3.esprit1718b3erp.payementservices;

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
public class PayementService extends GenericDAO<Payement> implements PayementServiceRemote, PayementServiceLocal {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public PayementService() {
		super(Payement.class);
	}

	@Override
	public List<Payement> listcash() {
		List<Payement> ba = new ArrayList<Payement>();
		List<Payement> ba2 = new ArrayList<Payement>();

		try{
    	String jpql="Select u from Payement u";
		Query query=entityManager.createQuery(jpql);
        ba=query.getResultList();
		for(int i=0;i<ba.size();i++)
		{
			if(ba.get(i).getB()==null)
             ba2.add(ba.get(i));
		}
	
		}
		catch(NullPointerException e)
		{
			
		}
		return ba2;
	}
}
