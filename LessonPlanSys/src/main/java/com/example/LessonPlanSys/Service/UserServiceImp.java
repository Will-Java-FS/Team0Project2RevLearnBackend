package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{
    UserRepo userRepo;
    @Autowired
    public UserServiceImp(UserRepo userRepo)
    {
        this.userRepo = userRepo;
    }

}
