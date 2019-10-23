package edu.northeastern.cs5200.models;

public class Phone {
	private int id;
	private String phone;
	private int primary;
	private int person;
	
	
	public Phone(int id, String phone, int primary, int person) {
		super();
		this.id = id;
		this.phone = phone;
		this.primary = primary;
		this.person = person;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getPrimary() {
		return primary;
	}
	public void setPrimary(int primary) {
		this.primary = primary;
	}
	public int getPerson() {
		return person;
	}
	public void setPerson(int person) {
		this.person = person;
	}
	
	
}
