package com.example.LessonPlanSys.Model;

import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", updatable = false)
    private int course_id;

    @Column(name = "course_name", nullable = false)
    private String course_name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "teacher_id", nullable = false)
    private int teacher_id;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private Timestamp created_at3;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updated_at3;
}
// https://trello.com/c/odeKevEG