package pep.mendez.smvcp1.spring.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pep.mendez.smvcp1.spring.model.entities.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

	public Contact findByUserName(String userName);
	
}
