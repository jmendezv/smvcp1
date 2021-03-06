package pep.mendez.smvcp1.spring.formbeans;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.ScriptAssert;

/*
 * ScriptAssert is a class-level constraint, that evaluates a script expression against the annotated element.
 * This constraint can be used to implement validation routines, that depend on multiple attributes of the annotated element.
 * The script must return a boolean object.
 * 
 * message defaults to {org.hibernate.validator.constraints.ScriptAssert.message}
 * 
 * , message = "{register.passwordsdontmatch}"
 * 
 */

/**
 * @author pep
 *
 */
@ScriptAssert(lang = "javascript", script = "_this.password.equals(_this.passwordConfirmation)", alias = "_this")

public class UserRegistrationBean {

	@Email
	@NotBlank
	private String userName;
	@Size(min = 6, max = 8)
	private String password;
	@Size(min = 6, max = 8)
	private String passwordConfirmation;

	public UserRegistrationBean() {
	}

	public UserRegistrationBean(String userName, String password,
			String passwordConfirmation) {

		this.userName = userName;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	@Override
	public String toString() {
		return String
				.format("UserRegistrationBean [userName=%s, password=%s, passwordConfirmation=%s]",
						userName, password, passwordConfirmation);
	}
	
}
