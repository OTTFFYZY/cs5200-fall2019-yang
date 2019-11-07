package edu.northeastern.cs5200.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;
import edu.northeastern.cs5200.entities.*;

public interface SectionRepo extends CrudRepository<Section, Integer> {
	 @Query("SELECT s FROM Section s WHERE s.course=:course")
	 public List<Section> findSectionForCourse(@Param("course") Course course);
	 @Query("SELECT s FROM Section s WHERE s.title=:title")
	 public Section findSectionByTitle(@Param("title") String title);
}
