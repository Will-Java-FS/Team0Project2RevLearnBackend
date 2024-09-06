package com.example.LessonPlanSys.Repo;

import com.example.LessonPlanSys.Model.UserLessonStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLessonStatusRepo extends JpaRepository <UserLessonStatus, Integer> {
    @Query(value = "SELECT uls.user_lesson_id, COALESCE(uls.complete, false) AS complete, lesson_plan_course.lesson_plan_id, COALESCE(uls.user_id, ?1) AS user_id FROM user_lesson_status AS uls Right JOIN lesson_plan_course ON lesson_plan_course.lesson_plan_id = uls.lesson_plan_id WHERE COALESCE(uls.user_id,?1) = ?1 AND lesson_plan_course.course_id = ?2", nativeQuery = true)
    List<UserLessonStatus> getAllULS(int uid, int cid);
}
