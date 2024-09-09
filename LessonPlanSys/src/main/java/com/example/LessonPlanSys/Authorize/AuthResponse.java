package com.example.LessonPlanSys.Authorize;

import com.example.LessonPlanSys.Model.Program;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    
    private String token;
    private String username;
    private Integer userId;
    private String role;
    private Program program;
}