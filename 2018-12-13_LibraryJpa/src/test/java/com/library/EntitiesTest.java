package com.library;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

import com.library.entity.Author;
import com.library.entity.AuthorId;
import com.library.entity.Book;
import com.library.entity.Country;
import com.library.entity.Publisher;
import com.library.repo.IAuthorRepo;
import com.library.repo.IBookRepo;
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
	private static final AuthorId JOYCE = new AuthorId("James", "Joyce");
	private static final Author[] authorArray = { new Author(JOYCE) };
	private static final Set<Author> AUTHORS = new HashSet<Author>(Arrays.asList(authorArray));
	private static final Publisher RED_SEA_GERMANY = new Publisher(RED_SEA, new Country(GERMANY));
	private static final Book ULYSSES = new Book(1l, AUTHORS, "ULYSSES", RED_SEA_GERMANY, LocalDate.of(1919, 3, 15),
			30.);
	
	
	@PersistenceContext // https://www.javabullets.com/access-entitymanager-spring-data-jpa/
	private EntityManager em;
	
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
		authorRepo.deleteAll();
		publisherRepo.deleteAll();
		countryRepo.deleteAll();

	}

	
	public void addBookDeleteAuthor() {
		//TODO: stub;
	}
	
	public void addBookDeletePublisher() {
		//TODO: stub;
	}
			
	

	
	/**
	 * Testing cascade add, without explicit saving;
	 */
	@Test
	public void addBookCascade() {

		Book createdBook = new Book(ULYSSES);
		bookRepo.save(createdBook);
		bookRepo.flush();

		Book persistedBook = bookRepo.findById(createdBook.getIsbn()).get();
		assertTrue(persistedBook.equals(createdBook));

	}
	
	/**
	 * Testing delete, without explicit removing; cascade works;
	 */
	@Test
	public void deleteBookCascade() {
		Book createdBook = new Book(ULYSSES);
	
		bookRepo.save(createdBook);
		bookRepo.flush();

		bookRepo.delete(createdBook);
		bookRepo.flush();

		assertFalse(bookRepo.existsById(createdBook.getIsbn()));
		assertFalse(authorRepo.existsById(JOYCE));
		assertFalse(countryRepo.existsById(GERMANY));
		assertFalse(publisherRepo.existsById(RED_SEA));
		
	}
	
	
	/**
	 * Testing add, with explicit saving
	 */
	@Test
	public void addBook() {
		Book createdBook = new Book(ULYSSES);
		
		for (Author author : createdBook.getAuthors()) {
			authorRepo.save(author);
		}
		authorRepo.flush();

		countryRepo.save(createdBook.getPublisher().getCountryName());
		countryRepo.flush();

		publisherRepo.save(createdBook.getPublisher());
		publisherRepo.flush();

		bookRepo.save(createdBook);
		bookRepo.flush();

		Book persistedBook = bookRepo.findById(createdBook.getIsbn()).get();
		System.out.println(persistedBook.toString());
		assertTrue(persistedBook.equals(createdBook));
	}
	
	/**
	 * Testing delete, with explicit removing;
	 */
	@Test
	public void deleteBook() {
		Book createdBook = new Book(ULYSSES);
		for (Author author : createdBook.getAuthors()) {
			authorRepo.save(author);
		}
		authorRepo.flush();

		countryRepo.save(createdBook.getPublisher().getCountryName());
		countryRepo.flush();

		publisherRepo.save(createdBook.getPublisher());
		publisherRepo.flush();

		bookRepo.save(createdBook);
		bookRepo.flush();

		bookRepo.delete(createdBook);
		bookRepo.flush();

		assertFalse(bookRepo.existsById(createdBook.getIsbn()));
	}
	
	/**
	 * Testing add, with explicit saving
	 */
	@Test
	public void addAuthor() {
		Author createdAuthor = new Author(JOYCE);

		authorRepo.save(createdAuthor);
		authorRepo.flush();

		Author persistedAuthor = authorRepo.findById(JOYCE).get();

		assertTrue(persistedAuthor.equals(createdAuthor));

	}

	/**
	 * Testing delete, with explicit removing;
	 */
	@Test
	public void removeAuthor() {

		Author createdAuthor = new Author(JOYCE);
		authorRepo.save(createdAuthor);
		authorRepo.flush();

		authorRepo.delete(createdAuthor);
		authorRepo.flush();
		assertFalse(authorRepo.existsById(JOYCE));

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
