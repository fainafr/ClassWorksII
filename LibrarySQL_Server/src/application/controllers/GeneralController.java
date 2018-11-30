package application.controllers;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.model.LibraryModel;

@RestController
@RequestMapping(value="/general")
public class GeneralController {
	
	@PostMapping(value="/addCountry")
	public int addCountry(@RequestParam(value="country") String country) throws SQLException {
		return LibraryModel.findOrAddCountry(country);
	}
	
	@PostMapping(value="/addAuthor")
	public int addAuthor(@RequestParam(value="first_name") String first_name,
						 @RequestParam(value="last_name") String last_name) throws SQLException {
		return LibraryModel.findOrAddAuthor(first_name, last_name);
	}
	
	@PostMapping(value="/addPublisher")
	public int addPublisher(@RequestParam(value="publisher_name") String publisher_name,
						    @RequestParam(value="country") String country) throws SQLException {
		return LibraryModel.findOrAddPublisher(publisher_name, country);
	}
	

}
