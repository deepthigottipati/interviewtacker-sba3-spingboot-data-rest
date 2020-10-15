package com.springboot.its.entity;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about the user.")
@Entity
@Table(name="user")
public class User {

	// define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	
	@Column(name="first_name")
	@Size(min=5,max=30, message="Name should have atleast 5 characters and max of 30")
	@ApiModelProperty(notes="Name should have atleast 5 characters and max of 30")
	private String firstName;
	
	
	@Column(name="last_name")
	@Size(min=3,max=25, message="Name should have atleast 3 characters and max of 25")
	@ApiModelProperty(notes="Name should have atleast 3 characters and max of 25")
	private String lastName;
	
	@Column(name="email")
	@NotBlank()
	private String email;
	
	@Column(name="mobile")
	@NotBlank()
	@Size(min=10,max=10, message="Number should be 10 digits")
	@ApiModelProperty(notes="Name should be 10 digits")	
	private BigInteger mobile;	
	// define constructors
	
	@OneToMany(mappedBy="user")
	private List<Interview> interviews;
	
	public User() {
		
	}
	
	public User(int id, String firstName, String lastName, String email,BigInteger mobile) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile=mobile;
	}


	public User(String firstName, String lastName, String email,BigInteger mobile) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile=mobile;
	}

	// define getter/setter
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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


	public BigInteger getMobile() {
		return mobile;
	}

	public void setMobile(BigInteger mobile) {
		this.mobile = mobile;
	}


	public List<Interview> getInterviews() {
		return interviews;
	}

	public void setInterviews(List<Interview> interviews) {
		this.interviews = interviews;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ",mobile=" + mobile +"]";
	}
		
}











