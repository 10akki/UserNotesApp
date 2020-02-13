package com.fis.usernotesapp.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.fis.usernotesapp.exception.UserNotesException;
import com.fis.usernotesapp.model.Users;

/**
 * Dao class to handle CRUD operations on Users entity
 * 
 * @author Akhil Garg
 *
 */
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	/**
	 * Method to get Session object
	 * 
	 * @return session object
	 */
	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		return session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> getUsersList() throws UserNotesException {
		Query query = getSession().createQuery("from Users");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Optional<Users> getUserByMailId(String mailId) throws UserNotesException {
		Query query = getSession().createQuery("from Users where emailId=:mailId");
		query.setParameter("mailId", mailId);
		List<Users> users = (List<Users>) query.getResultList();
		if (!users.isEmpty())
			return users.stream().findFirst();
		return Optional.empty();
	}
}
