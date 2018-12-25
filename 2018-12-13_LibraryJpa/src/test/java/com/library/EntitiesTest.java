package com.library;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.entity.Country;
import com.library.entity.Publisher;
import com.library.repo.IAuthorRepo;
import com.library.repo.IBookRepo;
import com.library.repo.ICountryRepo;
import com.library.repo.IPublisherRepo;

import lombok.NoArgsConstructor;

/**
 * Testing creation and deletion of entities to ensure relations work as intended;
 * 
 */
//TODO: migrate to H2 database;
@NoArgsConstructor
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Commit
public class EntitiesTest {
	
	//TODO: create-delete author and book please

	private static final String GERMANY = "Germany";	
	private static final String RED_SEA = "Red Sea";
	
	@Autowired
	IBookRepo bookRepo;
	@Autowired
	IAuthorRepo authorRepo;
	@Autowired
	ICountryRepo countryRepo;
	@Autowired
	IPublisherRepo publisherRepo;
	
	@Before
	public void clear() {
	
		bookRepo.deleteAll();
		publisherRepo.deleteAll();	
		countryRepo.deleteAll();
		
	}
	
	@Test
	public void addPublisher() {
		
		Publisher created = new Publisher(RED_SEA);

		publisherRepo.save(created);
		publisherRepo.flush();
	
		Publisher persisted = publisherRepo.findById(RED_SEA).get();
	
		assertTrue(created.getPublisherName().equals(persisted.getPublisherName()));

	}
	

	@Test
	public void addPublisherCountry() {
		
		Publisher newPublisher = new Publisher(RED_SEA);
		
		Country newCountry = new Country(GERMANY);
		
		countryRepo.save(newCountry);
		countryRepo.flush();
		
		newPublisher.setCountryName(countryRepo.findById(GERMANY).get());
		
		publisherRepo.save(newPublisher);		
		publisherRepo.flush();
		
		Publisher persistedPublisher = publisherRepo.findById(RED_SEA).get();

		assertTrue(newPublisher.equals(persistedPublisher));
	}
	
	@Test
	public void removePublisher() {
		
		Publisher created = new Publisher(RED_SEA);
		publisherRepo.save(created);
		publisherRepo.flush();
		
		publisherRepo.delete(created);
		publisherRepo.flush();
		assertFalse(publisherRepo.existsById(RED_SEA));
		
	}

	@Test
	public void removeCountry() {
		
		Country created = new Country(GERMANY);
		countryRepo.save(created);
		countryRepo.flush();
		
		countryRepo.delete(created);
		countryRepo.flush();
		assertFalse(countryRepo.existsById(GERMANY));
	
	}
	
	@Test
	public void removePublisherCountry() {

		Publisher createdPublisher = new Publisher(RED_SEA);
		Country createdCountry = new Country(GERMANY);
		
		countryRepo.save(createdCountry);
		countryRepo.flush();
		
		createdPublisher.setCountryName(createdCountry);
		publisherRepo.save(createdPublisher);
		publisherRepo.flush();
		
		countryRepo.deleteById(GERMANY);
		publisherRepo.deleteById(RED_SEA);
		
		assertFalse(countryRepo.existsById(GERMANY));
		assertFalse(publisherRepo.existsById(RED_SEA));
		
	}
}
