package application.entities;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter @Setter

public class Car {
	
	private int id;
	private String model;
	private int doors;
	private double engine;
	private LocalDate production;
	private boolean ac;

}
