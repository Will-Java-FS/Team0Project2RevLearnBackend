package com.example.LessonPlanSys.Controller;

import com.example.LessonPlanSys.Model.Forum;
import com.example.LessonPlanSys.Model.ForumPost;
import com.example.LessonPlanSys.Model.Program;
import com.example.LessonPlanSys.Model.User;
import com.example.LessonPlanSys.Service.ForumPostService;
import com.example.LessonPlanSys.Service.ForumServiceImpl;
import com.example.LessonPlanSys.Service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.time.LocalTime.now;

@RestController
@RequestMapping("/forumpost")
public class ForumPostController {

    ForumPostService fps;
    ForumServiceImpl fsi;
    UserServiceImp usi;
    @Autowired
    public ForumPostController(ForumPostService fps, ForumServiceImpl fsi, UserServiceImp usi){
        this.fps=fps;
        this.fsi=fsi;
        this.usi=usi;
    }

    @GetMapping
    public ResponseEntity<List<ForumPost>> getAllForumPost(){
        return ResponseEntity.status(HttpStatus.OK).body(fps.findAll());
    }
    @GetMapping("/{forumpost_id}")
    public ResponseEntity<ForumPost> getForumPostById(@PathVariable int forumpost_id){
        return ResponseEntity.status(HttpStatus.OK).body(fps.findByForumpost_id(forumpost_id));
    }
    @GetMapping("/user/{user_id}")
    public ResponseEntity<List<ForumPost>> getForumPostByUserId(@PathVariable int user_id){
        return ResponseEntity.status(HttpStatus.OK).body(fps.findByUser_id(user_id));
    }

    @PostMapping("/{user_id}/{forum_id}")
    public ResponseEntity<ForumPost> addForumPost(@RequestBody ForumPost forumpost, @PathVariable int user_id, @PathVariable int forum_id){
        User user  = usi.getUserByUID(user_id);
        forumpost.setUser(user);
        Forum forum = fsi.getForum(forum_id);
        forumpost.setForum(forum);
        forumpost.setPost_created_at(Timestamp.valueOf(LocalDateTime.now()));
        forumpost.setPost_updated_at(Timestamp.valueOf(LocalDateTime.now()));
        fps.addForumPost(forumpost);
        return ResponseEntity.status(HttpStatus.CREATED).body(forumpost);
    }

    @DeleteMapping("/{forumpost_id}")
    public ResponseEntity<Void> deleteForumPost(@PathVariable int forumpost_id) {
        fps.deleteForumPost(forumpost_id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{forumpost_id}/{user_id}/{forum_id}")
    public ResponseEntity<ForumPost> updateForumPost(@PathVariable int forumpost_id,@PathVariable int user_id, @PathVariable int forum_id, @RequestBody ForumPost forumPost) {
        ForumPost existingforumPost = fps.findByForumpost_id(forumpost_id);
        User user  = usi.getUserByUID(user_id);
        existingforumPost.setUser(user);
        Forum forum = fsi.getForum(forum_id);
        existingforumPost.setForum(forum);
        existingforumPost.setPost_updated_at(Timestamp.valueOf(LocalDateTime.now()));
        existingforumPost.setPost_text(forumPost.getPost_text());
        ForumPost updatedForumPost = fps.updateForumPost(forumpost_id, existingforumPost);
        return updatedForumPost != null ? ResponseEntity.ok(updatedForumPost) : ResponseEntity.notFound().build();
    }

}
