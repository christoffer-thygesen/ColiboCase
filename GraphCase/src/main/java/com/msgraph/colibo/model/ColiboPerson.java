package com.msgraph.colibo.model;

import java.util.ArrayList;
import java.util.List;

public class ColiboPerson {
	//id til db
	private Integer id;
	private String employeeId;
	private String address;
	private String city;
	private String name;
	private String title;
	private List<String> mobile = new ArrayList<String>();
	private List<String> email = new ArrayList<String>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getMobile() {
		return mobile;
	}
	public void setMobile(List<String> mobile) {
		this.mobile = mobile;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getEmail() {
		return email;
	}
	public void setEmail(List<String> email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "ColiboPerson [id=" + id + ", employeeId=" + employeeId + ", address=" + address + ", city=" + city
				+ ", name=" + name + ", title=" + title + ", mobile=" + mobile + ", email=" + email + "]";
	}
}
