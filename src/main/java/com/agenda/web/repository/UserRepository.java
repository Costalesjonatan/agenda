package com.agenda.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agenda.web.model.User;

public interface UserRepository extends JpaRepository<User, Long>  {

}
