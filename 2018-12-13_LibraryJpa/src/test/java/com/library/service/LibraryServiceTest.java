package com.library.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.entity.Author;
import com.library.entity.AuthorId;
import com.library.entity.Book;
import com.library.entity.BookLogs;
import com.library.entity.Country;
import com.library.entity.Publisher;
import com.library.repo.IAuthorRepo;
import com.library.repo.IBookRepo;
import com.library.repo.ICountryRepo;
import com.library.repo.IPublisherRepo;
import com.library.service.ILibraryService;
import com.library.service.LibraryService;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class LibraryServiceTest {

	public LibraryServiceTest() {
	}

	static {
		TimeZone.setDefault(TimeZone.getTimeZone("IST"));
	}

	private Book book1;
	private Book book2;
	private Book book3;
	private Book book4;
	private List<Book> books;
	private Set<Author> authors;
	private Author author1;
	private Author author2;
	private Publisher publisher1Country1;
	private Publisher publisher2Country1;
	private Publisher publisher1Country2;
	private Publisher publisher2Country2;
	private Country country1;
	private Country country2;

	@TestConfiguration
	static class LibraryServiceTestContextConfig{
		@Bean
		public ILibraryService libraryService() {
			return new LibraryService();
		}
	}
	@MockBean
	IBookRepo bookRepo;
	@MockBean
	IAuthorRepo authorRepo;
	@MockBean
	ICountryRepo countryRepo;
	@MockBean
	IPublisherRepo publisherRepo;
	
	@Autowired
	private ILibraryService model;

	@Before
	public void setUp() {
		authors = new HashSet<>();
		author1 = new Author(new AuthorId("TestAuthor1", "Testovich1"));
		author2 = new Author(new AuthorId("TestAuthor2", "Tectovich2"));
		authors.add(author1);
		authors.add(author2);
		country1 = new Country("TestCountry");
		publisher1Country1 = new Publisher("TestPublisher", country1);
		publisher2Country1 = new Publisher("AnotherPublisher", country1);

		country2 = new Country("AnotherTestCountry");
		publisher1Country2 = new Publisher("TestPublisher", country2);
		publisher2Country2 = new Publisher("AnotherPublisher", country2);


        book1 = new Book(1l,authors,"TestTitle1",publisher1Country1,
                LocalDate.of(1900,1,31),10., new ArrayList<BookLogs>());

        book2 = new Book(2l,authors,"TestTitle2",publisher1Country1,
                LocalDate.of(1900,2,11),20., new ArrayList<BookLogs>());

        book3 = new Book(3l,authors,"TestTitle3",publisher1Country1,
                LocalDate.of(1900,3,15),30., new ArrayList<BookLogs>());

        book4 = new Book(4l,authors,"TestTitle4",publisher1Country1,
                LocalDate.of(1900,12,31),40., new ArrayList<BookLogs>());

		books = new ArrayList<>();
		books.add(book1);
		books.add(book2);
		books.add(book3);
		books.add(book4);
	}

	@After
	public void tearDown() {
		model.clearAll();
	}

	@Test
	public void getBook() {
		model.add(book1);
		assertTrue(model.containsBook(book1.getIsbn()));  // works only with eager fetch; 
		assertEquals(model.getBook(book1.getIsbn()), book1);
	}

	@Test
	public void add() {

		Mockito.when(bookRepo.existsById(book1.getIsbn())).thenReturn(false).thenReturn(true);	
		Mockito.when(bookRepo.save(book1)).thenReturn(book1);
		Mockito.when(bookRepo.findById(book1.getIsbn())).thenReturn(Optional.of(book1));
				
		assertTrue(model.add(book1));
		assertTrue(model.containsBook(book1.getIsbn()));
		assertFalse(model.add(book1));
	}
	
	
	@Test
	public void removePublisher() {
		assertTrue(model.add(book1));
		assertFalse(model.add(book1));
	}

	@Test
	public void clear() {
		model.add(book1);
		model.clearAll();
		assertTrue(model.add(book1));
	}
	
	@Test
	public void delete() {
		assertTrue(model.add(book1));
		Book deletedBook = model.delete(book1.getIsbn());
		assertEquals(book1, deletedBook);
	}

	@Test
	public void update() {

		assertTrue(model.add(book1));
		Book testBook = new Book(book1.getIsbn(), authors, book1.getTitle(), publisher1Country1, LocalDate.now(),
				100., new ArrayList<BookLogs>());
		assertNotEquals(book1, model.update(testBook));
		assertEquals(testBook, model.getBook(testBook.getIsbn()));

	}

	@Test
	public void updatePublisher() {

		assertTrue(model.add(book1));
		Book testBook = new Book(book1.getIsbn(), authors, book1.getTitle(), publisher2Country1, LocalDate.now(),
				book1.getPrice(), new ArrayList<BookLogs>());
		assertNotEquals(book1, model.update(testBook));
		assertEquals(testBook, model.getBook(testBook.getIsbn()));

	}

	@Test
	public void updateCountry() {

		assertTrue(model.add(book1));
		Book testBook = new Book(book1.getIsbn(), authors, book1.getTitle(), publisher1Country2, LocalDate.now(),
				book1.getPrice(), new ArrayList<BookLogs>());
		assertNotEquals(book1, model.update(testBook));
		assertEquals(testBook, model.getBook(testBook.getIsbn()));

	}

	@Test
	public void updatePublisherAndCountry() {

		assertTrue(model.add(book1));
		Book testBook = new Book(book1.getIsbn(), authors, book1.getTitle(), publisher2Country2, LocalDate.now(),
				book1.getPrice(), new ArrayList<BookLogs>());
		assertNotEquals(book1, model.update(testBook));
		assertEquals(testBook, model.getBook(testBook.getIsbn()));

	}

	@Test
	public void getAll() {
		assertTrue(model.addBooks(books).containsAll(books));
	}

}
