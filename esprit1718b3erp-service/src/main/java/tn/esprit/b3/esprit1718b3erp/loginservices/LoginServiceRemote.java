package tn.esprit.b3.esprit1718b3erp.loginservices;

import javax.ejb.Remote;

import tn.esprit.b3.esprit1718b3erp.entities.Employee;

@Remote
public interface LoginServiceRemote {

	Boolean login(String login, String password);
    String encrypt(String s);
    Employee returnemp(String login);
}
