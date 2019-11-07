package edu.northeastern.cs5200.entities;

import java.util.*;
import javax.persistence.*;

@Entity
public class Student extends Person {
	@Column(name = "grad_year")
	private int gradYear;
	@Column
	private long scholarship;

	@OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
	private Set<Enrollment> enrollments;
	
	public Student() {
		
	}
	public Student(String username, String password, 
			String firstName, String lastName, int gradYear,long scholarship) {
		super(username, password, firstName, lastName);
		this.gradYear = gradYear;
		this.scholarship = scholarship;
	}
	
	public Student(int id, String username, String password, 
			String firstName, String lastName, int gradYear,long scholarship) {
		super(id, username, password, firstName, lastName);
		this.gradYear = gradYear;
		this.scholarship = scholarship;
	}
	
	public int getGradYear() {
		return gradYear;
	}
	public void setGradYear(int gradYear) {
		this.gradYear = gradYear;
	}
	public long getScholarship() {
		return scholarship;
	}
	public void setScholarship(long scholarship) {
		this.scholarship = scholarship;
	}
	
	public Set<Enrollment> getEnrollments() {
		return enrollments;
	}
	public void setEnrollments(Set<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}
}