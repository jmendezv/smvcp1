package pep.mendez.smvcp1.spring.formbeans;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import pep.mendez.smvcp1.spring.validators.Phone;

public class ProfileBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// @DateTimeFormat(pattern="MM/dd/yyyy") @NotNull @Past
	// ignores leading and trailing blanks
	@NotBlank
	private String name;
	@NotBlank
	private String city;
	@NotBlank
	private String profession;
	@Phone
	private String phone;

	public ProfileBean() {
	}

	public ProfileBean(String name, String city, String profession, String phone) {
		this.name = name;
		this.city = city;
		this.profession = profession;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
