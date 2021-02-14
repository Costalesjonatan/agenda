package com.agenda.web;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.agenda.web.implementation.ContactServiceImplementation;
import com.agenda.web.model.Contact;
import com.agenda.web.repository.ContactDAOInterface;

class ContactServiceTest {
	
	private ContactServiceImplementation contactService;
	private ContactDAOInterface contactDAO;

	@Test
	public void shouldCreateContact() {
		giverContactService();
		givenContactDAO();
		
		contactService.createContact(Contact.builder()
				.id(1)
				.name("jonatan")
				.number("1139586203")
				.idUser(1)
				.build());
		
		Optional<Contact> contactCreated = contactDAO.findById((long) 1);
		Contact contact = contactCreated.get();
		
		assertTrue(contact.getName().equals("jonatan") && contact.getNumber().equals("1139586203") && contact.getIdUser() == 1);
	}
	
	private void giverContactService() {
		contactService = new ContactServiceImplementation();
	}
	
	private void givenContactDAO() {
		contactDAO = new MockContactDAO();
	}
	
}
