package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Exception.*;
import com.example.LessonPlanSys.Model.UserLessonStatus;
import com.example.LessonPlanSys.Repo.UserLessonStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserLessonStatusServiceImp implements UserLessonStatusService{

    @Autowired
    private final UserLessonStatusRepo userLessonStatusRepo;

    public UserLessonStatusServiceImp(UserLessonStatusRepo userLessonStatusRepo){
        this.userLessonStatusRepo = userLessonStatusRepo;
    }

    @Override
    public UserLessonStatus createUserLessonStatus (UserLessonStatus userLessonStatus){
        if(userLessonStatusRepo.existsById(userLessonStatus.getId())){
            throw new AlreadyExistsException("UserLessonStatus with id" + userLessonStatus.getId() +
                    " already exists.");
        }
        return userLessonStatusRepo.save(userLessonStatus);
    }

    @Override
    public List<UserLessonStatus> getAllUserLessonStatus(){
        List<UserLessonStatus> statuses = userLessonStatusRepo.findAll();
        if(statuses.isEmpty()){
            throw new NotFoundException("No UserLessonStatuses found.");
        }
        return statuses;
    }

    @Override
    public Optional<UserLessonStatus> getUserLessonStatusById(Integer id){
        Optional<UserLessonStatus> status = userLessonStatusRepo.findById(id);
        if(status.isEmpty()){
            throw new NotFoundException("UserLessonStatus with id" + id + " not found.");
        }
        return status;
    }

    @Override
    public UserLessonStatus updateUserLessonStatus(Integer id, UserLessonStatus userLessonStatus){
        if(!userLessonStatusRepo.existsById(id)){
            throw new NotFoundException("UserLessonStatus with id" + id + " not found.");
        }
        userLessonStatus.setId(id);
        return userLessonStatusRepo.save(userLessonStatus);
    }

    @Override
    public boolean deleteUserLessonStatus(Integer id){
        if(userLessonStatusRepo.existsById(id)){
            userLessonStatusRepo.deleteById(id);
            return true;
        }
        throw new NotFoundException("UserLessonStatus with id" + id + " not found.");
    }
}
