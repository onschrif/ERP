package tn.esprit.b3.esprit1718b3erp.scm_purchaseServices;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.b3.esprit1718b3erp.entities.Employee;

import tn.esprit.b3.esprit1718b3erp.entities.Production;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;

/**
 * Session Bean implementation class ProductionServices
 */
@Stateless
@LocalBean
public class ProductionServices  extends GenericDAO<Production> implements ProductionServicesRemote, ProductionServicesLocal {

	
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	private ProductServicesLocal productServicesLocal;
	@EJB
	private BillOfMaterialServicesLocal billOfMaterialServicesLocal;
    /**
     * Default constructor. 
     */
    public ProductionServices() {
    	super(Production.class);
    }

    
    public Date endingManufacturingDate(Date startingDate,long duration,int hourlyPost) {
		if(duration!=0){
			float hourNbr = duration / 60;
			float dayNbr = hourNbr / (hourlyPost*8);
			int length = (int) Math.round(dayNbr + 0.5);
			Calendar c = Calendar.getInstance();
			c.setTime(startingDate);
			long startMillis = c.getTimeInMillis();
			long endMillis = 0;
			if(hourlyPost==3){
				endMillis = startMillis + duration*60*1000;
			} else {
				// if it's a work doesn't exceed 1 day
				if ((length - 1) == 1) {
					long testEndMillis = startMillis + duration * 60 * 1000;
					// check if the work is more than 17h
					// 86400000 =1 day and 61200000=17h and 54000000=15h
					//endTime = 15h or 23h
					//jumpTime = 16h or 8h
					long endTime = (7+8*hourlyPost)*60*60*1000;
					long rest = (testEndMillis % (86400000));
					if(rest< 25200000)
						rest=rest+24*60*60*1000;
					if (rest >= endTime) {
						long jumpTime=(24-8*hourlyPost)*60*60*1000;
						endMillis = testEndMillis + jumpTime;
					} else {
						endMillis = testEndMillis;
					}
				} else {
					long newDuration = duration - (length - 1) * 8*hourlyPost * 60;
					long newStartMillis = startMillis + (length - 1) * 24 * 60 * 60 * 1000;

					long testEndMillis = newStartMillis + newDuration * 60 * 1000;
					// check if the work is more than 17h
					// 86400000 =1 day and 61200000=17h and 54000000=15h
					//endTime = 15h or 23h
					//jumpTime = 16h or 8h
					long endTime = (7+8*hourlyPost)*60*60*1000;
					long rest = (testEndMillis % (86400000));
					if(rest< 25200000)
						rest=rest+24*60*60*1000;
					if (rest >= endTime) {
						long jumpTime=(24-8*hourlyPost)*60*60*1000;
						endMillis = testEndMillis + jumpTime;
					} else {
						endMillis = testEndMillis;
					}
				}
			}
			endMillis = endMillis-86400000;
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(endMillis);

			Date endingDate = calendar.getTime();
			return endingDate;
		} else {
			return startingDate;
		}
		
	}
    
    
    public Date startingManufacturingDate(Date endingDate, long duration, int hourlyPost) {
		if(duration!=0){
			float hourNbr = duration / 60;
			float dayNbr = hourNbr / (hourlyPost*8);
			int length = (int) Math.round(dayNbr + 0.5);
			Calendar c = Calendar.getInstance();
			c.setTime(endingDate);
			long endMillis = c.getTimeInMillis();
			long startMillis = 0;
			if(hourlyPost==3){
				startMillis = endMillis - duration*60*1000;
			} else {
				// if it's a work doesn't exceed 1 day
				if ((length - 1) == 0) {
					long testStartMillis;
					if(((endMillis%86400000) - duration*60*1000)<25200000 && hourlyPost==2){
						testStartMillis = endMillis - ((duration) * 60 * 1000)- 8*60*60*1000;
					} else {
						testStartMillis = endMillis - ((duration) * 60 * 1000);
					}
					
					// check if the work is more than 17h
					// 86400000 =1 day and 61200000=17h and 54000000=15h
					//endTime = 15h or 23h
					//jumpTime = 16h or 8h
					long endTime = (7+8*hourlyPost)*60*60*1000;
					long rest = (testStartMillis % (86400000));
//					if(rest< 25200000)
//						rest=rest+24*60*60*1000;
					if (rest >= endTime || rest<=7*60*60*1000) {
						long jumpTime=(24-8*hourlyPost)*60*60*1000;
						startMillis = testStartMillis - jumpTime;
					} else {
						startMillis = testStartMillis ;
					}
				} else {
					long newDuration = duration - (length - 1) * 8*hourlyPost * 60;
					long newEndtMillis = endMillis - (length - 1) * 24 * 60 * 60 * 1000;
					
					long testStartMillis;
					if(((newEndtMillis % 86400000) - newDuration*60*1000)<25200000 && hourlyPost==2){
						testStartMillis = newEndtMillis - newDuration * 60 * 1000 -8*60*60*1000;
					} else {
						testStartMillis = newEndtMillis - newDuration * 60 * 1000 ;
					}
					
					// check if the work is more than 17h
					// 86400000 =1 day and 61200000=17h and 54000000=15h
					//endTime = 15h or 23h
					//jumpTime = 16h or 8h
					long endTime = (7+8*hourlyPost)*60*60*1000;
					long rest = (testStartMillis % (86400000));
//					if(rest< 25200000)
//						rest=rest+24*60*60*1000;
					if (rest >= endTime || rest<=7*60*60*1000) {
						long jumpTime=(24-8*hourlyPost)*60*60*1000;
						startMillis = testStartMillis - jumpTime;
					} else {
						startMillis = testStartMillis;
					}
				}
			}
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(startMillis);

			Date startingDate = calendar.getTime();
			return startingDate;
		} else {
			return endingDate;
		}
	}


    public Employee FindById(String id) {
		
		return em.find(Employee.class, id);
	}
    

    public Boolean DisponibliliteRespo(Production production) {
    	Boolean verif = true;
    	List<Production> ListPr= new ArrayList<>();
		Query query = em.createQuery("SELECT P from Production P where P.pr_employee.idEmployee=:idEmp");
		query.setParameter("idEmp", production.getPr_employee().getIdEmployee());
		ListPr= query.getResultList();
		
		for (Production pr : ListPr) {
		    System.out.println(" liste emp "+ pr.getIdProduction());

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date1 = null;
			Date date2 = null;
			Date StartdateNew = null;
			Date EnddateNew = null;
			
			try {
				// date1 = df.format(pr.getStartDay()+"");
				date1 = dateFormat.parse(df.format(pr.getStartDay()));
			    date2 = dateFormat.parse(df.format(pr.getEndDay()));
			    StartdateNew = dateFormat.parse(df.format(production.getStartDay()));
			    EnddateNew = dateFormat.parse(df.format(production.getEndDay()));
			    
			    
			    if( (date2.before(StartdateNew)) || (date1.after(EnddateNew)) ) {}
			    else { 
			    System.out.println("Impos");
			    return false ; }
//			    System.out.println("date 1 : "+dateFormat.format(date1)+" compareTo date2 : "+dateFormat.format(date2)+" -> "+date1.compareTo(date2));
//			    System.out.println("date 2 : "+dateFormat.format(date2)+" compareTo date1 : "+dateFormat.format(date1)+" -> "+date2.compareTo(date1));
//			    System.out.println("date 1 : "+dateFormat.format(date1)+" compareTo date1 : "+dateFormat.format(date1)+" -> "+date1.compareTo(date1));
			} catch (Exception e) {
			    System.err.println("date1 et/ou date2 et/ou date3 et/ou date4 invalide. Format à utiliser : dd/MM/YYYY");
			    System.err.println(e.getMessage());
			}
		}
		return verif;
	}
    
    
//    public TreeItem<Product> NeedCalculation(Product p ){
//    	 TreeItem<Product> root=new TreeItem<Product>();
//    	 Product produitFini = productServicesLocal.FindProductByRef(p.getRef());
//    	 root.getChildren().size();
//		 TreeItem<Product> newItemarticlePere;
//		 TreeItem<Product> newItemarticleFils=null;
//    	 
//		// for(int i=0;i<produitFini.size();i++) {
//			 System.out.println("//\\");
//			 newItemarticlePere=new TreeItem<>(produitFini);
//			 System.out.println("Product Pere "+newItemarticlePere.getValue().getName());
//			 root.getChildren().add(newItemarticlePere)  ;
//		 		 
//		 ArrayDeque <TreeItem<Product>> queue=new ArrayDeque<>();
//		 queue.add(newItemarticlePere);
//		
//		 System.out.println("queue "+queue.getLast().getValue().getName());
//		 while(!queue.isEmpty()) {
//		
//				TreeItem<Product> TreeItemHead=queue.getFirst();
//				 queue.removeFirst();
//				 List<BillOfMaterial> listNomenclatureFils=billOfMaterialServicesLocal.FindComponentBill(TreeItemHead.getValue());
//
//				 for(int j=0;j<listNomenclatureFils.size();j++) {
//					 Product pr = productServicesLocal.find(listNomenclatureFils.get(j).getProductFils().getIdProduct());
//					 System.out.println("pr" +pr);
//					 newItemarticleFils=new TreeItem<>(pr);
//					 TreeItemHead.getChildren().add(newItemarticleFils);
//					 queue.addLast(newItemarticleFils);		
//		
//				 }
//			 }
//		 
//		// }
//		 
//    	 
//    	 
//    	 return root;
//    }
//    
}
