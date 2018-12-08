package tn.esprit.b3.esprit1718b3erp.projectservices;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Project;
import tn.esprit.b3.esprit1718b3erp.entities.Task;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;

/**
 * Session Bean implementation class TaskService
 */
@Stateless
public class TaskService extends GenericDAO<Task> implements TaskServiceRemote, TaskServiceLocal {
	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public TaskService() {
    	super(Task.class);
    }
    public List<Task> findByEmployee(Employee employee){
		Query query=em.createQuery("SELECT t FROM Task t WHERE t.employee= :e");
		query.setParameter("e", employee);
    	return query.getResultList();
    }
    public List<Task> findByName(String name,Employee employee){
    	Query query=em.createQuery("SELECT t FROM Task t WHERE t.name= :name AND t.employee= :e");
		query.setParameter("name", name);
		query.setParameter("e", employee);
		return query.getResultList();
    }
    public List<Task> findAvance(String name,Employee employee, Project project){
    	Query query=em.createQuery("SELECT t FROM Task t WHERE t.name= :name OR t.employee= :e OR t.project:=p");
		query.setParameter("name", name);
		query.setParameter("e", employee);
		query.setParameter("p", project);
		return query.getResultList();
    }
    
    
    public List<Task> findByProjectName(String projectName,Employee employee){
		Query query=em.createQuery("SELECT t FROM Task t JOIN t.project p ON p.name = :e join t.employee e ON e.idEmployee= :id");
		query.setParameter("e", projectName);
		query.setParameter("id", employee.getIdEmployee());

    	return query.getResultList();
    }
    public List<Task> findByProject(Project project){
		Query query=em.createQuery("SELECT t FROM Task t WHERE t.project= :p");
		query.setParameter("p", project);
    	return query.getResultList();
    }
    public void updateState(int id){
    	Query query = em.createQuery("UPDATE Task t SET t.state = 'done' WHERE t.idTask= :i");
		query.setParameter("i", id);
    	int rowCount = query.executeUpdate();
		//entityManager.createQuery("Update Reclamation set etat = 'Treated' where id="+id).executeUpdate();	

    }
    public Long countNbrTasksByProject(Project project){
	    Query query = em.createQuery("SELECT COUNT(t) FROM Task t WHERE t.project= :p");
		query.setParameter("p", project);
		return (Long)query.getSingleResult();
    }
    
    
    public Long countNbrDoneTasksByProject(Project project){
	    Query query = em.createQuery("SELECT COUNT(t) FROM Task t WHERE t.project= :p AND t.state = 'done'");
		query.setParameter("p", project);
		return (Long)query.getSingleResult();
    }
    
    
}
