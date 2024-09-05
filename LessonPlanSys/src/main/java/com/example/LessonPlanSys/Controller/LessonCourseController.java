package com.example.LessonPlanSys.Controller;

import com.example.LessonPlanSys.Model.Course;
import com.example.LessonPlanSys.Model.LessonCourse;
import com.example.LessonPlanSys.Model.LessonPlan;
import com.example.LessonPlanSys.Service.LessonCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class LessonCourseController {

    @Autowired
    LessonCourseService lcs;

    @GetMapping("/lesson-course")
    public ResponseEntity<List<LessonCourse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(lcs.getAll());
    }

    @GetMapping("/lesson-course/{lesson_course_id}")
    public ResponseEntity<LessonCourse> getLessonCourseById(@PathVariable int lesson_course_id) {
        LessonCourse lessonCourse = lcs.getLessonCourseById(lesson_course_id);
        return lessonCourse == null
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.status(HttpStatus.OK).body(lessonCourse);
    }

    @GetMapping("/course/{course_id}/lessons")
    public ResponseEntity<List<LessonPlan>> getLessonPlansByCourseId(@PathVariable int course_id) {
        List<LessonPlan> lessonPlans = lcs.getLessonPlansByCourseId(course_id);
        return ResponseEntity.status(HttpStatus.OK).body(lessonPlans);
    }


    @GetMapping("/lesson/{lesson_id}/courses")
    public ResponseEntity<List<Course>> getCoursesByLessonPlanId(@PathVariable int lesson_id) {
        List<Course> courses = lcs.getCoursesByLessonPlanId(lesson_id);
        return ResponseEntity.status(HttpStatus.OK).body(courses);
    }

    @PostMapping("/course/add-lesson/{lesson_plan_id}")
    public ResponseEntity<LessonCourse> addLessonToCourse(@RequestBody Course course,
                                                          @PathVariable int lesson_plan_id) {
        LessonCourse lc = lcs.addLessonToCourse(lesson_plan_id, course.getCourse_id());

        return (lc == null)
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
                : ResponseEntity.status(HttpStatus.ACCEPTED).body(lc);
    }

    @DeleteMapping("/course/delete-lesson/{lesson_plan_id}")
    public ResponseEntity<LessonCourse> removeLessonFromCourse(@RequestBody Course course,
                                                               @PathVariable int lesson_plan_id) {
        LessonCourse lc = lcs.getLessonCourseByLessonPlanIdAndCourseId(course.getCourse_id(), lesson_plan_id);

        if (lc != null) {
            lcs.deleteLessonFromCourse(lc.getLessonPlan().getLesson_plan_id(), lc.getCourse().getCourse_id());
            return ResponseEntity.status(HttpStatus.OK).body(lc);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}