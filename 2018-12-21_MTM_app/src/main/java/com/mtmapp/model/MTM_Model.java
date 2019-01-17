package com.mtmapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtmapp.entities.Author;
import com.mtmapp.entities.Book;
import com.mtmapp.repo.IAuthorRepo;
import com.mtmapp.repo.IBookRepo;

@Service
public class MTM_Model implements I_MTM_Model {

	@Autowired
	IBookRepo bookRepo;

	@Autowired
	IAuthorRepo authorRepo;

	@Override
	public boolean addAuthor(@NotNull String name) {

		if (authorRepo.existsById(name)) {
			return false;
		}
		authorRepo.save(new Author(name));
		return true;
	}

	@Override
	public boolean addBook(@NotNull String title) {

		if (bookRepo.existsById(title)) {
			return false;
		}
		bookRepo.save(new Book(title));
		return true;
	}

	@Override
	public boolean setAuthor(@NotNull String title, @NotNull String name) {

		Author author = authorRepo.findById(name).orElse(new Author(title));
		Book book = bookRepo.findById(title).orElse(new Book(title));
		book.getAuthors().add(author);
		// implicitly hoping that books will add automatically //
		// author.getBooks().add(book);
		bookRepo.save(book);
		// implicitly hoping that authors will add automatically
		return true;

	}

	@Override
	public Set<Author> getBookAuthors(@NotNull String title) {
		Book book = bookRepo.findById(title).orElse(null);
		return book != null ? book.getAuthors() : new HashSet<Author>();
	}

	@Override
	public Set<Book> getAuthorBooks(@NotNull String name) {
		Author author = authorRepo.findById(name).orElse(null);
		return author != null ? author.getBooks() : null;
	}

	@Override
	public boolean deleteAuthor(@NotNull String name) {
		if (authorRepo.existsById(name)) {
			authorRepo.deleteById(name);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteBook(@NotNull String title) {
		if (bookRepo.existsById(title)) {
			bookRepo.deleteById(title);
			return true;
		} else {
			return false;
		}
	}

}
