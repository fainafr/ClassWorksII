package com.mtmapp.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter @Setter

@Entity
@Table(name="book_mtm")
public class Book {
	
	@Id
	@NotNull
	@Column(nullable = false, length = 100)
	private String title;
	
	
	// subordinate in m2m relations
	@ManyToMany (fetch = FetchType.EAGER)
	@JsonManagedReference
	private Set<Author> authors = new HashSet<Author>();


	@Override
	public String toString() {
		return "Book [title=" + title + "]";
	}


	public Book(@NotNull String title) {
		super();
		this.title = title;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}


	


}
