package tn.esprit.b3.esprit1718b3erp.projectservices;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Project;
import tn.esprit.b3.esprit1718b3erp.entities.Qualification;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Local
public interface QualificationServiceLocal extends IGenericDAO<Qualification> {
	public Qualification findByName(String name);
	public List<Qualification> findAllByEmployee(Employee employee);
	public List<Qualification> findAllByProject(Project project);
	public List<Qualification> membersQualifications(Project project);
	public List<Qualification> missingQualifications(Project project);
	public boolean isHelpful(Employee employee, Project project);
	public List<Employee> helpfulEmployees(Project project);
	public void updateStateActive(Project project);
}
