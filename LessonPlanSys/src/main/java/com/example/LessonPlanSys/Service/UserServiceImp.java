package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.User;
import com.example.LessonPlanSys.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService{
    UserRepo userRepo;
    @Autowired
    public UserServiceImp(UserRepo userRepo)
    {
        this.userRepo = userRepo;
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
    public User updateUserById(int id, User nUser) {
        User oUser = userRepo.getUserByUId(id);
        if(oUser != null)
        {
            userRepo.save(nUser);
            return getUserByUID(id);
        }
        return null;
    }



}
