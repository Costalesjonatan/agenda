package com.agenda.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.agenda.web.model.UserEntity;
import com.agenda.web.repository.UserDAOInterface;

public class UserDetailsServiceImplementation implements UserDetailsService{
	
	@Autowired
	private UserDAOInterface UserDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = UserDAO.findbyUsername(username);
		UserBuilder builder = null;
		
		if(user != null)
		{
			builder = User.withUsername(username);
			builder.disabled(false);
			builder.password(user.getPassword());
			builder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
		}
		else
		{
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		
		
		return null;
		
	}

}
