package pep.mendez.smvcp1.spring.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pep.mendez.smvcp1.spring.model.entities.ProfileEntity;

/**
 * @author pep
 *
 */
@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

}
