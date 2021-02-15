package com.agenda.web.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda.web.exception.InvalidDataException;
import com.agenda.web.exception.NullDataException;
import com.agenda.web.exception.ResourceNotFoundException;
import com.agenda.web.model.Contact;
import com.agenda.web.repository.ContactDAOInterface;
import com.agenda.web.service.ContactServiceInterface;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ContactServiceImplementation implements ContactServiceInterface  {
	
	@Autowired
	private ContactDAOInterface contactDAO;
	
	@Override
	public Contact createContact(Contact contact) {
		validateContact(contact);
		contactDAO.save(contact);
		return contact;
	}

	@Override
	public Contact updateContact(Contact contact) {
		validateContact(contact);
		
		Contact contactUpdate = validateContactExistInDb(contact);
		
		contactUpdate.setName(contact.getName());
		contactUpdate.setNumber(contact.getNumber());
		
		return contactUpdate;
	}

	@Override
	public void deleteContact(Long id) {
		Contact deleteContact = validateContactExistsInDbById(id);
		contactDAO.delete(deleteContact);
	}

	@Override
	public List<Contact> getAllContactByUserId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateContact(Contact contact) {
		if(contact.getNumber() == null)
		{
			throw new NullDataException("El numero no puede ser nulo");
		}
		else {
			Pattern pattern = Pattern.compile("(\\d{10})");
			Matcher matcher = pattern.matcher(contact.getNumber()); 
			
			if(!matcher.find())
			{
				throw new InvalidDataException("nuemro de telefono invalido");
			}
		}
		if(contact.getName() == null)
		{
			throw new NullDataException("El nombre no puede ser nulo");
		}
		if(contact.getName().length() == 0 )
		{
			throw new InvalidDataException("El nombre no puede ser vacio");
		}
	}

	@Override
	public Contact validateContactExistInDb(Contact contact) {
		Optional<Contact> contactDb = contactDAO.findById(contact.getId());
		
		if(!contactDb.isPresent()){
			throw new ResourceNotFoundException("No se encontró el contacto con el id: " + contact.getId());
		}
		else{
			return contactDb.get();
		}
	}
	
	public Contact validateContactExistsInDbById(long id)
	{
		Optional<Contact> contactDb = contactDAO.findById(id);
		
		if(!contactDb.isPresent()){
			throw new ResourceNotFoundException("No se encontró el contacto con el id: " + id);
		}
		else{
			return contactDb.get();
		}	
	}
}
