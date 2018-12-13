package application.model;

import java.util.List;

import application.entities.Person;
import application.entities.PesonCompositeID;

public interface IPersonJpaModel {
	
	public List<Person> findAll();
	
	public Person findPersonById(PesonCompositeID personId);

	public void put(Person person);
	
}
