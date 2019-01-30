package application.entities;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

@Document(collection="car_sb")
public class Car {
	
	@Id
	private int id;
	private String model;
	private int doors;
	private double engine;
	private LocalDate production;
	private boolean ac;

}
