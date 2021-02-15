package com.agenda.web.service;

import java.util.List;

import com.agenda.web.model.Contact;

public interface ContactServiceInterface {
	
	public Contact createContact(Contact contact);
	public Contact updateContact(Contact contact);
	public void deleteContact(Long id);
	public List<Contact> getAllContactsOfAuserForId(long id);
	public void validateContact(Contact contact);
	public Contact validateContactExistInDb(Contact contact);
	public Contact validateContactExistsInDbById(long id);
}
