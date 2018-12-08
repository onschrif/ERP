package tn.esprit.b3.esprit1718erp.accessservices;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Project;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Local
public interface EmployeeServiceLocal extends IGenericDAO<Employee> {
	public List<Employee> findAllByProject(Project project);
	public Employee searchById(String id);

}
