package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.ForumPost;
import com.example.LessonPlanSys.Repo.ForumPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ForumPostService {

    private  ForumPostRepo fpr;
    @Autowired
    public ForumPostService (ForumPostRepo fpr) {
        this.fpr=fpr;
    }

    public List<ForumPost> findAll() {
        return fpr.findAll();
    }

    public Optional<ForumPost> findByForumpost_id(int forumpost_id) {
        return fpr.findById(forumpost_id);
    }

    public ForumPost addForumPost(ForumPost forumPost) {
        return fpr.save(forumPost);
    }

    public  void deleteForumPost(int forumpsot_id) {
        fpr.deleteById(forumpsot_id);
    }

    public  ForumPost updateForumPost(int forumpsot_id, ForumPost updatedForumPost) {
        if (fpr.existsById(forumpsot_id)) {
            updatedForumPost.setForumpost_id(forumpsot_id);
            return fpr.save(updatedForumPost);
        }
        return null;
    }

    public List<ForumPost> findByUser_id(int userId) {
        return fpr.findByUser_id(userId);
    }
}
