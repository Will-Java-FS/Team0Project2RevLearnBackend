package com.example.LessonPlanSys.Repo;

import com.example.LessonPlanSys.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepo extends JpaRepository<Course, Integer> {

    Optional<Course> findByCourseName(String name);

}
