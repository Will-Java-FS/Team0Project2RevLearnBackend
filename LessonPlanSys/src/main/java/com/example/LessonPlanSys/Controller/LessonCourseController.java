package com.example.LessonPlanSys.Controller;

import com.example.LessonPlanSys.Model.Course;
import com.example.LessonPlanSys.Model.LessonCourse;
import com.example.LessonPlanSys.Model.LessonPlan;
import com.example.LessonPlanSys.Service.LessonCourseServiceImpl;
import com.example.LessonPlanSys.Service.LessonPlanService;
import com.example.LessonPlanSys.Service.CourseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/lesson-course")
public class LessonCourseController {

    @Autowired
    LessonPlanService ls;

    @Autowired
    CourseServiceImp cs;

    @Autowired
    LessonCourseServiceImpl lcs;        // LCS?!

    @GetMapping
    public ResponseEntity<List<LessonCourse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(lcs.getAll());
    }

    @GetMapping("/{lesson_course_id}")
    public ResponseEntity<LessonCourse> getLessonCourseById(@PathVariable int lesson_course_id) {
        LessonCourse lessonCourse = lcs.getLessonCourseById(lesson_course_id);
        return lessonCourse == null
            ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
            : ResponseEntity.status(HttpStatus.OK).body(lessonCourse);
    }

//    @GetMapping("/course/{course_id}/lesson/{lesson_plan_id}")
//    public ResponseEntity<LessonCourse> getByCourseIdAndLessonPlanId(@PathVariable int course_id,
//                                                                     @PathVariable int lesson_plan_id) {
//        Optional<Course> c = cs.getById(course_id);
//        LessonPlan lp = ls.getById(lesson_plan_id);
//        if (c.isEmpty() || lp == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        LessonCourse lessonCourse = lcs.getLessonCourseByLessonPlanIdAndCourseId(lesson_plan_id, course_id);
//        return ResponseEntity.status(HttpStatus.OK).body(lessonCourse);
//    }

    @GetMapping("/course/{course_id}")
    public ResponseEntity<List<LessonPlan>> getLessonsByCourseId(@PathVariable int course_id) {
        Optional<Course> course = cs.getById(course_id);
        return course.isEmpty()
            ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
            : ResponseEntity.status(HttpStatus.OK).body(lcs.getLessonPlansByCourseId(course_id));
    }

    // TODO: Getting errors from LessonPlanRepo Custom Queries
//    @GetMapping("/lesson/{id}")
//    public ResponseEntity<List<Course>> getCoursesByLessonPlanId(@PathVariable int id) {
//        LessonPlan lesson = ls.getById(id);
//        if (lesson == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(lcs.getCoursesByLessonPlanId(id));
//    }

//    @PostMapping("/course/{course_id}/add-lesson/{lesson_plan_id}")
//    public ResponseEntity<LessonCourse> addLessonToCourse(@PathVariable int course_id,
//                                                          @PathVariable int lesson_plan_id) {
//
//        LessonPlan lp = ls.getById(lesson_plan_id);
//        Optional<Course> c = cs.getById(course_id);
//
//        if (c.isEmpty()) { // Will replace with respective error messages once exceptions are pulled
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        } else if (lp == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//
//        LessonCourse lc = lcs.addLessonToCourse(lesson_plan_id, course_id);
//
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(lc);
//    }

}
