package com.example.LessonPlanSys.Controller;

import java.util.List;

import com.example.LessonPlanSys.Model.Course;
import com.example.LessonPlanSys.Model.User;
import com.example.LessonPlanSys.Service.UserService;
import com.example.LessonPlanSys.Service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/user")
@Validated
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        try {
            // Basic validation for required fields
            if (user.getEmail() == null || user.getEmail().isEmpty() ||
                    user.getUsername() == null || user.getUsername().isEmpty() ||
                    user.getPasswordHash() == null || user.getPasswordHash().isEmpty()) {
                return ResponseEntity.badRequest().body(null);
            }

            // Check if the program is provided and valid
            if (user.getProgram() != null && user.getProgram().getProgramId() == 0) {
                return ResponseEntity.badRequest().body(null); // or provide a meaningful error message
            }

            User createdUser = userService.addUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        try {
            User authenticatedUser = userService.authenticateUser(user.getUsername(), user.getPasswordHash());
            if (authenticatedUser != null) {
                String token = userService.generateAuthToken(authenticatedUser); // Implement token generation
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during login");
        }
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable String role) {
        List<User> users = userService.getAllUsersByRole(role);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}/role/{role}")
    public ResponseEntity<User> getUserByRoleAndId(@PathVariable("id") int userId, @PathVariable String role) {
        try {
            User user = userService.getUserByRoleAndId(userId, role);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUser(@PathVariable("user_id") int id) {
        User retrievedUser = userService.getUserByUID(id);
        return retrievedUser == null
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.status(HttpStatus.OK).body(retrievedUser);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User savedUser = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<User> updateUser(@PathVariable("user_id") int id, @RequestBody User user) {
        User updatedUser = userService.updateUserById(id, user);
        return updatedUser == null
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
                : ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("user_id") int id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}/enroll/{programId}")
    public ResponseEntity<User> enrollUserInProgram(@PathVariable("userId") int userId,
            @PathVariable("programId") int programId) {
        try {
            User updatedUser = userService.enrollUserInProgram(userId, programId);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}