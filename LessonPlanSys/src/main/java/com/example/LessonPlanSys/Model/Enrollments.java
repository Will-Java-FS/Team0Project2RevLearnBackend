package com.example.LessonPlanSys.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "enrollments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enrollments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(mappedBy = "enrollments", cascade = CascadeType.ALL)
    private List<User> users;

    @ManyToOne(mappedBy = "enrollments", cascade = CascadeType.ALL)
    private List<Course> courses;

    @Column(nullable = false, length = 255)
    private String status;
}
