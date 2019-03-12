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
public class EntitiesTest_bidir_one_side_owner {

	private final String ANAME = "Alyssa";
	private final String TNAME = "Testing"; 
	private final User ALYSSA = new User();
	private final IndependentEvent TESTING = new IndependentEvent();


	@Autowired
	IUserRepo userRepo;
	@Autowired
	IEventRepo eventRepo;

	@Before
	public void build() {
		ALYSSA.setUserName(ANAME);
		TESTING.setName(TNAME);
		ALYSSA.getEvents().add(TESTING);
	}

	/**
	 * Testing without cascade add, without explicit saving;
	 */
	@Test
	public void saveRead() {
		
		userRepo.save(ALYSSA);
		
		User savedA = userRepo.findAll().get(0);
		assertEquals(savedA, ALYSSA);
		System.out.println(savedA);
		
		assertEquals(eventRepo.count(), 0);
		
	

	}
	
	

}
