package com.example.LessonPlanSys.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LessonPlanSys.Model.Course;
import com.example.LessonPlanSys.Model.LessonCourse;
import com.example.LessonPlanSys.Model.LessonPlan;

import com.example.LessonPlanSys.Repo.LessonCourseRepo;
import com.example.LessonPlanSys.Repo.LessonPlanRepo;
import com.example.LessonPlanSys.Repo.CourseRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonCourseServiceImpl implements LessonCourseService {

    @Autowired
    LessonPlanRepo lr;

    @Autowired
    CourseRepo cr;

    @Autowired
    LessonCourseRepo lcr;

    public List<LessonCourse> getAll() {
        return lcr.findAll();
    }

    public LessonCourse getLessonCourseById(int lesson_course_id) {
        return lcr.findById(lesson_course_id)
                .orElse(null);
    }

    public LessonCourse getLessonCourseByLessonPlanIdAndCourseId(int lesson_plan_id, int course_id) {

        return lcr.findAll().stream()
                .filter(lc -> lc.getCourse().getCourse_id() == course_id
                        && lc.getLessonPlan().getLesson_plan_id() == lesson_plan_id)
                .findFirst()
                .orElse(null);
    }

    public List<Course> getCoursesByLessonPlanId(int lesson_plan_id) {

        return lcr.findAll().stream()
                .filter(lc -> lc.getLessonPlan().getLesson_plan_id() == lesson_plan_id)
                .map(LessonCourse::getCourse)
                .collect(Collectors.toList());
    }

    public List<LessonPlan> getLessonPlansByCourseId(int course_id) {

        return lcr.findAll().stream()
                .filter(lc -> lc.getCourse().getCourse_id() == course_id)
                .map(LessonCourse::getLessonPlan)
                .collect(Collectors.toList());
    }


    public LessonCourse addLessonToCourse(int lesson_plan_id, int course_id) {
        LessonPlan lessonPlan = lr.findById(lesson_plan_id)
                .orElse(null);

        Course course = cr.findById(course_id)
                .orElse(null);

        // LessonPlan and Course should already exist
        if (lessonPlan != null && course != null) {
            LessonCourse lessonCourse = new LessonCourse();
            lessonCourse.setLessonPlan(lessonPlan);
            lessonCourse.setCourse(course);
            return lcr.save(lessonCourse);
        }
        return null;
    }

    public void deleteLessonFromCourse(int lesson_plan_id, int course_id) {
        LessonCourse lessonCourse = lcr.findByLessonPlanIdAndCourseId(lesson_plan_id, course_id);
        if (lessonCourse != null) {
            lcr.delete(lessonCourse);
        }
    }

}
