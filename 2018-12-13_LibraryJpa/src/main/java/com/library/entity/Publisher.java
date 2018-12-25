package com.library.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "publishers")
public class Publisher implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	String publisherName;
	
	//TODO: length constraint
	@OneToOne(orphanRemoval=true)
	Country countryName;
	
	
	public Publisher(String name) {
		this.publisherName = name;

	}

}
