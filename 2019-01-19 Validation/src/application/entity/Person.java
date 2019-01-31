package application.entity;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import application.annotations.ConstraintDoubleRange;
import application.annotations.ConstraintMarried;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter @Setter

@ConstraintMarried(minAge = 18)
public class Person {
	
	@NotNull
	@Size (min=3, max=6, message="Person: too short or too long name")
	private String name;
	
	@PositiveOrZero(message="Person: negative age")
	@Max(value=100, message="Person: age too big")
	private int age;
	
	@ConstraintDoubleRange(min=50., max=100.)
	private double weight;
	
	private boolean married;

}
