package com.agenda.web.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.agenda.web.model.UserEntity;
import com.agenda.web.repository.UserDAOInterface;
import com.agenda.web.service.UserServiceInterface;

@Service
public class UserServiceImplementation implements UserServiceInterface{
	
	@Autowired
	private BCryptPasswordEncoder passwordEnconder;
	
	@Autowired
	private UserDAOInterface userDAO;

	@Override
	public UserEntity findbyUsername(String username) {
		return userDAO.findByusername(username);
	}

	@Override
	public UserEntity registrar(UserEntity usuario) {
		usuario.setPassword(passwordEnconder.encode(usuario.getPassword()));
		return userDAO.save(usuario);
	}

	

}
