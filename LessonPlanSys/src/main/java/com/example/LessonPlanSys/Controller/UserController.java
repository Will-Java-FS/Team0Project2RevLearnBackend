package com.example.LessonPlanSys.Controller;


import com.example.LessonPlanSys.Model.Course;
import com.example.LessonPlanSys.Model.Program;
import com.example.LessonPlanSys.Model.User;
import com.example.LessonPlanSys.Service.ProgramServiceImpl;
import com.example.LessonPlanSys.Service.UserService;
import com.example.LessonPlanSys.Service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImp userService;
    ProgramServiceImpl programService;

    @Autowired
    public UserController(UserServiceImp userService,ProgramServiceImpl programService) {
        this.userService = userService;
        this.programService = programService;
    }

    @GetMapping("role/{role}")
    public ResponseEntity<List<User>> getAlluserbyrole(@PathVariable String role) {
        System.out.println(role);
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsersByRole(role));
    }

    @GetMapping("/{user_id}/{role}")
    ResponseEntity<User> getuserbyrole(@PathVariable int user_id, @PathVariable String role) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByRoleAndId(user_id, role));
    }

    // Get list of all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Get user by ID
    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUser(@PathVariable("user_id") int id) {
        User retrievedUser = userService.getUserByUID(id);
        return retrievedUser == null
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.status(HttpStatus.OK).body(retrievedUser);
    }

    // Add a new user
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {

        user.setUserCreatedAt(ZonedDateTime.now());
        user.setUserUpdatedAt(ZonedDateTime.now());
        User savedUser = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    // Update a user's username and password
    @PutMapping("update/{user_id}")
    public ResponseEntity<User> updateUser(@PathVariable("user_id") int id, @RequestBody User user) {
        User existingUser = userService.getUserByUID(id);
        existingUser.setUserUpdatedAt(ZonedDateTime.now());
        existingUser.setUsername(user.getUsername());
        existingUser.setPasswordHash(user.getPasswordHash());
        User updatedUser = userService.updateUserById(id, existingUser);
        return updatedUser == null
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
                : ResponseEntity.ok(updatedUser);
    }

    // Delete a user
    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("user_id") int id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    // Enroll a user in a program
    @PutMapping("{user_id}/enroll/{program_id}")
    public ResponseEntity<User> enrollUserInProgram(@PathVariable("user_id") int userId, @PathVariable("program_id") int programId) {
        User updatedUser = userService.enrollUserInProgram(userId, programId);
        return updatedUser == null
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.ok(updatedUser);
    }
}
