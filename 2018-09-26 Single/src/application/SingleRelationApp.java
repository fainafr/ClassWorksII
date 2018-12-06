package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan({"application.dao","application.controller"})
@EnableJpaRepositories("application.repo")
//@EntityScan("application.entities")

public class SingleRelationApp {
	
	public static void main(String[] args) {
		
		SpringApplication.run(SingleRelationApp.class, args);
	}

}
