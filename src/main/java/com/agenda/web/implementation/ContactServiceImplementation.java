package com.agenda.web.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda.web.model.Contact;
import com.agenda.web.repository.ContactDAOInterface;
import com.agenda.web.service.ContactServiceInterface;

@Service
public class ContactServiceImplementation implements ContactServiceInterface  {
	
	@Autowired
	private ContactDAOInterface contactDAO;
	
	@Override
	public Contact createContact(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact updateContact(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteConatct(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Contact> getAllContactsOfAuserForId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}
}
