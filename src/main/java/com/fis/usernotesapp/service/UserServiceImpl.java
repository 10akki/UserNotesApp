package com.fis.usernotesapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fis.usernotesapp.dao.UserDao;
import com.fis.usernotesapp.model.Users;

/**
 * Service Implementation class
 * @author Akhil Garg
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public Optional<Users> getUserDetailsByMailId(String emailId) {
		return userDao.getUserByMailId(emailId);
	}

	@Override
	public List<Users> getAllUsersData() {
		return userDao.getUsersList();
	}
}