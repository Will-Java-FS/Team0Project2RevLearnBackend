package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.Course;
import com.example.LessonPlanSys.Repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImp implements CourseService {
    @Autowired
    CourseRepo courseRepo;

    @Override
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    @Override
    public Optional<Course> getById(int id) {
        return courseRepo.findById(id);
    }

    @Override
    public Optional<Course> getByName(String name) {
        return courseRepo.findByCourseName(name);
    }

    @Override
    public Course addCourse(Course course) {
        return courseRepo.save(course);
    }
}
