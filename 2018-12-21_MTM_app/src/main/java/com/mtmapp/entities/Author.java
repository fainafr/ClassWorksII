package com.mtmapp.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter

@Entity
@Table(name="author_mtm")
public class Author {
	
	@Id
	@NotNull
	@Column(nullable = false, length = 100)
	private String name;
	

	//host in m2m relations
	@ManyToMany(mappedBy="authors", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//@JoinTable(name="book_author", joinColumns=@JoinColumn(name="book_id"), inverseJoinColumns=@JoinColumn(name="author_id"))
	@JsonBackReference
	private Set<Book> books = new HashSet<Book>(); 



	public Author(@NotNull String name) {
		super();
		this.name = name;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Author other = (Author) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Author [name=" + name + "]";
	}
}
