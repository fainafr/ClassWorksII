package com.library.entity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

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

import com.library.repo.ICountryRepo;
import com.library.repo.IPublisherRepo;

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

	// TODO: create-delete author and book please

	private static final String GERMANY = "Germany";
	private static final String RED_SEA = "Red Sea";
	private static final List<Integer> JOYCEMARKS = Arrays.asList(new Integer[]{5, 5, 4});
	
	private static final Publisher RED_SEA_GERMANY = new Publisher(RED_SEA, new Country(GERMANY));
	
	
	
	@PersistenceContext // https://www.javabullets.com/access-entitymanager-spring-data-jpa/
	private EntityManager em;
	
	@Autowired
	ICountryRepo countryRepo;
	@Autowired
	IPublisherRepo publisherRepo;

	@Before
	public void clear() {

	
	
		publisherRepo.deleteAll();
		countryRepo.deleteAll();

	}



	/**
	 * Testing cascade add, without explicit saving;
	 */
	@Test
	public void addPublisherCascade() {

		Publisher createdPublisher = new Publisher(RED_SEA_GERMANY);

		publisherRepo.save(createdPublisher);
		publisherRepo.flush();

		Publisher persistedPublisher = publisherRepo.findById(RED_SEA).get();

		assertTrue(createdPublisher.equals(persistedPublisher));

	}
	
	/**
	 * Testing add, with explicit saving
	 */
	@Test
	public void addPublisher() {

		Publisher createdPublisher = new Publisher(RED_SEA);

		publisherRepo.save(createdPublisher);
		publisherRepo.flush();

		Publisher persistedPublisher = publisherRepo.findById(RED_SEA).get();

		assertTrue(createdPublisher.getPublisherName().equals(persistedPublisher.getPublisherName()));

	}

	/**
	 * Testing add, with explicit saving
	 */
	@Test
	public void addPublisherCountry() {

		Publisher createdPublisher = new Publisher(RED_SEA);

		Country createdCountry = new Country(GERMANY);

		countryRepo.save(createdCountry);
		countryRepo.flush();

		createdPublisher.setCountryName(countryRepo.findById(GERMANY).get());

		publisherRepo.save(createdPublisher);
		publisherRepo.flush();

		Publisher persistedPublisher = publisherRepo.findById(RED_SEA).get();
		Country persistedCountry = countryRepo.findById(GERMANY).get();

		assertTrue(createdPublisher.equals(persistedPublisher));
		assertTrue(createdCountry.equals(persistedCountry));
	}

	/**
	 * Testing delete, with explicit removing;
	 */
	@Test
	public void removePublisher() {

		Publisher createdPublisher = new Publisher(RED_SEA);
		publisherRepo.save(createdPublisher);
		publisherRepo.flush();

		publisherRepo.delete(createdPublisher);
		publisherRepo.flush();
		assertFalse(publisherRepo.existsById(RED_SEA));

	}

	/**
	 * Testing delete, with explicit removing;
	 */
	@Test
	public void removeCountry() {

		Country createdCountry = new Country(GERMANY);
		countryRepo.save(createdCountry);
		countryRepo.flush();

		countryRepo.delete(createdCountry);
		countryRepo.flush();
		assertFalse(countryRepo.existsById(GERMANY));

	}

	/**
	 * Testing delete, with explicit removing;
	 */
	@Test
	public void removePublisherCountry() {

		Publisher createdPublisher = new Publisher(RED_SEA);
		Country createdCountry = new Country(GERMANY);

		countryRepo.save(createdCountry);
		countryRepo.flush();

		createdPublisher.setCountryName(countryRepo.findById(GERMANY).get());
		publisherRepo.save(createdPublisher);
		publisherRepo.flush();
		
		assertTrue(countryRepo.existsById(GERMANY));
		assertTrue(publisherRepo.existsById(RED_SEA));

		countryRepo.deleteById(GERMANY);
		publisherRepo.deleteById(RED_SEA);

		assertFalse(countryRepo.existsById(GERMANY));
		assertFalse(publisherRepo.existsById(RED_SEA));

	}
	
	/**
	 * Testing orphan removal with Country deletion
	 */
	@Test
	public void addPublisherDeleteCountry() {

		Publisher createdPublisher = new Publisher(RED_SEA_GERMANY);
		publisherRepo.save(createdPublisher);
		publisherRepo.flush();
		assertTrue(countryRepo.existsById(GERMANY));
		
		createdPublisher.setCountryName(null); // needs to explicitly set country to null before deleting it; 
		publisherRepo.save(createdPublisher);
		publisherRepo.flush();
		countryRepo.deleteById(GERMANY); 
		countryRepo.flush();
		assertFalse(countryRepo.existsById(GERMANY));
		
		publisherRepo.deleteById(RED_SEA_GERMANY.getPublisherName()); // deleting publisher explicitly
		assertFalse(countryRepo.existsById(GERMANY));
		assertFalse(publisherRepo.existsById(RED_SEA));

	}
	
	public boolean isDetachedCountry(Country country) { 
	    return country.getCountryName() != null  // must not be transient // wouldn't work with manual id;
	        && !em.contains(country)  // must not be managed now
	        && em.find(Country.class, country.getCountryName()) != null;  // must not have been removed
	}
}
