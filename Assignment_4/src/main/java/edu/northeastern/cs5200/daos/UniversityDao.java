package edu.northeastern.cs5200.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs5200.repositories.*;
import edu.northeastern.cs5200.entities.*;
import java.util.*;

@Repository
public class UniversityDao {
	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private EnrollmentRepo enrollmentRepo;
	
	@Autowired
	private SectionRepo sectionRepo;
	
	@Autowired
	private FacultyRepo facultyRepo;
	
	@Autowired
	private PersonRepo personRepo;
	
	public void truncateDatabase() {
		//System.out.println("Truncate Database");
		enrollmentRepo.deleteAll();
		sectionRepo.deleteAll();
		courseRepo.deleteAll();
		studentRepo.deleteAll();
		facultyRepo.deleteAll();
		personRepo.deleteAll();
	}
	
	public Faculty createFaculty(Faculty faculty) {
		return facultyRepo.save(faculty);
	}
	
	public Student createStudent(Student student) {
		return studentRepo.save(student);
	}
	
	public Course createCourse(Course course) {
		return courseRepo.save(course);
	}
	
	public Section createSection(Section section) {
		return sectionRepo.save(section);
	}
	
	public Course addSectionToCourse(Section section, Course course) {
		section.setCourse(course);
		course = courseRepo.save(course);
		sectionRepo.save(section);
		return course;
	}
	
	public Course setAuthorForCourse(Faculty faculty, Course course) {
		course.setFaculty(faculty);
		course = courseRepo.save(course);
		facultyRepo.save(faculty);
		return course;
	}
	
	public Boolean enrollStudentInSection(Student student, Section section) {
		// no available seats
		if(section.getSeats() == 0)
			return false;
		
		section.setSeats(section.getSeats() - 1);
		Enrollment enrollment = new Enrollment(student, section);
		enrollmentRepo.save(enrollment);
		studentRepo.save(student);
		sectionRepo.save(section);
		return true;
	}
	
	public List<Person> findAllUsers() {
		return (List<Person>) personRepo.findAll();
	}
	
	public List<Faculty> findAllFaculty() {
		return (List<Faculty>) facultyRepo.findAll();
	}
	public List<Student> findAllStudents() {
		return (List<Student>) studentRepo.findAll();
	}
	public List<Course> findAllCourses() {
		return (List<Course>) courseRepo.findAll();
	}
	public List<Section> findAllSections() {
		return (List<Section>) sectionRepo.findAll();
	}
	public List<Course> findCoursesForAuthor(Faculty faculty) {
		return courseRepo.findCoursesForAuthor(faculty);
	}
	public List<Section> findSectionForCourse(Course course) {
		return sectionRepo.findSectionForCourse(course);
	}
	public List<Student> findStudentsInSection(Section section) {
		List<Student> students = new ArrayList<>();
		for(Enrollment enrollment : enrollmentRepo.findEnrollmentsBySection(section)) {
			students.add(enrollment.getStudent());
		}
		return students;
	}
	public List<Section> findSectionsForStudent(Student student) {
		List<Section> sections = new ArrayList<>();
		for(Enrollment enrollment : enrollmentRepo.findEnrollmentsByStudent(student)) {
			sections.add(enrollment.getSection());
		}
		return sections;
	}
	public Section findSectionByTitle(String title) {
		return sectionRepo.findSectionByTitle(title);
	}
}
