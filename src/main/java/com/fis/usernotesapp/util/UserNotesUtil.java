package com.fis.usernotesapp.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.fis.usernotesapp.exception.UserNotesException;

public class UserNotesUtil {

	 public static String getLoggedInUser() {
	   	 String user="";
	   	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	       Object principal = auth.getPrincipal();
	       if(principal instanceof org.springframework.security.core.userdetails.User) {
	       	user=((User)principal).getUsername();
	       	System.out.print("user:::"+user);
	       }
	       System.out.print("user:::"+user);
	       return user;
	   }
	    
	    /**
	     * Method to validate loggedIn user with emailId
	     * @param emailId
	     */
	 public static void validateLoggedInUserWithMailId(String emailId) {
	    	String loggedInUser=getLoggedInUser();
			if(!loggedInUser.equals(emailId))
				throw new UserNotesException("Logged user: "+loggedInUser+" does not have access to notes linked to user:"+emailId);
	    }
}
