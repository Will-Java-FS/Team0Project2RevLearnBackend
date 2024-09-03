package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.UserLessonStatus;
import java.util.*;

public interface UserLessonStatusService {

    UserLessonStatus createUserLessonStatus(UserLessonStatus userLessonStatus);
    List<UserLessonStatus> getAllUserLessonStatus();
    Optional<UserLessonStatus> getUserLessonStatusById(Integer id);
    UserLessonStatus updateUserLessonStatus(Integer id, UserLessonStatus userLessonStatus);
    boolean deleteUserLessonStatus(Integer id);
}
