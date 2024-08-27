package com.example.LessonPlanSys.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.*;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class LessonPlan {
    @Getter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id",updatable = false)
    private int lesson_plan_id;

    @Getter
    @Setter
    @Column(name="title")
    private String title;

    @Getter
    @Setter
    @Column(name="content")
    private String content;

    @Getter
    @Setter
    @Column(name="created_at")
    private Timestamp created_at2;

    @Getter
    @Setter
    @Column(name="updated_at")
    private Timestamp updated_at2;

}
