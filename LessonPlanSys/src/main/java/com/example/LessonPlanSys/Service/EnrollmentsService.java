package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.Course;
import com.example.LessonPlanSys.Model.Enrollments;
import com.example.LessonPlanSys.Model.UserLessonStatus;
import com.example.LessonPlanSys.Repo.CourseRepo;
import com.example.LessonPlanSys.Repo.EnrollmentsRepo;
import com.example.LessonPlanSys.Repo.UserLessonStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentsService {
    EnrollmentsRepo enrollmentsRepo;
    UserLessonStatusRepo userLessonStatusRepo;
    CourseRepo courseRepo;

    @Autowired
    public EnrollmentsService(EnrollmentsRepo enrollmentsRepo, UserLessonStatusRepo userLessonStatusRepo, CourseRepo courseRepo) {
        this.enrollmentsRepo = enrollmentsRepo;
        this.userLessonStatusRepo = userLessonStatusRepo;
        this.courseRepo = courseRepo;
    }

    public Enrollments addEnrollment(Enrollments enrollment) {
        return enrollmentsRepo.save(enrollment);
    }

    public List<Enrollments> getAllEnrollments() {
        return enrollmentsRepo.findAll();
    }

    public List<Course> getAvailableCourses() {
        return courseRepo.findAll();
    }

    public Enrollments getTeacherOfCourse(Integer id) {
        return enrollmentsRepo.getTeacherOfCourse(id);
    }

    public Enrollments getEnrollmentByID(Integer id) {
        return enrollmentsRepo.findById(id).orElse(null);
    }

    public List<Enrollments> getEnrollmentsByStudentID(Integer id) {
        return enrollmentsRepo.getEnrollmentsByStudentID(id);
    }

    public List<Enrollments> getEnrollmentsByCourseID(Integer id) {
        return enrollmentsRepo.getEnrollmentsByCourseID(id);
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
            if(updatedEnrollment.getEnrollment_status() != null) {
                enrollment.setEnrollment_status(updatedEnrollment.getEnrollment_status());
            }
            if(updatedEnrollment.getPayment_status() != null) {
                enrollment.setPayment_status(updatedEnrollment.getPayment_status());
            }
            enrollmentsRepo.save(enrollment);
            return enrollment;
        } else {
            return null;
        }
    }


    public double getCourseCompletionPerc(int user_id, int course_id) {
        List<UserLessonStatus> ULS = userLessonStatusRepo.getAllULS(user_id,course_id);
        //System.out.println(ULS.size() + "Lessons found");
        double count = 0;
        double total = 0;
        for(UserLessonStatus item : ULS)
        {
            total += 1;
            if(item.isComplete())
            {
                count += 1;
            }
        }
        return count/total;
    }

    public List<Enrollments> getCompletedEnrollmentsByStudentID(int id) {
        return enrollmentsRepo.getCompletedEnrollmentsByStudentID(id);
    }

 
}
