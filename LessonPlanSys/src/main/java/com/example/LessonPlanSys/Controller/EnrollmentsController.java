package com.example.LessonPlanSys.Controller;

import com.example.LessonPlanSys.Model.Course;
import com.example.LessonPlanSys.Model.Enrollments;
import com.example.LessonPlanSys.Service.EnrollmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentsController {
    private EnrollmentsService enrollmentsService;

    @Autowired
    public EnrollmentsController(EnrollmentsService enrollmentsService) {
        this.enrollmentsService = enrollmentsService;
    }

    @PostMapping
    public ResponseEntity<Enrollments> addEnrollment(@RequestBody Enrollments enrollment) {
        enrollmentsService.addEnrollment(enrollment);
        return ResponseEntity.status(200).body(enrollment);
    }

    @GetMapping
    public ResponseEntity<List<Enrollments>> getAllEnrollments() {
        List<Enrollments> enrollments = enrollmentsService.getAllEnrollments();
        return ResponseEntity.status(200).body(enrollments);
    }

    @GetMapping("/{enroll_id}")
    public ResponseEntity<Enrollments> getEnrollmentByID(@PathVariable("enroll_id") Integer enrollId) {
        Enrollments enrollment = enrollmentsService.getEnrollmentByID(enrollId);
        return ResponseEntity.status(200).body(enrollment);
    }

    @GetMapping("/courses/{student_id}")
    public ResponseEntity<List<Enrollments>> getEnrollmentsByStudentID(@PathVariable("student_id") Integer studentId) {
        List<Enrollments> enrollments = enrollmentsService.getEnrollmentsByStudentID(studentId);
        return ResponseEntity.status(200).body(enrollments);
    }

    @GetMapping("/students/{course_id}")
    public ResponseEntity<List<Enrollments>> getEnrollmentsByCourseID(@PathVariable("course_id") Integer courseId) {
        List<Enrollments> enrollments = enrollmentsService.getEnrollmentsByCourseID(courseId);
        return ResponseEntity.status(200).body(enrollments);
    }

    @PatchMapping("/{enroll_id}")
    public ResponseEntity<Enrollments> updateEnrollment(@PathVariable("enroll_id") Integer enrollId, @RequestBody Enrollments enrollment) {
        Enrollments updatedEnrollment = enrollmentsService.updateEnrollment(enrollId, enrollment);
        return ResponseEntity.status(200).body(updatedEnrollment);
    }

    @DeleteMapping("/{enroll_id}")
    public ResponseEntity<Integer> deleteEnrollment(@PathVariable("enroll_id") Integer enrollId) {
        int enrollmentsDeleted = enrollmentsService.deleteEnrollment(enrollId);
        if(enrollmentsDeleted > 0) {
            return ResponseEntity.status(200).body(1);
        } else {
            return ResponseEntity.status(200).build();
        }
    }

    @GetMapping("/status/{enroll_id}")
    public ResponseEntity<Double> getEnrollmentCompletion(@PathVariable("enroll_id") int enrollId)
    {
        Enrollments enr = enrollmentsService.getEnrollmentByID(enrollId);

        double comp = enrollmentsService.getCourseCompletionPerc(enr.getUser().getUserId(), enr.getCourse().getCourse_id());
        return ResponseEntity.status(200).body(comp);
    }

    @GetMapping("/completed/{student_id}")
    public ResponseEntity<List<Enrollments>> getCompletedEnrollmentsByStudentID(@PathVariable("student_id") int studentId) {
        List<Enrollments> enrollments = enrollmentsService.getCompletedEnrollmentsByStudentID(studentId);
        return ResponseEntity.status(200).body(enrollments);
    }

    @GetMapping("/courses-available")
    public ResponseEntity<List<Course>> getAllAvailableCourses() {
        List<Course> courses = enrollmentsService.getAvailableCourses();
        return ResponseEntity.status(200).body(courses);
    }

    @GetMapping("/teacher/{courseID}")
    public ResponseEntity<Enrollments> getTeacherOfCourse(@PathVariable Integer id) {
        Enrollments teacher = enrollmentsService.getTeacherOfCourse(id);
        return ResponseEntity.status(200).body(teacher);
    }
}
