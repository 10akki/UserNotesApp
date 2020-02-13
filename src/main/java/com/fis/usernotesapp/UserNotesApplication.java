package com.fis.usernotesapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan({ "com.fis.usernotesapp" })
@EnableJpaAuditing
public class UserNotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserNotesApplication.class, args);
	}

}
