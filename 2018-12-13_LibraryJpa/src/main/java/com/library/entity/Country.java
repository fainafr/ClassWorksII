package com.library.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
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

//@Entity
//@Table(name = "countries")
public class Country implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	String countryName;
	
}
