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
    @PatchMapping("/enrollments/status/{enrollmentID}")
    public ResponseEntity<Enrollments> updateEnrollmentStatus(@PathVariable Integer enrollmentID, @RequestBody Enrollments updates) {

        Enrollments existingEnrollment = enrollmentsService.getEnrollmentByID(enrollmentID);

        if (existingEnrollment == null) {
            return ResponseEntity.notFound().build();
        }

        if (updates.getStatus()!=null) {
            existingEnrollment.setStatus(updates.getStatus());
        }


        Enrollments updatedEnrollment = enrollmentsService.updateEnrollment(enrollmentID, existingEnrollment);


        return ResponseEntity.ok(updatedEnrollment);
    }
}
