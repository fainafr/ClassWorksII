package application;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import application.entity.Person;

public class ValidationApp {

	public static void main(String[] args) {
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		Person p = new Person("Sasha", 13, 64.8, true);
		
		Set<ConstraintViolation<Person>> violations = validator.validate(p);
		
		for(ConstraintViolation<Person> violation : violations) System.out.println(violation.getMessage()+" "+violation.getInvalidValue());
		for(ConstraintViolation<Person> violation : violations) System.out.println(violation);
		
		System.out.println(p);

	}

}
