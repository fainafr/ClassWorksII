package com.library.entity;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.repo.IEmployeeRepo;
import com.library.repo.ITeamRepo;

/**
 * Testing creation and deletion of entities to ensure relations work as
 * intended;
 * 
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class EntitiesTest {

	private static final String GERMANY = "Germany";
	private static final String HANS = "Hans";
	private static final Employee HANS_GERMANY = new Employee(HANS, new Team(GERMANY));

	@PersistenceContext // https://www.javabullets.com/access-entitymanager-spring-data-jpa/
	private EntityManager em;

	@Autowired
	ITeamRepo countryRepo;
	@Autowired
	IEmployeeRepo publisherRepo;

	@Before
	public void clear() {

		publisherRepo.deleteAll();
		countryRepo.deleteAll();

	}

	/**
	 * Testing cascade add, without explicit saving; many side (publisher) is the
	 * boss;
	 */
	@Test
	public void childShowsUpInParentSet() {

		publisherRepo.save(new Employee(HANS_GERMANY));

		Team createdT = new Team(GERMANY);

		assertTrue(createdT.equals(HANS_GERMANY.getCountryName()));

		Employee persistedE = publisherRepo.findById(HANS).get(); 

		assertTrue(createdT.getPublishers().contains(persistedE)); // should be seen in the one side of the
																	// relationship;
		//TODO: now not works because despite the host is the publisher, we have bidirectional(?) link. 

	}

}
