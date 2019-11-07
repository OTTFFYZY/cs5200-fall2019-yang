package edu.northeastern.cs5200.repositories;

import org.springframework.data.repository.*;
import edu.northeastern.cs5200.entities.Student;

public interface StudentRepo extends CrudRepository<Student, Integer> {
	
}
