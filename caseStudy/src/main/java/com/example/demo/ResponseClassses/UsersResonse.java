package com.example.demo.ResponseClassses;

public class UsersResonse {
	private long userID;
	private String name;
	private String email;
	private String phone;
	private Address address;
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public UsersResonse(long userID, String name, String email, String phone, Address address) {
		super();
		this.userID = userID;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}
	public UsersResonse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
