package com.example.LessonPlanSys.Repo;

import com.example.LessonPlanSys.Model.LessonPlan;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.*;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface LessonPlanRepo extends JpaRepository<LessonPlan, Integer>{

}
