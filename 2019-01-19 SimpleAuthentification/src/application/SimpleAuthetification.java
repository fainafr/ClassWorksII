package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*@RestController
@ComponentScan({"controllers", "model", "security"})
@EnableJpaRepositories("repo")
@EntityScan("entities")*/

public class SimpleAuthetification {
	
	public static void main(String[] args) {
		SpringApplication.run(SimpleAuthetification.class, args);
	}
}
