package com.revature.daos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.revature.models.User;

import com.revature.utils.HibernateUtil;

public class UserDAO implements IUserDAO{
	
	@Override
	public User findById(int id) {
		Session ses = HibernateUtil.getSession();
		
		User u = ses.get(User.class, id);
		return u;
	}

	@Override
	public List<User> findAll() {
		Session ses = HibernateUtil.getSession();
		
		List<User> list = ses.createQuery("FROM User").list();
		
		return list;
	}

	@Override
	public boolean insertUser(User u) {
		Session ses = HibernateUtil.getSession();
		
		try {
			ses.save(u);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}


	@Override
	public User findByUsername(String username) {
		Session ses = HibernateUtil.getSession();
		
		Query query = ses.createQuery("FROM User U WHERE U.username = :username");
		query.setParameter("username", username);
		List<User> list = query.getResultList();
		
		User u = list.get(0);
		
		return u;
	}
	

}
