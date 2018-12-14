package application;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import application.model.LibraryModel;

@SpringBootApplication
public class LibrarySQL_ServerApp {

	public static void main(String[] args) throws SQLException {
		if(!LibraryModel.setConnection()) {
			System.out.println("DB connection failed.");
			return;
		}
		
		SpringApplication.run(LibrarySQL_ServerApp.class, args);
	}

}
