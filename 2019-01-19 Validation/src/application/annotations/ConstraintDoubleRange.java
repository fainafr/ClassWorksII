package application.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import application.validators.DoubleRangeValidator;

@Constraint(validatedBy = DoubleRangeValidator.class)
@Target({METHOD, FIELD})
@Retention(RUNTIME)

public @interface ConstraintDoubleRange {
	
	double min() default 0.;
	double max() default Double.MAX_VALUE;
	String message() default "DoubleRange constraint message";
	
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
	
}
