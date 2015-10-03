package pep.mendez.smvcp1.spring.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/*
 * JSR 303 custom validator implementation
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
		// validate phone numbers of format "1234567890"
		if (phoneNumber.matches("\\d+")) {
			return true;
		}
		// return false if nothing matches the input
		context.buildConstraintViolationWithTemplate("El formato del numero de telefono no es correcto.")
				.addConstraintViolation();
		return false;
	}

}