package com.library.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="books")
public class Book implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	long isbn;
//	
//	@ManyToMany()
//	@JoinTable(name = "book_author", 
//		joinColumns = {@JoinColumn(name = "isbn")}, 
//		inverseJoinColumns = {@JoinColumn(name="firstName"), @JoinColumn(name="lastName")})
//	Set<Author> authors = new HashSet<Author>();
//	
	String title;
//		
//	@ManyToOne(fetch = FetchType.LAZY)
//	Publisher publisher;
	
	LocalDate edition;

	Double price;

}
