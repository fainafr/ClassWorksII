package application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.entities.Person;
import application.entities.PesonCompositeID;
import application.model.IPersonJpaModel;

@RestController
@RequestMapping(value = "/person")
public class PersonsJpaController implements IPersonsJpaController {

	@Autowired
	IPersonJpaModel model;
	
	@GetMapping(value = "/all", produces = "application/json")
	@Override
	public List<Person> findAll() {
		
		return model.findAll();
		
	}

	@Override
	@GetMapping(value = "/id", produces = "application/json")
	public Person findById(@RequestParam(value="name") String name, @RequestParam(value="surname") String surname) {
	
		return model.findPersonById(new PesonCompositeID(name, surname));
		
	}

}
