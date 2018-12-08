package tn.esprit.b3.esprit1718b3erp.mBeans.Manufacturing;


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

import tn.esprit.b3.esprit1718b3erp.entities.Production;
import tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductServicesLocal;
import tn.esprit.b3.esprit1718b3erp.scm_purchaseServices.ProductionServicesLocal;
import tn.esprit.b3.esprit1718b3erp.services.UserServiceLocal;
import tn.esprit.b3.esprit1718erp.accessservices.EmployeeServiceLocal;


@ManagedBean
@ViewScoped
public class ManufacturingList {
	
	@EJB
	private UserServiceLocal userServiceLocal;
	@EJB
	private ProductServicesLocal productServicesLocal;
	@EJB
	private ProductionServicesLocal productionServicesLocal;
	@EJB
	private EmployeeServiceLocal employeeServiceLocal;
	
	
	private List<Production> productions;
    
    private Production selectedProduction;
    
    private Production editProduction;
    
  //  private Product selectedProduit;
    
    private String navigatoTO;
 
    
    public String doredirectToAddAction(){
    	navigatoTO = "/pages/manufacturing/manufacturing?faces-redirect=true";
    	return navigatoTO;
       	
    }
    
	 public void AfficherMessageInfo(String message) {
	        FacesContext context = FacesContext.getCurrentInstance();
	        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Operation Succeeded ", message);
	        context.addMessage(null, m );
	    }
	
  
    public void AfficherMessage(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System Error", message);
        context.addMessage(null, m );
    }
  
    public void doYes()  {
    	RequestContext requestContext1 = RequestContext.getCurrentInstance();		         
    	requestContext1.execute("PF('dlg').hide()");  
    	Production prdt = productionServicesLocal.find(selectedProduction.getIdProduction());
    	Date currentDate = new Date();
    	if (prdt.getStartDay().before(currentDate)){
			AfficherMessage("starting date is before than current Date") ;
			//System.out.print("nnnnnnn");
		}
		else {
			productionServicesLocal.delete(prdt);
			AfficherMessageInfo("DELETED with Succes"); 
		}

    }
    public void doNo()  {
    	RequestContext requestContext = RequestContext.getCurrentInstance();		         
    	requestContext.execute("PF('dlg').hide()");    	
    }
  
    public void doCancelUp()  {
    	RequestContext requestContext = RequestContext.getCurrentInstance();		         
    	requestContext.execute("PF('dlg3').hide()");    	
    } 
    
    
    public void doCancellation(int idP) throws ParseException  {
    	Production prdt = productionServicesLocal.find(idP);
    	Date currentDate = new Date();
    	System.out.print("ddddddate"+currentDate);	
    	System.out.print("SSSSSSSdate"+prdt.getStartDay());
    //	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	 //   Date currentD = dateFormat.parse(currentDate+"");
	  //  Date StartD= dateFormat.parse(prdt.getStartDay()+"");
    		if (prdt.getStartDay().before(currentDate)){
    			AfficherMessage("starting date is before than current Date") ;
    			System.out.print("nnnnnnn");
    		}
    		else {
    			productionServicesLocal.delete(prdt);
    			AfficherMessageInfo("DELETED with Succes"); 
    		}
    	}


	public List<Production> getProductions() {
		productions= productionServicesLocal.findAll();
		return productions;
	}

	public void setProductions(List<Production> productions) {
		this.productions = productions;
	}

	public Production getSelectedProduction() {
		return selectedProduction;
	}

	public void setSelectedProduction(Production selectedProduction) {
		this.selectedProduction = selectedProduction;
	}

	public Production getEditProduction() {
		return editProduction;
	}

	public void setEditProduction(Production editProduction) {
		this.editProduction = editProduction;
	}
	
	
	
	
}
