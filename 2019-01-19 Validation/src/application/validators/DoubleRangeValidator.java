package application.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import application.annotations.ConstraintDoubleRange;

public class DoubleRangeValidator implements ConstraintValidator<ConstraintDoubleRange, Double>{

	private double min;
	private double max;
	
	@Override
	public boolean isValid(Double value, ConstraintValidatorContext context) {
		return value >= min && value < max;
	}

	@Override
	public void initialize(ConstraintDoubleRange constraintAnnotation) {
		this.min = constraintAnnotation.min();
		this.max = constraintAnnotation.max();
	}

}
