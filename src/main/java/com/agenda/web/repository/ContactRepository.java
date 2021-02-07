package com.agenda.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agenda.web.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{

}
