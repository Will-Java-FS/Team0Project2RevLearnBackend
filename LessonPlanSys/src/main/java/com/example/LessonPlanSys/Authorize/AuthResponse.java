package com.example.LessonPlanSys.Authorize;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    
    private String username;
    private Integer userId;
    private String role;
    private String token;
}