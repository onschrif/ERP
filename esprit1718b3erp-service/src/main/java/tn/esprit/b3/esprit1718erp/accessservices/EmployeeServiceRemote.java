package tn.esprit.b3.esprit1718erp.accessservices;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Project;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Remote
public interface EmployeeServiceRemote extends IGenericDAO<Employee> {
	public Employee searchById(String id);
	public List<Employee> findAllByProject(Project project);
}
