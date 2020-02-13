package com.fis.usernotesapp.service;

import java.util.List;
import java.util.Optional;

import com.fis.usernotesapp.model.Users;

/**
 * Service class to implement business logic related to users entity
 * @author Akhil Garg
 *
 */
public interface UserService {

	 /**
	  * Method to get all  users list stored in the database
	 * @return List<Users>
	 */
	List<Users> getAllUsersData();
	
	/**
	 * Method to get User details on the basis of UserId
	 * @param emailId
	 * @return
	 */
	Optional<Users> getUserDetailsByMailId(String emailId);
}
