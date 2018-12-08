package tn.esprit.b3.esprit1718b3erp.orderservices;

import javax.ejb.Local;

import tn.esprit.b3.esprit1718b3erp.entities.Orders;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Local
public interface OrderServiceLocal extends IGenericDAO<Orders> {

}
