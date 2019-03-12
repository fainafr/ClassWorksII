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

	private final String TEAMA = "TEAM_A";
	private final String HANS = "Hans";
	private final Team teamA = new Team();
	private final Employee emplH = new Employee(HANS);

	@Autowired
	ITeamRepo teamRepo;
	@Autowired
	IEmployeeRepo employeeRepo;

	@Before
	public void build() {

		teamA.setName(TEAMA);
		teamA.getEmployees().add(emplH);
	}

	@Test
	public void saveRead() {

		teamRepo.save(teamA);

		Team savedT = teamRepo.getOne(TEAMA);
		Employee savedE = employeeRepo.getOne(HANS);
		Employee savedEfromT = savedT.getEmployees().iterator().next();

		assertEquals(savedT, teamA);
		assertEquals(savedE, emplH);
		assertEquals(savedEfromT, emplH);

	}

}
