package com.library.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.IdClass;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@EqualsAndHashCode(of = "id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(AuthorID.class)
@Entity
@Table(name = "authors")
public class Author implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	AuthorID id;
	
	String name;
	
	@ManyToMany(mappedBy="authors", fetch = FetchType.LAZY)
	Set<Book> books = new HashSet<Book>();
	
	public Author(AuthorID id) {
		this.id = id;
	}
	
}
