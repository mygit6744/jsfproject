package com.yugandhar.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ManagedBean(name = "user", eager = true)
@RequestScoped
public class User {
	@NotNull(message = "Name can't be empty")
	String name;
	@Min(18)
	int age;
	@NotNull(message = "Mobile can't be empty")
	@Size(min = 10, max = 10, message = "Mobile must have 10 digits")
	String mobile;
	@NotNull(message = "Name can't be empty")
	String password;
	@NotNull(message = "Name can't be empty")
	String address;
	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String login() {
		return "response";

	}
}