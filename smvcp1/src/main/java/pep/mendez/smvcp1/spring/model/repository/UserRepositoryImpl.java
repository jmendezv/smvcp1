package pep.mendez.smvcp1.spring.model.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

/**
 * Notice that this class DOES NOT implements UserRepository interface.
 * 
 * Spring Data JPA is still responsible for implementing that interface. Instead
 * it implements ExtendedUserRepository. You must also make sure that
 * UserRepository interface extends both, JpaRepository and
 * ExtendedUserRepository.
 * 
 * @author pep
 *
 */
@Repository
public class UserRepositoryImpl implements ExtendedUserRepository {

	@PersistenceContext
	private EntityManager entityManager;

	/* @Autowired */
	@Inject
	private UserRepository userRepository;

	@Override
	public void enableAll() {
		entityManager.createQuery("UPDATE User user SET user.enabled = 1 WHERE user.enabled = 0")
				.executeUpdate();
	}

}
