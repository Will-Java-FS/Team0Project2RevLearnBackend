package com.example.LessonPlanSys.Controller;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LessonPlanSys.Authorize.AuthResponse;
import com.example.LessonPlanSys.Authorize.JwtService;
import com.example.LessonPlanSys.Authorize.LoginForm;
import com.example.LessonPlanSys.Exception.NotFoundException;
import com.example.LessonPlanSys.Model.User;
import com.example.LessonPlanSys.Repo.UserRepo;
import com.example.LessonPlanSys.Service.ProgramServiceImpl;
import com.example.LessonPlanSys.Service.UserServiceImp;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    @Autowired
    UserServiceImp userService;
    ProgramServiceImpl programService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    public UserController(UserServiceImp userService,ProgramServiceImpl programService, UserRepo userRepo) {
        this.userService = userService;
        this.programService = programService;
        this.userRepo = userRepo;
    }

    // register a new user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        User existingUser = userRepo.findByUsername(user.getUsername());

        if(existingUser != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }

        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        userRepo.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    // user can login
@PostMapping("/login")
public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginForm loginForm) {
    try {
        // Authenticate the user using AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginForm.username(), loginForm.password())
        );

        // Check if authentication is successful
        if (authentication.isAuthenticated()) {
            // Retrieve the user from the repository
            User user = userRepo.findByUsername(loginForm.username());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AuthResponse(null, null, null, null, null));
            }

            // Generate JWT token upon successful authentication
            String token = jwtService.generateToken(userService.loadUserByUsername(loginForm.username()));

            // Construct and return the AuthResponse object
            AuthResponse authResponse = new AuthResponse(token, user.getUsername(), user.getUserId(), user.getRole(), user.getProgram());
            return ResponseEntity.ok(authResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse("Invalid credentials", null, null, null, null));
        }
    } catch (BadCredentialsException e) {
        // Handle incorrect credentials
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse("Invalid credentials", null, null, null, null));
    } catch (Exception e) {
        // Handle any other exceptions
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AuthResponse("An error occurred", null, null, null, null));
    }
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
