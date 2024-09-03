package com.example.LessonPlanSys.Controller;

import com.example.LessonPlanSys.Model.Enrollments;
import com.example.LessonPlanSys.Service.EnrollmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EnrollmentsController {
    private EnrollmentsService enrollmentsService;

    @Autowired
    public EnrollmentsController(EnrollmentsService enrollmentsService) {
        this.enrollmentsService = enrollmentsService;
    }

    @PostMapping("enrollments")
    public ResponseEntity<Enrollments> addEnrollment(@RequestBody Enrollments enrollment) {
        enrollmentsService.addEnrollment(enrollment);
        return ResponseEntity.status(200).body(enrollment);
    }

    @GetMapping("enrollments")
    public ResponseEntity<List<Enrollments>> getAllEnrollments() {
        List<Enrollments> enrollments = enrollmentsService.getAllEnrollments();
        return ResponseEntity.status(200).body(enrollments);
    }

    @GetMapping("enrollments/{enrollmentID}")
    public ResponseEntity<Enrollments> getEnrollmentByID(@PathVariable Integer id) {
        Enrollments enrollment = enrollmentsService.getEnrollmentByID(id);
        return ResponseEntity.status(200).body(enrollment);
    }

    @GetMapping("enrollments/{studentID}")
    public ResponseEntity<List<Enrollments>> getEnrollmentsByStudentID(@PathVariable Integer id) {
        List<Enrollments> enrollments = enrollmentsService.getEnrollmentsByStudentID(id);
        return ResponseEntity.status(200).body(enrollments);
    }

    @GetMapping("enrollments/{courseID}")
    public ResponseEntity<List<Enrollments>> getEnrollmentsByCourseID(@PathVariable Integer id) {
        List<Enrollments> enrollments = enrollmentsService.getEnrollmentsByCourseID(id);
        return ResponseEntity.status(200).body(enrollments);
    }

    @PatchMapping("enrollments/{enrollmentID}")
    public ResponseEntity<Enrollments> updateEnrollment(@PathVariable Integer id, @RequestBody Enrollments enrollment) {
        Enrollments updatedEnrollment = enrollmentsService.updateEnrollment(id, enrollment);
        return ResponseEntity.status(200).body(updatedEnrollment);
    }

    @DeleteMapping("enrollments/{enrollmentID}")
    public ResponseEntity<Integer> deleteEnrollment(@PathVariable Integer id) {
        int enrollmentsDeleted = enrollmentsService.deleteEnrollment(id);
        if(enrollmentsDeleted > 0) {
            return ResponseEntity.status(200).body(1);
        } else {
            return ResponseEntity.status(200).build();
        }
    }

    @GetMapping("enrollments/status/{enrollmentID}")
    public ResponseEntity<Double> getEnrollmentCompletion(@PathVariable int enrollmentID)
    {
        Enrollments enr = enrollmentsService.getEnrollmentByID(enrollmentID);

        double comp = enrollmentsService.getCourseCompletionPerc(enr.getUser().getUser_id(), enr.getCourse().getCourse_id());
        return ResponseEntity.status(200).body(comp);
    }
}
