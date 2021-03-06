package tn.esprit.b3.esprit1718b3erp.projectservices;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Project;
import tn.esprit.b3.esprit1718b3erp.entities.Task;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Remote
public interface ProjectServiceRemote extends IGenericDAO<Project> {
	public Project findByName(String name);
	public void assignEmployeeToProject(Employee employee, Project project);
	public List<Project> findAllByEmployee(Employee employee);
	public Project findByTask(Task task);
	public Long calculateNbrProjects();
	public Long calculateNbrCompletedProjects();
	public Long calculateNbrOnGoingProjects();
	public boolean testCompleted(Project project);
	public void updateState(Project project);
	public Long calculProgress(Project project);
	public float calculNbrDays(Project project);
	public float calculBudget(Project project);
}
