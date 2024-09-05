package com.example.LessonPlanSys.Service;

import com.example.LessonPlanSys.Model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserByUID(int id);

    User addUser(User nUser);

    void deleteUserById(int id);
    List<User>getAllUsersByRole(String role);

    User getUsersByRoleandId(int userId, String role);

    User updateUserById(int id, User nUser);

    User enrollUserInProgram(int user_id, int program_id);
}