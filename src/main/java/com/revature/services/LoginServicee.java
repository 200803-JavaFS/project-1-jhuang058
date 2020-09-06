package com.revature.services;

import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.IUserDAO;
import com.revature.daos.UserDAO;
import com.revature.models.User;
import com.revature.utils.Hash;

public class LoginServicee {
	
	private static final Logger log = LogManager.getLogger(LoginServicee.class);
	IUserDAO uDao = new UserDAO();
	
	public User login(User u) throws NoSuchAlgorithmException {
		log.info("logging in");
		User user = uDao.findByUsername(u.getUsername());
		
		if (user != null) {
			if (Hash.generateHash(u.getPassword(), "MD5").equals(user.getPassword())) {
				return user;
			} else {
				return null;
			}
		} else {
			System.out.println("User not found.");
			return null;
		}
		
	}

}
