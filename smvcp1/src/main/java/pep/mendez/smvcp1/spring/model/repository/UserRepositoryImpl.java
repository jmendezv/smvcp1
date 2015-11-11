package pep.mendez.smvcp1.spring.model.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
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

	/*
	 * Injects the entityManagerFactory bean of type
	 * LocalContainerEntityManagerFactoryBean
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/* @Autowired */
	@Inject
	private UserRepository userRepository;

	@Override
	public void enableAll() {
		entityManager
				.createQuery(
						"UPDATE UserEntity user SET user.enabled = 1 WHERE user.enabled = 0")
				.executeUpdate();
	}

	@Override
	public long countUsers() {
		StoredProcedureQuery query = entityManager
				.createStoredProcedureQuery("count_users");
		query.registerStoredProcedureParameter("total", Long.class, ParameterMode.OUT);
		query.execute();
		long total = (long) query.getOutputParameterValue("total");
		return total;
	}

}
