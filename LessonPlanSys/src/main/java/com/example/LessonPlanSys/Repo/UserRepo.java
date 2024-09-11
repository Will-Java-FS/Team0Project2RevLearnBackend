package com.example.LessonPlanSys.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.LessonPlanSys.Model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    // Automatically generated query
    List<User> findByRole(String role);

    @Query(value = "SELECT * FROM users WHERE role = ?1", nativeQuery = true)
    List<User> findAllByRole(String role);
    
    User findByUsername(String username);

    @Query(value = "SELECT * FROM users WHERE user_id = ?1 AND role = ?2", nativeQuery = true)
    User findByUserIdAndRole(int userId, String role);
}