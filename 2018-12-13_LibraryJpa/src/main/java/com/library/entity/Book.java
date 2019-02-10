package com.library.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.library.util.date.LocalDateConverter;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "BOOKS_MTM")
public class Book implements Serializable {

	/**
	 * Constructor for defensive copying 
	 */
	// TODO: check if new Long and new Double are acceptable idioms; 
	public Book(Book book) {
		this.isbn = new Long(book.getIsbn());
		this.authors = new HashSet<Author>(book.getAuthors());
		this.title = book.getTitle();
		this.publisher = new Publisher(book.getPublisher());
		this.edition = book.getEdition();
		this.price = new Double(book.getPrice());
		this.logs = new ArrayList<BookLogs>();
	}

	private static final long serialVersionUID = 1L;

	@Id
	Long isbn;

	/*
	 * Can't cascade.all because in m2m cascading delete book will get other books orphaned, throwing an exception. 
	 */
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JsonManagedReference
	Set<Author> authors = new HashSet<Author>();

	String title;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL) // can't serialize publisher with lazy fetch;
	@JsonManagedReference
	Publisher publisher;

	@Convert(converter = LocalDateConverter.class)
	LocalDate edition;

	Double price;
	
	@EqualsAndHashCode.Exclude
	@ElementCollection
	@CollectionTable
	private List<BookLogs> logs; 

}
