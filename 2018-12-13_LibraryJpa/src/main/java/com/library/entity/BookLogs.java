package com.library.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class BookLogs {

	private LocalDate date;
	private LocalTime time;
	private UserActions action;
	private String description;

	public enum UserActions {
		STATUS_CHANGE, EMAIL_CHANGE, ADDRESS_CHANGE,
	}
}
