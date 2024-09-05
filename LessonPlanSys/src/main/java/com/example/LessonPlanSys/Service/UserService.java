package com.example.LessonPlanSys.Service;

import java.util.List;

import com.example.LessonPlanSys.Model.User;

public interface UserService {

    User addUser(User user);

    List<User> getAllUsers();

    User getUserByUID(int id);

    void deleteUserById(int id);

    List<User> getAllUsersByRole(String role);

    User getUserByRoleAndId(int userId, String role);

    User updateUserById(int id, User updatedUser);

    User enrollUserInProgram(int userId, int programId);

    User authenticateUser(String username, String password);

    String generateAuthToken(User user);
}