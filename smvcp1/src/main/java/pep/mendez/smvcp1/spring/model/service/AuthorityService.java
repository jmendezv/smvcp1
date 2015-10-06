package pep.mendez.smvcp1.spring.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pep.mendez.smvcp1.spring.model.entities.Authority;
import pep.mendez.smvcp1.spring.model.repository.AuthorityRepository;

/**
 * @author pep
 *
 */
@Service
@Transactional
public class AuthorityService {

	@Autowired
	private AuthorityRepository authorityRepository;

	public void delete(Authority authority) {
		authorityRepository.delete(authority);
	}

	public void deleteAllAuthorities(String userName) {
		authorityRepository.deleteAllAuthorities(userName);
	}

}
