package com.example.LessonPlanSys.Repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.LessonPlanSys.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>{
    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<User> getAllUsers();

    @Query(value = "SELECT * FROM users WHERE user_id = ?1")
    User getUserByUId(int id);


}
