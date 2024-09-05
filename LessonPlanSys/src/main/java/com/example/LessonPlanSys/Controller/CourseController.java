package com.example.LessonPlanSys.Controller;

import com.example.LessonPlanSys.Model.Course;
//import com.example.LessonPlanSys.Model.Program;
import com.example.LessonPlanSys.Model.ForumPost;
import com.example.LessonPlanSys.Model.User;
import com.example.LessonPlanSys.Service.CourseService;
import com.example.LessonPlanSys.Service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    CourseService courseService;
    UserServiceImp us;
    @Autowired
    public CourseController(CourseService courseService, UserServiceImp us)
    {
        this.courseService = courseService;
        this.us=us;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getById(id));

    }

    @PostMapping
    public Course addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCourse(@PathVariable int id) {
        try {
            if (courseService.deleteCourse(id)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Course not found"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "An error occurred while deleting the course: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") int id, @RequestBody Course course) {
        Course updatedProgram = courseService.updateCourse(id, course);
        return (updatedProgram != null)
                ? new ResponseEntity<>(updatedProgram, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    @PostMapping("add/{user_id}")
    public Course addCourseteacher(@RequestBody Course course, @PathVariable int user_id) {
       User user=us.getUserByUID(user_id);
       if(user.getRole().equals("teacher")) {
          return courseService.addCourse(course);
       }
       else {
           return null;
       }
    }
}
