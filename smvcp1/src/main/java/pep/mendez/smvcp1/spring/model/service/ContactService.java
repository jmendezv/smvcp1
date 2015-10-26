package pep.mendez.smvcp1.spring.model.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pep.mendez.smvcp1.spring.model.entities.ContactEntity;
<<<<<<< HEAD
=======
import pep.mendez.smvcp1.spring.model.entities.UserEntity;
>>>>>>> branch 'master' of https://jmendezv@bitbucket.org/jmendezv/smvcp1.git
import pep.mendez.smvcp1.spring.model.repository.ContactRepository;

@Service
// readOnly defaults to false
@Transactional
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;

	@Transactional(readOnly = true)
	public ContactEntity findByuserName(String userName) {
		return contactRepository.findByUserName(userName);
	}


	@Transactional(readOnly = true)
	public Collection<ContactEntity> findAll() {
		return contactRepository.findAll();
	}

	public ContactEntity save(ContactEntity contact) {
		return contactRepository.save(contact);
	}


	@Transactional(readOnly = true)
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
