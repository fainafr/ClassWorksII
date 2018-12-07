package application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.entities.Person;
import application.model.IPersonJpaModel;

@RestController
@RequestMapping(value = "/person")
public class PersonsJpaController implements IPersonsJpaController {

	@Autowired
	IPersonJpaModel model;
	
	@GetMapping(value = "/getall", produces = "application/json")
	@Override
	public List<Person> findAll() {
		
		return model.findAll();
		
	}

}
