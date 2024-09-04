package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.Course;
import com.example.LessonPlanSys.Model.LessonCourse;
import com.example.LessonPlanSys.Model.LessonPlan;

import java.util.List;

public interface LessonCourseService {

    LessonCourse getLessonCourseById(int lesson_course_id);
    LessonCourse getLessonCourseByLessonPlanIdAndCourseId(int courseId, int lessonPlanId);

    List<LessonCourse> getAll();
    List<Course> getCoursesByLessonPlanId(int lesson_plan_id);
    List<LessonPlan> getLessonPlansByCourseId(int course_id);

    LessonCourse addLessonToCourse(int lessonPlanId, int courseId);
    void deleteLessonFromCourse(int lessonPlanId, int courseId);

}
