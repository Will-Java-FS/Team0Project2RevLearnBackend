package com.example.LessonPlanSys.Repo;

import com.example.LessonPlanSys.Model.ForumPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumPostRepo extends JpaRepository<ForumPost, Integer> {
}
