package com.example.LessonPlanSys.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.*;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import com.example.LessonPlanSys.Model.LessonCourse;

@Entity
@Table(name = "LessonPlans")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class LessonPlan {
    @Getter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="lesson_plan_id",updatable = false)
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
    private Timestamp lp_created_at;

    @Getter
    @Setter
    @Column(name="updated_at")
    private Timestamp lp_updated_at;

}
