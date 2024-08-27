package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> getAllCourses();
    Optional<Course> getById(int id);
    Optional<Course> getByName(String name);
    Course addCourse(Course course);
}
