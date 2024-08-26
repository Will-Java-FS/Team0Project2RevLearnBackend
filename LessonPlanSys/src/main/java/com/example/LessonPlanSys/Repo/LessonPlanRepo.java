package com.example.LessonPlanSys.Repo;

import com.example.LessonPlanSys.Model.LessonPlan;
import org.springframework.stereotype.Repository;
import com.example.LessonPlanSys.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface LessonPlanRepo extends JpaRepository<LessonPlan, Integer>{

}
