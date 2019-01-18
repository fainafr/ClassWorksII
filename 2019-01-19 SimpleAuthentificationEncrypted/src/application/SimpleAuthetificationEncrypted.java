package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@ComponentScan({"controllers", "model", "security"})
@EnableJpaRepositories("repo")
@EntityScan("entities")

public class SimpleAuthetificationEncrypted {
	
	public static void main(String[] args) {
		SpringApplication.run(SimpleAuthetificationEncrypted.class, args);
	}
}
