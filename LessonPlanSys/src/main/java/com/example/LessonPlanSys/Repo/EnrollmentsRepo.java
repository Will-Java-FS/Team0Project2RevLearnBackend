package com.example.LessonPlanSys.Repo;

import com.example.LessonPlanSys.Model.Enrollments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentsRepo extends JpaRepository<Enrollments, Integer> {

}
