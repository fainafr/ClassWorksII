package com.library.entity;

import static org.junit.Assert.assertFalse;

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

	private static final String ALYSSA = "Alyssa";
	private static final String BITDIDDLE = "Ben";
	private static final String TESTING = "Red Sea";
	private static final Event TESTING_BEN = new Event(TESTING, new User(BITDIDDLE));

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

		createdA.getPublishers().add(TESTING_BEN);

		createdB.getPublishers().add(TESTING_BEN);

		Event createdE = new Event(TESTING_BEN);

		eventRepo.save(createdE);
		userRepo.save(createdA);
		userRepo.save(createdB); // TODO: now saves because Event is the host and it had saved the user; 

		assertFalse(true); // can't allow me to place TESTING_BEN into both A and B;

	}

}
