package com.mahendra.beovolyticscomputing.services;



import com.mahendra.beovolyticscomputing.entities.User;

public interface UserService {
	
	public User createUser(User user);
	
	public User findUserByUsername(String username);

	public void updateLastLogin(String username);

	/* public User checkUserExists(User user); */
	
}
