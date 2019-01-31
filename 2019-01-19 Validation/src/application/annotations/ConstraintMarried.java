package application.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import application.validators.MarriedValidator;

@Constraint(validatedBy = MarriedValidator.class)
@Target(TYPE)
@Retention(RUNTIME)
public @interface ConstraintMarried {
	
	int minAge() default 14;
	String message() default "Person: too young to be married";
	
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
