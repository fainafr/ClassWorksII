package com.library.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@EqualsAndHashCode(exclude = "publishers")
@NoArgsConstructor 
@AllArgsConstructor
@ToString(exclude = "publishers")

@Entity
@Table(name = "COUNTRIES")
public class Country implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	@Column(length = 255)
	String countryName;
	
	@OneToMany(mappedBy = "countryName", cascade=CascadeType.ALL)
	Set<Publisher> publishers = new HashSet<Publisher>();
	
	public Country(@NotNull String countryName) {
		super();
		this.countryName = countryName;
	}

	public Country(Country country) {
		this.countryName = country.getCountryName();
		this.publishers = new HashSet<Publisher>(country.getPublishers());
	}
	
	
	
}
