package com.library;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.entity.Author;
import com.library.entity.AuthorId;
import com.library.entity.Book;
import com.library.entity.Country;
import com.library.entity.Publisher;
import com.library.service.ILibraryService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryServiceTest {

	public LibraryServiceTest() {}

    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;
    private List <Book> books;
    private Set<Author> authors;
    private Author author1;
    private Author author2;
    private Publisher publisher;
    private Country country;

    @Autowired
    private ILibraryService model;


    @Before //BeforeEach sometimes does not work
    public void setUp() {
        authors = new HashSet <>();
        author1 = new Author(new AuthorId("TestAuthor1","Testovich1"));
        author2 = new Author(new AuthorId("TestAuthor2","Tectovich2"));
        authors.add(author1);
        authors.add(author2);
        country = new Country("TestCountry");
        publisher = new Publisher("TestPublisher",country);
        
      book1 = new Book(1l,"TestTitle1",
              LocalDate.of(1900,1,31),10.);

      book2 = new Book(2l,"TestTitle2",
              LocalDate.of(1900,2,11),20.);

      book3 = new Book(3l,"TestTitle3",
              LocalDate.of(1900,3,15),30.);

      book4 = new Book(4l,"TestTitle4",
              LocalDate.of(1900,12,31),40.);
      
//        book1 = new Book(1l,authors,"TestTitle1",publisher,
//                LocalDate.of(1900,1,31),10.);
//
//        book2 = new Book(2l,authors,"TestTitle2",publisher,
//                LocalDate.of(1900,2,11),20.);
//
//        book3 = new Book(3l,authors,"TestTitle3",publisher,
//                LocalDate.of(1900,3,15),30.);
//
//        book4 = new Book(4l,authors,"TestTitle4",publisher,
//                LocalDate.of(1900,12,31),40.);

        books = new ArrayList <>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        
        model.clear();
    }

    @AfterEach
    public void tearDown() {
        model.deleteBooks(books);
    }

    @Test
    public void getBook() {

    }

    @Test
    public void add() {
        assertTrue(model.add(book1));
        assertFalse(model.add(book1));
    }


	@Test
	public void delete() {
        assertTrue(model.add(book1));
        assertEquals(book1, model.delete(book1.getIsbn()));
    }

    @Test
    public void update() {
    	
        assertTrue(model.add(book1));
        Book testBook = new Book(book1.getIsbn(),book1.getTitle(),
                LocalDate.now(), 100.);
     //   Book testBook = new Book(book1.getIsbn(),book1.getAuthors(),book1.getTitle(),book1.getPublisher(), LocalDate.now(), 100.);
        assertNotEquals(book1, model.update(testBook));
        
    }

    @Test
    public void getAll() {
        assertTrue(model.addBooks(books).containsAll(books));
    }

//    @Test
//    public void getAllBooksByPublisher() {
//        model.addBooks(books);
//        assertEquals(4, model.getAllBooksByPublisher(publisher).size());
//    }
//
//    @Test
//    public void getAllBooksByAuthor() {
//        model.addBooks(books);
//        assertEquals(4, model.getAllBooksByAuthor(author1).size());
//    }
//
//    @Test
//    public void getAllBooksBetweenEdition() {
//        model.addBooks(books);
//        assertEquals(3, model.getAllBooksBetweenEdition(LocalDate.of(1900, 1, 1),
//                LocalDate.of(1900, 11, 11)).size());
//
//    }
//
//    @Test
//    public void getAllBooksBetweenPrice() {
//        model.addBooks(books);
//        assertEquals(3, model.getAllBooksBetweenPrice(9., 31.).size());
//    }
    
}
