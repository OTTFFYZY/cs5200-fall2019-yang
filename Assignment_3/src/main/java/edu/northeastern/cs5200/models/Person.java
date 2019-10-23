/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.northeastern.cs5200.models;

 
import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author Zhenyu Yang
 */
public class Person {
    private int id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private Date dob;
    private ArrayList<Address> addresses;
    private ArrayList<Phone> phones;
    
    
    public Person(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Person(int id, String firstname, String lastname, String username, String password, String email, Date dob) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dob = dob;
    }

    public Person(int id, String firstname, String lastname, String username, String password, String email, Date dob, ArrayList<Address> addresses2, ArrayList<Phone> phones2) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dob = dob;
        this.addresses = addresses2;
        this.phones = phones2;
    }

   
    
    public ArrayList<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(ArrayList<Address> addresses) {
		this.addresses = addresses;
	}

	public ArrayList<Phone> getPhones() {
		return phones;
	}

	public void setPhones(ArrayList<Phone> phones) {
		this.phones = phones;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
    
    
}
