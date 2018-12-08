package tn.esprit.b3.esprit1718b3erp.loginservices;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.User;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;
import java.security.*;
/**
 * Session Bean implementation class LoginService
 */
@Stateless
public class LoginService extends GenericDAO<Employee> implements LoginServiceRemote, LoginServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public LoginService() {
		super(Employee.class);
	}

	@Override
	public Boolean login(String login, String password) {
		Employee emp = null;
		Boolean test=false;

		try {
			emp = entityManager.createQuery("SELECT u FROM Employee u WHERE u.email=:l AND u.password=:p", Employee.class)
					.setParameter("l", login).setParameter("p", password).getSingleResult();
			if(emp!=null)
				test=true;
		} catch (Exception e) {
		}
		return test;
	}
	
	@Override
	public Employee returnemp(String login) {
		Employee emp = null;
		Boolean test=false;

		try {
			emp = entityManager.createQuery("SELECT u FROM Employee u WHERE u.email=:l", Employee.class)
					.setParameter("l", login).getSingleResult();
			if(emp!=null)
				test=true;
		} catch (Exception e) {
		}
		return emp;
	}
	
	@Override
	public String encrypt(String s)
	{
		try {
	        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
	        byte[] array = md.digest(s.getBytes());
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < array.length; ++i) {
	          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
	       }
	        System.out.println(sb.toString());
			return sb.toString();
	    } catch (java.security.NoSuchAlgorithmException e) {
	    	return null;
	    }
	}

}
