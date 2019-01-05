package com.fdmgroup.daoJpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import com.fdmgroup.dao.UserDAOI;
import com.fdmgroup.model.Activity;
import com.fdmgroup.model.Privilege;
import com.fdmgroup.model.T_User;


public class UserDAOImpl implements UserDAOI{
	private DbConnection conn = null;
	
	public UserDAOImpl() {
		conn = DbConnection.getInstance();
	}

	@Override
	public boolean create(T_User t) {
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
	public boolean delete(T_User t) {
		t.setActive(false);
		return update(t);
	}

	@Override
	public T_User readById(long id) {

		EntityManager em = conn.getEntityManager();
		TypedQuery<T_User> q = em.createNamedQuery("T_User.findByID", T_User.class);
		q.setParameter("userId", id);
		List<T_User> list = q.getResultList();
		em.close();
		
		return list.get(0);
	}

	@Override
	public List<T_User> readAll() {

		EntityManager em = conn.getEntityManager();
		TypedQuery<T_User> q = em.createNamedQuery("T_User.findAll",T_User.class);
		List<T_User> list = q.getResultList();
		em.close();
		
		return list;
	}

	@Override
	public boolean update(T_User t) {

		EntityManager em = conn.getEntityManager();
		T_User foundUser = em.find(T_User.class, t.getUserId());
		em.getTransaction().begin();		
			foundUser.setFirstName(t.getFirstName());
			foundUser.setLastName(t.getLastName());
			foundUser.setPassword(t.getPassword());
			foundUser.setPosition(t.getPosition());
			foundUser.setActive(t.isActive());
			foundUser.setVisible(t.isVisible());
			foundUser.setPrivileges(t.getPrivileges());
			foundUser.setCases(t.getCases());
			
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
	public T_User findByUsername(String username) {
		EntityManager  em= conn.getEntityManager();
		TypedQuery<T_User> query = em.createNamedQuery("T_User.findByUsername",T_User.class);
		query.setParameter("username", username);
		List<T_User> users = query.getResultList();
		em.close();
		if (users != null && users.size() == 1){
			return users.get(0);
		}
		return null;
	}

}
