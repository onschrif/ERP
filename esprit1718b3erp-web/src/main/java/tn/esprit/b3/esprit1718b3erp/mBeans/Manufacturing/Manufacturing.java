package tn.esprit.b3.esprit1718b3erp.mBeans.Manufacturing;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.entities.Production;
import tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesLocal;
import tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductionServicesLocal;
import tn.esprit.b3.esprit1718b3erp.services.UserServiceLocal;
import tn.esprit.b3.esprit1718erp.accessservices.EmployeeServiceLocal;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class Manufacturing {
	
	@EJB
	private UserServiceLocal userServiceLocal;
	@EJB
	private ProductServicesLocal productServicesLocal;
	@EJB
	private ProductionServicesLocal productionServicesLocal;
	@EJB
	private EmployeeServiceLocal employeeServiceLocal;
	
	private Date date4;
	Product product = new Product();
	Production production = new Production();
	List<Product> listP = new ArrayList<>();
	
	List<Product> listCombo = null;
	List<String> listRef = new ArrayList<>();
	private String productRef ;
	
	List<Employee> listEmpCombo = null;
	List<String> listID= new ArrayList<>();
	private String employeID ;
	private Date currentDate = new Date();
	

	
	 public void AfficherMessage(String message) {
	        FacesContext context = FacesContext.getCurrentInstance();
	        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System Error", message);
	        context.addMessage(null, m );
	    }
	  
	 public void AfficherMessageInfo(String message) {
	        FacesContext context = FacesContext.getCurrentInstance();
	        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Operation Succeeded ", message);
	        context.addMessage(null, m );
	    }
	
	public void doAddProduction()  {
		Product p = productServicesLocal.FindProductByRef(productRef);
		Employee e = productionServicesLocal.FindById(employeID);
		System.out.print("empppID =" +employeID);
		System.out.print("the emp =" +e.getFirstName());
		Production manufAdd = new Production();
		manufAdd.setNumberHeures(production.getNumberHeures());
		manufAdd.setQteToProduce(production.getQteToProduce());
		
		
		//start day 
		Date startDate = production.getStartDay();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.setTimeInMillis(calendar.getTimeInMillis()+28800000);
		Date staringtDate = calendar.getTime();		
		manufAdd.setStartDay(staringtDate);
		
		
		//end Date 
		long duration = (long)(production.getNumberHeures()*60);
		Date  endDate = productionServicesLocal.endingManufacturingDate(production.getStartDay(), duration, 1);
		manufAdd.setEndDay(endDate);
		
	
		manufAdd.setPr_product(p);
		manufAdd.setPr_employee(e);
		if (productionServicesLocal.DisponibliliteRespo(manufAdd)){
			productionServicesLocal.save(manufAdd);
			//SendMailEmployee.SendingEmail(e.getEmail(), manufAdd);
			AfficherMessageInfo("ADDED with Succes"); 
		}
		else { AfficherMessage("this employee  has already a production in this period") ;}
	//	System.out.print("DisponibliliteRespo =" + productionServicesLocal.DisponibliliteRespo(manufAdd));
}
	
	public List<Product> getListP() {	
		listP= productServicesLocal.findAll();
		return listP;
	}
	public void setListP(List<Product> listP) {
		this.listP = listP;
	}
	
	public List<String> getListRef() {
	  listCombo= productServicesLocal.findAll();
		for (Product prd : listCombo) {
			listRef.add(prd.getRef());
		}
		
		return listRef;
	}

	public void setListRef(List<String> listRef) {
		this.listRef = listRef;
	}

	public Date getDate4() {
        return date4;
    }
 
    public void setDate4(Date date4) {
        this.date4 = date4;
    }

	public Production getProduction() {
		return production;
	}

	public void setProduction(Production production) {
		this.production = production;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getProductRef() {
		return productRef;
	}

	public void setProductRef(String productRef) {
		this.productRef = productRef;
	}

	public List<Product> getListCombo() {
		return listCombo;
	}

	public void setListCombo(List<Product> listCombo) {
		this.listCombo = listCombo;
	}


	public List<Employee> getListEmpCombo() {
		return listEmpCombo;
	}

	public void setListEmpCombo(List<Employee> listEmpCombo) {
		this.listEmpCombo = listEmpCombo;
	}

	public List<String> getListID() {
		listEmpCombo= employeeServiceLocal.findAll();
		for (Employee emp : listEmpCombo) {
			listID.add(emp.getIdEmployee());
		}
		return listID;
	}

	public void setListID(List<String> listID) {
		this.listID = listID;
	}

	public String getEmployeID() {
		return employeID;
	}

	public void setEmployeID(String employeID) {
		this.employeID = employeID;
	}

	public Date getCurrentDate() {		
//		DATE DATEDEM ;
//		DateDem = currentDate.setDate(currentDate.getDate());
		return currentDate;
		
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
 
	
	
}
