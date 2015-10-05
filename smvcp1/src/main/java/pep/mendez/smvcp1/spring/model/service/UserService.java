package pep.mendez.smvcp1.spring.model.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pep.mendez.smvcp1.spring.model.entities.User;
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

	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public Collection<User> findAll() {
		return userRepository.findAll();
	}

	/*
	 * PageRequest is a basic implementation of Pageable
	 */
	public Page<User> findAll(final int pageNumber) {
		PageRequest pageRequest = new PageRequest(pageNumber, PAGE_SIZE,
				Sort.Direction.ASC, "id");
		return userRepository.findAll(pageRequest);
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public User findOne(long id) {
		return userRepository.findOne(id);
	}
	
	public void delete(long id) {
		userRepository.delete(id);
	}

}
