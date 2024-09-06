package com.example.LessonPlanSys.Repo;

import com.example.LessonPlanSys.Model.LessonCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonCourseRepo extends JpaRepository<LessonCourse, Integer> {

}
