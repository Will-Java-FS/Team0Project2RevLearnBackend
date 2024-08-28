package com.example.LessonPlanSys.Repo;

import com.example.LessonPlanSys.Model.UserLessonStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLessonStatusRepo extends JpaRepository <UserLessonStatus, Integer> {

}
