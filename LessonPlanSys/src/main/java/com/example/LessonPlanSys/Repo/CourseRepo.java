package com.example.LessonPlanSys.Repo;

import com.example.LessonPlanSys.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepo extends JpaRepository<Course, Integer> {

    Optional<Course> findByCourseName(String name);

    @Query(value = "SELECT * FROM courses where program_id = ?1", nativeQuery = true)
    Optional<List<Course>> findAllCoursesByProgramId(Integer programId);
}
