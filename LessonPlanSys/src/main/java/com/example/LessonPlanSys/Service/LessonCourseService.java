package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.Course;
import com.example.LessonPlanSys.Model.LessonCourse;
import com.example.LessonPlanSys.Model.LessonPlan;

import java.util.List;
import java.util.Optional;

public interface LessonCourseService {

    List<Course> getCoursesByLessonPlanId(int lesson_plan_id);
    List<LessonPlan> getLessonPlansByCourseId(int course_id);
    LessonCourse getLessonCourseById(int lesson_course_id);

}
