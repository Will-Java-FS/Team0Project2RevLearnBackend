package com.example.LessonPlanSys.Repo;

import com.example.LessonPlanSys.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Integer> {

}
