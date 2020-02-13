package com.fis.usernotesapp.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fis.uersnotesapp.model.Users;

/**
 * Dao class to handle CRUD operations on Users entity
 * @author Akhil Garg
 *
 */
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * Method to get Session object
	 * @return session object
	 */
	public Session getSession() {
		Session session = sessionFactory.getCurrentSession(); 
		 return session;
	}
	
	 @SuppressWarnings("unchecked")
	 @Override
	public List<Users> getUsersList(){
		 Query query=getSession().createQuery("from Users");
		 return query.getResultList();	
	 }
	 
	 @SuppressWarnings("unchecked")
	 @Override
	public Optional<Users> getUserByMailId(String mailId) {
		 Query query=getSession().createQuery("from Users where emailId=:mailId");
		 query.setParameter("mailId", mailId);
		 List<Users> users=query.getResultList();
		 if(!users.isEmpty())
			 return users.stream().findFirst();
		 return Optional.empty();
	 }
}