package application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import application.aspects.LogExecutionTime;


@Component
public class JoinPointExample implements CommandLineRunner {

	@Override
	@LogExecutionTime
	public void run(String... args) throws InterruptedException {
		Thread.sleep(2000);
	}


}
