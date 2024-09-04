package com.example.LessonPlanSys.Controller;


import com.example.LessonPlanSys.Model.Course;
import com.example.LessonPlanSys.Model.User;
import com.example.LessonPlanSys.Service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    UserServiceImp userService;

    @Autowired
    public UserController(UserServiceImp userService) {
        this.userService = userService;
    }

    @GetMapping("role/{role}")
    public ResponseEntity<List<User>> getAlluserbyrole(@PathVariable String role) {
        System.out.println(role);
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsersByRole(role));
    }

    @GetMapping("/{user_id}/{role}")
    ResponseEntity<User> getuserbyrole(@PathVariable int user_id, @PathVariable String role) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsersByRoleandId(user_id, role));
    }
    // Previous controller
//    UserServiceImp userService;
//    @Autowired
//    public UserController(UserServiceImp userService)
//    {
//        this.userService = userService;
//    }



    // Get list of all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
        User retrievedUser = userService.getUserByUID(id);
        return retrievedUser == null
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.status(HttpStatus.OK).body(retrievedUser);
    }

    // Add a new user
    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User savedUser = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    // Update a user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        User updatedUser = userService.updateUserById(id, user);
        return updatedUser == null
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
                : ResponseEntity.ok(updatedUser);
    }

    // Delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id) {
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
