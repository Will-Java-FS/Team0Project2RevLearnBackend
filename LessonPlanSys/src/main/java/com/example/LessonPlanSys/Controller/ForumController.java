package com.example.LessonPlanSys.Controller;
import com.example.LessonPlanSys.Model.Forum;
import com.example.LessonPlanSys.Service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/forum")
public class ForumController {

    ForumService fs;
    @Autowired
    public ForumController(ForumService fs){
        this.fs=fs;
    }

    @GetMapping
    public ResponseEntity<List<Forum>> getAllForumPost(){
        return ResponseEntity.status(HttpStatus.OK).body(fs.getAllForums());
    }
    @GetMapping("/{forum_id}")
    public ResponseEntity<Forum> getForumPostById(@PathVariable int forum_id){
        return ResponseEntity.status(HttpStatus.OK).body(fs.getForum(forum_id));
    }

    @PostMapping("/add/{forum_id}")
    public ResponseEntity<Forum> addForumPost(@PathVariable int forum_id, @RequestBody Forum forum) {
        Forum createdForumPost = fs.addForum(forum_id,forum);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdForumPost);
    }

    @DeleteMapping("/{forum_id}")
    public ResponseEntity<Void> deleteForum(@PathVariable int forum_id) {
        fs.deleteForum(forum_id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{forum_id}")
    public ResponseEntity<Forum> updateForumPost(@PathVariable int forum_id, @RequestBody Forum forum) {
        Forum updatedForum = fs.updateForum(forum_id, forum);
        return updatedForum != null ? ResponseEntity.ok(updatedForum) : ResponseEntity.notFound().build();
    }
}
