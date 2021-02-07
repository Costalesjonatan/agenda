package com.agenda.web.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda.web.model.UserEntity;
import com.agenda.web.repository.UserDAOInterface;
import com.agenda.web.service.UserServiceInterface;

@Service
public class UserServiceImplementation implements UserServiceInterface{
	
	@Autowired
	private UserDAOInterface UserDAO;
	
	@Override
	public UserEntity createUser(UserEntity user) {
		return UserDAO.save(user);
	}
	
	@Override
	public void deleteUser(UserEntity user) {
		UserDAO.delete(user);
	}

	@Override
	public UserEntity findbyUsername(String username) {
		return UserDAO.findbyUsername(username);
	}

	

}
