package edu.northeastern.cs5200.entities;

import java.util.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String label;
	
	@ManyToOne
	@JsonIgnore
	private Faculty faculty;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "course")
	private Set<Section> sections;

	public Course() {
		
	}
	public Course(String label) {
		this.label = label;
	}
	
	public Course(int id, String label) {
		this.id = id;
		this.label = label;
	}
	public Course(String label, Faculty faulty) {
		this.label = label;
		this.setFaculty(faulty);
	}
	public Course(int id, String label, Faculty faulty) {
		this.id = id;
		this.label = label;
		this.setFaculty(faulty);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public Set<Section> getSections() {
		return sections;
	}
	public void setSections(Set<Section> sections) {
		this.sections = sections;
	}
	
	public Faculty getFaculty() {
		return faculty;
	}
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
		if(faculty.getCourses() == null) {
			faculty.setCourses(new HashSet<>());
		}
		if(!faculty.getCourses().contains(this)) {
			faculty.getCourses().add(this);
		}
	}
}
