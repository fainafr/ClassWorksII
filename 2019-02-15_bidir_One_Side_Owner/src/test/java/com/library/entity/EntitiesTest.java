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

import com.library.repo.IEventRepo;
import com.library.repo.IUserRepo;

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

	private final String ALYSSA = "Alyssa";
	private final String BITDIDDLE = "Ben";
	private final String TESTING = "Testing"; 
	private final Event TESTING_BEN = new Event(TESTING, new User(BITDIDDLE));

	@PersistenceContext // https://www.javabullets.com/access-entitymanager-spring-data-jpa/
	private EntityManager em;

	@Autowired
	IUserRepo userRepo;
	@Autowired
	IEventRepo eventRepo;

	@Before
	public void clear() {

		eventRepo.deleteAll();
		userRepo.deleteAll();

	}

	/**
	 * Testing cascade add, without explicit saving;
	 */
	@Test
	public void addChildToBothParentsShouldFail() {

		User createdA = new User(ALYSSA);

		User createdB = new User(BITDIDDLE);

		Event createdE = new Event(TESTING_BEN);

		eventRepo.save(createdE);
		userRepo.save(createdA);
		userRepo.save(createdB); 
		
		assertTrue(eventRepo.findAll().get(0).getUser().equals(new User(BITDIDDLE))); 
		// TODO: Bidirectional
		// can't allow me to place TESTING_BEN into both A and B;

	}

}
