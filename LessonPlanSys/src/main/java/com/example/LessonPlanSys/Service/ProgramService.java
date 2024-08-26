package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.Program;

import java.util.List;

public interface ProgramService {
    Program getProgram(int id);
    Program addProgram(Program program);
    Program updateProgram(int id, Program program);
    void deleteProgram(int id);
    List<Program> getAllPrograms();
    //List<Course> getAllCourses();
}
