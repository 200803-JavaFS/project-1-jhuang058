package com.revature.services;

import java.security.NoSuchAlgorithmException;

import com.revature.daos.IUserDAO;
import com.revature.daos.UserDAO;
import com.revature.models.User;
import com.revature.utils.Hash;

public class LoginServicee {
	
	IUserDAO uDao = new UserDAO();
	
	public User login(User u) throws NoSuchAlgorithmException {
		User user = uDao.findByUsername(u.getUsername());
		
		if (user != null) {
			if (Hash.generateHash(u.getPassword(), "MD5").equals(user.getPassword())) {
				return user;
			}
		} else {
			System.out.println("User not found.");
			return null;
		}
		return user;
		
	}

}
