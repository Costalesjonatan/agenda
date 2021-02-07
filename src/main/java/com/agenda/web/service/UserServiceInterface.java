package com.agenda.web.service;

import com.agenda.web.model.User;

public interface UserServiceInterface 
{
	User createUser(User user);
	
	void deleteUser(User user);
	
	public User findbyEmail(String email);
}
