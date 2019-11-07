package edu.northeastern.cs5200.entities;

import java.util.HashSet;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Enrollment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private int grade;
	@Column
	private String feedback;
	
	@ManyToOne(targetEntity = Student.class)
	@JsonIgnore
	private Student student;
	
	@ManyToOne(targetEntity = Section.class)
	@JsonIgnore
	private Section section;
	
	public Enrollment() {
		
	}
	public Enrollment(Student student, Section section) {
		this.setStudent(student);
		this.setSection(section);
	}
	public Enrollment(int grade, String feedback, Student student, Section section) {
		this.grade = grade;
		this.feedback = feedback;
		this.setStudent(student);
		this.setSection(section);
	}
	public Enrollment(int id, int grade, String feedback, Student student, Section section) {
		this.id = id;
		this.grade = grade;
		this.feedback = feedback;
		this.setStudent(student);
		this.setSection(section);
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
		if(student.getEnrollments() == null)
		{
			student.setEnrollments(new HashSet<>());
		}
		if(!student.getEnrollments().contains(this)) {
			student.getEnrollments().add(this);
		}
	}
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
		if(section.getEnrollments() == null)
		{
			section.setEnrollments(new HashSet<>());
		}
		if(!section.getEnrollments().contains(this)) {
			section.getEnrollments().add(this);
		}
	}
}
