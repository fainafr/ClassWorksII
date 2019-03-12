package com.library.entity;

import static org.junit.Assert.assertEquals;
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
public class EntitiesTest_bidir_no_owner {

	private final String ANAME = "Alyssa";
	private final String TNAME = "Testing"; 
	private final User ALYSSA = new User();
	private final Event TESTING = new Event();

	@Autowired
	IUserRepo userRepo;
	@Autowired
	IEventRepo eventRepo;

	@Before
	public void build() {
		
		ALYSSA.setUserName(ANAME);
		TESTING.setName(TNAME);
		TESTING.setUser(ALYSSA);

	}

	@Test
	public void saveRead() {

		eventRepo.save(TESTING);

		Event savedE = eventRepo.findAll().get(0); 
		User savedA = userRepo.findAll().get(0);
		User savedAfromE = savedE.getUser();
		
		assertEquals(savedE, TESTING);
		assertEquals(savedA, ALYSSA);
		assertEquals(savedAfromE, ALYSSA);
		

	}
	
	/**
	 * Testing no cascading from parent in one-to-many unidir where child is owner;
	 */
	@Test
	public void noCascadingParent() {

		userRepo.save(ALYSSA);

		assertEquals(eventRepo.count(), 0);
		assertEquals(userRepo.count(), 1);
		
		

	}
	
	@Test
	public void noCascadingChild() {

		eventRepo.save(TESTING);

		assertEquals(eventRepo.count(), 1);
		assertEquals(userRepo.count(), 0);
		
		

	}

}
