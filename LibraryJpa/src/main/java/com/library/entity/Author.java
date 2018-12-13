package com.library.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Entity
@Table(name = "authors")
public class Author implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	AuthorId id;
	
	@ManyToMany(mappedBy="authors", fetch = FetchType.LAZY)
	Set<Book> books = new HashSet<Book>();
	
	public Author(AuthorId id) {
		this.id = id;
	}
	
}