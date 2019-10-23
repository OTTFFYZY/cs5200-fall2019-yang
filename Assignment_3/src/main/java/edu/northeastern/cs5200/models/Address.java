/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.northeastern.cs5200.models;

/**
 *
 * @author Zhenyu Yang
 */
public class Address {
	private int id;
	private String street1;
	private String street2;
	private String city;
	private String state;
	private String zip;
	private int primary;
	private int person;
	
	
	public Address(int id, String street1, String street2, String city, String state, String zip, int primary,
			int person) {
		super();
		this.id = id;
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.primary = primary;
		this.person = person;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStreet1() {
		return street1;
	}
	public void setStreet1(String street1) {
		this.street1 = street1;
	}
	public String getStreet2() {
		return street2;
	}
	public void setStreet2(String street2) {
		this.street2 = street2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
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