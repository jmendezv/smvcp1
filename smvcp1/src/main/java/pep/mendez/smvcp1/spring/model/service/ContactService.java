package pep.mendez.smvcp1.spring.model.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pep.mendez.smvcp1.spring.model.entities.Contact;
import pep.mendez.smvcp1.spring.model.entities.User;
import pep.mendez.smvcp1.spring.model.repository.ContactRepository;

@Service
@Transactional
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;

	public Contact findByuserName(String userName) {
		return contactRepository.findByUserName(userName);
	}

	public Collection<Contact> findAll() {
		return contactRepository.findAll();
	}

	public Contact save(Contact contact) {
		return contactRepository.save(contact);
	}

	public Contact findOne(long id) {
		return contactRepository.findOne(id);
	}

	public void delete(Contact contact) {
		contactRepository.delete(contact);
	}

	public void delete(long id) {
		contactRepository.delete(id);
	}

}
