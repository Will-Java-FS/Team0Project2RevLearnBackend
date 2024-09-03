package com.example.LessonPlanSys.Repo;

import com.example.LessonPlanSys.Model.Course;
import com.example.LessonPlanSys.Model.LessonCourse;
import com.example.LessonPlanSys.Model.LessonPlan;
import com.example.LessonPlanSys.Model.UserLessonStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonCourseRepo extends JpaRepository<LessonCourse, Integer> {
    //Potential custom queries:
    @Query(value = "SELECT LessonPlans.* FROM LessonPlans RIGHT JOIN lesson_plan_course lc ON lc.lesson_plan_id = LessonPlans.lesson_plan_id WHERE lc.course_id = ?1", nativeQuery = true)
    List<LessonPlan> getAllLessons(int course_id);


}
