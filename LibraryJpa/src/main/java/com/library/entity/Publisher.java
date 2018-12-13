package com.library.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "publishers")
public class Publisher implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	String publisherName;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	Country countryName;

	@OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	Set<Book> books;

	public Publisher(String name) {
		this.publisherName = name;

	}

	public Publisher(String name, Country countryName) {
		this.publisherName = name;
		this.countryName = countryName;

	}
}