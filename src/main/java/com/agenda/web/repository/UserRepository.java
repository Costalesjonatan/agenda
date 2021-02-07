package com.agenda.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agenda.web.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  
{
	public User findbyEmail(String email);
}
