package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.Course;
//import com.example.LessonPlanSys.Model.Program;
import com.example.LessonPlanSys.Repo.CourseRepo;
import com.example.LessonPlanSys.Repo.ProgramRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImp implements CourseService {
    @Autowired
    CourseRepo courseRepo;
    ProgramRepo programRepo;

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

    @Override
    public boolean deleteCourse(int id) {
        if (courseRepo.existsById(id)) {
            courseRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Course updateCourse(int id, Course updatedCourse) {
        Course existingCourse = courseRepo.findById(id).orElse(null);
        if (existingCourse == null) return null;

        existingCourse.setCourseName(updatedCourse.getCourseName());
        existingCourse.setDescription(updatedCourse.getDescription());
        existingCourse.setTeacherId(updatedCourse.getTeacherId());
        return courseRepo.save(existingCourse);
    }

    @Override
    public Optional<List<Course>> getCoursesByProgramId(int program_id) {
        return courseRepo.findAllCoursesByProgramId(program_id);
    }
}
