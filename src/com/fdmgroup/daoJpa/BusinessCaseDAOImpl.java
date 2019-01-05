package com.fdmgroup.daoJpa;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import com.fdmgroup.dao.BusinessCaseDAO;
import com.fdmgroup.model.BusinessCase;
import com.fdmgroup.model.T_User;

public class BusinessCaseDAOImpl implements BusinessCaseDAO {
	private DbConnection conn = null;

	public BusinessCaseDAOImpl() {
		conn = DbConnection.getInstance();
	}

	@Override
	public boolean create(BusinessCase t) {

		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(t);
			return true;
		} catch (EntityExistsException e) {
			System.err.println("entity already exists");
		} catch (IllegalArgumentException e) {
			System.err.println("illegal argument");
		} catch (TransactionRequiredException e) {
			System.err.println("transaction required");
		} finally {
			em.getTransaction().commit();
			em.close();
		}

		return false;

	}

	@Override
	public boolean delete(BusinessCase t) {
		t.setVisible(false);
		return this.update(t);
	}

	@Override
	public BusinessCase readById(long id) {

		EntityManager em = conn.getEntityManager();
		TypedQuery<BusinessCase> q = em.createNamedQuery("BusinessCase.findByID", BusinessCase.class);
		q.setParameter("businessCaseId", id);
		List<BusinessCase> list = q.getResultList();
		em.close();

		return list.get(0);

	}

	@Override
	public List<BusinessCase> readAll() {

		EntityManager em = conn.getEntityManager();
		TypedQuery<BusinessCase> q = em.createNamedQuery("BusinessCase.findAll", BusinessCase.class);
		List<BusinessCase> list = q.getResultList();
		em.close();

		return list;

	}

	public List<BusinessCase> readBySentUser(long sentUser) {

		EntityManager em = conn.getEntityManager();
		TypedQuery<BusinessCase> query = em.createNamedQuery("BusinessCase.findBySentUser", BusinessCase.class);
		query.setParameter("sentUser", sentUser);
		List<BusinessCase> list = query.getResultList();
		em.close();
		return list;

	}

	public List<BusinessCase> readByRecUser(T_User recUser) {

		EntityManager em = conn.getEntityManager();
		TypedQuery<BusinessCase> query = em.createNamedQuery("BusinessCase.findByRecUser", BusinessCase.class);
		query.setParameter("recUserId", recUser);
		List<BusinessCase> list = query.getResultList();
		em.close();
		return list;

	}

	@Override
	public boolean update(BusinessCase t) {

		EntityManager em = conn.getEntityManager();
		BusinessCase foundu = em.find(BusinessCase.class, t.getBusinessCaseId());
		em.getTransaction().begin();
		foundu.setCaseDate(t.getCaseDate());
		foundu.setLocation(t.getLocation());
		foundu.setMessage(t.getMessage());
		foundu.setProgress(t.getProgress());
		foundu.setRecUser(t.getRecUser());
		foundu.setSubject(t.getSubject());
		foundu.setSentUserId(t.getSentUserId());
		foundu.setComments(t.getComments());
		
		try {
			em.getTransaction().commit();
			System.out.println("after commit");
			return true;
		} catch (IllegalStateException e) {
			System.err.println("Illegal State");
		} catch (RollbackException e) {
			System.err.println("Rollback something went wrong");
		} finally {
			em.close();
		}

		return false;

	}

}
