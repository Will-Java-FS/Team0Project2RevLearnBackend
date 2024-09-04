package com.example.LessonPlanSys.Controller;

import com.example.LessonPlanSys.Authorize.*;
import com.example.LessonPlanSys.Exception.NotFoundException;
//import com.example.LessonPlanSys.Model.Course;
import com.example.LessonPlanSys.Model.User;
import com.example.LessonPlanSys.Repo.UserRepo;
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
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserServiceImp userServiceImp;
    private final UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @Autowired
    public UserController(UserServiceImp userService, UserRepo userRepo) {
        this.userServiceImp = userService;
        this.userRepo = userRepo;
    }

    // register a new user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        Optional<User> existingUser = userRepo.findByUsername(user.getUsername());

        if(existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }

        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        userRepo.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    // user can login
    @PostMapping("/login")
    public AuthResponse loginUser(@RequestBody LoginForm loginForm){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            loginForm.username(), loginForm.password()));

        if(authentication.isAuthenticated()){
            Optional<User> optionalUser = userRepo.findByUsername(loginForm.username());
            User user = optionalUser.get();
            String token = jwtService.generateToken(userServiceImp.loadUserByUsername(loginForm.username()));

            if(optionalUser.isEmpty()){
                throw new NotFoundException("User not found");
            }
            return new AuthResponse(user.getUsername(), user.getUser_id(), user.getRole(), token);
        }
        else{
            throw new NotFoundException("Invalid credentials");
        }
    }

    @GetMapping("role/{role}")
    public ResponseEntity<List<User>> getAlluserbyrole(@PathVariable String role) {
        System.out.println(role);
        return ResponseEntity.status(HttpStatus.OK).body(userServiceImp.getAllUsersByRole(role));
    }

    @GetMapping("/{user_id}/{role}")
    ResponseEntity<User> getuserbyrole(@PathVariable int user_id, @PathVariable String role) {
        return ResponseEntity.status(HttpStatus.OK).body(userServiceImp.getUsersByRoleandId(user_id, role));
    }
    // Previous controller
//    UserServiceImp userService;
//    @Autowired
//    public UserController(UserServiceImp userService)
//    {
//        this.userService = userService;
//    }

    @Autowired
    UserService userService;

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
