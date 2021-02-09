package com.agenda.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agenda.web.model.Contact;

@Repository
public interface ContactDAOInterface extends JpaRepository<Contact, Long>{

}
