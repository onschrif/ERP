package tn.esprit.b3.esprit1718b3erp.projectservices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Project;
import tn.esprit.b3.esprit1718b3erp.entities.Task;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;
import tn.esprit.b3.esprit1718erp.accessservices.EmployeeServiceLocal;

/**
 * Session Bean implementation class ProjectService
 */
@Stateless
public class ProjectService extends GenericDAO<Project> implements ProjectServiceRemote, ProjectServiceLocal {
	@PersistenceContext
	EntityManager em;
	@EJB
	private TaskServiceLocal taskServiceLocal;
	@EJB
	private EmployeeServiceLocal employeeServiceLocal;
	/**
	 * Default constructor.
	 */
	public ProjectService() {
		super(Project.class);
	}

	@Override
	public Project findByName(String name) {
		
		Query query=em.createQuery("SELECT p FROM Project p WHERE p.name= :name");
		query.setParameter("name", name);
		return (Project)query.getSingleResult();
	}
	public Project findByTask(Task task) {
		
		Query query=em.createQuery("SELECT p FROM Project p JOIN p.projectTasks c WHERE c.idTask = :id");
		query.setParameter("id", task.getIdTask());
		return (Project)query.getSingleResult();
	}
	 public void assignEmployeeToProject(Employee employee, Project project)
	    {
		 
		 List<Employee> oldEmployees=project.getMembers();
		 oldEmployees.add(employee);
		 project.setMembers(oldEmployees);
		 update(project);
	   }
	 public List<Project> findAllByEmployee(Employee employee){
		
		    Query query = em.createQuery("SELECT p FROM Project p JOIN p.members c WHERE c.idEmployee = :idCategory");
		    query.setParameter("idCategory", employee.getIdEmployee());
		    return query.getResultList();
	 }
	 public Long calculateNbrProjects(){
		    Query query = em.createQuery("SELECT COUNT(e) FROM Project e");
			return (Long)query.getSingleResult();
	 }
	 public Long calculateNbrCompletedProjects(){
		    Query query = em.createQuery("SELECT COUNT(e) FROM Project e WHERE e.state='done'");
			return (Long)query.getSingleResult();
	 }
	 public Long calculateNbrOnGoingProjects(){
		    Query query = em.createQuery("SELECT COUNT(e) FROM Project e WHERE e.state='incomplete'");
			return (Long)query.getSingleResult();
	 }
	 public boolean testCompleted(Project project){
		 if(taskServiceLocal.countNbrTasksByProject(project)>taskServiceLocal.countNbrDoneTasksByProject(project))
			 return false;
		 else return true;
	 }
	 public void updateState(Project project){
		 if(testCompleted(project)==true)
		 {
		    	Query query = em.createQuery("UPDATE Project p SET p.state = 'done' WHERE p.idProject= :i");
				query.setParameter("i", project.getIdProject());
		    	int rowCount = query.executeUpdate();
		 }
	 }
	 public Long calculProgress(Project project){
		 return (taskServiceLocal.countNbrDoneTasksByProject(project)/taskServiceLocal.countNbrTasksByProject(project));
	 }
	 
	 public float calculNbrDays(Project project){
		Query query=em.createQuery("SELECT p.startDate FROM Project AS p WHERE p.idProject= :id");
		query.setParameter("id", project.getIdProject()); 
		String d1=(String)query.getSingleResult();

		Query query2=em.createQuery("SELECT p.finishDate FROM Project AS p WHERE p.idProject= :id2");
		query2.setParameter("id2", project.getIdProject()); 
		String d2=(String)query2.getSingleResult();
		System.out.println("**********daaate deb"+d1+"date fin"+d2);
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

	    Date date1,date2;
	    float days = 0;
		try {
			date1 = myFormat.parse(d1);
		    date2 = myFormat.parse(d2);
		    long diff = date2.getTime() - date1.getTime();
		    days = (diff / (1000*60*60*24));
		    System.out.println("diffffff"+days);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return days;
	 }
	 public float calculBudget(Project project){
		 List<Employee> employees=employeeServiceLocal.findAllByProject(project);
		 float salary = (float) 0.0;
		 float budg=(float) 0.0;
		 for(Employee employee:employees){
			 System.out.println("salaiiiire"+employee.getSalary()+"\n");
			 salary+=(employee.getSalary())/30;
		 }
		 System.out.println("moyenne salaire"+salary);
		 budg=salary*calculNbrDays(project);
		 return budg;
	 }

}
