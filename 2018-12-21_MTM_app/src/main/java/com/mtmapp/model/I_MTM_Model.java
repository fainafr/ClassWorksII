package com.mtmapp.model;

import java.util.Set;

import com.mtmapp.entities.Author;
import com.mtmapp.entities.Book;

public interface I_MTM_Model {
	
	public boolean addAuthor(String name);
	
	public boolean addBook(String title);
	
	/**
	 * Postcondition: always saves this title / this name
	 * @param title
	 * @param name
	 * @return
	 */
	public boolean setAuthor(String title, String name);
	
	/**
	 * Postcondition: empty set if no book, or if a book has no authors;
	 */
	public Set<Author> getBookAuthors(String title);
	
	/**
	 * Postcondition: empty set if no book, or if a book has no authors;
	 */
	public Set<Book> getAuthorBooks(String name);
	
	public boolean deleteAuthor(String name);
	
	public boolean deleteBook(String title);
}
