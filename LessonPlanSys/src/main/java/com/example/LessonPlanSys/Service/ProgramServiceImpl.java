package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.Program;
import com.example.LessonPlanSys.Repo.ProgramRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService {
    private final ProgramRepo programRepository;

    @Override
    public Optional<Program> getProgram(int id) {
        return programRepository.findById(id);
    }

    @Override
    public Program addProgram(Program program) {
        return programRepository.save(program);
    }

    @Override
    public Optional<Program> updateProgram(int id, Program newProgram) {
        return programRepository.findById(id)
                .map(existingProgram -> {
                    existingProgram.setProgram_name(newProgram.getProgram_name());
                    existingProgram.setCourses(newProgram.getCourses());
                    return programRepository.save(existingProgram);
                });
    }

    @Override
    public void deleteProgram(int id) {
        programRepository.deleteById(id);
    }

    @Override
    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }
}
