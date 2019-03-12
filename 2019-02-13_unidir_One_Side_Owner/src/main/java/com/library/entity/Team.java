package com.library.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(exclude = "employees")
@NoArgsConstructor 
@AllArgsConstructor
@ToString(exclude = "employees")
@Entity
@Table(name = "TEAMS")
public class Team implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	@Column(length = 255)
	String name;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_team_name")
	@JsonBackReference
	Set<Employee> employees = new HashSet<Employee>();
	
}
