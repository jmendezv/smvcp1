package pep.mendez.smvcp1.spring.formbeans;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class ResetPwdBean {

	@Email
	@NotBlank
	private String userName;

	public ResetPwdBean() {
	}

	public ResetPwdBean(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return String.format("UserRegistrationBean [userName=%s]", userName);
	}

}
