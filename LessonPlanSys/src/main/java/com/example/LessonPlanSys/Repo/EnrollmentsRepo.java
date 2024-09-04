package com.example.LessonPlanSys.Repo;

import com.example.LessonPlanSys.Model.Enrollments;
import com.example.LessonPlanSys.Model.UserLessonStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentsRepo extends JpaRepository<Enrollments, Integer> {
    @Query(value = "SELECT COALESCE(uls.complete, false) FROM user_lesson_status AS uls Right JOIN lessonplan_course ON lessonplan_course.lesson_plan_id = uls.lesson_plan_id WHERE COALESCE(uls.user_id,1) = ?1 AND lessonplan_course.course_id = ?2", nativeQuery = true)
    List<Boolean> getAllULS(int uid, int cid);
}
