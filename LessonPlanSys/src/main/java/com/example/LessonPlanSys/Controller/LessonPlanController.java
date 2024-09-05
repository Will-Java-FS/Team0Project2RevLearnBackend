package com.example.LessonPlanSys.Controller;

import com.example.LessonPlanSys.Model.LessonPlan;
import com.example.LessonPlanSys.Service.LessonPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonPlanController {

    LessonPlanService lessonPlanService;

    @Autowired
    public LessonPlanController(LessonPlanService lessonPlanService) {
        this.lessonPlanService = lessonPlanService;
    }

    // Get all Lessons
    @GetMapping
    public ResponseEntity<List<LessonPlan>> getAllLessonPlans() {
        return ResponseEntity.status(HttpStatus.OK).body(lessonPlanService.getAll());
    }

    // Get lesson by ID
    @GetMapping("/{lesson_id}")
    public ResponseEntity<LessonPlan> getLessonPlan(@PathVariable int lesson_id) {
        LessonPlan lessonPlan = lessonPlanService.getById(lesson_id);
        return lessonPlan != null
                ? ResponseEntity.status(HttpStatus.OK).body(lessonPlan)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Add lesson
    @PostMapping
    public ResponseEntity<LessonPlan> addLessonPlan(@RequestBody LessonPlan lessonPlan) {
        LessonPlan alreadyExists = lessonPlanService.getById(lessonPlan.getLesson_plan_id());
        return alreadyExists == null
            ? ResponseEntity.status(HttpStatus.CREATED).body(lessonPlanService.addLessonPlan(lessonPlan))
            : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // Update lesson, ids must match
    @PutMapping("/{lesson_id}")
    public ResponseEntity<LessonPlan> updateLessonPlan(@PathVariable int lesson_id,
                                                       @RequestBody LessonPlan lessonPlan) {
        // Should exist
        LessonPlan lp = lessonPlanService.getById(lesson_id);
        if (lp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        lp = lessonPlanService.updateLessonPlan(lesson_id, lessonPlan);
        return lp == null
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build() // didn't pass validation
                : ResponseEntity.status(HttpStatus.OK).body(lp);
    }

    // Delete lesson by ID
    @DeleteMapping("/{lesson_id}")
    public ResponseEntity<Void> deleteLessonPlan(@PathVariable int lesson_id) {
        lessonPlanService.deleteLessonPlan(lesson_id);
        return ResponseEntity.noContent().build();
    }

}
