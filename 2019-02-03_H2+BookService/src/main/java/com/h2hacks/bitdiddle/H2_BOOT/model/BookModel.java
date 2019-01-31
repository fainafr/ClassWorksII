package com.h2hacks.bitdiddle.H2_BOOT.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2hacks.bitdiddle.H2_BOOT.entity.Book;
import com.h2hacks.bitdiddle.H2_BOOT.repository.BookRepository;

@Service
public class BookModel {

	@Autowired
	private BookRepository repo;
	
	public void add(int id, String title) {
		Book book = new Book(id, title);
		repo.save(book);
	}
	
	public List<Book> getAll(){
		return repo.findAll();
	}
	
}
