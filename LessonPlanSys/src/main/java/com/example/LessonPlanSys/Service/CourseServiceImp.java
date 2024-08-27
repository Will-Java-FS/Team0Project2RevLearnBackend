package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Repo.CourseRepo;
import com.example.LessonPlanSys.Repo.LessonPlanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImp {

    CourseRepo courseRepo;
    @Autowired
    public CourseServiceImp(CourseRepo courseRepo)
    {
        this.courseRepo = courseRepo;
    }

}
