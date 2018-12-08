package tn.esprit.b3.esprit1718b3erp.paymentsheetservices;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b3.esprit1718b3erp.entities.PaymentSheet;

@Remote
public interface PaymentsheetServiceRemote {

	void addP(PaymentSheet paymentsheet);
	List<PaymentSheet> findallPaymentsheet();
	void updateP(PaymentSheet paymentsheet);
}
