package com.library.entity;

import static org.junit.Assert.assertEquals;

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
public class EntitiesTest_many_side_owner {

	private final String GERMANY = "Germany";
	private final String HANS = "Hans";
	private final Employee HANS_GERMANY = new Employee();
	private final ManagedTeam MERCEDES = new ManagedTeam();

	@Autowired
	ITeamRepo teamRepo;
	@Autowired
	IEmployeeRepo employeeRepo;

	@Before
	public void build() {
		
		MERCEDES.setTeamName(GERMANY);
		HANS_GERMANY.setEmployeeName(HANS);
		HANS_GERMANY.setTeam(MERCEDES);
		
	}

	/**
	 * Testing cascade add, without explicit saving; many side (employee) is the
	 * boss;
	 */
	@Test
	public void childShowsUpInParentSet() {

		employeeRepo.save(HANS_GERMANY);
		assertEquals(teamRepo.count(), 1);
		assertEquals(employeeRepo.count(), 1);
		
	}

}
