package tn.esprit.b3.esprit1718b3erp.payementservices;

import java.text.ParseException;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b3.esprit1718b3erp.entities.Assets;
import tn.esprit.b3.esprit1718b3erp.entities.BankAccount;
import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Payement;
import tn.esprit.b3.esprit1718b3erp.entities.User;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Remote
public interface PayementServiceRemote extends IGenericDAO<Payement>  {
public List<Payement> listcash();
}
