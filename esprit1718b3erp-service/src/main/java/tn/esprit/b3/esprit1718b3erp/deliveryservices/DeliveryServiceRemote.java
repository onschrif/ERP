package tn.esprit.b3.esprit1718b3erp.deliveryservices;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b3.esprit1718b3erp.entities.Client;
import tn.esprit.b3.esprit1718b3erp.entities.Delivery;
import tn.esprit.b3.esprit1718b3erp.entities.Employee;
import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.entities.Product;
import tn.esprit.b3.esprit1718b3erp.utilities.GenericDAO;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Remote
public interface DeliveryServiceRemote extends IGenericDAO<Delivery> {
 public int caldel(String firstName);
}
