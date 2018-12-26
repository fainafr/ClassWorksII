package com.library.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.config.RandomConfig;
import com.library.entity.Author;
import com.library.entity.Book;
import com.library.entity.Publisher;
import com.library.repo.IAuthorRepo;
import com.library.repo.IBookRepo;
import com.library.repo.ICountryRepo;
import com.library.repo.IPublisherRepo;

//@Transactional do we have to have it on class level?
@Service
public class LibraryService implements ILibraryService {

	@Autowired
	IBookRepo bookRepo;
	@Autowired
	IAuthorRepo authorRepo;
	@Autowired
	ICountryRepo countryRepo;
	@Autowired
	IPublisherRepo publisherRepo;
	
	@Override
	public Book getBook(long isbn) {

		return bookRepo.findById(isbn).orElse(new Book());
		// https://stackoverflow.com/a/24486114
	
	}
	
	
	@Override
	@Transactional
	// order has a meaning. TODO: entities that are cascade saved(?)
	public boolean add(Book book) {

		if (bookRepo.existsById(book.getIsbn())) return false;
		countryRepo.save(book.getPublisher().getCountryName());
		publisherRepo.save(book.getPublisher());
		authorRepo.saveAll(book.getAuthors());
		bookRepo.save(book);
		return true;
	
	}
	
	@Override
	@Transactional
	public Book delete(long isbn) {
		
		if (!bookRepo.existsById(isbn)) {
			
			return new Book();
		}
		Book book = getBook(isbn);
		bookRepo.deleteById(isbn);
		return book;
		
	}
	
	@Override
	@Transactional
	public Book update(Book book) { // no difference at all in add and update 
		
		if (!bookRepo.existsById(book.getIsbn())) {
			add(book);
			return book;
		}
	    publisherRepo.save(book.getPublisher());
	 	countryRepo.save(book.getPublisher().getCountryName());
	
		 bookRepo.save(book); 
		 return getBook(book.getIsbn());
	
	}
	
	@Override
	public boolean addRandomBook() {	
		return add(RandomConfig.randomBook());
	}
	
	@Override
	public List<Book> addBooks(List<Book> books) {
		for(Book book : books){
			add(book);
		}
		return books;
	}
	
	@Override
	public List<Book> getAll() {
		return bookRepo.findAll();
	}
	
	@Override
	public List<Book> getAllBooksByPublisher(Publisher publisher) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Book> getAllBooksByAuthor(Author author) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Book> getAllBooksBetweenEdition(LocalDate from, LocalDate to) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Book> getAllBooksBetweenPrice(double from, double to) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Book> fillRepository(int amount) {
		List<Book> list = new ArrayList<Book>(amount);
		for (int i = 0; i < amount; i++){
			list.add(RandomConfig.randomBook());
		}
		addBooks(list);
		return list;
	}
	
	@Override
	public void deleteBooks(List<Book> books) {
		for(Book book : books) {
			delete(book.getIsbn());
		}
		
	}
	
	@Override
	public List<Publisher> getAllPublishersByCountry(String country) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearAll() {
		
		bookRepo.deleteAll();
		authorRepo.deleteAll();
		publisherRepo.deleteAll();
		countryRepo.deleteAll();
		
		
	}

}
