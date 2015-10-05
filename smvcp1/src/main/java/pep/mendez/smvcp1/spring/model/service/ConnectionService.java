package pep.mendez.smvcp1.spring.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pep.mendez.smvcp1.spring.model.entities.Connection;
import pep.mendez.smvcp1.spring.model.repository.ConnectionRepository;

@Service
@Transactional
public class ConnectionService {
	
	@Autowired
	private ConnectionRepository connectionRepository;
	
	@Secured({ "ROLE_ADMIN" })
	public void save(Connection connection) {
		connectionRepository.save(connection);
	}

}
