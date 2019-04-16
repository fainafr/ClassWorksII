package application;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    /**
     * A bonus from https://spring.io/guides/gs/spring-boot/
     * @param ctx
     * @return
     * @throws InterruptedException
     */
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) throws InterruptedException {
    	return args -> { 

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
	
        };
    }
}
