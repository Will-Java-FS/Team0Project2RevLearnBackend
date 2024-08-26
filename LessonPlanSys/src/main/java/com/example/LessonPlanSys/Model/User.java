package com.example.LessonPlanSys.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {

    @Getter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int user_id;
}
