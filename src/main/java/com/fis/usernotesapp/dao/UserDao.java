package com.fis.usernotesapp.dao;

import java.util.List;
import java.util.Optional;

import com.fis.usernotesapp.model.Users;

/**
 * Dao Class to perform CRUD operations on Users entity
 * @author Akhil Garg
 *
 */
public interface UserDao {

	/**
	 * Method to get all users list from the database
	 * @return List<Users>
	 */
	List<Users> getUsersList();

	/**
	 * Method to get user details on the basis of mail Id
	 * @param mailId
	 * @return User object
	 */
	Optional<Users> getUserByMailId(String mailId);

}
