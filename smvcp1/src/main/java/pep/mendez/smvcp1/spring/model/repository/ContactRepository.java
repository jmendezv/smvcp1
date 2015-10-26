package pep.mendez.smvcp1.spring.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pep.mendez.smvcp1.spring.model.entities.ContactEntity;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {

	public ContactEntity findByUserName(String userName);
	
}
