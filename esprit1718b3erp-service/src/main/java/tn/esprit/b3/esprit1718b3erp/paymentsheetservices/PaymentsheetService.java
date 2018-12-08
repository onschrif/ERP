package tn.esprit.b3.esprit1718b3erp.paymentsheetservices;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b3.esprit1718b3erp.entities.PaymentSheet;

@Stateless
@LocalBean
public class PaymentsheetService implements PaymentsheetServiceRemote{

	@PersistenceContext
	private EntityManager entityManager;

	public PaymentsheetService(){
		super();
	}

	@Override
	public void addP(PaymentSheet paymentsheet) {
		entityManager.persist(paymentsheet);
		
	}

	@Override
	public List<PaymentSheet> findallPaymentsheet() {
		
		return entityManager.createQuery("From PaymentSheet").getResultList();
	}

	@Override
	public void updateP(PaymentSheet paymentsheet) {
		entityManager.merge(paymentsheet);
		
	}
	
}
