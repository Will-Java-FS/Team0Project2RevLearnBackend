package com.example.LessonPlanSys.Repo;

import com.example.LessonPlanSys.Model.LessonPlan;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.*;

@Repository
public interface LessonPlanRepo extends JpaRepository<LessonPlan, Integer>{

}
