package tn.esprit.b3.esprit1718b3erp.employeeservices;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b3.esprit1718b3erp.entities.Assets;
import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Remote
public interface EmplServiceRemote extends IGenericDAO<Employee> {
	void addEmployee(Employee employee);
	List<Employee> findallEmployees();
	Employee searchById(String id);
	void deleteE(String id);
	void updateE(Employee employee);
	void statistic();
}
