package application.controller;

import java.util.List;

import application.entities.Person;
import application.entities.PesonCompositeID;

public interface IPersonsJpaController {
	
	public List<Person> findAll();
	
	public Person findById(String name, String surname);

	public Person findById(PesonCompositeID id);

	public void putPeron(Person person);
	
}
