package pep.mendez.smvcp1.spring.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pep.mendez.smvcp1.spring.model.entities.EventEntity;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {

}
