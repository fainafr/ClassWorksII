package com.library.service;

import java.time.LocalDate;
import java.util.List;

import com.library.entity.Author;
import com.library.entity.Book;
import com.library.entity.Publisher;

public interface ILibraryService {

	    public Book getBook(long isbn);

	    public boolean add(Book book);

	    public Book delete(long isbn);

	    public Book update(Book book);

	    public boolean addRandomBook();

	    public List <Book> addBooks(List <Book> books);

	    public  List <Book> getAll();

	    public List <Book> getAllBooksByPublisher(Publisher publisher);

	    public List <Book> getAllBooksByAuthor(Author author);

	    public List <Book> getAllBooksBetweenEdition(LocalDate from, LocalDate to);

	    public List <Book> getAllBooksBetweenPrice(double from, double to);

	    public List <Book> fillRepository(int amount);

	    public  void deleteBooks(List <Book> books);

	    public List <Publisher> getAllPublishersByCountry(String country);

}
