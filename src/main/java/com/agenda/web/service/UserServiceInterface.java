package com.agenda.web.service;

import com.agenda.web.model.UserEntity;

public interface UserServiceInterface 
{
	public UserEntity findbyUsername(String username);
	public UserEntity registrar(UserEntity usuario);
}
