package tn.esprit.b3.esprit1718b3erp.assetservices;

import java.text.ParseException;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b3.esprit1718b3erp.entities.Assets;
import tn.esprit.b3.esprit1718b3erp.entities.BankAccount;
import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.User;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Remote
public interface AssetServiceRemote extends IGenericDAO<Assets>  {
public Boolean Input_ctrl(Assets asset)throws ParseException ;
public String getStringField(Object o);
public Employee fillemp(List<Employee> emp,int j) throws IndexOutOfBoundsException;
public List<BankAccount> findAllUsers();
}
