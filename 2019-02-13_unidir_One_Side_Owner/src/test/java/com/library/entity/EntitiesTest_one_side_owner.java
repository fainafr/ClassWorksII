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
public class EntitiesTest_one_side_owner {

	private final String TNAME = "TEAM_A";
	private final String HNAME = "Hans";
	private final Team TEAM_A = new Team();
	private final Employee HANS = new Employee(HNAME);

	@Autowired
	ITeamRepo teamRepo;
	@Autowired
	IEmployeeRepo employeeRepo;

	@Before
	public void build() {

		TEAM_A.setName(TNAME);
		TEAM_A.getEmployees().add(HANS);
	}

	@Test
	public void saveRead() {

		teamRepo.save(TEAM_A);

		Team savedT = teamRepo.getOne(TNAME);
		Employee savedE = employeeRepo.getOne(HNAME);
		Employee savedEfromT = savedT.getEmployees().iterator().next();

		assertEquals(savedT, TEAM_A);
		assertEquals(savedE, HANS);
		assertEquals(savedEfromT, HANS);

	}
	
	/**
	 * Testing no cascading from child in one-to-many unidir where parent is owner;
	 */
	@Test
	public void noCascading() {

		employeeRepo.save(HANS);
		
		assertEquals(teamRepo.count(), 0);
		assertEquals(employeeRepo.count(), 1);
		
	}

}
