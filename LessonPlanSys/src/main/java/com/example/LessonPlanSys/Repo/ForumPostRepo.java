package com.example.LessonPlanSys.Repo;

import com.example.LessonPlanSys.Model.ForumPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumPostRepo extends JpaRepository<ForumPost, Integer> {
    @Query(value = "SELECT * FROM ForumPost WHERE user_id=?1", nativeQuery = true )
    List<ForumPost> findByUser_id(int userId);
}
