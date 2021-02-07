package com.agenda.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agenda.web.model.UserEntity;

@Repository
public interface UserDAOInterface extends JpaRepository<UserEntity, Long>  
{
	
	public UserEntity findbyUsername(String username);
}
