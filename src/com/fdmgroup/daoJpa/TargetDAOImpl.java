package com.fdmgroup.daoJpa;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import com.fdmgroup.dao.TargetDAOI;
import com.fdmgroup.model.Activity;
import com.fdmgroup.model.BusinessCase;
import com.fdmgroup.model.T_User;
import com.fdmgroup.model.Target;

public class TargetDAOImpl implements TargetDAOI {
	private DbConnection conn = null;

	public TargetDAOImpl() {
		conn = DbConnection.getInstance();
	}

	@Override
	public boolean create(Target t) {
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		try {
            em.persist(t);
            return true;
		}catch (EntityExistsException e) {
            System.err.println("entity already exists");
		} catch (IllegalArgumentException e) {
            System.err.println("illegal argument");
		}catch (TransactionRequiredException e) {
            System.err.println("transaction required");
		}finally {
            em.getTransaction().commit();
            em.close();
		}
		
		return false;
	}

	@Override
	public boolean delete(Target t) {
		t.setVisible(false);
		return this.update(t);
	}

	@Override
	public Target readById(long id) {
		EntityManager em = conn.getEntityManager();
		TypedQuery<Target> q = em.createNamedQuery("target.findByID", Target.class);
		q.setParameter("activityId", id);
		List<Target> target = q.getResultList();
		em.close();
		
		return target.get(0);
	}

	@Override
	public List<Target> readAll() {
		EntityManager em = conn.getEntityManager();
		TypedQuery<Target> q = em.createNamedQuery("target.findAll", Target.class);
		List<Target> list = q.getResultList();
		em.close();

		return list;
	}

	@Override
	public boolean update(Target t) {

		EntityManager em = conn.getEntityManager();
		Target foundu = em.find(Target.class, t.getTargetId());
		em.getTransaction().begin();
		foundu.setTargetId(t.getTargetId());
		foundu.setTargetDate(t.getTargetDate());
		foundu.setCount(t.getCount());
		foundu.setType(t.getType());
		foundu.setUser(t.getUser());
		
		try {
			em.getTransaction().commit();
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

	@Override
	public List<Target> readByUser(T_User user) {
		
		EntityManager em = conn.getEntityManager();
		TypedQuery<Target> q = em.createNamedQuery("target.findByUser", Target.class);
		q.setParameter("user", user);
		List<Target> targets = q.getResultList();
		em.close();
		
		return targets;
	}

	@Override
	public List<Target> readByUserAndDay(T_User user, LocalDate activityDate) {
		
		EntityManager em = conn.getEntityManager();
		TypedQuery<Target> q = em.createNamedQuery("target.findByUserAndDay", Target.class);
		q.setParameter("user", user);
		q.setParameter("activityDate", activityDate);
		List<Target> targets = q.getResultList();
		em.close();
		
		return targets;

	}

	@Override
	public List<Target> readByUserAndDate(T_User user, LocalDate start, LocalDate end) {
		
		EntityManager em = conn.getEntityManager();
		TypedQuery<Target> q = em.createNamedQuery("target.findByUserAndDate", Target.class);
		q.setParameter("user", user);
		q.setParameter("start", start);
		q.setParameter("end", end);
		List<Target> targets = q.getResultList();
		em.close();
		
		return targets;
	}
	
	

}
