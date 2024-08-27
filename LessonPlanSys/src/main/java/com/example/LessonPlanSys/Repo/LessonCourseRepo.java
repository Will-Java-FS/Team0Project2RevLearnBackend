package com.example.LessonPlanSys.Repo;

import com.example.LessonPlanSys.Model.Course;
import com.example.LessonPlanSys.Model.LessonCourse;
import com.example.LessonPlanSys.Model.LessonPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonCourseRepo extends JpaRepository<LessonCourse, Integer> {
    //Potential custom queries:

}
