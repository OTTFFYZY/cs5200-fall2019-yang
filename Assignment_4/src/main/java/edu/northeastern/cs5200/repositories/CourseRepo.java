package edu.northeastern.cs5200.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.entities.Course;
import edu.northeastern.cs5200.entities.Faculty;

public interface CourseRepo extends CrudRepository<Course, Integer> {
	@Query("SELECT c FROM Course c WHERE c.faculty=:faculty")
    public List<Course> findCoursesForAuthor(@Param("faculty") Faculty faculty);
}
