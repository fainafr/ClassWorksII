package com.library.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@EqualsAndHashCode(of = "id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "id")

@Entity
@Table(name = "AUTHORS_MTM")
public class Author implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@NotNull
	AuthorId id;
	
	@ManyToMany(mappedBy="authors", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JsonBackReference
	Set<Book> books = new HashSet<Book>();
	
	public Author(@NotNull AuthorId id) {
		this.id = id;
	}
	
}
