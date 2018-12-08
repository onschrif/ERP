package tn.esprit.b3.esprit1718b3erp.projectservices;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Project;
import tn.esprit.b3.esprit1718b3erp.entities.Task;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;


@Local
public interface TaskServiceLocal extends IGenericDAO<Task> {
	public List<Task> findByEmployee(Employee employee);
    public List<Task> findByName(String name,Employee employee);
    public List<Task> findByProjectName(String projectName,Employee employee);
    public List<Task> findByProject(Project project);
    public void updateState(int id);
    public Long countNbrTasksByProject(Project project);
    public Long countNbrDoneTasksByProject(Project project);
}
