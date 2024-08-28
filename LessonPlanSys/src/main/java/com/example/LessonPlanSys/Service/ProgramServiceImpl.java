package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.Program;
import com.example.LessonPlanSys.Repo.ProgramRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProgramServiceImpl implements ProgramService {
    @Autowired
    ProgramRepo pr;

    @Override
    public Program getProgram(int id) {
        return pr.findById(id).orElse(null);
    }

    @Override
    public Program addProgram(Program program) {
        return pr.save(program);
    }

    @Override
    public Program updateProgram(int id, Program newProgram) {
        Program existingProgram = pr.findById(id).orElse(null);

        if (existingProgram == null) {
            return null;
        }

        //TODO: Adjust so that the courses are also copied over
        existingProgram.setProgram_name(newProgram.getProgram_name());
        //existingProgram.setProgramCourses(newProgram.getProgramCourses());

        return pr.save(existingProgram);

    }

    @Override
    public void deleteProgram(int id) {
        pr.deleteById(id);
    }

    @Override
    public List<Program> getAllPrograms() {
        return pr.findAll();
    }
}
