package com.example.LessonPlanSys.Repo;

import com.example.LessonPlanSys.Model.Enrollments;
import com.example.LessonPlanSys.Model.UserLessonStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentsRepo extends JpaRepository<Enrollments, Integer> {
    @Query(value = "SELECT c.course_name, e.enrollment_status FROM courses c JOIN enrollments e ON c.course_id = e.course_id WHERE user_id = ?", nativeQuery = true)
    List<Enrollments> getEnrollmentsByStudentID(int id);

    @Query(value = "SELECT u.first_name, u.last_name FROM users u JOIN enrollments e ON u.user_id = e.user_id WHERE course_id = ?", nativeQuery = true)
    List<Enrollments> getEnrollmentsByCourseID(int id);

    List<Enrollments> findEnrollmentsByUserUserId(int userId);

    @Query(value = "SELECT * FROM enrollments WHERE user_id = ?1 AND enrollment_status = 'completed'", nativeQuery = true)
    List<Enrollments> getCompletedEnrollmentsByStudentID(int id);

    @Query(value = "SELECT u.first_name, u.last_name FROM users u JOIN courses c ON u.user_id = c.teacher_id JOIN enrollments e ON c.course_id = e.course_id WHERE e.course_id = ?", nativeQuery = true)
    Enrollments getTeacherOfCourse(int id);

    }