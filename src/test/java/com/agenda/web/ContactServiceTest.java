package com.agenda.web;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.agenda.web.exception.InvalidDataException;
import com.agenda.web.exception.NullDataException;
import com.agenda.web.exception.ResourceNotFoundException;
import com.agenda.web.model.Contact;
import com.agenda.web.repository.ContactDAOInterface;
import com.agenda.web.service.implementation.ContactServiceImplementation;

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
				.userid(1)
				.build());
		
		Optional<Contact> contactCreated = contactDAO.findById((long) 1);
		Contact contact = contactCreated.get();
		
		assertTrue(contact.getName().equals("jonatan") && 
				contact.getNumber().equals("1139586203") && 
				contact.getUserid() == 1 && 
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
				.userid(1)
				.build());
		
		contactService.updateContact(Contact.builder()
				.id(1)
				.name("aaron")
				.number("1125609876")
				.userid(1)
				.build());
		
		Optional<Contact> contactUpdated = contactDAO.findById((long) 1);
		Contact contact = contactUpdated.get();
		
		assertTrue(contact.getName().equals("aaron") && 
				contact.getNumber().equals("1125609876") && 
				contact.getUserid() == 1 && 
				contact.getId() == 1);
	}
	
	@Test
	public void shouldDeleteContact(){
		givenContactDAO();
		givenContactService();
		
		contactService.createContact(Contact.builder()
				.id(1)
				.name("jonatan")
				.number("1139586203")
				.userid(1)
				.build());
		
		contactService.deleteContact((long)1);
		
		assertThrows(ResourceNotFoundException.class, () -> {
			contactService.validateContactExistsInDbById(1);
		});
	}
	
	@Test
	public void shouldGetAllContactByUserId(){
		givenContactDAO();
		givenContactService();
		
		contactService.createContact(Contact.builder()
				.id(1)
				.name("jonatan")
				.number("1139586203")
				.userid(1)
				.build());
		
		contactService.createContact(Contact.builder()
				.id(2)
				.name("aaron")
				.number("1123456777")
				.userid(1)
				.build());
		
		contactService.createContact(Contact.builder()
				.id(3)
				.name("aaron")
				.number("1123456777")
				.userid(2)
				.build());
		
		assertTrue(contactService.getAllContactByUserId(1).size() == 2);
	}
	
	@Test
	public void validateContactExistsExceptionTest() {
		givenContactDAO();
		givenContactService();
		
		assertThrows(ResourceNotFoundException.class, () -> {
			contactService.validateContactExistInDb(Contact.builder()
					.id(2)
					.name("aaron")
					.number("1125609876")
					.userid(1)
					.build());
	  });
	}
	
	@Test
	public void validateContactExistsTest() {
		givenContactDAO();
		givenContactService();
		
		contactService.createContact(Contact.builder()
				.id(1)
				.name("jonatan")
				.number("1139586203")
				.userid(1)
				.build());
		
		Contact contact = contactService.validateContactExistInDb(Contact.builder()
				.id(1)
				.name("jonatan")
				.number("1139586203")
				.userid(1)
				.build());
		
		assertTrue(contact.getName().equals("jonatan") && 
				contact.getNumber().equals("1139586203") && 
				contact.getUserid() == 1 && 
				contact.getId() == 1);
	}
	
	@Test
	public void validateContactExistsByIdExceptionTest() {
		givenContactDAO();
		givenContactService();
		
		assertThrows(ResourceNotFoundException.class, () -> {
			contactService.validateContactExistsInDbById(2);
	  });
	}
	
	@Test
	public void validateContactExistsByIdTest() {
		givenContactDAO();
		givenContactService();
		
		contactService.createContact(Contact.builder()
				.id(1)
				.name("jonatan")
				.number("1139586203")
				.userid(1)
				.build());
		
		Contact contact = contactService.validateContactExistsInDbById(1);
		
		assertTrue(contact.getName().equals("jonatan") && 
				contact.getNumber().equals("1139586203") && 
				contact.getUserid() == 1 && 
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
					.userid(1)
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
					.userid(1)
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
					.userid(1)
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
					.userid(1)
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
					.userid(1)
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
