package tn.esprit.b3.esprit1718b3erp.expenseservices;

import javax.ejb.Remote;

import tn.esprit.b3.esprit1718b3erp.entities.BankAccount;
import tn.esprit.b3.esprit1718b3erp.entities.Expense;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Remote
public interface ExpenseServiceRemote extends IGenericDAO<Expense> {
    public float retby(String s);

}
