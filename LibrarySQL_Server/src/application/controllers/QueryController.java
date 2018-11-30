package application.controllers;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.model.LibraryModel;
import dto.Publisher;

@RestController
@RequestMapping(value="/query")
public class QueryController {
	
	@GetMapping(value="/getPublisherById")
	public Publisher getPublisherById(@RequestParam(value="id") int id) throws SQLException {
		return LibraryModel.getPublisherById(id);
	}
	
	@GetMapping(value="/getPublishersByCountry")
	public Iterable<Publisher> getPublisherById(@RequestParam(value="country") String country) throws SQLException {
		return LibraryModel.getPublishersByCountry(country);
	}

}
