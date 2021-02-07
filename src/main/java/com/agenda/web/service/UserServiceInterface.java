package com.agenda.web.service;

import com.agenda.web.model.UserEntity;

public interface UserServiceInterface 
{
	UserEntity createUser(UserEntity user);
	
	void deleteUser(UserEntity user);
	
	public UserEntity findbyUsername(String username);
}
