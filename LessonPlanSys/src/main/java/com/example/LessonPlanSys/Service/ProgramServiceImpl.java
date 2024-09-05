package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.Program;
import com.example.LessonPlanSys.Repo.ProgramRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepo programRepo;

    @Autowired
    public ProgramServiceImpl(ProgramRepo programRepo) {
        this.programRepo = programRepo;
    }

    @Override
    public List<Program> getAllPrograms() {
        return programRepo.findAll();
    }

    @Override
    public Program getProgramById(int id) {
        return programRepo.findById(id).orElseThrow(() -> new RuntimeException("Program not found"));
    }

    @Override
    public Program createProgram(Program program) {
        return programRepo.save(program);
    }

    @Override
    public Program updateProgram(int id, Program programDetails) {
        Program program = programRepo.findById(id).orElseThrow(() -> new RuntimeException("Program not found"));
        program.setProgramName(programDetails.getProgramName());
        return programRepo.save(program);
    }

    @Override
    public void deleteProgram(int id) {
        programRepo.deleteById(id);
    }
}