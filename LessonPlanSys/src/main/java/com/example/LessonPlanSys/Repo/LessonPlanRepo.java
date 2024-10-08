package com.example.LessonPlanSys.Repo;

import com.example.LessonPlanSys.Model.LessonPlan;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.*;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface LessonPlanRepo extends JpaRepository<LessonPlan, Integer>{
    @Query(value = "SELECT * From lesson_plans", nativeQuery = true)
    List<LessonPlan> getAllLessonPlans();

    @Query(value = "SELECT * FROM lesson_plans where lesson_plan_id = ?1", nativeQuery = true)
    LessonPlan getbyLPID(int lpId);

    @Modifying
    @Query(value = "UPDATE lesson_plans l SET l.title = ?1, l.content = ?2, l.updated_at = ?3 where lesson_plan_id = ?4", nativeQuery = true)
    void setLPInfoById(String title, String content, Timestamp updated_at, int lp_id);
}
