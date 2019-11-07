package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;
import edu.northeastern.cs5200.entities.Faculty;

public interface FacultyRepo extends CrudRepository<Faculty, Integer> {
	@Query("SELECT p FROM Person p WHERE p.username=:username")
    public Faculty findPersonByUsername(@Param("username") String username);
}
