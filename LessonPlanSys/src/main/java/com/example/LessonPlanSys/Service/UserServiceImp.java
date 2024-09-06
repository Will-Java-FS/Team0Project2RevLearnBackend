package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Exception.NotFoundException;
import com.example.LessonPlanSys.Model.Program;
import com.example.LessonPlanSys.Model.User;
import com.example.LessonPlanSys.Repo.ProgramRepo;
import com.example.LessonPlanSys.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService, UserDetailsService{
    UserRepo userRepo;
    ProgramRepo programRepo;
    @Autowired
    public UserServiceImp(UserRepo userRepo, ProgramRepo programRepo)
    {
        this.userRepo = userRepo;
        this.programRepo = programRepo;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserByUID(int id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User addUser(User nUser) {
        return userRepo.save(nUser);
    }

    @Override
    public void deleteUserById(int id) {
        userRepo.deleteById(id);
    }

    @Override
    public List<User> getAllUsersByRole(String role) {
        return userRepo.findByRole(role);
    }

    @Override
    public User getUsersByRoleandId(int user_id, String role) {
        return userRepo.findByUserIdAndRole(user_id,role);
    }

    @Override
    public User updateUserById(int id, User nUser) {
        User oUser = userRepo.findById(id).orElse(null);
        if(oUser != null)
        {
            userRepo.save(nUser);
            return getUserByUID(id);
        }
        return null;
    }

    @Override
    public User enrollUserInProgram(int user_id, int program_id) {
        Program newProgram = programRepo.findById(program_id).orElse(null);
        User student = userRepo.findById(user_id).orElse(null);
        if (newProgram != null && student != null) {
            student.setProgram(newProgram);
            return userRepo.save(student);

        }
        return null;
    }

    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        User user = userRepo.findByUsername(username);
        if (user != null) {
            return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPasswordHash())
                .roles(user.getRole()) // Set roles if applicable
                .build();
        } else {
            throw new NotFoundException("User not found with username: " + username);
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