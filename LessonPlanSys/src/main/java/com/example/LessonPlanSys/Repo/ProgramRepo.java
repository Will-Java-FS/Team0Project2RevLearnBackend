package com.example.LessonPlanSys.Repo;

import com.example.LessonPlanSys.Model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProgramRepo extends JpaRepository<Program, Integer> {
}
