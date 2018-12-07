package application.model;

import java.util.List;

import application.entities.Person;

public interface IPersonJpaModel {
	
	public List<Person> findAll();
	
}
