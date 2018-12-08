package tn.esprit.b3.esprit1718b3erp.accountingservices;

import java.text.ParseException;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b3.esprit1718b3erp.entities.Assets;
import tn.esprit.b3.esprit1718b3erp.entities.BankAccount;
import tn.esprit.b3.esprit1718b3erp.entities.Payement;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Remote
public interface AccountingServiceRemote extends IGenericDAO<BankAccount> {
	public Float balancecount(String s);
	public List<Payement> findpay(int s);
	public int getbankid(String login) ;
	public Float getmonthlybalance(int j,int s) throws ParseException;
	public List<Payement> listpayement();
	public Float getmonthlyaverage(int j) throws ParseException;
	}
