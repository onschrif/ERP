package tn.esprit.b3.esprit1718b3erp.conatactmangment;

import javax.ejb.Local;

import tn.esprit.b3.esprit1718b3erp.entities.Promotion;
import tn.esprit.b3.esprit1718b3erp.utilities.IGenericDAO;

@Local
public interface PromotionserviceLocal extends IGenericDAO<Promotion> {

}
