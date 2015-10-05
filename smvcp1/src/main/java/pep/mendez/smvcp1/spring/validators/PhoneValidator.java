package pep.mendez.smvcp1.spring.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/*
 * JSR 303 custom validator implementation
 */
/**
 * @author pep
 *
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {

	@Override
	public void initialize(Phone phone) {
	}

	@Override
	public boolean isValid(String phoneNumber,
			ConstraintValidatorContext context) {
		if (phoneNumber == null) {
			return false;
		}
		// one or more digits
		if (phoneNumber.matches("\\d+")) {
			return true;
		}
		// return false if nothing matches the input
//		context.disableDefaultConstraintViolation();
//		context.buildConstraintViolationWithTemplate("\\d+")
//				.addConstraintViolation();
		return false;
	}

}