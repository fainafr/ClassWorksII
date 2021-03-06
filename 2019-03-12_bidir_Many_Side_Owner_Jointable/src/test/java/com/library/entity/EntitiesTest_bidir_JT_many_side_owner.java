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
import org.springframework.dao.InvalidDataAccessApiUsageException;
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
public class EntitiesTest_bidir_JT_many_side_owner {

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

	}

	/**
	 * No cascade from saving side -> exception;
	 */
	@Test
	public void incorrectSavingSide_incorrectBidirection() { // <- why no exception?

		ALYSSA.getEvents().add(TESTING);
		userRepo.save(ALYSSA);
		
		assertEquals(userRepo.count(), 1);
		assertEquals(eventRepo.count(), 0);
	}
	
	/**
	 * 
	 * Cascading from saving side, but the relation is managed only from the opposite side; 
	 */
	@Test
	public void incorrectBidirection() {

		ALYSSA.getEvents().add(TESTING);
		eventRepo.save(TESTING);

		assertEquals(userRepo.count(), 0);
		assertEquals(eventRepo.count(), 1);
	}
	
	/**
	 * Null instead of transient entity enables to save
	 */
	@Test
	public void incorrectSavingSide() {

		TESTING.setUser(ALYSSA);
		userRepo.save(ALYSSA);
	
		assertEquals(userRepo.count(), 1);
		assertEquals(eventRepo.count(), 0);

	}
	
	/**
	 * 
	 * Cascading from saving side, and the relation is managed from the saving side; 
	 * This is the correct saving procedure, but can we get the relation to work automatically?
	 */
	@Test
	public void CorrectSideAndNoAutomaticBidirection() {
		
		TESTING.setUser(ALYSSA);
		eventRepo.save(TESTING);
		
		assertEquals(userRepo.count(), 1);
		assertEquals(eventRepo.count(), 1);
		
		assertEquals(eventRepo.findAll().get(0).getUser(), ALYSSA);
		assertEquals(userRepo.findAll().get(0).getEvents().size(), 1);  //<- still cascading side does not mean second dir
	}
	



}
