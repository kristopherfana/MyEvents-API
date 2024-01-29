package net.javaguides.springboot.web.dto;

public class UserDto {

	private String firstName;

	private String lastName;
	private String email;

	private String password;

	private String phoneNumber;

	public UserDto(){}
	public UserDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public UserDto(String firstName, String lastName, String email,
				   String phoneNumber, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber=phoneNumber;
		this.password=password;
	}
	public UserDto(String firstName, String lastName, String email,
				   String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber=phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
