package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Repo.LessonPlanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonPlanServiceImp {
    LessonPlanRepo lessonPlanRepo;
    @Autowired
    public LessonPlanServiceImp(LessonPlanRepo lessonPlanRepo)
    {
        this.lessonPlanRepo = lessonPlanRepo;
    }

}
