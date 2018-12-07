package application.controller;

import java.util.List;

import application.entities.Person;

public interface IPersonsJpaController {
	
	public List<Person> findAll();
	
}
