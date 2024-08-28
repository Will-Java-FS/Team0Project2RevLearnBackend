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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LessonCourseServiceImpl {

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
                .orElseThrow(() -> new RuntimeException("LessonPlan|Course not found"));
    }

    public LessonCourse getLessonCourseByLessonPlanIdAndCourseId(int lesson_plan_id, int course_id) {
        return lcr.findAll().stream()
                .filter(lc -> lc.getCourse().getCourse_id() == course_id
                        && lc.getLessonPlan().getLesson_plan_id() == lesson_plan_id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("LessonCourse not found"));
    }

    public List<Course> getCoursesByLessonPlanId(int lesson_plan_id) {
        // FindAll -> convert to stream -> filter by lessonPlanId -> Map from LessonCourse to Course -> return list
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


    public LessonCourse addLessonToCourse(int lessonPlanId, int courseId) {
        LessonPlan lessonPlan = lr.findById(lessonPlanId)
                .orElseThrow(() -> new RuntimeException("LessonPlan not found"));

        Course course = cr.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        LessonCourse lessonCourse = new LessonCourse();

        if (lessonPlan != null && course != null) {
            lessonCourse.setLessonPlan(lessonPlan);
            lessonCourse.setCourse(course);
            return lcr.save(lessonCourse);
        }
        return null;
    }

    public void deleteLessonFromCourse(int lesson_plan_id, int course_id) {
        Optional<LessonCourse> lessonCourse = lcr.findAll().stream()
                .filter(lc -> lc.getCourse().getCourse_id() == course_id
                        && lc.getLessonPlan().getLesson_plan_id() == lesson_plan_id)
                .findFirst();
        lessonCourse.ifPresent(lc -> lcr.delete(lc));
    }

}
