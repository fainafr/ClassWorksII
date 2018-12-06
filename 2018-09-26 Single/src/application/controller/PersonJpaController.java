package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import application.dao.PersonJpaDao;
import application.entities.Person;

@RestController
public class PersonJpaController{
	
	@Autowired
	PersonJpaDao people;
	
	@GetMapping(value="/getAll")
	boolean getAll() {
		for(Person p : people.getAll())System.out.println(p);
		return true;
	};

}
