package com.example.LessonPlanSys.Repo;

import com.example.LessonPlanSys.Model.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepo extends JpaRepository<Program, Integer> {
//    Program getProgramByName(String name);
}
