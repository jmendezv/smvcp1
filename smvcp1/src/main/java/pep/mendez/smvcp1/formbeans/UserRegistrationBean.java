package pep.mendez.smvcp1.formbeans;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.ScriptAssert;

/*
 * ScriptAssert is a class-level constraint, that evaluates a script expression against the annotated element.
 * This constraint can be used to implement validation routines, that depend on multiple attributes of the annotated element.
 * The script must return a boolean object.
 */

@ScriptAssert(lang = "javascript", script = "_this.password.equals(_this.passwordConfirmation)", alias = "_this", message = "register.dontmatch")

public class UserRegistrationBean {

	@NotNull
	@NotEmpty
	@Email
	private String userName;
	@NotNull
	@NotEmpty
	@Size(min = 6, max = 8)
	private String password;
	@NotNull
	@NotEmpty
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
