package com.mtmapp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mtmapp.entities.Author;
import com.mtmapp.entities.Book;
import com.mtmapp.model.I_MTM_Model;

@RunWith(SpringRunner.class)
@SpringBootTest
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

	@Autowired
	private I_MTM_Model model;

	@Before
	public void setUp() {
		
		
		author1 = new Author("TestName1");
		author2 = new Author("TestName2");
		authors = new HashSet<>();
		authors.add(author1);
		authors.add(author2);

		book1 = new Book("TestTitle1");
		book2 = new Book("TestTitle2");
		book3 = new Book("TestTitle3");
		book4 = new Book("TestTitle4");
		books = new ArrayList<>();
		books.add(book1);
		books.add(book2);
		books.add(book3);
		books.add(book4);

	}

	@After
	public void tearDown() {
		model.deleteBook(book1.getTitle());
		model.deleteAuthor(author1.getName());
	}

	@Test
	public void addBook() {
		assertTrue(model.addBook(book1.getTitle()));
		assertFalse(model.addBook(book1.getTitle()));
	}

	@Test
	public void addAuthor() {
		System.out.println("AUTHOR1" + author1);
		
		
		assertTrue(model.addAuthor(author1.getName()));
		assertFalse(model.addAuthor(author1.getName()));
	}

	@Test
	public void delete() {
		model.addBook(book1.getTitle());
		assertTrue(model.deleteBook(book1.getTitle()));
	}

	@Test
	public void update() {

		model.addBook(book1.getTitle());
		model.addAuthor(author1.getName());
		model.setAuthor(book1.getTitle(), author1.getName());
		
		Set<Book> books = model.getAuthorBooks(author1.getName());
		Set<Author> authors = model.getBookAuthors(book1.getTitle());		
				
		assertTrue(books.contains(book1));
		assertTrue(authors.contains(author1));
		
		

	}

}
