package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.Program;
import com.example.LessonPlanSys.Model.User;
import com.example.LessonPlanSys.Repo.ProgramRepo;
import com.example.LessonPlanSys.Repo.UserRepo;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private final UserRepo userRepo;
    private final ProgramRepo programRepo;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    public UserServiceImp(UserRepo userRepo, ProgramRepo programRepo) {
        this.userRepo = userRepo;
        this.programRepo = programRepo;
    }

    @Override
    public User addUser(User user) {
        if (user == null) {
            logger.error("Attempted to add a null user");
            throw new IllegalArgumentException("User must not be null");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            logger.error("Email is null or empty for user");
            throw new IllegalArgumentException("Email must not be null or empty");
        }

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            logger.error("Username is null or empty for user");
            throw new IllegalArgumentException("Username must not be null or empty");
        }

        if (user.getPasswordHash() == null) {
            logger.error("Password hash is null for user with email {}", user.getEmail());
            throw new IllegalArgumentException("Password hash must not be null");
        }

        try {
            User savedUser = userRepo.save(user);
            logger.info("User with ID {} successfully added", savedUser.getUserId());
            return savedUser;
        } catch (Exception e) {
            logger.error("Error occurred while saving user: {}", e.getMessage());
            throw new RuntimeException("Error occurred while adding user", e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserByUID(int id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void deleteUserById(int id) {
        if (!userRepo.existsById(id)) {
            logger.error("Attempted to delete user with ID {} that does not exist", id);
            throw new RuntimeException("User not found");
        }
        userRepo.deleteById(id);
        logger.info("User with ID {} successfully deleted", id);
    }

    @Override
    public List<User> getAllUsersByRole(String role) {
        return userRepo.findByRole(role);
    }

    @Override
    public User getUserByRoleAndId(int userId, String role) {
        return userRepo.findByUserIdAndRole(userId, role)
                .orElseThrow(() -> new RuntimeException("User with ID " + userId + " and role " + role + " not found"));
    }

    @Override
    public User updateUserById(int id, User updatedUser) {
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPasswordHash(updatedUser.getPasswordHash());
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setRole(updatedUser.getRole());
        existingUser.setProgram(updatedUser.getProgram());

        try {
            User savedUser = userRepo.save(existingUser);
            logger.info("User with ID {} successfully updated", savedUser.getUserId());
            return savedUser;
        } catch (Exception e) {
            logger.error("Error occurred while updating user: {}", e.getMessage());
            throw new RuntimeException("Error occurred while updating user", e);
        }
    }

    @Override
    public User enrollUserInProgram(int userId, int programId) {
        Program program = programRepo.findById(programId)
                .orElseThrow(() -> new RuntimeException("Program not found"));

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setProgram(program);

        try {
            User updatedUser = userRepo.save(user);
            logger.info("User with ID {} successfully enrolled in program ID {}", userId, programId);
            return updatedUser;
        } catch (Exception e) {
            logger.error("Error occurred while enrolling user in program: {}", e.getMessage());
            throw new RuntimeException("Error occurred while enrolling user in program", e);
        }
    }

    @Override
    public User authenticateUser(String username, String password) {
        User user = userRepo.findByUsername(username);
        if (user != null && user.getPasswordHash().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public String generateAuthToken(User user) {
        // Implement your token generation logic here
        return "generated_token";
    }
}