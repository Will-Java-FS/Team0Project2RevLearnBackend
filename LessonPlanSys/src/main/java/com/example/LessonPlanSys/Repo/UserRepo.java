package com.example.LessonPlanSys.Repo;

import org.springframework.stereotype.Repository;
import com.example.LessonPlanSys.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface UserRepo extends JpaRepository<User,Integer>{
   // User findById(int id);
}
