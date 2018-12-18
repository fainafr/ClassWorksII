package com.pwh.pg.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;

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
public class Bid {

	@Id
	Long id;

	@Positive
	BigDecimal value;

	@Future
	LocalDateTime time;

	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	Item item;

	@ManyToOne
	User user;


}
