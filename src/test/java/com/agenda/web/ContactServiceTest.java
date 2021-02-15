package com.agenda.web;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.agenda.web.exception.InvalidDataException;
import com.agenda.web.exception.NullDataException;
import com.agenda.web.implementation.ContactServiceImplementation;
import com.agenda.web.model.Contact;
import com.agenda.web.repository.ContactDAOInterface;

class ContactServiceTest {
	
	private ContactServiceImplementation contactService;
	private ContactDAOInterface contactDAO;

	@Test
	public void shouldCreateContactTest() {
		givenContactDAO();
		givenContactService();
		
		contactService.createContact(Contact.builder()
				.id(1)
				.name("jonatan")
				.number("1139586203")
				.idUser(1)
				.build());
		
		Optional<Contact> contactCreated = contactDAO.findById((long) 1);
		Contact contact = contactCreated.get();
		
		assertTrue(contact.getName().equals("jonatan") && 
				contact.getNumber().equals("1139586203") && 
				contact.getIdUser() == 1 && 
				contact.getId() == 1);
	}
	
	@Test
	public void shouldUpdateContact() {
		givenContactDAO();
		givenContactService();
		
		contactService.createContact(Contact.builder()
				.id(1)
				.name("jonatan")
				.number("1139586203")
				.idUser(1)
				.build());
		
		contactService.updateContact(Contact.builder()
				.id(1)
				.name("aaron")
				.number("1125609876")
				.idUser(1)
				.build());
		
		Optional<Contact> contactUpdated = contactDAO.findById((long) 1);
		Contact contact = contactUpdated.get();
		
		assertTrue(contact.getName().equals("aaron") && 
				contact.getNumber().equals("1125609876") && 
				contact.getIdUser() == 1 && 
				contact.getId() == 1);
	}
	
	@Test
	public void validateNullNameTest(){
		givenContactService();
		
		assertThrows(NullDataException.class, () -> {
			contactService.validateContact(Contact.builder()
					.id(1)
					.name(null)
					.number("1139586203")
					.idUser(1)
					.build());
	  });	
	}
	
	@Test
	public void validateEmptyNameTest(){
		givenContactService();
		
		assertThrows(InvalidDataException.class, () -> {
			contactService.validateContact(Contact.builder()
					.id(1)
					.name("")
					.number("1139586203")
					.idUser(1)
					.build());
	  });	
	}
	
	@Test
	public void validateNullNumberTest(){
		givenContactService();
		
		assertThrows(NullDataException.class, () -> {
			contactService.validateContact(Contact.builder()
					.id(1)
					.name("jonatan")
					.number(null)
					.idUser(1)
					.build());
	  });	
	}
	
	@Test
	public void shouldNotCreateContactBecuseEmptyNumber(){
		givenContactService();
		
		assertThrows(InvalidDataException.class, () -> {
			contactService.validateContact(Contact.builder()
					.id(1)
					.name("jonatan")
					.number("")
					.idUser(1)
					.build());
	  });	
	}
	
	@Test
	public void shouldNotCreateContactBecuseInvalidNumber(){
		givenContactService();
		
		assertThrows(InvalidDataException.class, () -> {
			contactService.validateContact(Contact.builder()
					.id(1)
					.name("jonatan")
					.number("113245678")
					.idUser(1)
					.build());
	  });	
	}
	
	private void givenContactService() {
		contactService = new ContactServiceImplementation(contactDAO);
	}
	
	private void givenContactDAO() {
		contactDAO = new MockContactDAO();
	}
	
}
