package com.example.LessonPlanSys.Repo;

import com.example.LessonPlanSys.Model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProgramRepo extends JpaRepository<Program, Integer> {
//    @Query(value = "SELECT * From programs WHERE program_name = ? 1", nativeQuery = true)
//    Program getProgramByName(String name);
}
