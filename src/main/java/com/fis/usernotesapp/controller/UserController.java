package com.fis.usernotesapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fis.uersnotesapp.model.Users;
import com.fis.usernotesapp.service.UserService;

/**
 * Restcontroller class to handle user related Rest calls
 * @author Akhil Garg
 *
 */
@RestController
public class UserController {
	
	@Autowired
	UserService userService; 
	
	/**
	 * Method to get all users list
	 * @return
	 */
	@GetMapping(path = "/getUsers")
	public List<Users> getUsersList(){
		return userService.getAllUsersData();
	}
	
	/**
	 * Method to get user object on the basis of emailId
	 * @param emailId
	 * @return
	 */
	@GetMapping(path = "/getUser/{emailId}")
	public Optional<Users> getUserByMailId(@PathVariable("emailId") String emailId){
		return userService.getUserDetailsByMailId(emailId);
	}
}