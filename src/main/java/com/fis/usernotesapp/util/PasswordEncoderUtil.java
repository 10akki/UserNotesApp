package com.fis.usernotesapp.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Util class to create encoded password
 * @author Akhil Garg
 *
 */
public class PasswordEncoderUtil {
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encoded = encoder.encode("password");// bob's password
		System.out.println(encoded);
	}
}