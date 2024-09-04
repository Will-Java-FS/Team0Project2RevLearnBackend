package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Exception.NotFoundException;
import com.example.LessonPlanSys.Model.Program;
import com.example.LessonPlanSys.Model.User;
import com.example.LessonPlanSys.Repo.ProgramRepo;
import com.example.LessonPlanSys.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService, UserDetailsService {
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
        return userRepo.getAllUsers();
    }

   @Override
    public User getUserByUID(int id) {
        return userRepo.getUserByUId(id);
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
        return userRepo.findByUser_idAndRole(user_id,role);
    }

   @Override
    public User updateUserById(int id, User nUser) {
        User oUser = userRepo.getUserByUId(id);
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
        User student = userRepo.getUserByUId(user_id);
        if (newProgram != null && student != null) {
            student.setProgram(newProgram);
            return userRepo.save(student);

        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        Optional<User> user = userRepo.findByUsername(username);
        if (user.isPresent()) {
            return org.springframework.security.core.userdetails.User.builder()
                .username(user.get().getUsername())
                .password(user.get().getPasswordHash())
                .roles(user.get().getRole()) // Set roles if applicable
                .build();
        } else {
            throw new NotFoundException("User not found with username: " + username);
        }
    }
}
