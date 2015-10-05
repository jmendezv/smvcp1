package pep.mendez.smvcp1.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import pep.mendez.smvcp1.spring.model.repository.RepositoryDef;

/**
 * Equivalent to <jpa:repositories/> to enable JPA Repositories
 * 
 * Will scan the package of the annotated configuration class for Spring Data
 * repositories by default
 * 
 * This scans all packages below com.msinaptiks.root.model.dao for interfaces
 * extending JpaRepository and creates a Spring bean for it that is backed by an
 * implementation of SimpleJpaRepository
 * 
 * CRUD methods of the Spring Data JPA repository implementation are already
 * annotated with @Transactional
 * 
 * The 'Impl' postfix can be modified with repository-impl-postfix
 * 
 * @author pep
 *
 */
@Configuration
// will fail if classes are refactored
//@EnableJpaRepositories(basePackages = { "pep.mendez.smvcp1.spring.model.repository" }, repositoryImplementationPostfix = "Impl", enableDefaultTransactions = true)
@EnableJpaRepositories(basePackageClasses = {RepositoryDef.class}, repositoryImplementationPostfix = "Impl", enableDefaultTransactions = true)
@EnableTransactionManagement
public class JpaRepositoriesConfig {

}
