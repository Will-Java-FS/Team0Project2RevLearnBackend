package com.example.LessonPlanSys.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.*;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import com.example.LessonPlanSys.Model.LessonCourse;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "lesson_plans")
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
    @Column(name="description")
    private String description;

    @Getter
    @Setter
    @Column(name="real_world_application")
    private String realWorldApplication;

    @Getter
    @Setter
    @Column(name="implementation")
    private String implementation;

    @Getter
    @Setter
    @Column(name="summary")
    private String summary;

    @Getter
    @Setter
    @CreationTimestamp
    @Column(name="created_at")
    private Timestamp lp_created_at;

    @Getter
    @Setter
    @UpdateTimestamp
    @Column(name="updated_at")
    private Timestamp lp_updated_at;

}
