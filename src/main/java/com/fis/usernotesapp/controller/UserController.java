package com.fis.usernotesapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fis.usernotesapp.exception.UserNotesException;
import com.fis.usernotesapp.model.Users;
import com.fis.usernotesapp.service.UserService;
import com.fis.usernotesapp.util.UserNotesUtil;

/**
 * Restcontroller class to handle user related Rest calls
 * 
 * @author Akhil Garg
 *
 */
@RestController
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * Method to get all users list
	 * 
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(path = "/getUsers")
	public List<Users> getUsersList() {
		try {
			return userService.getAllUsersData();
		} catch (UserNotesException ex) {
			throw new UserNotesException(ex.getMessage());
		}
	}

	/**
	 * Method to get user object on the basis of emailId
	 * 
	 * @param emailId
	 * @return
	 */
	@GetMapping(path = "/getUser/{emailId}")
	public Optional<Users> getUserByMailId(@PathVariable("emailId") String emailId) {
		try {
			UserNotesUtil.validateLoggedInUserWithMailId(emailId);
			return userService.getUserDetailsByMailId(emailId);
		} catch (UserNotesException ex) {
			throw new UserNotesException(ex.getMessage());
		}
	}
}