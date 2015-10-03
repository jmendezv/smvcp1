package pep.mendez.smvcp1.formbeans;

import pep.mendez.smvcp1.spring.validators.Phone;

public class ProfileBean {

	// @DateTimeFormat(pattern="MM/dd/yyyy") @NotNull @Past
	private String name;
	private String city;
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
