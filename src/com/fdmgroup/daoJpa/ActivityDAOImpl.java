package com.fdmgroup.daoJpa;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import com.fdmgroup.dao.ActivityDAOI;
import com.fdmgroup.model.Activity;
import com.fdmgroup.model.T_User;

public class ActivityDAOImpl implements ActivityDAOI{
	private DbConnection conn = null;

	public ActivityDAOImpl() {
		conn = DbConnection.getInstance();
	}

	@Override
	public boolean create(Activity t) {

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
	public boolean delete(Activity t) {
		t.setVisible(t.isVisible());
		return update(t);
	}

	@Override
	public Activity readById(long id) {

		EntityManager em = conn.getEntityManager();
		TypedQuery<Activity> q = em.createNamedQuery("activity.findByID", Activity.class);
		q.setParameter("activityId", id);
		List<Activity> activity = q.getResultList();
		em.close();
		
		return activity.get(0);
	}

	@Override
	public List<Activity> readAll() {

		EntityManager em = conn.getEntityManager();
		TypedQuery<Activity> q = em.createNamedQuery("activity.findAll", Activity.class);
		List<Activity> activities = q.getResultList();
		em.close();
		
		return activities;
	}

	@Override
	public boolean update(Activity t) {

		EntityManager em = conn.getEntityManager();
		Activity foundActivity = em.find(Activity.class, t.getActivityId());
		em.getTransaction().begin();		
			foundActivity.setActivitiesDone(t.getActivitiesDone());
			foundActivity.setVisible(t.isVisible());
		try {
			em.getTransaction().commit();
			return true;
		} catch (IllegalStateException e) {
			System.err.println("Illegal State");
		}catch (RollbackException e) {
			System.err.println("Rollback something went wrong");
		}finally {
			em.close();
		}
	
		return false;
	}

	@Override
	public List<Activity> readByUser(T_User user) {

		EntityManager em = conn.getEntityManager();
		TypedQuery<Activity> q = em.createNamedQuery("activity.findByUser", Activity.class);
		q.setParameter("user", user);
		List<Activity> activities = q.getResultList();
		em.close();
		
		return activities;
	}
	
	@Override
	public List<Activity> readByUserAndDate(T_User user, LocalDate start, LocalDate end) {

		EntityManager em = conn.getEntityManager();
		TypedQuery<Activity> q = em.createNamedQuery("activity.findByUserAndDate", Activity.class);
		q.setParameter("user", user);
		q.setParameter("start", start);
		q.setParameter("end", end);
		List<Activity> activities = q.getResultList();
		em.close();
		
		return activities;
	}
	
	@Override
	public List<Activity> readByUserAndDay(T_User user, LocalDate activityDate) {

		EntityManager em = conn.getEntityManager();
		TypedQuery<Activity> q = em.createNamedQuery("activity.findByUserAndDay", Activity.class);
		q.setParameter("user", user);
		q.setParameter("activityDate", activityDate);
//		System.out.println("Dao Activitydate - " + activityDate);
//		System.out.println("Dao User - " + user);
		List<Activity> activities = q.getResultList();
		em.close();
		
		return activities;
	}

	@Override
	public List<Activity> readAllByDate(LocalDate start, LocalDate end) {
		EntityManager em = conn.getEntityManager();
		TypedQuery<Activity> q = em.createNamedQuery("activity.findAllByDate", Activity.class);
		q.setParameter("start", start);
		q.setParameter("end", end);
		List<Activity> activities = q.getResultList();
		em.close();
		
		return activities;
	}
	
}
