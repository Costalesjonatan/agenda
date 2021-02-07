package com.agenda.web.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda.web.model.User;
import com.agenda.web.repository.UserDAOInterface;
import com.agenda.web.service.UserServiceInterface;

@Service
public class UserServiceImplementation implements UserServiceInterface{
	
	@Autowired
	private UserDAOInterface UserDAO;
	
	@Override
	public User createUser(User user) {
		return UserDAO.save(user);
	}
	
	@Override
	public void deleteUser(User user) {
		UserDAO.delete(user);
	}

	@Override
	public User findbyEmail(String email) {
		return UserDAO.findbyEmail(email);
	}

	

}
