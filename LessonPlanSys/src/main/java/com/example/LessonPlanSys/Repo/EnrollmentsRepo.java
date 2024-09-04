package com.example.LessonPlanSys.Repo;

import com.example.LessonPlanSys.Model.Enrollments;
import com.example.LessonPlanSys.Model.UserLessonStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentsRepo extends JpaRepository<Enrollments, Integer> {
    @Query(value = "SELECT c.course_name, e.status FROM courses c JOIN enrollments e ON c.course_id = e.course_id WHERE user_id = ?", nativeQuery = true)
    List<Enrollments> getEnrollmentsByStudentID(int id);

    @Query(value = "SELECT u.first_name, u.last_name FROM users u JOIN enrollments e ON u.user_id = e.user_id WHERE course_id = ?", nativeQuery = true)
    List<Enrollments> getEnrollmentsByCourseID(int id);
    @Query(value = "SELECT uls.complete FROM user_lesson_status AS uls LEFT JOIN lessonplan_course ON lessonplan_course.lesson_plan_id = uls.lesson_plan_id WHERE uls.user_id = ?1 AND lessonplan_course.course_id = ?2", nativeQuery = true)
    List<Boolean> getAllULS(int uid, int cid);
}
