package tn.esprit.b3.esprit1718b3erp.leavesservices;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b3.esprit1718b3erp.entities.Leaves;

@Stateless
@LocalBean
public class LeavesService implements LeavesServiceRemote{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public LeavesService() {
		super();
	}
	
	@Override
	public void addLeaves(Leaves leaves) {
		entityManager.persist(leaves);
	}

	@Override
	public List<Leaves> findAllLeaves() {
		
		return entityManager.createQuery("From Leaves").getResultList();
	}

	@Override
	public void updateL(Leaves leaves) {
		
		entityManager.merge(leaves);
		
	}

	@Override
	public Leaves searchByIdL(int id) {
		
		return entityManager.find(Leaves.class, id);
	}	
}
