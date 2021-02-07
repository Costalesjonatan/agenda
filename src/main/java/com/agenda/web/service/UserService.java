package com.agenda.web.service;

import com.agenda.web.model.User;

public interface UserService 
{
	User createUser(User user);
	
	void deleteUser(long userId);
}
