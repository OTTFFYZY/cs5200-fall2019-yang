package edu.northeastern.cs5200.repositories;

import edu.northeastern.cs5200.entities.*;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;


public interface EnrollmentRepo extends CrudRepository<Enrollment, Integer> {
	@Query("SELECT e FROM Enrollment e WHERE e.section=:section")
	public List<Enrollment> findEnrollmentsBySection(@Param("section") Section section);
	
	@Query("SELECT e FROM Enrollment e WHERE e.student=:student")
	public List<Enrollment> findEnrollmentsByStudent(@Param("student") Student student);
}
