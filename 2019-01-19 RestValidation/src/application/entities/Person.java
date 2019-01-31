package application.entities;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter @Setter

public class Person {
	
	@NotNull
	@Size (min=3, max=6, message="Person: name is too short or too long")
	private String name;
	
	@PositiveOrZero(message="Person: negative age")
	@Max(value=100, message="Person: age is too big")
	private int age;
	
	private double weight;
	private boolean married;

}
