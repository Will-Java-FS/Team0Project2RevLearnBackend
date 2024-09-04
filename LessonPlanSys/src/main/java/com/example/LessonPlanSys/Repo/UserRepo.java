package com.example.LessonPlanSys.Repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.LessonPlanSys.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>{

    @Query(value = "SELECT * FROM users WHERE role=?1", nativeQuery = true )
    List<User> findAllByRole(String role);


    @Query(value = "SELECT * FROM users WHERE user_id=?1 and role=?2", nativeQuery = true)
    User findByUser_idAndRole(int user_id, String role);

    List<User> findByRole(String role);

}
