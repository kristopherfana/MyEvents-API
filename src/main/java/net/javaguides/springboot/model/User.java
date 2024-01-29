package net.javaguides.springboot.model;

import com.fasterxml.jackson.annotation.*;
import net.javaguides.springboot.web.dto.UserDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table(name =  "user", uniqueConstraints =
@UniqueConstraint(columnNames = "email"))
public class User {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	public Long id;
	@Column(name = "first_name")
	public String firstName;
	@Column(name = "last_name")
	public String lastName;
	public String email;
	public String password;
	@Column(name="phone_number")
	public String phoneNumber;

	@JsonBackReference
	@OneToMany(mappedBy = "creator",cascade = CascadeType.ALL)
	public List<Event> event;

	@JsonBackReference
	@ManyToMany(mappedBy = "guests")
	public List<Event> eventInvitedTo;

	public User() {}
	
	public User(String firstName, String lastName, String email, String password, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber=phoneNumber;
	}

	public User(Long id, String firstName, String lastName, String email, String password, String phoneNumber) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

	public User(String firstName, String lastName, String email, String password, String phoneNumber, List<Event> event, List<Event> eventInvitedTo) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.event = event;
		this.eventInvitedTo = eventInvitedTo;
	}

	public User(Long id, String firstName, String lastName, String email, String password, String phoneNumber, List<Event> event, List<Event> eventInvitedTo) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.event = event;
		this.eventInvitedTo = eventInvitedTo;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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

	public List<Event> getEvent() {
		return event;
	}

	public void setEvent(List<Event> event) {
		this.event = event;
	}

	public List<Event> getEventInvitedTo() {
		return eventInvitedTo;
	}

	public void setEventInvitedTo(List<Event> eventInvitedTo) {
		this.eventInvitedTo = eventInvitedTo;
	}
}
