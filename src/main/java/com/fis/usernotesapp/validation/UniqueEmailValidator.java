package com.fis.usernotesapp.validation;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.fis.uersnotesapp.model.Users;
import com.fis.usernotesapp.dao.UserDaoImpl;
import com.fis.usernotesapp.exception.UserNotesException;


/**
 * class to validate input email Id 
 * @author Akhil Garg
 *
 */
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	@Autowired
	private UserDaoImpl userDao;

	/**
	 *Method to check entered email Id in the database
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) throws UserNotesException {
		if (value != null) {
			Optional<Users> post = userDao.getUserByMailId(value);
			if (post.isPresent()) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}
}