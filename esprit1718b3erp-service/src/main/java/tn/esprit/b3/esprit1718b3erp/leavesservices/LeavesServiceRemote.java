package tn.esprit.b3.esprit1718b3erp.leavesservices;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b3.esprit1718b3erp.entities.Leaves;

@Remote
public interface LeavesServiceRemote {

	void addLeaves(Leaves leaves);
	List<Leaves> findAllLeaves();
	void updateL(Leaves leaves);
	Leaves searchByIdL(int id);
}
