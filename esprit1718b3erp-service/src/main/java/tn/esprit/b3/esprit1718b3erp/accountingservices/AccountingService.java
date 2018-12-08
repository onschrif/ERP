package tn.esprit.b3.esprit1718b3erp.accountingservices;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.b3.esprit1718b3erp.entities.Assets;
import tn.esprit.b3.esprit1718b3erp.entities.BankAccount;
import tn.esprit.b3.esprit1718b3erp.entities.Payement;
import tn.esprit.b3.esprit1718b3erp.entities.User;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;

/**
 * Session Bean implementation class AccountingService
 */
@Stateless
public class AccountingService extends GenericDAO<BankAccount> implements AccountingServiceRemote, AccountingServiceLocal {
	@PersistenceContext
	private EntityManager entityManager;

    /**
     * Default constructor. 
     */
    public AccountingService() {
		super(BankAccount.class);
    }

    
	@Override
	public Float balancecount(String s) {
		List<BankAccount> ba = new ArrayList<BankAccount>();
		Float f=0f;
		try{
    	String jpql="Select u from BankAccount u";
		Query query=entityManager.createQuery(jpql);
		ba=query.getResultList();
        for(int i=0;i<ba.size();i++)
        {
        	if(ba.get(i).getType().equals(s))
        	f+=ba.get(i).getBalance();
        }
		}
		catch(NullPointerException e)
		{
			
		}
		return f;
	}
	
	@Override
	public List<Payement> listpayement() {
		List<Payement> ba = new ArrayList<Payement>();
		try{
    	String jpql="Select u from Payement u";
		Query query=entityManager.createQuery(jpql);
		ba=query.getResultList();
		}
		catch(NullPointerException e)
		{
			
		}
		return ba;
	}

	
	@Override
	public List<Payement> findpay(int s) {
		List<Payement> ba = new ArrayList<>();
		Float f=0f;
		try{
    	String jpql="Select u from Payement u where u.b="+s;
		Query query=entityManager.createQuery(jpql);
		ba=query.getResultList();
		}
		catch(NullPointerException e)
		{
		}
		return ba;

	}
	
	@Override
	public int getbankid(String login) {
		BankAccount user = new BankAccount();
		try {
			user = entityManager.createQuery("SELECT u FROM BankAccount u WHERE u.BIC=:l", BankAccount.class)
					.setParameter("l",login).getSingleResult();
		} catch (Exception e) {
		}
		return user.getId();
	}
	

	
	@SuppressWarnings("deprecation")
	@Override
	public Float getmonthlybalance(int j,int s) throws ParseException
	{
		Float f=0f;
		List<Payement>list=new ArrayList<>();
    	String jpql="Select u from Payement u where u.b="+s;
		Query query=entityManager.createQuery(jpql);
		list=query.getResultList();
		 for(int i=0;i<list.size();i++)
		 {
			String str_date = list.get(i).getPaymentDate();
			Integer f1=Integer.valueOf(str_date.substring(5,7));
			if(j+3>f1 && f1>=j)
			{
				f+=list.get(i).getAmount();
			}

		 }

		
		return f;
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Float getmonthlyaverage(int j) throws ParseException
	{
		Float f=0f;
		List<Payement>list=new ArrayList<>();
    	String jpql="Select u from Payement u";
		Query query=entityManager.createQuery(jpql);
		list=query.getResultList();
		 for(int i=0;i<list.size();i++)
		 {
			String str_date = list.get(i).getPaymentDate();
			Integer f1=Integer.valueOf(str_date.substring(5,7));
			if(j+3>f1 && f1>=j)
			{
				f+=list.get(i).getAmount();
			}

		 }

		
		return f;
		
	}
	}

		

