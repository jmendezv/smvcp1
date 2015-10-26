package pep.mendez.smvcp1.spring.model.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pep.mendez.smvcp1.spring.model.entities.ContactEntity;
import pep.mendez.smvcp1.spring.model.entities.UserEntity;
import pep.mendez.smvcp1.spring.model.repository.ContactRepository;

@Service
@Transactional
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;

	public ContactEntity findByuserName(String userName) {
		return contactRepository.findByUserName(userName);
	}

	public Collection<ContactEntity> findAll() {
		return contactRepository.findAll();
	}

	public ContactEntity save(ContactEntity contact) {
		return contactRepository.save(contact);
	}

	public ContactEntity findOne(long id) {
		return contactRepository.findOne(id);
	}

	public void delete(ContactEntity contact) {
		contactRepository.delete(contact);
	}

	public void delete(long id) {
		contactRepository.delete(id);
	}

}
