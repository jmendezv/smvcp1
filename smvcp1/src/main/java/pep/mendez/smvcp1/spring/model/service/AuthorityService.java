package pep.mendez.smvcp1.spring.model.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pep.mendez.smvcp1.spring.model.entities.Authority;
import pep.mendez.smvcp1.spring.model.repository.AuthorityRepository;

@Service
@Transactional
public class AuthorityService {

	@Inject
	private AuthorityRepository authorityRepository;

	public void delete(Authority authority) {
		authorityRepository.delete(authority);
	}

	public void deleteAllAuthorities(String userName) {
		authorityRepository.deleteAllAuthorities(userName);
	}

}
