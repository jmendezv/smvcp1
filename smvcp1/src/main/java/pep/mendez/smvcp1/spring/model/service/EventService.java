package pep.mendez.smvcp1.spring.model.service;

import java.util.Collection;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pep.mendez.smvcp1.spring.model.entities.EventEntity;
import pep.mendez.smvcp1.spring.model.repository.EventRepository;

@Service
@Transactional
public class EventService {
	
	@Autowired
	private EventRepository eventRepository;
	
	@Transactional(readOnly = true)
	public Collection<EventEntity> findAll() {
		return eventRepository.findAll();
	}

}
