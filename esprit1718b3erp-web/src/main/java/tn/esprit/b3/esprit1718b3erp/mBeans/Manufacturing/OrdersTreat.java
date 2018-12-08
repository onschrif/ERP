package tn.esprit.b3.esprit1718b3erp.mBeans.Manufacturing;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.entities.Production;
import tn.esprit.b3.esprit1718b3erp.orderservices.OrderServiceLocal;
import tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.Manufacturing1Local;
import tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesLocal;
import tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductionServicesLocal;
import tn.esprit.b3.esprit1718b3erp.services.UserServiceLocal;
import tn.esprit.b3.esprit1718erp.accessservices.EmployeeServiceLocal;


@ManagedBean
@ViewScoped
public class OrdersTreat {
	
	@EJB
	private UserServiceLocal userServiceLocal;
	@EJB
	private ProductServicesLocal productServicesLocal;
	@EJB
	private ProductionServicesLocal productionServicesLocal;
	@EJB
	private EmployeeServiceLocal employeeServiceLocal;
	@EJB 
	private OrderServiceLocal orderServiceLocal;
	
	@EJB
	private Manufacturing1Local manufacturing1Local;
	private List<Orders> orders;
    
    private Orders selectedOrders;
    
    Production productionAdd = new Production();
  
	private String employeID ;

    
    public void doviewProduct(String idP)  {
//    	System.out.print("iiiiiiid"+idP);
//         selectedProduit= productServicesLocal.FindProductByRef(idP);
//    	System.out.print("pppppp"+selectedProduit.getName());
    }
    
    public void doYes()  {
    	RequestContext requestContext1 = RequestContext.getCurrentInstance();		         
    	requestContext1.execute("PF('dlg').hide()");  
    	requestContext1.execute("PF('dlg2').show()");
    }
    public void doNo()  {
    	RequestContext requestContext = RequestContext.getCurrentInstance();		         
    	requestContext.execute("PF('dlg').hide()");  
    	requestContext.execute("PF('dlg2').hide()");
    	selectedOrders.setOrderState("Canceled");
		manufacturing1Local.update(selectedOrders);
    }

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
    public void doAddProduction() throws ParseException  {
    	Product p = productServicesLocal.FindProductByRef(selectedOrders.getP().getRef());
		Employee e = productionServicesLocal.FindById(employeID);
		
		Production manufAdd = new Production();
		manufAdd.setNumberHeures(productionAdd.getNumberHeures());
		manufAdd.setQteToProduce(selectedOrders.getQuantityToOrder());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		//endDate
	    Date endDate = dateFormat.parse(selectedOrders.getRecommendedDate());
	    manufAdd.setEndDay(endDate);
	    
		//start day 
		long duration = (long)(productionAdd.getNumberHeures()*60);
		Date  startDate = productionServicesLocal.startingManufacturingDate(endDate, duration, 1);

		startDate.setDate(startDate.getDate()+1);		
		manufAdd.setStartDay(startDate);
		
		manufAdd.setPr_product(p);
		manufAdd.setPr_employee(e);
		Date currentDate = new Date();
	//	System.out.print("currentDate : "+currentDate+" ///startDate = " +startDate);
		
		if (startDate.before(currentDate)){
			AfficherMessage("starting date is before than current Date") ;
			selectedOrders.setOrderState("Canceled");
			manufacturing1Local.update(selectedOrders);
			}
		else {
			if (productionServicesLocal.DisponibliliteRespo(manufAdd)){
		
					productionServicesLocal.save(manufAdd);
					//SendMailEmployee.SendingEmail(e.getEmail(), manufAdd);
					AfficherMessageInfo("ADDED with Succes"); 
					
					//orders
					selectedOrders.setOrderState("Confirmed");
					manufacturing1Local.update(selectedOrders);
			}
			else { 
				AfficherMessage("this employee  has already a production in this period") ;
				 RequestContext requestContext = RequestContext.getCurrentInstance();		         
			      //requestContext.update("form:display");
			      requestContext.execute("PF('dlg').show()");
			      
//				selectedOrders.setOrderState("Canceled");
//				manufacturing1Local.update(selectedOrders);
			}
		}
}
    
	public List<Orders> getOrders() {
		orders= manufacturing1Local.FindAllorderW();
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public Orders getSelectedOrders() {
		return selectedOrders;
	}

	public void setSelectedOrders(Orders selectedOrders) {
		this.selectedOrders = selectedOrders;
	}


	public Production getProductionAdd() {
		return productionAdd;
	}


	public void setProductionAdd(Production productionAdd) {
		this.productionAdd = productionAdd;
	}


	public String getEmployeID() {
		return employeID;
	}


	public void setEmployeID(String employeID) {
		this.employeID = employeID;
	}
    


	
	
	
}
