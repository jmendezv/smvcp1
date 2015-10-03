package pep.mendez.smvcp1.spring.config;

import java.util.Properties;

import javax.sql.DataSource;

import net.sf.ehcache.CacheManager;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import pep.mendez.smvcp1.spring.SpringDef;
import pep.mendez.smvcp1.spring.aop.AdminService2IntroducerAspect;
import pep.mendez.smvcp1.spring.aop.LoggerAspect;
import pep.mendez.smvcp1.spring.validators.ProfileFormValidator;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = false)
@ComponentScan(basePackageClasses = { SpringDef.class }, excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class) })
@PropertySources({ @PropertySource(value = "classpath:application.properties"), })
@EnableCaching
public class SpringContextConfig {

	@Autowired
	Environment env;

	org.slf4j.Logger logger = LoggerFactory
			.getLogger(this.getClass().getName());

	/**
	 * An Apache commons data source
	 * 
	 * @return a data source
	 */
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();

		String url = env.getProperty("jdbc.url");
		String driver = env.getProperty("jdbc.driverClassName");
		String username = env.getProperty("jdbc.username");
		String password = env.getProperty("jdbc.password");

		dataSource.setUrl(url);
		dataSource.setDriverClassName(driver);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.addConnectionProperty("MaxActive", "20");
		dataSource.addConnectionProperty("MaxWait", "60000");
		dataSource.addConnectionProperty("TestWhileIdle", "true");
		dataSource.addConnectionProperty("TimeBetweenEvictionRunsMillis",
				"300000");
		dataSource
				.addConnectionProperty("MinEvictableIdleTimeMillis", "300000");
		dataSource.addConnectionProperty("TestOnBorrow", "true");
		dataSource.addConnectionProperty("ValidationQuery", "SELECT 1");

		return dataSource;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}

	/**
	 * Spring comes with a handful of JPA vendor adapters:
	 * 
	 * EclipseLinkJpaVendorAdapter, HibernateJpaVendorAdapter,
	 * OpenJpaVendorAdapter and TopLinkJpaVendorAdapter (deprecated sin Spring
	 * 3.1)
	 * 
	 * @return a Jpa vendor adapter
	 */
	/*
	 * @Bean public JpaVendorAdapter jpaVendorAdapter() {
	 * HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new
	 * HibernateJpaVendorAdapter();
	 * hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
	 * hibernateJpaVendorAdapter.setShowSql(true);
	 * hibernateJpaVendorAdapter.setGenerateDdl(true); hibernateJpaVendorAdapter
	 * .setDatabasePlatform("org.hibernate.dialect.MySQLDialect"); return
	 * hibernateJpaVendorAdapter; }
	 */

	/**
	 * This is the most powerful way to set up a shared JPA EntityManagerFactory
	 * in a Spring application context; the EntityManagerFactory can then be
	 * passed to JPA-based DAOs via dependency injection.
	 * 
	 * As with LocalEntityManagerFactoryBean, configuration settings are usually
	 * read in from a META-INF/persistence.xml config file, residing in the
	 * class path, according to the general JPA configuration contract.
	 * 
	 * However LocalContainerEntityManagerFactoryBean allows zero xml
	 * configuration as it is the case in this example.
	 * 
	 * @param dataSource
	 * @param jpaVendorAdapter
	 * @return an EntityManagerFactory
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(dataSource());
		localContainerEntityManagerFactoryBean
				.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		localContainerEntityManagerFactoryBean
				.setPackagesToScan("pep.mendez.smvcp1.spring.model.entities");
		Properties jpaProperties = new Properties();
		String hibernateDialect = env.getRequiredProperty("hibernate.dialect");
		/**
		 * This allows Hibernate to create SQL that is optimized for the used
		 * database.
		 */
		jpaProperties.put("hibernate.dialect",
				env.getRequiredProperty("hibernate.dialect"));
		/**
		 * 
		 * This allows Hibernate to create SQL that is optimized for the used
		 * database.
		 */
		jpaProperties.put("hibernate.dialect", hibernateDialect);
		/**
		 * Specifies the action that is invoked to the database when the
		 * Hibernate SessionFactory is created or closes
		 */
		jpaProperties.put("hibernate.hbm2ddl.auto",
				env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		/**
		 * Configures the naming strategy that is used when Hibernate creates
		 * new database objects and schema elements
		 */
		// jpaProperties.put("hibernate.ejb.naming_strategy",
		// env.getRequiredProperty("hibernate.ejb.naming_strategy"));
		/**
		 * If the value of this property is true, Hibernate writes all SQL
		 * statements to the console
		 */
		jpaProperties.put("hibernate.show_sql",
				env.getRequiredProperty("hibernate.show_sql"));
		/**
		 * If the value of this property is true, Hibernate will format the SQL
		 * that is written to the console
		 */
		jpaProperties.put("hibernate.format_sql",
				env.getRequiredProperty("hibernate.format_sql"));

		localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);
		return localContainerEntityManagerFactoryBean;
	}

	@Bean(autowire = Autowire.BY_NAME)
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		return transactionManager;
	}

	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		String host = env.getProperty("mailserver.host");
		String port = env.getProperty("mailserver.port");
		String username = env.getProperty("mailserver.username");
		String password = env.getProperty("mailserver.password");

		mailSender.setHost(host);
		// smtp
		mailSender.setProtocol(JavaMailSenderImpl.DEFAULT_PROTOCOL);
		mailSender.setPort(Integer.valueOf(port));
		mailSender.setUsername(username);
		mailSender.setPassword(password);

		Properties properties = new Properties();

		properties.put("mail.mstp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.debug", "true");
		properties.put("mail.smtp.timeout", "8500");

		mailSender.setJavaMailProperties(properties);

		return mailSender;
	}

	@Bean
	@Qualifier(value = "profileValidator")
	public Validator profileFormValidator() {
		return new ProfileFormValidator();
	}

	/*
	 * Aspect
	 */
	@Bean
	public LoggerAspect loggerAspect() {
		return new LoggerAspect();
	}

	/*
	 * Aspect
	 */
	@Bean
	public AdminService2IntroducerAspect adminService2IntroducerAspect() {
		return new AdminService2IntroducerAspect();
	}

	/*
	 * Ecache
	 */
	@Bean
	public EhCacheCacheManager cacheManager(CacheManager cacheManager) {
		return new EhCacheCacheManager(cacheManager);
	}

	/*
	 * Generates an EhCache CacheManager. Because it's a factory bean
	 * (implements FactoryBean interface), the bean that is registered in the
	 * Spring application context isn't an instance of EhCacheManagerFactoryBean
	 * but rather is an instance of CacheManager, suitable for injection into
	 * EhCacheCacheManager.
	 */
	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactroryBean() {
		EhCacheManagerFactoryBean ehCacheManagerFactroryBean = new EhCacheManagerFactoryBean();
		ehCacheManagerFactroryBean.setConfigLocation(new ClassPathResource(
				"pep/mendez/smvcp1/spring/config/cache/ehcache.xml"));
		ehCacheManagerFactroryBean.setShared(true);
		return ehCacheManagerFactroryBean;
	}
	
}
