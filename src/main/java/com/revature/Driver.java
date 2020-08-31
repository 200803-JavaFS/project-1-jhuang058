package com.revature;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.revature.daos.IReimbDAO;
import com.revature.daos.IUserDAO;
import com.revature.daos.ReimbDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.utils.Hash;

public class Driver {
	
	public static IUserDAO uDao = new UserDAO();
	public static IReimbDAO rDao = new ReimbDAO();

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		insertValues();
		
		List<User> users = uDao.findAll();
		
		for (User u : users) {
			System.out.println(u);
		}

	}
	
	public static void insertValues() throws NoSuchAlgorithmException {
		UserRole role1 = new UserRole("manager");
		UserRole role2 = new UserRole("employee");
		
		String pw1 = Hash.generateHash("ilikesushi", "MD5");
		String pw2 = Hash.generateHash("ilikehoney", "MD5");
		User u1 = new User("hk", pw1, "Hello", "Kitty", "hellokitty@hotmail.com", role1);
		User u2 = new User("wp", pw2, "Winnie", "Pooh", "winniethepooh@hotmail.com", role2);
		
		uDao.insertUser(u1);
		uDao.insertUser(u2);
		
	}

}
