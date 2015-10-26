package pep.mendez.smvcp1.spring.model.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pep.mendez.smvcp1.spring.model.entities.UserEntity;
import pep.mendez.smvcp1.spring.model.repository.UserRepository;

/**
 * @author pep
 *
 */
@Service
@Transactional
public class UserService {
	private static final int PAGE_SIZE = 5;

	@Autowired
	private UserRepository userRepository;

	public UserEntity findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public Collection<UserEntity> findAll() {
		return userRepository.findAll();
	}

	/*
	 * PageRequest is a basic implementation of Pageable
	 */
	public Page<UserEntity> findAll(final int pageNumber) {
		PageRequest pageRequest = new PageRequest(pageNumber, PAGE_SIZE,
				Sort.Direction.ASC, "id");
		return userRepository.findAll(pageRequest);
	}

	public UserEntity save(UserEntity user) {
		return userRepository.save(user);
	}

	public UserEntity findOne(long id) {
		return userRepository.findOne(id);
	}
	
	public void delete(UserEntity user) {
		userRepository.delete(user);
	}
	
	public void delete(long id) {
		userRepository.delete(id);
	}

}
