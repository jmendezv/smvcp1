package pep.mendez.smvcp1.spring.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pep.mendez.smvcp1.spring.model.entities.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

	@Modifying
	// @Transactional
	@Query(value = "delete from Authority a where a.userName = :userName")
	public void deleteAllAuthorities(@Param("userName") String userName);

}
