package application.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import application.annotations.ConstraintMarried;
import application.entity.Person;

public class MarriedValidator implements ConstraintValidator<ConstraintMarried, Person>{

	private int minAge;
	
	@Override
	public boolean isValid(Person person, ConstraintValidatorContext context) {
		return person.getAge() >= minAge || !person.isMarried();
	}

	@Override
	public void initialize(ConstraintMarried annotation) {
		this.minAge = annotation.minAge();
	}

}
