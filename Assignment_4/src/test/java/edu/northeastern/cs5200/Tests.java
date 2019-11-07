package edu.northeastern.cs5200;

import org.junit.Before;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import edu.northeastern.cs5200.entities.*;
import edu.northeastern.cs5200.daos.UniversityDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Tests {
	
	@Autowired(required = true)
	private UniversityDao uniDao;
	
	private Faculty alan, ada;
	private Student alice, bob, charlie, dan, edward, frank, gregory;
	private Course cs1234, cs2345, cs3456, cs4567;
	private Section sec4321, sec5432, sec6543, sec7654;
	
	@Before
	public void testInit()
	{
		// 1. empty the database
		uniDao.truncateDatabase();
		System.out.println("clean ok!");
		
		// 2. create faculties
		alan = new Faculty("alan", "password", "Alan", "Turin", "123A", true);
		ada = new Faculty("ada", "password", "Ada", "Lovelace", "123B", true);
				
		uniDao.createFaculty(alan);
		uniDao.createFaculty(ada);
		System.out.println("faculty ok!");
		
		// 10. create students
		alice = new Student("alice", "password", "Alice", "Wonderland", 2020, 12000L);
		bob = new Student("bob", "password", "Bob", "Hope", 2021, 23000L);
		charlie = new Student("charlie", "password", "Charlie", "Brown", 2019, 21000L);
		dan = new Student("dan", "password", "Dan", "Craig", 2019, 0L);
		edward = new Student("edward", "password", "Edward", "Scissorhands", 2022, 11000L);
		frank = new Student("frank", "password", "Frank", "Herbert", 2018, 0L);
		gregory = new Student("gregory", "password", "Gregory", "Peck", 2023, 10000L);
		
		uniDao.createStudent(alice);
		uniDao.createStudent(bob);
		uniDao.createStudent(charlie);
		uniDao.createStudent(dan);
		uniDao.createStudent(edward);
		uniDao.createStudent(frank);
		uniDao.createStudent(gregory);
		System.out.println("students ok!");
		
		// 11. create courses
		cs1234 = new Course("CS1234", alan);
		cs2345 = new Course("CS2345", alan);
		cs3456 = new Course("CS3456", ada);
		cs4567 = new Course("CS4567", ada);
		uniDao.createCourse(cs1234);
		uniDao.createCourse(cs2345);
		uniDao.createCourse(cs3456);
		uniDao.createCourse(cs4567);
		System.out.println("courses ok!");
		
		// 12. create sections
		sec4321 = new Section(50, "SEC4321", cs1234);
	    sec5432 = new Section(50, "SEC5432", cs1234);
		sec6543 = new Section(50, "SEC6543", cs2345);
		sec7654 = new Section(50, "SEC7654", cs3456);

		sec4321 = uniDao.createSection(sec4321);
		sec5432 = uniDao.createSection(sec5432);
		sec6543 = uniDao.createSection(sec6543);
		sec7654 = uniDao.createSection(sec7654);
		System.out.println("sections ok!");
		
		// 13. enroll student into section
		uniDao.enrollStudentInSection(alice, sec4321);
		uniDao.enrollStudentInSection(alice, sec5432);
		uniDao.enrollStudentInSection(bob, sec5432);
		uniDao.enrollStudentInSection(charlie, sec6543);
		System.out.println("enroll ok!");
		
		System.out.println("insert ok!");
	}
	
	@Test
	public void testNumbers() {
		// 14. validates users
		assertEquals(9, uniDao.findAllUsers().size());
		
		// 15. validates faculties
		assertEquals(2, uniDao.findAllFaculty().size());
		
		// 16. validates students
		assertEquals(uniDao.findAllStudents().size(), 7);
		
		// 17. validates courses
		assertEquals(uniDao.findAllCourses().size(), 4);
		
		// 18. validates sections
		assertEquals(uniDao.findAllSections().size(), 4);
		
		// 19. validates course authorship
		assertEquals(2, uniDao.findCoursesForAuthor(alan).size());
		assertEquals(2, uniDao.findCoursesForAuthor(ada).size());
		
		// 20. validates section per course
		assertEquals(2, uniDao.findSectionForCourse(cs1234).size());
		assertEquals(1, uniDao.findSectionForCourse(cs2345).size());
		assertEquals(1, uniDao.findSectionForCourse(cs3456).size());
		assertEquals(0, uniDao.findSectionForCourse(cs4567).size());
		
		// 21. validates section enrollments
		assertEquals(1, uniDao.findStudentsInSection(sec4321).size());
		assertEquals(2, uniDao.findStudentsInSection(sec5432).size());
		assertEquals(1, uniDao.findStudentsInSection(sec6543).size());
		assertEquals(0, uniDao.findStudentsInSection(sec7654).size());
		
		// 22. validates student enrollments
		assertEquals(2, uniDao.findSectionsForStudent(alice).size());
		assertEquals(1, uniDao.findSectionsForStudent(bob).size());
		assertEquals(1, uniDao.findSectionsForStudent(charlie).size());
		assertEquals(0, uniDao.findSectionsForStudent(dan).size());
		assertEquals(0, uniDao.findSectionsForStudent(edward).size());
		assertEquals(0, uniDao.findSectionsForStudent(frank).size());
		assertEquals(0, uniDao.findSectionsForStudent(gregory).size());
		
		// 23. validates section seats
		assertEquals(49, uniDao.findSectionByTitle(sec4321.getTitle()).getSeats());
		assertEquals(48, uniDao.findSectionByTitle(sec5432.getTitle()).getSeats());
		assertEquals(49, uniDao.findSectionByTitle(sec6543.getTitle()).getSeats());
		assertEquals(50, uniDao.findSectionByTitle(sec7654.getTitle()).getSeats());
			
		System.out.println("ok!");
	}
}
