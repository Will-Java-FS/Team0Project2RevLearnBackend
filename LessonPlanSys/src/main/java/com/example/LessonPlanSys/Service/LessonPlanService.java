package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.LessonPlan;

import java.util.List;

public interface LessonPlanService {

    List<LessonPlan> getAll();

    LessonPlan getById(int id);

    LessonPlan addLessonPlan(LessonPlan nlp);

    void deleteLessonPlan(int id);

    LessonPlan updateLessonPlan(LessonPlan nlp, int id);
}
