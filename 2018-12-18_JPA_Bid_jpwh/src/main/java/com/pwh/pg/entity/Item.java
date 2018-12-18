package com.pwh.pg.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
public class Item {

	@Id
	@ToString.Exclude
	Long id;

	String description;

	@OneToMany(mappedBy = "item", fetch = FetchType.EAGER)
	Set<Bid> bids;
	
}
