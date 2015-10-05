package pep.mendez.smvcp1.spring.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pep.mendez.smvcp1.spring.formbeans.ProfileBean;

/*
 *  Custom Validator implementation that is specific to Spring Framework
 */
/**
 * @author pep
 *
 */
public class ProfileFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// make sure this Validator is only applied to ProfileBean objects
		return ProfileBean.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProfileBean profileBean = (ProfileBean) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
	}

}
