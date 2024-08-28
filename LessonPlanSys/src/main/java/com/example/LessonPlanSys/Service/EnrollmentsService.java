package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.Enrollments;
import com.example.LessonPlanSys.Repo.EnrollmentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentsService {
    EnrollmentsRepo enrollmentsRepo;

    @Autowired
    public EnrollmentsService(EnrollmentsRepo enrollmentsRepo) {
        this.enrollmentsRepo = enrollmentsRepo;
    }

    public Enrollments addEnrollment(Enrollments enrollment) {
        return enrollmentsRepo.save(enrollment);
    }

    public List<Enrollments> getAllEnrollments() {
        return enrollmentsRepo.findAll();
    }

    public Enrollments getEnrollmentByID(Integer id) {
        return enrollmentsRepo.findById(id).get();
    }

    public int deleteEnrollment(Integer id) {
        Optional<Enrollments> enrollment = enrollmentsRepo.findById(id);
        if(enrollment.isPresent()) {
            enrollmentsRepo.deleteById(id);
            return 1;
        } else {
            return 0;
        }
    }

    public Enrollments updateEnrollment(Integer id, Enrollments updatedEnrollment) {
        Optional<Enrollments> optionalEnrollment = enrollmentsRepo.findById(id);
        if(optionalEnrollment.isPresent()) {
            Enrollments enrollment = optionalEnrollment.get();
            if(updatedEnrollment.getStatus() != null) {
                enrollment.setStatus(updatedEnrollment.getStatus());
            }
            enrollmentsRepo.save(enrollment);
            return enrollment;
        } else {
            return null;
        }
    }
}