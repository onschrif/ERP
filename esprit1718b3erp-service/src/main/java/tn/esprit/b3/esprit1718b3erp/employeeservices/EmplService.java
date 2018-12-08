package tn.esprit.b3.esprit1718b3erp.employeeservices;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b3.esprit1718b3erp.entities.Assets;
import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;

@Stateless
@LocalBean
public class EmplService extends GenericDAO<Employee> implements EmplServiceRemote{

	@PersistenceContext
	private EntityManager entityManager;

	public EmplService(){
		super(Employee.class);
	}
	
	@Override
	public void addEmployee(Employee employee) {
		
		entityManager.persist(employee);
	}

	@Override
	public List<Employee> findallEmployees() {
		
		return entityManager.createQuery("From Employee").getResultList();
	}

	@Override
	public Employee searchById(String id) {
		
		return entityManager.find(Employee.class, id);
	}

	@Override
	public void deleteE(String id) {
		entityManager.remove(searchById(id));
		
	}

	@Override
	public void updateE(Employee employee) {
		entityManager.merge(employee);
		
	}

	@Override
	public void statistic() {
		int countf = 0;
    	int countm = 0;
    	List<Employee> employees = findallEmployees();
    	
    	for(Employee e : employees){
    		
    		if(e.getGender().equalsIgnoreCase("Female")){
    			countf++;
    		}else{
    			countm++;
    		}
    	}	
	}	
		
}
