package com.fis.usernotesapp.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Class to configure database connection using hibernate
 * @author Akhil Garg
 *
 */
@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {
	@Value("${spring.datasource.driver-class-name}")
	private String DRIVER;
 
	@Value("${spring.datasource.password}")
	private String PASSWORD;
 
	@Value("${spring.datasource.url}")
	private String URL;
 
	@Value("${spring.datasource.username}")
	private String USERNAME;
 
	@Value("${hibernate.dialect}")
	private String DIALECT;
 
	@Value("${hibernate.show_sql}")
	private String SHOW_SQL;
 
	@Value("${hibernate.hbm2ddl.auto}")
	private String HBM2DDL_AUTO;
 
	@Value("${entitymanager.packagesToScan}")
	private String PACKAGES_TO_SCAN;
 
	/**
	 * Method to create datasource object
	 * @return datasource
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		return dataSource;
	}
 
	/**
	 * Method to create sessionFactory
	 * @return sessionFactory
	 * 
	 */
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN);
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", DIALECT);
		hibernateProperties.put("hibernate.show_sql", SHOW_SQL);
		hibernateProperties.put("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);
		sessionFactory.setHibernateProperties(hibernateProperties);
 
		return sessionFactory;
	}
 
	/**
	 * Method to handle hibernate transaction
	 * @return transactionManager
	 */
	@Bean(name="entityManagerFactory")
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}	
}
 