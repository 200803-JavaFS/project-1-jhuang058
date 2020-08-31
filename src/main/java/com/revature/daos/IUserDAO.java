package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface IUserDAO {
	
	public User findByUsername(String username);
	
	public User findById(int id);

	public List<User> findAll();

	public boolean insertUser(User u);
	
}
