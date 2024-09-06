package com.example.LessonPlanSys.Controller;
import com.example.LessonPlanSys.Model.Course;
import com.example.LessonPlanSys.Model.Forum;
import com.example.LessonPlanSys.Service.CourseServiceImp;
import com.example.LessonPlanSys.Service.ForumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/forum")
public class ForumController {

    ForumServiceImpl fs;
    CourseServiceImp csi;
    @Autowired
    public ForumController(ForumServiceImpl fs, CourseServiceImp csi){
        this.fs=fs;
        this.csi=csi;
    }

    @GetMapping
    public ResponseEntity<List<Forum>> getAllForum(){
        return ResponseEntity.status(HttpStatus.OK).body(fs.getAllForums());
    }
    @GetMapping("/{forum_id}")
    public ResponseEntity<Forum> getForumById(@PathVariable int forum_id){
        return ResponseEntity.status(HttpStatus.OK).body(fs.getForum(forum_id));
    }

    @PostMapping("/add/{course_id}")
    public ResponseEntity<Forum> addForum( @PathVariable int course_id, @RequestBody Forum forum) {
        Course course = csi.getById(course_id);
        forum.setCourse(course);
        forum.setForumCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        //forum.getForumUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        Forum createdForum = fs.addForum(forum);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdForum);
    }

    @DeleteMapping("/{forum_id}")
    public ResponseEntity<Void> deleteForum(@PathVariable int forum_id) {
        fs.deleteForum(forum_id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{forum_id}")
    public ResponseEntity<Forum> updateForum(@PathVariable int forum_id, @RequestBody Forum forum) {
        Forum existingForum = fs.getForum(forum_id);
        existingForum.setTitle(forum.getTitle());
        Forum updatedForum = fs.updateForum(forum_id, existingForum);
        return updatedForum != null ? ResponseEntity.ok(updatedForum) : ResponseEntity.notFound().build();
    }
}
