package com.example.LessonPlanSys.Controller;

import com.example.LessonPlanSys.Model.ForumPost;
import com.example.LessonPlanSys.Service.ForumPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/forumpost")
public class ForumPostController {

    ForumPostService fps;
    @Autowired
    public ForumPostController(ForumPostService fps){
        this.fps=fps;
    }

    @GetMapping
    public ResponseEntity<List<ForumPost>> getAllForumPost(){
        return ResponseEntity.status(HttpStatus.OK).body(fps.findAll());
    }
    @GetMapping("/{forumpost_id}")
    public ResponseEntity<Optional<ForumPost>> getForumPostById(@PathVariable int forumpost_id){
        return ResponseEntity.status(HttpStatus.OK).body(fps.findByForumpost_id(forumpost_id));
    }
    @GetMapping("/user/{user_id}")
    public ResponseEntity<List<ForumPost>> getForumPostByUserId(@PathVariable int user_id){
        return ResponseEntity.status(HttpStatus.OK).body(fps.findByUser_id(user_id));
    }

    @PostMapping
    public ResponseEntity<ForumPost> addForumPost(@RequestBody ForumPost forumpost) {
        ForumPost createdForumPost = fps.addForumPost(forumpost);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdForumPost);
    }

    @DeleteMapping("/{forumpost_id}")
    public ResponseEntity<Void> deleteForumPost(@PathVariable int forumpost_id) {
        fps.deleteForumPost(forumpost_id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{forumpost_id}")
    public ResponseEntity<ForumPost> updateForumPost(@PathVariable int forumpost_id, @RequestBody ForumPost forumPost) {
        ForumPost updatedForumPost = fps.updateForumPost(forumpost_id, forumPost);
        return updatedForumPost != null ? ResponseEntity.ok(updatedForumPost) : ResponseEntity.notFound().build();
    }

}
