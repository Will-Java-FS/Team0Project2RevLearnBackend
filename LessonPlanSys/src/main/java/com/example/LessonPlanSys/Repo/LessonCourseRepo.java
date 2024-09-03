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

    @Query("SELECT lc " +
            "FROM LessonCourse lc " +
            "WHERE lc.course.course_id=:course_id " +
            "AND lc.lessonPlan.lesson_plan_id=:lesson_plan_id")
    LessonCourse findByLessonPlanIdAndCourseId(@Param("lesson_plan_id") int lesson_plan_id,
                                               @Param("course_id") int course_id);
}
