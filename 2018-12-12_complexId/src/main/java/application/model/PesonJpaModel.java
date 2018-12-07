package application.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entities.Person;
import application.repo.IPersonsJpaRepo;

@Service
public class PesonJpaModel implements IPersonJpaModel {

	@Autowired
	IPersonsJpaRepo repo;

	@Override
	public List<Person> findAll() {

		return repo.findAll();
		
	}

}
