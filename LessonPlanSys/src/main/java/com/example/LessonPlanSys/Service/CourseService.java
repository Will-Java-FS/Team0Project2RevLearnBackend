package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.Course;
import com.example.LessonPlanSys.Model.Program;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> getAllCourses();
    Optional<Course> getById(int id);
    Optional<Course> getByName(String name);
    Course addCourse(Course course);
    boolean deleteCourse(int id);
    Course updateCourse(int id, Course updatedCourse);
    Optional<List<Course>> getCoursesByProgramId(int id);
}
