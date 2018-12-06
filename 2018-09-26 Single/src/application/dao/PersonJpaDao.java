package application.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entities.Person;
import application.repo.PersonJpaRepository;

@Service
public class PersonJpaDao{
	
	@Autowired
	PersonJpaRepository repo1;

	public List<Person> getAll() {
		return repo1.findAll();
	}

	
}
