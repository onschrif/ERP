package tn.esprit.b3.esprit1718b3erp.conatactmangment;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


import tn.esprit.b3.esprit1718b3erp.entities.Suppliers;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;

@Stateless
@LocalBean
public class ContactMangmentService2  extends GenericDAO<Suppliers> implements ContactMangmentLocal2,ContactMangmentRemote2 {

	   /**
     * Default constructor. 
     */
    public ContactMangmentService2() {
    	super(Suppliers.class);
	}
  

}
