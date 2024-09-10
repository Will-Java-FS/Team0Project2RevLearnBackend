package com.example.LessonPlanSys.Repo;

import com.example.LessonPlanSys.Model.Enrollments;
import com.example.LessonPlanSys.Model.UserLessonStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentsRepo extends JpaRepository<Enrollments, Integer> {
    @Query(value = "SELECT c.course_name, e.status FROM courses c JOIN enrollments e ON c.course_id = e.course_id WHERE user_id = ?", nativeQuery = true)
    List<Enrollments> getEnrollmentsByStudentID(int id);

    @Query(value = "SELECT u.first_name, u.last_name FROM users u JOIN enrollments e ON u.user_id = e.user_id WHERE course_id = ?", nativeQuery = true)
    List<Enrollments> getEnrollmentsByCourseID(int id);


    @Query(value = "SELECT * FROM enrollments WHERE user_id = ?1 AND enrollment_status = 'completed'", nativeQuery = true)
    List<Enrollments> getCompletedEnrollmentsByStudentID(int id);

    @Query(value = "SELECT c.teacher_id FROM courses c JOIN enrollments e ON c.course_id = e.course_id WHERE e.course_id = ?", nativeQuery = true)
    Enrollments getTeacherOfCourse(int id);

    @Modifying
    @Query(value = "UPDATE enrollments SET payment_status = 'Paid' WHERE user_id = :userId AND course_id = :courseId", nativeQuery = true)
    int updateEnrollmentStatusToPaidByUserIdAndCourseId(@Param("userId") Integer userId, @Param("courseId") Integer courseId);

}