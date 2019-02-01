package com.library.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
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
	}

	private static final long serialVersionUID = 1L;

	@Id
	Long isbn;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY) // works only with eager fetch; 
	@JsonManagedReference // subordinate in m2m relations
	Set<Author> authors = new HashSet<Author>();

	String title;
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	Publisher publisher;

	@Convert(converter = LocalDateConverter.class)
	LocalDate edition;

	Double price;

}
