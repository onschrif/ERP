package tn.esprit.b3.esprit1718b3erp.expenseservices;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.b3.esprit1718b3erp.entities.BankAccount;
import tn.esprit.b3.esprit1718b3erp.entities.Expense;
import tn.esprit.b3.esprit1718b3erp.entities.Payement;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;

/**
 * Session Bean implementation class ExpenseService
 */
@Stateless
public class ExpenseService extends GenericDAO<Expense> implements ExpenseServiceRemote, ExpenseServiceLocal {
	@PersistenceContext
	private EntityManager entityManager;

    /**
     * Default constructor. 
     */
    public ExpenseService() {
		super(Expense.class);
    }

    public float retby(String s)
    {
    	float f=0f;
    	float f2=0f;

    	List<Expense> ba = new ArrayList<Expense>();
		try{
    	String jpql="Select u from Expense u";
		Query query=entityManager.createQuery(jpql);
		ba=query.getResultList();
		
		for(int i=0;i<ba.size();i++)
		{
			f+=ba.get(i).getAmount();
			if(ba.get(i).getType().equals(s))
			{
				f2+=ba.get(i).getAmount();
			}
		}
		
		}
		catch(NullPointerException e)
		{
			
		}
	return (f2/f)*100;
    	
    }
    
}
