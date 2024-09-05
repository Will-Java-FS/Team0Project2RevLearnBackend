package com.example.LessonPlanSys.Controller;

import com.example.LessonPlanSys.Exception.NotFoundException;
import com.example.LessonPlanSys.Model.UserLessonStatus;
import com.example.LessonPlanSys.Service.UserLessonStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RequestMapping("/status")
@RestController
public class UserLessonStatusController {

    @Autowired
    private final UserLessonStatusService userLessonStatusService;

    public UserLessonStatusController(UserLessonStatusService userLessonStatusService){
        this.userLessonStatusService = userLessonStatusService;
    }

    @PostMapping
    public ResponseEntity<UserLessonStatus> createUserLessonStatus(@RequestBody UserLessonStatus userLessonStatus){
        UserLessonStatus created = userLessonStatusService.createUserLessonStatus(userLessonStatus);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserLessonStatus>> getAllUserLessonStatuses(){
        try {
            List<UserLessonStatus> statuses = userLessonStatusService.getAllUserLessonStatus();
            return new ResponseEntity<>(statuses, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserLessonStatus> getUserLessonStatusById(@PathVariable Integer id) {
        try {
            Optional<UserLessonStatus> status = userLessonStatusService.getUserLessonStatusById(id);
            return status.map(ResponseEntity::ok)
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserLessonStatus> updateUserLessonStatus(@PathVariable Integer id, @RequestBody UserLessonStatus userLessonStatus) {
        UserLessonStatus updated = userLessonStatusService.updateUserLessonStatus(id, userLessonStatus);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserLessonStatus(@PathVariable Integer id) {
        if(userLessonStatusService.deleteUserLessonStatus(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
