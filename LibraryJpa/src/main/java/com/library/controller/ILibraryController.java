package com.library.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.entity.Book;
import com.library.entity.Publisher;

public interface ILibraryController {

	boolean addRandomBook();

	Book getBook(long id);

	boolean addBook(Book book);

	Book deleteBook(long id);

	Book updateBook(Book book);

	List<Book> getAll();

	List<Book> getAllBooksByPublisher(String publisherName);

	List<Book> getAllBooksByAuthor(String firstName, String lastName);

	List<Book> getAllBooksBetweenEditon(LocalDate from, LocalDate to);

	List<Book> getAllBooksBetweenPrice(double from, double to);

	List<Book> fillRepository(int amount);

	List<Publisher> getAllPublishersByCountry(String country);

}