package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.Program;

import java.util.List;
import java.util.Optional;

public interface ProgramService {
    Optional<Program> getProgram(int id);
    Program addProgram(Program program);
    Program updateProgram(int id, Program program);
    void deleteProgram(int id);
    List<Program> getAllPrograms();
    //List<Course> getAllCourses();
}
