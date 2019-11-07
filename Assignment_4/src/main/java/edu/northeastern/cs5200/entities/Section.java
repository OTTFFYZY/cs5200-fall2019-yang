package edu.northeastern.cs5200.entities;

import java.util.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Section {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private int seats;
	@Column
	private String title;
	
	@ManyToOne
	@JsonIgnore
	private Course course;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="section")
	private Set<Enrollment> enrollments;
	
	public Section() {
		
	}
	public Section(int seats, String title, Course course) {
		this.seats = seats;
		this.title = title;
		this.setCourse(course);
	}
	
	public Section(int id, int seats, String title, Course course) {
		this.id = id;
		this.seats = seats;
		this.setCourse(course);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
		if(course.getSections() == null) {
			course.setSections(new HashSet<>());
		}
		if(!course.getSections().contains(this)) {
			course.getSections().add(this);
		}
	}

	public Set<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(Set<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}
}
