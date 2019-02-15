package com.library.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "PUBLISHERS")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	String publisherName;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PUBLISHERCOUNTRY")
	@JsonManagedReference
	Team countryName;

	public Employee(@NotNull String publisherName) {
		super();
		this.publisherName = publisherName;
	}

	public Employee(Employee publisher) {
		this.publisherName = publisher.getPublisherName();
		this.countryName = new Team(publisher.getCountryName());
	}

}