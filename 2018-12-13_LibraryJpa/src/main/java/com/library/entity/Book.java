package com.library.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	Publisher publisher;
	

	@Convert(converter = LocalDateConverter.class)
	LocalDate edition;

	Double price;

}
