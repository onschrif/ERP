package tn.esprit.b3.esprit1718erp.accessservices;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Project;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;

/**
 * Session Bean implementation class EmployeeService
 */
@Stateless
public class EmployeeService extends GenericDAO<Employee> implements EmployeeServiceRemote, EmployeeServiceLocal {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public EmployeeService() {
		super(Employee.class);
	}
	public Employee searchById(String id) {
		
		return entityManager.find(Employee.class, id);
	}
	public List<Employee> findAllByProject(Project project){
	    Query query = entityManager.createQuery("SELECT e FROM Employee e JOIN e.project_employee c WHERE c.idProject = :id");
	    query.setParameter("id", project.getIdProject());
	    return query.getResultList();
	}

}
