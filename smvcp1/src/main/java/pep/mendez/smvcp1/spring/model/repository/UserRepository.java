package pep.mendez.smvcp1.spring.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pep.mendez.smvcp1.spring.model.entities.User;

/**
 * 
 * Spring Data Repository
 * 
 * The goal of Spring Data repository abstraction is to significantly reduce the
 * amount of boilerplate code required to implement data access layers for
 * various persistence stores.
 * 
 * Defining this interface serves two purposes:
 * 
 * First, by extending JpaRepository we get a bunch of generic CRUD methods into
 * our type that allows us saving User, deleting them and so on.
 * 
 * Second, this will allow the Spring Data JPA repository infrastructure to scan
 * the classpath for this interface and create a Spring bean for it
 * automatically!!!
 * 
 * Spring will implement all JpaRepository interfaces at application start up
 * time.
 * 
 * @author pep
 *
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long>,
		ExtendedUserRepository {

	/*
	 * 
	 * The JpaRepository interface extends Spring Data’s
	 * PagingAndSortingRepository interface, so I get some paging/sorting
	 * finders for free.
	 * 
	 * @Cacheable: indicates that Spring should look in a cache for the method's
	 * return type before invoking the method. If the value is found, the cached
	 * value is returned. If not, then the method is invoked and the return
	 * value is put in the cache.
	 * 
	 * 
	 * @CachePut: Indicates that Spring should put the method's return value in
	 * a cache. The cache isn't checked prior to method invocation, and the
	 * method is always invoked.
	 * 
	 * @CacheEvict: Indicates that Spring should evict one or more entries from
	 * a cache.
	 * 
	 * @Caching: A grouping annotation for applying multiples of the other
	 * caching annotations at once.
	 */
	//@Cacheable(value = "usersCache", condition = "true", unless = "false")
	//@Lock(LockModeType.READ)
	User findByUserName(String userName);

}
