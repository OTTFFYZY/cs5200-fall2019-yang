package edu.northeastern.cs5200.entities;

import java.util.*;
import javax.persistence.*;

@Entity
public class Faculty extends Person {
	@Column
	private String office;
	@Column
	private boolean tenured;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "faculty")
	private Set<Course> courses;
	
	public Faculty() {
		
	}
	public Faculty(String username, String password, String firstName, String lastName, String office, boolean tenured) {
		super(username, password, firstName, lastName);
		this.office = office;
		this.tenured = tenured;
	}
	
	public Faculty(int id, String username, String password, String firstName, String lastName, String office, boolean tenured) {
		super(id, username, password, firstName, lastName);
		this.office = office;
		this.tenured = tenured;
	}

	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public Boolean getTenured() {
		return tenured;
	}
	public void setTenured(Boolean tenured) {
		this.tenured = tenured;
	}
	
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
}
