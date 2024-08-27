package com.example.LessonPlanSys.Repo;

import com.example.LessonPlanSys.Model.Forum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ForumRepo extends JpaRepository<Forum, Integer> {

    @Query("SELECT COUNT(id) FROM Course where course_id=:id")
    Integer courseExists(@Param("id") Integer id);
}
