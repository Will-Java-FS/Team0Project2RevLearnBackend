package com.example.LessonPlanSys.Repo;

import com.example.LessonPlanSys.Model.ForumPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumPostRepo extends JpaRepository<ForumPost, Integer> {
    @Query(value = "SELECT * FROM project2.forum_posts WHERE user_id=?1", nativeQuery = true )
    List<ForumPost> findByUser_id(int userId);

    @Query(value = "SELECT * FROM project2.forum_posts WHERE forum_id=?1", nativeQuery = true )
    List<ForumPost> findByForum_id(int forumId);
}
