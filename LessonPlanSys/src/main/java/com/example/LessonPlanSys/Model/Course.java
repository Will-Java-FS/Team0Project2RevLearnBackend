package com.example.LessonPlanSys.Model;

import java.sql.Timestamp;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @Column(name = "course_name", nullable = false, unique = true)
    private String courseName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "teacher_id", nullable = false)
    private int teacherId;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)

    private Timestamp course_created_at;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp course_updated_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id", nullable = false)
    private Program program;
}
// https://trello.com/c/odeKevEG